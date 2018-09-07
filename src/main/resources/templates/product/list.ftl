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
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>所属种类</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#-- freemarker的list遍历 -->
                        <#list productInfoPage.getContent() as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="70" width="70" src="${productInfo.productIcon}" alt="" class="img-rounded"></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.updateTime}</td>
                            <td><a class="btn btn-default btn-warning" href="/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                            <td>
                                <#if productInfo.getProductStatusEnum().message == "商品上架ing...">
                                    <a class="btn btn-default btn-danger" href="/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                    <a class="btn btn-default btn-primary" href="/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
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
                        <li><a href="/seller/product/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <!-- 数字页按钮 -->
                    <#list 1..productInfoPage.getTotalPages() as index>
                        <#if currentPage == index>  <!-- 如果当前页,灰掉按钮 -->
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <!-- 下一页按钮 -->
                    <#if currentPage gte productInfoPage.getTotalPages()><!-- 当前页大于页码总数，灰掉按钮 -->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>