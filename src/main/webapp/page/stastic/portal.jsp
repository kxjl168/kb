<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>访问统计</title>
			<!-- <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap-table/css/bootstrap-table.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
	 -->
		</head>

		<body >


			<div class="" id="content" style="">

				<!-- <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
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
								<li ><a href="#" ng-click="load('device')">设备状态</a></li>
										<li class="active"><a  href="#" >转发日志</a></li>
										<li class="hide"><a href="">登出</a></li>  

						</ul>


					</div>
				</nav>
 -->



				<div class="row row-margin-top-70">






					<div class="col-xs-12 ">
						<div class="panel panel-success">
							<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-target="#collapseOne">
								<div class="row">
									<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">查询条件</h3>
					
					
					
									<span id="titlepic"  class="glyphicon glyphicon-chevron-up pull-right "></span>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
					
									<div class="container">
					
					
					
										<div class="row  form-group margin-bottom-5">
											<div class="hide col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-4 col-lg-4 ">统计类型：</div>
												<div class="  col-md-6 col-xs-8 text-right ">
													<select class="form-control " id="s_type" ng-model="s_type">
														<option ng-repeat="x in types_select " value="{{x.dict_key}}">{{x.dict_name}}</option>
													</select>
												</div>
											</div>
					
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

<div id="sdata" class=" col-xs-4  margin-bottom-10 padding-right-0 ">
</div>
<div class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
										<button type="button" ng-click="getTList()" class="btn btn-primary btn-block   ">查询</button>
									</div>
		</div>
									
					<div class="col-xs-12 row ">
				
				
				<div  class="col-sm-6 row col-xs-12" >
				<div id="pchart1" data-options="region:'east'"
			style="height: 400px; width: 100%"></div>
				</div>
				
				<div  class="col-sm-6 row col-xs-12" >
				<div id="pchart2" data-options="region:'east'"
			style="height: 400px; width: 100%"></div>
				</div>
				
				<div  class="col-sm-6 row col-xs-12" >
				<div id="pchart3" data-options="region:'east'"
			style="height: 400px; width: 100%"></div>
				</div>
				
				<div  class="col-sm-6 row col-xs-12" >
				<div id="pchart4" data-options="region:'east'"
			style="height: 400px; width: 100%"></div>
				</div>
				
					<div  class="col-sm-6 row col-xs-12" >
				<div id="pchart5" data-options="region:'east'"
			style="height: 400px; width: 100%"></div>
				</div>
			
					
					
	</div>



</div>


<div class="modal fade" id="myModal1"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title " id="myModalLabel">{{detaildate}}访问地区分布</h4>
				</div>



				<div class="modal-body   container margin-top-10 " >
					<div class="row " >
					
					   <table class="table table-responsive table-bordered" style="width:95%;margin-left: 10px;">
									<caption  title=""  href=".collapseOne2 " data-toggle="collapse"   >
										 <div class="row">
										 <div class="col-xs-5 control-label"></div>
										 <span id="titlepic"  data-parent="#accordion2"   style="color:#999;text-align: center;" class="hide control-label col-xs-2 glyphicon glyphicon-chevron-up  "></span>
										
										 </div>
										 	
									</caption>
									
									<thead>
										<tr>
										<th>地区</th>
											<th>访问IP</th>
											<th>访问次数</th>
											 
										</tr>
										
									</thead>
									<tbody>
									
					
										<tr ng-repeat="x in detaillist">
										
										<td class="recordid " >{{ x.city }}</td>
									    
										
											  <td class="desc">{{ x.userid }}</td>
											  
											  
											  
											  <td class="desc" ng-bind-html="x.total_uv|sanitize"></td>
											 
										
										</tr>
									
									
									</tbody>
									
									
								
								</table>
								
								<div class="col-xs-12 row tablefoot" ng-if="page2">


						<ul class="pagination pull-right">
							<li>
								<a href="#" ng-click="getDetailList(page2-1)">&laquo;</a>
							</li>
							<li ng-repeat="x in pageDataPre2">

								<a href="#" ng-click="getDetailList(x)">{{x}}</a>

							</li>
							<li class="active">
								<a href="#" ng-click="getDetailList(page2)">{{page2}}</a>
							</li>
							<li ng-repeat="x in pageDataAft2">

								<a href="#" ng-click="getDetailList(x)">{{x}}</a>

							</li>
							<li>
								<a href="#" ng-click="getDetailList(page2+1)">&raquo;</a>
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

						<select onchange="changerows2(this)" class="pull-right">
							<option ng-repeat="x in rows_select">{{x}}</option>
						</select>
					</div>
					</div>
				</div>
				
					<div class="modal-footer">
					
					</button>
					                 <button type="button " class="btn btn-primary "  data-dismiss="modal" > 确定 </button>
				</div>
			</div>
		</div>
</div>





<div class="modal fade" id="myModal_detail"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title " id="myModalLabel">{{detaildate}}访问详情</h4>
				</div>



				<div class="modal-body   container margin-top-10 " >
					<div class="row " >
					
					   <div class="table-responsive" style="margin: 10px;">
										<table id="table_detail"
											class="table table-bordered table-hover"></table>
						</div>
						

					</div>
					
				</div>
				
					<div class="modal-footer">
					
					</button>
					                 <button type="button " class="btn btn-primary "  data-dismiss="modal" > 确定 </button>
				</div>
			</div>
		</div>
</div>






			<!-- 	<script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>


				<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>
 



				<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
				<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>


				<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>
				
				<script type="text/javascript" src="../../js/plugin/bootstrap-table/js/bootstrap-table.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>


				<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>
 
  -->
 <script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>
 
 				<script type="text/javascript" src="../../js/plugin/bootstrap-table/js/bootstrap-table.min.js"></script>
				<script type="text/javascript" src="../../js/plugin/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
 <script src="${basePath}/js/plugin/echart/echarts.js"></script>
 
			
			
			
				<script type="text/javascript" src="${basePath}/page/stastic/portal.js"></script>
				<script type="text/javascript" src="${basePath}/page/stastic/time.js"></script>
				<script type="text/javascript" src="${basePath}/page/stastic/chart.js"></script>




</body >
</html >