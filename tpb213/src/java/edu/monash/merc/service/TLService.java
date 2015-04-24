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
import edu.monash.merc.common.page.Pagination;
import edu.monash.merc.common.sql.OrderBy;
import edu.monash.merc.domain.TrafficLight;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 28/05/12
 * Time: 3:24 PM
 */
public interface TLService {

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
     * Get a TrafficLight based on a specified chromosome type and a ensembl accession id and a version timestampe
     *
     * @param chromType  a specified ChromType
     * @param ensgAc       a specified ensembl accession id
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
}
