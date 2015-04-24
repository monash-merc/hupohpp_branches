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


$("a.tl_type_level_1").live('click', function (event) {
    event.preventDefault();
    var hrefLink = $(this);
    var tlTypeLevel = hrefLink.attr("id");
    var viewUrl = hrefLink.attr('href');
    removeHighLightColor();
    var tlColorTd = hrefLink.parent();
    highLightTLColor(tlColorTd);
    TLEvSummary(viewUrl);
})

$("a.tl_type_level_2").live('click', function (event) {
    event.preventDefault();
    var hrefLink = $(this);
    var tlTypeLevel = hrefLink.attr("id");
    var viewUrl = hrefLink.attr('href');
    removeHighLightColor();
    var tlColorTd = hrefLink.parent();
    highLightTLColor(tlColorTd);
    TLEvSummary(viewUrl);
})

$("a.tl_type_level_3").live('click', function (event) {
    event.preventDefault();
    var hrefLink = $(this);
    var tlTypeLevel = hrefLink.attr("id");
    var viewUrl = hrefLink.attr('href');
    removeHighLightColor();
    var tlColorTd = hrefLink.parent();
    highLightTLColor(tlColorTd);
    TLEvSummary(viewUrl);
})

$("a.tl_type_level_4").live('click', function (event) {
    event.preventDefault();
    var hrefLink = $(this);
    var tlTypeLevel = hrefLink.attr("id");
    var viewUrl = hrefLink.attr('href');
    removeHighLightColor();
    var tlColorTd = hrefLink.parent();
    highLightTLColor(tlColorTd);
    TLEvSummary(viewUrl);
})


//highlight the traffic light color if it's clicked
function highLightTLColor(tlColorTd) {
    tlColorTd.attr("style", "border:solid 1px #0033ff;")
    tlColorTd.addClass('tl_hl_color');
}

//remove the previous clicked highlight color
function removeHighLightColor() {
    var highlightColorTd = $("td.tl_hl_color");
    if (highlightColorTd != null && highlightColorTd != 'undefine') {
        highlightColorTd.attr('style', '');
        highlightColorTd.attr('class', '');
    }
}

function TLEvSummary(linkUrl) {
    $.ajax({
            type:"get",
            url:linkUrl,
            cache:false,
            contentType:'application/json; charset=utf-8',
            dataType:'json',
            success:function (respdata) {
                var ok = respdata.success;
                if (ok == 'true') {
                    TLEvSummarySuccess(respdata);
                } else {
                    showErrorDialog(respdata.message);
                }
            },
            error:function (request, exception) {
                var errormsg = getErrorMsg(request, exception);
                showErrorDialog(errormsg);
            }
        }
    )
}

function TLEvSummarySuccess(respData) {
    //get the Traffic Light Evidence Summary Header
    var tlEvSummary = respData.tlEvidenceSummary;
    var summaryHeader = tlEvSummary.tlEvidenceSummaryHeader;
    //get the type name
    var type = summaryHeader.tpbDataType;
    if (type != null) {
        type = type.toLowerCase();
    }

    var tlEvSummaryDiv = $('.tl_ev_summary_div');
    tlEvSummaryDiv.html();
    tlEvSummaryDiv.attr('id', (type + '_sum'));
    tlEvSummaryDiv.show();
    //create data type gene's evidence summary panel
    var tlSumPanel = "<div class='evidnece_header'>" +
        "<div class='tl_evidence_title'>" + summaryHeader.geneName + " - " + summaryHeader.typeDisplayName + " (" + summaryHeader.typeShortName + ")</div>" +
        "<table class='tl_sum_tab'>" +
        "<thead>" +
        "<tr>" +
        "<th width='10%'>DataSource</th>" +
        "<th width='10%'>Gene Name</th>" +
        "<th width='15%'>Accession</th>" +
        "<th width='18%'>Type</th>" +
        "<th width='5%'>Level</th>" +
        "<th width='37%'>Evidence</th>" +
        "<th width='5%'>Details</th>" +
        "</tr>" +
        "</thead>" +
        "</table>" +
        "</div>";
    var tlEvidences = tlEvSummary.tlEvidences;
    if (tlEvidences != null) {
        $.each(tlEvidences, function (key, tls) {
            var isLastLevel = tls.lastLevel;

            var tlColorLevel = tls.colorLevel;
            var colorClass = 'tl1_color';
            if (tlColorLevel == '4') {
                colorClass = 'tl4_color';
            }
            if (tlColorLevel == '3') {
                colorClass = 'tl3_color';
            }
            if (tlColorLevel == '2') {
                colorClass = 'tl2_color';
            }
            if (tlColorLevel == '1') {
                colorClass = 'tl1_color';
            }
            var dbSrcId = tls.dbSource;
            var tpbType = tls.tpbDataType;
            var identifiedId = dbSrcId + "_" + tpbType;

            var tlLevel = tls.tlLevel;
            var typeLevel = "tl_l" + tlLevel;
            var dentClss = 'tlevel_1';

            if (tlLevel == 2) {
                dentClss = 'tlevel_2';
            }
            if (tlLevel == 3) {
                dentClss = 'tlevel_3';
            }
            if (tlLevel == 4) {
                dentClss = 'tlevel_4';
            }

            tlSumPanel += "<div class='ev_separator'></div><div class='" + identifiedId + "' id= '" + typeLevel + "' >" +
                "<table class='tl_sum_tab'>" +
                "<tbody>" +
                "<tr class='tr_normal' onMouseOver=\"this.className='tr_highlight'\" onMouseOut=\"this.className='tr_normal'\">" +
                "<td width='10%'>" + tls.dbSource + "</td>" +
                "<td width='10%'>" + tls.geneName + "</td>" +
                "<td width='15%'>" + tls.ensemblId + "</td>" +
                "<td width='18%'><div class='" + dentClss + "'>" + tls.typeShortName + "</div></td>" +
                "<td width='5%'>" +
                "<div class='" + colorClass + "'>&nbsp;</div>" +
                "</td>" +
                "<td width='37%'>Summary</td>" +
                "<td width='5%'>" +
                "<div class='tl_link'>";
            if (isLastLevel) {
                tlSumPanel += "<a class='view_evidence' id='" + identifiedId + "' href='viewevidences.jspx?tpbDataType=" + tls.tpbDataType + "&geneId=" + tls.geneId + "' >";
            } else {
                tlSumPanel += "<a class='view_src_gene_sum'  id='" + identifiedId + "' href='srcgsum.jspx?dbSource=" + tls.dbSource + "&tpbDataType=" + tls.tpbDataType + "&geneId=" + tls.geneId + "'>";
            }
            tlSumPanel += "<span class='span_expand'>&nbsp;</span></a></div>" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "<div class='" + identifiedId + "_All_Subs'></div>" +
                "</div>";
        });
    } else {
        tlSumPanel += "<div class='none_evidence'>The evidence is unavailable</div>"
    }
    tlEvSummaryDiv.html(tlSumPanel);
}

function showErrorDialog(errorMsg) {
    var htmlMessage = "<div class='resp_error_msg'>" + errorMsg + "</div> ";
    $('.tl_ev_summary_div').mPopup({
        title:"Error",
        content:htmlMessage,
        close_on_body_click:true,
        width:400
    });
    return false;
}

function getErrorMsg(request, exception) {
    var errormsg = '';
    if (request.status === 0) {
        errormsg = 'Failed to connect to the server';
    } else if (request.status == 404) {
        errormsg = 'The requested page not found';
    } else if (request.status == 500) {
        errormsg = 'The internal server error';
    } else if (exception === 'parsererror') {
        errormsg = 'The requested JSON parse failed';
    } else if (exception === 'timeout') {
        errormsg = 'Connection time out';
    } else if (exception === 'abort') {
        errormsg = 'The request aborted';
    } else {
        errormsg = 'Failed to call the service. ' + request.responseText;
    }
    return errormsg;
}


//view type evidence summary by source
$("a.view_src_gene_sum").live('click', function (event) {
    event.preventDefault();
    var hrefLink = $(this);
    var linkUrl = hrefLink.attr('href');
    var linkId = hrefLink.attr('id');
    if (requestForCollapse(hrefLink)) {
        collapseSubs(linkId);
        //reset it as expand
        changeLinkAsExpand(hrefLink);
    } else {
        srcGeneEvSummary(linkUrl, linkId);
        //change link as collapse status
        changeLinkAsCollapse(hrefLink);
    }
})

function collapseSubs(parentId) {
    var tobeRemovedDiv = $('div.' + parentId + '_All_Subs');
    if (tobeRemovedDiv != null) {
        tobeRemovedDiv.html('');
    }
}

function requestForCollapse(hrefLink) {
    var spanEL = hrefLink.find('span');
    var clss = spanEL.attr('class');
    if (clss == 'span_collapse') {
        return true;
    } else {
        return false;
    }
}

function changeLinkAsCollapse(hrefLink) {
    //change link as collapse status
    var spanEL = hrefLink.find('span');
    spanEL.attr('class', 'span_collapse');
}

function changeLinkAsExpand(hrefLink) {
    var spanEL = hrefLink.find('span');
    spanEL.attr('class', 'span_expand');
}

function srcGeneEvSummary(viewUrl, linkId) {
    $.ajax({
            type:"get",
            url:viewUrl,
            cache:false,
            contentType:'application/json; charset=utf-8',
            dataType:'json',
            success:function (respdata) {
                var ok = respdata.success;
                if (ok == 'true') {
                    srcGeneEvSummarySuccess(respdata, linkId);
                } else {
                    showErrorDialog(respdata.message);
                }
            },
            error:function (request, exception) {
                var errormsg = getErrorMsg(request, exception);
                showErrorDialog(errormsg);
            }
        }
    )
}

function srcGeneEvSummarySuccess(respData, parentDivId) {
    var parentDiv = $('div.' + parentDivId + '_All_Subs');
    var srcGeneEvSummary = respData.tlEvidenceSummary;

    var srcGeneEvidences = srcGeneEvSummary.tlEvidences;
    var srcGeneSumPanel = "";
    if (srcGeneEvidences != null) {
        $.each(srcGeneEvidences, function (key, tls) {
            var tlColorLevel = tls.colorLevel;
            var colorClass = 'tl1_color';
            if (tlColorLevel == '4') {
                colorClass = 'tl4_color';
            }
            if (tlColorLevel == '3') {
                colorClass = 'tl3_color';
            }
            if (tlColorLevel == '2') {
                colorClass = 'tl2_color';
            }
            if (tlColorLevel == '1') {
                colorClass = 'tl1_color';
            }

            var isLastLevel = tls.lastLevel;
            var dbs = tls.dbSource;
            var dataType = tls.tpbDataType;
            var identifiedId = dbs + "_" + dataType;

            var tlLevel = tls.tlLevel;
            var dentClss = 'tlevel_1';

            if (tlLevel == 2) {
                dentClss = 'tlevel_2';
            }
            if (tlLevel == 3) {
                dentClss = 'tlevel_3';
            }
            if (tlLevel == 4) {
                dentClss = 'tlevel_4';
            }

            var exitSrcGeneSumPanel = $('div.' + identifiedId);
            if (exitSrcGeneSumPanel != null) {
                exitSrcGeneSumPanel.remove();
            }
            //srcGeneSumPanel += "<div class='blank_separator'></div>";
            srcGeneSumPanel += "<div class='" + identifiedId + "' >" +
                "<table class='tl_sum_tab'>" +
                "<tbody>" +
                "<tr class='tr_normal' onMouseOver=\"this.className='tr_highlight'\" onMouseOut=\"this.className='tr_normal'\">" +
                "<td width='10%'>" + tls.dbSource + "</td>" +
                "<td width='10%'>" + tls.geneName + "</td>" +
                "<td width='15%'>" + tls.ensemblId + "</td>" +
                "<td width='18%'><div class='" + dentClss + "'>" + tls.typeShortName + "</div></td>" +
                "<td width='5%'>" +
                "<div class='" + colorClass + "'>&nbsp;</div>" +
                "</td>" +
                "<td width='37%'>Summary</td>" +
                "<td width='5%'>" +
                "<div class='tl_link'>";
            if (isLastLevel) {
                srcGeneSumPanel += "<a class='view_evidence' id='" + identifiedId + "' href='viewevidences.jspx?tpbDataType=" + tls.tpbDataType + "&geneId=" + tls.geneId + "' >";

            } else {
                srcGeneSumPanel += "<a class='view_src_gene_sum'  id='" + identifiedId + "' href='srcgsum.jspx?dbSource=" + tls.dbSource + "&tpbDataType=" + tls.tpbDataType + "&geneId=" + tls.geneId + "'>";

            }
            srcGeneSumPanel += "<span class='span_expand'>&nbsp;</span></a></div>" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "<div class='" + identifiedId + "_All_Subs'></div>" +
                "</div>";
        });

    } else {
        srcGeneSumPanel += "<div class='none_evidence'>The evidence is unavailable</div>"
    }
    parentDiv.html(srcGeneSumPanel);
}


$("a.view_evidence").live('click', function (event) {
    event.preventDefault();
    var hrefLink = $(this);
    var viewUrl = hrefLink.attr('href');
    var linkId = hrefLink.attr('id');
    if (requestForCollapse(hrefLink)) {
        collapseSubs(linkId);
        //reset it as expand
        changeLinkAsExpand(hrefLink);
    } else {
        allEvidences(viewUrl, linkId);
        changeLinkAsCollapse(hrefLink);
    }
})

function allEvidences(viewUrl, parentId) {
    $.ajax({
            type:"get",
            url:viewUrl,
            cache:false,
            contentType:'application/json; charset=utf-8',
            dataType:'json',
            success:function (respdata) {
                var ok = respdata.success;
                if (ok == 'true') {
                    allEvidencesSuccess(respdata, parentId);
                } else {
                    showErrorDialog(respdata.message);
                }
            },
            error:function (request, exception) {
                var errormsg = getErrorMsg(request, exception);
                showErrorDialog(errormsg);
            }
        }
    )
}

function allEvidencesSuccess(respData, parentDivId) {
    var parentDiv = $('div.' + parentDivId + '_All_Subs');
    if (parentDiv != null) {
        parentDiv.html();
    }
    var tlEvSummary = respData.tlEvidenceSummary;
    var tlEvidences = tlEvSummary.tlEvidences;
    var allEvPanel = '';
    if (tlEvidences != null) {

        allEvPanel += "<table class='tl_sum_tab'>" +
            "<tbody>";
        $.each(tlEvidences, function (key, tls) {
            var tlColorLevel = tls.colorLevel;
            var colorClass = 'tl1_color';
            if (tlColorLevel == '4') {
                colorClass = 'tl4_color';
            }
            if (tlColorLevel == '3') {
                colorClass = 'tl3_color';
            }
            if (tlColorLevel == '2') {
                colorClass = 'tl2_color';
            }
            if (tlColorLevel == '1') {
                colorClass = 'tl1_color';
            }
            //dent for type
            var tlLevel = tls.tlLevel;
            var dentClss = 'tlevel_1';

            if (tlLevel == 2) {
                dentClss = 'tlevel_2';
            }
            if (tlLevel == 3) {
                dentClss = 'tlevel_3';
            }
            if (tlLevel == 4) {
                dentClss = 'tlevel_4';
            }

            allEvPanel += "<tr class='tr_normal' onMouseOver=\"this.className='tr_highlight'\" onMouseOut=\"this.className='tr_normal'\">" +
                "<td width='10%'>" + tls.dbSource + "</th>" +
                "<td width='10%'>" + tls.geneName + "</td>" +
                "<td width='15%'>" + tls.identifiedAccession + "</td>" +
                "<td width='18%'><div class='" + dentClss + "'>" + tls.typeShortName + "</div></td>" +
                "<td width='5%'>" +
                "<div class='" + colorClass + "'>&nbsp;</div>" +
                "</td>" +
                "<td width='37%'>" + tls.evidence + "</td>" +
                "<td width='5%'>" +
                "<div class='tl_link'><a href='" + tls.hyperLink + "' target='_blank' class='evidence_source'><span class='span_grey'>&nbsp;</span></a></div>" +
                "</td>" +
                "</tr>";
        });
        allEvPanel += "</tbody>" +
            "</table>";
    } else {
        allEvPanel += "<div class='none_evidence'>The evidence is unavailable</div>";
    }
    parentDiv.html(allEvPanel);
}


//view evidence source
$("a.evidence_source").live('click', function (event) {
    event.preventDefault();
    var hrefLink = $(this);
    var viewUrl = hrefLink.attr('href');

    //open new tab
    window.open(viewUrl, '_blank');
    //change out link background
    changeOutLinkBackground(hrefLink);
})

function changeOutLinkBackground(hrefLink) {
    var foundSpan = $('a span#src_span');
    if (foundSpan != 'undefine') {
        foundSpan.attr('id', '');
        foundSpan.attr('class', 'span_grey');
    }
    //set the background for current span
    var spanEL = hrefLink.find('span');
    spanEL.attr('class', 'span_blue');
    spanEL.attr('id', 'src_span');
}

$("div#pupop_msg").live('click', function (event) {
    event.preventDefault();
    var popUpDiv = $(this);
    var type = popUpDiv.attr("class");
    var title = "Popup";
    var htmlMessage = "<div class='message'><br/>This is a pop-up message, it will show some text messages based on a data type.<br/><br/></div> ";

    if (type == 'tl_pe_type') {
        title = "PE Expression"
        htmlMessage = "<div class='message'><b>Protein expression</b><br/><br/>Evidence for protein expression from MS- or antibody-based methods, or curated annotation.  It is a summary of numerous underlying data types.<br/><br/><b>Green:</b>    Strong evidence for expression at the protein level<br/><b>Yellow:</b>   Probable expression at the protein level<br/><b>Red:</b>    Dubious evidence for protein level expression, or strong evidence of transcript expression<br/><b>Black:</b>    No evidence for protein expression is available<br/></div> ";
    }
    if (type == 'tl_pe_ms_type') {
        title = "PE MS"
        htmlMessage = "<div class='message'><b>Protein expression by mass spectrometry</b><br/><br/>Direct MS-based evidence for protein expression.  It is a summary of several underlying data types.<br/><br/><b>Green:</b>    Strong evidence for expression at the protein level<br/><b>Yellow:</b>   Probable expression at the protein level<br/><b>Red:</b>    Dubious evidence for protein level expression<br/><b>Black:</b>    No evidence for protein expression is available<br/></div> ";
    }
    if (type == 'tl_pe_ms_ann_type') {
        title = "PE MS ANN"
        htmlMessage = "<div class='message'><b>Annotation of protein expression by Mass Spectrometry</b><br/><br/>Annotated, indirect evidence for MS-based detection of protein expression.<br/><br/><b>Green:</b>    NA<br/><b>Yellow:</b>   Annotation of detection in Peptide Atlas<br/><b>Red:</b>    Annotation of detection in PRIDE<br/><b>Black:</b>    No annotation of MS evidence is available<br/></div> ";
    }
    if (type == 'tl_pe_ms_prob_type') {
        title = "PE MS PROB"
        htmlMessage = "<div class='message'><b>Probability-based MS detection of protein expression</b><br/><br/>Evidence for protein expression by MS, based upon the highest probability in a single analysis.<br/><br/><b>Green:</b>    log(e)&lt-10 (GPM)<br/><b>Yellow:</b>   -10&ltlog(e)&lt-3 (GPM)<br/><b>Red:</b>    -3&ltlog(e)&lt-1 (GPM)<br/><b>Black:</b>    -1&ltlog(e) (GPM) or no probability-based MS evidence is available<br/></div> ";
    }
    if (type == 'tl_pe_ms_sam_type') {
        title = "PE MS SAM"
        htmlMessage = "<div class='message'><b>Frequency of MS detection</b><br/><br/>Repeated detection of protein expression by MS.<br/><br/><b>Green:</b>    Detected in 100 or more samples (GPM)<br/><b>Yellow:</b>   Detected in 20 to 100 samples (GPM)<br/><b>Red:</b>    Detected in under 20 samples<br/><b>Black:</b>    Not detected in any samples<br/></div> ";
    }
    if (type == 'tl_pe_anti_type') {
        title = "PE ANTI"
        htmlMessage = "<div class='message'><b>Protein expression by antibody technologies</b><br/><br/> Antibody-based evidence for protein expression.  It is a summary of several underlying data types.<br/><br/><b>Green:</b>    Strong evidence for expression at the protein level<br/><b>Yellow:</b>   Probable expression at the protein level<br/><b>Red:</b>    Dubious evidence for protein level expression<br/><b>Black:</b>    No evidence for protein expression is available<br/></div> ";
    }
    if (type == 'tl_pe_anti_ann_type') {
        title = "PE ANTI ANN"
        htmlMessage = "<div class='message'><b>Annotation of antibodies</b><br/><br/> Annotated availability of antibodies in Human Protein Atlas <br/><br/><b>Green:</b>    Not applicable<br/><b>Yellow:</b>   Multiple antibodies are available <br/><b>Red:</b>    A single antibody is available <br/><b>Black:</b>    No antibodies are available<br/></div> ";
    }
    if (type == 'tl_pe_oth_type') {
        title = "PE OTH"
        htmlMessage = "<div class='message'><b>Other evidence for protein expression</b><br/><br/> Any non MS- or antibody-based evidence for protein expression. <br/><br/><b>Green:</b>    Strong evidence for expression at the protein level<br/><b>Yellow:</b>   Probable expression at the protein level<br/><b>Red:</b>    Dubious evidence for protein level expression<br/><b>Black:</b>    No evidence for protein expression is available<br/></div> ";
    }
    if (type == 'tl_pe_oth_cur_type') {
        title = "PE OTH CUR"
        htmlMessage = "<div class='message'><b>Curated annotation of protein expression</b><br/><br/><b>Green:</b>    Direct annotation of protein expression <br/><b>Yellow:</b>   Not applicable<br/><b>Red:</b>    Direct annotation of transcript expression <br/><b>Black:</b>    No evidence for protein expression is available<br/></div> ";
    }
    showPopupMessage(popUpDiv, title, htmlMessage);
})

function showPopupMessage(el, popUpTitle, htmlMessage) {
    $(el).mDialog({
        title:popUpTitle,
        content:htmlMessage,
        close_on_body_click:true,
        width:400
    });
    return false;
}

//expand and collapse function
$("div.expand").live('click', function (event) {
    var expand_collapse = $(this);
    var state = expand_collapse.attr('title');
    var idName = expand_collapse.attr('id');
    expand_collapse.attr('class', 'collapse');
    expand_collapse.attr('title', 'collapse')
    expandSubTypes(idName);
})

$("div.collapse").live('click', function (event) {
    var expand_collapse = $(this);
    var state = expand_collapse.attr('title');
    var idName = expand_collapse.attr('id');
    expand_collapse.attr('class', 'expand');
    expand_collapse.attr('title', 'expand')
    collapseSubTypes(idName);
})

function changeExpandCollapseClass(idName, className, titleVale) {
    var clickDiv = $('div#' + idName);
    clickDiv.attr('class', className);
    clickDiv.attr('title', titleVale);
}

function expandSubTypes(idName) {
    if (idName == 'pe') {
        $('tr.pe_ms').each(function () {
            $(this).show();
        });

        $('tr.pe_anti').each(function () {
            $(this).show();
        });

        $('tr.pe_oth').each(function () {
            $(this).show();
        });
    }
    if (idName == 'pe_ms') {
        $('tr.pe_ms_ann').each(function () {
            $(this).show();
        });
        $('tr.pe_ms_prob').each(function () {
            $(this).show();
        });
        $('tr.pe_ms_sam').each(function () {
            $(this).show();
        });
    }

    if (idName == 'pe_anti') {
        $('tr.pe_anti_ann').each(function () {
            $(this).show();
        });
        $('tr.pe_anti_ihc').each(function () {
            $(this).show();
        });
    }

    if (idName == 'pe_anti_ihc') {
        $('tr.pe_anti_ihc_norm').each(function () {
            $(this).show();
        });
    }

    if (idName == 'pe_oth') {
        $('tr.pe_oth_cur').each(function () {
            $(this).show();
        });
    }
}

function collapseSubTypes(idName) {
    if (idName == 'pe') {
        $('tr.pe_ms').each(function () {
            $(this).hide();
        });
        changeExpandCollapseClass('pe_ms', 'expand', "expand");
        $('tr.pe_ms_ann').each(function () {
            $(this).hide();
        });

        $('tr.pe_ms_prob').each(function () {
            $(this).hide();
        });

        $('tr.pe_ms_sam').each(function () {
            $(this).hide();
        });

        $('tr.pe_anti').each(function () {
            $(this).hide();
        });
        changeExpandCollapseClass('pe_anti', 'expand', "expand");

        $('tr.pe_anti_ann').each(function () {
            $(this).hide();
        });

        $('tr.pe_anti_ihc').each(function () {
            $(this).hide();
        });

        changeExpandCollapseClass('pe_anti_ihc', 'expand', "expand");

        $('tr.pe_anti_ihc_norm').each(function () {
            $(this).hide();
        });

        $('tr.pe_oth').each(function () {
            $(this).hide();
        });
        changeExpandCollapseClass('pe_oth', 'expand', "expand");

        $('tr.pe_oth_cur').each(function () {
            $(this).hide();
        });
    }
    if (idName == 'pe_ms') {
        $('tr.pe_ms_ann').each(function () {
            $(this).hide();
        });
        $('tr.pe_ms_prob').each(function () {
            $(this).hide();
        });
        $('tr.pe_ms_sam').each(function () {
            $(this).hide();
        });
    }

    if (idName == 'pe_anti') {
        $('tr.pe_anti_ann').each(function () {
            $(this).hide();
        });
        $('tr.pe_anti_ihc').each(function () {
            $(this).hide();
        });
        changeExpandCollapseClass('pe_anti_ihc', 'expand', "expand");
        $('tr.pe_anti_ihc_norm').each(function () {
            $(this).hide();
        });
    }

    if (idName == 'pe_anti_ihc') {
        $('tr.pe_anti_ihc_norm').each(function () {
            $(this).hide();
        });
        changeExpandCollapseClass('pe_anti_ihc', 'expand', "expand");
    }

    if (idName == 'pe_oth') {
        $('tr.pe_oth_cur').each(function () {
            $(this).hide();
        });
    }
    //check if it's visible or not. if not.just reset the sum panel
    checkTLSumVisible();
}

function checkTLSumVisible() {
    var tlSumPanel = $('.tl_ev_summary_div');
    var evSumId = tlSumPanel.attr('id');

    if (evSumId != null || evSumId != 'undefine') {
        var trTypeId = evSumId.substr(0, evSumId.indexOf('_sum'));
        var tlTr = $('tr.' + trTypeId);
        if (tlTr != null) {
            if (tlTr.is(':visible')) {
                //do nothing
            } else {
                //remove the highlight color
                removeHighLightColor();
                //reset TrafficLight evidence summary div
                resetTLEvSumDiv();
            }
        }
    }
}

function resetTLEvSumDiv() {
    var tlEvSumDiv = $('.tl_ev_summary_div');
    tlEvSumDiv.removeAttr('id');
    tlEvSumDiv.html('');
    tlEvSumDiv.hide();
}

$('input:checkbox#selected_dbsource').live('change', function (event) {
    changeTPBVersion();


});
$('#selected_chrom').live('change', function (event) {
    changeTPBVersion();
});

function changeTPBVersion() {
    var nxdbs = $("input[name='tlSearchBean.nxDbSelected']").is(':checked');
    var gpmdbs = $("input[name='tlSearchBean.gpmDbSelected']").is(':checked');
    var hpadbs = $("input[name='tlSearchBean.hpaDbSelected']").is(':checked');
    var padbs = $("input[name='tlSearchBean.paDbSelected']").is(':checked');

    var chromType = $('#selected_chrom').val();
    $.ajax({
        type:"get",
        url:"findTpbVersions.jspx?",
        data:{'tlSearchBean.selectedChromType':chromType, 'tlSearchBean.nxDbSelected':nxdbs, 'tlSearchBean.gpmDbSelected':gpmdbs, 'tlSearchBean.hpaDbSelected':hpadbs, 'tlSearchBean.paDbSelected':padbs},
        cache:false,
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success:function (respData) {
            //reset the version values
            $("#selected_version").get(0).options.length = 0;
            // $("#selected_version").get(0).options[0] = new Option("- select a version -", "-1");

            $.each(respData, function (key, value) {
                $('#selected_version').get(0).options[$('#selected_version').get(0).options.length] = new Option(value, key);
            })
        },
        error:function () {
        }
    })
}

$("#selected_ev_level").live('change', function () {
    var selectedIndexValue = $('#selected_ev_level').val();
    var selectedIndexText = $('#selected_ev_level option:selected').text();

    var tabRowIndex = $("#datatype_evidence_level_tab > tbody > tr").length;
    var alreadyAddedRow = $("input=[id=" + selectedIndexValue + "][value=" + selectedIndexValue + "]").val();
    if (alreadyAddedRow != null) {
        return;
    }

    if (selectedIndexValue != '-1') {
        $('#datatype_evidence_level_tab > tbody:last').append("<tr id='type_filter_id_" + tabRowIndex + "'>" +
            "<td width='160px'><input type='hidden' name='tlSearchBean.tlTypeEvLevelFilters[" + tabRowIndex + "].dataType' id='" + selectedIndexValue + "' value='" + selectedIndexValue + "' />" + selectedIndexText + "</td>" +
            "<td><div class='tl4_level'>&nbsp;</div></td>" +
            "<td><input type='checkbox' name='tlSearchBean.tlTypeEvLevelFilters[" + tabRowIndex + "].typeEvLevel4' value='true' id='typeEvLevel4' class='check_box_norm'/></td>" +
            "<td><div class='tl3_level'>&nbsp;</div></td>" +
            "<td><input type='checkbox' name='tlSearchBean.tlTypeEvLevelFilters[" + tabRowIndex + "].typeEvLevel3' value='true' id='typeEvLevel3' class='check_box_norm'/></td>" +
            "<td><div class='tl2_level'>&nbsp;</div></td>" +
            "<td><input type='checkbox' name='tlSearchBean.tlTypeEvLevelFilters[" + tabRowIndex + "].typeEvLevel2' value='true' id='typeEvLevel2' class='check_box_norm'/></td>" +
            "<td><div class='tl1_level'>&nbsp;</div></td>" +
            "<td><input type='checkbox' name='tlSearchBean.tlTypeEvLevelFilters[" + tabRowIndex + "].typeEvLevel1' value='true' id='typeEvLevel1' class='check_box_norm'/></td>" +
            "<td width='50px' align='right'><div class='cancel_type_ev_level'  >&nbsp;</div></td>" +
            "</tr>");
    }
})

$('.cancel_type_ev_level').live('click', function (event) {
    event.preventDefault();

    //var typeRemoveTabRowIndex = $(this).attr('id');
    var trRowId = $(this).closest('tr');
    var trId = trRowId.attr('id');
    alert("try to remove." + trId);
    trRowId.remove();

})



