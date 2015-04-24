<#include  "../template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>FAQ</title>
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
        <div class="page_title">FAQ</div>
        <div class="display_inner_div">
            <div class="sub_b_title">
                Table of contents
            </div>
            <ul id="toc">
                <li><a href="#section1">Overview/Introduction</a></li>
                <li><a href="#section2">How do I view a chromosome report?</a></li>
                <li>
                    <a href="#section3">How do I filter the chromosome report:</a>
                    <ul id="toc_small">
                        <li><a href="#section3.1">For missing proteins?</a></li>
                        <li><a href="#section3.2">For specific levels of evidence?</a></li>
                        <li><a href="#section3.3">For a list of specific genes?</a></li>
                        <li><a href="#section3.4">For a chromosome region?</a></li>
                    </ul>
                </li>
                <li><a href="#section4">What data sources are used to generate the report and how often are they updated?</a></li>
                <li><a href="#section5">What do each of the data types and colours refer to in the traffic light report?</a></li>
                <li><a href="#section6">How are the colour levels determined for parent data types?</a></li>
                <li><a href="#section7">Why are some tracks not visible when I expand the traffic light display?</a></li>
                <li><a href="#section8">Why are some genes named Unknown?</a></li>
            </ul>
            <div class="sub_b_title" id="section1">
                Overview/Introduction
            </div>
            <div class="paragraph_div">
                This page contains answers to several frequently asked questions regarding usage of The Proteome
                Browser. The user guide includes detailed information about the system, including descriptions of
                the display, functionality, parsing rules, etc.
            </div>
            <div class="sub_b_title" id="section2">
                How do I view a chromosome report?
            </div>
            <div class="paragraph_div">
                From the home page, select the chromosome of interest in the drop down list and select Explore.
                The maximal list of data sources and the most recent version is selected to be the default. On the
                traffic light report additional filters may be applied by selecting the “more options” link near the
                Explore button. These filters include: specific chromosome regions, specific genes or genes with
                specific levels of evidence.
            </div>
            <div class="sub_b_title" id="section3">
                How do I filter the chromosome report:
            </div>
            <div class="sub_norm_title"  id="section3.1">
                For missing proteins?
            </div>
            <div class="paragraph_div">
                View the traffic light report for a chromosome and view the advanced filter options by selecting
                “more options” near the Explore button. Select the PE (Protein Expression) data type from the TPB data type
                drop down, select the black and red data level check boxes and select Explore. Note that while red traffic
                lights show the presence of some data, it is far from definitive and therefore should be considered in the missing proteins.
            </div>

            <div class="sub_norm_title"  id="section3.2">
                For specific levels of evidence?
            </div>
            <div class="paragraph_div">
                Similar to searching for missing proteins, any combination of data type and colour level may be used
                as criteria to filter the traffic light report. Under “more options” on the traffic light report, numerous
                TPB data types and any combination of colour levels for each data type may be used as the filter
                criteria. Note that the criteria are analysed as an OR within a data type and as an AND between
                data types, for example, if the colours green and yellow are selected for PE MS and the colour
                black is selected for PE ANTI, then the criteria will filter genes that have either green OR yellow
                Mass Spectrometry evidence AND black Antibody evidence. If all or no colour levels are selected
                for a data type, that filter criteria is ignored. Data types may be removed from the filter criteria by
                selecting the small red x to the right of the colour criteria.
            </div>

            <div class="sub_norm_title"  id="section3.3">
                For a list of specific genes?
            </div>
            <div class="paragraph_div">
                A list of gene symbols or accessions may be typed or pasted into the Gene List box, by ensuring
                the drop box is appropriately selected. Note that currently the system does not look for gene
                synonyms; therefore the symbols used must correspond to the displayed gene name. Also, currently
                the system only recognises Ensembl gene accessions (i.e. starting with ENSG). If a single entry is
                provided in the Gene List it is treated as a (pre and post) wild card. For example, filtering for a gene
                symbol of “TMEM” will return all genes with TMEM in the name. This can be useful to find members
                of gene families.
            </div>

            <div class="sub_norm_title"  id="section3.4">
                For a chromosome region?
            </div>
            <div class="paragraph_div">
                By inserting start and end base pair indexes into the relevant boxes, specific regions of the
                chromosome can be filtered. These indexes are inclusive; meaning an index of 750000 will pick up a
                gene that spans the 750000 base pair. Leaving the start or end index as 0 will filter from the start or
                end of the chromosome respectively.
            </div>
            <div class="sub_b_title"  id="section4">
                What data sources are used to generate the report and how often are they updated?
            </div>
            <div class="paragraph_div">
                A full list of current data sources is available on the Data Sources tab. Currently, data from neXtProt,
                GPM and Human Protein Atlas are used to compile the evidence. Each source is updated as soon as
                a new version is detected by the system and the most recent version and import date for each data
                source is viewable on the Data Sources tab. Additionally the different versions from each database
                can be viewed on the traffic light report page by selecting a single data source of interest and
                viewing the versions available in the drop box. Note that for combinations of data sources, a version
                of the traffic light is created each time one or more of the data sources is updated, thus if multiple
                data sources are selected, there will be versions corresponding to the versions of each selected data
                source.
            </div>

            <div class="sub_b_title"  id="section5">
                What do each of the data types and colours refer to in the traffic light report?
            </div>
            <div class="paragraph_div">
                A pop-up with a description of the data type and a brief definition of the colour coding is available by
                selecting the data type name on the traffic light report. For detailed information of the parsing rules
                from the data sources to TPB data types, please refer to the Documentation tab.
            </div>

            <div class="sub_b_title"  id="section6">
                How are the colour levels determined for parent data types?
            </div>
            <div class="paragraph_div">
                Currently all data types inherit the highest level evidence from child data types. For example if a
                gene has red Mass spectrometry evidence (PE MS) and green Antibody evidence (PE ANTI), the
                Protein Expression traffic light (PE) will be green.
            </div>
            <div class="sub_b_title"  id="section7">
                Why are some tracks not visible when I expand the traffic light display?
            </div>
            <div class="paragraph_div">
                Due to the size of the database and the large number of genes on some queries, the tracks may
                take some time to load. A progress wheel shows when data is still being loaded. If some tracks do
                not appear, please wait and they will render. If they don’t appear after a reasonable time (up to
                a minute), try collapsing and reopening any sub tracks. Do not refresh the screen, except as a last
                resort, as this will restart the loading.
            </div>
            <div class="sub_b_title"  id="section8">
                Why some genes are named “Unknown”?
            </div>
            <div class="paragraph_div">
                Some evidence from the data sources do not cross reference a known gene or don’t include a
                gene name. At this stage full sequence comparison is not performed to identify identical gene
                sequences and therefore these data are assigned to an unknown gene and placed at the end of the
                chromosome if there is no gene location available. These data are often compiled into known genes
                with updates of the underlying data sources. We will endeavour to assign a sensible gene name to
                these entries in the near future, probably based on an accession or name from the data source that
                generated the data in TPB.
            </div>

            <br/>

            <div class="paragraph_div">
                <p>
                    If you have a question, please click <a href="${base}/site/contactusinfo.jspx">Contact Us</a>.
                </p>
            </div>

            <br/>
        </div>
    </div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>
