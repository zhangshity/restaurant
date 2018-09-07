<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
        <#--订单主表,外加附表连接和取消连接-->
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>环境id</th>
                            <th>餐桌名称</th>
                            <th>餐桌人数</th>
                            <th>餐桌状态</th>
                            <th>餐桌描述</th>
                            <th colspan="3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#-- freemarker的list遍历 -->
                        <#list environmentInfoPage.content as environmentInfo>
                        <tr>
                            <td>${environmentInfo.environmentId}</td>
                            <td>${environmentInfo.environmentName}</td>
                            <td>${environmentInfo.peopleQuantity}</td>
                            <td>
                                <#if environmentInfo.environmentStatus == 0>
                                    空桌
                                <#elseif environmentInfo.environmentStatus ==1>
                                    使用中ing
                                <#elseif environmentInfo.environmentStatus ==2>
                                    餐桌移除
                                </#if>
                            </td>
                            <td>${environmentInfo.environmentDescription}</td>

                            <td align="center">
                                <#if environmentInfo.environmentStatus == 0 >
                                    <a class="btn btn-default btn-success"
                                       href="/environment/inUse?environmentId=${environmentInfo.environmentId}">顾客入座</a>
                                <#elseif environmentInfo.environmentStatus == 1>
                                    <a class="btn btn-default btn-primary"
                                       href="/environment/notUsed?environmentId=${environmentInfo.environmentId}">顾客离开</a>
                                <#elseif environmentInfo.environmentStatus == 2>
                                    <span class="glyphicon glyphicon-ban-circle" aria-hidden="false"></span>
                                </#if>
                            </td>

                            <td align="center">
                                <#if environmentInfo.environmentStatus != 2>
                                    <a class="btn btn-default btn-danger"
                                       href="/environment/abadon?environmentId=${environmentInfo.environmentId}">移除餐桌</a>
                                <#else>
                                    <span class="glyphicon glyphicon-trash" aria-hidden="false">餐桌已经移除</span>
                                </#if>

                            </td>

                            <td align="center">
                                    <a class="btn btn-default btn-warning"
                                       href="/environment/index?environmentId=${environmentInfo.environmentId}">修改信息</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right"> <!-- 分页到右边去  -->
                        <!-- 上一页按钮 -->
                    <#if currentPage lte 1> <!--当前页小于等于1,灰掉按钮-->
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/environment/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                        <!-- 数字页按钮 -->
                    <#list 1..environmentInfoPage.getTotalPages() as index>
                        <#if currentPage == index>  <!-- 如果当前页,灰掉按钮 -->
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/environment/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                        <!-- 下一页按钮 -->
                    <#if currentPage gte environmentInfoPage.getTotalPages()><!-- 当前页大于页码总数，灰掉按钮 -->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/environment/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>