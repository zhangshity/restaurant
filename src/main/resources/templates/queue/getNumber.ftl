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
                    <form role="form" method="post" action="/queue/create">

                    <#-- 买家电话 -->
                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">电话</span>
                            </button>
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">*</span>      <#--如果是重新取号，会传电话过来-->
                                <input name="buyerPhone" type="text" class="form-control" value="${(buyerPhone)!''}"  placeholder="电话" aria-describedby="basic-addon1">
                            </div>
                        </div>

                        <div class="form-group">
                            <button type="button" class="btn btn-default btn-sm" aria-label="Left Align">
                                <span class="glyphicon glyphicon-education" aria-hidden="false">状态</span>
                            </button>
                            <select name="peopleQuantity" class="form-control">
                                <option value="4">4人小桌</option>
                                <option value="8">8人大桌</option>
                                <option value="16">16人包厢</option>
                            </select>
                        </div>
                    <#--提交按钮-->
                        <button type="submit" class="btn btn-default btn-info">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>