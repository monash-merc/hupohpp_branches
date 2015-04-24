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

package edu.monash.merc.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * @since 1.0
 *        <p/>
 *        Date: 8/03/12
 *        Time: 10:33 AM
 */

@Entity
@Table(name = "accession")
@org.hibernate.annotations.Table(appliesTo = "accession",
        indexes = {@Index(name = "idx_accession", columnNames = {"accession"})
        })
public class Accession extends Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pk_generator")
    @TableGenerator(table = "pk_gen", name = "pk_generator", pkColumnName = "pk_column_name", valueColumnName = "pk_column_value", pkColumnValue = "accession_pk")
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "accession")
    private String accession;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne(targetEntity = AccessionType.class)
    @JoinColumn(name = "ac_type_id", referencedColumnName = "id", nullable = false)
    @Cascade({CascadeType.REFRESH})
    private AccessionType acType;


    @ManyToOne(targetEntity = DBSource.class)
    @JoinColumn(name = "db_source_id", referencedColumnName = "id", nullable = false)
    @Cascade({CascadeType.REFRESH})
    private DBSource dbSource;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccessionType getAcType() {
        return acType;
    }

    public void setAcType(AccessionType acType) {
        this.acType = acType;
    }

    public DBSource getDbSource() {
        return dbSource;
    }

    public void setDbSource(DBSource dbSource) {
        this.dbSource = dbSource;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Accession)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Accession ac = (Accession) obj;
        return new EqualsBuilder().append(this.accession, ac.getAccession()).append(this.description, ac.getDescription()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(this.accession).append(this.description).toHashCode();
    }
}
