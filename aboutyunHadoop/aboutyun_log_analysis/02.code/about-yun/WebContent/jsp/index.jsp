<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>统计中心</title>
<link href="../css/common.css" rel="stylesheet">
<script src="../js/jquery-1.9.1.js"></script>
<!--[if IE 6]>
<script src="js/DD_belatedPNG.js"></script>
<script type="text/javascript">
$(function() {
DD_belatedPNG.fix('.logo img,.ico-msg,.header .nav');
});
</script>
<![endif]-->
</head>

<body>
<div style="height:77px;margin-left:auto;margin-right:auto;width:1000px; background:url(../images/logo.png) no-repeat"></div>
<div class="crumbs">
    <div class="wrapper">您现在的位置：<a href="#nogo">aboutyun</a>&nbsp;>&nbsp;<strong>统计中心 </strong></div>
</div>
<div class="wrapper mt10 clearfix">
	<div class="my-side">
        	<dt><strong>各种统计</strong></dt>
			<dd><a href="<%=path%>/statistical/show_log.html">显示日志</a></dd>
            <dd><a href="<%=path%>/statistical/ip_statistical.html">统计ip</a></dd>
            <dd><a href="#nogo">统计跳出率</a></dd>
            <dd><a href="#nogo">统计后台登陆ip</a></dd>
            <dd><a href="#nogo">统计搜索量及搜索词</a></dd>
            <dd><a href="#nogo">统计模块点击量</a></dd>
            <dd><a href="#nogo">空间总访问量</a></dd>
            <dd><a href="#nogo">统计导航点击量</a></dd>
    </div>
    <div class="my-main">
    	<div class="boxA">
			<div class="hd">
			</div>
		</div>
    </div>
</div>
<!--底部开始
<div class="footer">
	<div class="wrapper clearfix">
        <div class="l-box">
            <p class="link" align="right">
            <a href="#nogo">联系我们</a></p>
        </div>
    </div>
</div>-->

</body>
</html>
