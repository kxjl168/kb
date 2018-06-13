<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
>
<%@include file="/common/tag.jsp"%>

<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
	<title>概览</title>
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
			<div class="navbar-header">
				<button type="button" id="menuBtn" class="navbar-toggle" data-toggle="collapse" data-target="#menuItem">
					<span class="sr-only"></span>
					<span class="glyphicon glyphicon-th-list  "></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">公司账号管理</a>
			</div>
			<div class="collapse navbar-collapse  pull-right-k" id="menuItem">
				<ul class="hide nav navbar-nav ">
					<li class=" bg-success">
						<a href="javascript:void(0)" ng-click="btnP()"> test</a>
					</li>

				</ul>
				<form class=" navbar-form navbar-left " role="search">


				</form>

				<ul id="menuul" class="nav navbar-nav ">
					<!-- <li class="active">
						<a href="#">{{title}}</a>
					</li>
					<li >
						<a href="#" ng-click="load('pccount')">手机账号管理</a>
					</li>
					
					<li >
						<a href="#" ng-click="load('device')">路由器管理</a>
					</li>
					<li >
						<a href="#" ng-click="load('translog')">流量日志</a>
					</li>
					
					
					<li>
						<a href="#" ng-click="load('set')">设置</a>
					</li>
					<li class="">
						<a href="">登出</a>
					</li> -->

				</ul>


			</div>
		</nav>




		<div class=" row-margin-top-70 row">

			<div class="menu hide">
				<div id="slides">
					<ul class="swiper-container" id="swiper_menu">
						<div class="swiper-wrapper" style="padding-top: 0px; padding-bottom: 0px; width: 320px; height: 50px; transform: translate3d(0px, 0px, 0px);">

						</div>
					</ul>
				</div>
			</div>



					

<div class="col-xs-12 ">
						<div class="panel panel-success row">
							<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" 
                href="#collapseOne">
							<div class="row">
								<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">查询条件</h3>
								
					
                
                	<span id="titlepic" data-toggle="collapse" data-parent="#accordion" 
                href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
                </div>
							</div>
							 <div id="collapseOne" class="panel-collapse collapse ">
							<div class="panel-body">

								<div class="container">


					
									<div class="row  form-group margin-bottom-5">
											<div class=" col-md-6  col-xs-12  ">
														<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">公司名称：</div>
														<div class="col-md-6 col-xs-8 text-right ">
															 <input type="text" class="form-control" id="name" ng-model="company_name" placeholder="">
				
														</div>
													</div>

												<div class=" col-md-6  col-xs-12  ">
														<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">公司账号ID：</div>
														<div class="col-md-6 col-xs-8 text-right ">
															 <input type="text" class="form-control" id="deviceid" ng-model="id" placeholder="">
				
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
										<div class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
											<button type="button" ng-click="addOrModify()"  class="btn btn-primary btn-block   ">新增公司</button>
										</div>
										<div class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
											<button type="button" ng-click="getList()" class="btn btn-primary btn-block   ">查询</button>
										</div>
									</caption>
									<thead>
										<tr>
										<th>公司名称</th>
											<th>公司账号</th>
											<th  class="hide">IP段</th>
											<th>出口IP刷新周期</th>
											<th>描述</th>
											<th>操作</th>
										</tr>
										
									</thead>
									<tbody>
									
					
										<tr ng-repeat="x in datalist">
											 <td>{{ x.company_name }}</td>
											 <td>{{ x.accountid }}</td>
											 <td  class="hide">{{ x.ip_desc }}</td>
											 <td>{{ x.ip_refresh_interval }}</td>
											 <td>{{ x.desc_info }}</td>
											 
											 	<td><a href="#"  class="text-info" ng-click="addOrModify(x)">修改</a>
											 	<a href="#"  class="text-info" ng-click="editproxysvr($event,x)">分配中转服务器IP段</a>
								
								<a href="#" class="text-warning" ng-click="del(x)">删除</a>
								
								
								</td>
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
										<li ng-repeat="x in pageDataPre" >
										
												<a    href="#" ng-click="getList(x)">{{x}}</a>
												
										</li>
											<li class="active">
							<a href="#" ng-click="getList(page)">{{page}}</a>
							</li>
										<li ng-repeat="x in pageDataAft" >
										
												<a    href="#" ng-click="getList(x)">{{x}}</a>
												
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
	<option ng-repeat="x in rows_select"  >{{x}}</option>
</select>
					</div>


		</div>

	</div>







	



	<div class="modal fade" data-backdrop="static" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">{{edit}}公司账号</h4>
				</div>



				<div class="modal-body container margin-top-10 " >
				<div class="row " >
					<form name="fm" id="fm" class="form-horizontal col-xs-12 col-lg-12 " style="min-width: 150px; ">
				

					
						<input  type="text " class="hide form-control " name="s_recordid" id="s_recordid" ng-model="s_recordid" placeholder=" ">
					
					
					<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">公司账号ID：</div>
							<div class="col-xs-7 text-right ">
							 <input required type="text" class="form-control" id="s_account" name="s_account" ng-model="s_account" placeholder="">
							 
							

							</div>
							
							<div class="col-xs-12 col-xs-offset-4 row ">
							<!--  <span ng-show="fm.s_account.$error.required">
							<span style="color:red" title="用户ID必须填写">
							*用户ID必须填写&nbsp;
							</span> 
							 </span> -->
							</div>
						</div>
						
					
					<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">密码：</div>
							<div class="col-xs-7 text-right ">
						
						
						 <input required type="text" class="form-control"  type="password" id="s_pass" name="s_pass" ng-model="s_pass" placeholder="">
							 
						
							</div>
							
							 <div class="col-xs-12 col-xs-offset-4 row ">
							<!--  <span ng-show="fm.s_pass.$error.required">
							<span style="color:red" title="密码必须填写">
							*密码必须填写&nbsp;
							</span> 
							 </span> -->
							</div>
						</div>
						
					
						<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">公司名称：</div>
							<div class="col-xs-7 text-right ">
								 <input type="text" required class="form-control"  id="s_company" name="s_company" ng-model="s_company" placeholder="">
						

							</div>
						</div>
						
						<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">描述：</div>
							<div class="col-xs-7 text-right ">
								 <textarea type="text" class="form-control"  id="name" ng-model="s_desc" placeholder="">
						</textarea>

							</div>
						</div>
						
						
						
						<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">出口IP刷新周期：</div>
							<div class="col-xs-7 text-right ">
								 <input required type="number" ng-pattern="/^\d*$/"  min="1" max="1000"  class="form-control"  id="s_ip_refresh" name= "s_ip_refresh" ng-model="s_ip_refresh" placeholder="">(分钟)
					
							</div>
							 <div class="col-xs-12 col-xs-offset-4 row  ">
							<!--  <span ng-show="fm.s_ip_refresh.$error.required">
							<span style="color:red" title="刷新周期必须填写">
							*刷新周期必须填写&nbsp;
							</span> 
							 </span>
							 
							  <span ng-show=" fm.s_ip_refresh.$invalid">
							<span style="color:red" title="刷新周期为1-1000的数字">
							*刷新周期为1-1000的数字&nbsp;
							</span> 
							 </span> -->
							</div>
						</div>

					
					</form>
					</div>
					
				</div>
				<div class="modal-footer ">
					<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
					</button>
					                 <button type="button " class="btn btn-primary " ng-click="update()" > 确定 </button>
				</div>
			</div>
		</div>
	</div>




<div class="modal fade" id="myModal3" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 250px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">确认操作</h4>
				</div>



				<div class="modal-body container margin-top-10 " ">
				<div class="row " >
					
					<p class="col-xs-10">确认执行操作吗？</p>
					
				</div>
				</div>
				<div class="modal-footer ">
					<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
					</button>
					                 <button id="btnconfirm" type="button " class="btn btn-primary " > 确定 </button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div  class="dtable  collapse   "  >
		<div class="panel " style="margin-bottom: 0px;">
							 <div  class=" collapseOne2 panel-collapse collapse ">
							<div class="panel-body" style="padding: 0px;">
								<table class="table table-responsive table-bordered" style="width:95%;margin-left: 10px;">
									<caption  title="隐藏中转服务器分配信息"  href=".collapseOne2 " data-toggle="collapse"   >
										 <div class="row">
										 <div class="col-xs-5 control-label">{{c}}已分配中转服务器及IP段</div>
										 <span id="titlepic"  data-parent="#accordion2"   style="color:#999;text-align: center;" class="control-label col-xs-2 glyphicon glyphicon-chevron-up  "></span>
										<div class=" col-lg-2 col-md-2 col-xs-4  margin-bottom-10 pull-right ">
											<button type="button"  onclick="addOrModifyProserver()"  class="btn btn-primary btn-block   ">新增服务器IP分配</button>
										</div>
										<div class="hide col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
											<button type="button" ng-click="getList()" class="btn btn-primary btn-block   ">查询</button>
										</div>
										 </div>
										 	
									</caption>
									
									<thead>
										<tr>
										<th>服务器ID</th>
											<th>服务器IP</th>
											<th>服务器端口</th>
											<th class="hide">最大可分配手机数</th>
											<th  class="hide">已分配手机数</th>
											<th>IP段</th>
											<th>操作</th> 
										</tr>
										
									</thead>
									<tbody>
									
					
										<tr ng-repeat="x in compayProxys">
										
										<td class="recordid hide" >{{ x.recordid }}</td>
									    
											 <td ng-show="x.rowspan" rowspan="{{x.rowspan}}"  class="id" >{{ x.id }}</td>
											 <td ng-show="x.rowspan" rowspan="{{x.rowspan}}"  class="ip">{{ x.ip }}</td>
											 <td ng-show="x.rowspan" rowspan="{{x.rowspan}}" >{{ x.port }}</td>
											 <td ng-show="x.rowspan" rowspan="{{x.rowspan}}"  class="hide">{{ x.maxphones }}</td>
											 <td ng-show="x.rowspan" rowspan="{{x.rowspan}}"  class="hide" >{{ x.assignphones }}</td>
											  <td class="desc">{{ x.ipdesc }}</td>
											 
										<td>
										<a href="#" class="text-info"  onclick="addOrModifyProserver(this)">编辑IP段</a>
								<a href="#" class="text-warning" onclick="delpro(this)">取消分配</a>
								
								</td>
										</tr>
									
									
									</tbody>
									
									
								
								</table>
								</div>
								</div>
							</div>
							</div>
						
	
	
	
	<div class="modal fade" id="myModal1"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">{{edit}}中转服务器及IP段分配</h4>
				</div>



				<div class="modal-body   container margin-top-10 " >
					<div class="row " >
					<form name="fmp" id="fmp" class="form-horizontal  col-xs-12" style="min-width: 150px; ">
				

					<input  type="text " class="hide form-control " name="p_record_id" id="p_record_id " ng-model="p_record_id" placeholder=" ">
						<input  type="text " class="hide form-control " name="p_company_id" id="p_company_id " ng-model="p_company_id" placeholder=" ">
					
					
						<div class="form-group row ">
							<div class="control-label padding-top-0 col-xs-3 ">中转服务器：</div>
							<div class="col-xs-6 text-right ">
								<select class="form-control "  id="p_proxyserver_id" name="p_proxyserver_id" ng-model="p_proxyserver_id">
									<option  ng-repeat="x in proxyservers " value="{{x.id}}">{{x.id}}-{{x.ip}}:{{x.port}}</option>
								</select>
							

							</div>
							<div class="col-xs-12 col-xs-offset-3 row hide">
							 <!-- <span ng-show="fmp.p_proxyserver_id.$error.required">
							<span style="color:red" title="">
							*中转服务器必须填写
							</span> 
							 </span> -->
							 </div>
							
						</div>
						
						<div class="form-group  row ">
							<div class="control-label padding-top-0 col-xs-3 ">IP段：</div>
							<div class="col-xs-6 text-right ">
								 <textarea required type="text"  
								  ng-pattern="/^\d+.\d+.\d+.\d+-\d+.\d+.\d+.\d+$/"  
								   class="form-control"  id="p_ip_desc"
								   name="p_ip_desc"
								    ng-model="p_ip_desc" placeholder="">
								 </textarea>
							</div>
							<div class="col-xs-12 col-xs-offset-3 row ">
							<!--  <span ng-show="fmp.p_ip_desc.$error.required">
							<span style="color:red" title="IP段必须填写">
							*IP段必须填写&nbsp;
							</span> 
							 </span>
							 <span ng-show="fmp.p_ip_desc.$invalid">
							<span style="color:red" title="">
							*IP段格式为:192.168.1.1-192.168.1.20&nbsp;
							</span> 
							 </span> -->
							</div>
						</div>
						
						
						
					</form>
					
					
						
						
					</div>
					
					
					
					
				</div>
				<div class="modal-footer">
					<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
					</button>
					                 <button type="button " class="btn btn-primary " ng-click="updateCompanyPro()" > 确定 </button>
				</div>
			</div>
		</div>
	</div>
	


</script>

	<script type="text/javascript "
		src="../../js/plugin/jquery/jquery.v1.11.3.js "></script>

<script type="text/javascript "
		src="../../js/plugin/jquery/jquery-ui.js "></script>

	<script type="text/javascript "
		src="../../js/plugin/angular/angular.min.js "></script>
	<script type="text/javascript "
		src="../../js/plugin/angular/angular-resource.min.js "></script>




	<script type="text/javascript "
		src="../../js/plugin/jquery/jquery.noty.min.js "></script>
	<script type="text/javascript "
		src="../../js/plugin/jquery/noty.layout.center.js "></script>
	<script type="text/javascript "
		src="../../js/plugin/jquery/noty.themes.bootstrap.js "></script>


	<script type="text/javascript "
		src="../../js/plugin/bootstrap/js/bootstrap.min.js "></script>


	<script type="text/javascript "
		src="../../js/plugin/swiper/idangerous.swiper.min.js "></script>

	
	<script src="../../js/loading.js "></script>
	
	
		<script src="../../js/menu.js "></script>
	
			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/kvalidate.js"></script>
	

	<script type="text/javascript "
		src="../../js/plugin/select2/select2.full.min.js "></script>
	<script type="text/javascript " src="index.js "></script>


<script>
	$(function () {
		$('#myModal').modal({
			keyboard: true
		})
	});


</body >
</html >