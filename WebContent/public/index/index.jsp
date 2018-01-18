<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			
<meta name="keywords" content="KxのBook">
<meta name="description" content="KxのBook 的个人BLOG" />
<meta name="author" content="Kx"> 

			<title>KxのBook,Welcome~</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
			 <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">
	<link rel="stylesheet" href="../../css/common.css">

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

			<!-- <script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script> -->

	<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
 <!-- <script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script> -->
	<script type="text/javascript" src="../../js/plugin/angular/angular-sanitize.min.js"></script>
<!-- 			
				 -->
			
			<link rel="stylesheet" href="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">
			
	<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
		
  
	
		

		</head>
		
		
		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

					<%@include file="../phead.jsp"%>

				<div class=" row row-margin-top-70">

					<div class="  ">


						<div id="pgdiv" name="pgdiv" class="col-sm-9 col-xs-12 pleft rightline">

							<div ng-cloak>
								<div ng-repeat="x in datalist" class="pgdiv">

									<div class="row col-xs-12 ">

										<div class="nopadding-left">
											<img class="nopaddding img-responsive col-xs-2" style="width:25px; height:25px;" title="{{x.blog_type_name}}"   ng-src="{{x.blog_type_url}}">
											<div class="col-sm-8 col-xs-11  ptitle  "><a class="ptitle" ng-click="detail(x)">{{x.title}}</a>
											<a ng-href="{{preurl}}/public/detail/{{x.imei}}.html" style="display: none;" class="for spider" >{{x.title}}</a>
											</div>


											<div class="col-sm-3 col-xs-12  text-right">
										
											
											<a ng-repeat="t in x.tagStrs"  ng-click="showtgs(t)">{{t}},</a>
											</div>
										</div>
									</div>

									<div class="margin-top-10 row col-sm-10 col-xs-12"></div>
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
												<a ng-href="{{preurl}}/public/detail/?i={{x.imei}}" style="display: none;" class="for spider" >{{x.title}}</a>

											</div>
									
									</div>
									<br>
									<div ng-cloak class="row  col-sm-10 col-xs-12 margin-top-5 ">post@{{x.create_date}}&nbsp;
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
								<a ng-href="{{preurl}}/public/index/i/{{page-1}}.html"  style="display: none;"  style="" class="for spider" >下一页</a>
							</li>
							<li ng-repeat="x in pageDataPre">

								<a href="#" ng-click="getList(x)">{{x}}</a>
<a ng-href="{{preurl}}/public/index/i/{{x}}.html" style="display: none;" class="for spider" >第{{x}}页</a>
							</li>
							<li class="active">
								<a href="#" ng-click="getList(page)">{{page}}</a>
							</li>
							<li ng-repeat="x in pageDataAft">

								<a href="#" ng-click="getList(x)">{{x}}</a>
<a ng-href="{{preurl}}/public/index/i/{{x}}.html" style="display: none;" class="for spider" >第{{x}}页</a>
							</li>
							<li>
								<a href="#" ng-click="getList(page+1)">&raquo;</a>
								<a ng-href="{{preurl}}/public/index/i/{{page+1}}.html" style="display: none;" class="for spider" >下一页</a>
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


						<div class="col-sm-3 col-xs-12">

							<%@include file="pright.jsp"%> 

							
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
	
	<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>

			<!-- <script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script> -->
		

<%--  <script type="text/javascript"
	src="<c:out value="${basePath}" />/js/plugin/ckeditor4.8/ckeditor.js"></script>
						 <script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script>
  --%>

						

				 		<!-- <script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script>  -->
						<script type="text/javascript" src="index.js"></script>
							<script type="text/javascript" src="../m_index.js"></script>
						<script type="text/javascript" src="../pright/pright_t_h.js"></script>


				


</body>
</html>