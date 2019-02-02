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
					<span id="myModal_item_title">添加</span>	Rss订阅
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">





									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">Rss链接</label>

										<div class="col-lg-9">
										<input type="text" name="link" 
											
											class="form-control" id="link"
												placeholder="Rss链接" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">名称</label>

										<div class="col-lg-9">
										<input type="text" name="mName" 
											
											class="form-control" id="mName"
												placeholder="名称" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">备注</label>

										<div class="col-lg-9">
										<input type="text" name="mRemark" 
											
											class="form-control" id="mRemark"
												placeholder="备注" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">订阅类型</label>

										<div class="col-lg-9">
										
										<select class="form-control " name="autoRss" id="autoRss" >
								<option selected="selected" value="1">自动订阅</option>
								<option  value="0">手动订阅</option>
								</select>
										
									
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
