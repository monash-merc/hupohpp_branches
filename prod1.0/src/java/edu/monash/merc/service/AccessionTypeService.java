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

import edu.monash.merc.domain.AccessionType;

/**
 * AccessionTypeService interface which provides the service operations for AccessionType.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 17/04/12 3:50 PM
 */
public interface AccessionTypeService {
    /**
     * save an AccessionType
     *
     * @param accessionType an AccessionType
     */
    void saveAccessionType(AccessionType accessionType);

    /**
     * merge an AccessionType
     *
     * @param accessionType an AccessionType
     */
    void mergeAccessionType(AccessionType accessionType);

    /**
     * update an AccessionType
     *
     * @param accessionType an AccessionType
     */
    void updateAccessionType(AccessionType accessionType);

    /**
     * delete an AccessionType
     *
     * @param accessionType an AccessionType
     */
    void deleteAccessionType(AccessionType accessionType);

    /**
     * delete an AccessionType by an AccessionType id
     *
     * @param id an AccessionType id
     */
    void deleteAccessionTypeById(long id);

    /**
     * get an AccessionType by an AccessionType id
     *
     * @param id an AccessionType id
     * @return an AccessionType
     */
    AccessionType getAccessionTypeById(long id);


    /**
     * get an AccessionType by an Accession type name
     *
     * @param typeName an Accession type name
     * @return an AccessionType
     */
    AccessionType getAccessionTypeByType(String typeName);
}
