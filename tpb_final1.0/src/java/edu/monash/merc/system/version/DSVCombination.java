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

package edu.monash.merc.system.version;

import edu.monash.merc.domain.DSVersion;
import org.apache.commons.lang.StringUtils;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * DSVCombination class is an utility class which track the combination of the different datasources
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 23/07/12 11:29 AM
 */
public class DSVCombination {

    private static String ALL_EMPTY_TOKEN = "0000";

    private static String DS_TRACK_TOKEN_AVAILABLE = "1";

    private static String DS_TRACK_TOKEN_UNAVAILABLE = "0";

    public static List<DSVersionTrack> createDSVersionTracks(DSVersion dsVersion) {
        List<DSVersionTrack> dsVersionTracks = new ArrayList<DSVersionTrack>();

        DSVersionTrack dsVersionTrackNone = new DSVersionTrack();
        dsVersionTrackNone.setTrackToken(DS_TRACK_TOKEN_UNAVAILABLE);

        //add dsVersionTrackNone first
        dsVersionTracks.add(dsVersionTrackNone);
        if (dsVersion != null) {
            DSVersionTrack dsVersionTrack = new DSVersionTrack();
            dsVersionTrack.setTrackToken(DS_TRACK_TOKEN_AVAILABLE);
            dsVersionTrack.setDsVersion(dsVersion);
            dsVersionTracks.add(dsVersionTrack);
        }
        return dsVersionTracks;
    }

    public static List<TLVersionTrack> createTLVersionTracks(DSVersion nxDsVersion, DSVersion gpmDsVersion, DSVersion hpaDsVersion, DSVersion paDsVersion) {
        List<DSVersionTrack> nxVersionTracks = createDSVersionTracks(nxDsVersion);
        List<DSVersionTrack> gpmVersionTracks = createDSVersionTracks(gpmDsVersion);
        List<DSVersionTrack> hpaVersionTracks = createDSVersionTracks(hpaDsVersion);
        List<DSVersionTrack> paVersionTracks = createDSVersionTracks(paDsVersion);
        return createTLVersionTracks(nxVersionTracks, gpmVersionTracks, hpaVersionTracks, paVersionTracks);
    }

    public static List<TLVersionTrack> createTLVersionTracks(List<DSVersionTrack> nxVersionTracks, List<DSVersionTrack> gpmVersionTracks, List<DSVersionTrack> hpaVersionTracks, List<DSVersionTrack> paVersionTracks) {
        List<TLVersionTrack> tlVersionTracks = new ArrayList<TLVersionTrack>();
        for (DSVersionTrack nxvtrack : nxVersionTracks) {
            DSVersion nxDsVersion = nxvtrack.getDsVersion();
            String nxTrackToken = nxvtrack.getTrackToken();

            for (DSVersionTrack gpmtrack : gpmVersionTracks) {
                DSVersion gpmDsVersion = gpmtrack.getDsVersion();
                String gpmTrackToken = gpmtrack.getTrackToken();

                for (DSVersionTrack hpatrack : hpaVersionTracks) {
                    DSVersion hpaDsVersion = hpatrack.getDsVersion();
                    String hpaTrackToken = hpatrack.getTrackToken();

                    for (DSVersionTrack patrack : paVersionTracks) {
                        DSVersion paDsVersion = patrack.getDsVersion();
                        String paTrackToken = patrack.getTrackToken();
                        String combinatedToken = nxTrackToken + gpmTrackToken + hpaTrackToken + paTrackToken;
                        int tokenNum = Integer.valueOf(StringUtils.reverse(combinatedToken));
                        if (tokenNum != 0) {
                            //create a new TLVersionTrack
                            TLVersionTrack tlVersionTrack = new TLVersionTrack();

                            //set the DSVersions for this TLVersionTrack
                            tlVersionTrack.setNxDsVersion(nxDsVersion);
                            tlVersionTrack.setGpmDsVersion(gpmDsVersion);
                            tlVersionTrack.setHpaDsVersion(hpaDsVersion);
                            tlVersionTrack.setPaDsVersion(paDsVersion);
                            tlVersionTrack.setTrackToken(tokenNum);
                            //add this TLVersionTrack into the list
                            tlVersionTracks.add(tlVersionTrack);
                        }
                    }
                }
            }
        }
        return tlVersionTracks;
    }


    public static void main(String[] args) {
        //testing
        List<DSVersionTrack> nxVersionTracks = new ArrayList<DSVersionTrack>();
        DSVersionTrack nxDsVersionTrack1 = new DSVersionTrack();
        nxDsVersionTrack1.setTrackToken("1");
        DSVersionTrack nxDsVersionTrack2 = new DSVersionTrack();
        nxDsVersionTrack2.setTrackToken("0");
        nxVersionTracks.add(nxDsVersionTrack1);
        nxVersionTracks.add(nxDsVersionTrack2);

        List<DSVersionTrack> gpmVersionTracks = new ArrayList<DSVersionTrack>();
        DSVersionTrack gpmDsVersionTrack1 = new DSVersionTrack();
        gpmDsVersionTrack1.setTrackToken("1");
        DSVersionTrack gpmDsVersionTrack2 = new DSVersionTrack();
        gpmDsVersionTrack2.setTrackToken("0");
        gpmVersionTracks.add(gpmDsVersionTrack1);
        gpmVersionTracks.add(gpmDsVersionTrack2);

        List<DSVersionTrack> hpaVersionTracks = new ArrayList<DSVersionTrack>();
        DSVersionTrack hpaDsVersionTrack2 = new DSVersionTrack();
        hpaDsVersionTrack2.setTrackToken("1");
        hpaVersionTracks.add(hpaDsVersionTrack2);
        DSVersionTrack hpaDsVersionTrack1 = new DSVersionTrack();
        hpaDsVersionTrack1.setTrackToken("0");
        hpaVersionTracks.add(hpaDsVersionTrack1);


        List<DSVersionTrack> paVersionTracks = new ArrayList<DSVersionTrack>();
        DSVersionTrack paDsVersionTrack2 = new DSVersionTrack();
        paDsVersionTrack2.setTrackToken("1");
        paVersionTracks.add(paDsVersionTrack2);
        DSVersionTrack paDsVersionTrack1 = new DSVersionTrack();
        paDsVersionTrack1.setTrackToken("0");
        paVersionTracks.add(paDsVersionTrack1);

        List<TLVersionTrack> tlVersionTracks = DSVCombination.createTLVersionTracks(nxVersionTracks, gpmVersionTracks, hpaVersionTracks, paVersionTracks);

        System.out.println("=========> total combination: " + tlVersionTracks.size());
        if (tlVersionTracks != null) {
            for (TLVersionTrack tlVTrack : tlVersionTracks) {
                int trackToken = tlVTrack.getTrackToken();

                System.out.println("======> DSVersion combinated token:  " + trackToken);
            }
        }

        System.out.println("==============> Another testing case:");
        DSVersion nxDsVersion = new DSVersion();
        DSVersion gpmDsVersion = new DSVersion();
        DSVersion hpaDsVersion = null;
        DSVersion paDsVersion = null;

        List<TLVersionTrack> tlVersionTracks2 = DSVCombination.createTLVersionTracks(nxDsVersion, gpmDsVersion, hpaDsVersion, paDsVersion);
        System.out.println("=========> total combination: " + tlVersionTracks2.size());
        if (tlVersionTracks2 != null) {
            for (TLVersionTrack tlVTrack : tlVersionTracks2) {
                int trackToken = tlVTrack.getTrackToken();
                System.out.println("======> DSVersion combinated token:  " + trackToken);
            }
        }

    }
}
