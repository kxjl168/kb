<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			
			<title>KxのBook,Welcome~</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css">



<!-- 
			<script type="text/javascript" src="../../js/plugin/ie8/html5shiv.js"></script>
			<script type="text/javascript" src="../../js/plugin/ie8/respond.min.js"></script>
 
 -->

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

			<script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script>

	<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>
			
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
		
		<script type="text/javascript" src="<c:out value="${basePath}" />/js/plugin/jquery/jquery-ui-data-zh-cn.js"></script>
	<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/jquery/jquery-ui.css">
			
	
		

		</head>
		
		
		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

					<%@include file="../phead.jsp"%>




				<div class=" row row-margin-top-70">

					<div class="menu hide">
						<div id="slides">
							<ul class="swiper-container" id="swiper_menu">
								<div class="swiper-wrapper" style="padding-top: 0px; padding-bottom: 0px; width: 320px; height: 50px; transform: translate3d(0px, 0px, 0px);">

								</div>
							</ul>
						</div>
					</div>






					<div class="col-xs-12">
						<div class="hide panel panel-success">
							<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								<div class="row">
									<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">查询条件</h3>



									<span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse ">
								<div class="panel-body">

									<div class="container">



										<div class="row  form-group margin-bottom-5">


											<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">文章标题：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="q_name" ng-model="q_name" placeholder="">

												</div>
											</div>

											<div class=" hide col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">文章类型：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<select class="form-control " id="q_type" ng-model="q_type">
														<option ng-repeat="x in dicts " value="{{x.dict_key}}">{{x.dict_name}}</option>
													</select>
												</div>
											</div>



										</div>
										<div class=" row  form-group margin-bottom-5">
											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">Tags：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="q_tags" ng-model="q_tags" placeholder="">

												</div>
											</div>


											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">关键字：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="q_keys" ng-model="q_keys" placeholder="">

												</div>
											</div>
										</div>





									</div>



								</div>
							</div>
						</div>
					</div>


					<div class="col-xs-12  ">


						<div id="pgdiv" name="pgdiv" class="col-xs-9 pleft rightline">

							<div ng-cloak>
								<div ng-repeat="x in datalist" class="pgdiv">

									<div class="row col-xs-12 ">

										<div class="nopadding-left">
											<img class="nopaddding img-responsive col-xs-2" style="width:25px; height:25px;" title="{{x.blog_type_name}}"   src="{{x.blog_type_url}}">
											<div class="col-xs-8  ptitle  "><a class="ptitle" ng-click="detail(x)">{{x.title}}</a></div>


											<div class="col-xs-3 text-right">
											
											<a ng-repeat="t in x.tagStrs"  ng-click="showtgs(t)">{{t}},</a>
											</div>
										</div>
									</div>

									<div class="margin-top-10 row col-xs-12"></div>
									<div class="hide row col-xs-12">
										<hr></hr>
									</div>


									<div class="col-xs-12 row">
										<div class="alldot">
											<div ng-cloak class=" pageText " ng-bind-html="x.context">
											</div>
										</div>
											<div>
												
												<a href="#" class="detailbtn h5 text-right pull-right text-info margin-right-20" ng-click="detail(x)">详情</a>

											</div>
									
									</div>
									<br>
									<div ng-cloak class="row col-xs-10 margin-top-5 ">post@{{x.create_date}}&nbsp;
										<span ><a ng-click="detail(x)">阅读</a>({{x.view_nums}})&nbsp;</span>
										<span ><a ng-click="detail(x,'rpdiv')">评论</a>({{x.replay_nums}})&nbsp;</span>
									</div>


									<div class="row col-xs-12">
										<hr></hr>
									</div>
								</div>


<div class="  col-xs-12 row tablefoot" ng-show="total">


						<ul class="pagination pull-right">
							<li>
								<a href="#" ng-click="getList(page-1)">&laquo;</a>
							</li>
							<li ng-repeat="x in pageDataPre">

								<a href="#" ng-click="getList(x)">{{x}}</a>

							</li>
							<li class="active">
								<a href="#" ng-click="getList(page)">{{page}}</a>
							</li>
							<li ng-repeat="x in pageDataAft">

								<a href="#" ng-click="getList(x)">{{x}}</a>

							</li>
							<li>
								<a href="#" ng-click="getList(page+1)">&raquo;</a>
							</li>

							<!-- li>
									<a href="#">&laquo;</a>
								</li>
								<li class="active">
									<a href="#">1</a>
								</li>
								<li class="disabled">
									<a href="#">2</a>
								</li>
								<li>
									<a href="#">3</a>
								</li>
								<li>
									<a href="#">&raquo;</a>
								</li> -->
						</ul>

						<select onchange="changerows(this)" class="hide pull-right">
							<option ng-repeat="x in rows_select">{{x}}</option>
						</select>
					</div>

							</div>


						</div>


						<div class="col-xs-3" >

<div style="min-width: 293px;">
							<%@include file="pright.jsp"%> 
</div>
							
						</div>


					</div>





					



					


				</div>

			</div>







			

<%@include file="../pfoot.jsp" %>

	


<script type="text/javascript" src="../../js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>



			<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/kvalidate.js"></script>
	

			<script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script>
			<script src="../../js/menu.js"></script>



<script type="text/javascript"
	src="<c:out value="${basePath}" />/js/plugin/ckeditor4.8/ckeditor.js"></script>
	
	
	
	
						 <script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script>


						<script src="../../js/loading.js"></script>

						<script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script>
						<script type="text/javascript" src="index.js"></script>
							<script type="text/javascript" src="../m_index.js"></script>
						<script type="text/javascript" src="../pright/pright_t_h.js"></script>


						<script>
							$(function () {
								$('#myModal').modal({
									keyboard: true
								})
							});


</body >
</html >