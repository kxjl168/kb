<%@ page language="java" contentType="text/html; charset=UTF-8"%>

    <div class="row col-xs-12" id="commet">
                                            <hr></hr>
                                 </div>
    <div ng-cloak >

    
      <!--   <div ng-repeat="x in rdatalist" id="cblock"> -->
       <div id="cblock"> 

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
            <form name="fm" id="fm" class=" padding-left-0 col-xs-12" style="min-width: 150px; ">


                <div class="form-group col-xs-12 row ">
                    <div class="control-label padding-left-0 padding-top-0 col-xs-12 ">留言：</div>
                    <div class="col-sm-7 col-xs-12 text-right nopaddding ">
                        <textarea required type="text" rows="3" class="form-control " name="s_text" id="s_text" ng-model="s_text" placeholder=" ">
															 </textarea>
                    </div>
                    <div class="col-xs-12 col-xs-offset-4 row ">

                    </div>
                </div>

                <div class="form-group col-xs-12 row ">
                    <div class="control-label padding-top-0 padding-left-0 text-left col-xs-12 ">您的大名：</div>
                    <div class="col-sm-7 col-xs-12 text-right nopaddding">
                        <input required type="text" class="form-control " name="s_uid" id="s_uid" ng-model="s_uid" placeholder=" ">
                    </div>
                    <div class="col-xs-12 col-xs-offset-4 row ">

                    </div>
                </div>

                <div class="form-group col-xs-12 row ">
                    <div class="control-label padding-top-0 padding-left-0 text-left col-xs-12 ">您的Blog地址(欢迎互访)：</div>
                    <div class="col-sm-7 col-xs-12 text-right nopaddding">
                        <input  type="text" class="form-control " name="s_ublog" id="s_ublog" ng-model="s_ublog" placeholder=" ">
                    </div>
                    <div class="col-xs-12 col-xs-offset-4 row ">

                    </div>
                </div>
                
                
                <div class="form-group col-xs-12 row ">
                    <div class="control-label padding-top-0 padding-left-0 text-left col-xs-12 ">您的邮箱地址(您的邮箱地址不会公开,仅作为有回复后的消息通知手段)：</div>
                    <div class="col-sm-7 col-xs-12 text-right nopaddding">
                        <input  type="text" class="form-control " name="s_email" id="s_email" ng-model="s_email" placeholder=" ">
                    </div>
                    <div class="col-xs-12 col-xs-offset-4 row ">

                    </div>
                </div>




                <div class="hide form-group col-xs-12 row ">
                    <div class="control-label padding-top-0 col-xs-4 ">：</div>
                    <div class="col-sm-7 col-xs-12 text-right ">
                        <input required type="text" class="form-control " name="s_tags" id="s_tags" ng-model="s_tags" placeholder=" ">
                    </div>
                    <div class="col-xs-12 col-xs-offset-4 row ">

                    </div>
                </div>



            </form>
        </div>
        <div class="col-lg-10 row">
            <button id="sbtn" type="button " class="btn btn-primary  col-lg-2 " ng-click="replay() "> 回复 </button>
            <button id="cbtn" type="button " class="hide btn btn-primary col-lg-offset-1 col-lg-2 " ng-click="canc() "> 取消 </button>
        </div>
    </div>
    
    
    
    <div class="modal fade " id="myModal3" tabindex="-1 " role="dialog " aria-labelledby="myModalLabel " aria-hidden="true">
				<div class="modal-dialog " style="width: 250px; ">
					<div class="modal-content ">
						<div class="modal-header ">
							<button type="button " class="close " data-dismiss="modal" aria-hidden="true ">&times;</button>
							<h4 class="modal-title " id="myModalLabel ">确认操作</h4>
						</div>



						<div class="modal-body container margin-top-10 ">
							<div class="row ">

								<p class="col-xs-10 ">确认执行操作吗？</p>

							</div>
						</div>
						<div class="modal-footer ">
							<button type="button" class="btn btn-default btn-warning " data-dismiss="modal">取消
							</button>
							<button id="btnconfirm"  type="button " class="btn btn-primary "> 确定 </button>
						</div>
					</div>
				</div>
			</div>
    
    	
    	
    	<div class="modal fade " id="myModal_outurl" tabindex="-1 " role="dialog " aria-labelledby="myModalLabel " aria-hidden="true">
				<div class="modal-dialog " style="width: 250px; ">
					<div class="modal-content ">
						<div class="modal-header ">
							<button type="button " class="close " data-dismiss="modal" aria-hidden="true ">&times;</button>
							<h4 class="modal-title " id="myModalLabel ">访问确认</h4>
						</div>



						<div class="modal-body container margin-top-10 ">
							<div class="row ">

								<p class="col-xs-10 ">确认要访问该网站吗？</p>

							</div>
						</div>
						<div class="modal-footer ">
							<button type="button" class="btn btn-default btn-warning " data-dismiss="modal">取消
							</button>
							<button id="btnconfirm_outurl"  type="button " class="btn btn-primary "> 确定 </button>
						</div>
					</div>
				</div>
			</div>
    