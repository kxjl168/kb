<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

    <%@include file="/common/tag.jsp"%>

        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
            <meta name="fragment" content="!">
            <title id="title">详细</title>
            <meta name="keywords" content="KxのBook">
            <meta name="description" content="Kx 的个人站点" />
            <meta name="author" content="ZHANG JIE">
            <link rel="stylesheet" type="text/css" media="screen" href="${basePath}/js/plugin/bootstrap/css/bootstrap.min.css">
            <!-- <link rel="stylesheet" type="text/css" media="screen" href="${basePath}/js/plugin/angular-xeditable-0.8.1/css/xeditable.min.css">
 -->
            <link rel="stylesheet" href="${basePath}/css/kCommon.css">

            <!-- <link rel="stylesheet" href="${basePath}/css/zcfg.css"> -->
            <link rel="stylesheet" href="${basePath}/css/common.css">
            <!-- 			<link rel="stylesheet" href="${basePath}/css/swiper_zcfg.css">
			<link rel="stylesheet" href="${basePath}/js/plugin/swiper/idangerous.swiper.css"> -->

            <script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery.v1.11.3.js"></script>

            <script type="text/javascript" src="${basePath}/js/plugin/bootstrap/js/bootstrap.min.js"></script>

            <!-- <script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery-ui.js"></script> -->

            <script type="text/javascript" src="${basePath}/js/plugin/angular/angular.min.js"></script>
            <script type="text/javascript" src="${basePath}/js/plugin/angular/angular-resource.min.js"></script>
            <script type="text/javascript" src="${basePath}/js/plugin/angular/angular-sanitize.min.js"></script>


            <link rel="stylesheet" href="${basePath}/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/default.css">
            <link rel="stylesheet" href="<c:out value='${basePath}'/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/styles/obsidian.css">

            <script type="text/javascript" src="<c:out value='${basePath}'/>/js/plugin/ckeditor4.8/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>



        </head>

        <body id="ngSection"  ng-app="myApp" ng-controller="eduCtrl">


            <div class="" id="content" style="">

                <%@include file="../phead.jsp"%>




                    <div class=" row row-margin-top-70">



                        <div class="wrapper col-xs-12 ">


                            <div id="pleft" class="  pleft">

                                <div ng-cloak>
                                    <div>

                                        <div class="row col-xs-12 ">

                                            <div class="nopadding-left">
                                                <img class="nopaddding img-responsive col-xs-2" style="width:25px; height:25px;" title="{{x.blog_type_name}}" ng-src="{{x.blog_type_url}}">
                                                <div class="col-sm-8 col-xs-11 ptitle  ">{{x.title}}</div>


                                                <div class="col-sm-3 col-xs-12 text-right">

                                                    <a ng-repeat="t in x.tagStrs" ng-click="showtgs(t)">{{t}},</a>
                                                </div>
                                                <span ng-click="ff()" style="margin-top:5px;width:15px; height:20px;color:#1f4433;" class="ctrl nopaddding img-responsive col-xs-2 glyphicon glyphicon-transfer" title="隐藏/打开侧边栏"></span>
                                            </div>
                                        </div>

                                        <div class="margin-top-10 row col-xs-12"></div>
                                        <div class="hide row col-xs-12">
                                            <hr></hr>
                                        </div>

                                        <div class="col-xs-12 row">
                                            <div class="">
                                                <div ng-cloak class=" pageText " ng-bind-html="x.context|sanitize">
                                                </div>
                                            </div>


                                        </div>

                                        <br>
                                        <div ng-cloak class="row col-xs-10 margin-top-5 ">post@{{x.create_date}}&nbsp;
                                            <span>阅读(<span id="rdnum">{{x.view_nums}}</span>)&nbsp;</span>
                                            <span>评论(<span id="rpnum">{{x.replay_nums}}</span> )&nbsp;</span>
                                        </div>


                                        <div class="row col-xs-12">
                                            <hr></hr>
                                        </div>
                                    </div>


                                    <div class="  col-xs-12 row tablefoot">
                                        <ul class="pagination pull-left">
                                            <li>
                                                <a ng-href="{{preurl}}/public/html/{{pre.showdate}}/{{pre.imei}}.html" ng-show="pre" ng-click="" title="上一篇:{{pre.title}}">&laquo;{{pre.title}}</a>
                                                <a ng-href="{{preurl}}/public/html/{{pre.showdate}}/{{pre.imei}}.html" style="display: none;" class="for spider">{{pre.title}}</a>
                                            </li>
                                        </ul>
                                        <ul class="pagination pull-right">
                                            <li>
                                                <a ng-href="{{preurl}}/public/html/{{next.showdate}}/{{next.imei}}.html" ng-show="next" ng-click="" title="下一篇:{{next.title}}">{{next.title}} &raquo;</a>
                                                <a ng-href="{{preurl}}/public/html/{{next.showdate}}/{{next.imei}}.html" style="display: none;" class="for spider">{{next.title}}</a>
                                            </li>
                                        </ul>





                                        <select onchange="changerows(this)" class="hide pull-right">
										<option ng-repeat="x in rows_select">{{x}}</option>
									</select>
                                    </div>
                                    
                                    
                                    
                                    
                                       <div class="row col-xs-12 ">

                                            <div class="pageText lincense">
                                            
                                            <div id="license_information">
                                           
                                            <p>本文 [{{x.title}}]基于<a href="https://mit-license.org/" title="MIT License " target="_blank">MIT License </a>
                                            	许可协议发布,作者：KxのBook<a href="http://www.256kb.cn/">http://www.256kb.cn/</a>
                                            	</p></div>
                                            
                                            
                                          <p>文章固定链接： <a ng-href="{{preurl}}/public/html/{{x.showdate}}/{{x.imei}}.html">{{preurl}}/public/html/{{x.showdate}}/{{x.imei}}.html</a> 转载请注明</p>
                                          <p></p>
                                              
                                              
                                            </div>
                                        </div>
                                        
                                        
                                        
                                        <div class="row col-xs-12">
                                            <hr></hr>
                                        </div>
                                           <div class="row col-xs-12 more">
                                           <p>相关文章：</p>
                                            <div class="nopadding-left">
                                               
                                               <ul class="pagination pull-right">
                                           			 <li>
                                              <!--   <a ng-href="{{preurl}}/public/html/{{next.showdate}}/{{next.imei}}.html" ng-show="next" ng-click="" title="下一篇:{{next.title}}">{{next.title}} &raquo;</a> -->
                                                
                                            		</li>
                                       		 </ul>
                                              
                                              
                                            </div>
                                        </div>
                                        

                                </div>




                                <%@include file="comment.jsp" %>




                            </div>


                            <div id="pright" class="">

                                <%@include file="/public/pright/pright.jsp" %>



                                    <div class="hide panel panel-success">
                                        <div class="panel-heading" title="点击显示/隐藏查询条件" data-toggle="collapse" data-parent="#accordion" href="#collapseOne2">
                                            <div class="row">
                                                <h3 class="panel-title col-xs-4 col-lg-4 col-md-4 ">面板2</h3>



                                                <span id="titlepic" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="glyphicon glyphicon-chevron-up pull-right "></span>
                                            </div>
                                        </div>
                                        <div id="collapseOne2" class="panel-collapse collapse in">
                                            <div class="panel-body">

                                                <div class="container">

                                                    <p>站位！！</p>
                                                </div>



                                            </div>
                                        </div>
                                    </div>
                            </div>


                        </div>












                    </div>

            </div>







            <div class="result" id="outdiv">
                <div class="indiv">
                    <img class="imgresult" id="bigimg" src="">
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


            <%@include file="../pfoot.jsp" %>


                <!-- <script type="text/javascript" src="${basePath}/js/plugin/angular-xeditable-0.8.1/js/xeditable.js"></script>
 -->


                <script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery.noty.min.js"></script>
                <script type="text/javascript" src="${basePath}/js/plugin/jquery/noty.layout.center.js"></script>
                <script type="text/javascript" src="${basePath}/js/plugin/jquery/noty.themes.bootstrap.js"></script>


                <script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery.validate.js"></script>

                <script type="text/javascript" src="${basePath}/js/own/kvalidate.js"></script>

		<script type="text/javascript" src="${basePath}/js/plugin/jquery/jquery-ui.js"></script>


                <!-- <script type="text/javascript" src="${basePath}/js/plugin/swiper/idangerous.swiper.min.js"></script> -->
                <script src="${basePath}/js/own/menu.js"></script>
                <script src="${basePath}/js/own/loading.js"></script>

                <%-- 
<script type="text/javascript"
	src="<c:out value="${basePath}" />/js/plugin/ckeditor4.8/ckeditor.js"></script>
						<script type="text/javascript" src="<c:out value="${basePath}"/>/js/plugin/ckeditor4.8/adapters/jquery.js"></script>

 --%>

                    <!-- <script type="text/javascript" src="${basePath}/js/plugin/select2/select2.full.min.js"></script> -->
                    <script type="text/javascript" src="${basePath}/public/detail/index.js"></script>
                    <script type="text/javascript" src="${basePath}/public/detail/replay.js"></script>
                    <script type="text/javascript" src="${basePath}/public/pright/pright_t_h.js"></script>





        </body>

        </html>