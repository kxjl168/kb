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
<meta name="keywords" content="KxのBook 个人站点  个人BLOG">
<meta name="description" content="首页- Kx的个人站点" />
<meta name="author" content="ZHANG JIE">

<title>KxのBook -256kb.cn | 野生的喵喵 的个人站点</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">



<script type="text/javascript"
	src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script>




<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />


<link rel="stylesheet" href="${basePath}/css/kCommon.css">
<link rel="stylesheet" href="${basePath}/css/common.css">
<link rel="stylesheet" href="${basePath}/css/k2020.css">



</head>
	<body  class="k2020" id="ngSection" ng-app="myApp" ng-controller="eduCtrl" style="overflow: hidden">


	


	<sitemesh:write property='body' />


<%@include file="frontend/adialog.jsp"%>


</body>




</html>



