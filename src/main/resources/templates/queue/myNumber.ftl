<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#--<#include "../common/nav.ftl">-->

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>手机号码</th>
                            <th>排队号码</th>
                            <th>领取时间</th>
                            <th>号码状态</th>
                        </tr>
                        </thead>
                        <tbody>

                        <tr>
                            <td>${queueInfo.buyerPhone}</td>
                            <td>${queueInfo.queueNumber}</td>
                            <td>${queueInfo.createTime}</td>
                            <td>
                                <#if queueInfo.numberStatus == 0>
                                    等待中
                                <#elseif queueInfo.numberStatus ==1>
                                    抱歉,过号了~请重新领取
                                </#if>
                            </td>
                        </tr>

                        </tbody>


                    <#--重新领取号码-->
                    <#--按钮在下一行-->
                        <thead>
                        <tr>
                            <th colspan="4" style="text-align:center;">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <tr>
                            <td align="center" colspan="4">
                                <#if queueInfo.numberStatus == 1 >
                                    <a class="btn btn-default btn-success"
                                       href="/queue/getNumber?buyerPhone=${queueInfo.buyerPhone}">重新领取</a>
                                <#else>
                                    <span class="glyphicon glyphicon-ban-circle" aria-hidden="false"></span>
                                </#if>
                            </td>
                        </tr>


                        </tbody>

                    </table>

                <#--显示等待人数-->
                    <span class="glyphicon glyphicon-hourglass" aria-hidden="false">
                            在您之前还有${counter}个人
                            </span>
                </div>


            </div>
        </div>
    </div>

</div>


</body>
</html>