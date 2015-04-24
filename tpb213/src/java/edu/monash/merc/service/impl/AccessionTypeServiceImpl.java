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

import edu.monash.merc.dao.AccessionTypeDAO;
import edu.monash.merc.domain.AccessionType;
import edu.monash.merc.service.AccessionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccessionTypeServiceImpl class which implements AccessionTypeService interface.
 *
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 *
 * Date: 17/04/12
 * Time: 3:52 PM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@Transactional
public class AccessionTypeServiceImpl implements AccessionTypeService {
    @Autowired
    private AccessionTypeDAO accessionTypeDao;

    public void setAccessionTypeDao(AccessionTypeDAO accessionTypeDao) {
        this.accessionTypeDao = accessionTypeDao;
    }

    public void saveAccessionType(AccessionType accessionType) {
        this.accessionTypeDao.add(accessionType);
    }

    public void mergeAccessionType(AccessionType accessionType) {
        this.accessionTypeDao.merge(accessionType);
    }

    public void updateAccessionType(AccessionType accessionType) {
        this.accessionTypeDao.update(accessionType);
    }

    public void deleteAccessionType(AccessionType accessionType) {
        this.accessionTypeDao.remove(accessionType);
    }

    public void deleteAccessionTypeById(long id) {
        this.accessionTypeDao.deleteAccessionTypeById(id);
    }

    public AccessionType getAccessionTypeById(long id) {
        return this.accessionTypeDao.get(id);
    }

    public AccessionType getAccessionTypeByType(String typeName) {
        return this.accessionTypeDao.getAccessionTypeByType(typeName);
    }
}
