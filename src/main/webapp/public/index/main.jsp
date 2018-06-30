<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>

<link rel="stylesheet" type="text/css" media="screen"
	href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" media="screen"
		href="${basePath}/js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">
<link rel="stylesheet"
	href="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">


<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular.min.js"></script>

<script type="text/javascript"
	src="${basePath}/js/plugin/angular/angular-sanitize.min.js"></script>


	<script type="text/javascript"
		src="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
</head>


<body>
	<div class="" id="content" style="">



		<div class="center row row-margin-top-70">

			<div class="  " style="min-height: 500px;">


				<div id="pgdiv" name="pgdiv"
					class="col-sm-9 col-xs-12 pleft rightline">

					<div ng-cloak>

						<%@include file="../list.jsp"%>

					</div>


				</div>


				<div id="pright" class="col-sm-3 col-xs-12">

					<%@include file="pright.jsp"%>


				</div>


			</div>

		</div>

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
	<script type="text/javascript"
		src="${basePath}/public/pright/pright_t_h.js"></script>





</body>
</html>