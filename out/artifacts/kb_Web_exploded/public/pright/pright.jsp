<%@ page language="java" contentType="text/html; charset=UTF-8"%>

    <div class="rblock">
        <div class=" panel panel-success">
            <div class="panel-heading" title="" data-toggle="collapse" data-parent="#accordion" href="#collapseOner11">
                <div class="row">
                    <h3 class="panel-title  col-xs-10">文章分类</h3>



                    <span id="titlepic" class="hide" data-toggle="collapse" data-parent="#accordion" href="#collapseOner11" class="glyphicon glyphicon-chevron-up pull-right "></span>
                </div>
            </div>
            <div id="collapseOner11" class="panel-collapse collapse in ">
                <div class="panel-body">

                    <div class="container">

                        <div ng-cloak ng-repeat="x in tplist">
                            <div class="row">
                                <a ng-click="showtp(x)">
                                    <img class="nopaddding img-responsive col-xs-2" style="width:20px; height:20px;" title="{{x.blog_type_name}}" ng-src="{{x.blog_type_url}}"> {{x.blog_type_name}}&nbsp;
                                    <span>({{x.view_nums}})</span>
                                </a>

                                <a ng-href="{{preurl}}/public/index/bt/{{x.blog_type}}.html" style="display: none;" style="" class="for spider"> {{x.blog_type_name}}</a>
                            </div>
                        </div>
                    </div>



                </div>
            </div>
        </div>

        <div class=" panel panel-success">
            <div class="panel-heading" title="" data-toggle="collapse" data-parent="#accordion" href="#collapseOner2">
                <div class="row">
                    <h3 class="panel-title col-xs-10">文章归档</h3>



                    <span id="titlepic" class="hide" data-toggle="collapse" data-parent="#accordion" href="#collapseOner2" class="glyphicon glyphicon-chevron-up pull-right "></span>
                </div>
            </div>
            <div id="collapseOner2" class="panel-collapse collapse in ">
                <div class="panel-body">

                    <div class="container">

                        <div ng-cloak ng-repeat="x in hlist">
                            <div class="row">
                                <a ng-click="showh(x)"> 
					 {{x.month}}&nbsp;<span>({{x.view_nums}})</span>
					 </a>
                                <a ng-href="{{preurl}}/public/index/h/{{x.month}}.html" style="display: none;" style="" class="for spider">{{x.month}}</a>
                            </div>
                        </div>
                    </div>



                </div>
            </div>
        </div>


        <div class=" panel panel-success">
            <div class="panel-heading " title="" data-toggle="collapse" data-parent="#accordion" href="#collapseOner3">
                <div class="row">
                    <h3 class="panel-title col-xs-10  ">标签</h3>



                    <span id="titlepic" class="hide" data-toggle="collapse" data-parent="#accordion" href="#collapseOner3" class="glyphicon glyphicon-chevron-up pull-right "></span>
                </div>
            </div>
            <div id="collapseOner3" class=" panel-collapse collapse in ">
                <div class="panel-body ">

                    <div class="container ">



                        <a ng-cloak ng-repeat="x in tglist" ng-click="showtg(x)"> 
					 {{x.tags}}&nbsp;,
					 </a>
                        <a ng-cloak ng-repeat="x in tglist" ng-href="{{preurl}}/public/index/tg/{{x.tags}}.html" style="display: none;" style="" class="for spider">{{x.tags}}</a>

                    </div>



                </div>
            </div>
        </div>
    </div>