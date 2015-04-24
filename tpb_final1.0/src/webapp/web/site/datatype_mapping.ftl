<#include  "../template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Data Types and Mapping</title>
<#include "../template/header.ftl" />
</head>
<body>
<div class="blank_separator"></div>
<#include "../template/navigation.ftl" />
<div class="display_main_container">
<@s.if test="%{#session.authentication_flag =='authenticated'}">
    <!-- sub-nav bar -->
    <div class="subnav_section">
        <#include "../template/user_nav.ftl"/>
    </div>
    <!-- end of sub-nav bar -->
</@s.if>
    <div class="display_middle_div">
        <div class="blank_separator"></div>
        <div class="blank_separator"></div>
        <div class="page_title"><a href="${base}/site/docs.jspx">Documentation</a> >> Data types and mapping</div>
        <div class="display_inner_div">
            <div class="sub_b_title" id="top">
                Table of contents
            </div>
            <ul id="toc">
                <li><a href="#section1">Overview</a></li>
                <li>
                    <a href="#section2">Current TPB data types</a>
                    <ul id="toc_small">
                        <li><a href="#section2.1">Protein Expression (PE)</a></li>
                        <li><a href="#section2.2">Protein Expression by Mass Spectrometry (PE MS)</a></li>
                        <li><a href="#section2.3">Annotation of protein expression by Mass Spectrometry (PE MS ANN)</a></li>
                        <li><a href="#section2.4">Probability-based MS detection of protein expression (PE MS PROB)</a></li>
                        <li><a href="#section2.5">Frequency of MS detection (PE MS SAM)</a></li>
                        <li><a href="#section2.6">Protein expression by antibody technologies (PE ANTI)</a></li>
                        <li><a href="#section2.7">Annotation of antibodies (PE ANTI ANN)</a></li>
                        <li><a href="#section2.8">Immunohistochemical detection of protein expression (PE ANTI IHC)</a></li>
                        <li><a href="#section2.9">Immunohistochemical detection in normal tissues (PE ANTI IHC NORM)</a></li>
                        <li><a href="#section2.10">Other evidence for protein expression (PE OTH)</a></li>
                        <li><a href="#section2.11">Curated annotation of protein expression (PE OTH CUR)</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#section3">Source to data type and quality score mappings</a>
                    <ul id="toc_small">
                        <li><a href="#section3.1">Introduction</a></li>
                        <li><a href="#section3.2">neXtProt</a></li>
                        <li><a href="#section3.3">GPM</a></li>
                        <li><a href="#section3.4">Human Protein Atlas</a></li>
                    </ul>
                </li>
            </ul>
            <div class="sub_b_title" id="section1">
                Overview
            </div>
            <div class="paragraph_div">
                This document describes the data types currently implemented within TPB, as well as mappings from
                each data source to these types in a source dependant manner. For each data source (currently
                neXtProt, GPM and Human Protein Atlas), mappings are provided between the raw source data and
                TPB data types as well as the thresholds used to define the quality score (i.e. definitions of green,
                yellow, red and black traffic lights).
            </div>
            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <div class="sub_b_title" id="section2">
                Current TPB data types
            </div>
            <div class="blank_separator"></div>

            <!-- PE -->
            <div class="sub_norm_title" id="section2.1">
                Protein Expression (PE)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Evidence for the presence of protein expression. It is a summary of numerous
                    underlying data types, currently from MS- or antibody-based methods, or
                    curated annotation. Note that it is not a measure of expression level (quantitative).
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    1
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    N/A
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    <a href="#section2.2">PE MS</a>, <a href="#section2.6">PE ANTI</a>, <a href="#section2.10">PE OTH</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE MS -->
            <div class="sub_norm_title" id="section2.2">
                Protein Expression by Mass Spectrometry (PE MS)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Direct MS-based evidence for protein expression. It is a summary of several
                    underlying data types.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    2
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.1">PE</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    <a href="#section2.3">PE MS ANN</a>,<a href="#section2.4"> PE MS PROB</a>, <a href="#section2.5">PE MS SAM</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE MS ANN -->
            <div class="sub_norm_title" id="section2.3">
                Annotation of protein expression by Mass Spectrometry (PE MS ANN)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Annotated, indirect evidence for MS-based detection of protein expression.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    3
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.2">PE MS</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    None
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Direct data sources:
                </div>
                <div class="para_field_section">
                    <a href="http://www.nextprot.org" target="_blank">nextProt</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE MS PROB -->
            <div class="sub_norm_title"  id="section2.4">
                Probability-based MS detection of protein expression (PE MS PROB)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Evidence for protein expression by MS, based upon the highest probability in a single analysis.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    3
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.2">PE MS</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    None
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Direct data sources:
                </div>
                <div class="para_field_section">
                    <a href="http://gpmdb.thegpm.org" target="_blank">GPM</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE MS SAM -->
            <div class="sub_norm_title"  id="section2.5">
                Frequency of MS detection (PE MS SAM)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Repeated detection of protein expression by MS.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    3
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.2">PE MS</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    None
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Direct data sources:
                </div>
                <div class="para_field_section">
                    <a href="http://gpmdb.thegpm.org" target="_blank">GPM</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE ANTI -->
            <div class="sub_norm_title"  id="section2.6">
                Protein expression by antibody technologies (PE ANTI)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Antibody-based evidence for protein expression. It is a summary of several
                    underlying data types.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    2
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.1">PE</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    <a href="#section2.7">PE ANTI ANN</a>, <a href="#section2.8">PE ANTI IHC</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE ANTI ANN -->
            <div class="sub_norm_title"  id="section2.7">
                Annotation of antibodies (PE ANTI ANN)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Annotated availability of antibodies in Human Protein Atlas
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    3
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.6">PE ANTI</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    None
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Direct data sources:
                </div>
                <div class="para_field_section">
                    <a href="http://www.nextprot.org" target="_blank">nextProt</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE ANTI IHC -->
            <div class="sub_norm_title"  id="section2.8">
                Immunohistochemical detection of protein expression (PE ANTI IHC)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Detection of protein expression using immunohistochemical methods.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    3
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.6">PE ANTI</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    <a href="#section2.9">PE ANTI IHC NORM</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE ANTI IHC NROM-->
            <div class="sub_norm_title"  id="section2.9">
                Immunohistochemical detection in normal tissues (PE ANTI IHC NORM)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Detection of protein expression in “normal” (non-diseased) tissue by
                    immunohistochemical methods.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    4
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.8">PE ANTI IHC</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    None
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Direct data sources:
                </div>
                <div class="para_field_section">
                    <a href="http://www.proteinatlas.org"  target="_blank">Human Protein Atlas</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE OTH -->
            <div class="sub_norm_title"  id="section2.10">
                Other evidence for protein expression (PE OTH)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Any non MS- or antibody-based evidence for protein expression.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    2
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.1">PE</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    <a href="#section2.11">PE OTH CUR</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- PE OTH CUR -->
            <div class="sub_norm_title"  id="section2.11">
                Curated annotation of protein expression (PE OTH CUR)
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Description:
                </div>
                <div class="para_field_section">
                    Curated annotation of protein expression.
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data level:
                </div>
                <div class="para_field_section">
                    3
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Parent data type:
                </div>
                <div class="para_field_section">
                    <a href="#section2.10">PE OTH</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Child data types:
                </div>
                <div class="para_field_section">
                    None
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Direct data sources:
                </div>
                <div class="para_field_section">
                    <a href="http://www.nextprot.org" target="_blank">nextProt</a>
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <div class="sub_b_title" id="section3">
                Source to data type and quality score mappings
            </div>
            <div class="sub_norm_title"  id="section3.1">
                    Introduction
            </div>
            <div class="paragraph_div">
                For each source repository, a summary of the source files utilised is provided, along with mappings
                of each data type that is derived from the source and the colour mappings used.
            </div>

            <!-- neXtProt -->
            <div class="sub_norm_title"  id="section3.2">
                neXtProt
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Source file format:
                </div>
                <div class="para_field_section">
                    XML
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Source repository:
                </div>
                <div class="para_field_section">
                    &nbsp;
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Data file(s):
                </div>
                <div class="dent_para_field_section">
                    <a href="ftp://ftp.nextprot.org/pub/current_release/xml/nextprot_all.xml.gz">ftp://ftp.nextprot.org/pub/current_release/xml/nextprot_all.xml.gz</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Schema:
                </div>
                <div class="dent_para_field_section">
                    <a href="ftp://ftp.nextprot.org/pub/current_release/xml/nextprotExport.xsd">ftp://ftp.nextprot.org/pub/current_release/xml/nextprotExport.xsd</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data mapping:
                </div>
                <div class="para_field_section">
                    &nbsp;
                </div>
            </div>
            <!-- data mapping 1. PE OTH CUR -->
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    <b>1. TPB data type:</b>
                </div>
                <div class="dent_para_field_section">
                    <a href="#section2.11">PE OTH CUR</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Source data:
                </div>
                <div class="dent_para_field_section">
                    XPath: proteins/protein/proteinExistence@value
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Quality score:
                </div>
                <div class="dent_para_field_section">
                    Based on direct mapping from source data to following colour levels.
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    4 (green):
                </div>
                <div class="dent_para_field_section">
                    “protein level”
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    3 (yellow):
                </div>
                <div class="dent_para_field_section">
                    N/A
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    2 (red):
                </div>
                <div class="dent_para_field_section">
                    “transcript level”
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    1 (black):
                </div>
                <div class="dent_para_field_section">
                    “homology”, “predicted” or “uncertain”
                </div>
            </div>
            <br/>

            <!-- data mapping 2. PE ANTI ANN -->
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    <b>2. TPB data type:</b>
                </div>
                <div class="dent_para_field_section">
                    <a href="#section2.7">PE ANTI ANN</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Source data:
                </div>
                <div class="dent_para_field_section">
                    XPath: proteins/protein/xrefs/xref where @category=“Antibody databases”,
                    @database=“HPA” and @accession starts with “CAB” or “HPA”
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Quality score:
                </div>
                <div class="dent_para_field_section">
                    Based on count of number of antibodies available. Note, only one entry per protein entry.
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    4 (green):
                </div>
                <div class="dent_para_field_section">
                    N/A
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    3 (yellow):
                </div>
                <div class="dent_para_field_section">
                    count > 1
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    2 (red):
                </div>
                <div class="dent_para_field_section">
                    count = 1
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    1 (black):
                </div>
                <div class="dent_para_field_section">
                    count = 0
                </div>
            </div>
            <br/>

            <!-- data mapping 3. PE MS ANN -->
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    <b>2. TPB data type:</b>
                </div>
                <div class="dent_para_field_section">
                    <a href="#section2.3">PE MS ANN</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Source data:
                </div>
                <div class="dent_para_field_section">
                    XPath: proteins/protein/xrefs/xref where @category=“Proteomic
                    databases” and @database=“PeptideAtlas” or “PRIDE”
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Quality score:
                </div>
                <div class="dent_para_field_section">
                    Based on the @database value.
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    4 (green):
                </div>
                <div class="dent_para_field_section">
                    N/A
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    3 (yellow):
                </div>
                <div class="dent_para_field_section">
                    PeptideAtlas
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    2 (red):
                </div>
                <div class="dent_para_field_section">
                    PeptideAtlas
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    1 (black):
                </div>
                <div class="dent_para_field_section">
                    N/A
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- GPM -->
            <div class="sub_norm_title"  id="section3.3">
                GPM
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Source file format:
                </div>
                <div class="para_field_section">
                    XML (customised by R.Beavis for TPB)
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Source repository:
                </div>
                <div class="para_field_section">
                    &nbsp;
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Data file(s):
                </div>
                <div class="dent_para_field_section">
                    URL to the current version is available in an RSS feed at <a href="http://gpmdb.thegpm.org/tpb/current.xml">http://gpmdb.thegpm.org/tpb/current.xml</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Schema:
                </div>
                <div class="dent_para_field_section">
                    gpm2tpb_schema.xsd
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data mapping:
                </div>
                <div class="para_field_section">
                    &nbsp;
                </div>
            </div>
            <!-- data mapping 1. PE MS PROB -->
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    <b>1. TPB data type:</b>
                </div>
                <div class="dent_para_field_section">
                    <a href="#section2.4">PE MS PROB</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Source data:
                </div>
                <div class="dent_para_field_section">
                    XPath: gpmdbsummary/protein/identification@beste
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Quality score:
                </div>
                <div class="dent_para_field_section">
                    Based on the highest log(e) score for each protein.
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    4 (green):
                </div>
                <div class="dent_para_field_section">
                    less than or equal to -10
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    3 (yellow):
                </div>
                <div class="dent_para_field_section">
                    less than or equal to -3 and greater than -10
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    2 (red):
                </div>
                <div class="dent_para_field_section">
                    less than or equal to -1 and greater than -3
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    1 (black):
                </div>
                <div class="dent_para_field_section">
                    higher than -1
                </div>
            </div>
            <br/>

            <!-- data mapping 2. PE MS SAM -->
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    <b>2. TPB data type:</b>
                </div>
                <div class="dent_para_field_section">
                    <a href="#section2.5">PE MS SAM</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Source data:
                </div>
                <div class="dent_para_field_section">
                    XPath: gpmdbsummary/protein/identification@samples
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Quality score:
                </div>
                <div class="dent_para_field_section">
                    Based on number of samples in which the protein was detected.
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    4 (green):
                </div>
                <div class="dent_para_field_section">
                    100 or more samples
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    3 (yellow):
                </div>
                <div class="dent_para_field_section">
                    20 to 99 samples
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    2 (red):
                </div>
                <div class="dent_para_field_section">
                    1 to 19 samples
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    1 (black):
                </div>
                <div class="dent_para_field_section">
                    not detected
                </div>
            </div>
            <br/>

            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
            <!-- Human Protein Atlas -->
            <div class="sub_norm_title"  id="section3.4">
                Human Protein Atlas
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Source file format:
                </div>
                <div class="para_field_section">
                    XML
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Source repository:
                </div>
                <div class="para_field_section">
                    &nbsp;
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Data file(s):
                </div>
                <div class="dent_para_field_section">
                    <a href="http://www.proteinatlas.org/download/proteinatlas.xml.zip">http://www.proteinatlas.org/download/proteinatlas.xml.zip</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Schema:
                </div>
                <div class="dent_para_field_section">
                    <a href="http://www.proteinatlas.org/download/proteinatlas.xsd">http://www.proteinatlas.org/download/proteinatlas.xsd</a>
                </div>
            </div>
            <div class="para_row">
                <div class="para_field_title">
                    Data mapping:
                </div>
                <div class="para_field_section">
                    &nbsp;
                </div>
            </div>
            <!-- data mapping 1. PE ANTI IHC NROM -->
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    <b>1a. TPB data type:</b>
                </div>
                <div class="dent_para_field_section">
                    <a href="#section2.9">PE ANTI IHC NORM</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Source data:
                </div>
                <div class="dent_para_field_section">
                    XPath: proteinAtlas/entry/tissueExpression@type=“APE”&@technology=“IH”
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Quality score:
                </div>
                <div class="dent_para_field_section">
                    Based on reliability scores generated by HPA (documentation available at
                    <a href="http://www.proteinatlas.org/about/quality+scoring#re">http://www.proteinatlas.org/about/quality+scoring#re</a>) though with more
                    stringent colour mappings. Only proteins with positive expression are
                    provided the following quality scores, any tissue that has no expression is
                    given a quality score of 1 (black).
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    4 (green):
                </div>
                <div class="dent_para_field_section">
                    High
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    3 (yellow):
                </div>
                <div class="dent_para_field_section">
                    Medium
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    2 (red):
                </div>
                <div class="dent_para_field_section">
                    Low or Very Low
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    1 (black):
                </div>
                <div class="dent_para_field_section">
                    No protein expression in tissue
                </div>
            </div>
            <br/>

            <!-- data mapping 2b. PE ANTI IHC NORM -->
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    <b>1b. TPB data type:</b>
                </div>
                <div class="dent_para_field_section">
                    <a href="#section2.9">PE ANTI IHC NORM</a>
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Source data:
                </div>
                <div class="dent_para_field_section">
                    XPath: proteinAtlas/entry/tissueExpression@type=“staining”&@technology=“IH”
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    Quality score:
                </div>
                <div class="dent_para_field_section">
                    Based on validation scores generated by HPA (documentation available at
                    <a href="http://www.proteinatlas.org/about/quality+scoring#va">http://www.proteinatlas.org/about/quality+scoring#va</a>) though with more
                    stringent colour mappings. Only proteins with positive staining are provided
                    the following quality scores, any tissue that has no staining is given a quality
                    score of 1 (black).
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    4 (green):
                </div>
                <div class="dent_para_field_section">
                    N/A
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    3 (yellow):
                </div>
                <div class="dent_para_field_section">
                    Supportive
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    2 (red):
                </div>
                <div class="dent_para_field_section">
                    Uncertain, Non-supportive
                </div>
            </div>
            <div class="dent_para_row">
                <div class="dent_para_field_title">
                    1 (black):
                </div>
                <div class="dent_para_field_section">
                    Negative for protein staining in tissue
                </div>
            </div>
            <br/>
            <div class="back_top" title="Go to top">[<a href="#top">Go To Top] <img src="${base}/images/back_top.png" align="top"/></a></div>
        </div>
    </div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>
