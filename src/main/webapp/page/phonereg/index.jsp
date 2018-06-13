
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width,user-scalable=no, initial-scale=1" />
<title>设备注册</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="../../js/plugin/bootstrap/css/bootstrap.min.css" />


<link rel="stylesheet" href="../../css/kCommon.css" />

<link rel="stylesheet" href="../../css/zcfg.css" />
<link rel="stylesheet" href="../../css/common.css" />
<link rel="stylesheet" href="../../css/swiper_zcfg.css" />
<link rel="stylesheet"
	href="../../js/plugin/swiper/idangerous.swiper.css" />
</head>

<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


	<div class="container login_back2" id="content">


		<nav class="navbar navbar-default navbar-fixed-top  navbar-inverse"
			role="navigation">
		<div class="navbar-header">
			<button type="button" id="menuBtn" class="navbar-toggle"
				data-toggle="collapse" data-target="#menuItem">
				<span class="sr-only"></span> <span
					class="hide glyphicon glyphicon-search   "></span> <span
					class=" hide icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">设备注册</a>
		</div>
		<div class="collapse navbar-collapse" id="menuItem"></div>
		</nav>


		<div class=" col-xs-12 column row row-margin-top-70">


			<div class=" row-margin-top-70">




				<form name="fm" class="form-horizontal " style="min-width: 150px;">

					<div class="form-group  row ">
						<div class="control-label padding-top-0 col-xs-4 ">用户ID：</div>
						<div class="col-xs-7 text-right ">
							<input required type="text " class="form-control  "
								name="s_account" id="s_account" ng-model="s_account"
								placeholder=" ">
						</div>
						<div class="col-xs-offset-4  col-xs-5  ">
							<span ng-show="fm.s_account.$error.required"> <span
								style="color: red" title="用户ID必须填写"> <span
									class="glyphicon glyphicon-remove">用户ID必须填写</span>
							</span>
							</span>
						</div>
					</div>


					<div class="form-group  row ">
						<div class="control-label padding-top-0 col-xs-4 ">密码：</div>
						<div class="col-xs-7 text-right ">


							<input required type="text " class="form-control "
								type="password " name="s_pass" id="s_pass" ng-model="s_pass"
								placeholder=" ">
						</div>
						<div class="col-xs-offset-4  col-xs-5  ">
							<span ng-show="fm.s_pass.$error.required"> <span
								style="color: red" title="密码必须填写"> <span
									class="glyphicon glyphicon-remove">密码必须填写</span>
							</span>
							</span>
						</div>
					</div>





					<div class="form-group  row ">
						<div class="control-label padding-top-0 col-xs-4 ">选择位置：</div>

						<div class="col-xs-7 text-right ">

							<select required class="form-control" name="s_city" id="s_city"
								ng-model="s_city">
								<option ng-repeat="x in citys_select ">{{x}}</option>
							</select>
						</div>
						<div class="col-xs-offset-4  col-xs-5  ">
							<span ng-show="fm.s_city.$error.required"> <span
								style="color: red" title="出口城市必须填写"> <span
									class="glyphicon glyphicon-remove">出口城市必须填写</span>
							</span>
							</span>
						</div>


					</div>



				</form>



				<div class="form-group">
					<div id="errorMsg"
						class="input-group error col-xs-12 pull-left text-left h2"></div>
				</div>
				<div class="form-group row-margin-top-10 form-inline ">
					<!-- <div class="col-xs-offset-1 col-xs-10"> -->
					<div class="input-group  col-xs-6 col-xs-offset-4">
						<button type="button" ng-click="reg()"
							class="btn btn-primary btn-block  ">注册</button>

					</div>
					<div class="hide input-group  col-xs-4  col-lg-4  ">

						<a class=" control-label " href="reg.jsp"> <span
							class="unline"></span>
						</a>

					</div>
				</div>




			</div>


		</div>

	</div>



	<!--底部 str-->
	<div
		style="width: 100%; height: 48px; background: #e6edf0; position: relative; z-index: 999999; text-align: center; padding-top: 12px; border-top: 1px solid #fff; position: fixed; bottom: 0;">

		<p style="margin-top: 4px; color: #333;">@Copyright 2017</p>
	</div>






</body>

<script type="text/javascript"
	src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

<script type="text/javascript"
	src="../../js/plugin/angular/angular.min.js"></script>
<script type="text/javascript"
	src="../../js/plugin/angular/angular-resource.min.js"></script>




<script type="text/javascript"
	src="../../js/plugin/jquery/jquery.noty.min.js"></script>
<script type="text/javascript"
	src="../../js/plugin/jquery/noty.layout.center.js"></script>
<script type="text/javascript"
	src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


<script type="text/javascript"
	src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>


<script type="text/javascript"
	src="../../js/plugin/swiper/idangerous.swiper.min.js"></script>


<script src="../../js/loading.js"></script>

<script type="text/javascript"
	src="../../js/plugin/select2/select2.full.min.js"></script>
<script type="text/javascript" src="index.js"></script>



</html>