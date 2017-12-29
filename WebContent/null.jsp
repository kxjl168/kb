<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/tag.jsp"%>


<html>
<head>

<title>服务器内部错误</title>

<meta name="viewport"
	content="width=device-width, initial-sclae=1.0, maximun-scale=1.0, minimum-scale=1.0, user0scalable=yes">

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" href="${basePath}/css/kCommon.css">

		<link rel="stylesheet" href="${basePath}/css/zcfg.css">
			<link rel="stylesheet" href="${basePath}/css/common.css">
				<link rel="stylesheet"
					href="${basePath}/css/swiper_zcfg.css">
<script type="text/javascript" src="${basePath}/js/login.js"></script>
<script type="text/javascript" src="${basePath}/js/code.js"></script>
<script type="text/javascript" src="${basePath}/js/loading.js"></script>
<script type="text/javascript">
	
</script>
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
			<a class="navbar-brand" href="#">异常页面</a>
		</div>
		<div class="collapse navbar-collapse" id="menuItem"></div>
		</nav>


		<div class=" col-xs-12 column row row-margin-top-70">


			<p>服务器发生内部错误！,<a class="text-info" href="javascript:void(0);" onclick="window.history.go(-1)">点击这里返回上一页面</a> -__-!</p>


		</div>

	</div>



	<!--底部 str-->
	<div
		style="width: 100%; height: 48px; background: #e6edf0; position: relative; z-index: 999999; text-align: center; padding-top: 12px; border-top: 1px solid #fff; position: fixed; bottom: 0;">
		
		<p style="margin-top: 4px; color: #333;">
			@Copyright 2017</p>
	</div>


	<!-- 	<script type="text/javascript" -->
	<%-- 		src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script> --%>


	<script type="text/javascript"
		src="${basePath}/js/plugin/angular/angular.min.js"></script>




	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.noty.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/noty.layout.center.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/noty.themes.bootstrap.js"></script>


	<script src="${basePath}/js/loading.js"></script>


</body>
</html>
