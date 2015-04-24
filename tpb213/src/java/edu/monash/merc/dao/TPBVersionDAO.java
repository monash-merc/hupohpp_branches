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
import edu.monash.merc.domain.DSVersion;
import edu.monash.merc.domain.TPBVersion;
import edu.monash.merc.repository.ITPBVersionRep;
import edu.monash.merc.system.version.TLVersionTrack;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 25/05/12
 * Time: 10:34 AM
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Repository
public class TPBVersionDAO extends HibernateGenericDAO<TPBVersion> implements ITPBVersionRep {

    /**
     * {@inheritDoc}
     */
    public TPBVersion getCurrentVersion(ChromType chromType, int trackToken) {
        Criteria qCriteria = this.session().createCriteria(this.persistClass);
        qCriteria.add(Restrictions.eq("chromosome", chromType.chm()).ignoreCase());
        qCriteria.add(Restrictions.eq("trackToken", trackToken));
        qCriteria.addOrder(Order.desc("versionNo")).setMaxResults(1);
        return (TPBVersion) qCriteria.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTPBVersionById(long id) {
        String del_hql = "DELETE FROM " + this.persistClass.getSimpleName() + " AS tpbv WHERE tpbv.id = : id";
        Query query = this.session().createQuery(del_hql);
        query.setLong("id", id);
        query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkTPBVersionAvailable(ChromType chromType, TLVersionTrack tlVersionTrack) {
        Criteria qCriteria = this.session().createCriteria(this.persistClass);
        qCriteria.add(Restrictions.eq("chromosome", chromType.chm()).ignoreCase());

        int trackToken = tlVersionTrack.getTrackToken();
        qCriteria.add(Restrictions.eq("trackToken", trackToken));

        DSVersion nxVersion = tlVersionTrack.getNxDsVersion();
        if (nxVersion != null) {
            Criteria nxCriteria = qCriteria.createCriteria("nxVersion");
            nxCriteria.add(Restrictions.eq("id", nxVersion.getId()));
        }

        DSVersion gpmVersion = tlVersionTrack.getGpmDsVersion();
        if (gpmVersion != null) {
            Criteria gpmCriteria = qCriteria.createCriteria("gpmVersion");
            gpmCriteria.add(Restrictions.eq("id", gpmVersion.getId()));
        }

        DSVersion hpaVersion = tlVersionTrack.getHpaDsVersion();
        if (hpaVersion != null) {
            Criteria hpaCriteria = qCriteria.createCriteria("hpaVersion");
            hpaCriteria.add(Restrictions.eq("id", hpaVersion.getId()));
        }

        DSVersion paVersion = tlVersionTrack.getPaDsVersion();
        if (paVersion != null) {
            Criteria paCriteria = qCriteria.createCriteria("peptideVersion");
            paCriteria.add(Restrictions.eq("id", paVersion.getId()));
        }

        long num = (Long) qCriteria.setProjection(Projections.rowCount()).uniqueResult();
        if (num >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public TPBVersion getCurrentTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken) {
        Criteria qCriteria = this.session().createCriteria(this.persistClass);
        qCriteria.add(Restrictions.eq("chromosome", chromType.chm()).ignoreCase());
        qCriteria.add(Restrictions.eq("trackToken", trackToken));
        qCriteria.addOrder(Order.desc("versionNo")).setMaxResults(1);
        return (TPBVersion) qCriteria.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    public List<TPBVersion> getAllTPBVersionByChromTypeTrackToken(ChromType chromType, int trackToken) {
        Criteria qCriteria = this.session().createCriteria(this.persistClass);
        qCriteria.add(Restrictions.eq("chromosome", chromType.chm()).ignoreCase());
        qCriteria.add(Restrictions.eq("trackToken", trackToken));
        qCriteria.addOrder(Order.asc("versionNo"));
        return qCriteria.list();
    }

}
