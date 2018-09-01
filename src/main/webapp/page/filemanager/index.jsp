<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>

	<html>	
		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<title>附件管理</title>
<!-- 			<link rel="stylesheet" type="text/css" media="screen" href="${basePath }/js/plugin/bootstrap/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css" media="screen" href="${basePath }/js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">

			<link rel="stylesheet" href="${basePath }/css/kCommon.css">

			<link rel="stylesheet" href="${basePath }/css/zcfg.css">
			<link rel="stylesheet" href="${basePath }/css/common.css">
			<link rel="stylesheet" href="${basePath }/css/swiper_zcfg.css">
			<link rel="stylesheet" href="${basePath }/js/plugin/swiper/idangerous.swiper.css">

 -->
 <link rel="stylesheet" href="${basePath}/js/own/FileUploadMuti.css">

		</head>

		<body >


			<div class="" id="content" style="">

				




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
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">附件名称：</div>
												<div class="col-md-6 col-xs-8 text-right ">
													<input type="text" class="form-control" id="q_name" ng-model="q_name" placeholder="">

												</div>
											</div>
											
											<div class=" hide col-md-6  col-xs-12  ">
												<div class="control-label padding-top-0 col-xs-4 col-md-3 col-lg-3">账号名称：</div>
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
										<button type="button" ng-click="addOrModify()" class="btn btn-primary btn-block   ">新增附件</button>
									</div>
									<div class=" col-lg-2 col-md-2 col-xs-4 pull-right margin-bottom-10 padding-right-0 ">
										<button type="button" ng-click="getList()" class="btn btn-primary btn-block   ">查询</button>
									</div>
								</caption>
								<thead>
									<tr>
										<th>id</th>
											<th class="" >附件原名</th>
										<th class="" >下载链接</th>
										
									
										<th class="">下载次数</th>
										<th class="">相对路径</th>
										<th class="">上传日期</th>
										
										<th>操作</th>
									</tr>

								</thead>
								<tbody>


									<tr ng-repeat="x in datalist">
										<td>{{ x.id }}</td>
											<td>{{x.old_name}}</td>
										 
										
											<td class=""><a href="${basePath}{{ x.http_down_url}}">${basePath}{{ x.http_down_url}}</a></td>
												<td class="">{{ x.down_nums}}</td>
										<td class=""><a href="{{prepath}}{{ x.http_relative_path}}">{{prepath}}{{ x.http_relative_path}}</a></td>
										<td class="">{{ x.save_date}}</td>
										
										<td>
											<a href="#" class="hide text-info" ng-click="addOrModify(x)">修改</a>
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







			



			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">{{edit}}附件</h4>
						</div>



						<div class="modal-body container margin-top-10  ">
				<div class="row " >
					<form name="fm" id="fm" class="form-horizontal col-xs-12 col-lg-12 " style="min-width: 150px; ">
				

					<input   type="text " class="hide form-control " name="s_recordid" id="s_recordid" ng-model="s_recordid" placeholder=" ">
					
				
						
						<div class="form-group col-xs-12 row ">
							<div class="control-label padding-top-0 col-xs-3 ">附件：</div>
							<div class="col-xs-9 text-right ">
								<div id="filedv"></div>
							</div>
						
						
						</div>
						
						
						

					
					

					
					</form>
					</div>
					
				</div>
				<div class="modal-footer ">
					<button type="button " class="hide btn btn-default btn-warning " data-dismiss="modal">取消
					</button>
					                 <button type="button " class="btn btn-primary " ng-click="update() "> 确定 </button>
				</div>
			</div>
		</div>
	</div>


<div class="modal fade " id="myModal1" tabindex="-1 " data-backdrop="static" role="dialog " aria-labelledby="myModalLabel " >
		<div class="modal-dialog " style="width: 350px; ">
			<div class="modal-content ">
				<div class="modal-header ">
					<button type="button " class="close hide " data-dismiss="modal " aria-hidden="true ">&times;</button>
					<h4 class="modal-title " id="myModalLabel ">选择文件</h4>
				</div>
				
				<input type="hidden" id="fileSvrPath" ng-model="fileSvrPath" />
	

	
				 <input id="md5" name="md5"
						ng-model="md5" type="hidden" /> 
			
	 <input id="url" name="url"
					value="{{url}}" type="hidden" /> 
						 <input id="val2" value="{{val2}}" name="val2"
						type="hidden" /> 
			

			<iframe id="fileUploadFrame" name="fileUploadFrame" src=""
			frameborder="0" hspace="0" height="0" width="0"></iframe>
		
			
	
		
				<div class="modal-body container margin-top-10 " >
				
					<form method="post" id="fileform" name="fileform"
			target="fileUploadFrame" enctype="multipart/form-data">
				
						<div class="row col-xs-12">
								<div class="control-label padding-top-0  ">图标：</div>
							</div>
						<div class="row col-xs-11">
						
						   <div class="col-xs-10 text-right ">
							 <input  required
							 class=" form-control"
											
							  type="file" name="fileUploadURL" id="fileUploadURL" ng-model="fileUploadURL" placeholder=" ">
						   </div>
							 <div class="col-xs-2 text-right ">
							   <button type="button " class="btn btn-primary" name="uploadSub" id="uploadSub" ng-click="upload"> 上传 </button>
							 </div>

						</div>
							
						<div class="row col-xs-12 hide">
							 <img 
							src="<c:out value="${basePath}"/>/images/versionmanage/busy.gif" id="uploadState" />
						</div>
						
				</form>
				
				</div>
				
						<div class="modal-footer ">
							<button type="button " class="btn btn-default btn-warning " data-dismiss="modal">取消
							</button>
							<button  type="button " class=" hide btn btn-primary "  > 确定 </button>
						</div>
					</div>
				</div>
			</div>


<div class="modal fade " id="myModal3" data-backdrop="static" tabindex="-1 " role="dialog " aria-labelledby="myModalLabel " aria-hidden="true ">
		<div class="modal-dialog " style="width: 250px; ">
			<div class="modal-content ">
				<div class="modal-header ">
					<button type="button " class="close " data-dismiss="modal" aria-hidden="true ">&times;</button>
					<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
				</div>



				<div class="modal-body container margin-top-10 " >
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

			
			
			<script type="text/javascript" src="${basePath }/js/plugin/angular/angular-resource.min.js"></script>		
			<script type="text/javascript" src="${basePath }/js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>
			<script type="text/javascript" src="${basePath }/js/plugin/select2/select2.full.min.js"></script>
<%-- 
			<script type="text/javascript" src="${basePath }/js/plugin/jquery/jquery.v1.11.3.js"></script>

			


			<script type="text/javascript" src="${basePath }/js/plugin/angular/angular.min.js"></script>
		




			<script type="text/javascript" src="${basePath }/js/plugin/jquery/jquery.noty.min.js"></script>
			<script type="text/javascript" src="${basePath }/js/plugin/jquery/noty.layout.center.js"></script>
			<script type="text/javascript" src="${basePath }/js/plugin/jquery/noty.themes.bootstrap.js"></script>

		

			    <script type="text/javascript"
	src="${basePath }/js/plugin/jquery/jquery.validate.js"></script> 

		<script type="text/javascript"
	src="${basePath}/js/own/kvalidate.js"></script>
	

			<script type="text/javascript" src="${basePath }/js/plugin/swiper/idangerous.swiper.min.js"></script>
				<script src="${basePath }/js/own/menu.js"></script>
<script src="${basePath }/js/own/loading.js"></script>

			
			 --%>
			<script type="text/javascript" src="${basePath }/page/filemanager/index.js"></script>
		<script type="text/javascript"
	src="${basePath}/js/own/FileUploadMuti.js"></script>


	
</script>

</body>
</html>