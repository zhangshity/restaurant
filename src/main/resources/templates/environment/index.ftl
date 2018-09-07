<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/environment/save">

                    <#-- 餐桌名称 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">名称</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="environmentName" type="text" class="form-control"  value="${(environmentInfo.environmentName)!''}" placeholder="1号桌" aria-describedby="basic-addon1">
                            </div>
                        </div>

                    <#-- 餐桌人数 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">人数</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="peopleQuantity" type="text" class="form-control"  value="${(environmentInfo.peopleQuantity)!''}" placeholder="6" aria-describedby="basic-addon1">
                            </div>
                        </div>


                    <#-- 餐桌状态 -->
                        <#--<div class="form-group">-->
                            <#--<button type="button" class="btn btn-default btn-sm" aria-label="Left Align">-->
                                <#--<span class="glyphicon glyphicon-education" aria-hidden="false">状态</span>-->
                            <#--</button>-->
                            <#--<select name="environmentStatus" class="form-control">-->
                                <#--<#list environmentInfoListSelect as environmentInfoSelect>-->
                                    <#--<option value="${environmentInfoSelect.environmentStatus}"  &lt;#&ndash; 两个??表示存在 &ndash;&gt;-->
                                            <#--<#if (environmentInfo.environmentStatus)??>-->
                                                <#--selected-->
                                            <#--</#if>-->
                                    <#-->-->
                                        <#--<#if environmentInfoSelect.environmentStatus == 0>-->
                                            <#--无人使用-->
                                        <#--<#elseif environmentInfoSelect.environmentStatus ==1>-->
                                            <#--正在使用ing-->
                                        <#--<#elseif environmentInfoSelect.environmentStatus ==2>-->
                                            <#--已经移除-->
                                        <#--</#if>-->
                                        <#--&lt;#&ndash;${environmentInfoSelect.environmentStatus}&ndash;&gt;-->
                                    <#--</option>-->
                                <#--</#list>-->
                            <#--</select>-->
                        <#--</div>-->

                    <#-- 餐桌状态 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">状态</span>
                            </button>
                            <select name="environmentStatus" class="form-control">
                                <option value="0"<#if environmentInfo?? && environmentInfo.environmentStatus==0>selected</#if>>空桌</option>
                                <option value="1"<#if environmentInfo?? && environmentInfo.environmentStatus==1>selected</#if>>使用中ing</option>
                                <option value="2"<#if environmentInfo?? && environmentInfo.environmentStatus==2>selected</#if>>餐桌移除</option>
                            </select>
                        </div>


                    <#-- 餐桌描述 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">描述</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="environmentDescription" type="text" class="form-control"  value="${(environmentInfo.environmentDescription)!''}" placeholder="6人大厅桌" aria-describedby="basic-addon1">
                            </div>
                        </div>



                        <#--信息post传回/environment/save-->
                        <#--因为更新的方法需要productId，但是又不需要显示，就隐藏传回-->
                        <input hidden type="text" name="environmentId" value="${(environmentInfo.environmentId)!''}">
                        <button type="submit" class="btn btn-default btn-info">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>