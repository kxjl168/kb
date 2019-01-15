<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@include file="/common/tag.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>权限菜单管理</title>
<link rel="stylesheet" href="${basePath }/js/plugin/ztree/zTreeStyle.css">
</head>

<body>





	<div>



		<div class="row">
		
		
		
		
			<div class="col-lg-12">
				<div class="panel panel-default panel-success pshodow">
					<div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								<div class="row">
									<h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">查询条件</h3>



									<span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
								</div>
							</div>
				
					<div id="collapseOne" class="panel-collapse collapse in panel-body">

						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row ">
								<div class=" col-sm-9">
									<div class="form-group">
										<label for="name" class="lb_text col-lg-5 control-label">权限菜单名称:</label>

										<div class="col-lg-7">
											<input id="q_name" type="text" name="q_name"
												class="form-control " placeholder=""
												aria-controls="dataTables-example">
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="lb_text col-lg-5 control-label">父菜单:</label>

										<div class="col-lg-7">
										<input id="q_pid" type="text" name="q_pid" readonly="readonly"
												class="form-control " placeholder=""
												aria-controls="dataTables-example">
																							
												
										<div id="menuContent" class="help-block menuContent" style="display:none; position: absolute;">
										<span id="cleanselect" class="cleanbtn">清除选择</span>
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
										
									
													
										<!-- 	<input id="q_pid" type="text" name="q_pid"
												class="form-control " placeholder=""
												aria-controls="dataTables-example"> -->
										</div>
									</div>
									 
								</div>
								
								<div class="col-sm-3 ">
									<button type="button" class="btn btn-default btn-query zxys" id="btnQry"
										onclick="doquery()">查询</button>

									<button type="button" class="btn btn-default btn-add" id="btnAdd">新增</button>
								</div>
							</div>

							

						</div>
					</div>


				</div>
			</div>
			
			
				<div class="col-xs-12 row permission">
						<div class="table-responsive">
							<table class="table"  id="table_list">
							
							</table>
							</div>
				</div>
		</div>

		<!-- 模态框（Modal） -->
		<div class="modal fade in"  data-backdrop="static" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							权限菜单<span id="faction">编辑</span><span id="message" style="margin-left: 20px;"></span>
						</h4>

					</div>

					<form id="mform" name="mform" class="form-horizontal" role="form"
						action="" method="post">
						
						<div class="modal-body">
							<div class="row">

								<div class="col-lg-12">


									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单ID</label>

										<div class="col-lg-9">
											<input type="text" name="menuId" class="form-control" id="menuId"
												placeholder="权限菜单ID" readonly="readonly">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单名称</label>

										<div class="col-lg-9">
											<input type="text" name="menuName" class="form-control" id="menuName"
												placeholder="权限菜单名称">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单类型</label>

										<div class="col-lg-9">
											<select name="type" class="form-control" id="type">

												<option value="1">一级菜单</option>
												<option value="2"  selected="selected">二级菜单</option>
												<option value="3"  >按钮</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">URL</label>

										<div class="col-lg-9">
											<input type="text" name="menuUrl" class="form-control" id="menuUrl"
												placeholder="URL">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group hide">
										<label for="name" class="col-lg-3 control-label">权限代码</label>

										<div class="col-lg-9">
											<input type="text" name="percode" class="form-control"
												id="percode" placeholder="权限代码">
											<p class="help-block"></p>
												<p class="tip-block small text-success">示例:action:view</p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">父菜单ID</label>

										<div class="col-lg-9">
										<!-- 	<input type="text" name="parentid" class="form-control"
												id="parentid" placeholder="父菜单ID"> -->
												<select name="menuParentid" class="form-control" id="menuParentid">
												</select>
											<p class="help-block"></p>
										
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">排序号</label>

										<div class="col-lg-9">
											<input type="text" name='menuOrderid' class="form-control"
												id="menuOrderid" placeholder="排序号">
											<p class="help-block"></p>
										</div>
									</div>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">是否可见</label>

										<div class="col-lg-9">

											<select name="available" class="form-control" id="available">

												<option value="1"  selected="selected" >可见</option>
												<option value="0">不可见</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>







								</div>

							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-info"
								data-dismiss="modal" id="close">关闭</button>
							<button type="button" class="btn btn-primary btn-warning" id="btnSubmit">
								提交更改</button>
						</div>
					</form>


				</div>
			</div>
		</div>
		
		

			<script src="${basePath }/js/plugin/ztree/jquery.ztree.all.min.js"></script>
		<script src="${basePath }/page/permission/permission.js"></script>
</body>
</html>