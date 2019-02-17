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
					<span id="myModal_item_title">添加</span>	收支记录
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">




								   <div class="form-group">
										<label for="name" class="col-lg-3 control-label">收支类型</label>

										<div class="col-lg-9">
										<select name="inOut" class="form-control" id="inOut">

										<option value="1" >收入</option>
										<option value="2" selected="selected">支出</option>
										
									</select>
										
											<p class="help-block"></p>
										</div>
									</div>
									

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">金额</label>

										<div class="col-lg-9">
										<input type="text" name="money" 
											
											class="form-control" id="money"
												placeholder="金额" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">分类</label>

										<div class="col-lg-9">
										<select  name="mType"  style="width: auto"
											
											class="form-control col-xs-12" id="mType"
												placeholder="分类" ></select>
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">日期</label>

										<div class="col-lg-9">
										<input type="text" name="mDate"  readonly="readonly"
											
											class="form-control" id="mDate"
												placeholder="日期 " >
											<p class="help-block"></p>
										</div>
									</div>
									
								<script>	
                           
        						
       						
                            </script>
									
									
									
									
									
									
									
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">备注</label>

										<div class="col-lg-9">
										<input type="text" name="remark" 
											
											class="form-control" id="remark"
												placeholder="备注" >
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
