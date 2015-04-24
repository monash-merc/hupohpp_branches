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

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 8/03/12
 * Time: 10:33 AM
 */

@Entity
@Table(name = "gene")
@org.hibernate.annotations.Table(appliesTo = "gene",
        indexes = {@Index(name = "idx_display_name", columnNames = {"display_name"}),
                @Index(name = "idx_chromosome", columnNames = {"chromosome"}),
                @Index(name = "idx_ensg_accession", columnNames = {"ensg_accession"}),
                @Index(name = "idx_band", columnNames = {"band"}),
                @Index(name = "idx_strand", columnNames = {"strand"})
        })
public class Gene extends VersionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pk_generator")
    @TableGenerator(table = "pk_gen", name = "pk_generator", pkColumnName = "pk_column_name", valueColumnName = "pk_column_value", pkColumnValue = "gene_pk")
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "display_name")
    private String displayName;

    @Basic
    @Column(name = "chromosome")
    private String chromosome;

    @Basic
    @Column(name = "ensg_accession")
    private String ensgAccession;

    @Basic
    @Column(name = "start_position")
    private long startPosition;

    @Basic
    @Column(name = "end_position")
    private long endPosition;

    @Basic
    @Column(name = "band")
    private String band;

    @Basic
    @Column(name = "strand")
    private String strand;

    @Basic
    @Column(name = "description", length = 2000)
    private String description;

    @ManyToMany(targetEntity = Accession.class)
    @JoinTable(name = "gene_accession", joinColumns = {@JoinColumn(name = "gene_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "accession_id", referencedColumnName = "id")}, uniqueConstraints = {@UniqueConstraint(columnNames = {
            "gene_id", "accession_id"})})
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Accession> accessions;

    @ManyToOne(targetEntity = DBSource.class)
    @JoinColumn(name = "dbsource_id", referencedColumnName = "id", nullable = false)
    @Cascade({CascadeType.REFRESH})
    private DBSource dbSource;

    @ManyToMany(targetEntity = TLGene.class, mappedBy = "genes")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<TLGene> tlGenes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getEnsgAccession() {
        return ensgAccession;
    }

    public void setEnsgAccession(String ensgAccession) {
        this.ensgAccession = ensgAccession;
    }

    public long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(long startPosition) {
        this.startPosition = startPosition;
    }

    public long getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(long endPosition) {
        this.endPosition = endPosition;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Accession> getAccessions() {
        return accessions;
    }

    public void setAccessions(List<Accession> accessions) {
        this.accessions = accessions;
    }

    public DBSource getDbSource() {
        return dbSource;
    }

    public void setDbSource(DBSource dbSource) {
        this.dbSource = dbSource;
    }

    public List<TLGene> getTlGenes() {
        return tlGenes;
    }

    public void setTlGenes(List<TLGene> tlGenes) {
        this.tlGenes = tlGenes;
    }
}
