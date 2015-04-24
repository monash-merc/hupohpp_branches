<div class="tl_control_div">
<@s.form action="trafficlight.jspx" namespace="/tl" method="post">
    <div class="tl_control_left">
        <div class="filter_field_row">
            <div class="base_conds">
                Chromosome: <@s.select name ="tlSearchBean.selectedChromType" id="selected_chrom"list ="chromTypes"  cssClass="select_norm"/>
                &nbsp;&nbsp;
                &nbsp;Data Sources
                : <@s.checkbox name="tlSearchBean.nxDbSelected" id="selected_dbsource" cssClass="check_box_norm" />
                neXtProt
                &nbsp;
                &nbsp; <@s.checkbox name="tlSearchBean.gpmDbSelected" id="selected_dbsource" cssClass="check_box_norm" />
                GPM
                &nbsp;
                &nbsp; <@s.checkbox name="tlSearchBean.hpaDbSelected" id="selected_dbsource" cssClass="check_box_norm" />
                HPA
                &nbsp;
                &nbsp; <@s.checkbox name="tlSearchBean.paDbSelected" id="selected_dbsource" cssClass="check_box_norm" />
                PA
                &nbsp;
                &nbsp; &nbsp;
                Version: <@s.select name = "tlSearchBean.selectedVersion" id="selected_version" headerKey="tlSearchBean.selectedVersion" list="tpbVersions" cssClass="select_norm2"/>

            </div>
        </div>
        <div style="clear: both;"></div>
        <div class="filter_separator"></div>
        <div class="filter_field_row">
            <div class="filter_field_title">Data Type</div>
            <div class="filter_field_section">
                <div>
                    The Evidence Level Of Data
                    Type:  <@s.select name = "dataType" id="selected_ev_level" headerKey="-1" headerValue ="-- select a data type --" list="tpbDataTypes" cssClass="select_norm2"/>
                </div>
                <div class="blank_separator"></div>
                <table class="datatype_evidence_level_tab" id="datatype_evidence_level_tab">
                    <tbody>
                        <@s.iterator status="eLevelStat" value="tlSearchBean.tlTypeEvLevelFilters" id="evLevel" >
                        <tr id='type_filter_id_<@s.property  value="#eLevelStat.index" />'>
                            <td width="160px">
                                <@s.hidden name="tlSearchBean.tlTypeEvLevelFilters[%{#eLevelStat.index}].dataType"  value="%{#evLevel.dataType}" id="%{#evLevel.dataType}"/>
                                <@s.property  value="#evLevel.dataType" />
                            </td>
                            <td>
                                <div class='tl4_level'>&nbsp;</div>
                            </td>
                            <td><@s.checkbox name="tlSearchBean.tlTypeEvLevelFilters[%{#eLevelStat.index}].typeEvLevel4" id="typeEvLevel4" cssClass="check_box_norm"/></td>
                            <td>
                                <div class='tl3_level'>&nbsp;</div>
                            </td>
                            <td><@s.checkbox name="tlSearchBean.tlTypeEvLevelFilters[%{#eLevelStat.index}].typeEvLevel3" id="typeEvLevel3" cssClass="check_box_norm"/></td>
                            <td>
                                <div class='tl2_level'>&nbsp;</div>
                            </td>
                            <td><@s.checkbox name="tlSearchBean.tlTypeEvLevelFilters[%{#eLevelStat.index}].typeEvLevel2" id="typeEvLevel2" cssClass="check_box_norm"/></td>
                            <td>
                                <div class='tl1_level'>&nbsp;</div>
                            </td>
                            <td><@s.checkbox name="tlSearchBean.tlTypeEvLevelFilters[%{#eLevelStat.index}].typeEvLevel1" id="typeEvLevel1" cssClass="check_box_norm"/></td>
                            <td width="50px" align="right"><div class="cancel_type_ev_level">&nbsp;</div></td>
                        </tr>
                        </@s.iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <div style="clear: both;"></div>
        <div class="filter_separator"></div>
        <div class="filter_field_row">
            <div class="filter_field_title">Chromosome Region</div>
            <div class="filter_field_value">From: <@s.textfield name="chromlocationFrom" /> &nbsp;
                To: <@s.textfield name="chromlocationFrom" /></div>
        </div>
        <div style="clear: both;"></div>
        <div class="filter_separator"></div>
        <div class="filter_field_row">
            <div class="filter_field_title">Gene List</div>
            <div class="filter_field_section">
                <@s.textarea  name="searchBean.ensembls" cols="100" rows="5" cssClass="input_textarea" /> &nbsp; &nbsp;
                <div class="comments">(eg: ENSG00000182179,ENSG00000126351 &nbsp; Separated by Comma or Tab or NewLine)</div>
            </div>
        </div>
        <div style="clear: both;"></div>
        <br/>
        <div class="div_center">
            <input type="submit" name="explore" value="Explore" class="chrom_button">
        </div>
        <div style="clear: both;"></div>
    </div>
</@s.form>
    <div class="tl_control_right">
        &nbsp;
    </div>
</div>
<div style="clear: both;"></div>