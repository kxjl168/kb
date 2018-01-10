<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>SEO日志</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
	


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
						<a class="navbar-brand" href="#">SEO日志</a>
					</div>
					<div class="collapse navbar-collapse pull-right-k " id="menuItem">
						
						

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
												<div class="control-label padding-top-0 col-xs-4  col-md-4 col-lg-4 ">爬虫标示：</div>
												<div class="  col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="head" ng-model="head" placeholder="">

												</div>
											</div>

											<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4  col-md-4 col-lg-4 ">URL：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="url" ng-model="url" placeholder="">

												</div>
											</div>
										</div>
											<div class="row  form-group margin-bottom-5">
										<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4 ">时间维度：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<select class="form-control " id="dateType" ng-model="dateType">
														<option ng-repeat="x in date_type2 " value="{{x.value}}">{{x.name}}</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row  form-group margin-bottom-5">
										
										
											
											<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4">开始时间：</div>
												<div class=" col-md-6  col-xs-8 text-right">
													<div class="input-group text-right  " style="">
					
														<input id="effectDate" readonly="readonly" name="effectDate" ng-model="effectDate" type="text" class=" form-control">
														<span class="input-group-addon " style="padding:0px 10px 0px 10px;">
															<button id="effectDate_img" title="选择时间" class="glyphicon glyphicon-calendar   "></button>
														</span>
														</input>
					
													</div>
												</div>
											</div>
					
											<div class=" col-md-6  col-xs-12  ">
													<div class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4">结束时间：</div>
													<div class="col-md-6 col-xs-8 text-right ">
														<div class="input-group text-right  " style="">
					
															<input id="effectDate2" readonly="readonly" name="effectDate2" ng-model="effectDate2" type="text" class=" form-control">
															<span class="input-group-addon " style="padding:0px 10px 0px 10px;">
																<button id="effectDate_img2" title="选择时间" class="glyphicon glyphicon-calendar   "></button>
															</span>
															</input>
					
														</div>
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
										<th>爬虫标示</th>
										<th>爬取URL</th>
										<th>请求IP</th>
										<th>所属地市</th>
										<th>时间</th>
										

									</tr>
								</thead>
								<tbody>

									<tr ng-repeat="x in datalist">
										<td>{{ x.spider_head }}</td>
										<td>{{ x.request_url }}</td>
										<td>{{ x.request_ip }}</td>
										<td>{{ x.request_city }}</td>
										<td>{{ x.city }}</td>
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
			


				<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
				<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


				<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>


			
			<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>
				
				<script type="text/javascript" src="index.js"></script>
				<script type="text/javascript" src="../stastic/time.js"></script>



</body >
</html >