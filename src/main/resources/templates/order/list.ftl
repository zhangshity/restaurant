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
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#-- freemarker的list遍历 -->
                        <#list orderDTOPage.content as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                            <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                            <td>${orderDTO.createTime}</td>
                            <td><a class="btn btn-default btn-info"
                                   href="/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <td align="center">
                                <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                    <a class="btn btn-default btn-success"
                                       href="/seller/order/finish?orderId=${orderDTO.orderId}">完结</a>
                                <#else>
                                    <span  class="glyphicon glyphicon-ban-circle" aria-hidden="false"></span>
                                </#if>
                            </td>
                            <td align="center">
                                <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                    <a class="btn btn-default btn-danger"
                                       href="/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                        <li><a href="/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                        <!-- 数字页按钮 -->
                    <#list 1..orderDTOPage.getTotalPages() as index>
                        <#if currentPage == index>  <!-- 如果当前页,灰掉按钮 -->
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                        <!-- 下一页按钮 -->
                    <#if currentPage gte orderDTOPage.getTotalPages()><!-- 当前页大于页码总数，灰掉按钮 -->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

<#--弹窗-->
<div class="modal fade" id="webSocketModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                顾客已下单,请及时处理!
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('webSocketMusic').pause()" type="button"
                        class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <#--<button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>-->

                <a id="detailButton" class="btn btn-default btn-primary">查看新的订单</a>

                <#--<a id="detailButton" class="btn btn-default btn-primary"-->
                   <#--href="/seller/order/detail?orderId=">查看新的订单</a>-->
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<#--H5原生的播放音乐写法-->
<audio id="webSocketMusic" loop="loop">
    <source src="/music/song.mp3" type="audio/mpeg"/>
</audio>

<#--外部CDN引用-->
<#--<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>-->
<#--<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->

<#--本地库引用jquery要在前-->
<#--暂时放在head里，提出去引用-->
<#--<script src="/jquery/jquery-3.3.1.min.js"></script>-->
<#--<script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>-->

<script>
    var websocket = null;  //new一个websocket对象
    if ('WebSocket' in window) { //判断下浏览器是否支持websocket
        websocket = new WebSocket('ws://zhangchunyang.natapp1.cc/webSocket');
    } else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) { //对象on open，事件函数{ 控制台打印日志 }
        console.log('建立连接');
    }

    websocket.onclose = function (event) { //对象on close，事件函数{ 控制台打印日志 }
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {  //对象收到消息，事件函数{ 控制台打印日志 }
        console.log('收到消息:\n    订单号:' + event.data)
        //弹窗提醒, 播放音乐
        $('#webSocketModal').modal('show'); //jquery弹窗展示

        document.getElementById('webSocketMusic').play();//h5播放音乐

        $("#detailButton").attr('href', '/seller/order/detail?orderId=' + event.data);
    }

    websocket.onerror = function () { //对象 发生错误，事件函数{ 控制台打印日志 }
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () { //窗口关闭的时候，事件函数{ 控制台打印日志 }
        websocket.close();                  //Websocket关闭
    }
</script>

</body>
</html>