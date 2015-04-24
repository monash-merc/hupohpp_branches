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

package edu.monash.merc.dao;

import edu.monash.merc.common.name.ChromType;
import edu.monash.merc.common.page.Pagination;
import edu.monash.merc.common.sql.OrderBy;
import edu.monash.merc.domain.TrafficLight;
import edu.monash.merc.repository.ITrafficLightRep;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 9/03/12
 * Time: 11:57 AM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Repository
public class TrafficLightDAO extends HibernateGenericDAO<TrafficLight> implements ITrafficLightRep {

    public void deleteTLById(long id) {
        String del_hql = "DELETE FROM " + this.persistClass.getSimpleName() + " AS tl WHERE tl.id = : id";
        Query query = this.session().createQuery(del_hql);
        query.setLong("id", id);
        query.executeUpdate();
    }

    public TrafficLight getTLByChromEnsemblAcVersionToken(ChromType chromType, String ensgAc, long versionId, int trackToken) {
        Criteria qCriteria = this.session().createCriteria(this.persistClass);

        Criteria tlGeneCriteria = qCriteria.createCriteria("tlGene");
        tlGeneCriteria.add(Restrictions.eq("chromosome", chromType.chm()));

        Disjunction orConds = Restrictions.disjunction();
        orConds.add(Restrictions.eq("ensgAccession", ensgAc));
        tlGeneCriteria.add(orConds);
        Criteria tpbVCriteria = qCriteria.createCriteria("tpbVersion");
        tpbVCriteria.add(Restrictions.eq("id", versionId));
        tpbVCriteria.add(Restrictions.eq("trackToken", trackToken));
        return (TrafficLight) qCriteria.uniqueResult();
    }

    public Pagination<TrafficLight> getVersionTrafficLight(ChromType chromType, int trackToken, long versionId, int startPage, int recordsPerPage, OrderBy[] orderConds) {

        Criteria tlCriteria = this.session().createCriteria(this.persistClass);
        Criteria tlgCriteria = tlCriteria.createCriteria("tlGene");
        Criteria tpbVCriteria = tlCriteria.createCriteria("tpbVersion");
        tlgCriteria.add(Restrictions.eq("chromosome", chromType.chm()));
        // tlgCriteria.add(Restrictions.in("displayName", geneNames));
        tpbVCriteria.add(Restrictions.eq("id", versionId));
        tpbVCriteria.add(Restrictions.eq("trackToken", trackToken));
        tlCriteria.setProjection(Projections.rowCount());
        int total = ((Long) tlCriteria.uniqueResult()).intValue();
        //get all results
        if (recordsPerPage == -1) {
            recordsPerPage = total;
            startPage = 1;
        }
        Pagination<TrafficLight> ptl = new Pagination<TrafficLight>(startPage, recordsPerPage, total);

        Criteria tlQCriteria = this.session().createCriteria(this.persistClass);
        Criteria tlgQCriteria = tlQCriteria.createCriteria("tlGene");
        Criteria tpbVQCriteria = tlQCriteria.createCriteria("tpbVersion");
        tlgQCriteria.add(Restrictions.eq("chromosome", chromType.chm()));
        tpbVQCriteria.add(Restrictions.eq("id", versionId));

        // add orders
        if (orderConds != null && orderConds.length > 0) {
            for (int i = 0; i < orderConds.length; i++) {
                Order order = orderConds[i].getOrder();
                if (order != null) {
                    tlgQCriteria.addOrder(order);
                }
            }
        }
        tlQCriteria.setFirstResult(ptl.getFirstResult());
        tlQCriteria.setMaxResults(ptl.getSizePerPage());

        List<TrafficLight> tlList = tlQCriteria.list();
        ptl.setPageResults(tlList);
        return ptl;
    }
}
