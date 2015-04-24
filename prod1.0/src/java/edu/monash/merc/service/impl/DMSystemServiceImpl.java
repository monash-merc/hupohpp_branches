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

package edu.monash.merc.service.impl;

import edu.monash.merc.common.name.ChromType;
import edu.monash.merc.common.name.ColorType;
import edu.monash.merc.common.name.DataType;
import edu.monash.merc.common.name.DbAcType;
import edu.monash.merc.common.page.Pagination;
import edu.monash.merc.common.sql.OrderBy;
import edu.monash.merc.domain.*;
import edu.monash.merc.domain.rifcs.RIFCSDataset;
import edu.monash.merc.dto.*;
import edu.monash.merc.dto.gpm.GPMEntryBean;
import edu.monash.merc.dto.hpa.HPAEntryBean;
import edu.monash.merc.dto.rifcs.RifcsInfoBean;
import edu.monash.merc.dto.tl.TLSearchBean;
import edu.monash.merc.exception.DMException;
import edu.monash.merc.mail.MailService;
import edu.monash.merc.rifcs.RifcsService;
import edu.monash.merc.service.*;
import edu.monash.merc.system.version.TLVersionTrack;
import edu.monash.merc.util.DMUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

/**
 * DMSystemServiceImpl class implements the DMSystemService interface which provides the TPB Data service operations.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 16/04/12 11:49 AM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@Transactional
@Qualifier("DMSystemService")
public class DMSystemServiceImpl implements DMSystemService {

    @Autowired
    protected GeneService geneService;

    @Autowired
    protected DBSourceService dbSourceService;

    @Autowired
    protected AccessionService accessionService;

    @Autowired
    protected AccessionTypeService accessionTypeService;

    @Autowired
    protected TPBDataTypeService tpbDataTypeService;

    @Autowired
    protected TLColorService tlColorService;

    @Autowired
    protected DSVersionService dsVersionService;

    @Autowired
    protected TPBVersionService tpbVersionService;

    @Autowired
    protected PEEvidenceService peEvidenceService;

    @Autowired
    protected NXAnnotationService nxAnnotationService;

    @Autowired
    protected NXAnnEvidenceService nxAnnEvidenceService;

    @Autowired
    protected NXIsoFormAnnService nxIsoFormAnnService;

    @Autowired
    protected TLGeneService tlGeneService;

    @Autowired
    protected TLService tlService;

    @Autowired
    protected TPBGeneService tpbGeneService;

    @Autowired
    protected RifcsDatasetService rifcsDatasetService;

    @Autowired
    private RifcsService rifcsService;

    @Autowired
    private MailService mailService;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void setGeneService(GeneService geneService) {
        this.geneService = geneService;
    }

    public void setDbSourceService(DBSourceService dbSourceService) {
        this.dbSourceService = dbSourceService;
    }

    public void setAccessionService(AccessionService accessionService) {
        this.accessionService = accessionService;
    }

    public void setAccessionTypeService(AccessionTypeService accessionTypeService) {
        this.accessionTypeService = accessionTypeService;
    }

    public void setTpbDataTypeService(TPBDataTypeService tpbDataTypeService) {
        this.tpbDataTypeService = tpbDataTypeService;
    }

    public void setTlColorService(TLColorService tlColorService) {
        this.tlColorService = tlColorService;
    }

    public void setDsVersionService(DSVersionService dsVersionService) {
        this.dsVersionService = dsVersionService;
    }

    public void setTpbVersionService(TPBVersionService tpbVersionService) {
        this.tpbVersionService = tpbVersionService;
    }

    public void setPeEvidenceService(PEEvidenceService peEvidenceService) {
        this.peEvidenceService = peEvidenceService;
    }

    public void setNxAnnotationService(NXAnnotationService nxAnnotationService) {
        this.nxAnnotationService = nxAnnotationService;
    }

    public void setNxAnnEvidenceService(NXAnnEvidenceService nxAnnEvidenceService) {
        this.nxAnnEvidenceService = nxAnnEvidenceService;
    }

    public void setNxIsoFormAnnService(NXIsoFormAnnService nxIsoFormAnnService) {
        this.nxIsoFormAnnService = nxIsoFormAnnService;
    }

    public void setTlGeneService(TLGeneService tlGeneService) {
        this.tlGeneService = tlGeneService;
    }

    public void setTlService(TLService tlService) {
        this.tlService = tlService;
    }

    public void setTpbGeneService(TPBGeneService tpbGeneService) {
        this.tpbGeneService = tpbGeneService;
    }

    public void setRifcsDatasetService(RifcsDatasetService rifcsDatasetService) {
        this.rifcsDatasetService = rifcsDatasetService;
    }

    public void setRifcsService(RifcsService rifcsService) {
        this.rifcsService = rifcsService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    //Gene Implementations

    /**
     * {@inheritDoc}
     */
    public void saveGene(Gene gene) {
        this.geneService.saveGene(gene);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeGene(Gene gene) {
        this.geneService.mergeGene(gene);
    }

    /**
     * {@inheritDoc}
     */
    public void updateGene(Gene gene) {
        this.geneService.updateGene(gene);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteGene(Gene gene) {
        this.geneService.deleteGene(gene);
    }

    /**
     * {@inheritDoc}
     */
    public Gene getGeneById(long id) {
        return this.geneService.getGeneById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteGeneById(long id) {
        this.geneService.deleteGeneById(id);
    }

    /**
     * {@inheritDoc}
     */
    public Gene getGeneByEnsgAndDbVersion(String ensgAccession, String dbSource, Date versionTime) {
        return this.geneService.getGeneByEnsgAndDbVersion(ensgAccession, dbSource, versionTime);
    }

    /**
     * {@inheritDoc}
     */
    public List<Gene> getGeneByDBSChromVersion(DbAcType dbAcType, ChromType chromType, Date versionTime) {
        return this.geneService.getGeneByDBSChromVersion(dbAcType, chromType, versionTime);
    }

    /**
     * {@inheritDoc}
     */
    public List<Gene> getGenesByTLGeneId(long tlGeneId) {
        return this.geneService.getGenesByTLGeneId(tlGeneId);
    }

    /**
     * {@inheritDoc}
     */
    public List<Accession> getAllAssociatedAccessionsByGeneId(long geneId) {
        return this.geneService.getAllAssociatedAccessionsByGeneId(geneId);
    }

    /**
     * {@inheritDoc}
     */
    public void saveDBSource(DBSource dbSource) {
        this.dbSourceService.saveDBSource(dbSource);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeDBSource(DBSource dbSource) {
        this.dbSourceService.mergeDBSource(dbSource);
    }

    /**
     * {@inheritDoc}
     */
    public void updateDBSource(DBSource dbSource) {
        this.dbSourceService.updateDBSource(dbSource);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteDBSourceById(long id) {
        this.dbSourceService.deleteDBSourceById(id);
    }

    /**
     * {@inheritDoc}
     */
    public DBSource getDBSourceById(long id) {
        return this.dbSourceService.getDBSourceById(id);
    }

    /**
     * {@inheritDoc}
     */
    public DBSource getDBSourceByName(String dbName) {
        return this.dbSourceService.getDBSourceByName(dbName);
    }

    /**
     * {@inheritDoc}
     */
    public void saveAccessionType(AccessionType accessionType) {
        this.accessionTypeService.saveAccessionType(accessionType);
    }

    /**
     * {@inheritDoc}
     */

    public void mergeAccessionType(AccessionType accessionType) {
        this.accessionTypeService.mergeAccessionType(accessionType);
    }

    /**
     * {@inheritDoc}
     */
    public void updateAccessionType(AccessionType accessionType) {
        this.accessionTypeService.updateAccessionType(accessionType);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAccessionType(AccessionType accessionType) {
        this.accessionTypeService.deleteAccessionType(accessionType);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAccessionTypeById(long id) {
        this.accessionTypeService.deleteAccessionTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public AccessionType getAccessionTypeById(long id) {
        return this.accessionTypeService.getAccessionTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public AccessionType getAccessionTypeByType(String typeName) {
        return this.accessionTypeService.getAccessionTypeByType(typeName);
    }

    /**
     * {@inheritDoc}
     */
    public void saveAccession(Accession accession) {
        this.accessionService.saveAccession(accession);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeAccession(Accession accession) {
        this.accessionService.mergeAccession(accession);
    }

    /**
     * {@inheritDoc}
     */
    public void updateAccession(Accession accession) {
        this.accessionService.updateAccession(accession);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAccession(Accession accession) {
        this.accessionService.deleteAccession(accession);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAccessionById(long id) {
        this.accessionService.deleteAccessionById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAccessionByAcId(String acId) {
        this.accessionService.deleteAccessionByAcId(acId);
    }

    /**
     * {@inheritDoc}
     */
    public Accession getAccessionById(long id) {
        return this.accessionService.getAccessionById(id);
    }

    /**
     * {@inheritDoc}
     */
    public Accession getAccessionByAccessionAcType(String accession, String acType) {
        return this.accessionService.getAccessionByAccessionAcType(accession, acType);
    }

    /**
     * {@inheritDoc}
     */
    public void saveTPBDataType(TPBDataType tpbDataType) {
        this.tpbDataTypeService.saveTPBDataType(tpbDataType);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTPBDataType(TPBDataType tpbDataType) {
        this.tpbDataTypeService.mergeTPBDataType(tpbDataType);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTPBDataType(TPBDataType tpbDataType) {
        this.tpbDataTypeService.updateTPBDataType(tpbDataType);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBDataType(TPBDataType tpbDataType) {
        this.tpbDataTypeService.deleteTPBDataType(tpbDataType);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBDataTypeById(long id) {
        this.tpbDataTypeService.deleteTPBDataTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public TPBDataType getTPBDataTypeById(long id) {
        return this.tpbDataTypeService.getTPBDataTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public TPBDataType getTPBDataTypeByTypeName(String type) {
        return this.tpbDataTypeService.getTPBDataTypeByTypeName(type);
    }

    /**
     * {@inheritDoc}
     */
    public List<TPBDataType> getSubTPBDataType(long id) {
        return this.tpbDataTypeService.getSubTPBDataType(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<TPBDataType> getSubTPBDataType(String dataType) {
        return this.tpbDataTypeService.getSubTPBDataType(dataType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLastLevelType(String dataType) {
        return this.tpbDataTypeService.isLastLevelType(dataType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLastLevelType(long id) {
        return this.tpbDataTypeService.isLastLevelType(id);
    }

    //DSVersion

    /**
     * {@inheritDoc}
     */
    public void saveDSVersion(DSVersion dsVersion) {
        this.dsVersionService.saveDSVersion(dsVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeDSVersion(DSVersion dsVersion) {
        this.dsVersionService.mergeDSVersion(dsVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void updateDSVersion(DSVersion dsVersion) {
        this.dsVersionService.updateDSVersion(dsVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteDSVersion(DSVersion dsVersion) {
        this.dsVersionService.deleteDSVersion(dsVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteDSVersionById(long id) {
        this.dsVersionService.deleteDSVersionById(id);
    }

    /**
     * {@inheritDoc}
     */
    public DSVersion getDSVersionById(long id) {
        return this.dsVersionService.getDSVersionById(id);
    }

    /**
     * {@inheritDoc}
     */
    public DSVersion getCurrentDSVersionByChromDbs(DbAcType dbAcType, ChromType chromType) {
        return this.dsVersionService.getCurrentDSVersionByChromDbs(dbAcType, chromType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkUpToDate(DbAcType dbAcType, ChromType chromType, String fileName, String timeToken) {
        return this.dsVersionService.checkUpToDate(dbAcType, chromType, fileName, timeToken);
    }

    /**
     * {@inheritDoc}
     */
    public List<DBVersionBean> getLatestDBSVersionByChromosome(ChromType chromType) {
        return this.dsVersionService.getLatestDBSVersionByChromosome(chromType);
    }

    //TPBVersion

    /**
     * {@inheritDoc}
     */
    public void saveTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionService.saveTPBVersion(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionService.mergeTPBVersion(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionService.updateTPBVersion(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionService.deleteTPBVersion(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public TPBVersion getTPBVersionById(long id) {
        return this.tpbVersionService.getTPBVersionById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBVersionById(long id) {
        this.tpbVersionService.deleteTPBVersionById(id);
    }

    /**
     * {@inheritDoc}
     */
    public TPBVersion getCurrentVersion(ChromType chromType, int trackTokne) {
        return this.tpbVersionService.getCurrentVersion(chromType, trackTokne);
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkTPBVersionAvailable(ChromType chromType, TLVersionTrack tlVersionTrack) {
        return this.tpbVersionService.checkTPBVersionAvailable(chromType, tlVersionTrack);
    }

    /**
     * {@inheritDoc}
     */
    public TPBVersion getCurrentTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken) {
        return this.tpbVersionService.getCurrentTPBVersionByChromTypeTrackToken(chromType, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public List<TPBVersion> getAllTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken) {
        return this.tpbVersionService.getAllTPBVersionByChromTypeTrackToken(chromType, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public List<TPBVersion> getAllTPBVersions() {
        return this.tpbVersionService.getAllTPBVersions();
    }

    public List<MaxDsTPBVersion> getAllChromosomeTPBVersionByMaxCombinatedDs() {
        return this.tpbVersionService.getAllChromosomeTPBVersionByMaxCombinatedDs();
    }

    /**
     * {@inheritDoc}
     */


    //Traffic Lights TLColor
    public void saveTLColor(TLColor tlColor) {
        this.tlColorService.saveTLColor(tlColor);
    }

    public void mergeTLColor(TLColor tlColor) {
        this.tlColorService.mergeTLColor(tlColor);
    }

    public void updateTLColor(TLColor tlColor) {
        this.tlColorService.updateTLColor(tlColor);
    }

    public void deleteTLColor(TLColor tlColor) {
        this.tlColorService.deleteTLColor(tlColor);
    }

    public void deleteTLColorById(long id) {
        this.tlColorService.deleteTLColorById(id);
    }

    public TLColor getTLColorById(long id) {
        return this.tlColorService.getTLColorById(id);
    }

    public TLColor getTLColorByColor(String color) {
        return this.tlColorService.getTLColorByColor(color);
    }

    public TLColor getTLColorByColorLevel(int colorLevel) {
        return this.tlColorService.getTLColorByColorLevel(colorLevel);
    }

    //Evidence
    public void savePEEvidence(PEEvidence peEvidence) {
        this.peEvidenceService.savePEEvidence(peEvidence);
    }

    public void mergePEEvidence(PEEvidence peEvidence) {
        this.peEvidenceService.mergePEEvidence(peEvidence);
    }

    public void updatePEEvidence(PEEvidence peEvidence) {
        this.peEvidenceService.updatePEEvidence(peEvidence);
    }

    public void deletePEEvidence(PEEvidence peEvidence) {
        this.peEvidenceService.deletePEEvidence(peEvidence);
    }

    public void deletePEEvidenceById(long id) {
        this.peEvidenceService.deletePEEvidenceById(id);
    }

    public PEEvidence getPEEvidenceById(long id) {
        return this.peEvidenceService.getPEEvidenceById(id);
    }

    public PEEvidence getPESummaryByGeneAndType(long geneId, DataType dataType) {
        return this.peEvidenceService.getPESummaryByGeneAndType(geneId, dataType);
    }

    public List<PEEvidence> getEvidencesByGeneAndType(long geneId, DataType dataType) {
        return this.peEvidenceService.getEvidencesByGeneAndType(geneId, dataType);
    }

    public TLEvidenceSummary getAllEvidencesByGeneAndType(long geneId, DataType dataType) {
        List<PEEvidence> allEvidences = this.getEvidencesByGeneAndType(geneId, dataType);
        return copyFromEvidences(allEvidences);
    }

    private TLEvidenceSummary copyFromEvidences(List<PEEvidence> peEvidences) {
        TLEvidenceSummary evidenceSummary = new TLEvidenceSummary();
        if (peEvidences != null) {
            List<TLEvidence> tlEvidenceList = new ArrayList<TLEvidence>();
            for (PEEvidence peEvidence : peEvidences) {
                int colorLevel = peEvidence.getColorLevel();
                Gene gene = peEvidence.getGene();
                long geneId = gene.getId();
                String geneName = gene.getDisplayName();
                String ensemblId = gene.getEnsgAccession();
                if (StringUtils.isBlank(ensemblId)) {
                    ensemblId = "N/A";
                }
                long evidenceId = peEvidence.getId();
                String evidence = peEvidence.getEvidenceValue();

                //identified accession
                Accession identifiedAc = peEvidence.getIdentifiedAccession();
                String identifiedAccession = identifiedAc.getAccession();

                //primary DBSource
                DBSource primaryDbSource = gene.getDbSource();
                String dbSourceName = primaryDbSource.getDbName();

                TPBDataType tpbDataType = peEvidence.getTpbDataType();
                String dataType = tpbDataType.getDataType();
                String dataTypeDisplayName = tpbDataType.getDisplayName();
                String typeShortName = StringUtils.replace(dataType, "_", " ");
                String hyperLink = peEvidence.getHyperLink();
                TLEvidence tlEvidence = new TLEvidence();
                tlEvidence.setGeneId(geneId);
                tlEvidence.setGeneName(geneName);
                tlEvidence.setEnsemblId(ensemblId);
                tlEvidence.setEvidenceId(evidenceId);
                tlEvidence.setEvidence(evidence);
                tlEvidence.setIdentifiedAccession(identifiedAccession);
                tlEvidence.setDbSource(dbSourceName);
                tlEvidence.setTpbDataType(dataType);
                tlEvidence.setTypeShortName(typeShortName);
                tlEvidence.setTypeDisplayName(dataTypeDisplayName);
                tlEvidence.setColorLevel(colorLevel);
                tlEvidence.setHyperLink(hyperLink);

                boolean lastLevelType = this.isLastLevelType(tpbDataType.getId());
                tlEvidence.setLastLevel(lastLevelType);
                tlEvidence.setTlLevel(tpbDataType.getTlLevel());
                tlEvidenceList.add(tlEvidence);
            }
            //set the evidence list
            evidenceSummary.setTlEvidences(tlEvidenceList);
        }
        return evidenceSummary;
    }

    public TLEvidenceSummary getPETLSummary(long tlGeneId, DataType dataType) {
        TrafficLight trafficLight = this.getTrafficLightById(tlGeneId);
        //get the traffic light gene
        TLGene tlGene = trafficLight.getTlGene();
        //get the TPBDataType
        TPBDataType tpbDataType = this.getTPBDataTypeByTypeName(dataType.type());
        //Create TLEvidenceSummary
        TLEvidenceSummary tlEvidenceSummary = new TLEvidenceSummary();
        //create a traffic light TLEvidenceSummaryHeader
        TLEvidenceSummaryHeader tlEvidenceSummaryHeader = generateTLEvidenceSummaryHeaderByTLGene(tlGene, null, tpbDataType);
        //set the TLEvidenceSummaryHeader
        tlEvidenceSummary.setTlEvidenceSummaryHeader(tlEvidenceSummaryHeader);

        //get all genes which are associated with this traffic light gene
        List<Gene> genes = this.getGenesByTLGeneId(tlGeneId);
        //create a list of TLEvidence Bean to store the TLEvidence
        List<TLEvidence> tlEvidences = new ArrayList<TLEvidence>();
        for (Gene gene : genes) {
            PEEvidence peEvidence = this.getPESummaryByGeneAndType(gene.getId(), dataType);
            if (peEvidence != null) {
                //create a TLEvidence Bean
                TLEvidence tlEvidence = covertToTLEvidence(gene, tpbDataType, peEvidence);
                boolean lastLevelType = this.isLastLevelType(tpbDataType.getId());
                tlEvidence.setLastLevel(lastLevelType);
                tlEvidence.setTlLevel(tpbDataType.getTlLevel());
                tlEvidences.add(tlEvidence);
            }
        }
        if (tlEvidences.size() != 0) {
            tlEvidenceSummary.setTlEvidences(tlEvidences);
        }
        return tlEvidenceSummary;
    }

//    public TLEvidenceSummary getPETLSummary(long tlGeneId, DataType dataType) {
//        TrafficLight trafficLight = this.getTrafficLightById(tlGeneId);
//        //get the traffic light gene
//        TLGene tlGene = trafficLight.getTlGene();
//        //get the TPBDataType
//        TPBDataType tpbDataType = this.getTPBDataTypeByTypeName(dataType.type());
//        //Create TLEvidenceSummary
//        TLEvidenceSummary tlEvidenceSummary = new TLEvidenceSummary();
//        //create a traffic light TLEvidenceSummaryHeader
//        TLEvidenceSummaryHeader tlEvidenceSummaryHeader = generateTLEvidenceSummaryHeaderByTLGene(tlGene, null, tpbDataType);
//        //set the TLEvidenceSummaryHeader
//        tlEvidenceSummary.setTlEvidenceSummaryHeader(tlEvidenceSummaryHeader);
//
//        //get all genes which are associated with this traffic light gene
//        List<Gene> genes = this.getGenesByTLGeneId(tlGeneId);
//        //create a list of TLEvidence Bean to store the TLEvidence
//        List<TLEvidence> tlEvidences = new ArrayList<TLEvidence>();
//        for (Gene gene : genes) {
//
//            List<TPBDataType> subTPBDataTypes = this.getSubTPBDataType(tpbDataType.getId());
//
//            //if this DataType is already a last level of DataType
//            if (subTPBDataTypes == null || subTPBDataTypes.size() == 0) {
//                PEEvidence peEvidence = this.getPESummaryByGeneAndType(gene.getId(), dataType);
//                if (peEvidence != null) {
//                    //create a TLEvidence Bean
//                    TLEvidence tlEvidence = generateTLEvidenceBean(gene, tpbDataType, peEvidence);
//                    tlEvidences.add(tlEvidence);
//                }
//                //get all evidences for all sub DataTypes
//            } else {
//                for (TPBDataType subTpbDataType : subTPBDataTypes) {
//                    String subDType = subTpbDataType.getDataType();
//                    DataType subDataType = DataType.fromType(subDType);
//
//                    PEEvidence peEvidence = this.getPESummaryByGeneAndType(gene.getId(), subDataType);
//                    if (peEvidence != null) {
//                        //Create TLEvidence DTO Bean
//                        TLEvidence tlEvidence = generateTLEvidenceBean(gene, subTpbDataType, peEvidence);
//                        tlEvidences.add(tlEvidence);
//                    }
//                }
//            }
//        }
//        if (tlEvidences.size() != 0) {
//            tlEvidenceSummary.setTlEvidences(tlEvidences);
//        }
//        return tlEvidenceSummary;
//    }

    private TLEvidenceSummaryHeader generateTLEvidenceSummaryHeaderByTLGene(TLGene tlGene, String dbSourceName, TPBDataType tpbDataType) {
        String tlGeneName = tlGene.getDisplayName();
        String tlEnsgAc = tlGene.getEnsgAccession();
        if (StringUtils.isBlank(tlEnsgAc)) {
            tlEnsgAc = "N/A";
        }
        String tpbDataTypeName = tpbDataType.getDataType();
        String typeDisplayName = tpbDataType.getDisplayName();
        String typeShortName = StringUtils.replace(tpbDataTypeName, "_", " ");

        TLEvidenceSummaryHeader tlEvidenceSummaryHeader = new TLEvidenceSummaryHeader();
        tlEvidenceSummaryHeader.setGeneName(tlGeneName);
        tlEvidenceSummaryHeader.setEnsemblId(tlEnsgAc);
        tlEvidenceSummaryHeader.setTpbDataType(tpbDataTypeName);
        tlEvidenceSummaryHeader.setTypeShortName(typeShortName);
        tlEvidenceSummaryHeader.setTypeDisplayName(typeDisplayName);
        //set the dbSource Name
        tlEvidenceSummaryHeader.setDbSourceName(dbSourceName);

        return tlEvidenceSummaryHeader;
    }

    private TLEvidence covertToTLEvidence(Gene gene, TPBDataType tpbDataType, PEEvidence peEvidence) {
        int colorLevel = peEvidence.getColorLevel();
        long geneId = gene.getId();
        String geneName = gene.getDisplayName();
        String ensemblId = gene.getEnsgAccession();
        if (StringUtils.isBlank(ensemblId)) {
            ensemblId = "N/A";
        }
        long evidenceId = peEvidence.getId();
        String evidence = peEvidence.getEvidenceValue();

        //identified accession
        Accession identifiedAc = peEvidence.getIdentifiedAccession();
        String identifiedAccession = identifiedAc.getAccession();

        //primary DBSource
        DBSource primaryDbSource = gene.getDbSource();
        String dbSourceName = primaryDbSource.getDbName();

        String dataType = tpbDataType.getDataType();
        String dataTypeDisplayName = tpbDataType.getDisplayName();
        String typeShortName = StringUtils.replace(dataType, "_", " ");
        TLEvidence tlEvidence = new TLEvidence();
        tlEvidence.setGeneId(geneId);
        tlEvidence.setGeneName(geneName);
        tlEvidence.setEnsemblId(ensemblId);
        tlEvidence.setEvidenceId(evidenceId);
        tlEvidence.setEvidence(evidence);
        tlEvidence.setIdentifiedAccession(identifiedAccession);
        tlEvidence.setDbSource(dbSourceName);
        tlEvidence.setTpbDataType(dataType);
        tlEvidence.setTypeShortName(typeShortName);
        tlEvidence.setTypeDisplayName(dataTypeDisplayName);
        tlEvidence.setColorLevel(colorLevel);
        return tlEvidence;
    }

    public TLEvidenceSummary getTLPESummaryBySrcGene(String dbSource, long geneId, DataType dataType) {
        //get the gene
        Gene gene = this.getGeneById(geneId);
        //get the TPBDataType
        TPBDataType tpbDataType = this.getTPBDataTypeByTypeName(dataType.type());


        //Create TLEvidenceSummary
        TLEvidenceSummary tlEvidenceSummary = new TLEvidenceSummary();
        //create a traffic TLEvidenceSummaryHeader
        TLEvidenceSummaryHeader tlEvidenceSummaryHeader = generateTLEvidenceSummaryHeaderByGene(gene, dbSource, tpbDataType);
        //set the TLEvidenceBaseInfo
        tlEvidenceSummary.setTlEvidenceSummaryHeader(tlEvidenceSummaryHeader);

        List<TLEvidence> tlEvidences = new ArrayList<TLEvidence>();

        List<TPBDataType> subTPBDataTypes = this.getSubTPBDataType(tpbDataType.getId());
        //if this DataType is already a last level of DataType
        if (subTPBDataTypes == null || subTPBDataTypes.size() == 0) {
            PEEvidence peEvidence = this.getPESummaryByGeneAndType(gene.getId(), dataType);
            if (peEvidence != null) {
                //create TLEvidence Bean
                TLEvidence tlEvidence = covertToTLEvidence(gene, tpbDataType, peEvidence);
                tlEvidence.setLastLevel(true);
                tlEvidence.setTlLevel(tpbDataType.getTlLevel());
                tlEvidences.add(tlEvidence);
            }
            //get all evidences for all sub DataTypes
        } else {
            for (TPBDataType subTpbDataType : subTPBDataTypes) {
                String subDType = subTpbDataType.getDataType();
                DataType subDataType = DataType.fromType(subDType);

                PEEvidence peEvidence = this.getPESummaryByGeneAndType(gene.getId(), subDataType);
                if (peEvidence != null) {
                    //create TLEvidence Bean
                    TLEvidence tlEvidence = covertToTLEvidence(gene, subTpbDataType, peEvidence);
                    //check whether it's last level of the TPBDataType or not
                    boolean lastLevelType = this.isLastLevelType(subTpbDataType.getId());
                    tlEvidence.setLastLevel(lastLevelType);
                    tlEvidence.setTlLevel(subTpbDataType.getTlLevel());
                    tlEvidences.add(tlEvidence);
                }
            }
        }

        if (tlEvidences.size() != 0) {
            tlEvidenceSummary.setTlEvidences(tlEvidences);
        }
        return tlEvidenceSummary;
    }

    private TLEvidenceSummaryHeader generateTLEvidenceSummaryHeaderByGene(Gene gene, String dbSourceName, TPBDataType tpbDataType) {
        String tlGeneName = gene.getDisplayName();
        String tlEnsgAc = gene.getEnsgAccession();
        if (StringUtils.isBlank(tlEnsgAc)) {
            tlEnsgAc = "N/A";
        }
        String tpbDataTypeName = tpbDataType.getDataType();
        String typeDisplayName = tpbDataType.getDisplayName();
        String typeShortName = StringUtils.replace(tpbDataTypeName, "_", " ");

        TLEvidenceSummaryHeader tlEvidenceSummaryHeader = new TLEvidenceSummaryHeader();
        tlEvidenceSummaryHeader.setGeneName(tlGeneName);
        tlEvidenceSummaryHeader.setEnsemblId(tlEnsgAc);
        tlEvidenceSummaryHeader.setTpbDataType(tpbDataTypeName);
        tlEvidenceSummaryHeader.setTypeShortName(typeShortName);
        tlEvidenceSummaryHeader.setTypeDisplayName(typeDisplayName);
        tlEvidenceSummaryHeader.setDbSourceName(dbSourceName);
        return tlEvidenceSummaryHeader;
    }

    //nextprot annotations
    public void saveNXAnnotation(NXAnnotation nxAnnotation) {
        this.nxAnnotationService.saveNXAnnotation(nxAnnotation);
    }

    public void updateNXAnnotation(NXAnnotation nxAnnotation) {
        this.nxAnnotationService.updateNXAnnotation(nxAnnotation);
    }

    public void deleteNXAnnotation(NXAnnotation nxAnnotation) {
        this.nxAnnotationService.deleteNXAnnotation(nxAnnotation);
    }

    public void deleteNXAnnotationById(long id) {
        this.nxAnnotationService.deleteNXAnnotationById(id);
    }

    public NXAnnotation getNXAnnotationById(long id) {
        return this.nxAnnotationService.getNXAnnotationById(id);
    }

    //nextprot annotation evidence
    public void saveNXAnnEvidence(NXAnnEvidence nxAnnEvidence) {
        this.nxAnnEvidenceService.saveNXAnnEvidence(nxAnnEvidence);
    }

    public void updateNXAnnEvidence(NXAnnEvidence nxAnnEvidence) {
        this.nxAnnEvidenceService.updateNXAnnEvidence(nxAnnEvidence);
    }

    public void deleteNXAnnEvidence(NXAnnEvidence nxAnnEvidence) {
        this.nxAnnEvidenceService.deleteNXAnnEvidence(nxAnnEvidence);
    }

    public void deleteNXAnnEvidenceById(long id) {
        this.nxAnnEvidenceService.deleteNXAnnEvidenceById(id);
    }

    public NXAnnEvidence getNXAnnEvidenceById(long id) {
        return this.nxAnnEvidenceService.getNXAnnEvidenceById(id);
    }

    //annotation isoform annotation
    public void saveNXIsoFormAnn(NXIsoFormAnn nxIsoFormAnn) {
        this.nxIsoFormAnnService.saveNXIsoFormAnn(nxIsoFormAnn);
    }

    public void updateNXIsoFormAnn(NXIsoFormAnn nxIsoFormAnn) {
        this.nxIsoFormAnnService.updateNXIsoFormAnn(nxIsoFormAnn);
    }

    public void deleteNXIsoFormAnn(NXIsoFormAnn nxIsoFormAnn) {
        this.nxIsoFormAnnService.deleteNXIsoFormAnn(nxIsoFormAnn);
    }

    public void deleteNXIsoFormAnnById(long id) {
        this.nxIsoFormAnnService.deleteNXIsoFormAnnById(id);
    }

    public NXIsoFormAnn getNXIsoFormAnnById(long id) {
        return this.nxIsoFormAnnService.getNXIsoFormAnnById(id);
    }

    private DSVersion createDSVersionByDbsChrom(ChromType chromType, DbAcType dbAcType, Date importedTime, String fileName, String timeToken) {
        //check the current nextprot tpbversion
        DSVersion latestDSVersion = this.getCurrentDSVersionByChromDbs(dbAcType, chromType);

        if (latestDSVersion == null) {
            latestDSVersion = new DSVersion();
            latestDSVersion.setCreatedTime(importedTime);
            latestDSVersion.setDbSource(dbAcType.type());
            latestDSVersion.setChromosome(chromType.chm());
            latestDSVersion.setFileName(fileName);
            latestDSVersion.setTimestampToken(timeToken);
            latestDSVersion.setVersionNo(1);

            this.saveDSVersion(latestDSVersion);
            return latestDSVersion;
        } else {
            int latestVersionNum = latestDSVersion.getVersionNo();
            DSVersion currentDSVersion = new DSVersion();
            currentDSVersion.setCreatedTime(importedTime);
            currentDSVersion.setDbSource(dbAcType.type());
            currentDSVersion.setChromosome(chromType.chm());
            currentDSVersion.setFileName(fileName);
            currentDSVersion.setTimestampToken(timeToken);
            currentDSVersion.setVersionNo(latestVersionNum + 1);
            this.saveDSVersion(currentDSVersion);
            return currentDSVersion;
        }
    }

    public void saveNextProtDataEntryByChromosome(ChromType chromType, List<NXEntryBean> nxEntryBeans, Date importedTime, String fileName, String timeToken) {

        logger.info("TPB is starting to save the nextprot data.");
        //create new nextprot DSVersion
        DSVersion nxDSVersion = createDSVersionByDbsChrom(chromType, DbAcType.NextProt, importedTime, fileName, timeToken);
        boolean evidenceSaved = false;
        for (NXEntryBean nxEntryBean : nxEntryBeans) {
            GeneBean geneBean = nxEntryBean.getGeneBean();
            AccessionBean identifiedAcBean = nxEntryBean.getIdentifiedAccessionBean();

            List<Accession> savedAcs = new ArrayList<Accession>();

            List<DbSourceAcEntryBean> nxDbSourceAcEntryBeans = nxEntryBean.getDbSourceAcEntryBeans();
            for (DbSourceAcEntryBean nxDbSourceAcEntryBean : nxDbSourceAcEntryBeans) {
                //DBSource
                DBSourceBean dbSourceBean = nxDbSourceAcEntryBean.getDbSourceBean();
                //persist DBSource
                DBSource dbSource = persistDBSource(dbSourceBean);

                //Accession
                AccessionBean accessionBean = nxDbSourceAcEntryBean.getAccessionBean();
                String acType = accessionBean.getAcType();
                String acid = accessionBean.getAccession();

                //persist AccessionType
                AccessionType accessionType = persistAccessionType(acType);
                //persist Accession
                Accession accession = persistAccession(dbSource, accessionBean, accessionType);
                //if this accession is not added
                if (!savedAcs.contains(accession)) {
                    savedAcs.add(accession);
                }
            }

            //start to save gene
            String dbName = nxEntryBean.getDbSourceName();
            DBSource dbSource = this.getDBSourceByName(dbName);

            //persist Gene
            Gene nxGene = persistGene(geneBean, dbSource, savedAcs, importedTime);

            //get the identified accession
            String nextProtAc = identifiedAcBean.getAccession();
            String actType = identifiedAcBean.getAcType();
            Accession accession = this.getAccessionByAccessionAcType(nextProtAc, actType);

            //PE OTH CUR Evidence
            PEEvidenceBean peOThCurEvidenceBean = nxEntryBean.getPeOthCurEvidenceBean();
            if (peOThCurEvidenceBean != null) {
                //data type
                TPBDataTypeBean peOThCurDTypeBean = peOThCurEvidenceBean.getHPBDataTypeBean();
                String peOthTypeName = peOThCurDTypeBean.getDataType();
                TPBDataType peOThDType = this.getTPBDataTypeByTypeName(peOthTypeName);
                Evidence peOthCurEvidence = this.persistPEEvidence(nxGene, accession, peOThDType, peOThCurEvidenceBean, importedTime);
            }

            //get PE MS Ann and PE Anti Ann
            NXPeMsAntiEntryBean nxPeMsAntiEntryBean = nxEntryBean.getNxPeMsAntiEntryBean();

            //check if PE MS Ann, PE Antibody Ann Evidences are available
            if (nxPeMsAntiEntryBean != null) {
                List<PEEvidenceBean> nxPEMSAnnEvBeans = nxPeMsAntiEntryBean.getPeMsAnnEvidenceBeans();
                if (nxPEMSAnnEvBeans != null) {
                    for (PEEvidenceBean peMsAnEvidenceBean : nxPEMSAnnEvBeans) {
                        //data type
                        TPBDataTypeBean msTpbDTypeBean = peMsAnEvidenceBean.getHPBDataTypeBean();
                        String msType = msTpbDTypeBean.getDataType();
                        TPBDataType msTpbDType = this.getTPBDataTypeByTypeName(msType);
                        Evidence peMsAnn = this.persistPEEvidence(nxGene, accession, msTpbDType, peMsAnEvidenceBean, importedTime);
                        evidenceSaved = true;
                    }
                }

                PEEvidenceBean peAntiAnnEvidenceBean = nxPeMsAntiEntryBean.getPeAntiAnnEvidenceBean();
                if (peAntiAnnEvidenceBean != null) {
                    //data type
                    TPBDataTypeBean antiTpbDTypeBean = peAntiAnnEvidenceBean.getHPBDataTypeBean();
                    String antiType = antiTpbDTypeBean.getDataType();
                    TPBDataType antiTpbDType = this.getTPBDataTypeByTypeName(antiType);
                    Evidence peAntiAnn = this.persistPEEvidence(nxGene, accession, antiTpbDType, peAntiAnnEvidenceBean, importedTime);
                    evidenceSaved = true;
                }
            }
            /**
             //import the annotations
             List<NXAnnEntryBean> nxAnnEntryBeans = nxEntryBean.getNxAnnEntryBeanList();
             System.out.println("==============> nextprot annotation entry size: " + nxAnnEntryBeans.size());
             persistNXAnnoations(nxGene, accession, nxAnnEntryBeans);
             System.out.print("==============> finished to load the annotation");

             */
        }
        if (!evidenceSaved) {
            throw new DMException("no evidence from NextProt datasource is saved.");
        }
    }

    //Persist the gene (new or update)


    private Gene persistGeneForNx(GeneBean geneBean, DBSource dbSource, List<Accession> accessions, Date importedTime) {
        Gene nxGene = new Gene();
        nxGene.setDisplayName(geneBean.getDisplayName());
        nxGene.setChromosome(geneBean.getChromosome());
        nxGene.setEnsgAccession(geneBean.getEnsgAccession());
        nxGene.setStartPosition(geneBean.getStartPosition());
        nxGene.setEndPosition(geneBean.getEndPosition());
        nxGene.setBand(geneBean.getBand());
        nxGene.setStrand(geneBean.getStrand());
        nxGene.setDescription(geneBean.getDescription());

        //set a list of associated Accession
        nxGene.setAccessions(accessions);

        //set the identified DBSource
        nxGene.setDbSource(dbSource);

        //set the created time
        nxGene.setCreatedTime(importedTime);
        //set the last updated time
        nxGene.setLastUpdatedTime(importedTime);
        //just save this gene
        this.saveGene(nxGene);
        return nxGene;
    }


    private DBSource persistDBSource(DBSourceBean dbSourceBean) {
        String dbName = dbSourceBean.getDbName();
        DBSource dbSource = new DBSource();
        dbSource.setDbName(dbName);
        dbSource.setDbOwner(dbSourceBean.getDbOwner());
        dbSource.setDescription(dbSourceBean.getDescription());
        dbSource.setHyperLink(dbSourceBean.getHyperLink());
        dbSource.setPrimaryEvidences(dbSourceBean.isPrimaryEvidences());

        //try to find the DBSource from database
        DBSource foundDbSource = this.getDBSourceByName(dbName);
        if (foundDbSource == null) {
            this.saveDBSource(dbSource);
            foundDbSource = dbSource;
        } else {
            if (!foundDbSource.equals(dbSource)) {
                foundDbSource.setDbName(dbName);
                String dbOwner = dbSource.getDbOwner();
                if (StringUtils.isNotBlank(dbOwner)) {
                    foundDbSource.setDbOwner(dbOwner);
                }

                String hyperLink = dbSource.getHyperLink();
                if (StringUtils.isNotBlank(hyperLink)) {
                    foundDbSource.setHyperLink(hyperLink);
                }

                String desc = dbSource.getDescription();
                if (StringUtils.isNotBlank(desc)) {
                    foundDbSource.setDescription(desc);
                }

                boolean primaryEv = dbSource.isPrimaryEvidences();
                if (primaryEv) {
                    foundDbSource.setPrimaryEvidences(primaryEv);
                }
                this.mergeDBSource(foundDbSource);
            }
        }
        return foundDbSource;
    }

    private AccessionType persistAccessionType(String accessionType) {
        //accession type
        AccessionType foundAcType = this.getAccessionTypeByType(accessionType);
        if (foundAcType == null) {
            foundAcType = new AccessionType();
            foundAcType.setAcType(accessionType);
            this.saveAccessionType(foundAcType);
        }
        return foundAcType;
    }

    private Accession persistAccession(DBSource dbSource, AccessionBean accessionBean, AccessionType accessionType) {
        String accessionId = accessionBean.getAccession();
        Accession accession = new Accession();
        accession.setAccession(accessionId);
        accession.setDescription(accessionBean.getDescription());
        accession.setAcType(accessionType);
        accession.setDbSource(dbSource);
        Accession foundAc = this.getAccessionByAccessionAcType(accessionId, accessionType.getAcType());
        if (foundAc == null) {
            this.saveAccession(accession);
            foundAc = accession;
        } else {
            String desc = accessionBean.getDescription();
            if (StringUtils.isNotBlank(desc)) {
                foundAc.setDescription(accessionBean.getDescription());
            }
            foundAc.setAcType(accessionType);
            foundAc.setDbSource(dbSource);
            this.mergeAccession(foundAc);
        }
        return foundAc;
    }

    private PEEvidence persistPEEvidence(Gene gene, Accession accession, TPBDataType tpbDataType, PEEvidenceBean peEvidenceBean, Date importedTime) {
        PEEvidence peEvidence = new PEEvidence();
        peEvidence.setColorLevel(peEvidenceBean.getColorLevel());
        peEvidence.setEvidenceValue(peEvidenceBean.getEvidenceValue());
        peEvidence.setHyperLink(peEvidenceBean.getHyperlink());
        //set the created time
        peEvidence.setCreatedTime(importedTime);
        //set the last update time
        peEvidence.setLastUpdatedTime(importedTime);

        peEvidence.setIdentifiedAccession(accession);
        peEvidence.setTpbDataType(tpbDataType);
        peEvidence.setGene(gene);
        //save the pe evidence
        this.savePEEvidence(peEvidence);
        return peEvidence;
    }

    private void persistNXAnnoations(TLGene tlGene, Accession accession, List<NXAnnEntryBean> nxAnnEntryBeans) {
        if (nxAnnEntryBeans != null) {
            for (NXAnnEntryBean nxAnnEntryBean : nxAnnEntryBeans) {
                NXAnnotation nxAnnotation = copyFromNXAnnEntryBean(nxAnnEntryBean);

                System.out.println("=============> annotation category: " + nxAnnotation.getCategory());
                List<NXAnnEvidenceBean> nxAnnEvidenceBeans = nxAnnEntryBean.getNxAnnEvidenceBeans();

                List<NXIsoFormAnnBean> nxisoFormAnnBeans = nxAnnEntryBean.getNxisoFormAnnBeans();

                List<NXAnnEvidence> nxAnnEvidences = copyFromNXAnnEvidenceBeans(nxAnnEvidenceBeans, nxAnnotation);
                List<NXIsoFormAnn> nxIsoFormAnns = copyFromNXIsoFormAnnBeans(nxisoFormAnnBeans, nxAnnotation);

                if (nxAnnEvidences.size() > 0) {
                    nxAnnotation.setNxAnnEvidences(nxAnnEvidences);
                }
                if (nxIsoFormAnns.size() > 0) {
                    System.out.println("============= nxIsoFormAnns SIZE: : " + nxIsoFormAnns.size());
                    nxAnnotation.setNxIsoFormAnns(nxIsoFormAnns);
                }
                nxAnnotation.setIdentifiedAccession(accession);
                System.out.println("============= start to save annotation for the nextprot accession: " + accession.getAccession());
                this.saveNXAnnotation(nxAnnotation);
            }
        }
    }

    private NXAnnotation copyFromNXAnnEntryBean(NXAnnEntryBean nxAnnEntryBean) {
        NXAnnotation nxAnnotation = new NXAnnotation();

        if (nxAnnEntryBean != null) {
            String category = nxAnnEntryBean.getCategory();
            String qualityQualifier = nxAnnEntryBean.getQualityQualifier();
            String uniqueName = nxAnnEntryBean.getUniqueName();
            String desc = nxAnnEntryBean.getDescription();
            String cvName = nxAnnEntryBean.getCvName();
            String cvTermAc = nxAnnEntryBean.getCvTermAccession();

            if (StringUtils.isNotBlank(category)) {
                nxAnnotation.setCategory(category);
            }
            if (StringUtils.isNotBlank(qualityQualifier)) {
                nxAnnotation.setQualityQualifier(qualityQualifier);
            }
            if (StringUtils.isNotBlank(uniqueName)) {
                nxAnnotation.setUniqueName(uniqueName);
            }
            if (StringUtils.isNotBlank(cvName)) {
                nxAnnotation.setCvName(cvName);
            }
            if (StringUtils.isNotBlank(cvTermAc)) {
                nxAnnotation.setCvTermAccession(cvTermAc);
            }
            if (StringUtils.isNotBlank(desc)) {
                nxAnnotation.setDescription(desc);
            }
        }
        return nxAnnotation;
    }

    private List<NXAnnEvidence> copyFromNXAnnEvidenceBeans(List<NXAnnEvidenceBean> nxAnnEvidenceBeans, NXAnnotation nxAnnotation) {
        List<NXAnnEvidence> annEvidenceList = new ArrayList<NXAnnEvidence>();
        if (nxAnnEvidenceBeans != null) {
            for (NXAnnEvidenceBean nxAnnEvidenceBean : nxAnnEvidenceBeans) {

                NXAnnEvidence nxAnnEvidence = new NXAnnEvidence();
                boolean isNegative = nxAnnEvidenceBean.isNegative();
                String qualifierType = nxAnnEvidenceBean.getQualifierType();
                String resourceAssocType = nxAnnEvidenceBean.getResourceAssocType();
                int resourceRef = nxAnnEvidenceBean.getResourceRef();

                nxAnnEvidence.setNegative(isNegative);
                if (StringUtils.isNotBlank(qualifierType)) {
                    nxAnnEvidence.setQualifierType(qualifierType);
                }
                if (StringUtils.isNotBlank(resourceAssocType)) {
                    nxAnnEvidence.setResourceAssocType(resourceAssocType);
                }
                nxAnnEvidence.setResourceRef(resourceRef);
                nxAnnEvidence.setNxAnnotation(nxAnnotation);
                annEvidenceList.add(nxAnnEvidence);
            }
        }
        return annEvidenceList;
    }

    private List<NXIsoFormAnn> copyFromNXIsoFormAnnBeans(List<NXIsoFormAnnBean> nxisoFormAnnBeans, NXAnnotation nxAnnotation) {
        List<NXIsoFormAnn> nxIsoFormAnnList = new ArrayList<NXIsoFormAnn>();

        if (nxisoFormAnnBeans != null) {
            for (NXIsoFormAnnBean nxisoFormAnnBean : nxisoFormAnnBeans) {

                NXIsoFormAnn nxIsoFormAnn = new NXIsoFormAnn();

                String isoformRef = nxisoFormAnnBean.getIsoFormRef();
                int first = nxisoFormAnnBean.getFirstPosition();
                String firstStatus = nxisoFormAnnBean.getFirstStatus();
                int last = nxisoFormAnnBean.getLastPosition();
                String lastStatus = nxisoFormAnnBean.getLastStatus();

                if (StringUtils.isNotBlank(isoformRef)) {
                    nxIsoFormAnn.setIsoFormRef(isoformRef);
                }
                nxIsoFormAnn.setFirstPosition(first);

                if (StringUtils.isNotBlank(firstStatus)) {
                    nxIsoFormAnn.setFirstStatus(firstStatus);
                }

                nxIsoFormAnn.setLastPosition(last);

                if (StringUtils.isNotBlank(lastStatus)) {
                    nxIsoFormAnn.setLastStatus(lastStatus);
                }
                nxIsoFormAnn.setNxAnnotation(nxAnnotation);
                nxIsoFormAnnList.add(nxIsoFormAnn);
            }
        }
        return nxIsoFormAnnList;
    }

    public void saveGPMDataEntry(List<ChromType> chromTypes, List<GPMEntryBean> gpmEntryBeans, Date importedTime, String fileName, String timeToken) {
        logger.info("TPB is starting to save the gpm data.");
        //create new gmp  DSVersion  for chrom 7
        if (chromTypes.isEmpty()) {
            chromTypes = ChromType.allChroms();
        }
        for (ChromType chromType : chromTypes) {
            DSVersion gpmChromDSVersion = createDSVersionByDbsChrom(chromType, DbAcType.GPM, importedTime, fileName, timeToken);
        }
        //create new gmp  DSVersion  for chrom
        boolean evidenceSaved = false;

        for (GPMEntryBean gpmEntryBean : gpmEntryBeans) {
            GeneBean geneBean = gpmEntryBean.getGeneBean();
            String ensgAc = geneBean.getEnsgAccession();

            TPBGene foundTPBGene = this.getTPBGeneByEnsgAc(ensgAc);
            if (foundTPBGene != null) {
                String chrom = foundTPBGene.getChromosome();
                ChromType foundChromType = ChromType.fromType(chrom);
                //only focus on the required chromosome type
                if (chromTypes.contains(foundChromType)) {
                    //update the gene bean info from the found tpg bean
                    updateFromTPBGene(foundTPBGene, geneBean);

                    DBSourceBean gpmDbSource = gpmEntryBean.getPrimaryDbSourceBean();
                    AccessionBean identAcBean = gpmEntryBean.getIdentifiedAccessionBean();

                    List<DbSourceAcEntryBean> dbSourceAcEntryBeans = gpmEntryBean.getDbSourceAcEntryBeans();
                    //save the sessions and db sources
                    List<Accession> savedAcs = new ArrayList<Accession>();
                    for (DbSourceAcEntryBean gpmDbSourceAcEntryBean : dbSourceAcEntryBeans) {
                        //DBSource
                        DBSourceBean dbSourceBean = gpmDbSourceAcEntryBean.getDbSourceBean();
                        //persist DBSource
                        DBSource dbSource = persistDBSource(dbSourceBean);

                        //Accession
                        AccessionBean accessionBean = gpmDbSourceAcEntryBean.getAccessionBean();
                        String acType = accessionBean.getAcType();

                        //persist AccessionType
                        AccessionType accessionType = persistAccessionType(acType);
                        //persist Accession
                        Accession accession = persistAccession(dbSource, accessionBean, accessionType);
                        //if this accession is not added
                        if (!savedAcs.contains(accession)) {
                            savedAcs.add(accession);
                        }
                    }
                    //persist the GPM Db source bean
                    DBSource dbSource = persistDBSource(gpmDbSource);
                    //persist Gene
                    //System.out.println("=====> gene ensg ac: " + geneBean.getEnsgAccession() + ", display name: " + geneBean.getDisplayName());
                    Gene gpmGene = persistGene(geneBean, dbSource, savedAcs, importedTime);

                    //get the identified accession
                    String identAc = identAcBean.getAccession();
                    String actType = identAcBean.getAcType();
                    // System.out.println("================= identified accession : " + identAc + " ac type : " + actType);
                    Accession identifiedAccession = this.getAccessionByAccessionAcType(identAc, actType);

                    //PE PE MS PRob Evidence
                    PEEvidenceBean peMsProbEvidenceBean = gpmEntryBean.getPeMsProbEvidenceBean();
                    if (peMsProbEvidenceBean != null) {
                        //data type
                        TPBDataTypeBean peMsProbTypeBean = peMsProbEvidenceBean.getHPBDataTypeBean();
                        String peMsProbTypeName = peMsProbTypeBean.getDataType();
                        TPBDataType peMsProbType = this.getTPBDataTypeByTypeName(peMsProbTypeName);
                        Evidence peMsProbEvidence = this.persistPEEvidence(gpmGene, identifiedAccession, peMsProbType, peMsProbEvidenceBean, importedTime);
                        evidenceSaved = true;
                    }

                    //pe ms samoles evidence
                    PEEvidenceBean peMsSamplesEvidenceBean = gpmEntryBean.getPeMsSamplesEvidenceBean();
                    if (peMsSamplesEvidenceBean != null) {
                        //data type
                        TPBDataTypeBean peMsSamTpbTypeBean = peMsSamplesEvidenceBean.getHPBDataTypeBean();
                        String peMsSamTypeName = peMsSamTpbTypeBean.getDataType();
                        TPBDataType peMsSamType = this.getTPBDataTypeByTypeName(peMsSamTypeName);
                        Evidence peMsProbEvidence = this.persistPEEvidence(gpmGene, identifiedAccession, peMsSamType, peMsSamplesEvidenceBean, importedTime);
                        evidenceSaved = true;
                    }
                }
            }
        }

        //if no evidence saved, just make sure not save the ds version, genes, accession and db sources, and auto rollback
        if (!evidenceSaved) {
            throw new DMException("no evidence is saved");
        }
    }

    private void updateFromTPBGene(TPBGene tpbGene, GeneBean geneBean) {
        if (tpbGene != null) {
            String displayName = tpbGene.getGeneName();
            if (StringUtils.isNotBlank(displayName)) {
                geneBean.setDisplayName(displayName);
            }

            String chromosome = tpbGene.getChromosome();
            if (StringUtils.isNotBlank(chromosome)) {
                geneBean.setChromosome(chromosome);
            }

            String desc = tpbGene.getDescription();
            if (StringUtils.isNotBlank(desc)) {
                geneBean.setDescription(desc);
            }

            String ensgAc = tpbGene.getEnsgAccession();
            if (StringUtils.isNotBlank(ensgAc)) {
                geneBean.setEnsgAccession(ensgAc);
            }

            long start = tpbGene.getStartPosition();
            if (start != 0) {
                geneBean.setStartPosition(start);
            }

            long stop = tpbGene.getEndPosition();
            if (stop != 0) {
                geneBean.setEndPosition(stop);
            }
            String band = tpbGene.getBand();
            if (StringUtils.isNotBlank(band)) {
                geneBean.setBand(band);
            }

            String strand = tpbGene.getStrand();
            if (StringUtils.isNotBlank(strand)) {
                geneBean.setStrand(strand);
            }
        }
    }


    //Persist the gene (new or update)
    private Gene persistGene(GeneBean geneBean, DBSource dbSource, List<Accession> accessions, Date importedTime) {
        Gene foundGene = null;
        String ensgAg = geneBean.getEnsgAccession();
        if (StringUtils.isNotBlank(ensgAg)) {
            foundGene = getGeneByEnsgAndDbVersion(ensgAg, dbSource.getDbName(), importedTime);
        }

        if (foundGene == null) {
            foundGene = new Gene();
            foundGene.setDisplayName(geneBean.getDisplayName());
            foundGene.setChromosome(geneBean.getChromosome());
            foundGene.setEnsgAccession(geneBean.getEnsgAccession());
            foundGene.setStartPosition(geneBean.getStartPosition());
            foundGene.setEndPosition(geneBean.getEndPosition());
            foundGene.setBand(geneBean.getBand());
            foundGene.setStrand(geneBean.getStrand());
            foundGene.setDescription(geneBean.getDescription());
            //set a list of associated Accession
            foundGene.setAccessions(accessions);
            //set the identified DBSource
            foundGene.setDbSource(dbSource);
            //set the created time
            foundGene.setCreatedTime(importedTime);
            //set the last updated time
            foundGene.setLastUpdatedTime(importedTime);
            //just save this gene
            this.saveGene(foundGene);
        } else {
            List<Accession> foundGeneAcs = this.getAllAssociatedAccessionsByGeneId(foundGene.getId());
            if (foundGeneAcs != null) {
                for (Accession ac : foundGeneAcs) {
                    if (!accessions.contains(ac)) {
                        accessions.add(ac);
                    }
                }
            }
            //set a list of associated Accession
            foundGene.setAccessions(accessions);
            //set the identified DBSource
            foundGene.setDbSource(dbSource);
            //set the created time
            foundGene.setCreatedTime(importedTime);
            //set the last updated time
            foundGene.setLastUpdatedTime(importedTime);
            this.mergeGene(foundGene);
        }
        return foundGene;
    }

    public void saveHPADataEntry(List<ChromType> chromTypes, List<HPAEntryBean> hpaEntryBeans, Date importedTime, String fileName, String versionToken) {
        //create DSVersions for chromosome types
        logger.info("TPB is starting to save the hpa data.");
        if (chromTypes.isEmpty()) {
            chromTypes = ChromType.allChroms();
        }
        for (ChromType chromType : chromTypes) {
            DSVersion hpaChromDSVersion = createDSVersionByDbsChrom(chromType, DbAcType.HPA, importedTime, fileName, versionToken);
        }
        //create new hpa  DSVersion  for chrom
        boolean evidenceSaved = false;

        for (HPAEntryBean hpaEntryBean : hpaEntryBeans) {
            GeneBean geneBean = hpaEntryBean.getGeneBean();
            String ensgAc = geneBean.getEnsgAccession();
            TPBGene foundTPBGene = this.getTPBGeneByEnsgAc(ensgAc);
            if (foundTPBGene != null) {
                String chrom = foundTPBGene.getChromosome();
                ChromType foundChromType = ChromType.fromType(chrom);
                //only focus on the required chromosome type
                if (chromTypes.contains(foundChromType)) {
                    //update the gene bean info from the found tpg bean
                    updateFromTPBGene(foundTPBGene, geneBean);

                    DBSourceBean hpaDbSourceBean = hpaEntryBean.getPrimaryDbSourceBean();
                    AccessionBean identAcBean = hpaEntryBean.getIdentifiedAccessionBean();

                    List<DbSourceAcEntryBean> dbSourceAcEntryBeans = hpaEntryBean.getDbSourceAcEntryBeans();

                    //save the sessions and db sources
                    List<Accession> savedAcs = new ArrayList<Accession>();
                    for (DbSourceAcEntryBean hpaDbSourceAcEntryBean : dbSourceAcEntryBeans) {
                        //DBSource
                        DBSourceBean dbSourceBean = hpaDbSourceAcEntryBean.getDbSourceBean();

                        //persist DBSource
                        DBSource dbSource = persistDBSource(dbSourceBean);

                        //Accession
                        AccessionBean accessionBean = hpaDbSourceAcEntryBean.getAccessionBean();
                        String acType = accessionBean.getAcType();

                        //persist AccessionType
                        AccessionType accessionType = persistAccessionType(acType);

                        //persist Accession
                        Accession accession = persistAccession(dbSource, accessionBean, accessionType);
                        //if this accession is not added
                        if (!savedAcs.contains(accession)) {
                            savedAcs.add(accession);
                        }
                    }

                    //persist the hpa Db source bean
                    DBSource hpaDbSource = persistDBSource(hpaDbSourceBean);

                    //persist Gene
                    Gene hpaGene = persistGene(geneBean, hpaDbSource, savedAcs, importedTime);

                    //get the identified accession
                    String identAc = identAcBean.getAccession();
                    String actType = identAcBean.getAcType();

                    Accession identifiedAccession = this.getAccessionByAccessionAcType(identAc, actType);

                    //PE_ANTI_IHC_NORM
                    List<PEEvidenceBean> peAntiIHCNormEvidenceBeans = hpaEntryBean.getPeAntiIHCNormEvidencesBeans();
                    for (PEEvidenceBean peAnitIhcNormEvidenceBean : peAntiIHCNormEvidenceBeans) {
                        //data type
                        TPBDataTypeBean antiDataTypeBean = peAnitIhcNormEvidenceBean.getHPBDataTypeBean();
                        String antiDataTypeName = antiDataTypeBean.getDataType();
                        TPBDataType antiType = this.getTPBDataTypeByTypeName(antiDataTypeName);
                        this.persistPEEvidence(hpaGene, identifiedAccession, antiType, peAnitIhcNormEvidenceBean, importedTime);
                        evidenceSaved = true;
                    }

                }
            } else {
                //no way to save this gene information
            }
        }

        //if no evidence saved, just make sure not save the ds version, genes, accession and db sources, and auto rollback
        if (!evidenceSaved) {
            throw new DMException("no evidence is saved");
        }
    }


    //TLGene

    /**
     * {@inheritDoc}
     */
    public TLGene getTLGeneById(long id) {
        return this.tlGeneService.getTLGeneById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void saveTLGene(TLGene tlGene) {
        this.tlGeneService.saveTLGene(tlGene);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTLGene(TLGene tlGene) {
        this.tlGeneService.mergeTLGene(tlGene);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTLGene(TLGene tlGene) {
        this.tlGeneService.updateTLGene(tlGene);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTLGene(TLGene tlGene) {
        this.tlGeneService.deleteTLGene(tlGene);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTLGeneById(long id) {
        this.tlGeneService.deleteTLGeneById(id);
    }

    //TrafficLight

    /**
     * {@inheritDoc}
     */
    public void saveTrafficLight(TrafficLight trafficLight) {
        this.tlService.saveTrafficLight(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTrafficLight(TrafficLight trafficLight) {
        this.tlService.mergeTrafficLight(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTrafficLight(TrafficLight trafficLight) {
        this.tlService.updateTrafficLight(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTrafficLight(TrafficLight trafficLight) {
        this.tlService.deleteTrafficLight(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public TrafficLight getTrafficLightById(long id) {
        return this.tlService.getTrafficLightById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTLById(long id) {
        this.tlService.deleteTLById(id);
    }


    public boolean createVersionTLByChromType(ChromType chromType, TLVersionTrack tlVersionTrack, Date createdTime) {
        boolean created = false;
        if (tlVersionTrack != null) {
            //check this combination version is available in TPBVersion table or not?
            //if not, then create a combination traffic lights and create a tpb version
            //if the traffic lights are already created before, we just ignore this combination
            boolean tpbVersionExisted = this.checkTPBVersionAvailable(chromType, tlVersionTrack);

            if (!tpbVersionExisted) {

                TPBVersion tpbVersion = createTPBVersion(chromType, tlVersionTrack, createdTime);
                DSVersion nxDSVersion = tlVersionTrack.getNxDsVersion();
                if (nxDSVersion != null) {
                    createTrafficLights(chromType, tpbVersion, nxDSVersion);
                }
                DSVersion gpmDSVersion = tlVersionTrack.getGpmDsVersion();
                if (gpmDSVersion != null) {
                    createTrafficLights(chromType, tpbVersion, gpmDSVersion);
                }
                DSVersion hpaDSVersion = tlVersionTrack.getHpaDsVersion();
                if (hpaDSVersion != null) {
                    createTrafficLights(chromType, tpbVersion, hpaDSVersion);
                }
                DSVersion paDSVersion = tlVersionTrack.getPaDsVersion();
                if (paDSVersion != null) {
                    createTrafficLights(chromType, tpbVersion, paDSVersion);
                }
                created = true;
            }
        }
        return created;
    }

    private void createTrafficLights(ChromType chromType, TPBVersion tpbVersion, DSVersion dsVersion) {
        TLColor tlGreenColor = this.getTLColorByColorLevel(ColorType.GREEN.color());
        TLColor tlYellowColor = this.getTLColorByColorLevel(ColorType.YELLOW.color());
        TLColor tlRedColor = this.getTLColorByColorLevel(ColorType.RED.color());
        TLColor tlBlackColor = this.getTLColorByColorLevel(ColorType.BLACK.color());

        if (dsVersion != null) {
            String dbSource = dsVersion.getDbSource();
            DbAcType dataSourceType = DbAcType.fromType(dbSource);
            DbAcType dbAcType = DbAcType.fromType(dbSource);
            Date dsVersionTime = dsVersion.getCreatedTime();
            //get all genes based on a datasource type and datasource version time
            List<Gene> genes = this.getGeneByDBSChromVersion(dataSourceType, chromType, dsVersionTime);

            for (Gene gene : genes) {
                String ensgAc = gene.getEnsgAccession();

                TrafficLight trafficLight = null;
                if (StringUtils.isNotBlank(ensgAc)) {
                    trafficLight = this.getTLByChromEnsemblAcVersionToken(chromType, ensgAc, tpbVersion.getId(), tpbVersion.getTrackToken());
                }
                //here we just recreate a new traffic light based on a traffic light which we got from db, it might be null or existed
                //we will check it later.
                trafficLight = generateTrafficLightEntity(trafficLight, gene.getId(), tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);

                //set tpb version
                trafficLight.setTpbVersion(tpbVersion);
                //if id is zero which means it's a new traffic
                if (trafficLight.getId() == 0) {
                    //new TrafficLight
                    TLGene tlGene = null;
                    //if the ensembl accession is not null, we create a TLGene based on a source gene and update it from our master TPBGene.
                    if (StringUtils.isNotBlank(ensgAc)) {
                        TPBGene tpbGene = this.getTPBGeneByEnsgAc(ensgAc);
                        if (tpbGene != null) {
                            tlGene = copyFromTPBGene(tpbGene);
                        } else {
                            tlGene = copyFromGene(gene);
                        }
                    } else {  //if ensembl accession is null, we just create a TLGene based on a source gene
                        tlGene = copyFromGene(gene);
                    }
                    //temp for store all the source genes for this TLGene
                    List<Gene> allGenes = new ArrayList<Gene>();

                    allGenes.add(gene);
                    //set all source genes
                    tlGene.setGenes(allGenes);
                    //save the TLGene
                    this.saveTLGene(tlGene);
                    //set the TLGene for traffic light
                    trafficLight.setTlGene(tlGene);
                    //save as a new TrafficLight
                    this.saveTrafficLight(trafficLight);

                } else {// a TrafficLight already existed, we just need updating it
                    //update TrafficLight
                    TLGene tlGene = trafficLight.getTlGene();
                    //ensembl accession is not null, then we try to update the TLGene info based on our master TPBGene
                    if (StringUtils.isNotBlank(ensgAc)) {
                        TPBGene tpbGene = this.getTPBGeneByEnsgAc(ensgAc);
                        if (tpbGene != null) {
                            long tlgid = tlGene.getId();
                            tlGene = copyFromTPBGene(tpbGene);
                            tlGene.setId(tlgid);
                        } else {
                            //this just try to replace the unknown gene name if available
                            tlGene = replaceUnknownGeneName(tlGene, gene);
                        }
                    } else {
                        //this just try to replace the unknown gene name if available
                        tlGene = replaceUnknownGeneName(tlGene, gene);
                    }

                    List<Gene> allGenes = this.getGenesByTLGeneId(tlGene.getId());

                    if (allGenes != null) {
                        if (!allGenes.contains(gene)) {
                            allGenes.add(gene);
                        }
                    } else {
                        allGenes = new ArrayList<Gene>();
                        allGenes.add(gene);
                    }
                    //set the all genes for this traffic light genes
                    tlGene.setGenes(allGenes);

                    //merge TLGene
                    this.mergeTLGene(tlGene);
                    //merge TrafficLight
                    this.mergeTrafficLight(trafficLight);
                }
            }
        }
    }

    private TLGene replaceUnknownGeneName(TLGene tlGene, Gene gene) {
        if (tlGene != null && gene != null) {
            String tlGeneDisplayName = tlGene.getDisplayName();
            String geneDisplayName = gene.getDisplayName();
            if (StringUtils.equals(tlGeneDisplayName, "Unknown")) {
                if (!StringUtils.equals(geneDisplayName, "Unknown")) {
                    tlGeneDisplayName = geneDisplayName;
                    tlGene.setDisplayName(tlGeneDisplayName);
                }
            }
        }
        return tlGene;
    }

    private TLGene copyFromGene(Gene gene) {
        TLGene tlGene = new TLGene();
        String displayName = gene.getDisplayName();
        if (StringUtils.isNotBlank(displayName)) {
            tlGene.setDisplayName(displayName);
        }

        String chromosome = gene.getChromosome();
        if (StringUtils.isNotBlank(chromosome)) {
            tlGene.setChromosome(chromosome);
        }

        String desc = gene.getDescription();
        if (StringUtils.isNotBlank(desc)) {
            tlGene.setDescription(desc);
        }

        String ensgAc = gene.getEnsgAccession();
        if (StringUtils.isNotBlank(ensgAc)) {
            tlGene.setEnsgAccession(ensgAc);
        }

        long start = gene.getStartPosition();
        if (start != 0) {
            tlGene.setStartPosition(start);
        }

        long stop = gene.getEndPosition();
        if (stop != 0) {
            tlGene.setEndPosition(stop);
        }

        String band = gene.getBand();
        if (StringUtils.isNotBlank(band)) {
            tlGene.setBand(band);
        }

        String strand = gene.getStrand();
        if (StringUtils.isNotBlank(strand)) {
            tlGene.setStrand(strand);
        }
        return tlGene;
    }

    private TLGene copyFromTPBGene(TPBGene tpbGene) {
        TLGene tlGene = new TLGene();

        String displayName = tpbGene.getGeneName();
        if (StringUtils.isNotBlank(displayName)) {
            tlGene.setDisplayName(displayName);
        }

        String chromosome = tpbGene.getChromosome();
        if (StringUtils.isNotBlank(chromosome)) {
            tlGene.setChromosome(chromosome);
        }

        String desc = tpbGene.getDescription();
        if (StringUtils.isNotBlank(desc)) {
            tlGene.setDescription(desc);
        }

        String ensgAc = tpbGene.getEnsgAccession();
        if (StringUtils.isNotBlank(ensgAc)) {
            tlGene.setEnsgAccession(ensgAc);
        }

        long start = tpbGene.getStartPosition();
        if (start != 0) {
            tlGene.setStartPosition(start);
        }

        long stop = tpbGene.getEndPosition();
        if (stop != 0) {
            tlGene.setEndPosition(stop);
        }

        String band = tpbGene.getBand();
        if (StringUtils.isNotBlank(band)) {
            tlGene.setBand(band);
        }

        String strand = tpbGene.getStrand();
        if (StringUtils.isNotBlank(strand)) {
            tlGene.setStrand(strand);
        }
        return tlGene;
    }

    private TrafficLight generateTrafficLightEntity(TrafficLight trafficLight, long soureGeneId, TLColor tlGreenColor, TLColor tlYellowColor, TLColor tlRedColor, TLColor tlBlackColor) {
        if (trafficLight == null) {
            trafficLight = new TrafficLight();
        }
        //get all data types evidences first
        PEEvidence peSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE);
        PEEvidence peMsSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_MS);
        PEEvidence peMsAnnSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_MS_ANN);
        PEEvidence peMsProbSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_MS_PROB);
        PEEvidence peMsSamSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_MS_SAM);
        PEEvidence peAntiSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_ANTI);
        PEEvidence peAntiAnnSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_ANTI_ANN);
        PEEvidence peAntiIHCSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_ANTI_IHC);
        PEEvidence peAntiIHCNormSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_ANTI_IHC_NORM);
        PEEvidence peOthSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_OTH);
        PEEvidence peOthCurSum = this.getPESummaryByGeneAndType(soureGeneId, DataType.PE_OTH_CUR);

        //PE
        TLColor peColor = generatePETLColor(peSum, DataType.PE, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setPeTlColor(peColor);

        //PE MS
        TLColor peMsColor = generatePETLColor(peMsSum, DataType.PE_MS, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setPeMSTlColor(peMsColor);

        //PE MS ANN
        TLColor peMsAnnColor = generatePETLColor(peMsAnnSum, DataType.PE_MS_ANN, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEMSANNColor(peMsAnnColor);

        //PE MS PROB
        TLColor peMsProbColor = generatePETLColor(peMsProbSum, DataType.PE_MS_PROB, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEMSPROBColor(peMsProbColor);

        //PE MS Samples
        TLColor peMsSamColor = generatePETLColor(peMsSamSum, DataType.PE_MS_SAM, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEMSSAMColor(peMsSamColor);

        //PE ANTI
        TLColor peAntiColor = generatePETLColor(peAntiSum, DataType.PE_ANTI, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEANTIColor(peAntiColor);

        //PE ANTI ANN
        TLColor peAntiAnnColor = generatePETLColor(peAntiAnnSum, DataType.PE_ANTI_ANN, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEANTIANNColor(peAntiAnnColor);

        //PE ANTI IHC
        TLColor peAntiIhcColor = generatePETLColor(peAntiIHCSum, DataType.PE_ANTI_IHC, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEANTIIHCColor(peAntiIhcColor);

        //PE ANTI ANN
        TLColor peAntiIhcNormColor = generatePETLColor(peAntiIHCNormSum, DataType.PE_ANTI_IHC_NORM, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEANTIIHCNORMColor(peAntiIhcNormColor);

        //PE OTH
        TLColor peOthColor = generatePETLColor(peOthSum, DataType.PE_OTH, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEOTHColor(peOthColor);

        //PE OTH CUR
        TLColor peOthCurColor = generatePETLColor(peOthCurSum, DataType.PE_OTH_CUR, trafficLight, tlGreenColor, tlYellowColor, tlRedColor, tlBlackColor);
        trafficLight.setTlPEOTHCURColor(peOthCurColor);
        return trafficLight;
    }

    private TLColor generatePETLColor(PEEvidence evidence, DataType dataType, TrafficLight trafficLight, TLColor tlGreenColor, TLColor tlYellowColor, TLColor tlRedColor, TLColor tlBlackColor) {
        TLColor peTLColor = tlBlackColor;
        //PE OTH CUR
        if (evidence != null) {
            if (evidence.getColorLevel() == ColorType.GREEN.color()) {
                peTLColor = tlGreenColor;
            } else if (evidence.getColorLevel() == ColorType.YELLOW.color()) {
                peTLColor = tlYellowColor;
            } else if (evidence.getColorLevel() == ColorType.RED.color()) {
                peTLColor = tlRedColor;
            } else {
                peTLColor = tlBlackColor;
            }
        }
        //to see new color is higher than the existed one or not
        TLColor existedPETLColor = trafficLight.getTLColorByDataType(dataType);
        if (existedPETLColor == null) {
            return peTLColor;
        } else {
            if (peTLColor.getColorLevel() > existedPETLColor.getColorLevel()) {
                return peTLColor;
            } else {
                return existedPETLColor;
            }
        }
    }


    private TPBVersion createTPBVersion(ChromType chromType, TLVersionTrack tlVersionTrack, Date createdTime) {
        TPBVersion tpbVersion = new TPBVersion();
        //set the chromosome type
        tpbVersion.setChromosome(chromType.chm());
        //TPBVersion time:
        tpbVersion.setCreatedTime(createdTime);
        int trackToken = tlVersionTrack.getTrackToken();
        tpbVersion.setTrackToken(trackToken);

        TPBVersion currentTPBVersion = this.getCurrentTPBVersionByChromTypeTrackToken(chromType, trackToken);
        //create a TPBVersion number
        int versionNo = 1;
        if (currentTPBVersion != null) {
            int currentVersionNo = currentTPBVersion.getVersionNo();
            versionNo = currentVersionNo + 1;
        }
        tpbVersion.setVersionNo(versionNo);

        DSVersion nxDSVersion = tlVersionTrack.getNxDsVersion();
        if (nxDSVersion != null) {
            tpbVersion.setNxVersion(nxDSVersion);
        }

        DSVersion gpmDSVersion = tlVersionTrack.getGpmDsVersion();
        if (gpmDSVersion != null) {
            tpbVersion.setGpmVersion(gpmDSVersion);
        }

        DSVersion hpaDSVersion = tlVersionTrack.getHpaDsVersion();
        if (hpaDSVersion != null) {
            tpbVersion.setHpaVersion(hpaDSVersion);
        }

        DSVersion paDSVersion = tlVersionTrack.getPaDsVersion();
        if (paDSVersion != null) {
            tpbVersion.setPeptideVersion(paDSVersion);
        }
        this.saveTPBVersion(tpbVersion);
        return tpbVersion;
    }

    /**
     * {@inheritDoc}
     */
    public TrafficLight getTLByChromEnsemblAcVersionToken(ChromType chromType, String ensgAc, long versionId, int trackToken) {
        return this.tlService.getTLByChromEnsemblAcVersionToken(chromType, ensgAc, versionId, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public Pagination<TrafficLight> getVersionTrafficLight(ChromType chromType, int trackToken, long versionId, int startPage, int recordsPerPage, OrderBy[] orderConds) {
        return this.tlService.getVersionTrafficLight(chromType, trackToken, versionId, startPage, recordsPerPage, orderConds);
    }

    /**
     * {@inheritDoc}
     */
    public Pagination<TrafficLight> getVersionTrafficLight(TLSearchBean tlSearchBean, int startPage, int recordsPerPage, OrderBy[] orderConds) {
        return this.tlService.getVersionTrafficLight(tlSearchBean, startPage, recordsPerPage, orderConds);
    }

    /**
     * {@inheritDoc}
     */
    public TLSumReporter getTLSumReporter(TLSearchBean tlSearchBean) {
        return this.tlService.getTLSumReporter(tlSearchBean);
    }

    /**
     * {@inheritDoc}
     */
    public TPBGene getTPBGeneById(long id) {
        return this.tpbGeneService.getTPBGeneById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void saveTPBGene(TPBGene tpbGene) {
        this.tpbGeneService.saveTPBGene(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTPBGene(TPBGene tpbGene) {
        this.tpbGeneService.mergeTPBGene(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTPBGene(TPBGene tpbGene) {
        this.tpbGeneService.updateTPBGene(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBGene(TPBGene tpbGene) {
        this.tpbGeneService.deleteTPBGene(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public TPBGene getTPBGeneByEnsgAc(String ensgAccession) {
        return this.tpbGeneService.getTPBGeneByEnsgAc(ensgAccession);
    }

    /**
     * {@inheritDoc}
     */
    public void importTPBGenes(List<TPBGene> tpbGenes) {
        for (TPBGene tpbGene : tpbGenes) {
            String ensgAc = tpbGene.getEnsgAccession();
            if (StringUtils.isNotBlank(ensgAc)) {
                TPBGene existedTpbGene = this.getTPBGeneByEnsgAc(ensgAc);
                if (existedTpbGene != null) {
                    tpbGene.setId(existedTpbGene.getId());
                    this.mergeTPBGene(tpbGene);
                } else {
                    this.saveTPBGene(tpbGene);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void saveRifcsDataset(RIFCSDataset rifcsDataset) {
        this.rifcsDatasetService.saveRifcsDataset(rifcsDataset);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeRifcsDataset(RIFCSDataset rifcsDataset) {
        this.rifcsDatasetService.mergeRifcsDataset(rifcsDataset);
    }

    /**
     * {@inheritDoc}
     */
    public void updateRifcsDataset(RIFCSDataset rifcsDataset) {
        this.rifcsDatasetService.updateRifcsDataset(rifcsDataset);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteRifcsDataset(RIFCSDataset rifcsDataset) {
        this.rifcsDatasetService.deleteRifcsDataset(rifcsDataset);
    }

    /**
     * {@inheritDoc}
     */
    public RIFCSDataset getRifcsDatasetById(long id) {
        return this.rifcsDatasetService.getRifcsDatasetById(id);
    }

    /**
     * {@inheritDoc}
     */
    public RIFCSDataset getRifcsDsByTpbVersionAndTrackToken(long tpbVersionId, int trackToken) {
        return this.rifcsDatasetService.getRifcsDsByTpbVersionAndTrackToken(tpbVersionId, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteRifcsDsById(long id) {
        this.rifcsDatasetService.deleteRifcsDsById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteRifcsDsByTpbVersionAndTrackToken(long tpbVersionId, int trackToken) {
        this.rifcsDatasetService.deleteRifcsDsByTpbVersionAndTrackToken(tpbVersionId, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public void createRifcs(RifcsInfoBean rifcsInfoBean) {
        boolean republishRequired = rifcsInfoBean.isRepublishRequired();
        //get all max combinated datasource tpb versions
        // List<TPBVersion> tpbVersions = this.getAllTPBVersions();
        List<MaxDsTPBVersion> maxDsTPBVersions = this.getAllChromosomeTPBVersionByMaxCombinatedDs();
        String rifcsStoreLocation = rifcsInfoBean.getRifcsStoreLocation();
        String rifcsTemplate = rifcsInfoBean.getRifcsTemplate();


        //create a traffic light base url
        String baseUrl = rifcsInfoBean.getServerName() + File.separator + rifcsInfoBean.getAppRootRelPath();

        //loop the traffic versions
        for (MaxDsTPBVersion tpbVersion : maxDsTPBVersions) {
            long tpbvId = tpbVersion.getId();
            int tpbversionNo = tpbVersion.getVersionNo();
            int trackToken = tpbVersion.getTrackToken();
            String chromosome = tpbVersion.getChromosome();
            // System.out.println("================== tpbversion id: " + tpbvId + ", version no: " + tpbversionNo + ", token : " + trackToken + ", chromosome : " + chromosome);

            //try find a RIFCSDataset if any based on a tpb version and a track token
            RIFCSDataset rifcsDataset = this.getRifcsDsByTpbVersionAndTrackToken(tpbvId, trackToken);
            //A RIFCSDataset not found
            if (rifcsDataset == null) {
                //create the RIFCS unique key
                String rifcsUniqueKey = DMUtil.genUUIDWithPrefix(rifcsInfoBean.getRifcsKeyPrefix());

                //create the traffic light url
                String tlUrl = generateTlUrl(baseUrl, chromosome, trackToken, tpbvId);

                //populate the RIFCS template data
                Map<String, String> templateData = populateTemplateMap(rifcsUniqueKey, chromosome, trackToken, tlUrl, rifcsInfoBean);
                createRifcsFile(rifcsStoreLocation, rifcsUniqueKey, templateData, rifcsTemplate);

                //save the RifcsDataset
                rifcsDataset = new RIFCSDataset();
                rifcsDataset.setUniqueKey(rifcsUniqueKey);
                rifcsDataset.setPublished(true);
                rifcsDataset.setTpbVersionId(tpbvId);
                rifcsDataset.setTrackToken(trackToken);
                rifcsDataset.setPublishDate(rifcsInfoBean.getPublishedDate());
                //persist the RIFCSDataset
                this.saveRifcsDataset(rifcsDataset);
            } else {
                if (republishRequired || (!rifcsDataset.isPublished())) {
                    String rifcsUniqueKey = rifcsDataset.getUniqueKey();

                    String tlUrl = generateTlUrl(baseUrl, chromosome, trackToken, tpbvId);
                    //populate the RIFCS template data
                    Map<String, String> templateData = populateTemplateMap(rifcsUniqueKey, chromosome, trackToken, tlUrl, rifcsInfoBean);
                    createRifcsFile(rifcsStoreLocation, rifcsUniqueKey, templateData, rifcsTemplate);

                    //set rifcs publishing flag to tru
                    rifcsDataset.setPublished(true);
                    //set the published date
                    rifcsDataset.setPublishDate(rifcsInfoBean.getPublishedDate());
                    //upate the RIFCSDataset
                    this.mergeRifcsDataset(rifcsDataset);
                }
            }
        }
    }


    //create a traffic light url
    private String generateTlUrl(String baseUrl, String chromosome, int trackToken, long tpbversionId) {
        StringBuffer url = new StringBuffer();
        url.append(baseUrl).append("/tl/trafficlight.jspx?");
        url.append("chm=").append(chromosome);
        url.append("&tt=").append(trackToken);
        url.append("&vid=").append(tpbversionId);
        return DMUtil.replaceURLAmpsands(url.toString());
    }

    //populate rifcs template data
    private Map<String, String> populateTemplateMap(String rifcsIdentifier, String chromosome, int trackToken, String tlUrl, RifcsInfoBean rifcsInfoBean) {
        String tpbGroupName = rifcsInfoBean.getTpbGroupName();
        String originatingSource = rifcsInfoBean.getServerName();
        String dbSources = combinatedDbSources(trackToken);

        Map<String, String> templateMap = new HashMap<String, String>();
        templateMap.put("TLDatasetGroupName", tpbGroupName);
        templateMap.put("TLDatasetIdentifierKey", rifcsIdentifier);
        templateMap.put("TLDatasetOriginatingSrc", originatingSource);
        templateMap.put("TLDatasetLocalKey", rifcsIdentifier);
        templateMap.put("NameChrom", chromosome);
        templateMap.put("NameDbSource", dbSources);
        templateMap.put("TLDatasetURL", tlUrl);

        templateMap.put("DescChrom", chromosome);
        templateMap.put("DescDbSource", dbSources);

        return templateMap;
    }

    //call RIFCSService to create a rifcs file
    private void createRifcsFile(String rifcsStoreLocation, String rifcsFileIdentifier, Map<String, String> templateMap, String rifcsTemplate) {
        this.rifcsService.createRifcsFile(rifcsStoreLocation, rifcsFileIdentifier, templateMap, rifcsTemplate);
    }

    //generate a dbsource combinated string
    private String combinatedDbSources(int combinatedToken) {
        boolean nxDbSelected = false;
        boolean gpmDbSelected = false;
        boolean hpaDbSelected = false;
        boolean paDbSelected = false;
        String trackStr = String.valueOf(combinatedToken);
        String[] tokens = DMUtil.split(trackStr);
        int tokenLength = tokens.length;
        //token number is 4 digital

        if (tokenLength == 4) {
            for (int i = (tokenLength - 1); i >= 0; i--) {
                String tk = tokens[i];

                if (StringUtils.equals(tk, "1")) {
                    if (i == 3) {
                        nxDbSelected = true;
                    }
                    if (i == 2) {
                        gpmDbSelected = true;
                    }
                    if (i == 1) {
                        hpaDbSelected = true;
                    }
                    if (i == 0) {
                        paDbSelected = true;
                    }
                }
            }
        }

        //token number is 3 digital int 111 or 110 or 101 or 100
        if (tokenLength == 3) {
            for (int i = (tokenLength - 1); i >= 0; i--) {
                String tk = tokens[i];
                if (StringUtils.equals(tk, "1")) {
                    if (i == 2) {
                        nxDbSelected = true;
                    }
                    if (i == 1) {
                        gpmDbSelected = true;
                    }
                    if (i == 0) {
                        hpaDbSelected = true;
                    }
                }
            }
        }
        //token number is 3 digital int 11 or 10
        if (tokenLength == 2) {
            for (int i = (tokenLength - 1); i >= 0; i--) {
                String tk = tokens[i];
                if (StringUtils.equals(tk, "1")) {
                    if (i == 1) {
                        nxDbSelected = true;
                    }
                    if (i == 0) {
                        gpmDbSelected = true;
                    }
                }
            }
        }

        //token number is int 1 0r 0
        if (tokenLength == 1) {
            String tk = tokens[0];
            if (StringUtils.equals(tk, "1")) {
                nxDbSelected = true;
            }
        }
        String dbsText = "";
        if (nxDbSelected) {
            dbsText += DbAcType.NextProt.type();
        }
        if (gpmDbSelected) {
            if (StringUtils.isBlank(dbsText)) {
                dbsText += DbAcType.GPM.type();
            } else {
                dbsText += ", " + DbAcType.GPM.type();
            }
        }
        if (hpaDbSelected) {
            if (StringUtils.isBlank(dbsText)) {
                dbsText += DbAcType.HPA.type();
            } else {
                dbsText += ", " + DbAcType.HPA.type();
            }
        }
        if (paDbSelected) {
            if (StringUtils.isBlank(dbsText)) {
                dbsText += DbAcType.PA.type();
            } else {
                dbsText += ", " + DbAcType.PA.type();
            }
        }
        return dbsText;
    }

    public void sendMail(String emailFrom, String emailTo, String emailSubject, String emailBody, boolean isHtml) {
        this.mailService.sendMail(emailFrom, emailTo, emailSubject, emailBody, isHtml);
    }

    public void sendMail(String emailFrom, String emailTo, String emailSubject, Map<String, String> templateValues, String templateFile, boolean isHtml) {
        this.mailService.sendMail(emailFrom, emailTo, emailSubject, templateValues, templateFile, isHtml);
    }
}
