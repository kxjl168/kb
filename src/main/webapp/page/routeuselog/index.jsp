<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	>
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>{{title}}</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css">



		</head>

		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">


			<div class="" id="content" style="">

				<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
					<div class="navbar-header  ">
						<button type="button" id="menuBtn" class="navbar-toggle" data-toggle="collapse" data-target="#menuItem">
							<span class="sr-only"></span>
							<span class="glyphicon glyphicon-th-list  "></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">{{title}}</a>
					</div>
					<div class="collapse navbar-collapse pull-right-k " id="menuItem">
						<ul class="hide nav navbar-nav ">
							<li class=" bg-success">
								<a href="javascript:void(0)" ng-click="btnP()"> 行政许可</a>
							</li>
							<li class="  bg-warning">
								<a href="javascript:void(0)" ng-click="btnF()">行政处罚</a>
							</li>

						</ul>
						<form class=" hide navbar-form navbar-left " role="search">

							<div class="input-group  ">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-search   "></i>
								</span>
								<input id="key" type="text" class="form-control" placeholder="过滤账号">
								<span class="input-group-btn">
									<input type="button" class="btn btn-primary" ng-click="Search()" value="搜索"> </span>
							</div>
						</form>

						<ul class="nav navbar-nav " id="menuul">
							<!-- 	<li ><a href="#" ng-click="load('device')">设备状态</a></li>
										<li class="active"><a  href="#" >转发日志</a></li>
										<li class="hide"><a href="">登出</a></li>   -->

						</ul>


					</div>
				</nav>




				<div class="row row-margin-top-70">






					<div class="col-xs-12 ">
						<div class="panel panel-success">
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
											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">手机IP</div>
												<div class="  col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="phoneip" ng-model="phoneip" placeholder="">

												</div>
											</div>

											<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">路由器ID：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="routeid" ng-model="routeid" placeholder="">

												</div>
											</div>
										</div>
										<div class="hide row  form-group margin-bottom-5">
											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">路由器IP：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="ip" ng-model="device_ip" placeholder="">

												</div>
											</div>


											<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">出口城市：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="name" ng-model="city" placeholder="">

												</div>
											</div>


										</div>





									</div>



								</div>
							</div>
						</div>
					</div>


					<div class="col-xs-12 row ">
						<div class="table-responsive">
							<table class="table">
								<caption>{{title}}

									<div class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
										<button type="button" ng-click="getList()" class="btn btn-primary btn-block   ">查询</button>
									</div>
								</caption>
								<thead>
									<tr>
										<th>路由器ID</th>
										<th>手机IP</th>
										<th class="hide">路由器IP</th>
										<th class="hide">路由器ID</th>
										<th  class="hide">出口位置</th>
										<th>时间</th>

									</tr>
								</thead>
								<tbody>

									<tr ng-repeat="x in datalist">
										<td>{{ x.routeid }}</td>
										<td>{{ x.phoneip }}</td>
										<td class="hide">{{ x.route_ip }}</td>
										<td class="hide">{{ x.route_id }}</td>
										<td class="hide">{{ x.city }}</td>
										<td>{{ x.time }}</td>

									</tr>




								</tbody>
							</table>
						</div>
					</div>




					<div class="col-xs-12 row tablefoot" ng-if="page">


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

						<select onchange="changerows(this)" class="pull-right">
							<option ng-repeat="x in rows_select">{{x}}</option>
						</select>
					</div>



				</div>










				</script>

				<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>


				<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>




				<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
				<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


				<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>


				<script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script>

				<script src="../../js/menu.js "></script>

				<script src="../../js/loading.js"></script>

				<script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script>
				<script type="text/javascript" src="index.js"></script>


				<script>
					$(function () {
						$('#myModal').modal({
							keyboard: true
						})
					});


</body >
</html >