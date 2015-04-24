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

import edu.monash.merc.domain.TLColor;
import edu.monash.merc.repository.ITLColorRep;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 *
 * Date: 23/04/12
 * Time: 11:21 AM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Repository
public class TLColorDAO extends HibernateGenericDAO<TLColor> implements ITLColorRep {

    public void deleteTLColorById(long id) {
        String del_hql = "DELETE FROM " + this.persistClass.getSimpleName() + " AS color WHERE color.id = : id";
        Query query = this.session().createQuery(del_hql);
        query.setLong("id", id);
        query.executeUpdate();
    }

    public TLColor getTLColorByColor(String color) {
        Criteria criteria = this.session().createCriteria(this.persistClass);
        criteria.add(Restrictions.eq("tlColor", color).ignoreCase());
        return (TLColor) criteria.uniqueResult();
    }

    public TLColor getTLColorByColorLevel(int colorLevel) {
        Criteria criteria = this.session().createCriteria(this.persistClass);
        criteria.add(Restrictions.eq("colorLevel", colorLevel));
        return (TLColor) criteria.uniqueResult();
    }
}
