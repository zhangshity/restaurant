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
                    <form role="form" method="post" action="/seller/product/save">
                        <#-- 名称 -->
                        <div class="form-group">
                            <#--<span class="label label-info">名称</span>-->
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">名称</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="productName" type="text" class="form-control"  value="${(productInfo.productName)!''}" placeholder="麻辣小龙虾" aria-describedby="basic-addon1">
                            </div>
                            <#--<input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>-->
                        </div>
                        <#-- 价格 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">价格</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="productPrice" type="text" class="form-control"  value="${(productInfo.productPrice)!''}" placeholder="88.00" aria-describedby="basic-addon1">
                            </div>
                            <#--<label>价格</label>-->
                            <#--<input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>-->
                        </div>
                        <#-- 库存 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">库存</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="productStock" type="number" class="form-control"  value="${(productInfo.productStock)!''}" placeholder="100" aria-describedby="basic-addon1">
                            </div>
                            <#--<label>库存</label>-->
                            <#--<input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>-->
                        </div>
                        <#-- 描述 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">描述</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="productDescription" type="text" class="form-control"  value="${(productInfo.productDescription)!''}" placeholder="麻辣小龙虾又名口味虾、长沙口味虾、香辣小龙虾，是湖南著名的地方小吃" aria-describedby="basic-addon1">
                            </div>
                            <#--<label>描述</label>-->
                            <#--<input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}"/>-->
                        </div>
                        <#-- 图片 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">图片</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="productIcon" type="text" class="form-control"  value="${(productInfo.productIcon)!''}" placeholder="http://cdn.picture.jpg" aria-describedby="basic-addon1">
                            </div>
                            <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="" class="img-rounded">
                            <#--<label>图片</label>-->
                            <#--<img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">-->
                            <#--<input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}"/>-->
                        </div>
                        <#-- 类目 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">类目</span>
                            </button>
                            <select name="categoryType" class="form-control">
                                <#list productCategoryList as productCategory>
                                    <option value="${productCategory.categoryType}"  <#-- 两个??表示存在 -->
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == productCategory.categoryType>
                                                selected
                                            </#if>
                                    >
                                        ${productCategory.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                            <#--信息post传回/sell/seller/product/save-->
                            <#--因为更新的方法需要productId，但是又不需要显示，就隐藏传回-->
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default btn-info">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>