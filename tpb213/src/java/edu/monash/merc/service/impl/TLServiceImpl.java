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
import edu.monash.merc.common.page.Pagination;
import edu.monash.merc.common.sql.OrderBy;
import edu.monash.merc.dao.TrafficLightDAO;
import edu.monash.merc.domain.TrafficLight;
import edu.monash.merc.service.TLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 28/05/12
 * Time: 3:26 PM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@Transactional
public class TLServiceImpl implements TLService {

    @Autowired
    private TrafficLightDAO trafficLightDao;

    public void setTrafficLightDao(TrafficLightDAO trafficLightDao) {
        this.trafficLightDao = trafficLightDao;
    }

    /**
     * {@inheritDoc}
     */
    public void saveTrafficLight(TrafficLight trafficLight) {
        this.trafficLightDao.add(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTrafficLight(TrafficLight trafficLight) {
        this.trafficLightDao.merge(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTrafficLight(TrafficLight trafficLight) {
        this.trafficLightDao.update(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTrafficLight(TrafficLight trafficLight) {
        this.trafficLightDao.remove(trafficLight);
    }

    /**
     * {@inheritDoc}
     */
    public TrafficLight getTrafficLightById(long id) {
        return this.trafficLightDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTLById(long id) {
        this.trafficLightDao.deleteTLById(id);
    }

    /**
     * {@inheritDoc}
     */
    public TrafficLight getTLByChromEnsemblAcVersionToken(ChromType chromType, String ensgAc, long versionId, int trackToken) {
        return this.trafficLightDao.getTLByChromEnsemblAcVersionToken(chromType, ensgAc, versionId, trackToken);
    }

    /**
     * {@inheritDoc}
     */
    public Pagination<TrafficLight> getVersionTrafficLight(ChromType chromType, int trackToken, long versionId, int startPage, int recordsPerPage, OrderBy[] orderConds) {
        return this.trafficLightDao.getVersionTrafficLight(chromType, trackToken, versionId, startPage, recordsPerPage, orderConds);
    }
}
