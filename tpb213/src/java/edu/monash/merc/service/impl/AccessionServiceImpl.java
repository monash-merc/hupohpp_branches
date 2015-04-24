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

import edu.monash.merc.dao.AccessionDAO;
import edu.monash.merc.domain.Accession;
import edu.monash.merc.service.AccessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 17/04/12
 * Time: 4:02 PM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@Transactional
public class AccessionServiceImpl implements AccessionService {
    @Autowired
    private AccessionDAO accessionDao;

    public void setAccessionDao(AccessionDAO accessionDao) {
        this.accessionDao = accessionDao;
    }

    public void saveAccession(Accession accession) {
        this.accessionDao.add(accession);
    }

    public void mergeAccession(Accession accession) {
        this.accessionDao.merge(accession);
    }

    public void updateAccession(Accession accession) {
        this.accessionDao.update(accession);
    }

    public void deleteAccession(Accession accession) {
        this.accessionDao.remove(accession);
    }

    public void deleteAccessionById(long id) {
        this.accessionDao.deleteAccessionById(id);
    }

    public void deleteAccessionByAcId(String acId) {
        this.accessionDao.deleteAccessionByAcId(acId);
    }

    public Accession getAccessionById(long id) {
        return this.accessionDao.get(id);
    }

    public Accession getAccessionByAccessionAcType(String accession, String acType) {
        return this.accessionDao.getAccessionByAccessionAcType(accession, acType);
    }

}
