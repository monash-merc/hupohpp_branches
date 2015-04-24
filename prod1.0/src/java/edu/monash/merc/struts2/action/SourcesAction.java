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

package edu.monash.merc.struts2.action;

import edu.monash.merc.common.name.ChromType;
import edu.monash.merc.dto.DBVersionBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * SourcesAction action class which handles the sources action request
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 7/08/12 12:46 PM
 */
@Scope("prototype")
@Controller("site.sourcesAction")
public class SourcesAction extends BaseAction {

    private String selectedChrom;

    private List<DBVersionBean> dbVersionBeans;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public String sources() {
        if (StringUtils.isBlank(selectedChrom)) {
            selectedChrom = ChromType.CHM7.chm();
        }

        try {
            dbVersionBeans = dmSystemService.getLatestDBSVersionByChromosome(ChromType.fromType(selectedChrom));
        } catch (Exception ex) {
            logger.error(ex);
            addActionError(getText("ds.failed.to.get.the.lastest.version.of.dbsource"));
            return ERROR;
        }
        return SUCCESS;
    }

    public String getSelectedChrom() {
        return selectedChrom;
    }

    public void setSelectedChrom(String selectedChrom) {
        this.selectedChrom = selectedChrom;
    }

    public List<DBVersionBean> getDbVersionBeans() {
        return dbVersionBeans;
    }

    public void setDbVersionBeans(List<DBVersionBean> dbVersionBeans) {
        this.dbVersionBeans = dbVersionBeans;
    }
}
