<html>
<head>
    <meta charset="utf-8">
    <title>成功提示</title>
    <#--<link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功!
                </h4> <strong>${msg!""}</strong><a href="${url}" class="alert-link">10s后自动跳转</a>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    <#-- 定时跳转指定URL的页面 -->
    setTimeout('location.href="${url}"', 10000);
</script>

</html>