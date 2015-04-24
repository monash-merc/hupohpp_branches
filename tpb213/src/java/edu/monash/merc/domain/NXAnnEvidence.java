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

import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * @author Simon Yu
 * @email Xiaoming.Yu@monash.edu
 *
 * Date: 7/05/12
 * Time: 3:39 PM
 * @version 1.0
 */
@Entity
@Table(name = "nx_ann_evidence")
@org.hibernate.annotations.Table(appliesTo = "nx_ann_evidence",
        indexes = {@Index(name = "idx_qualifier_type", columnNames = {"qualifier_type"}),
                @Index(name = "idx_resource_assoc_type", columnNames = {"resource_assoc_type"})
        })
public class NXAnnEvidence extends Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pk_generator")
    @TableGenerator(table = "pk_gen", name = "pk_generator", pkColumnName = "pk_column_name", valueColumnName = "pk_column_value", pkColumnValue = "nx_ann_evidence_pk")
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "is_negative")
    private boolean negative;

    @Basic
    @Column(name = "qualifier_type")
    private String qualifierType;

    @Basic
    @Column(name = "resource_assoc_type")
    private String resourceAssocType;

    @Basic
    @Column(name = "resource_ref")
    private int resourceRef;

    @ManyToOne(targetEntity = NXAnnotation.class)
    @JoinColumn(name = "nx_annotation_id", referencedColumnName = "id", nullable = false)
    private NXAnnotation nxAnnotation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public String getQualifierType() {
        return qualifierType;
    }

    public void setQualifierType(String qualifierType) {
        this.qualifierType = qualifierType;
    }

    public String getResourceAssocType() {
        return resourceAssocType;
    }

    public void setResourceAssocType(String resourceAssocType) {
        this.resourceAssocType = resourceAssocType;
    }

    public int getResourceRef() {
        return resourceRef;
    }

    public void setResourceRef(int resourceRef) {
        this.resourceRef = resourceRef;
    }

    public NXAnnotation getNxAnnotation() {
        return nxAnnotation;
    }

    public void setNxAnnotation(NXAnnotation nxAnnotation) {
        this.nxAnnotation = nxAnnotation;
    }
}
