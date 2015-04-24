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
            margin-left: -25px;
        }
    </style>
    <![endif]-->
</head>
<body>
<div class="blank_separator"></div>
<#include "../template/navigation.ftl" />
<div class="display_main_container">
<!-- sub-nav bar -->
<!-- div class="subnav_section" -->
<!-- #include "template/user_nav.ftl"/ -->
<!-- /div -->
<!-- end of sub-nav bar -->

<div class="display_middle_div">
<div class="blank_separator"></div>
<div class="blank_separator"></div>
<div class="blank_separator"></div>
<div class="display_inner_div">
<#include "tlconditions.ftl" />
<div class="filter_separator"></div>
<@s.if test="%{tlPagination.pageResults.size() > 0}">

<div class="tl_main_panel">
<div class="tl_data_type">
    <table class="tl_data_type_tab">
        <thead>
        <tr>
            <th>
                <div class="tl_type_title">Data Type</div>
            </th>
            <th width="22px">&nbsp;</th>
        </tr>
        </thead>

        <tbody>
        <tr class="pe">
            <td>
                <div id="pupop_msg" class="tl_pe_type">PE</div>
            </td>
            <td align="center" width="22px"><div class="expand"  id="pe" title="expand"></div></td>
        </tr>
        <tr class="pe_ms">
            <td>
                <div id="pupop_msg" class="tl_pe_ms_type">PE MS</div>
            </td>
            <td align="center" width="22px"><div class="expand" id="pe_ms" title="expand"></div></td>
        </tr>
        <tr class ="pe_ms_ann">
            <td>
                <div id="pupop_msg" class="tl_pe_ms_ann_type">PE MS ANN</div>
            </td>
            <td align="center" width="22px"></td>
        </tr>
        <tr class ="pe_ms_prob">
            <td>
                <div id="pupop_msg" class="tl_pe_ms_prob_type">PE MS PROB</div>
            </td>
            <td align="center" width="22px"></td>
        </tr>
        <tr class ="pe_ms_sam">
            <td>
                <div id="pupop_msg" class="tl_pe_ms_sam_type">PE MS SAM</div>
            </td>
            <td align="center" width="22px"></td>
        </tr>
        <tr class="pe_anti">
            <td>
                <div id="pupop_msg" class="tl_pe_anti_type">PE ANTI</div>
            </td>
            <td align="center" width="22px"><div class="expand" id="pe_anti" title="expand"></td>
        </tr>
        <tr class="pe_anti_ann">
            <td>
                <div id="pupop_msg" class="tl_pe_anti_ann_type">PE ANTI ANN</div>
            </td>
            <td align="center" width="22px"></td>
        </tr>
        <tr class="pe_anti_ihc">
            <td>
                <div id="pupop_msg" class="tl_pe_anti_ihc_type">PE ANTI IHC</div>
            </td>
            <td align="center" width="22px"><div class="expand" id="pe_anti_ihc" title="expand"></td>
        </tr>
        <tr class="pe_anti_ihc_norm">
            <td>
                <div id="pupop_msg" class="tl_pe_anti_ihc_norm_type">PE ANTI IHC NORM</div>
            </td>
            <td align="center" width="22px"></td>
        </tr>
        <tr class="pe_oth">
            <td>
                <div id="pupop_msg" class="tl_pe_oth_type">PE OTH</div>
            </td>
            <td align="center" width="22px"><div class="expand" id="pe_oth" title="expand"></td>
        </tr>
        <tr class="pe_oth_cur">
            <td>
                <div id="pupop_msg" class="tl_pe_oth_cur_type">PE OTH CUR</div>
            </td>
            <td align="center" width="22px"></td>
        </tr>
        </tbody>
    </table>
</div>

<div class='traffic_light'>
<table class="tl_tab">
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
        <a href="${base}/tl/petlsum.jspx?tpbDataType=PE&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_1">
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
<a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_2">
<@s.if test="%{#tlResult.peMSTlColor.colorLevel == 4}">
    <div class='tl4_color'>&nbsp;</div>
</@s.if>
<@s.elseif test="%{#tlResult.peMSTlColor.colorLevel == 3}">
    <div class='tl3_color'
    '>&nbsp;</div>
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
<tr class ="pe_ms_ann">
<@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
    <td>
        <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS_ANN&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_3">
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
<tr class ="pe_ms_prob">
    <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
        <td>
            <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS_PROB&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_3">
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
<tr class ="pe_ms_sam">
    <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
        <td>
            <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_MS_SAM&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_3">
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
        <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_2">
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
        <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI_ANN&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_3">
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
            <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI_IHC&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_3">
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

<!-- PE ANTI IHC Evidence -->
<tr class="pe_anti_ihc_norm">
    <@s.iterator status="tlStat" value="tlPagination.pageResults" id="tlResult" >
        <td>
            <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_ANTI_IHC_NORM&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_4">
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
        <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_OTH&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_2">
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
        <a href="${base}/tl/petlsum.jspx?tpbDataType=PE_OTH_CUR&tlGeneId=${tlResult.tlGene.id?c}" class="tl_type_level_3">
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
</@s.if>
<@s.else>
<br/>
<br/>
This combination of chromosome and data sources is not currently available.</br> Try using another type</br>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
</@s.else>
<br/>
<div class="tl_ev_summary_div">

</div>
<br/>

<br/>
</div>
</div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>
