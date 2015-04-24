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
import edu.monash.merc.common.name.DbAcType;
import edu.monash.merc.domain.DSVersion;
import edu.monash.merc.dto.DBVersionBean;

import java.util.List;

/**
 * DSVersionService interface which provides the service operations for DSVersion.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 21/05/12 6:23 PM
 */
public interface DSVersionService {

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

    /**
     * Get a list of latest DBVersions by chromsome type
     *
     * @param chromType a chromosome type
     * @return a list of latest DBVersionBean objects
     */
    List<DBVersionBean> getLatestDBSVersionByChromosome(ChromType chromType);
}
