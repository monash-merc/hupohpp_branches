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
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 *
 * Date: 2/04/12
 * Time: 5:47 PM
 */
@Entity
@Table(name = "tl_puts_entry")
@org.hibernate.annotations.Table(appliesTo = "tl_puts_entry",
        indexes = {@Index(name = "idx_puts_name", columnNames = {"puts_name"}),
                @Index(name = "idx_puts_value", columnNames = {"puts_value"})
        })
public class TLPUTsEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pk_generator")
    @TableGenerator(table = "pk_gen", name = "pk_generator", pkColumnName = "pk_column_name", valueColumnName = "pk_column_value", pkColumnValue = "tl_puts_entry_pk")
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "puts_name")
    private String putsName;

    @Basic
    @Column(name = "puts_value")
    private String putsValue;

    @ManyToOne(targetEntity = TLPUTs.class)
    @JoinColumn(name = "puts_id", referencedColumnName = "id", nullable = false)
    private TLPUTs tlputs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPutsName() {
        return putsName;
    }

    public void setPutsName(String putsName) {
        this.putsName = putsName;
    }

    public String getPutsValue() {
        return putsValue;
    }

    public void setPutsValue(String putsValue) {
        this.putsValue = putsValue;
    }

    public TLPUTs getTlputs() {
        return tlputs;
    }

    public void setTlputs(TLPUTs tlputs) {
        this.tlputs = tlputs;
    }
}
