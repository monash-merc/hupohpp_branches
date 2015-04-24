<#include  "template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>TPB Home</title>
<#include "template/header_jquery.ftl" />
    <script type="text/javascript">
        $(document).ready(function () {
            var rssFeedDiv = $('#tpb_rssfeed');
            if (rssFeedDiv != null) {
                loadRssFeeds(rssFeedDiv);
            }
        });
    </script>
</head>
<body>
<div class="blank_separator"></div>
<#include "template/navigation.ftl" />
<div class="display_main_container">
<@s.if test="%{#session.authentication_flag =='authenticated'}">
    <!-- sub-nav bar -->
    <div class="subnav_section">
        <#include "template/user_nav.ftl"/>
    </div>
    <!-- end of sub-nav bar -->
</@s.if>
    <div class="display_middle_div">
        <div class="blank_separator"></div>
        <div class="blank_separator"></div>
        <div class="page_title">Home</div>
        <div class="display_inner_div">
            <div class="inner_left">
                <p>
                    Welcome to The Proteome Browser (TPB). This web portal brings together data and information about human
                    proteins from a number of sources and presents them in a gene- and chromosome-centric, interactive format.
                    This resource is currently in the initial phase of development; hence this is only a draft version to
                    demonstrate its potential. To view the interactive prototype report, select a chromosome from the drop down
                    menu in the top right corner or below.
                </p>

                <div class="home_tl_explore">
                <#include "tl/simple_cond.ftl" />
                </div>
                <div style="clear:both"></div>
                <p>
                    TPB is an initiative of the Australia/New Zealand Chromosome 7 consortium of the Chromosome-centric
                    Human
                    Proteome Project (C-HPP).
                </p>

                <p>
                    TPB is currently being developed by the Monash University e-Research Centre <a
                        href="http://www.monash.edu/eresearch">(MeRC)</a> with funding for the
                    initial phase of development provided by the Australian National Data Service <a
                        href="http://www.ands.org.au">(ANDS)</a>.
                </p>

                <p>
                    An important concept of this resource is that it is developed in close collaboration with the global
                    proteomics community. We therefore encourage your involvement and input. For more information, to
                    keep
                    up to date with developments or to provide suggestions/feedback please check the <a target="_blank"
                                                                                                        href=http://www.ozhupohpp7.com/home>Project
                    Wiki</a> or join the
                    <a href="https://groups.google.com/forum/?hl=en&fromgroups#%21forum/theproteomebrowserproject"
                       target="_blank">discussion group.</a>
                </p>

                <div class="blank_separator"></div>

                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>

            </div>
            <div class="inner_right">
            <#include "template/rss_feeds.ftl" />
            </div>
            <div style="clear: both;"/>
        </div>
    </div>
</div>
<#include "template/footer.ftl"/>
</body>
</html>
