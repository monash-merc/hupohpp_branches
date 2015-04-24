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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.List;

/**
 * @author Simon Yu
 * @email Xiaoming.Yu@monash.edu
 *
 * Date: 7/05/12
 * Time: 3:14 PM
 * @version 1.0
 */

@Entity
@Table(name = "nx_annotation")
@org.hibernate.annotations.Table(appliesTo = "nx_annotation",
        indexes = {@Index(name = "idx_category", columnNames = {"category"}),
                @Index(name = "idx_qualifier", columnNames = {"quality_qualifier"}),
                @Index(name = "idx_cvterm_ac", columnNames = {"cvterm_accession"}),
                @Index(name = "idx_cv_name", columnNames = {"cv_name"}),
                @Index(name = "idx_description", columnNames = {"description"})
        })
public class NXAnnotation extends Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pk_generator")
    @TableGenerator(table = "pk_gen", name = "pk_generator", pkColumnName = "pk_column_name", valueColumnName = "pk_column_value", pkColumnValue = "nx_annotation_pk")
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "category")
    private String category;

    @Basic
    @Column(name = "quality_qualifier")
    private String qualityQualifier;

    @Basic
    @Column(name = "unique_name")
    private String uniqueName;

    @Basic
    @Column(name = "cvterm_accession")
    private String cvTermAccession;

    @Basic
    @Column(name = "cv_name")
    private String cvName;

    @Basic
    @Column(name = "description", length = 4000)
    private String description;

    @ManyToOne(targetEntity = Accession.class)
    @JoinColumn(name = "identified_ac_id", referencedColumnName = "id", nullable = false)
    private Accession identifiedAccession;

    @OneToMany(mappedBy = "nxAnnotation")
    @Cascade(CascadeType.ALL)
    private List<NXAnnEvidence> nxAnnEvidences;

    @OneToMany(mappedBy = "nxAnnotation")
    @Cascade(CascadeType.ALL)
    private List<NXIsoFormAnn> nxIsoFormAnns;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQualityQualifier() {
        return qualityQualifier;
    }

    public void setQualityQualifier(String qualityQualifier) {
        this.qualityQualifier = qualityQualifier;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getCvTermAccession() {
        return cvTermAccession;
    }

    public void setCvTermAccession(String cvTermAccession) {
        this.cvTermAccession = cvTermAccession;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Accession getIdentifiedAccession() {
        return identifiedAccession;
    }

    public void setIdentifiedAccession(Accession identifiedAccession) {
        this.identifiedAccession = identifiedAccession;
    }

    public List<NXAnnEvidence> getNxAnnEvidences() {
        return nxAnnEvidences;
    }

    public void setNxAnnEvidences(List<NXAnnEvidence> nxAnnEvidences) {
        this.nxAnnEvidences = nxAnnEvidences;
    }

    public List<NXIsoFormAnn> getNxIsoFormAnns() {
        return nxIsoFormAnns;
    }

    public void setNxIsoFormAnns(List<NXIsoFormAnn> nxIsoFormAnns) {
        this.nxIsoFormAnns = nxIsoFormAnns;
    }
}
