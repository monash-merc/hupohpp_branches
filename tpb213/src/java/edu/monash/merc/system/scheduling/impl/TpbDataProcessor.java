/*
 * Copyright (c) 2011-2012, Monash e-Research Centre
 * (Monash University, Australia)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 	* Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 	* Redistributions in binary form must reproduce the above copyright
 * 	  notice, this list of conditions and the following disclaimer in the
 * 	  documentation and/or other materials provided with the distribution.
 * 	* Neither the name of the Monash University nor the names of its
 * 	  contributors may be used to endorse or promote products derived from
 * 	  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.system.scheduling.impl;

import edu.monash.merc.common.name.ChromType;
import edu.monash.merc.common.name.DbAcType;
import edu.monash.merc.domain.DSVersion;
import edu.monash.merc.domain.TPBGene;
import edu.monash.merc.dto.GPMEntryBean;
import edu.monash.merc.dto.NXEntryBean;
import edu.monash.merc.dto.hpa.HPAEntryBean;
import edu.monash.merc.service.DMSystemService;
import edu.monash.merc.system.config.SystemPropConts;
import edu.monash.merc.system.config.SystemPropSettings;
import edu.monash.merc.system.remote.FTPFileGetter;
import edu.monash.merc.system.parser.gpm.GPMRSSReader;
import edu.monash.merc.system.parser.gpm.GPMSyndEntry;
import edu.monash.merc.system.parser.gpm.GPMXMLParser;
import edu.monash.merc.system.parser.nextprot.NxXMLParser;
import edu.monash.merc.system.remote.HttpHpaFileGetter;
import edu.monash.merc.system.scheduling.DataProcessor;
import edu.monash.merc.system.version.DSVCombination;
import edu.monash.merc.system.version.TLVersionTrack;
import edu.monash.merc.system.xml.HPAWSXmlParser;
import edu.monash.merc.system.xml.WSXmlInputFactory;
import edu.monash.merc.util.DMUtil;
import edu.monash.merc.util.file.DMFileGZipper;
import edu.monash.merc.wsclient.biomart.BioMartClient;
import edu.monash.merc.wsclient.biomart.GeneConsts;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 28/02/12
 * Time: 11:38 AM
 */
@Service
public class TpbDataProcessor implements DataProcessor {

    private static String DOWNLOAD_DATA_DIR = "download_data";

    private static String NEXTPROT_CHROME_NAME = "chromosome";

    private String chromosomeTypes;

    private boolean dbEnsemblImportEnabled = true;

    private boolean dsNxImportEnabled = true;

    private boolean dsGpmImportEnabled = true;

    private boolean dsHpaImportEnabled = true;

    @Autowired
    private SystemPropSettings systemPropSettings;

    @Autowired
    private DMSystemService dmSystemService;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void setSystemPropSettings(SystemPropSettings systemPropSettings) {
        this.systemPropSettings = systemPropSettings;
    }

    public void setDmSystemService(DMSystemService dmSystemService) {
        this.dmSystemService = dmSystemService;
    }

    @PostConstruct
    public void tpbInit() {
        this.chromosomeTypes = this.systemPropSettings.getPropValue(SystemPropConts.CHROMOSOME_IMPORT_TYPE);

        String ensemblEnabled = this.systemPropSettings.getPropValue(SystemPropConts.DATASOURCE_ENSEMBL_IMPORT_ENABLE);
        String dsNxEnabled = this.systemPropSettings.getPropValue(SystemPropConts.DATASOURCE_NEXTPORT_IMPORT_ENABLE);
        String dsGpmEnabled = this.systemPropSettings.getPropValue(SystemPropConts.DATASOURCE_GPM_IMPORT_ENABLE);
        String dsHpaEnabled = this.systemPropSettings.getPropValue(SystemPropConts.DATASOURCE_HPA_IMPORT_ENABLE);

        this.dbEnsemblImportEnabled = Boolean.valueOf(ensemblEnabled);
        this.dsNxImportEnabled = Boolean.valueOf(dsNxEnabled).booleanValue();
        this.dsGpmImportEnabled = Boolean.valueOf(dsGpmEnabled).booleanValue();
        this.dsHpaImportEnabled = Boolean.valueOf(dsHpaEnabled).booleanValue();
    }

    public void process() {

        Date importedTime = GregorianCalendar.getInstance().getTime();
        //import Genes for HUMAN from Ensembl Database (include all chromosome types)
        if (dbEnsemblImportEnabled) {
            System.out.println("======= start to import Ensembl DB");
            importEnsemblGenes(GeneConsts.HUMAN, null);
        }

        List<ChromType> requiredChromTypes = getChmTypes(chromosomeTypes);
        if (requiredChromTypes.isEmpty()) {
            requiredChromTypes = ChromType.allChroms();
        }

        System.out.println("===== start to import HPA datasource first ...");
        if (dsHpaImportEnabled) {
            logger.info("======= start to import HPA DS");
            long startTime = System.currentTimeMillis();
            processHPAData(requiredChromTypes, importedTime);
            long endTime = System.currentTimeMillis();
            logger.info("=====> HPA Datasource:  The total process time: " + (endTime - startTime) / 1000 + "seconds");
            //try to pause 1 minute after NextProt datasource completed
            rest(6000);
        }

        //start to process the gpm data
        if (dsGpmImportEnabled) {
            logger.info("======= start to import GPM DS");
            long startTime = System.currentTimeMillis();
            processGPMData(requiredChromTypes, importedTime);
            long endTime = System.currentTimeMillis();
            logger.info("=====> GPM Datasource:  The total process time: " + (endTime - startTime) / 1000 + "seconds");
            //try to pause 1 minute after gpm datasource completed
            rest(6000);
        }


        //start to process the nextprot data
        for (ChromType chromType : requiredChromTypes) {
            if (dsNxImportEnabled) {
                logger.info("======= start to import Nextprot DS");
                long startTime = System.currentTimeMillis();
                processNextProtData(chromType, importedTime);
                long endTime = System.currentTimeMillis();
                logger.info("=====> NeXtProt Datasource:  The total process time: " + (endTime - startTime) / 1000 + "seconds");
                //try to pause 1 minute between two chromosome files
                rest(6000);
            }
        }

        //start to generate the traffic Lights
        for (ChromType chromType : requiredChromTypes) {
            createTLByChromosome(chromType, importedTime);
            //try to pause 2 seconds between two chromosome type
            rest(2000);
        }
    }

    private void importEnsemblGenes(String species, String chromosome) {
        BioMartClient client = new BioMartClient();
        try {
            String wsURL = this.systemPropSettings.getPropValue(SystemPropConts.BIOMART_RESTFUL_WS_URL);
            client.configure(wsURL, species, chromosome);
            List<TPBGene> tpbGeneList = client.importGenes();
            logger.info("total gene size : " + tpbGeneList.size());
            this.dmSystemService.importTPBGenes(tpbGeneList);
            logger.info("======== imported the ensembl genes into database successfully");
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            client.release();
        }
    }

    //process the nextprot data
    private void processNextProtData(ChromType chromType, Date importedTime) {
        FTPFileGetter ftpFileGetter = new FTPFileGetter();
        String ftpHost = systemPropSettings.getPropValue(SystemPropConts.FTP_NX_SERVER_NAME);
        String ftpUserName = systemPropSettings.getPropValue(SystemPropConts.FTP_NX_USER_NAME);
        String ftpPassword = systemPropSettings.getPropValue(SystemPropConts.FTP_NX_USER_PASSWORD);
        String workingDir = systemPropSettings.getPropValue(SystemPropConts.FTP_NX_CHROMOSOME_REL_DIR);
        try {
            if (ftpFileGetter.connectAndLogin(ftpHost, ftpUserName, ftpPassword)) {
                ftpFileGetter.changeToWorkingDirectory(workingDir);
                ftpFileGetter.setPassiveMode(true);
                //get all chromosome file
                Vector<String> chromosomeFiles = ftpFileGetter.listFileNames();

                //set the binary download mode
                ftpFileGetter.binary();
                InputStream chromInputStream = null;
                for (String file : chromosomeFiles) {

                    if (StringUtils.contains(file, NEXTPROT_CHROME_NAME + "_" + chromType.chm() + ".xml")) {
                        String fileLastModifiedTime = ftpFileGetter.getLastModifiedTime(file);
                        System.out.println("===== file: " + file + " ---- last modified time: " + fileLastModifiedTime);
                        //check if the file hasn't been updated, then get the file

                        if (!checkUpToDate(DbAcType.NextProt, chromType, file, fileLastModifiedTime)) {

                            chromInputStream = ftpFileGetter.downloadFileStream(file);
                            DMFileGZipper gZipper = new DMFileGZipper();
                            String outFileName = StringUtils.substringBefore(file, ".gz");
                            String storeDir = this.systemPropSettings.getSystemBaseDir() + DOWNLOAD_DATA_DIR + File.separator;
                            gZipper.unzipFile(chromInputStream, storeDir + outFileName);

                            //call completePendingCommand to to finish command
                            if (!ftpFileGetter.completePendingCommand()) {
                                ftpFileGetter.logout();
                                ftpFileGetter.disconnect();
                                System.out.println("===== Failed to download the file");
                            }
                            FileInputStream fileInputStream = new FileInputStream(new File(storeDir + outFileName));
                            processNextProtXML(chromType, fileInputStream, importedTime, file, fileLastModifiedTime);
                        }
                    }
                }
            } else {
                logger.error("Failed to login the ftp server - " + ftpHost);
            }
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            try {
                ftpFileGetter.logout();
                ftpFileGetter.disconnect();
            } catch (Exception ftpEx) {
                //ignore whatever caught here
            }
        }
    }

    private boolean checkUpToDate(DbAcType dbAcType, ChromType chromType, String fileName, String timetoken) {
        return this.dmSystemService.checkUpToDate(dbAcType, chromType, fileName, timetoken);
    }

    private void processNextProtXML(ChromType chromType, InputStream fileInputStream, Date importedTime, String fileName, String timeToken) {
        NxXMLParser parser = new NxXMLParser();
        List<NXEntryBean> nxEntryBeans = parser.parseNextProtXML(fileInputStream);
        this.dmSystemService.saveNextProtDataEntryByChromosome(chromType, nxEntryBeans, importedTime, fileName, timeToken);
    }

    public void processGPMData(List<ChromType> chromTypes, Date importedTime) {
        FTPFileGetter ftpFileGetter = new FTPFileGetter();
        GPMRSSReader gpmrssReader = new GPMRSSReader();
        String url = systemPropSettings.getPropValue(SystemPropConts.GPM_RSS_FEED_URL);
        try {
            String gpmFtpServer = null;
            String gpmFtpUserName = "anonymous";
            String gpmFtpPassword = null;
            String workingDir = null;
            String gpm2tpbReleasedFile = null;
            GPMSyndEntry gpmSyndEntry = gpmrssReader.readRSS(url);
            if (gpmSyndEntry != null) {
                gpmFtpServer = gpmSyndEntry.getGmpFtpServer();
                workingDir = gpmSyndEntry.getTpbWorkDir();
                gpm2tpbReleasedFile = gpmSyndEntry.getReleasedTpbFileName();
            }
            if (ftpFileGetter.connectAndLogin(gpmFtpServer, gpmFtpUserName, gpmFtpPassword)) {
                ftpFileGetter.changeToWorkingDirectory(workingDir);
                ftpFileGetter.setPassiveMode(true);
                //set the binary download mode
                ftpFileGetter.binary();
                InputStream gpmInputStream = null;
                String fileLastModifiedTime = ftpFileGetter.getLastModifiedTime(gpm2tpbReleasedFile);
                System.out.println("===== file: " + gpm2tpbReleasedFile + " ---- last modified time: " + fileLastModifiedTime);

                if (!checkUpToDate(DbAcType.NextProt, null, gpm2tpbReleasedFile, fileLastModifiedTime)) {
                    gpmInputStream = ftpFileGetter.downloadFileStream(gpm2tpbReleasedFile);
                    DMFileGZipper gZipper = new DMFileGZipper();
                    String outFileName = StringUtils.substringBefore(gpm2tpbReleasedFile, ".gz");
                    String storeDir = this.systemPropSettings.getSystemBaseDir() + DOWNLOAD_DATA_DIR + File.separator;
                    gZipper.unzipFile(gpmInputStream, storeDir + outFileName);

                    //call completePendingCommand to to finish command
                    if (!ftpFileGetter.completePendingCommand()) {
                        ftpFileGetter.logout();
                        ftpFileGetter.disconnect();
                        System.out.println("===== Failed to download the file");
                    }

                    FileInputStream gpmFileInputStream = new FileInputStream(new File(storeDir + outFileName));
                    processGPMXML(chromTypes, gpmFileInputStream, importedTime, gpm2tpbReleasedFile, fileLastModifiedTime);
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            try {
                gpmrssReader.release();
                ftpFileGetter.logout();
                ftpFileGetter.disconnect();
            } catch (Exception ftpEx) {
                //ignore whatever caught here
            }
        }
    }

    private void processGPMXML(List<ChromType> chromTypes, InputStream gpmInputStream, Date importedTime, String fileName, String timeToken) {
        GPMXMLParser parser = new GPMXMLParser();
        List<GPMEntryBean> gpmEntryBeans = parser.parseGPMXML(gpmInputStream);
        this.dmSystemService.saveGPMDataEntry(chromTypes, gpmEntryBeans, importedTime, fileName, timeToken);
    }

    public void processHPAData(List<ChromType> chromTypes, Date importedTime) {

        HttpHpaFileGetter fileGetter = new HttpHpaFileGetter();
        String remoteHpaFile = this.systemPropSettings.getPropValue(SystemPropConts.HPA_DATA_RELEASE_LOCATION);
        String destFile = this.systemPropSettings.getSystemBaseDir() + DOWNLOAD_DATA_DIR + File.separator + "proteinatlas.xml";
        try {
            //start to import hpa xml file
            fileGetter.importHPAXML(remoteHpaFile, destFile);
            logger.info("=== finished to download proteinatlas file");
            HPAWSXmlParser parser = new HPAWSXmlParser();

            List<HPAEntryBean> hpaEntryBeans = parser.parseHPAXml(destFile, WSXmlInputFactory.getInputFactoryConfiguredForXmlConformance());
            logger.info("=== finished to parse proteinatlas xml file");
            if ((hpaEntryBeans != null) && (hpaEntryBeans.size() > 0)) {
                String hpaVersion = hpaEntryBeans.get(0).getHpaVersion();
                System.out.println("=== hpa version: " + hpaVersion + " total size: " + hpaEntryBeans.size());
                if (!checkUpToDate(DbAcType.HPA, null, remoteHpaFile, hpaVersion)) {
                    System.out.println("==== start to save hpa data");
                    this.dmSystemService.saveHPADataEntry(chromTypes, hpaEntryBeans, importedTime, remoteHpaFile, hpaVersion);
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public void createTLByChromosome(ChromType chromType, Date importedTime) {
        DSVersion nxDsVersion = this.dmSystemService.getCurrentDSVersionByChromDbs(DbAcType.NextProt, chromType);
        DSVersion gpmDsVersion = this.dmSystemService.getCurrentDSVersionByChromDbs(DbAcType.GPM, chromType);
        DSVersion hpaDsVersion = this.dmSystemService.getCurrentDSVersionByChromDbs(DbAcType.HPA, chromType);
        DSVersion paDsVersion = this.dmSystemService.getCurrentDSVersionByChromDbs(DbAcType.PA, chromType);
        List<TLVersionTrack> tlVersionTracks = DSVCombination.createTLVersionTracks(nxDsVersion, gpmDsVersion, hpaDsVersion, paDsVersion);
        if (tlVersionTracks != null) {
            logger.info("it will create total " + tlVersionTracks.size() + " traffic lights for " + chromType.chm());
            for (TLVersionTrack tlvTrack : tlVersionTracks) {
                createVersionTLByChromType(chromType, tlvTrack, importedTime);
            }
        }
    }

    private void createVersionTLByChromType(ChromType chromType, TLVersionTrack tlVersionTrack, Date createdTime) {
        try {
            this.dmSystemService.createVersionTLByChromType(chromType, tlVersionTrack, createdTime);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    private List<ChromType> getChmTypes(String chromTypes) {
        if (StringUtils.isBlank(chromTypes)) {
            return ChromType.allChroms();
        }
        if (StringUtils.equalsIgnoreCase(chromTypes, "all")) {
            return ChromType.allChroms();
        }

        List<ChromType> requiredChmTypes = new ArrayList<ChromType>();
        String[] ctypes = DMUtil.splitStrByDelim(chromTypes, ",");
        for (String type : ctypes) {
            ChromType aType = ChromType.fromType(type);
            if (!aType.equals(ChromType.UNKNOWN)) {
                requiredChmTypes.add(aType);
            }
        }
        return requiredChmTypes;
    }

    private void rest(long time) {
        //try to pause (time)
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
            //ignore whatever caught
        }
    }
}
