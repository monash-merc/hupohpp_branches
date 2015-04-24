<#include  "../template/ftl_header.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Term Of Use</title>
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
        <div class="page_title">Terms of Use</div>
        <div class="display_inner_div">

            <div class="sub_b_title" id="section1">
                Terms Of Use
            </div>
            <div class="paragraph_italic_div">
                The use of the Proteome Browser website by a person (“User”) is subject to these Terms of Use.
                The Terms of Use contain important information, including warranty disclaimers and limitations of liability. By using this website, Users agree to be bound by these Terms of Use.
            </div>
            <div class="sub_b_title" id="section2">
                Use of this website
            </div>
            <div class="paragraph_div">
                The material published on this website has been developed by Monash University with assistance from a number of other parties.
            </div>
            <div class="paragraph_div">
                The information provided is for general information purposes only. It is not intended to be comprehensive and does not constitute, and must not be relied upon as, a substitute for
                professional advice.
            </div>
            <div class="paragraph_div">
                Users access the website, and rely on the material contained in the website, entirely at their own risk.
            </div>
            <div class="sub_b_title">
                Disclaimer of warranties and representations
            </div>
            <div class="paragraph_div">
                Except as required by law, Monash University gives no express or implied warranties or makes any representations in relation to this website or any of the content. In particular, while
                care has been taken in creating the website, Monash University does not warrant or represent that:
            </div>
            <ul id="toc">
                <li>the information provided on the website is accurate, complete, up-to-date or suitable for any purpose;</li>
                <li>the website itself is free from any computer viruses or other defects; or</li>
                <li>the User's access to the website will be timely, secure, continuous or uninterrupted.</li>
            </ul>
            <div class="sub_b_title">
                Limitation of liability
            </div>
            <div class="paragraph_div">
                To the fullest extent permitted by law, Monash University, its officers, employees, agents and representatives, will not be liable in any way for any loss or damage (including any
                indirect, incidental, special or consequential loss or damage) to any person, however caused (whether by negligence or otherwise) which may arise directly or indirectly in respect of
                any use of this website or its content. Where conditions and warranties implied by law cannot be excluded, Monash University limits its liability to the extent provided for by the
                Australian Consumer Law (Schedule 2 of the Competition and Consumer Act 2010 (Cth)).
            </div>
            <div class="sub_b_title">
                Links to third party websites
            </div>
            <div class="paragraph_div">
                This website contains links to third party websites over which Monash University has no control. These links are provided for convenience only and may not be current.
            </div>
            <div class="paragraph_div">
                Monash University is not responsible for the contents of the linked websites. Users access those websites at their own risk. Provision of a link should not be construed as an
                endorsement or approval of the third party website by Monash University.
            </div>
            <div class="paragraph_div">
                Third party websites should have their own terms of use, which Users are encouraged to read.
            </div>

            <div class="sub_b_title">
                Copyright
            </div>
            <div class="paragraph_div">
                Unless otherwise indicated, copyright in the website and its content is owned by Monash University. Third parties may own the copyright in some materials incorporated within this
                website.
            </div>
            <div class="paragraph_div">
                Users may only access and use the content from this website, including saving an electronic copy or printing out a copy, for their own personal, non-commercial use. Otherwise, except
                as permitted by the Copyright Act 1968 (Cth), no part of the website may be reproduced in any form without the prior written consent of Monash University. Users must not modify any
                content nor remove the copyright notice from any content.
            </div>
            <div class="sub_b_title">
                Jurisdiction
            </div>
            <div class="paragraph_div">
                Users must ensure that their access to and use of this website is in accordance with the laws in their jurisdiction.
            </div>
            <div class="paragraph_div">
                The content, operation and interpretation of this website and these Terms of Use will be governed by the laws of Victoria, Australia. Users agree to submit to the exclusive
                jurisdiction of the Courts of Victoria, Australia in the event of a dispute arising out of, or in connection with, this website.
            </div>

            <div class="sub_b_title">
                Changes to these Terms of Use
            </div>
            <div class="paragraph_div">
                Monash University may amend these Terms of Use from time to time, at its discretion and without notice. A current version of the Terms of Use will be available on this website.
            </div>
            <br/>
        </div>
    </div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>
