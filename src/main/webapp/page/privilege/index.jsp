<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	>
	<%@include file="/common/tag.jsp"%>

		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<title>角色管理</title>
			<!-- <link rel="stylesheet" type="text/css" media="screen" href="../../js/plugin/bootstrap/css/bootstrap.min.css">

			<link rel="stylesheet" href="../../css/kCommon.css">

			<link rel="stylesheet" href="../../css/zcfg.css">
			<link rel="stylesheet" href="../../css/common.css">
			<link rel="stylesheet" href="../../css/swiper_zcfg.css">
			<link rel="stylesheet" href="../../js/plugin/swiper/idangerous.swiper.css">-->
			<link rel="stylesheet" href="../../js/plugin/ztree/zTreeStyle.css"> 



		</head>

		<body >


			<div class="" id="content" style="">

				<!-- <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
					<div class="navbar-header">
						<button type="button" id="menuBtn" class="navbar-toggle" data-toggle="collapse" data-target="#menuItem">
							<span class="sr-only"></span>
							<span class="glyphicon glyphicon-th-list  "></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">{{title}}</a>
					</div>
					<div class="collapse navbar-collapse  pull-right-k" id="menuItem">
						<ul class="hide nav navbar-nav ">
							<li class=" bg-success">
								<a href="javascript:void(0)" ng-click="btnP()"> test</a>
							</li>

						</ul>
						<form class=" navbar-form navbar-left " role="search">


						</form>

						<ul class="nav navbar-nav " id="menuul">
							<li>
						<a href="#" ng-click="load('company')">公司账号管理</a>
					</li>
					<li class="active">
						<a href="#">{{title}}</a>
					</li>
					<li>
						<a href="#" ng-click="load('set')">设置</a>
					</li>
					<li class="">
						<a href="">登出</a>
					</li>

						</ul>


					</div>
				</nav> -->




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


											<div class=" col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">角色ID：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="role_id" ng-model="role_id" placeholder="">

												</div>
											</div>

											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">角色名称：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="role_name" ng-model="role_name" placeholder="">

												</div>
											</div>
										</div>
										<div class=" hide row  form-group margin-bottom-5">
											<div class=" col-md-6  col-xs-12 ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3 ">位置：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="city" ng-model="city" placeholder="">

												</div>
											</div>

											<div class="hide col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">公司名称：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="name" ng-model="compy_name" placeholder="">

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
										<button type="button" ng-click="addOrModify()" class=" btn btn-primary btn-block  ">新增</button>
									</div>
									<div class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
										<button type="button" ng-click="getList()" class="btn btn-primary btn-block   ">查询</button>
									</div>
								</caption>
								<thead>
									<tr>
										<th>角色名称</th>
										<th>角色ID</th>
										<th>角色说明</th>
										
										<th>操作</th>
									</tr>

								</thead>
								<tbody>

									
									<tr ng-repeat="x in datalist">
										<td>{{ x.role_zh }}</td>
										<td>{{ x.role_en }}</td>
										<td>{{ x.role_desc }}</td>
										
										<td>
											<a href="#" class="text-info" ng-click="addOrModify(x)">修改</a>
											<a href="#" class="text-warning" ng-click="del(x)">删除</a>

										</td>
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

			</div>







			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">条件选择</h4>
						</div>



						<div class="modal-body">
							<div class="row" ng-show="selType==1">
								<div class="form-group col-xs-12">
									<div class="control-label col-xs-4">选择位置</div>
									<div class="col-xs-8 text-right">
										<select class="form-control" onchange="" ng-model="cpTypes" id="cpType" name="cpType">
										</select>
									</div>
								</div>
							</div>
							<div class="row" ng-show="selType==2">
								<div class="form-group col-xs-12">
									<div class="control-label col-xs-4">是否公开</div>
									<div class="col-xs-8 text-center">
										<div class="info_value">
											<input type="radio" value="1" onclick="selectPublic(1)" name="IsPublic" ng-model="cp.IsPublic" ng-checked="cp.IsPublic=='1'">是
											<input type="radio" value="0" onclick="selectPublic(0)" name="IsPublic" ng-model="cp.IsPublic" ng-checked="cp.IsPublic=='0'">否
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="row" ng-show="selType==3">
                    <div class="form-group col-xs-12">
                        <div class="control-label col-xs-4">部门</div>
                        <div class="col-xs-8 text-center">
                            <div class="info_value">
                                <div id="menuContent" class="menuContent">
                                    <ul id="depTree" class="ztree" style="margin-top:0; width:160px;"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-warning" data-dismiss="modal">取消
							</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal"> 确定 </button>
						</div>
					</div>
				</div>
			</div>



			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">{{edit}}角色</h4>
						</div>



						<div class="modal-body container margin-top-10 " >
				<div class="row " >
					<form name="fm" id="fm" class="form-horizontal col-xs-12 col-lg-12 " style="min-width: 150px; ">
				

					<input   type="text" class="hide form-control" name="s_recordid" id="s_recordid" ng-model="s_recordid" placeholder=" ">
					
					
					<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">角色ID：</div>
							<div class="col-xs-7 text-right ">
							 <input required 
							 class=" form-control"
										
							  type="text" name="s_roleid" id="s_roleid" ng-model="s_roleid" placeholder=" ">
							 </div>
							 <div class="col-xs-1 text-right ">
							<!--  <span ng-show="fm.s_roleid.$error.required ">
							<span style="color:red " title="角色ID必须填写 ">
							<span class="glyphicon glyphicon-remove "></span>
							</span> 
							 </span> -->
							</div>
						</div>
						
					
					<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">角色名称：</div>
							<div class="col-xs-7 text-right ">
						
						
						 <input required class="form-control "  type="text" name="s_rolename" id="s_rolename" ng-model="s_rolename" placeholder=" ">
						
							</div>
							 <div class="col-xs-1 text-right ">
							<!--  <span ng-show="fm.s_rolename.$error.required ">
							<span style="color:red " title="角色名称必须填写 ">
							<span class="glyphicon glyphicon-remove "></span>
							</span> 
							 </span> -->
							</div>
						</div>

						<div class="form-group col-xs-12 row ">
								<div class="control-label padding-top-0 col-xs-4 ">描述：</div>
								<div class="col-xs-7 text-right ">
							 <textarea type="text" class="form-control"  id="name" ng-model="s_desc" placeholder="">
						</textarea>
							</div>
							 <div class="hide col-xs-1 text-right ">
							<!--  <span ng-show="fm.s_ip_refresh.$error.required ">
							<span style="color:red " title="刷新周期必须填写 ">
							<span class="glyphicon glyphicon-remove "></span>
							</span> 
							 </span> -->
							 
							  <span ng-show=" fm.s_ip_refresh.$invalid ">
							<span style="color:red " title="刷新周期为大于9的数字 ">
							<span class="glyphicon glyphicon-remove "></span>
							</span> 
							 </span>
							</div>
							</div>
					


						<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-4 ">可见菜单项：</div>
						
							<div class="col-xs-7 text-right ">
						
							<span style="margin: 5px;"> 
                 <input id="selectAll"  name="app" type="checkbox"
              
                  attr="" value="" class="r_hide">
                 <span style=";margin-top:5px; vertical-align: middle;" class="WellCheckBox WellCheckBoxH"></span>
			     <label  style="margin-left:5px; margin-right:5px;" class="WellCheckname ">全选</label>
										
										<div id="treeDemo" style="width: 90%; height: 80%" class="ztree"></div>	
							</div>
							
							
							
						</div>
						
						

					
					</form>
					</div>
					
				</div>
				<div class="modal-footer ">
					<button type="button " class="btn btn-default btn-warning"  data-dismiss="modal">取消
					</button>
					                 <button type="button " class="btn btn-primary" ng-click="update()"> 确定 </button>
				</div>
			</div>
		</div>
	</div>



<div class="modal fade " id="myModal3" tabindex="-1 " role="dialog " aria-labelledby="myModalLabel " aria-hidden="true ">
		<div class="modal-dialog " style="width: 250px; ">
			<div class="modal-content ">
				<div class="modal-header ">
					<button type="button " class="close " data-dismiss="modal " aria-hidden="true ">&times;</button>
					<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
				</div>



				<div class="modal-body container margin-top-10 " ">
							<div class="row ">

								<p class="col-xs-10">确认执行操作吗？</p>

							</div>
						</div>
						<div class="modal-footer ">
							<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
							</button>
							<button id="btnconfirm" type="button " class="btn btn-primary "> 确定 </button>
						</div>
					</div>
				</div>
			</div>

			
			<!-- <script type="text/javascript" src="../../js/plugin/jquery/jquery.v1.11.3.js"></script>

			<script type="text/javascript" src="../../js/plugin/bootstrap/js/bootstrap.min.js"></script>

			<script type="text/javascript" src="../../js/plugin/jquery/jquery-ui.js"></script>



			<script type="text/javascript" src="../../js/plugin/angular/angular.min.js"></script>
		

			    <script type="text/javascript"
	src="../../js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	

				<script src="../../js/own/menu.js"></script>
<script src="../../js/own/loading.js"></script>

			<script type="text/javascript" src="../../js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="../../js/plugin/jquery/noty.themes.bootstrap.js"></script>




			<script type="text/javascript" src="../../js/plugin/swiper/idangerous.swiper.min.js"></script> -->
		
			<script type="text/javascript" src="../../js/plugin/ztree/jquery.ztree.all.min.js"></script>

		<script type="text/javascript" src="../../js/plugin/angular/angular-resource.min.js"></script>

	
			<script type="text/javascript" src="../../js/plugin/select2/select2.full.min.js"></script>
			<script type="text/javascript" src="index.js"></script>




</body >
</html >