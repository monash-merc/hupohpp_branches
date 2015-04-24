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

package edu.monash.merc.common.name;

import org.apache.commons.lang.StringUtils;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 23/05/12
 * Time: 4:54 PM
 */
public enum DbAcType {
    NextProt("NextProt"), GPM("GPM"), PA("PA"), HPA("HPA"), Ensembl("Ensembl"), Gene("Gene"), Protein("Protein"), SwissProt("SwissProt"), Unknown("Unknown");

    private String name;

    DbAcType(String name) {
        this.name = name;
    }

    public String type() {
        return this.name;
    }

    public static DbAcType fromType(String type) {
        if (StringUtils.equalsIgnoreCase(type, NextProt.type())) {
            return NextProt;
        }
        if (StringUtils.equalsIgnoreCase(type, GPM.type())) {
            return GPM;
        }
        if (StringUtils.equalsIgnoreCase(type, HPA.type())) {
            return HPA;
        }
        if (StringUtils.equalsIgnoreCase(type, PA.type())) {
            return PA;
        }
        if (StringUtils.equalsIgnoreCase(type, Ensembl.type())) {
            return Ensembl;
        }
        if (StringUtils.equalsIgnoreCase(type, Gene.type())) {
            return Gene;
        }
        if (StringUtils.equalsIgnoreCase(type, Protein.type())) {
            return Protein;
        }
        if (StringUtils.equalsIgnoreCase(type, SwissProt.type())) {
            return SwissProt;
        }
        return Unknown;
    }

    public String toString() {
        switch (this) {
            case NextProt:
                return "NextProt";
            case GPM:
                return "GPM";
            case PA:
                return "PA";
            case HPA:
                return "HPA";
            case Ensembl:
                return "Ensembl";
            case Gene:
                return "Gene";
            case Protein:
                return "Protein";
            case SwissProt:
                return "SwissProt";
            default:
                return "Unknown";
        }
    }


}
