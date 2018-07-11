
var app = angular.module('myApp', [ "ngSanitize"]);

app.controller('eduCtrl', function($scope) {
	
});






function convertDateFromString(dateString) { 
	if (dateString) { 
	var arr1 = dateString.split(" "); 
	var sdate = arr1[0].split('-'); 
	var date = new Date(sdate[0], sdate[1]-1, sdate[2]); 
	return date;
	} 
	};
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt))
fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o){
    if (new RegExp("(" + k + ")").test(fmt)) {
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
}
    }
    return fmt;
};



function sortResult(){
	var html=createResultHtml(qdata,true);
	$("#wuliuresult").html(html);
};

var qdata;
var createResultHtml = function(json, forceOrd) {
	
    var html='',
       ord = json.ord
      , com = json.company;
    if (!com) {
        com = {
            name: json.expTextName,
            spell: json.expSpellName,
            dir: "",
            tel: "",
            url: ""
        }
    }
    
    var uclass="";
    var dclass=" igray ";
    if(forceOrd!=null ){
    	
    	json.data=json.data.reverse();
    	if(json.ord=="ASC"){
    	
    	uclass=" igray ";
    	dclass=" ";
    	json.ord="DESC";
    	}
    	else
    		{
    		uclass="  ";
        	dclass="igray ";
        	json.ord="ASC";
    		}
    	
    
    }
   
    
    qdata=json;
    	
    
    html += '<div class="col-xs-12 row">'+json.expTextName+'</div>';
   
    html += '<div class="col-xs-12 row result-table-header ' + json.ord + ' notranslate">' + '<span class="col-xs-3" data-order="' + ord + '" onClick="sortResult()"  >时间'
   +' <div class="tbdiv col-xs-3 pull-right row">   '
   +' <i id="itop" class="itop col-xs-12 fa fa-caret-up '+uclass+' fa-stack-1x" ></i> '
   +' <i id="ibottom" class="ibottom col-xs-12 fa fa-caret-down  '+dclass+' fa-stack-1x"></i> '
   +' </div> '
    +' </span>' + '<span class="col-xs-8">地点和跟踪进度</span>' + "</div>";
    html += '<table class="result-data wuliu ' + ord + '" border="0" cellspacing="0" width="100%">';
    var index = 0;
    var lastday,cday;
    for (var i = 0, l = json.data.length; i < l; i++) {
        var row = json.data[i]
          , clsName = " even "
          , ad = "";
        
        var  icon=' fa-angle-down ';
        var  done='  ';
    	if(json.ord=="DESC")
    		 icon=' fa-angle-up ';
    		
        if (index % 2 == 0) {
            clsName = " odd ";
        }
        if (i == 0) {
        	
            clsName += " first ";
        
            if(json.ord=="ASC")
            	{
            	icon=" fa-circle ";	
            	done=' ';
            	}
            
            else
            	{
            	icon=" fa-check ";
            	done=' done ';
            	}
            	
        } else if (i == l - 1) {
        
            clsName += " last ";
            if(json.ord=="ASC")
            	{
            	done=' done ';
            	icon=" fa-check ";
            	}
            
            else
            	{
            	done='  ';
            	icon=" fa-circle ";
            	}
            	
            	
            if (json.status == 3) {
                clsName += " sign ";
            }
            if ("undefined" != typeof queryAD)
                ad = queryAD();
        }
        
        
        var day="";
        var time=row.time;
        cday= convertDateFromString( row.time).Format("yyyy-MM-dd");
        ctime=  row.time.replace(cday,"");
        if(lastday==null){
        	time=row.time;
        	lastday=cday;
        }
        else
        	{
        
        	if(lastday!=cday)
        		{
        		lastday=cday;
        		
        		}
        	else
        		{
        		cday="";
        		}
        		
        
        	
        	}
        
        
        html += '<tr class="' + clsName + '">';
        html += '<td class="col-xs-2 itime">' + cday + "</td>";
        html += '<td class="col-xs-1 ">' + ctime + "</td>";
        html += '<td class="col-2 lineparent"><span class="line">';
        	
        	  html+=''
                  +	' <span class="fa-stack route-icon ' +  done +' "> '
                  +	'  <i class="fa fa-circle-thin fa-stack-2x "></i> '
                  +	'  <i class="fa '+icon+' fa-stack-1x "></i> '
                  +	'  </span> ';
        	
        	html+='</i></span></td>';
        html += '<td class="col-xs-8" id="content-index-' + index + '">' + row.context + ad + "</td>";
        html += "</tr>";
        index++;
    }
    html += "</table>";
    html += "</div>";
    return html;
};


$(function() {
	
	init();
	
	// hljs.initHighlightingOnLoad();
	
	

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_uid : {
			required : true,
			maxlength:40,

		},
		s_ublog : {
			required : false,
			maxlength:100,
	
		},
		s_text : {required : true,
			minlength:10,
			maxlength:500,
		}
		
	}, {
		s_uid : {
			required : "大侠请留名！",
			maxlength : "字数有点太多了-_-！",
		},
		s_ublog : {
		
			maxlength : "字数有点太多了-_-！",
		},
		s_text :  {required :  "写几个字吧 ",
			minlength : "再多写两个字呗 ",
			maxlength : "字数有点太多了-_-！",
			
		}
	}, $scope.doupdate, "","");

});

function changerows(option) {
	var num = $(option).val();
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.rows = num;
		$scope.getList();
	});
};

function init() {

	initmenu_p($("#menuul"), "public/wuliu/");

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
			

				$scope.init=function(){
					$("#srzt").on("click","a", function(e) {
						
						var url=e.currentTarget.href;
						if(url.indexOf("javascript")>-1)
							{
							
							}
						else
							{
					//	msg(url);
						$scope.stopDefault(e);
						$scope.search(url);
							}

				});
				};
				//$scope.init();

				$scope.replay = function(x) {

					kvalidate.validate("#fm");

				};
				
				
				$scope.title = siteurl;
				
				$scope.page = 1;
				$scope.rows = 10;

				
				var title="GOOGLE SEARCH";
				$("#title").html(title);

			//	document.title=title;*/
				

			/*	var meta = document.getElementsByTagName('meta');
				meta["keywords"].setAttribute('content',$scope.x.tags);
				*/
				$("#kwd").bind('keypress', function(event) {
					if (event.keyCode == "13") {
						$scope.search();
					}
				});
				
				
				
				$scope.init = function(url) {

					var obj = {};

				
					SZUMWS(http + "search/init.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						console.log('-----return -code= ' + code + ';message= '
								+ message);
						if (code == 200) {
							
				

						} else {
							msg(message);
						}
						

					}, function error(data) {
						msg("net work error！");
						

					}, false, false

					);

				};
				$scope.init();
				
				
				$scope.lastData=[];
				$scope.back=function()
				{
					var last="";
					if($scope.lastData.length>0)
						{
						last=$scope.lastData.pop();
						
						$("#srzt").html(last);
						}
						
					else
						{
						msg("已经无法后退啦~");
						}
					
				};
				
				$scope.search = function(url) {

					var obj = {};

					if($("#num").val()==null||$("#num").val()==""||$("#num").val().length<4)
						{
						info("请输入正确快递单号！");
						return;
						}
					
					obj.id =$("#num").val();

					SZUMWS(http + "public/wuliuinfo.action", JSON
							.stringify(obj), function succsess(response) {

						if(response){
							var html=createResultHtml(response);
							$("#wuliuresult").html(html);
							}
							else{
								$("#wuliuresult").html("暂无物流信息");
							}
							
						

					}, function error(data) {
						msg("net work error！");
						

					}, true, false

					);

				};
				
				$scope.stopDefault=function( e ) 
				 {       
				 if ( e && e.preventDefault )          
				     e.preventDefault();      
				 else          
				 
				 window.event.returnValue = false;              
				 return false;  }  ;
				
			

				
				
				$scope.canc=function(event,x){
					var row=	$("#rpdiv");

					kvalidate.resetForm("#fm");
						$("#rdivc").after(row);
						$("#cbtn").addClass("hide");	
				};
				

				$scope.beginReplay=function(event,x){
					$scope.preplay=x;
					var row=	$("#rpdiv");
				
					
					($(event.target).parent()).after(row);

					$("#cbtn").removeClass("hide");
	
				};
				
			});
				

};


app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
