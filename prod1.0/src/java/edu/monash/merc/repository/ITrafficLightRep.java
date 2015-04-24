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

package edu.monash.merc.repository;

import edu.monash.merc.common.name.ChromType;
import edu.monash.merc.common.page.Pagination;
import edu.monash.merc.common.sql.OrderBy;
import edu.monash.merc.domain.TrafficLight;
import edu.monash.merc.dto.TLSumReporter;
import edu.monash.merc.dto.tl.TLSearchBean;

/**
 * ITrafficLightRep interface which defines the database operations for the TrafficLight.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 9/03/12 11:33 AM
 */
public interface ITrafficLightRep {

    /**
     * Delete a TrafficLight by id
     *
     * @param id a TrafficLight id
     */
    void deleteTLById(long id);

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
     * Get a Paginable TrafficLights
     *
     * @param tlSearchBean   a TLSearchBean
     * @param startPage      a start page
     * @param recordsPerPage how many records will be displayed in one page
     * @param orderConds     an OrderBy conditions
     * @return A Pagination TrafficLight object
     */
    Pagination<TrafficLight> getVersionTrafficLight(TLSearchBean tlSearchBean, int startPage, int recordsPerPage, OrderBy[] orderConds);

    /**
     * Get the traffic light summary report
     *
     * @param tlSearchBean a TLSearchBean
     * @return a TLSumReporter object.
     */
    TLSumReporter getTLSumReporter(TLSearchBean tlSearchBean);
}
