<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<!-- 模态框（Modal） -->
<div class="modal fade" data-backdrop="static" id="myModal_item"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				
				
				<h4 class="modal-title" id="myModal_itemLabel">
					<span id="myModal_item_title">添加</span>	评论
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="recordid" name="recordid">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label"></label>

										<div class="col-lg-9">
										<input type="text" name="content" 
											
											class="form-control" id="content"
												placeholder="" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">用户ID</label>

										<div class="col-lg-9">
										<input type="text" name="userid" 
											
											class="form-control" id="userid"
												placeholder="用户ID" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group hide">
										<label for="name" class="col-lg-3 control-label"></label>

										<div class="col-lg-9">
										<input type="text" name="createDate" 
											
											class="form-control" id="createDate"
												placeholder="" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">用户blog</label>

										<div class="col-lg-9">
										<input type="text" name="userBlog" 
											
											class="form-control" id="userBlog"
												placeholder="用户blog" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">通过审核</label>

										<div class="col-lg-9">
										<select class="form-control " id="state" >
								<option selected="selected" value="0">待审核</option>
								<option  value="1">已审核</option>
								</select>
										
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">用户Email</label>

										<div class="col-lg-9">
										<input type="text" name="email" 
											
											class="form-control" id="email"
												placeholder="" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">用户IP</label>

										<div class="col-lg-9">
										<input type="text" name="ip" 
											
											class="form-control" id="ip"
												placeholder="" >
											<p class="help-block"></p>
										</div>
									</div>
									










						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal"
						id="close">关闭</button>
					<button type="button" class="btn btn-warning" id="btnSubmit_item">
						提交更改</button>
				</div>
			</form>


		</div>
	</div>
</div>
