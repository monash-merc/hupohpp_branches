<#include  "../template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Data Sources</title>
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
                Currently we are utilising the following data sources in TPB.
                We are very grateful to all of them for providing access to
                their data and assistance with implementing them in TPB.
            </p>

            <p>
                <a target="_blank" href="http://www.nextprot.org"><img style="width:200px"
                                                                       src="${base}/images/logo/np.png" border="0"
                                                                       alt="nextprot"/></a>
            </p>

            <p>
                <a target="_blank" href="http://gpmdb.thegpm.org"><img style="width:150px"
                                                                       src="${base}/images/logo/gpm.png" border="0"
                                                                       alt="GPM"/></a>

            <p/>
            </p>
            <p>
                We soon hope to include data from the following data sources:
            </p>

            <p>
                <a target="_blank" href="http://www.proteinatlas.org"><img style="width:200px"
                                                                           src="${base}/images/logo/logo_text_small.gif"
                                                                           border="0" alt="proteinatlas"/>
                    <img src="${base}/images/logo/logo_hex_anim_small.gif" border="0" alt="proteinatlas"/> </a>
            </p>

            <p>
                <a target="_blank" href="http://www.peptideatlas.org"><img style="width:200px"
                                                                           src="${base}/images/logo/PeptideAtlas_Logo.png"
                                                                           border="0" alt="peptideatlas"/></a>
            </p>

            <p>
                <a target="_blank" href="http://www.genecards.org"><img style="width:200px"
                                                                        src="${base}/images/logo/genecards.jpg"
                                                                        border="0"
                                                                        alt="Genecards"/></a>
            </p>

            <p>
                If you manage other data sources that you believe should be included in TPB, or would like to
                suggest other data sources, please <a href="http://hpbdev.its.monash.edu/tpb/site/contactus.jspx">contact
                us</a>.
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
