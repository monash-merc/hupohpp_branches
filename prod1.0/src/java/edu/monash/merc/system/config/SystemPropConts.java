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

package edu.monash.merc.system.config;

/**
 * SystemPropConts interface defines the configuration constants of the system proeperties.
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 28/02/12 11:49 AM
 */
public interface SystemPropConts {

    static String APPLICATION_NAME = "application.name";

    static String SYSTEM_SERVICE_EMAIL = "system.service.email";

    static String CHROMOSOME_IMPORT_TYPE = "chromosome.import.type";

    static String DATASOURCE_NEXTPORT_IMPORT_ENABLE = "datasource.nextprot.import.enable";

    static String FTP_NX_SERVER_NAME = "ftp.nx.server.name";

    static String FTP_NX_USER_NAME = "ftp.nx.user.name";

    static String FTP_NX_USER_PASSWORD = "ftp.nx.user.password";

    static String FTP_NX_CHROMOSOME_REL_DIR = "ftp.nx.chromosome.release.directory.name";

    static String DATASOURCE_ENSEMBL_IMPORT_ENABLE = "datasource.ensembl.import.enable";

    static String BIOMART_RESTFUL_WS_URL = "biomart.restful.ws.url";

    static String DATASOURCE_GPM_IMPORT_ENABLE = "datasource.gpm.import.enable";

    static String DATASOURCE_HPA_IMPORT_ENABLE = "datasource.hpa.import.enable";

    static String HPA_DATA_RELEASE_LOCATION = "hpa.data.release.location";

    static String GPM_RSS_FEED_URL = "gpm.rss.feed.url";

    static String TRAFFIC_LIGHT_COMBINATION_DEFAILT = "traffic.light.combination.default";

    static String RDA_RIFCS_ENABLED = "rda.rifcs.enabled";
}
