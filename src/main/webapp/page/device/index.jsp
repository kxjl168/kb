<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	>
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>出口设备管理</title>
			<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css">



		</head>

		<body id="ngSection" ng-app="myApp" ng-controller="eduCtrl">




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
							<input id="key" type="text" class="form-control" placeholder="过滤设备">
							<span class="input-group-btn">
								<input type="button" class="btn btn-primary" ng-click="Search()" value="搜索"> </span>
						</div>
					</form>

					<ul class="nav navbar-nav " id="menuul">
						<!-- <li class="active"><a href="#">设备状态</a></li>
										<li><a  href="#" ng-click="load('log')">转发日志</a></li>
										<li class="hide"><a href="">登出</a></li>   -->

					</ul>


				</div>
			</nav>



			<div class="col-xs-12 row row-margin-top-70">








				<div class="panel panel-success">
					<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
						<div class="row">
							<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">查询条件</h3>



							<span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
						</div>
					
					</div>
					<div id="collapseOne" class="panel-collapse collapse ">
				<!-- 	<div id="collapseOne" class="panel-collapse collapse in"> -->
						<div class="panel-body">

							<div class="container">



								<div class="row  form-group margin-bottom-5">
									<div class=" col-md-6  col-xs-12 ">
										<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">位置：</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<input type="text" class="form-control" id="city" ng-model="city" placeholder="请输入位置">

										</div>
									</div>

									<div class=" col-md-6  col-xs-12  ">
										<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">设备ID：</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<input type="text" class="form-control" id="deviceid" ng-model="deviceid" placeholder="请输入设备ID">

										</div>
									</div>
								</div>
								<div class="row  form-group margin-bottom-5">
									<div class=" col-md-6  col-xs-12 ">
										<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">设备IP：</div>
										<div class="col-md-6 col-xs-8 text-right ">
											<input type="text" class="form-control" id="ip" ng-model="ip" placeholder="请输入设备IP">

										</div>
									</div>


	<div class=" col-md-6  col-xs-12  ">
	<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">状态：</div>
										<div class="col-md-6 col-xs-8  row">

												<select class="form-control " id="s_flag" ng-model="s_flag">
									<option  ng-repeat="x in flags " value="{{x.value}}">{{x.name}}</option>
								</select>
										</div>
										</div>

									<div class="hide  col-md-6  col-xs-12  ">
									
								
									
									
									
									
										<div class="hide control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">状态：</div>
										<div class="col-md-6 col-xs-8  row">

											<div class="control-label pull-left">
												<input type="checkbox" name="isonline" ng-click="getList()" ng-model="showall" class="ck" value="">在线
											</div>
										</div>

										<div class="hide col-md-6  col-xs-12  ">
											<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">用户名：</div>
											<div class="col-md-6 col-xs-8 text-right ">
												<input type="text" class="form-control" id="name" placeholder="请输入名称">

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
						<caption>
							{{title}}
							<div class=" hide col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
								<button type="button" data-toggle="modal" data-target="#myModal2" class="btn btn-primary btn-block   ">新增子账号</button>
							</div>
							<div class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
								<button type="button" ng-click="getList()" class="btn btn-primary btn-block   ">查询</button>
							</div>
						</caption>
						<thead>
							<tr>
								<th>ID</th>
								<th>IP</th>
								<th>出口位置</th>
								<th>上线时间</th>
								
								<th>分配状态</th>
								<th>是否空闲</th>
								
								
								<th ng-show="!showall">下线时间</th>
							</tr>

						</thead>
						<tbody>



							<tr ng-repeat="x in datalist">
								<td>{{ x.routeid }}</td>
								<td>{{ x.ip }}</td>
								<td>{{ x.city }}</td>
								<td>{{ x.onlinetime }}</td>
									<td>{{ x.flagtype }}</td>
										<td>{{ x.freetype }}</td>
								
								
								<td ng-show="!showall">{{ x.offlinetime }}</td>
							</tr>


						</tbody>
					</table>
				</div>
			</div>



			<div class="col-xs-12 row tablefoot">


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
				</ul>



				<select onchange="changerows(this)" class="pull-right">
					<option ng-repeat="x in rows_select">{{x}}</option>
				</select>
			</div>







			</div>












			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:500px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">新增子账号</h4>
						</div>



						<div class="modal-body container margin-top-10 ">
							<div class="row ">
								<form class="form-horizontal col-xs-12 col-lg-12 " style="min-width: 150px; ">





									<div class="form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-3 ">用户名：</div>
										<div class="col-xs-8 text-right ">
											<div class="input-group ">
												<span class=" input-group-addon " title="用户 ">
													<i class="glyphicon glyphicon-user "></i>
												</span>
												<input type="text " name="username " id="username " class="form-control " placeholder="用户 ">
											</div>

										</div>
									</div>


									<div class="form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-3 ">密码：</div>
										<div class="col-xs-8 text-right ">
											<div class="input-group ">
												<span class=" input-group-addon ">
													<i class=" glyphicon glyphicon-lock "></i>
												</span>
												<input type="password " name="password " id="password " class="form-control " placeholder="密码 ">
											</div>
										</div>
									</div>

									<div class="form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-3 ">确认密码：</div>
										<div class="col-xs-8 text-right ">
											<div class="input-group ">
												<span class=" input-group-addon ">
													<i class=" glyphicon glyphicon-lock "></i>
												</span>
												<input type="password " name="password2 " id="password2 " class="form-control " placeholder="确认密码 ">
											</div>
										</div>
									</div>



									<div class="form-group col-xs-12 row ">
										<div class="control-label padding-top-0 col-xs-3 ">选择位置：</div>
										<div class="col-xs-8 text-right ">
											<select class="form-control " onchange=" " ng-model="cpTypes" id="cpType2 " name="cpType2 ">
											</select>
										</div>
									</div>




								</form>
							</div>

						</div>
						<div class="modal-footer ">
							<button type="button " class="btn btn-default btn-warning " data-dismiss="modal ">取消
							</button>
							<button type="button " class="btn btn-primary " data-dismiss="modal "> 确定 </button>
						</div>
					</div>
				</div>
			</div>


			</script>

			<script type="text/javascript " src="../../js/plugin/jquery/jquery.v1.11.3.js "></script>


			<script type="text/javascript " src="../../js/plugin/angular/angular.min.js "></script>
			<script type="text/javascript " src="../../js/plugin/angular/angular-resource.min.js "></script>




			<script type="text/javascript " src="../../js/plugin/jquery/jquery.noty.min.js "></script>
			<script type="text/javascript " src="../../js/plugin/jquery/noty.layout.center.js "></script>
			<script type="text/javascript " src="../../js/plugin/jquery/noty.themes.bootstrap.js "></script>


			<script type="text/javascript " src="../../js/plugin/bootstrap/js/bootstrap.min.js "></script>


			<script type="text/javascript " src="../../js/plugin/swiper/idangerous.swiper.min.js "></script>

			<script src="../../js/menu.js "></script>

			<script src="../../js/loading.js "></script>

			<script type="text/javascript " src="../../js/plugin/select2/select2.full.min.js "></script>
			<script type="text/javascript " src="index.js "></script>


			<script>
				$(function () {
					$('#myModal').modal({
						keyboard: true
					})
				});


</body >
</html >