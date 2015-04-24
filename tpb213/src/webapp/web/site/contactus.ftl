<#include  "../template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Contact Us</title>
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
                If you have suggestions or comments about TPB, please contact us at <a
                    href="mailto:admin@ozhupohpp7.com">admin@ozhupohpp7.com</a>.
            </p>

            <p>
                Also don’t forget to check the <a target="_blank" href="http://www.ozhupohpp7.com">wiki</a> for project
                updates.
            </p>

            <br/>
            <br/>

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