<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title id="title">KxのGOOGLE 搜索代理转发</title>
			<meta name="keywords" content="GOOGLE 搜索代理转发 KxのBook">
<meta name="description" content="KxのBook 的个人BLOG-GOOGLE 搜索代理转发" />
<meta name="author" content="Kx"> 
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
			<!-- <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">
 -->
			<link rel="stylesheet" href="../../css/kCommon.css">

			<!-- <link rel="stylesheet" href="../../css/zcfg.css"> -->
			<link rel="stylesheet" href="../../css/common.css">
<!-- 			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css"> -->

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

			<!-- <script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script> -->

	<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>
				
					
				<link rel="stylesheet" href="../../js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/default.css">
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>

		

		</head>

		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

			<%@include file="../phead.jsp"%>




				<div class=" row row-margin-top-70">

					


					<div class="c ">
					
					
						<div class="col-sm-12 col-xs-12 nopadding pleft">
					
							
					
				<div class="panel panel-success">
							<div class="panel-heading" title="" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								<div class="row">
									<h3 class="panel-title col-xs-10 ">搜索</h3>
					
					
					
									<span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="hide glyphicon glyphicon-chevron-up pull-right "></span>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in ">
								<div class="panel-body">
					
									<div class="container">
					
					
					
										<div class="row  form-group margin-bottom-5">
										
										
										
											
																		
											<div class=" col-sm-8  col-xs-12 nopaddding row mar margin-bottom-5">
											<div class="control-label nopaddding padding-top-0 col-xs-4 col-sm-3  ">GOOGLE：</div>
												<div class="  col-sm-9 col-xs-8 text-right ">
													<input id="kwd" placeholder="Google is cool!"  name="kwd" ng-model="kwd" type="text" class=" form-control">
												</div>
											</div>		

											<div class=" col-sm-4  col-xs-12 nopaddding row mar margin-bottom-5">
											
											<div class="control-label nopaddding padding-top-0 col-xs-2 col-sm-3  " style="lline-height: 30px;">
													<span class="glyphicon  glyphicon-arrow-left  " title="后退" ng-click="back();"></span>
													<span class="glyphicon  glyphicon-arrow-left  " title="后退" ng-click="back();"></span>  
											</div>
												<div class="  col-sm-5 col-xs-10 text-right ">
									 <button type="button" ng-click="search()" class="btn btn-primary btn-block   ">搜索</button>
												</div>
												
											
											
											</div>		
										</div>
									</div>

							</div>
							</div>
					</div>	
					
					
					<div class="col-xs-12" id="srzt" style="min-height: 400px;">
					
					</div>								
				
					
						</div>
					
					
						<div class="hide col-sm-3 col-xs-12">
					
						 
	
					
					
							<div class=" panel panel-success">
								<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" href="#collapseOne2">
									<div class="row">
										<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">面板2</h3>
					
					
					
										<span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
									</div>
								</div>
								<div id="collapseOne2" class="panel-collapse collapse in">
									<div class="panel-body">
					
										<div class="container">
					
											<p>站位！！</p>
										</div>
					
					
					
									</div>
								</div>
							</div>
						</div>
					
					
					</div>
					
					
					
					
					
					
					
					
					
					
					
					
				</div>
					
			</div>







			



			



			<div class="modal fade " id="myModal3" tabindex="-1 " role="dialog " aria-labelledby="myModalLabel " aria-hidden="true ">
				<div class="modal-dialog " style="width: 250px; ">
					<div class="modal-content ">
						<div class="modal-header ">
							<button type="button " class="close " data-dismiss="modal" aria-hidden="true ">&times;</button>
							<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
						</div>



						<div class="modal-body container margin-top-10 ">
							<div class="row ">

								<p class="col-xs-10 ">确认执行操作吗？</p>

							</div>
						</div>
						<div class="modal-footer ">
							<button type="button " class="btn btn-default btn-warning " data-dismiss="modal ">取消
							</button>
							<button id="btnconfirm " type="button " class="btn btn-primary "> 确定 </button>
						</div>
					</div>
				</div>
			</div>

		

			<%@include file="../pfoot.jsp" %>
			

<!-- <script type="text/javascript" src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>
 -->


			<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	

			<!-- <script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script> -->
			<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>

<%-- 
<script type="text/javascript"
	src="<c:out value="${basePath}" />/js/plugin/ckeditor4.8/ckeditor.js"></script>
						<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script>

 --%>
						
						<!-- <script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script> -->
						<script type="text/javascript" src="index.js"></script>
				<script type="text/javascript" src="../pright/pright_t_h.js"></script>


					


</body>
</html>