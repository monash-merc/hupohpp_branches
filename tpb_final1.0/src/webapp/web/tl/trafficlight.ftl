<#include  "../template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>TPB Traffic Light</title>
<#include "../template/header_tl_jquery.ftl" />
    <!--[if lte IE 8]>
    <style type="text/css" media="screen">
        .tl_type_title {
            writing-mode: tb-lr;
        }

        .gene_name {
            writing-mode: tb-lr;
            margin-left: -35px;
        }
    </style>
    <![endif]-->
    <!--  Fade out the progress bar if completely loaded the traffic light -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('.progress_bar').fadeOut();
        });
    </script>
</head>
<body>
<div class="blank_separator"></div>
<#include "../template/navigation.ftl" />
<div class="display_main_container">
<!-- sub-nav bar -->
<@s.if test="%{#session.authentication_flag =='authenticated'}">
<div class="subnav_section">
    <#include "../template/user_nav.ftl"/>
</div>
</@s.if>
<!-- end of sub-nav bar -->
<div class="display_middle_div">
<div class="blank_separator"></div>
<div class="display_error_div">
<#include "../template/action_errors.ftl" />
</div>
<!-- page title -->
<div class="page_title">Traffic Light</div>
<!-- inner display area -->
<div class="display_inner_div">
<#include "tlconditions.ftl" />
<@s.if test="%{tlPagination != null}">
    <@s.if test="%{tlPagination.pageResults.size() > 0}">
    <div class="tl_main_panel">
    <div class="tl_data_type">
        <table class="tl_data_type_tab">
            <thead>
            <tr>
                <th>
                    <div class="tl_type_title">&nbsp;</div>
                </th>
                <th width="22px">&nbsp;</th>
            </tr>
            </thead>

            <tbody>
            <tr class="pe">
                <td>
                    <div class="popup_msg" id="tl_pe_type" title="Click to View The PE Definition">PE <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px">
                    <div class="expand" id="pe" title="expand"></div>
                </td>
            </tr>
            <tr class="pe_ms">
                <td>
                    <div class="popup_msg" id="tl_pe_ms_type" title="Click to View The PE MS Definition">PE MS <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px">
                    <div class="expand" id="pe_ms" title="expand"></div>
                </td>
            </tr>
            <tr class="pe_ms_ann">
                <td>

                    <div class="popup_msg" id="tl_pe_ms_ann_type" title="Click to View The PE MS ANN Definition">PE MS ANN <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px"></td>
            </tr>
            <tr class="pe_ms_prob">
                <td>
                    <div class="popup_msg" id="tl_pe_ms_prob_type"  title="Click to View The PE MS PROB Definition">PE MS PROB <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px"></td>
            </tr>
            <tr class="pe_ms_sam">
                <td>
                    <div class="popup_msg" id="tl_pe_ms_sam_type"  title="Click to View The PE MS SAM Definition">PE MS SAM <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px"></td>
            </tr>
            <tr class="pe_anti">
                <td>
                    <div class="popup_msg" id="tl_pe_anti_type"  title="Click to View The PE ANTI Definition">PE ANTI <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px">
                    <div class="expand" id="pe_anti" title="expand"></div>
                </td>
            </tr>
            <tr class="pe_anti_ann">
                <td>
                    <div class="popup_msg" id="tl_pe_anti_ann_type" title="Click to View The PE ANTI ANN Definition">PE ANTI ANN <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px"></td>
            </tr>
            <tr class="pe_anti_ihc">
                <td>
                    <div class="popup_msg" id="tl_pe_anti_ihc_type" title="Click to View The PE ANTI IHC Definition">PE ANTI IHC <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px">
                    <div class="expand" id="pe_anti_ihc" title="expand"></div>
                </td>
            </tr>
            <tr class="pe_anti_ihc_norm">
                <td>
                    <div class="popup_msg" id="tl_pe_anti_ihc_norm_type" title="Click to View The PE ANTI IHC NORM Definition">PE ANTI IHC NORM <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px"></td>
            </tr>
            <tr class="pe_oth">
                <td>
                    <div class="popup_msg" id="tl_pe_oth_type" title="Click to View The PE OTH Definition">PE OTH <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px">
                    <div class="expand" id="pe_oth" title="expand"></div>
                </td>
            </tr>
            <tr class="pe_oth_cur">
                <td>
                    <div class="popup_msg" id="tl_pe_oth_cur_type" title="Click to View The PE OTH CUR Definition">PE OTH CUR <div class='type_info'>&nbsp;&nbsp;&nbsp;</div></div>
                </td>
                <td align="center" width="22px"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class='traffic_light'>
    <table class="tl_tab">
    <!-- gene symbol -->
    <thead>
    <tr>
        <@s.iterator status="tl_gene_stat" value="tlPagination.pageResults" id="tlgenes" >
            <th>
                <div class="gene_name">${tlgenes.tlGene.displayName}</div>
            </th>
        </@s.iterator>
    </tr>
    </thead>
    <tbody>
    <!-- PE Evidence -->
    <tr class="pe">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.peTlColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.peTlColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.peTlColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>
    <!-- PE MS Evidence -->
    <tr class="pe_ms">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.peMSTlColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.peMSTlColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.peMSTlColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE MS ANN Evidence -->
    <tr class="pe_ms_ann">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS_ANN&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEMSANNColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEMSANNColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEMSANNColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE MS Prob Evidence -->
    <tr class="pe_ms_prob">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS_PROB&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEMSPROBColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEMSPROBColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEMSPROBColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE MS Samples Evidence -->
    <tr class="pe_ms_sam">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS_SAM&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEMSSAMColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEMSSAMColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEMSSAMColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE ANTI Evidence -->
    <tr class="pe_anti">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEANTIColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEANTIColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEANTIColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE ANTI ANN Evidence -->
    <tr class="pe_anti_ann">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI_ANN&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEANTIANNColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEANTIANNColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEANTIANNColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE ANTI IHC Evidence -->
    <tr class="pe_anti_ihc">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI_IHC&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEANTIIHCColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEANTIIHCColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEANTIIHCColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE ANTI IHC NORM Evidence -->
    <tr class="pe_anti_ihc_norm">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI_IHC_NORM&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEANTIIHCNORMColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEANTIIHCNORMColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEANTIIHCNORMColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE OTH Evidence -->
    <tr class="pe_oth">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_OTH&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEOTHColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEOTHColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEOTHColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>

    <!-- PE OTH CUR Evidence -->
    <tr class="pe_oth_cur">
        <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
            <td>
                <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_OTH_CUR&tlGeneId=${tlResult.tlGene.id?c}" class="pe_tl_sum">
                    <@s.if test="%{#tlResult.tlPEOTHCURColor.colorLevel == 4}">
                        <div class='tl4_color'>&nbsp;</div>
                    </@s.if>
                    <@s.elseif test="%{#tlResult.tlPEOTHCURColor.colorLevel == 3}">
                        <div class='tl3_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.elseif test="%{#tlResult.tlPEOTHCURColor.colorLevel == 2}">
                        <div class='tl2_color'>&nbsp;</div>
                    </@s.elseif>
                    <@s.else>
                        <div class='tl1_color'>&nbsp;</div>
                    </@s.else>
                </a>
            </td>
        </@s.iterator>
    </tr>
    </tbody>
    </table>
    </div>
    </div>

    <div class="tl_counter_div">
        <div class="tl_counter_title">A total of <font color="green"> ${tlPagination.totalRecords} </font> gene(s) in the traffic light</div>
        <div class="tl_sum_report"><a href="#" class="view_sum_report" title="view the summary report">view summary report</a> &nbsp; &nbsp; <a href="${base}/tl/exportAc.jspx" title="export the gene accessions as a CSV file">export accessions <img src="${base}/images/download.png"/></a></div>
    </div>
    <div class="tl_sum_report_div"></div>
    <div class="tl_ev_summary_div"></div>
    </@s.if>
    <@s.else>
    <br/>

    <div class="none_tl_results_div">
        The traffic light is not currently available based on the filter condtions. Try using other conditions.
    </div>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    </@s.else>
</@s.if>
<@s.else>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
</@s.else>
</div>
</div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>
