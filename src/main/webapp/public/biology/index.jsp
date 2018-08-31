<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<meta name="fragment" content="!">
<meta name="keywords" content="生物学">
<meta name="description" content="生物学 - KxのBook - Kx的个人站点" />

<meta name="author" content="ZHANG JIE"> 
			<title>生物学- KxのBook,Welcome~ Kx的个人站点</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
			 <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">
	<link rel="stylesheet" href="../../css/common.css">


			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
 <!-- <script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script> -->
	<script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>

			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
		
  
	
		

		</head>
		
		
		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

					<%@include file="../phead.jsp"%>




				<div class=" row row-margin-top-70">

					


					<div class="  ">


						<div id="pgdiv" name="pgdiv" class="col-sm-9 col-xs-12 pleft rightline">

	<div class="col-xs-12 margin-bottom-5" ng-cloak>
		<div class="panel panel-top panel-default row " >


 				<div class="panel-heading">Kx说：博物学专题-博物学是人类与大自然打交道的一门古老学问.</div>
			 <div class="panel-body">
		
					<div id="div1" style="margin: 0 auto">
						<p>这里都是生物、化学、物理相关的东西<p>&nbsp;
						<p>大概这些是世界上最神奇的东西了吧<p>&nbsp;
					</div>
					&nbsp;
					
    			</div>

				

		</div>
			<div class="row col-xs-12">
										<hr></hr>
			</div>
	</div>




								<div ng-cloak>
								
									<%@include file="../list.jsp"%> 

							</div>


						</div>


						<div class="col-sm-3 col-xs-12">

							<%@include file="pright.jsp"%> 

							
						</div>


					</div>



				</div>

			</div>



<%@include file="../pfoot.jsp" %>

	

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	
	<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>

	<script type="text/javascript" src="index.js"></script>
							<script type="text/javascript" src="../m_index.js"></script>
						<script type="text/javascript" src="../pright/pright_t_h.js"></script>


				


</body>
</html>