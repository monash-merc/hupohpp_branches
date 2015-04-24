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

import edu.monash.merc.common.name.DataType;
import edu.monash.merc.domain.PEEvidence;
import edu.monash.merc.repository.IPEEvidenceRep;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
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
 * Date: 23/04/12
 * Time: 12:54 PM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Repository
public class PEEvidenceDAO extends HibernateGenericDAO<PEEvidence> implements IPEEvidenceRep {

    public void deletePEEvidenceById(long id) {
        String del_hql = "DELETE FROM " + this.persistClass.getSimpleName() + " AS ev WHERE ev.id = : id";
        Query query = this.session().createQuery(del_hql);
        query.setLong("id", id);
        query.executeUpdate();
    }

    public PEEvidence getPESummaryByGeneAndType(long geneId, DataType dataType) {
        Criteria peCriteria = this.session().createCriteria(this.persistClass);
        Criteria gCriteria = peCriteria.createCriteria("gene");
        Criteria dtypeCriteria = peCriteria.createCriteria("tpbDataType");

        gCriteria.add(Restrictions.eq("id", geneId));

        Disjunction dtypeOr = Restrictions.disjunction();

        //PE
        if (dataType.equals(DataType.PE)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_PROB.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_SAM.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH_CUR.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_MS)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_PROB.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_SAM.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_MS_ANN)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_MS_ANN.type()));

        } else if (dataType.equals(DataType.PE_MS_PROB)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_MS_PROB.type()));

        } else if (dataType.equals(DataType.PE_MS_SAM)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_MS_SAM.type()));

        } else if (dataType.equals(DataType.PE_ANTI)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_ANTI_ANN)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_ANTI_ANN.type()));

        } else if (dataType.equals(DataType.PE_ANTI_IHC)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_ANTI_IHC_NORM)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_OTH)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH_CUR.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_OTH_CUR)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_OTH_CUR.type()));

        } else {
            return null;
        }
        //TODO: Other DataType to be added
        peCriteria.addOrder(Order.desc("colorLevel"));
        peCriteria.setMaxResults(1);
        return (PEEvidence) peCriteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<PEEvidence> getEvidencesByGeneAndType(long geneId, DataType dataType) {
        Criteria peCriteria = this.session().createCriteria(this.persistClass);
        Criteria gCriteria = peCriteria.createCriteria("gene");
        Criteria dtypeCriteria = peCriteria.createCriteria("tpbDataType");
        dtypeCriteria.addOrder(Order.asc("displayName"));

        gCriteria.add(Restrictions.eq("id", geneId));

        Disjunction dtypeOr = Restrictions.disjunction();

        //PE
        if (dataType.equals(DataType.PE)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_PROB.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_SAM.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH_CUR.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_MS)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_PROB.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_MS_SAM.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_MS_ANN)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_MS_ANN.type()));

        } else if (dataType.equals(DataType.PE_MS_PROB)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_MS_PROB.type()));

        } else if (dataType.equals(DataType.PE_MS_SAM)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_MS_SAM.type()));

        } else if (dataType.equals(DataType.PE_ANTI)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_ANN.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_ANTI_ANN)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_ANTI_ANN.type()));

        } else if (dataType.equals(DataType.PE_ANTI_IHC)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC.type()));
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));

        } else if (dataType.equals(DataType.PE_ANTI_IHC_NORM)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_ANTI_IHC_NORM.type()));

        } else if (dataType.equals(DataType.PE_OTH)) {
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH.type()));
            dtypeOr.add(Restrictions.eq("dataType", DataType.PE_OTH_CUR.type()));
            dtypeCriteria.add(dtypeOr);

        } else if (dataType.equals(DataType.PE_OTH_CUR)) {
            dtypeCriteria.add(Restrictions.eq("dataType", DataType.PE_OTH_CUR.type()));

        } else {
            return null;
        }
        peCriteria.addOrder(Order.desc("colorLevel"));
        return peCriteria.list();
    }
}
