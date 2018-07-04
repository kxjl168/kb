
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" media="screen"
		href="${basePath}/js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

</head>


<body>
	<div ng-cloak>
	
	<input type="hidden" id="i" value="${i }" />
	<input type="hidden" id="bt" value="${bt }" />
		<input type="hidden" id="tg" value="${tg }" />
			<input type="hidden" id="h" value="${h }" />
 
						<%@include file="../list.jsp"%>

					</div>






	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/jquery.noty.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/noty.layout.center.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/plugin/jquery/noty.themes.bootstrap.js"></script>





	<!-- <script type="text/javascript" src="${basePath}/js/plugin/select2/select2.full.min.js"></script>  -->
	<script type="text/javascript" src="${basePath}/public/index/index.js"></script>
	<script type="text/javascript" src="${basePath}/public/m_index.js"></script>



</body>
</html>