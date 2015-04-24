<#include  "../template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Documentations</title>
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
        <div class="blank_separator"></div>
        <div class="display_inner_div">
            <p>
                We’re currently working on the full documentation and user guide. In the meantime,
                please view the interactive chromosome reports by selecting a chromosome in the drop-down menu in the
                top right corner.
            </p>

            <p>
                The primary report is a matrix of chromosome-ordered genes and expandable data-types. The level of
                evidence for each gene/data-type combination is signified by a traffic light colour system, with green
                being
                highly reliable evidence, yellow representing reasonable evidence, red demonstrating some evidence is
                available or black
                suggesting there is no available evidence. The data-types are hierarchical and thus can be expanded to
                display underlying data-types.
            </p>

            <p>
                Selection of any individual traffic light will launch the secondary report that provides details of the
                individual pieces of evidence.
                This is also hierarchical and provides links out to the original data source from which the evidence was
                sourced.
            </p>

            <p>
                We recommend using Google Chrome or Firefox. At this stage Internet Explorer is not the supported
                browser.
            </p>

            <div class="blank_separator"></div>

            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
        </div>
    </div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>
