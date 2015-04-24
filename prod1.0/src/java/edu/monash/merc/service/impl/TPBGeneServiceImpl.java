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

import edu.monash.merc.dao.TPBGeneDAO;
import edu.monash.merc.domain.TPBGene;
import edu.monash.merc.service.TPBGeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TPBGeneServiceImpl class implements the TPBGeneService interface which provides the service operations for TPBGene.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 19/06/12 3:02 PM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@Transactional
public class TPBGeneServiceImpl implements TPBGeneService {
    @Autowired
    private TPBGeneDAO tpbGeneDao;

    /**
     * set a  TPBGeneDAO
     *
     * @param tpbGeneDao a TPBGeneDAO object
     */
    public void setTpbGeneDao(TPBGeneDAO tpbGeneDao) {
        this.tpbGeneDao = tpbGeneDao;
    }

    /**
     * {@inheritDoc}
     */
    public TPBGene getTPBGeneById(long id) {
        return this.tpbGeneDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public void saveTPBGene(TPBGene tpbGene) {
        this.tpbGeneDao.add(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTPBGene(TPBGene tpbGene) {
        this.tpbGeneDao.merge(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTPBGene(TPBGene tpbGene) {
        this.tpbGeneDao.update(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBGene(TPBGene tpbGene) {
        this.tpbGeneDao.remove(tpbGene);
    }

    /**
     * {@inheritDoc}
     */
    public TPBGene getTPBGeneByEnsgAc(String ensgAccession) {
        return this.tpbGeneDao.getTPBGeneByEnsgAc(ensgAccession);
    }
}
