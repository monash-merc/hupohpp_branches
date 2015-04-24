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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TPBDataType Domain class
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 2/04/12 3:56 PM
 */
@Entity
@Table(name = "tpb_data_type")
@org.hibernate.annotations.Table(appliesTo = "tpb_data_type",
        indexes = {@Index(name = "idx_data_type", columnNames = {"data_type"}),
                @Index(name = "idx_display_name", columnNames = {"display_name"})
        })
public class TPBDataType extends Domain {
    @Id
    @GeneratedValue(generator = "pk_generator")
    @GenericGenerator(name = "pk_generator", strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "table_name", value = "pk_gen_tab"),
                    @org.hibernate.annotations.Parameter(name = "value_column_name ", value = "pk_next_val"),
                    @org.hibernate.annotations.Parameter(name = "segment_column_name", value = "pk_sequence"),
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "tpb_data_type_id"),
                    @org.hibernate.annotations.Parameter(name = "increment_size  ", value = "20"),
                    @org.hibernate.annotations.Parameter(name = "optimizer ", value = "hilo")
            })
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "display_name")
    private String displayName;

    @Basic
    @Column(name = "data_type")
    private String dataType;

    @Basic
    @Column(name = "tl_level")
    private int tlLevel;

    @ManyToOne(targetEntity = TPBDataType.class)
    @JoinColumn(name = "parent_data_type_id")
    private TPBDataType parentDataType;

    @OneToMany(mappedBy = "parentDataType", fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
    private List<TPBDataType> subDataTypes = new ArrayList<TPBDataType>();

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getTlLevel() {
        return tlLevel;
    }

    public void setTlLevel(int tlLevel) {
        this.tlLevel = tlLevel;
    }

    public TPBDataType getParentDataType() {
        return parentDataType;
    }

    public void setParentDataType(TPBDataType parentDataType) {
        this.parentDataType = parentDataType;
    }

    public List<TPBDataType> getSubDataTypes() {
        return subDataTypes;
    }

    public void setSubDataTypes(List<TPBDataType> subDataTypes) {
        this.subDataTypes = subDataTypes;
    }
}
