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

                    <div id="tpdiv"  class="container">

                        <div  ng-cloak ng-repeat="x in tplist">
                            <div class="row">
                                <a  ng-href="{{preurl}}/public/index/bt/{{x.blog_type}}.html" >
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

                    <div id="mdiv"  class="container">

                        <div ng-cloak ng-repeat="x in hlist">
                            <div class="row">
                                <a ng-href="{{preurl}}/public/index/h/{{x.month}}.html" > 
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

                    <div id="tgdiv" class="container ">


       <a ng-cloak ng-repeat="x in tglist"  ng-href="{{preurl}}/public/index/yq/{{x.tags}}.html"> 
					 {{x.tags}}&nbsp;,
					 </a>
                        <a ng-cloak ng-repeat="x in tglist" ng-href="{{preurl}}/public/index/yq/{{x.tags}}.html" style="display: none;" style="" class="for spider">{{x.tags}}</a>

                      

                    </div>



                </div>
            </div>
        </div>
        
        
        <div class=" panel panel-success">
            <div class="panel-heading " title="" data-toggle="collapse" data-parent="#accordion" href="#collapseOner3">
                <div class="row">
                    <h3 class="panel-title col-xs-10  ">友情链接</h3>



                    <span id="titlepic" class="hide" data-toggle="collapse" data-parent="#accordion" href="#collapseOner3" class="glyphicon glyphicon-chevron-up pull-right "></span>
                </div>
            </div>
            <div id="collapseOner3" class=" panel-collapse collapse in ">
                <div class="panel-body ">

                    <div class="container " id="yqdiv">


  <a ng-cloak ng-repeat="x in yqlist"  ng-href="{{x.url_val}}"   onclick="return gourl(this)" > 
     <div class="row col-xs-6">
					 <span class="glyphicon glyphicon-hand-right"></span> {{x.url_name}}&nbsp;
					 </div>
					 </a>
                       
                 
                    </div>



                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript"
	src="${basePath}/js/plugin/tagcloud.js"></script>
	
    	<script type="text/javascript" src="${basePath}/public/pright/pright_t_h.js"></script>
    	
    	
    	 <div class=" panel panel-success">
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <!-- auto -->
    <ins class="adsbygoogle"
             style="display:block"
                  data-ad-client="ca-pub-4546997533420825"
                       data-ad-slot="5098577692"
                            data-ad-format="auto"
                                 data-full-width-responsive="true"></ins>
                             <script>
                                 (adsbygoogle = window.adsbygoogle || []).push({});
                             </script>

</div>
    
