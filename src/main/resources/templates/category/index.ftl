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
                    <form role="form" method="post" action="/sell/seller/category/save">

                        <#--类目名称-->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">名称</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="categoryName" type="text" class="form-control"  value="${(productCategory.categoryName)!''}" placeholder="热销榜" aria-describedby="basic-addon1">
                            </div>
                        </div>

                        <#--类目编号-->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">类目编号</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>
                                <input name="categoryType" type="text" class="form-control"  value="${(productCategory.categoryType)!''}" placeholder="1" aria-describedby="basic-addon1">
                            </div>
                        </div>

                        <#--类目Id(隐藏传输)-->
                        <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                        <button type="submit" class="btn btn-default btn-primary">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>