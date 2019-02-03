<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<!-- 模态框（Modal） -->
<div class="modal fade" data-backdrop="" id="myModal_item"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content ">
			<div class="modal-header row">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>


				<h4 class="modal-title" id="myModal_itemLabel">
					<span id="myModal_item_title">添加</span> Rss文章
				</h4>

			</div>



			<div class="modal-body  row">
				<div class="rsspagedetail col-xs-12">
					
						<div class="col-lg-12 row ">

							<input type="hidden" id="id" name="id">
								<div class="form-group">


									<div class="col-xs-12">
										<h3 type="text" name="title" class="" id="title"
											placeholder="文章标题">
									</div>
									<p class="help-block"></p>
									</h3>

									<div class="col-xs-12">
										<p type="text" name="updateDate" class="" id="updateDate"
											placeholder="更新时间">
									</div>

									</p>

									<div class="col-xs-12">
										<a type="text" target="_blank" name="link" class="" id="link"
											placeholder="文章链接"></a>
									</div>
								</div> <br></br>

								<div class="form-group">
									<label for="name" class="col-lg-3 hide control-label">文章内容</label>

									<div class="col-xs-12">
										<div type="text" name="context" class="" id="context"
											placeholder="文章内容"></div>
										<p class="help-block"></p>
									</div>
								</div>
						</div>



				</div>
			</div>




			<div class=" modal-footer row " style="">
				<button type="button" class="btn btn-info" data-dismiss="modal"
					id="close">关闭</button>
				<button type="button" class="hide btn btn-warning"
					id="btnSubmit_item">提交更改</button>
			</div>
		</div>


	</div>
</div>
