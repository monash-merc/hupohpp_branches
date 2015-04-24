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

package edu.monash.merc.struts2.action;

import edu.monash.merc.common.name.ChromType;
import edu.monash.merc.common.name.DataType;
import edu.monash.merc.common.page.Pagination;
import edu.monash.merc.common.sql.OrderBy;
import edu.monash.merc.common.sql.OrderCondition;
import edu.monash.merc.common.sql.SqlCondition;
import edu.monash.merc.domain.TPBVersion;
import edu.monash.merc.domain.TrafficLight;
import edu.monash.merc.dto.TLEvidenceResponse;
import edu.monash.merc.dto.TLEvidenceSummary;
import edu.monash.merc.dto.tl.TLTypeEvLevelFilter;
import edu.monash.merc.util.DMUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 30/05/12
 * Time: 12:56 PM
 */

@Scope("prototype")
@Controller("tl.tlAction")
public class TrafficLightAction extends BaseAction {

    private Pagination<TrafficLight> tlPagination;

    private long tlGeneId;

    private long geneId;

    private String tpbDataType;

    private String dbSource;

    private TLEvidenceResponse tlEvidenceResponse;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private static String SUCCESS_RESPONSE = "true";

    private static String FAILED_RESPONSE = "false";

    private static final String NA = "N/A";

    private static final String ENSEMBL_ENSP_PREFIX = "ENSP";

    private HashMap<String, String> tpbDataTypes = new LinkedHashMap<String, String>();

    @PostConstruct
    public void initTL() {

        tpbDataTypes.put("PE", "PE");
        tpbDataTypes.put("PE_MS", "PE MS");
        tpbDataTypes.put("PE_MS_ANN", "PE MS ANN");
        tpbDataTypes.put("PE_MS_PROB", "PE MS PROB");
        tpbDataTypes.put("PE_MS_SAM", "PE MS SAM");
        tpbDataTypes.put("PE_ANTI", "PE ANTI");
        tpbDataTypes.put("PE_ANTI_ANN", "PE ANTI ANN");
        tpbDataTypes.put("PE_ANTI_IHC", "PE ANTI IHC");
        tpbDataTypes.put("PE_ANTI_IHC_NORM", "PE ANTI IHC NORM");
        tpbDataTypes.put("PE_OTH", "PE OTH");
        tpbDataTypes.put("PE_OTH_CUR", "PE OTH CUR");
    }

    public String trafficLight() {
        try {
            //debug
            printTypeFilter();


            String selectedChromType = tlSearchBean.getSelectedChromType();
            ChromType chromType = ChromType.fromType(selectedChromType);
            int combinatedDBSToken = combinateDBSToken();
            System.out.println(" === data source combination : " + combinatedDBSToken);
            //if no version provided, we just get the latest version for the current dbsource combination
            long selectedVersion = tlSearchBean.getSelectedVersion();
            if (selectedVersion == -1 || selectedVersion == 0) {
                TPBVersion tpbVersion = this.dmSystemService.getCurrentVersion(chromType, combinatedDBSToken);
                if (tpbVersion != null) {
                    selectedVersion = tpbVersion.getId();
                    tlSearchBean.setSelectedVersion(selectedVersion);
                }
            }
            //check the version number
            System.out.println("===== version no : " + selectedVersion);
            //added the traffic light order by the gene's start position.
            OrderCondition orderCon = SqlCondition.orderCon().add(OrderBy.asc("startPosition"));
            OrderBy[] orderBys = orderCon.orderBy();
            if (selectedVersion > 0) {
                tlPagination = this.dmSystemService.getVersionTrafficLight(chromType, combinatedDBSToken, selectedVersion, 1, -1, orderBys);
            } else {
                tlPagination = new Pagination<TrafficLight>();
            }
            //post-process the tpb versions
            processTPBVersions(combinatedDBSToken, selectedChromType);
        } catch (Exception ex) {
            logger.error(ex);
            addActionError(getText("tl.failed.to.display.the.traffic.light"));
            return ERROR;
        }
        return SUCCESS;
    }

    private void printTypeFilter() {

        List<TLTypeEvLevelFilter> typeEvLevelFilters = tlSearchBean.getTlTypeEvLevelFilters();
        if (typeEvLevelFilters != null) {
            System.out.println("=============== data type filter size: " + typeEvLevelFilters.size());
            for (TLTypeEvLevelFilter tFilter : typeEvLevelFilters) {
                System.out.println("============= data type: " + tFilter.getDataType());
                System.out.println("============= Green Level: " + tFilter.isTypeEvLevel4());
                System.out.println("============= Yellow Level: " + tFilter.isTypeEvLevel3());
                System.out.println("============= Red Level: " + tFilter.isTypeEvLevel2());
                System.out.println("============= Black Level: " + tFilter.isTypeEvLevel1());


            }
        }
    }

    /**
     * petlsum action - see the summary of the traffic light
     *
     * @return success if there is no error.
     */
    public String peTlSum() {
        tlEvidenceResponse = new TLEvidenceResponse();
        try {
            DataType dataType = DataType.fromType(tpbDataType);
            TLEvidenceSummary tlEvidenceSummary = this.dmSystemService.getPETLSummary(tlGeneId, dataType);
            if (tlEvidenceSummary != null) {
                tlEvidenceResponse.setTlEvidenceSummary(tlEvidenceSummary);
            }
            tlEvidenceResponse.setSuccess(SUCCESS_RESPONSE);
            tlEvidenceResponse.setMessage(getText("tl.get.the.traffic.light.summary.success.message"));
        } catch (Exception ex) {
            logger.error(ex);
            tlEvidenceResponse.setSuccess(FAILED_RESPONSE);
            tlEvidenceResponse.setMessage(getText("tl.failed.to.get.the.traffic.light.summary"));
        }
        return SUCCESS;
    }

    /**
     * srcGeneSum action , get a gene evidence summaries based on a db source and a data type
     *
     * @return success if retrieving a gene evidence summaries based on a db source and a data type
     */
    public String srcGeneSum() {
        tlEvidenceResponse = new TLEvidenceResponse();

        try {
            DataType dataType = DataType.fromType(tpbDataType);
            TLEvidenceSummary tlEvidenceSummary = this.dmSystemService.getTLPESummaryBySrcGene(dbSource, geneId, dataType);
            if (tlEvidenceSummary != null) {
                tlEvidenceResponse.setTlEvidenceSummary(tlEvidenceSummary);
            }

            tlEvidenceResponse.setSuccess(SUCCESS_RESPONSE);
            tlEvidenceResponse.setMessage(getText("tl.get.the.gene.evidence.summary.success.message"));
        } catch (Exception ex) {
            logger.error(ex);
            tlEvidenceResponse.setSuccess(FAILED_RESPONSE);
            tlEvidenceResponse.setMessage(getText("tl.failed.to.get.the.gene.evidence.summary"));
        }
        return SUCCESS;
    }

    /**
     * viewevidences action. get all evidences for a particular gene based on a data type
     *
     * @return success if there is no error.
     */
    public String viewEvidences() {
        tlEvidenceResponse = new TLEvidenceResponse();
        try {
            DataType dataType = DataType.fromType(tpbDataType);
            TLEvidenceSummary evidenceSummary = this.dmSystemService.getAllEvidencesByGeneAndType(geneId, dataType);
            tlEvidenceResponse.setTlEvidenceSummary(evidenceSummary);
            tlEvidenceResponse.setSuccess(SUCCESS_RESPONSE);
            tlEvidenceResponse.setMessage(getText("tl.get.the.gene.evidences.success.message"));
        } catch (Exception ex) {
            logger.error(ex);
            tlEvidenceResponse.setSuccess(FAILED_RESPONSE);
            tlEvidenceResponse.setMessage(getText("tl.failed.to.get.the.gene.evidences"));
        }
        return SUCCESS;
    }

    public String findTPBVersions() {
        System.out.println("=========== selected chromosome:" + tlSearchBean.getSelectedChromType());
        System.out.println("=========== selected nx db source:" + tlSearchBean.isNxDbSelected());
        System.out.println("=========== selected gpm db source:" + tlSearchBean.isGpmDbSelected());
        System.out.println("=========== selected hpa db source:" + tlSearchBean.isHpaDbSelected());
        System.out.println("=========== selected pa db source:" + tlSearchBean.isPaDbSelected());
        int combinatedDBSToken = combinateDBSToken();
        processTPBVersions(combinatedDBSToken, tlSearchBean.getSelectedChromType());
        return SUCCESS;
    }

    private void processTPBVersions(int token, String selectedChrom) {
        ChromType chromType = ChromType.fromType(selectedChrom);
        List<TPBVersion> allTpbVersions = this.dmSystemService.getAllTPBVersionByChromTypeTrackToken(chromType, token);
        tpbVersions = convertToVersionMap(allTpbVersions);
    }

    private HashMap<Long, String> convertToVersionMap(List<TPBVersion> tlVersions) {
        HashMap<Long, String> versions = new LinkedHashMap<Long, String>();
        //put the head
        versions.put(Long.valueOf(-1), "- select a version -");
        for (TPBVersion tpbVersion : tlVersions) {
            long versionId = tpbVersion.getId();
            int versionNum = tpbVersion.getVersionNo();
            Date versionTime = tpbVersion.getCreatedTime();
            versions.put(versionId, (versionNum + " - [ " + DMUtil.dateToDDMMYYYYStr(versionTime) + " ]"));
        }
        return versions;
    }

    private int combinateDBSToken() {
        String token = NONE_DS_SELECTED;

        if (tlSearchBean.isNxDbSelected()) {
            token = DS_SELECTED;
        }

        if (tlSearchBean.isGpmDbSelected()) {
            token = token + DS_SELECTED;
        } else {
            token = token + NONE_DS_SELECTED;
        }
        if (tlSearchBean.isHpaDbSelected()) {
            token = token + DS_SELECTED;
        } else {
            token = token + NONE_DS_SELECTED;
        }
        if (tlSearchBean.isPaDbSelected()) {
            token = token + DS_SELECTED;
        } else {
            token = token + NONE_DS_SELECTED;
        }
        //just make sure we select a one datasource if none datasource selected
        if (StringUtils.equals(token, NONE_DS_SELECTED)) {
            token = DS_SELECTED;
        }
        return Integer.valueOf(StringUtils.reverse(token));
    }

    public Pagination<TrafficLight> getTlPagination() {
        return tlPagination;
    }

    public void setTlPagination(Pagination<TrafficLight> tlPagination) {
        this.tlPagination = tlPagination;
    }

    public long getTlGeneId() {
        return tlGeneId;
    }

    public void setTlGeneId(long tlGeneId) {
        this.tlGeneId = tlGeneId;
    }

    public long getGeneId() {
        return geneId;
    }

    public void setGeneId(long geneId) {
        this.geneId = geneId;
    }

    public String getTpbDataType() {
        return tpbDataType;
    }

    public void setTpbDataType(String tpbDataType) {
        this.tpbDataType = tpbDataType;
    }

    public String getDbSource() {
        return dbSource;
    }

    public void setDbSource(String dbSource) {
        this.dbSource = dbSource;
    }

    public TLEvidenceResponse getTlEvidenceResponse() {
        return tlEvidenceResponse;
    }

    public void setTlEvidenceResponse(TLEvidenceResponse tlEvidenceResponse) {
        this.tlEvidenceResponse = tlEvidenceResponse;
    }

    public HashMap<String, String> getTpbDataTypes() {
        return tpbDataTypes;
    }

    public void setTpbDataTypes(HashMap<String, String> tpbDataTypes) {
        this.tpbDataTypes = tpbDataTypes;
    }
}
