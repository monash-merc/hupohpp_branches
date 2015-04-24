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

package edu.monash.merc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * NXEntryBean class which is a DTO class populates the NXEntryBean object.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 3/04/12 2:13 PM
 */
public class NXEntryBean implements Serializable {

    private AccessionBean identifiedAccessionBean;

    private String dbSourceName;

    private GeneBean geneBean;

    private PEEvidenceBean peOthCurEvidenceBean;

    private NXPeMsAntiEntryBean nxPeMsAntiEntryBean;

    private List<DbSourceAcEntryBean> dbSourceAcEntryBeans;

    //TODO: to be removed, just for graping the raw data
    private List<NXAnnEntryBean> nxAnnEntryBeanList;

    public AccessionBean getIdentifiedAccessionBean() {
        return identifiedAccessionBean;
    }

    public void setIdentifiedAccessionBean(AccessionBean identifiedAccessionBean) {
        this.identifiedAccessionBean = identifiedAccessionBean;
    }

    public String getDbSourceName() {
        return dbSourceName;
    }

    public void setDbSourceName(String dbSourceName) {
        this.dbSourceName = dbSourceName;
    }

    public GeneBean getGeneBean() {
        return geneBean;
    }

    public void setGeneBean(GeneBean geneBean) {
        this.geneBean = geneBean;
    }

    public PEEvidenceBean getPeOthCurEvidenceBean() {
        return peOthCurEvidenceBean;
    }

    public void setPeOthCurEvidenceBean(PEEvidenceBean peOthCurEvidenceBean) {
        this.peOthCurEvidenceBean = peOthCurEvidenceBean;
    }

    public NXPeMsAntiEntryBean getNxPeMsAntiEntryBean() {
        return nxPeMsAntiEntryBean;
    }

    public void setNxPeMsAntiEntryBean(NXPeMsAntiEntryBean nxPeMsAntiEntryBean) {
        this.nxPeMsAntiEntryBean = nxPeMsAntiEntryBean;
    }

    public List<DbSourceAcEntryBean> getDbSourceAcEntryBeans() {
        return dbSourceAcEntryBeans;
    }

    public void setDbSourceAcEntryBeans(List<DbSourceAcEntryBean> dbSourceAcEntryBeans) {
        this.dbSourceAcEntryBeans = dbSourceAcEntryBeans;
    }


    //TODO: remove
    public List<NXAnnEntryBean> getNxAnnEntryBeanList() {
        return nxAnnEntryBeanList;
    }

    public void setNxAnnEntryBeanList(List<NXAnnEntryBean> nxAnnEntryBeanList) {
        this.nxAnnEntryBeanList = nxAnnEntryBeanList;
    }
}
