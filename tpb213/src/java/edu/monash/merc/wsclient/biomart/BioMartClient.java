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

import au.com.bytecode.opencsv.CSVReader;
import edu.monash.merc.domain.TPBGene;
import edu.monash.merc.exception.WSException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * @date 18-06-2012 5:09 PM
 */
public class BioMartClient {

    private String wsUrl = "http://www.biomart.org/biomart/martservice/result?query=";

    private String species;

    private String chromosome;

    private HttpClient httpclient;

    private HttpGet httpget;

    private boolean configured;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public String getWsUrl() {
        return wsUrl;
    }

    public void setWsUrl(String wsUrl) {
        this.wsUrl = wsUrl;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public boolean configure() {
        if (StringUtils.isNotBlank(wsUrl) && StringUtils.isNotBlank(species)) {
            httpclient = new DefaultHttpClient();
            configured = true;
        } else {
            throw new WSException("the url of webservice and the gene species must be provided");
        }
        return configured;
    }

    public boolean configure(String wsUrl, String species, String chromosome) {
        this.wsUrl = wsUrl;
        this.species = species;
        this.chromosome = chromosome;
        if (StringUtils.isNotBlank(wsUrl) && StringUtils.isNotBlank(species)) {
            httpclient = new DefaultHttpClient();
            configured = true;
        } else {
            throw new WSException("the url of webservice and the gene species must be provided");
        }
        return configured;
    }

    public void release() {
        if (this.httpget != null) {
            this.httpget.releaseConnection();
        }
    }

    private InputStream getGeneWsResponse() {
        HttpGet httpget = null;
        try {
            String query = geneQueryString(this.species, this.chromosome);
            if (logger.isDebugEnabled()) {
                logger.debug("ws query string: " + query);
            }
            String url = wsUrl + URLEncoder.encode(query, "UTF-8");
            httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (logger.isDebugEnabled()) {
                logger.debug(response.getStatusLine());
            }
            if (entity != null) {
                return entity.getContent();
            } else {
                throw new WSException("can't get the webservice response");
            }
        } catch (Exception ex) {
            throw new WSException(ex);
        }
    }

    public List<TPBGene> importGenes() {
        if (!configured) {
            throw new WSException("The configure method must be called first.");
        }

        CSVReader csvReader = null;
        List<TPBGene> tpbGenes = new ArrayList<TPBGene>();
        try {
            InputStream response = getGeneWsResponse();
            csvReader = new CSVReader(new InputStreamReader(response));
            String[] columnsLines = csvReader.readNext();
            if (columnsLines.length != 8) {
                throw new WSException("incorrect webservice response, can't parse the content of the webservice response.");
            }
            String[] columnValuesLines;
            int count = 0;
            while ((columnValuesLines = csvReader.readNext()) != null) {
                count++;
                CSVGeneCreator geneCreator = new CSVGeneCreator();

                for (int i = 0; i < columnsLines.length; i++) {

                    geneCreator.getColumns().add(new CSVColumn(columnsLines[i], columnValuesLines[i]));
                }
                TPBGene tpbGene = geneCreator.createGene();

                if (StringUtils.isNotBlank(tpbGene.getEnsgAccession())) {
                    tpbGenes.add(tpbGene);
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("======== total lines: " + count);
            }
        } catch (Exception ex) {
            throw new WSException(ex);
        } finally {
            try {
                if (csvReader != null) {
                    csvReader.close();
                }
            } catch (Exception x) {
                //ignore whatever caught
            }
        }
        return tpbGenes;
    }

    public String geneQueryString(String species, String chromosome) {

        StringBuilder query = new StringBuilder();
        query.append("<?xml version='1.0' encoding='UTF-8'?>");
        query.append("<!DOCTYPE Query>");
        query.append("<Query  virtualSchemaName = 'default' formatter = 'CSV' header = '1' uniqueRows = '1' count = ''>");
        query.append("<Dataset name = '").append(species).append("' interface = 'default' >");
        if (StringUtils.isNotBlank(chromosome)) {
            query.append("<Filter name = 'chromosome_name' value = '").append(chromosome).append("'/>");
        }
        query.append("<Attribute name = 'ensembl_gene_id' />");
        query.append("<Attribute name = 'description' />");
        query.append("<Attribute name = 'chromosome_name' />");
        query.append("<Attribute name = 'start_position' />");
        query.append("<Attribute name = 'end_position' />");
        query.append("<Attribute name = 'strand' />");
        query.append("<Attribute name = 'band' />");
        query.append("<Attribute name = 'external_gene_id' />");
        query.append("</Dataset>").append("</Query>");
        return query.toString();
    }

    public static void main(String[] args) throws Exception {
        String wsUrl = "http://www.biomart.org/biomart/martservice/result?query=";
        String chromosome = "";
        String species = "hsapiens_gene_ensembl";
        BioMartClient bioMartClient = new BioMartClient();
        bioMartClient.configure(wsUrl, species, chromosome);
        List<TPBGene> tpbGeneList = bioMartClient.importGenes();
        System.out.println(" size : " + tpbGeneList.size());
//        for (TPBGene gene : tpbGeneList) {
//            System.out.println(gene.getEnsgAccession() + " - " + gene.getDescription() + " - " + gene.getChromosome() + " - " + gene.getStartPosition() +
//                    " - " + gene.getEndPosition() + " - " + gene.getStrand() + " - " + gene.getBand() + " - " + gene.getGeneName());
//        }
//        System.out.println(" size : " + tpbGeneList.size());
    }
}
