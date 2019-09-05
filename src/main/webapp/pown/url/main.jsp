<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title id="title">常用链接,KxのBook </title>
			<meta name="keywords" content="常用链接 KxのBook">
            <meta name="robots" content="noindex,nofollow">

<meta name="description" content="常用链接- KxのBook Kx的个人站点" />
<meta name="author" content="ZHANGJIE"> 
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

	
			<link rel="stylesheet" href="../../css/common.css">

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

	
	<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>
				
					
				<link rel="stylesheet" href="../../js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/default.css">
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>

		

		</head>

		<body >


			<div class="" id="content" style="">

		



				<div class=" row row-margin-top-70">

					


					<div class="c ">
					
					
						<div class="col-sm-12 col-xs-12 nopadding pleft">
					
							
					
				
				


					
					
					<div class="col-xs-12" id="srzt" style="min-height: 400px;">
					
					
						
						<div class="row">
					<div class="pull-left " style="margin-top:5px;">分类：</div>
					<div class="row col-lg-3 col-xs-6  margin-bottom-5 ">
					
						<select
						class=" col-xs-12 form-control txt-select-building in-line selectdist "
						id="q_type" name="q_type">

					</select> 
					</div>
					</div>
					

					<div ng-repeat="items in datalist" class="pgdiv" ng-cloak >	
					<div class="panel panel-success urldv row">
					   <div id="{{items.name}}" ></div>
							<div class="panel-heading urlhead" title="" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								<div class="row">
								 
									<h3 class="panel-title col-xs-10 " >{{items.name}}</h3>
					
					
					
									
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in ">
								<div class="panel-body urlbd">
					
									<div class="container">
					
					
					
										<div class="row  form-group margin-bottom-5">
										
											<div ng-repeat="x in items.val" class="pgdiv" ng-cloak >
										
												<div class="col-sm-2 col-xs-12 " >
												
												<div  id="{{x.url_name}}"></div>
												
												<div  class='durl'>
															<div class="row">
															<div class=""><img ng-if="x.icon!=null" style="max-width:30px;"  class="pull-left img-responsive" src="{{x.val2}}{{x.icon}}"></img>
															
															</div>
															<div class="col-xs-9 padding5">
															
															<a class="clear row title" ng-href="{{x.url_val}}" target="_blank"><span class="kutitle" title="{{x.url_name}}">{{x.url_name}}</span></a>
																
																

				
				   										
															</div>
															</div>


												         
																<div class="udesc">{{x.desc_info}}</div>
															
																
															</div>
												
												
		 										</div>
											</div>
										</div>
									</div>

							</div>
							</div>
						</div>	
					</div>
					
					
			
					
					
					
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

		

			<%@include file="../../public/pfoot.jsp" %>
			



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

	<script type="text/javascript" src="index.js"></script>
		
	<script type="text/javascript" src="urlSelect2.js"></script>
	
					


</body>
</html>
