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

import edu.monash.merc.dao.TLColorDAO;
import edu.monash.merc.domain.TLColor;
import edu.monash.merc.service.TLColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TLColorServiceImpl class implements the TLColorService interface which provides the service operations for TLColor.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 23/04/12 11:28 AM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@Transactional
public class TLColorServiceImpl implements TLColorService {

    @Autowired
    private TLColorDAO tlColorDao;

    /**
     * set a TLColorDAO
     *
     * @param tlColorDao aTLColorDAO object
     */
    public void setTlColorDao(TLColorDAO tlColorDao) {
        this.tlColorDao = tlColorDao;
    }

    /**
     * {@inheritDoc}
     */
    public void saveTLColor(TLColor tlColor) {
        this.tlColorDao.add(tlColor);
    }

    /**
     * {@inheritDoc}
     */
    public void mergeTLColor(TLColor tlColor) {
        this.tlColorDao.merge(tlColor);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTLColor(TLColor tlColor) {
        this.tlColorDao.update(tlColor);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTLColor(TLColor tlColor) {
        this.tlColorDao.remove(tlColor);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTLColorById(long id) {
        this.tlColorDao.deleteTLColorById(id);
    }

    /**
     * {@inheritDoc}
     */
    public TLColor getTLColorById(long id) {
        return this.tlColorDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public TLColor getTLColorByColor(String color) {
        return this.tlColorDao.getTLColorByColor(color);
    }

    /**
     * {@inheritDoc}
     */
    public TLColor getTLColorByColorLevel(int colorLevel) {
        return this.tlColorDao.getTLColorByColorLevel(colorLevel);
    }
}
