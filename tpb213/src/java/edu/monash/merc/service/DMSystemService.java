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

package edu.monash.merc.service;

import edu.monash.merc.common.name.ChromType;
import edu.monash.merc.common.name.DataType;
import edu.monash.merc.common.name.DbAcType;
import edu.monash.merc.common.page.Pagination;
import edu.monash.merc.common.sql.OrderBy;
import edu.monash.merc.domain.*;
import edu.monash.merc.dto.GPMEntryBean;
import edu.monash.merc.dto.NXEntryBean;
import edu.monash.merc.dto.TLEvidenceSummary;
import edu.monash.merc.dto.hpa.HPAEntryBean;
import edu.monash.merc.system.version.TLVersionTrack;

import java.util.Date;
import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 16/04/12
 * Time: 11:46 AM
 */
public interface DMSystemService {
    /**
     * Save a Gene
     *
     * @param gene A Gene
     */
    void saveGene(Gene gene);

    /**
     * Merge a Gene
     *
     * @param gene A Gene
     */
    void mergeGene(Gene gene);

    /**
     * Update a Gene
     *
     * @param gene A Gene
     */
    void updateGene(Gene gene);

    /**
     * Delete a Gene
     *
     * @param gene
     */
    void deleteGene(Gene gene);

    /**
     * Get a Gene by id
     *
     * @param id A Gene id
     * @return A Gene object
     */
    Gene getGeneById(long id);

    /**
     * Delete a Gene by id
     *
     * @param id A Gene
     */
    void deleteGeneById(long id);

    /**
     * Get a Gene specified by an Ensembl gene accession,  db source and version timestamp
     *
     * @param ensgAccession An ensembl gene accession
     * @param dbSource      A db source name
     * @param versionTime   A version timestamp
     * @return An unique Gene object
     */
    Gene getGeneByEnsgAndDbVersion(String ensgAccession, String dbSource, Date versionTime);

    /**
     * Get a list of Gene for a specified DbAcType type and Chromosome Type and a version timestamp
     *
     * @param dbAcType    a DbAcType type
     * @param chromType   a ChromType chromosome type
     * @param versionTime a version timestamp
     * @return A list of Gene based on  a specified DbAcType type and Chromosome Type and a version timestamp
     */
    List<Gene> getGeneByDBSChromVersion(DbAcType dbAcType, ChromType chromType, Date versionTime);

    /**
     * Get a list of Accession associated with this Gene
     *
     * @param geneId a Gene Id
     * @return a List of associated Accessions objects
     */
    List<Accession> getAllAssociatedAccessionsByGeneId(long geneId);

    /**
     * Get a list of Gene for a specified TLGene id;
     *
     * @param tlGeneId a specified TLGene id
     * @return A list of Gene based on  a specified TLGene id
     */
    List<Gene> getGenesByTLGeneId(long tlGeneId);

    //database source
    void saveDBSource(DBSource dbSource);

    void mergeDBSource(DBSource dbSource);

    void updateDBSource(DBSource dbSource);

    void deleteDBSourceById(long id);

    DBSource getDBSourceById(long id);

    DBSource getDBSourceByName(String dbName);

    //accession type
    void saveAccessionType(AccessionType accessionType);

    void mergeAccessionType(AccessionType accessionType);

    void updateAccessionType(AccessionType accessionType);

    void deleteAccessionType(AccessionType accessionType);

    void deleteAccessionTypeById(long id);

    AccessionType getAccessionTypeById(long id);

    AccessionType getAccessionTypeByType(String typeName);

    //accession
    void saveAccession(Accession accession);

    void mergeAccession(Accession accession);

    void updateAccession(Accession accession);

    void deleteAccession(Accession accession);

    void deleteAccessionById(long id);

    void deleteAccessionByAcId(String acId);

    Accession getAccessionById(long id);

    Accession getAccessionByAccessionAcType(String accession, String acType);

    //tpb data type

    /**
     * Save a TPBDataType
     *
     * @param tpbDataType a TPBDataType
     */
    void saveTPBDataType(TPBDataType tpbDataType);

    /**
     * Merge a TPBDataType
     *
     * @param tpbDataType a TPBDataType
     */
    void mergeTPBDataType(TPBDataType tpbDataType);

    /**
     * Update a TPBDataType
     *
     * @param tpbDataType a TPBDataType
     */
    void updateTPBDataType(TPBDataType tpbDataType);

    /**
     * Delete a TPBDataType
     *
     * @param tpbDataType a TPBDataType
     */
    void deleteTPBDataType(TPBDataType tpbDataType);

    /**
     * Delete a TPBDataType by id
     *
     * @param id a TPBDataType id
     */
    void deleteTPBDataTypeById(long id);

    /**
     * Get a TPBDataType by a id
     *
     * @param id A TPBDataType id
     * @return a TPBDataType object
     */
    TPBDataType getTPBDataTypeById(long id);

    /**
     * Get a TPBDataType by a type name
     *
     * @param type a type name of TPBDataType
     * @return a TPBDataType object
     */
    TPBDataType getTPBDataTypeByTypeName(String type);


    /**
     * Get the sub TPBDataType by a TPBDataType Id;
     *
     * @param id a TPBDataType id
     * @return a List of TPBDataType if available
     */
    List<TPBDataType> getSubTPBDataType(long id);


    /**
     * Get the sub TPBDataType by a TPBDataType dataType name;
     *
     * @param dataType a TPBDataType dataType
     * @return a List of TPBDataType if available
     */
    List<TPBDataType> getSubTPBDataType(String dataType);


    /**
     * Check whether it's a last level of TPBDataType or not
     *
     * @param dataType a TPBDataType dataType name
     * @return true if it's a last level of TPBDataType.
     */
    boolean isLastLevelType(String dataType);

    /**
     * Check whether it's a last level of TPBDataType or not
     *
     * @param id a TPBDataType dataType id
     * @return true if it's a last level of TPBDataType.
     */
    public boolean isLastLevelType(long id);

    //DSVersion

    /**
     * Save the DSVersion
     *
     * @param dsVersion a DSVersion object
     */
    void saveDSVersion(DSVersion dsVersion);


    /**
     * Merge the DSVersion
     *
     * @param dsVersion a DSVersion object
     */
    void mergeDSVersion(DSVersion dsVersion);

    /**
     * Update the DSVersion
     *
     * @param dsVersion a DSVersion object
     */
    void updateDSVersion(DSVersion dsVersion);

    /**
     * Delete the DSVersion
     *
     * @param dsVersion a DSVersion object
     */
    void deleteDSVersion(DSVersion dsVersion);

    /**
     * Delete the DSVersion by id
     *
     * @param id a DSVersion object id
     */
    void deleteDSVersionById(long id);

    /**
     * Get a DSVersion by id
     *
     * @param id a DSVersion object id
     * @return a DSVersion object
     */
    DSVersion getDSVersionById(long id);


    /**
     * Get the current version of the DSVersion based on a DBSource Type and a ChromType chromosome type
     *
     * @param dbAcType  a DbAcType type
     * @param chromType a ChromType name
     * @return a DSVersion object
     */
    DSVersion getCurrentDSVersionByChromDbs(DbAcType dbAcType, ChromType chromType);

    /**
     * Check the data in the database is up to date or not.
     *
     * @param dbAcType  a DbAcType type
     * @param chromType a chromosome type
     * @param fileName  an imported file name
     * @param timeToken a timeToken of an imported file
     * @return true if the data is up to date
     */
    boolean checkUpToDate(DbAcType dbAcType, ChromType chromType, String fileName, String timeToken);

    //TPBVersion

    /**
     * Save a TPBVersion
     *
     * @param tpbVersion A TPBVersion
     */
    void saveTPBVersion(TPBVersion tpbVersion);

    /**
     * Merge a TPBVersion
     *
     * @param tpbVersion A TPBVersion
     */
    void mergeTPBVersion(TPBVersion tpbVersion);

    /**
     * Update a TPBVersion
     *
     * @param tpbVersion A TPBVersion
     */
    void updateTPBVersion(TPBVersion tpbVersion);

    /**
     * Delete a TPBVersion
     *
     * @param tpbVersion
     */
    void deleteTPBVersion(TPBVersion tpbVersion);

    /**
     * Get a TPBVersion by id
     *
     * @param id a TPBVersion id
     * @return a TPBVersion object
     */
    TPBVersion getTPBVersionById(long id);

    /**
     * Delete a TPBVersion by id
     *
     * @param id a TPBVersion id
     */
    void deleteTPBVersionById(long id);

    /**
     * get the current TPBVersion by a chromosome type
     *
     * @param chromType  a chromosome ChromType type
     * @param trackToken a combination of different datasource track token
     * @return a TPBVersion object if available or null
     */
    TPBVersion getCurrentVersion(ChromType chromType, int trackToken);

    /**
     * get a TPBVersion by a chromosome type and a traffic light version track
     *
     * @param chromType      a chromosome ChromType
     * @param tlVersionTrack a traffic light version track  TLVersionTrack
     * @return true if available or false.
     */
    boolean checkTPBVersionAvailable(ChromType chromType, TLVersionTrack tlVersionTrack);

    /**
     * get the current TPBVersion by a chromosome type and a combination track token.
     *
     * @param chromType  a chromosome ChromType
     * @param trackToken a combination of datasource track token
     * @return a TPBVersion if available or null
     */
    TPBVersion getCurrentTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken);

    /**
     * get all TPBVersions by a chromosome and a combination datasource track token.
     *
     * @param chromType  a chromosome ChromType
     * @param trackToken a combination datasource track token
     * @return a list of TPBVersions if available
     */
    List<TPBVersion> getAllTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken);

    /**
     * Save the NextProt data entries for a chromosome type
     *
     * @param chromType    a ChromType
     * @param nxEntryBeans a List of NXEntryBean
     * @param importedTime a imported time
     * @param fileName     a nextprot xml file name
     * @param timeToken    a nextprot xml timetoken
     */
    void saveNextProtDataEntryByChromosome(ChromType chromType, List<NXEntryBean> nxEntryBeans, Date importedTime, String fileName, String timeToken);

    /**
     * Save the GPM data entries
     *
     * @param chromTypes    a List of ChromType
     * @param gpmEntryBeans a List of GPM data entry
     * @param importedTime  a imported time
     * @param fileName      a gpm xml file name
     * @param timeToken     a gpm xml timetoken
     */
    void saveGPMDataEntry(List<ChromType> chromTypes, List<GPMEntryBean> gpmEntryBeans, Date importedTime, String fileName, String timeToken);


    /**
     * Save the HPA data entries
     *
     * @param chromTypes    a List of ChromType
     * @param hpaEntryBeans a List of HPA data entry
     * @param importedTime  a imported time
     * @param fileName      a hpa xml file name
     * @param versionToken  a hpa xml version token
     */
    void saveHPADataEntry(List<ChromType> chromTypes, List<HPAEntryBean> hpaEntryBeans, Date importedTime, String fileName, String versionToken);

    //traffic light TLColor
    void saveTLColor(TLColor tlColor);

    void mergeTLColor(TLColor tlColor);

    void updateTLColor(TLColor tlColor);

    void deleteTLColor(TLColor tlColor);

    void deleteTLColorById(long id);

    TLColor getTLColorById(long id);

    TLColor getTLColorByColor(String color);

    TLColor getTLColorByColorLevel(int colorLevel);

    //Evidence
    void savePEEvidence(PEEvidence peEvidence);

    void mergePEEvidence(PEEvidence peEvidence);

    void updatePEEvidence(PEEvidence peEvidence);

    void deletePEEvidence(PEEvidence peEvidence);

    void deletePEEvidenceById(long id);

    PEEvidence getPEEvidenceById(long id);

    PEEvidence getPESummaryByGeneAndType(long geneId, DataType dataType);

    TLEvidenceSummary getPETLSummary(long tlGeneId, DataType dataType);

    TLEvidenceSummary getTLPESummaryBySrcGene(String dbSource, long geneId, DataType dataType);

    TLEvidenceSummary getAllEvidencesByGeneAndType(long geneId, DataType dataType);

    List<PEEvidence> getEvidencesByGeneAndType(long geneId, DataType dataType);

    //Annotation
    void saveNXAnnotation(NXAnnotation nxAnnotation);

    void updateNXAnnotation(NXAnnotation nxAnnotation);

    void deleteNXAnnotation(NXAnnotation nxAnnotation);

    void deleteNXAnnotationById(long id);

    NXAnnotation getNXAnnotationById(long id);

    //Annotation Evidence
    void saveNXAnnEvidence(NXAnnEvidence nxAnnEvidence);

    void updateNXAnnEvidence(NXAnnEvidence nxAnnEvidence);

    void deleteNXAnnEvidence(NXAnnEvidence nxAnnEvidence);

    void deleteNXAnnEvidenceById(long id);

    NXAnnEvidence getNXAnnEvidenceById(long id);

    //Annotation IsoFormAnnotation
    void saveNXIsoFormAnn(NXIsoFormAnn nxIsoFormAnn);

    void updateNXIsoFormAnn(NXIsoFormAnn nxIsoFormAnn);

    void deleteNXIsoFormAnn(NXIsoFormAnn nxIsoFormAnn);

    void deleteNXIsoFormAnnById(long id);

    NXIsoFormAnn getNXIsoFormAnnById(long id);

    //TLGene

    /**
     * Get a TLGene by gene id
     *
     * @param id a TLGene id
     * @return a TLGene object
     */
    TLGene getTLGeneById(long id);

    /**
     * Save a TLGene
     *
     * @param tlGene a TLGene object
     */
    void saveTLGene(TLGene tlGene);

    /**
     * Merge a TLGene object
     *
     * @param tlGene a TLGene object
     */
    void mergeTLGene(TLGene tlGene);

    /**
     * Update a TLGene object
     *
     * @param tlGene a TLGene object
     */
    void updateTLGene(TLGene tlGene);

    /**
     * Delete a TLGene object
     *
     * @param tlGene a TLGene object
     */
    void deleteTLGene(TLGene tlGene);

    /**
     * Delete a TLGene object by a TLGene id
     *
     * @param id a TLGene id
     */
    void deleteTLGeneById(long id);

    //TrafficLight

    /**
     * Save a TrafficLight
     *
     * @param trafficLight A TrafficLight
     */
    void saveTrafficLight(TrafficLight trafficLight);

    /**
     * Merge a TrafficLight
     *
     * @param trafficLight a TrafficLight
     */
    void mergeTrafficLight(TrafficLight trafficLight);

    /**
     * Update a TrafficLight
     *
     * @param trafficLight A TrafficLight
     */
    void updateTrafficLight(TrafficLight trafficLight);

    /**
     * Delete a TrafficLight
     *
     * @param trafficLight
     */
    void deleteTrafficLight(TrafficLight trafficLight);

    /**
     * Get a TrafficLight by id
     *
     * @param id a TrafficLight id
     * @return a TrafficLight object
     */
    TrafficLight getTrafficLightById(long id);


    /**
     * Delete a TrafficLight by id
     *
     * @param id a TrafficLight id
     */
    void deleteTLById(long id);

    /**
     * create a versioning traffic lights based on a chromosome type.
     *
     * @param chromType      a specific chromosome type
     * @param tlVersionTrack a traffic light version track which includes the combination of different datasource
     * @param createdTime    a created time
     */
    void createVersionTLByChromType(ChromType chromType, TLVersionTrack tlVersionTrack, Date createdTime);

    /**
     * Get a TrafficLight based on a specified chromosome type and a ensembl accession id and a version timestampe
     *
     * @param chromType  a specified ChromType
     * @param ensgAc     a specified ensembl accession id
     * @param versionId  a specified version id
     * @param trackToken a combination of datasources track token
     * @return a TrafficLight object
     */
    TrafficLight getTLByChromEnsemblAcVersionToken(ChromType chromType, String ensgAc, long versionId, int trackToken);

    /**
     * Get a Paginable TrafficLights
     *
     * @param chromType      a ChromType chromosome type
     * @param trackToken     a combination of datasource track token
     * @param versionId      a version id of TrafficLight
     * @param startPage      a start page
     * @param recordsPerPage how many records will be displayed in one page
     * @param orderConds     an OrderBy conditions
     * @return A Pagination TrafficLight object
     */
    Pagination<TrafficLight> getVersionTrafficLight(ChromType chromType, int trackToken, long versionId, int startPage, int recordsPerPage, OrderBy[] orderConds);

    /**
     * Get a TPBGene by TPBGene id
     *
     * @param id a TPBGene id
     * @return a TPBGene object
     */
    TPBGene getTPBGeneById(long id);

    /**
     * Save a TPBGene
     *
     * @param tpbGene a TPBGene object
     */
    void saveTPBGene(TPBGene tpbGene);

    /**
     * Merge a TPBGene object
     *
     * @param tpbGene a TPBGene object
     */
    void mergeTPBGene(TPBGene tpbGene);

    /**
     * Update a TPBGene object
     *
     * @param tpbGene a TPBGene object
     */
    void updateTPBGene(TPBGene tpbGene);

    /**
     * Delete a TPBGene object
     *
     * @param tpbGene a TPBGene object
     */
    void deleteTPBGene(TPBGene tpbGene);

    /**
     * Get the TPBGene by an ensembl accession id
     *
     * @param ensgAccession an ensembl accession id
     * @return a TPBGene
     */
    TPBGene getTPBGeneByEnsgAc(String ensgAccession);

    /**
     * Import all the TPBGenes
     *
     * @param tpbGenes a list of TPBGenes to persist
     */
    void importTPBGenes(List<TPBGene> tpbGenes);
}
