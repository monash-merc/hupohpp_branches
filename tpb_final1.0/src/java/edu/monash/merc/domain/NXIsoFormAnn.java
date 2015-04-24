/*
 * Copyright (c) 2011-2013, Monash e-Research Centre
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
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
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
 *
 * You should have received a copy of the GNU Affero General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package edu.monash.merc.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NXIsoFormAnn Domain class
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 8/05/12 3:13 PM
 */
@Entity
@Table(name = "nx_isoform_ann")
@org.hibernate.annotations.Table(appliesTo = "nx_isoform_ann",
        indexes = {@Index(name = "idx_isoFormRef", columnNames = {"iso_form_ref"}),
                @Index(name = "idx_firstStatus", columnNames = {"first_status"}),
                @Index(name = "idx_lastStatus", columnNames = {"last_status"})
        })
public class NXIsoFormAnn extends Domain {
    @Id
    @GeneratedValue(generator = "pk_generator")
    @GenericGenerator(name = "pk_generator", strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "table_name", value = "pk_gen_tab"),
                    @org.hibernate.annotations.Parameter(name = "value_column_name ", value = "pk_next_val"),
                    @org.hibernate.annotations.Parameter(name = "segment_column_name", value = "pk_sequence"),
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "nx_isoform_ann_id"),
                    @org.hibernate.annotations.Parameter(name = "increment_size  ", value = "20"),
                    @org.hibernate.annotations.Parameter(name = "optimizer ", value = "hilo")
            })
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "iso_form_ref")
    private String isoFormRef;

    @Basic
    @Column(name = "first")
    private int firstPosition;

    @Basic
    @Column(name = "first_status")
    private String firstStatus;

    @Basic
    @Column(name = "last")
    private int lastPosition;

    @Basic
    @Column(name = "last_status")
    private String lastStatus;

    @ManyToOne(targetEntity = NXAnnotation.class)
    @JoinColumn(name = "nx_annotation_id", referencedColumnName = "id", nullable = false)
    private NXAnnotation nxAnnotation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsoFormRef() {
        return isoFormRef;
    }

    public void setIsoFormRef(String isoFormRef) {
        this.isoFormRef = isoFormRef;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }

    public String getFirstStatus() {
        return firstStatus;
    }

    public void setFirstStatus(String firstStatus) {
        this.firstStatus = firstStatus;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    public NXAnnotation getNxAnnotation() {
        return nxAnnotation;
    }

    public void setNxAnnotation(NXAnnotation nxAnnotation) {
        this.nxAnnotation = nxAnnotation;
    }
}
