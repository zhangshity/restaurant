<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <#-- 订单展示侧边栏 -->
        <li>
            <a href="/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <#-- 商品管理侧边栏 -->
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header"><span class="glyphicon glyphicon-education" aria-hidden="false">操作</span></li>
                <li><a href="/seller/product/list">商品信息</a></li>
                <li><a href="/seller/product/index">添加商品</a></li>
            </ul>
        </li>
        <#-- 类目管理侧边栏 -->
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header"><span class="glyphicon glyphicon-education" aria-hidden="false">操作</span></li>
                <li><a href="/seller/category/list">类目信息</a></li>
                <li><a href="/seller/category/index">添加类目</a></li>
            </ul>
        </li>
        <#--环境(餐桌)管理侧边栏-->
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 环境 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header"><span class="glyphicon glyphicon-education" aria-hidden="false">操作</span></li>
                <li><a href="/environment/list">餐桌信息</a></li>
                <li><a href="/environment/index">添加餐桌</a></li>
            </ul>
        </li>
        <#-- 排号侧边栏 -->
        <li>
            <a href="/queue/list"><i class="fa fa-fw fa-list-alt"></i> 排号信息</a>
        </li>
         <#-- 取号侧边栏(跳转到买家单独的窗口)-->
        <li>
            <a href="/queue/getNumber" target="_blank"><i class="fa fa-fw fa-list-alt"></i> 取号</a>
        </li>
        <#-- 登出侧边栏 -->
        <li>
            <a href="/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>