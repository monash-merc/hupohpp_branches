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
import edu.monash.merc.dao.TPBVersionDAO;
import edu.monash.merc.domain.TPBVersion;
import edu.monash.merc.service.TPBVersionService;
import edu.monash.merc.system.version.TLVersionTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 25/05/12
 * Time: 3:04 PM
 */

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@Transactional
public class TPBVersionServiceImpl implements TPBVersionService {

    @Autowired
    private TPBVersionDAO tpbVersionDao;

    public void setTpbVersionDao(TPBVersionDAO tpbVersionDao) {
        this.tpbVersionDao = tpbVersionDao;
    }

    /**
     * {@inheritDoc}
     */
    public void saveTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionDao.add(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionDao.merge(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionDao.update(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBVersion(TPBVersion tpbVersion) {
        this.tpbVersionDao.remove(tpbVersion);
    }

    /**
     * {@inheritDoc}
     */
    public TPBVersion getTPBVersionById(long id) {
        return this.tpbVersionDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBVersionById(long id) {
        this.tpbVersionDao.deleteTPBVersionById(id);
    }

    /**
     * {@inheritDoc}
     */
    public TPBVersion getCurrentVersion(ChromType chromType, int trackToken) {
        return this.tpbVersionDao.getCurrentVersion(chromType, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkTPBVersionAvailable(ChromType chromType, TLVersionTrack tlVersionTrack) {
        return this.tpbVersionDao.checkTPBVersionAvailable(chromType, tlVersionTrack);
    }

    /**
     * {@inheritDoc}
     */
    public TPBVersion getCurrentTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken) {
        return this.tpbVersionDao.getCurrentTPBVersionByChromTypeTrackToken(chromType, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public List<TPBVersion> getAllTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken) {
        return this.tpbVersionDao.getAllTPBVersionByChromTypeTrackToken(chromType, trackToken);
    }
}
