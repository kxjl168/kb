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
					<span id="myModal_item_title">添加</span>	IP黑名单
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">IP地址</label>

										<div class="col-lg-9">
										<input type="text" name="ip" 
											
											class="form-control" id="ip"
												placeholder="IP地址" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group hide">
										<label for="name" class="col-lg-3 control-label">时间</label>

										<div class="col-lg-9">
										<input type="text" name="dtime" 
											  readonly="readonly"  
											
											class="form-control" id="dtime"
												placeholder="时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#dtime").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#dtime").data('datetimepicker')
						 .setDate(new Date());
                            });
                            </script>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">描述</label>

										<div class="col-lg-9">
										<input type="text" name="desc" 
											
											class="form-control" id="desc"
												placeholder="描述" >
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
