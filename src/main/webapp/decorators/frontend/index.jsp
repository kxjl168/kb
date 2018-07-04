<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tag.jsp"%>


<html>
<head>


<link rel="bookmark" type="image/x-icon" href="/favicon.ico" />
<link rel="shortcut icon" href="/favicon.ico" />

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1">
<meta name="fragment" content="!">
<meta name="keywords" content="KxのBook,256kb,野生的喵喵,个人站点">
<meta name="description" content="KxのBook -256kb.cn | 野生的喵喵 的个人站点 | 分享工作及生活的点滴" />
<meta name="author" content="ZHANG JIE">
<title><sitemesh:write property='title' /></title>
<title>KxのBook -256kb.cn | 野生的喵喵 的个人站点</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">



<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script>



<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular.min.js"></script>

<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular-sanitize.min.js"></script>

<script src="${basePath}/js/own/menu.js"></script>
<script src="${basePath}/js/own/loading.js"></script>

<link rel="stylesheet"
	href="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">


	<script type="text/javascript"
		src="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>

<sitemesh:write property='head' />


<link rel="stylesheet" href="${basePath}/css/kCommon.css">
<link rel="stylesheet" href="${basePath}/css/common.css">


</head>
<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl" >


	<%@include file="../../public/phead.jsp"%>


	<div class="" id="content" style="">



		<div class=" row row-margin-top-70">

		<div class=" pall " style="min-height: 500px;">

			<div id="pgdiv" name="pgdiv"
				class="col-sm-9 col-xs-12 pleft rightline">
				<sitemesh:write property='body' />

			</div>

			<div class="col-sm-3 col-xs-12 pright">

				<%@include file="../../public/index/pright.jsp"%>

			</div>
			</div>

		</div>





		<%@include file="../../public/pfoot.jsp"%>
</body>



<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="${basePath}/js/plugin/bootstrap/js/bootstrap.min.js"></script>




<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}/js/own/kvalidate.js"></script>

				


</html>



