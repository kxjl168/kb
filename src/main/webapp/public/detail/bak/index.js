var app = angular.module('myApp', ["ngSanitize"]);

app.controller('eduCtrl', function($scope,$sce) {

});

function showOriImg(){
	
}

$(function() {

    init();
    //hljs.configure({useBR: true});
    hljs.initHighlightingOnLoad();


    $(".paytype").click(function(){
    	 var forele=$(this).attr('for');
    	 $(".paycontent").find(".tab-pane").removeClass('active');
    	 $(forele).addClass("active");
    });
    
    $('.pct').autoMenu();
    

    $("#title").html($(".ptitle").html());

    document.title = $(".ptitle").html() + "-" + sitetitle;


    var meta = document.getElementsByTagName('meta');
    meta["keywords"].setAttribute('content', $("#tagstrval").val());

    var $scope = angular.element(ngSection).scope();
    $scope.$apply(function() {
    	// $scope.initimg();
    	 $scope.x={};
    	 $scope.x.imei=$("#imei").val();
    	 
    		setTimeout(function() {
   			 $scope.getReplayList();
   		}, 300);
    });
   
    
    initPic();
    

});

function initPic(){
	 
    var items=[];
    
    $.each($('img[fid]'),function(index,item){
    	
    	var picitem={};
    	picitem.src=$(item).attr('orisrc');
    	picitem.opts={};
    	
    	
    	var cap=$(item).attr('desc');
    	if(cap!=null&&cap!="")
    	    	picitem.opts.caption=cap;
    	
    	picitem.opts.thumb=$(item).attr('src');
    	
    	items.push(picitem);
    	
    });
    
    var opts={'transitionEffect':'slide',
    		 'transitionDuration': 800,
    		 'animationDuration': 500,};
   
    $.each($('img[fid]'),function(index,item){
    	$(item).click(function(){
    		$.fancybox.open( items, opts, index );	
    	})
    	
    });
}



function changerows(option) {
    var num = $(option).val();

    var $scope = angular.element(ngSection).scope();
    $scope.$apply(function() {
        $scope.rows = num;
        $scope.getList();
    });
};


function closepop(){
	  $(".pbtn").popover('hide');
};

function init() {

    initmenu_p($("#menuul"), "pulbic/detail/");

    $('#collapseOne').on(
        'shown.bs.collapse',
        function() {

            $("#titlepic").attr("class",
                "glyphicon glyphicon-chevron-up pull-right");
        });

    $('#collapseOne').on(
        'hidden.bs.collapse',
        function() {
            $("#titlepic").attr("class",
                "glyphicon glyphicon-chevron-down pull-right");
        });

    $('.modal').on('show.bs.modal', function() {
        $(this).draggable();
        $(this).css("overflow-y", "scroll");
    });

    var $scope = angular.element(ngSection).scope();
    $scope
        .$apply(function() {


            var http = getImUrl();


            $scope.ff = function(e) {

                var wrapper = $('.pall');



                wrapper.toggleClass("is-open");

            };

            $scope.initimg = function() {

                var bigimg = "#bigimg";
                var outdiv = "#outdiv";
                var indiv = ".indiv";

                var winW = $(window).width();
                var winH = $(window).height();
                $(".indiv").find("img").load(function() {
                    var imgW = this.width;
                    var imgH = this.height;

                    if (typeof this.naturalWidth == "undefined") {
                        // IE 6/7/8 
                        var i = new Image();
                        i.src = this.src;
                        imgW = i.width;
                        imgH = i.height;
                    } else {
                        // HTML5 browsers 
                        imgW = this.naturalWidth;
                        imgH = this.naturalHeight;
                    }


                    var scale = imgW / imgH;
                    if (imgW > winW) {
                    	
                    	var wrate= imgW/winW;
                    	var hrate=imgH/winH;
                    	
                    	
                    	var pich=winH*0.8;
                    	var picw= (imgW/imgH)*pich;
                    	
                    	var left= (winW-picw)/2+'px';
                    	var top='10%';
                    	
                     	 top='10%';
                         /*
                    		 pich=winH*(winH/imgH-0.2);
                        	 picw= (imgW/imgH)*pich;
                        	
                        	 left= (winW-picw)/2+'px';
                 		}
                    	else{ */
                    		picw=winW*(0.65);
                    		pich= (imgH/imgW)*picw;
                    		left='10%';
                    		top= (winH-pich)/2+'px';
                    	//}
                    	
                    
                    	
                    	//var rwidth= 
                        $(bigimg).css("width", picw+'px').css("height", pich+'px');
  
                        $(indiv).css({ "left": left, "top": top });
                    
                    } else  {
                        $(bigimg).css("width", imgW + 'px').css("height", imgH + 'px');
                        var w = (winW - imgW) / 2;
                        var h = (winH - imgH) / 2;
                        $(indiv).css({ "left": w, "top": h });
                    }


                    $(outdiv).click(function() {
                        $(outdiv).fadeOut("normal");
                    });
                });

                $(".pageText").find("img").each(function(index, img) {


                    $(img).click(function() {
                        var m = this;
                        var url = m.src;

                        //  msg(url);

                        var sufixIndex = url.lastIndexOf(".");
                        var pre = url.substr(0, sufixIndex);
                        var sufix = url.substr(sufixIndex);
                        var bigurl =  $(img).attr("orisrc");//;
                        if(bigurl==null)
                        	bigurl=pre + "_orign" + sufix;



                        $(bigimg).attr("src", bigurl);
                        $(outdiv).fadeIn("normal");

                    })

                });
                
                
                 function mouseWheel() {
                    function mousewheelIn(obj, upfun, downfun) {
                        if (document.attachEvent) {
                        	document.attachEvent("onmousewheel", scrollFn)
                        } else {
                            if (document.addEventListener) {
                            	document.addEventListener("mousewheel", scrollFn, false);
                            	document.addEventListener("DOMMouseScroll", scrollFn, false)
                            }
                        }
                        function scrollFn(e) {
                        	
                        	if(!$(outdiv).is(":visible"))
                        		return;
                        	
                            var ev = e || window.event;
                            var dir = ev.wheelDelta || ev.detail;
                            if (ev.preventDefault) {
                                ev.preventDefault()
                            } else {
                                ev.returnValue = false
                            }
                            if (dir >0 ) {
                                upfun()
                            } else {
                                downfun()
                            }
                        }
                    }
                    var that = this;
                    mousewheelIn($(bigimg), function() {
                       /* if ($(obj).innerWidth() > $("body").width() - 20) {
                            alert("不能再放大了");
                            return
                        }
                        if ($("." + that.bigimg).innerHeight() > $("body").height() - 50) {
                            alert("不能再放大");
                            return
                        }*/
                        var zoomHeight = $(bigimg).css("height").substr(0,$(bigimg).css("height").length-2) * 1.03;
                        var zoomWidth = $(bigimg).css("width").substr(0,$(bigimg).css("width").length-2) * 1.03;
                        $(bigimg).css({
                            height: zoomHeight + "px",
                            width: zoomWidth + "px"
                        })
                    }, function() {
                       /* if ($("." + that.bigimg).innerWidth() < 100) {
                            alert("不能再缩小了哦！");
                            return
                        }
                        if ($("." + that.bigimg).innerHeight() < 100) {
                            alert("不能再缩小了哦！");
                            return
                        }*/
                        var zoomHeight = $(bigimg).css("height").substr(0,$(bigimg).css("height").length-2) / 1.03;
                        var zoomWidth = $(bigimg).css("width").substr(0,$(bigimg).css("width").length-2) / 1.03;
                        $(bigimg).css({
                            height: zoomHeight + "px",
                            width: zoomWidth + "px"
                        })
                    })
                };
                
                mouseWheel();





            };




            $scope.title =sitetitle;

            $scope.page = 1;
            $scope.rows = 10;

            $scope.rows_select = [5, 10, 20];
            setTimeout(function() {
                $("div.tablefoot select").val($scope.rows);
            }, 50);
            $scope.pageData = [];

            $scope.getgoodnum=function(imei){
           	 var obj = new Object();
                if (typeof(imei) == "undefined")
               	 {
               	 obj.i = GetQueryString("i");
               		if(obj.i==null)
                           obj.i=$scope.getIMEI();
               	 }
                    
                else
                    obj.i = imei;
                
                SZUMWS(
                        http + "bloglike/goodnum.action",
                        JSON.stringify(obj),
                        function succsess(json) {

                            var code = json.ResponseCode;
                            var message = json.ResponseMsg;
                       
                            if (code == 200) {

                                $scope.goodnum = eval(json.total);

                                if($scope.goodnum==0)
                                	   $scope.goodnum ="";
                               
                                $("#gdnum").html($scope.goodnum);
                                $("#rdnum").html(json.view_num);
                                $("#rpnum").html(json.replay_num);
                                
                                $("#spidernum").html(json.spider_num);
                                
                                
                                if(json.showtime!=null&&json.showtime=="1")
                                	{
                                if(json.days>json.maxday)
                                	{
                                	$("#days").html(json.days);
                                	$("#timediv").removeClass("hide");
                                	}
                                	}
                                
                                
                                
                                
                                $scope.$apply();
                                
                               

                            } else {
                                msg(message);
                            }

                        },
                        function error(data) {
                          

                        }, false, false

                    );
               };

               
          
            
            $scope.good=function(imei){
            	 var obj = new Object();
                 if (typeof(imei) == "undefined")
                	 {
                	 obj.imei = GetQueryString("i");
                		if(obj.imei==null)
                            obj.imei=$scope.getIMEI();
                	 }
                     
                 else
                     obj.imei = imei;

                 
                 var uid=getUUID("myCanvas");
                 obj.cookie = uid;
                 SZUMWS(
                     http + "bloglike/good.action",
                     JSON.stringify(obj),
                     function succsess(json) {

                         var code = json.ResponseCode;
                         var message = json.ResponseMsg;
                    
                         if (code == 200) {
                             
                        	  $scope.goodnum = eval(json.total);
                              $("#gdnum").html($scope.goodnum);
                            
                              $scope.$apply();
                             

                         } else {
                             msg(message);
                         }

                     },
                     function error(data) {

                     }, false, "json"

                 );
            };
            $scope.pay=function(imei){
            	
            	
            	$("#payMd").modal('show');
            	
            	return;
            	
                   var  content =
                	    "   <div>" +
                	    "<div><label><input class='paytype' type='radio' value='1'>微信</label></div>" +
                	    "<div><label><input class='paytype' type='radio' value='2'>支付宝</label></div>" +
                	    "</div>" +
                	   "<div class='pbord' >"+
                       
                     
                         "   <img class='img-responsive' src=''></img>" +
                         
                           "<div class='ptip'>xxx</div>"+
                         "</div>";
                
                 $(".pbtn").popover({
                 	 container: 'body',
                     placement: "bottom",
                     content: content,
                     title: "<b>选择打赏方式:</b> &nbsp<span class='pclose fa fa-times' style='float:right' onclick='closepop()'></span>",
                     html: true
                 });
                 $(".pbtn").popover('show');
                 
                
            };
            
            $scope.getRelatedList=function(imei){
            	

                 var obj = new Object();
                 if (typeof(imei) == "undefined")
                	 {
                	 obj.i = GetQueryString("i");
                		if(obj.i==null)
                            obj.i=$scope.getIMEI();
                	 }
                     
                 else
                     obj.i = imei;


                 SZUMWS(
                     http + "blog/getRelatedList.action",
                     JSON.stringify(obj),
                     function succsess(json) {

                         var code = json.ResponseCode;
                         var message = json.ResponseMsg;
                    
                         if (code == 200) {

                             $scope.relatedlist = eval(json.datalist);
                             
                             
                             //关联
                             var html="";
                             html+="   <ul class=\"col-xs-12 \">";
                             $.each($scope.relatedlist,function(index,item){
                            	 html+=''
                            	 
                            	+' <li >  '

                            	+' 	<img class="nopaddding img-responsive col-xs-2" '
                            	+' 	style="width: 25px; height: 25px;" '
                            	+' 	title="'+item.blog_type_name+'" '
                            	+' 	src="'+item.blog_type_url+'"> <a '
                            	+' 		href="'+basePath+'/public/html/'+item.showdate+'/'+item.imei+'.html"  '
                            	+' 		class="col-sm-8 col-xs-11 ">'+item.title+'</a> '
                            	+' </li> ' ;
                             });
                             html+="   </ul>";
                             $(".relatedLink").html(html);
                             
                             //前后文
                             $scope.datalist = eval(json.prenextlist);
                             $scope.x=$scope.datalist[0];
                             if ($scope.datalist.length > 1) {
                                 if ($scope.datalist[1].recordid > $scope.x.recordid)
                                     $scope.next = $scope.datalist[1];
                                 else
                                     $scope.pre = $scope.datalist[1];
                             }
                             if ($scope.datalist.length > 2) {

                                 $scope.pre = $scope.datalist[2];
                             }
                             
                           var prenexthtml='';
                           
                           if( $scope.pre!=null)
                        	   prenexthtml+= ' <ul class="pagination pull-left"> '
                        	   +' <li> '
                        	   +'<a  '
                        	   +'href="'+basePath+'/public/html/'+$scope.pre.showdate+'/'+$scope.pre.imei+'.html" '
                        	   +'style="" class="for spider">前一篇:'+$scope.pre.title+'</a> '
 								
 								 +'</li>'
 								 +'</ul>';
                           
                           if( $scope.next!=null)
                        	   prenexthtml+=' <ul class="pagination pull-right">'
 								+' 	<li>'
 								
 								+' <a '
 						 	   +'href="'+basePath+'/public/html/'+$scope.next.showdate+'/'+$scope.next.imei+'.html" '
                        	   +'style="" class="for spider">后一篇:'+$scope.next.title+'</a> '
 								
 								+' </li>'
 								+' </ul>' ;
                             
                           $(".prenexlist").html(prenexthtml);
                             
                             

                             
                             $scope.getgoodnum();
                       
                             $scope.$apply();
                             
                            

                         } else {
                             msg(message);
                         }

                     },
                     function error(data) {
                       

                     }, false, false

                 );
            };
            

            $scope.getIMEI= function () {

            	var index = window.location.href.lastIndexOf("/");
            	var indexj = window.location.href.lastIndexOf(".html");

            	// 最后一个/开始 截取#前面的，兼容history.js html4 url
            	var searchpath = window.location.href.substr(index + 1);
            	if (indexj > 0)
            		searchpath = window.location.href.substr(index + 1, indexj - index - 1);

            
            	return searchpath;
            };
            
            
            $scope.getList = function(imei, fucOnFinished, clear) {



                $scope.page = 1;

                $scope.pre = $scope.next = null;

                var obj = new Object();
                if (typeof(imei) == "undefined"){
                	obj.i = GetQueryString("i");
                	if(obj.i==null)
                    obj.i=$scope.getIMEI();
                }
                else
                    obj.i = imei;


                obj.page = $scope.page;
                obj.rows = $scope.rows;
                SZUMWS(
                    http + "blog/getDetailList.action",
                    JSON.stringify(obj),
                    function succsess(json) {

                        var code = json.ResponseCode;
                        var message = json.ResponseMsg;
                       // console.log('-----return -code= ' + code +
                      //      ';message= ' + message);
                        if (code == 200) {

                            $scope.datalist = eval(json.datalist);

                            if ($scope.datalist.length > 0) {
                                $scope.x = $scope.datalist[0];
                                $scope.x.context = unescape($scope.x.content);
                                
                                
                                 $("#rdnum").html($scope.x.view_nums);
                                 $("#rpnum").html($scope.x.replay_nums);
                                
                                
                            }

                            if ($scope.datalist.length > 1) {
                                if ($scope.datalist[1].recordid > $scope.x.recordid)
                                    $scope.next = $scope.datalist[1];
                                else
                                    $scope.pre = $scope.datalist[1];
                            }
                            if ($scope.datalist.length > 2) {

                                $scope.pre = $scope.datalist[2];

                            }





                            $scope.total = json.total;
                            $scope.pageDataPre = [];
                            $scope.pageDataAft = [];
                            $scope.pageNum = Math.ceil($scope.total /
                                $scope.rows);

                            for (var i = $scope.page - 3; i < $scope.page; i++) {
                                if (i > 0)
                                    $scope.pageDataPre.push(i);
                            }
                            for (var i = $scope.page + 1; i < $scope.page + 3; i++) {
                                if (i <= $scope.pageNum)
                                    $scope.pageDataAft.push(i);
                            }


                            $scope.$apply();
                            
                            $('.pct').autoMenu();
                            

                            $scope.getRelatedList();
                      

                            $('pre code').each(function(i, block) {
                                hljs.highlightBlock(block);
                            });



                            $("#title").html($scope.x.title);

                            document.title = $scope.x.title + "-" + $scope.title;


                            var meta = document.getElementsByTagName('meta');
                            meta["keywords"].setAttribute('content', $scope.x.tags);


                            $scope.initimg();


                            console.log('-----guideList -OK= ');

                        } else {
                            msg(message);
                        }



                    },
                    function error(data) {
                        msg("net work error！");

                        if (fucOnFinished != null)
                            fucOnFinished();

                    }, false, false

                );

            };
            
            setTimeout(function(){
            	$scope.getRelatedList();	
			}, 50);
            
            
          //  $scope.getList();

        });

};


app.filter("sanitize", ['$sce', function($sce) {
    return function(htmlCode) {
       // return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
    }
}]);