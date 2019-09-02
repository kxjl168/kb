<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<div class="row col-xs-12" id="commet">
	<hr></hr>
</div>
<div ng-cloak>


	<!--   <div ng-repeat="x in rdatalist" id="cblock"> -->


	<div id="cblock">
		<div class="hide row col-lg-10 replayblock b1">

			<div class="pull-left">
				<a id="f91" href="https://ddd.com" onclick="return gourl(this);"
					title="大虎-https://ddd.com"> <img class="rheadpic"
					src="https://www.gravatar.com/avatar/b50114d6569e96528aeca04430fe0a09?s=55&amp;r=pg&amp;d=identicon">
				</a>
			</div>

			<div class="col-xs-10">
				<div>
					<span>大虎</span><span class="rptime text-right">&nbsp;2019-01-13
						17:26:45 </span>
				</div>
				<div class="rcct">好战的一部，好！！</div>
				<div class="row  margin-bottom-5">
					<a href="#f91" class="ba1 replaybtn  text-info margin-right-20"
						onclick="beginReplay(this,'91')">回复</a>
				</div>
				<div class=" rcblock"></div>

			</div>

		</div>
		<!-- <div class="row col-xs-12 ">

                <div class="row col-lg-10 replayblock b1">


                    <div class="hide" id="f{{rnum-$index}}">{{rnum-$index}}楼&nbsp;<a href="{{x.user_blog}}" title="{{x.user_blog}}">{{x.userid}}</a> <span class="pull-right text-right">&nbsp;{{x.create_date}}</span> </div>

                    <div id="f{{rnum-$index}}"><a ng-href="{{x.user_blog}}"   onclick="return gourl(this);"  title="{{x.user_blog}}">{{x.userid}}  </a> <span class=" text-right">&nbsp;{{x.create_date}} </span>
                    
                    <span  ng-show="root">【  <a  ng-show="x.state!=1" href="#f{{rnum-$index}}" class="   text-success margin-right-20" onclick="pass(this,'{{x.recordid}}')">审核通过</a>&nbsp;
                                       <a href="#f{{rnum-$index}}" class="   text-warning margin-right-20" onclick="del(this,'{{x.recordid}}')">删除</a>】
                                       </span>
                    </div>

                    <div class="rcct">{{x.content}}</div>
                    <div class="row  margin-bottom-5">

                        <a href="#f{{rnum-$index}}" class="ba1 replaybtn  text-info margin-right-20" ng-click="beginReplay($event,x)">回复</a>
                    
                    </div>

                    <div class=" rcblock">
                        <div ng-repeat="t in x.reback" class="replayblock b2">
                            <div id=""><a ng-href="{{t.user_blog}}"  onclick="return gourl(this)"   title="{{t.user_blog}}">{{t.userid}}</a> @ <a href="{{t.tuser_blog}}" title="{{t.tuser_blog}}">{{t.tuid}}</a> <span class=" text-right">&nbsp;{{t.create_date}}</span>
                            
                                 <span  ng-show="root">【  <a  ng-show="x.state!=1" href="#f{{rnum-$index}}" class="   text-success margin-right-20" onclick="pass(this,'{{x.recordid}}')">审核通过</a>&nbsp;
                                       <a href="#f{{rnum-$index}}" class="   text-warning margin-right-20" onclick="del(this,'{{x.recordid}}')">删除</a>】
                                       </span>
                             </div>
                            <div class="rcct">{{t.content}}</div>
                            <div class="row  margin-bottom-5">

                                <a href="#ff{{rnum-$index}}" class=" replaybtn text-info margin-right-20" ng-click="beginReplay($event,t,x)">回复</a>
                            
                            </div>
                        </div>
                    </div>
                </div>
            </div> -->
	</div>
</div>

<div id="rdivc"></div>
<div class="row replay col-xs-12 margin-bottom-10" id="rpdiv">
	<div class="row">
		<span style="padding-left: 10px; line-height: 30px">发表新的评论</span>
	</div>
	<div class="row">
		<form name="fm" id="fm" class=" padding-left-0 col-xs-12"
			style="min-width: 150px;">




			<div class="ckdisplay form-group col-xs-12 row ">
				<div class="control-label padding-left-0 padding-top-0  " ng-cloak>
					<img src="{{s_icon}}"><span class='gname'><span
						class='rname' ng-cloak>{{s_uid}} </span>&nbsp;<span
						class="cmodify"><i class="fa fa-refresh" id="btnChangeName"
							title="修改信息"></i></span> ,&nbsp;欢迎回来. </span>
				</div>
			</div>

			<div class="nameinfo">
				<div class="form-group col-xs-12 row ">
					<div
						class="control-label padding-top-0 padding-left-0 text-left col-xs-12 ">
						您的称呼(<span class='red'>*</span>必填)：
					</div>
					<div class="col-sm-9 col-xs-12 text-right nopaddding">
						<input required type="text" class="form-control " name="s_uid"
							id="s_uid" ng-model="s_uid" placeholder="您的称呼 ">
					</div>
					<div class="col-xs-12 col-xs-offset-4 row "></div>
				</div>

				<div class="form-group col-xs-12 row ">
					<div
						class="control-label padding-top-0 padding-left-0 text-left col-xs-12 ">
						您的邮箱地址(<span class='red'>*</span>必填,您的邮箱地址不会公开,仅作为有回复后的消息通知手段)：
					</div>
					<div class="col-sm-9 col-xs-12 text-right nopaddding">
						<input type="text" class="form-control " name="s_email"
							id="s_email" ng-model="s_email" placeholder=" ">
					</div>
					<div class="col-xs-12 col-xs-offset-4 row "></div>
				</div>

				<div class="form-group col-xs-12 row ">
					<div
						class="control-label padding-top-0 padding-left-0 text-left col-xs-12 ">您的站点地址(选填)：</div>
					<div class="col-sm-9 col-xs-12 text-right nopaddding">
						<input type="text" class="form-control " name="s_ublog"
							id="s_ublog" ng-model="s_ublog" placeholder=" 您的站点地址(欢迎互访)：">
					</div>
					<div class="col-xs-12 col-xs-offset-4 row "></div>
				</div>





			</div>

			<div class="form-group col-xs-12 row ">
				<div class="control-label padding-left-0 padding-top-0 col-xs-12 ">


					<span>留言：</span>
				</div>
				<div class="col-sm-9 col-xs-12 text-right nopaddding ">
					<textarea required type="text" rows="4" class="form-control "
						name="s_text" id="s_text" ng-model="s_text"
						placeholder=您的留言经过审核后才会显示出来.">
															 </textarea>

					<div class="  ">
						<div class=""  >
							<div class="row">
								<h3 class="panel-title em_start  " data-toggle="collapse"
							data-parent="#eemj" href="#collapseEmj" title="来点表情？" >∑( ° △ °|||)︴</h3>
							</div>
						</div>


						<div id="collapseEmj" class="emj panel-collapse collapse  ">
							<div class="">

								<div  class="emjcontainer">


									<div class="em_item catsay" title="耶！">(๑•̀ㅂ•́)و✧</div>
									<div class="em_item catsay" title="得意"><(￣）￣)></div>
									<div class="em_item catsay" title="乾杯">[]~(￣▽￣)~*</div>
									<div class="em_item catsay" title="满足">(￣ˇ￣)</div>
									<div class="em_item catsay" title="乾杯">[]~(￣▽￣)~*</div>
									<div class="em_item catsay" title="满足">(￣ˇ￣)</div>
									<div class="em_item catsay" title="无耐">╮(￣▽￣)╭</div>
									<div class="em_item catsay" title="被打一巴掌">(￣ε(#￣)</div>
									<div class="em_item catsay" title="惊讶">(⊙ˍ⊙)</div>
									<div class="em_item catsay" title="装傻">(￣▽￣)~*</div>
									<div class="em_item catsay" title="惊吓">∑( ° △ °|||)︴</div>

								</div>



							</div>
						</div>
					</div>



				</div>
				<div class="col-xs-12 col-xs-offset-4 row "></div>
			</div>






		</form>
	</div>
	<div class="col-lg-10 row">
		<button id="sbtn" type="button " class="btn btn-info  col-lg-2 "
			ng-click="replay() ">回复</button>
		<button id="cbtn" type="button "
			class="hide btn btn-warning col-lg-offset-1 col-lg-2 "
			ng-click="canc() ">取消</button>
	</div>
</div>



<div class="modal fade " id="myModal3" tabindex="-1 " role="dialog "
	aria-labelledby="myModalLabel " aria-hidden="true">
	<div class="modal-dialog " style="width: 250px;">
		<div class="modal-content ">
			<div class="modal-header ">
				<button type="button " class="close " data-dismiss="modal"
					aria-hidden="true ">&times;</button>
				<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
			</div>



			<div class="modal-body container margin-top-10 ">
				<div class="row ">

					<p class="col-xs-10 ">确认执行操作吗？</p>

				</div>
			</div>
			<div class="modal-footer ">
				<button type="button" class="btn btn-default btn-warning "
					data-dismiss="modal">取消</button>
				<button id="btnconfirm" type="button " class="btn btn-primary ">
					确定</button>
			</div>
		</div>
	</div>
</div>





<!-- 

乾杯 []~(￣▽￣)~*

满足 (￣ˇ￣)

没睡醒 (￣﹏￣)

狡猾(｀﹏′)

被打一巴掌 (￣ε(#￣)

无言 (￣.￣)

无奈 ╮(￣▽￣)╭

装傻 (￣▽￣)~*

惊讶 (⊙ˍ⊙)

发现(￣.￣)+

惊吓 ∑( ° △ °|||)︴

冷 (￣▽￣)" -->
