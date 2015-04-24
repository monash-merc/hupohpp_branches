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

import edu.monash.merc.common.name.DataType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * TrafficLight Domain class
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 8/03/12 1:05 PM
 */
@Entity
@Table(name = "traffic_light")
public class TrafficLight extends Domain {

    @Id
    @GeneratedValue(generator = "pk_generator")
    @GenericGenerator(name = "pk_generator", strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "table_name", value = "pk_gen_tab"),
                    @org.hibernate.annotations.Parameter(name = "value_column_name ", value = "pk_next_val"),
                    @org.hibernate.annotations.Parameter(name = "segment_column_name", value = "pk_sequence"),
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "traffic_light_id"),
                    @org.hibernate.annotations.Parameter(name = "increment_size  ", value = "20"),
                    @org.hibernate.annotations.Parameter(name = "optimizer ", value = "hilo")
            })
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne(targetEntity = TLGene.class)
    @JoinColumn(name = "tl_gene_id", referencedColumnName = "id")
    private TLGene tlGene;

    @ManyToOne(targetEntity = TPBVersion.class)
    @JoinColumn(name = "version_id", referencedColumnName = "id", nullable = false)
    private TPBVersion tpbVersion;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_color_id", referencedColumnName = "id")
    private TLColor peTlColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_ms_color_id", referencedColumnName = "id")
    private TLColor peMSTlColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_anti_color_id", referencedColumnName = "id")
    private TLColor tlPEANTIColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_oth_color_id", referencedColumnName = "id")
    private TLColor tlPEOTHColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_ms_ann_color_id", referencedColumnName = "id")
    private TLColor tlPEMSANNColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_ms_prob_color_id", referencedColumnName = "id")
    private TLColor tlPEMSPROBColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_ms_sam_color_id", referencedColumnName = "id")
    private TLColor tlPEMSSAMColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_anti_ann_color_id", referencedColumnName = "id")
    private TLColor tlPEANTIANNColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_anti_ihc_color_id", referencedColumnName = "id")
    private TLColor tlPEANTIIHCColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_anti_ihc_norm_color_id", referencedColumnName = "id")
    private TLColor tlPEANTIIHCNORMColor;

    @ManyToOne(targetEntity = TLColor.class)
    @JoinColumn(name = "pe_oth_cur_color_id", referencedColumnName = "id")
    private TLColor tlPEOTHCURColor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TLGene getTlGene() {
        return tlGene;
    }

    public void setTlGene(TLGene tlGene) {
        this.tlGene = tlGene;
    }

    public TPBVersion getTpbVersion() {
        return tpbVersion;
    }

    public void setTpbVersion(TPBVersion tpbVersion) {
        this.tpbVersion = tpbVersion;
    }

    public TLColor getPeTlColor() {
        return peTlColor;
    }

    public void setPeTlColor(TLColor peTlColor) {
        this.peTlColor = peTlColor;
    }

    public TLColor getPeMSTlColor() {
        return peMSTlColor;
    }

    public void setPeMSTlColor(TLColor peMSTlColor) {
        this.peMSTlColor = peMSTlColor;
    }

    public TLColor getTlPEANTIColor() {
        return tlPEANTIColor;
    }

    public void setTlPEANTIColor(TLColor tlPEANTIColor) {
        this.tlPEANTIColor = tlPEANTIColor;
    }

    public TLColor getTlPEOTHColor() {
        return tlPEOTHColor;
    }

    public void setTlPEOTHColor(TLColor tlPEOTHColor) {
        this.tlPEOTHColor = tlPEOTHColor;
    }

    public TLColor getTlPEMSANNColor() {
        return tlPEMSANNColor;
    }

    public void setTlPEMSANNColor(TLColor tlPEMSANNColor) {
        this.tlPEMSANNColor = tlPEMSANNColor;
    }

    public TLColor getTlPEMSPROBColor() {
        return tlPEMSPROBColor;
    }

    public void setTlPEMSPROBColor(TLColor tlPEMSPROBColor) {
        this.tlPEMSPROBColor = tlPEMSPROBColor;
    }

    public TLColor getTlPEMSSAMColor() {
        return tlPEMSSAMColor;
    }

    public void setTlPEMSSAMColor(TLColor tlPEMSSAMColor) {
        this.tlPEMSSAMColor = tlPEMSSAMColor;
    }

    public TLColor getTlPEANTIANNColor() {
        return tlPEANTIANNColor;
    }

    public void setTlPEANTIANNColor(TLColor tlPEANTIANNColor) {
        this.tlPEANTIANNColor = tlPEANTIANNColor;
    }

    public TLColor getTlPEANTIIHCColor() {
        return tlPEANTIIHCColor;
    }

    public void setTlPEANTIIHCColor(TLColor tlPEANTIIHCColor) {
        this.tlPEANTIIHCColor = tlPEANTIIHCColor;
    }

    public TLColor getTlPEANTIIHCNORMColor() {
        return tlPEANTIIHCNORMColor;
    }

    public void setTlPEANTIIHCNORMColor(TLColor tlPEANTIIHCNORMColor) {
        this.tlPEANTIIHCNORMColor = tlPEANTIIHCNORMColor;
    }

    public TLColor getTlPEOTHCURColor() {
        return tlPEOTHCURColor;
    }

    public void setTlPEOTHCURColor(TLColor tlPEOTHCURColor) {
        this.tlPEOTHCURColor = tlPEOTHCURColor;
    }

    public TLColor getTLColorByDataType(DataType dataType) {
        if (dataType.equals(DataType.PE)) {
            return this.peTlColor;
        } else if (dataType.equals(DataType.PE_MS)) {
            return this.peMSTlColor;
        } else if (dataType.equals(DataType.PE_MS_PROB)) {
            return this.tlPEMSPROBColor;
        } else if (dataType.equals(DataType.PE_MS_SAM)) {
            return this.tlPEMSSAMColor;
        } else if (dataType.equals(DataType.PE_MS_ANN)) {
            return this.tlPEMSANNColor;
        } else if (dataType.equals(DataType.PE_ANTI)) {
            return this.tlPEANTIColor;
        } else if (dataType.equals(DataType.PE_ANTI_ANN)) {
            return this.tlPEANTIANNColor;
        } else if (dataType.equals(DataType.PE_ANTI_IHC)) {
            return this.tlPEANTIIHCColor;
        } else if (dataType.equals(DataType.PE_ANTI_IHC_NORM)) {
            return this.tlPEANTIIHCNORMColor;
        } else if (dataType.equals(DataType.PE_OTH)) {
            return this.tlPEOTHColor;
        } else if (dataType.equals(DataType.PE_OTH_CUR)) {
            return this.tlPEOTHCURColor;
        } else {
            return null;
        }
    }
}
