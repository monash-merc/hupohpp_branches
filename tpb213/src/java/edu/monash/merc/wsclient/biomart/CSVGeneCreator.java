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

package edu.monash.merc.wsclient.biomart;

import edu.monash.merc.domain.TPBGene;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * @date 19-06-2012 1:18 PM
 */
public class CSVGeneCreator {
    private List<CSVColumn> columns = new ArrayList<CSVColumn>();

    public List<CSVColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<CSVColumn> columns) {
        this.columns = columns;
    }

    public TPBGene createGene() {
        TPBGene tpbGene = new TPBGene();
        for (CSVColumn csvColumn : columns) {
            String columnName = csvColumn.getColumnName();
            String columnValue = csvColumn.getColumnValue();
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.ENSG_ACCESSION)) {
                if (StringUtils.isNotBlank(columnValue) && !StringUtils.equals("\t", columnValue)) {
                    tpbGene.setEnsgAccession(columnValue);
                }
            }
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.DESCRIPTION)) {
                if (StringUtils.isNotBlank(columnValue) && !StringUtils.equals("\t", columnValue)) {
                    tpbGene.setDescription(columnValue);
                }
            }
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.CHROMOSOME)) {
                if (StringUtils.isNotBlank(columnValue) && !StringUtils.equals("\t", columnValue)) {
                    tpbGene.setChromosome(columnValue);
                }
            }
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.START_POSITION)) {
                tpbGene.setStartPosition(Long.valueOf(columnValue).longValue());
            }
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.END_POSITION)) {
                tpbGene.setEndPosition(Long.valueOf(columnValue).longValue());
            }
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.STRAND)) {
                if (StringUtils.isNotBlank(columnValue) && !StringUtils.equals("\t", columnValue)) {
                    tpbGene.setStrand(columnValue);
                }
            }
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.BAND)) {
                if (StringUtils.isNotBlank(columnValue) && !StringUtils.equals("\t", columnValue)) {
                    tpbGene.setBand(columnValue);
                }
            }
            if (StringUtils.equalsIgnoreCase(columnName, GeneConsts.GENE_NAME)) {
                if (StringUtils.isNotBlank(columnValue) && !StringUtils.equals("\t", columnValue)) {
                    tpbGene.setGeneName(columnValue);
                }
            }
        }
        return tpbGene;
    }
}
