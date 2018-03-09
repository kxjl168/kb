<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="webctx" value="${pageContext.request.contextPath}" />

<script>
    	var basePath = "${basePath}";
    	var webctx="${basePath}";
    	
    	var lstheight=700;
    	
    	//var path="${HTTP_PATH}";
    
</script>
<html>
<head>

<title>IE版本升级</title>
<meta name="keywords" content="KxのBook 个人站点  个人BLOG">
<meta name="description" content="首页-欢迎访问-Kx的个人站点" />
<meta name="author" content="ZHANG JIE"> 

<meta name="viewport"
	content="width=device-width, initial-sclae=1.0, maximun-scale=1.0, minimum-scale=1.0, user0scalable=yes">

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" href="${basePath}/css/kCommon.css">

		<link rel="stylesheet" href="${basePath}/css/zcfg.css">
			<link rel="stylesheet" href="${basePath}/css/common.css">
				<link rel="stylesheet"
					href="${basePath}/css/swiper_zcfg.css">

</head>
<body>


<div class="container " id="content">
	

		<nav class="navbar navbar-default navbar-fixed-top  navbar-inverse"
			role="navigation">
		<div class="navbar-header">
			<button type="button" id="menuBtn" class="navbar-toggle"
				data-toggle="collapse" data-target="#menuItem">
				<span class="sr-only"></span><span
					class="hide glyphicon glyphicon-search   "></span><span class=" hide icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://256kb.cn">KxのBook</a>
		</div>
		<div class="collapse navbar-collapse" id="menuItem"></div>
		</nav>


		<div class=" col-xs-12 column row row-margin-top-70">


			<div style="text-align: center;margin: 20 auto;">

<img src="images/sorry.png"></img>

				<p>本站暂不支持IE8以下浏览器访问，请升级您的浏览器获取更好的体验</p>
				<a class="text-info" href="https://support.microsoft.com/zh-cn/help/17621/internet-explorer-downloads">跟上前沿的脚步，GO!</a>



		</div>
		</div>

	</div>
	




	<!--底部 str-->
	<div
		style="width: 100%; height: 34px; background: #e6edf0; position: relative; z-index: 999999; text-align: center; padding-top: 1px; border-top: 1px solid #fff; position: fixed; bottom: 0;">
		
		<p style=" color: #333;">
			KxのBook@Copyright 2017  All Rights Reserved </p>
	</div>


	<!-- 	<script type="text/javascript" -->
	<%-- 		src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script> --%>


	


</body>
</html>
