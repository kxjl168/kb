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
					<span id="myModal_item_title">添加</span>	文章许可
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">许可名称</label>

										<div class="col-lg-9">
										<input type="text" name="name" 
											
											class="form-control" id="name"
												placeholder="许可名称" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">许可链接</label>

										<div class="col-lg-9">
										<input type="text" name="link" 
											
											class="form-control" id="link"
												placeholder="许可链接" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">图标</label>

										<div class="col-lg-9">
										<div id="upimgs"></div>
										<input id="icon" type="hidden" />
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">备注</label>

										<div class="col-lg-9">
										<input type="text" name="remark" 
											
											class="form-control" id="remark"
												placeholder="备注" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group hide">
										<label for="name" class="col-lg-3 control-label">新建时间</label>

										<div class="col-lg-9">
										<input type="text" name="createDate" 
											  readonly="readonly"  
											
											class="form-control" id="createDate"
												placeholder="新建时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#createDate").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#createDate").data('datetimepicker')
						 .setDate(new Date());
                            });
                            </script>
									<div class="form-group hide">
										<label for="name" class="col-lg-3 control-label">更新时间</label>

										<div class="col-lg-9">
										<input type="text" name="updateDate" 
											  readonly="readonly"  
											
											class="form-control" id="updateDate"
												placeholder="更新时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#updateDate").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#updateDate").data('datetimepicker')
						 .setDate(new Date());
                            });
                            </script>










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
