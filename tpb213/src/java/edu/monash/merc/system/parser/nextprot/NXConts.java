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

package edu.monash.merc.system.parser.nextprot;

/**
 * @author Simon Yu
 * @email Xiaoming.Yu@monash.edu
 *
 * Date: 31/05/12
 * Time: 3:20 PM
 * @version 1.0
 */
public interface NXConts {

    static String UNKNOWN = "Unknown";

    static String PE_PROTEIN_LEVEL = "protein level";

    static String PE_TRANSCRIPT_LEVEL = "transcript level";

    static String PE_HOMOLOGY = "homology";

    static String PE_PREDICTED = "predicated";

    static String PE_DUBIOUS = "dubious";

    static String PE_OTH_CUR_NX_BASE_URL = "http://www.nextprot.org/db/entry/";

    static String XREF_CA_PROTEOMIC_DATABASES = "Proteomic databases";

    static String XREF_CA_ANTIBODY_DATABASES = "Antibody databases";

    static String XREF_DB_PRIDE = "PRIDE";

    static String XREF_DB_PEPTIDE_ATLAS = "PeptideAtlas";

    static String XREF_DB_HPA = "HPA";

    static String XREF_AC_PREFIX_ENSG = "ENSG";

    static String XREF_AC_ENSG_URL_END_PART = "/normal";

    static String XREF_AC_PREFIX_CAB = "CAB";

    static String XREF_AC_PREFIX_HPA = "HPA";

    static String XREF_HPA_ANTIBODY_DESC = "Human Protein Atlas Antibodies";
}
