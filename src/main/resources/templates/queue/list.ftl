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
                            <th>队列id</th>
                            <th>买家电话</th>
                            <th>队列号码</th>
                            <th>队列状态</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#-- freemarker的list遍历 -->
                        <#list queueInfoPage.content as queueInfo>
                        <tr>
                            <td>${queueInfo.queueId}</td>
                            <td>${queueInfo.buyerPhone}</td>
                            <td>${queueInfo.queueNumber}</td>
                            <td>
                                <#if queueInfo.numberStatus == 0>
                                    排队中
                                <#elseif queueInfo.numberStatus ==1>
                                    过号
                                </#if>
                            </td>
                            <td>${queueInfo.createTime}</td>

                            <td align="center">
                                <#if queueInfo.numberStatus == 0 >
                                    <a class="btn btn-default btn-danger"
                                       href="/queue/cancelNumber?queueId=${queueInfo.queueId}">取消排号</a>
                                <#else>
                                    <span class="glyphicon glyphicon-ban-circle" aria-hidden="false"></span>
                                </#if>
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
                        <li><a href="/queue/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                        <!-- 数字页按钮 -->
                    <#list 1..queueInfoPage.getTotalPages() as index>
                        <#if currentPage == index>  <!-- 如果当前页,灰掉按钮 -->
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/queue/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                        <!-- 下一页按钮 -->
                    <#if currentPage gte queueInfoPage.getTotalPages()><!-- 当前页大于页码总数，灰掉按钮 -->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/queue/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>