
var app = angular.module('myApp', [  ]);
app.controller('eduCtrl', function($scope) {

});

/*document.addEventListener("deviceready", function() {
	console.log("deviceready2=======: ");
	init();
});*/

$(function() {
	/*if (clinetType == "Android" || clinetType == "http") {
		console.log("deviceready1=======: ");
		

	}*/
	initDateSelect();
	
	init();
	
	

});

var cdate="";
function getDetailList(page,date,ele){
	cdate=date;
	 var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.rows2=10;
			$scope.page2=page;
			$scope.getDetailList(page,date,ele);
		});
}

function changerows2(option)
{
	var num= $(option).val();
	//msg(num);
	
		 var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.rows2=num;
			$scope.getDetailList();
		});
}


function changerows(option)
{
	var num= $(option).val();
	//msg(num);
	
		 var $scope = angular.element(ngSection).scope();
		$scope.$apply(function() {
			$scope.rows=num;
			$scope.getList();
		});
}

function showaction(userid){
	cuserid=userid;
	 var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {
		$scope.showaction();
	});
}


var cuserid='';
function initDetailTable() {
	
	var http = getImUrl();// "";

	
	

    // 初始化Table
    $('#table_detail').bootstrapTable({
        url:http + "statistics/GetActionList.action", // 请求后台的URL（*）
        method: 'post', // 请求方式（*）
        contentType: 'application/x-www-form-urlencoded',
        toolbar: '#toolbar', // 工具按钮用哪个容器
    
        showHeader: true,
        searchAlign: 'left',
        buttonsAlign: 'left',
        searchOnEnterKey: true,
        striped: true, // 是否显示行间隔色
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, // 是否显示分页（*）
        sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 10, // 每页的记录行数（*）
        pageList: [10, 25], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        detailView: false,
        // showColumns: true, //是否显示所有的列
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        // queryParamsType : "limit",
        queryParams: function queryParams(params) { // 设置查询参数
        	
        	var rows= params.limit; // 每页要显示的数据条数
           var offset= params.offset; // 每页显示数据的开始行号
           
           
        	var page=1+ offset/rows;
     
            var param = {
            		rows: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                page:page,
                sort: params.sort, // 要排序的字段
             
                qType : "detailpag_",//$("#s_type").val(),
                date_type : $("#dateType").val(),
                qName : $("#s_type ").find("option:selected").text(),
                date:cdate,
                userid:cuserid,
            };
            return param;
        },
        columns: [
        	{
                field: 'id',
                title: '订单编号',
                align: 'center',
                valign: 'middle',
                visible:false
            }, 
        	{
            field: 'action_date',
            title: '时间',
            align: 'center',
            valign: 'middle'
        },
        {
            field: 'type_first',
            title: '分类一',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'type_second',
            title: '分类二',
            align: 'center',
            valign: 'middle'
        },  {
            field: 'blogname',
            title: '文章',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return "<a href='"+basePath+"/public/detail/?i="+row.blog_id+"'>"+value+"</a>";
            }
        },
        {
            field: 'spider_flag',
            title: '爬虫',
            align: 'center',
            valign: 'middle'
        },
        /*{
            title: '操作',
            field: 'vehicleno',
            align: 'center',
            formatter: modifyAndDeleteButton,
            events: PersonnelInformationEvents
        }*/
        ],
    });
}



function init() {
	
	initmenu($("#menuul"),"page/portal/");
	
	initDetailTable();
	
	var $scope = angular.element(ngSection).scope();
	$scope.$apply(function() {

		$scope.effectDate = getNowFormatDate();
		$scope.effectDate2 = getNowFormatDate();
		
		
		$('#collapseOne').on('shown.bs.collapse', function () {
		    // 执行一些动作...
			$("#titlepic").attr("class", "glyphicon glyphicon-chevron-up pull-right"); 
		});
		
		$('#collapseOne').on('hidden.bs.collapse', function () {
			$("#titlepic").attr("class", "glyphicon glyphicon-chevron-down pull-right"); 
	});
		
		
		$scope.date_type2 = [ {
			"value" : "HOUR",
			"name" : "小时"
		}, {
			"value" : "DAY",
			"name" : "天"
		}, {
			"value" : "MONTH",
			"name" : "月"
		}];
		  setTimeout(function(){
			   $("#dateType").get(0).selectedIndex=1;
			   $("#dateType").trigger('change');
		   }, 50);
	
		
		

		$scope.title="访问统计";
		//$scope.curpage=1;
		$scope.page=1;
		$scope.rows=10;
		
		$scope.rows_select=[5,10,20];
		  setTimeout(function(){
			   $("div.tablefoot select").val($scope.rows);
		   }, 50);
		$scope.pageData=[];
		
	
		
		$scope.getsdata = function () {

			var http = getImUrl();// "";

			var obj = new Object();


			SZUMWS(http + "statistics/GetRecentVisitData.action", JSON
				.stringify(obj), function succsess(json) {

					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
						+ message);
					if (code == 200) {
						
						var ydata=eval(json.y)||{};
						var ndata=eval(json.n)||{};
						

					var	yu=ydata.userVisitNum||0;
						
						
						
					var data= "昨日新增独立ip访问："+ (ydata.userVisitNum||0)+ "(attack:"+(ydata.attackVisitNum||0)+") 页面访问："+ (ydata.pageVisitNum||0) +"(attack:"+(ydata.attackPageVisitNum||0)+")<br>";
					data+= " / 今日新增独立ip访问："+ (ndata.userVisitNum||0)+ "(attack:"+(ndata.attackVisitNum||0)+") 页面访问："+ (ndata.pageVisitNum||0)  +"(attack:"+(ndata.attackPageVisitNum||0)+")";

$("#sdata").html(data);

					} else {
						msg(message);
					}

					


				}, function error(data) {
					msg("网络异常!");

				

				}, false, false

			);

		}
		$scope.getsdata();
		
		
		$scope.getTypeList = function (id, fucOnFinished, clear) {

			var http = getImUrl();// "";

			var obj = new Object();

			//obj.deviceid = $scope.id;// "12345678";
			//obj.ip = $scope.ip;
			//obj.compy_name = $scope.compy_name;
			obj.page = 1;// 1;// "12345678";
			obj.rows = 40;// 10;// "12345678";

			SZUMWS(http + "statistics/getTypeInfoList.action", JSON
				.stringify(obj), function succsess(json) {

					var code = json.ResponseCode;
					var message = json.ResponseMsg;
					console.log('-----return -code= ' + code + ';message= '
						+ message);
					if (code == 200) {


						$scope.types_select = eval(json.datalist);

						 setTimeout(function(){
								
							  // $('#p_proxyserver_id').find("option:selected").attr("selected", false);
							   $('#s_type').get(0).selectedIndex=1;
							   // alert($("#p_proxyserver_id").val());
							    $scope.s_type=$("#s_type").val();
							  
							    
							    
							    $scope.getList("homepage_","首页",'pchart1');
							    $scope.getList("detailpag_","详情",'pchart2');
							    $scope.getList("G搜索_","搜索",'pchart3');
							    $scope.getList("attack_","异常",'pchart4');
							    $scope.getList("about_","关于",'pchart5');
							    
						   }, 30);
						

						$scope.$apply();

						console.log('-----guideList -OK= ');

					} else {
						msg(message);
					}

					


				}, function error(data) {
					msg("网络异常!");

				

				}, false, false

			);

		}
		$scope.getTypeList();
		//$scope.getcompayList();
		
	$scope.showaction=function(id,date){
			
			$("#myModal_detail").modal('show');
			var http = getImUrl();// "";
			 var opt = {
				        url:http+ "statistics/GetActionList.action",
				    };
				    $("#table_detail").bootstrapTable('refresh', opt);
	};
		
		
		
		
		$scope.getDetailList=function(id,date,ele){
			
			$("#myModal1").modal('show');
			
	    $scope.page2 = (id != null) ? id :1;
			
			
			if($scope.page2>$scope.pageNum2)
				$scope.page2=$scope.page2-1;
			
			if($scope.page2<=0)
				$scope.page2=1;
			
			

			var http = getImUrl();// "";

			if(typeof(date)!="undefined")
				{
				$scope.detaildate=date;
				}
			
			$("#"+ele).attr("type");//obj.qType);
			$("#"+ele).attr("title");//,title);
			
			var obj = new Object();
			obj.date =$scope.detaildate;// "12345678";
			//obj.date2 = $("#effectDate2").val();// "12345678";
			obj.date_type = $("#dateType").val();
			obj.qName =$("#"+ele).attr("title");// $("#s_type ").find("option:selected").text();
			
			obj.qType =$("#"+ele).attr("type");//$("#s_type").val();
			//obj.showall = $scope.showall?"false":"true";
			
			//msg(obj.showall);
			
			
			obj.page = $scope.page2;// 1;// "12345678";
			obj.rows = $scope.rows2;// 10;// "12345678";
			SZUMWS(http + "statistics/getDetailList.action", JSON
					.stringify(obj), function succsess(json) {
				// var json = JSON.parse(decryData);
				var code = json.ResponseCode;
				var message = json.ResponseMsg;
				console.log('-----return -code= ' + code + ';message= '
						+ message);
				if (code == 200) {

			
					$scope.detaillist =eval(json.datalist);
					
					
					
					$.each($scope.detaillist,function(index,item){
						if(obj.qType.indexOf('detailpag')>-1)
							{
							$scope.detaillist[index].total_uv="<a href='#' onclick='showaction(\""+$scope.detaillist[index].userid+"\")'>"+$scope.detaillist[index].total_uv+"</a>";
							}
						else
							$scope.detaillist[index].total_uv="<span>"+$scope.detaillist[index].total_uv+"</span>";
					})
					
					//setchartdata(json.datalist);

					$scope.total2 = json.total;
					$scope.pageDataPre2=[];
					$scope.pageDataAft2=[];
					$scope.pageNum2=Math.ceil( $scope.total2/$scope.rows2);// + ($scope.total%$scope.rows)>0?1:0;
					
					for(var i =$scope.page2-3;i<$scope.page2;i++){
						if(i>0)
						   $scope.pageDataPre2.push(i);
					}
					for(var i =$scope.page2+1;i<$scope.page2+3;i++){
						if(i<=$scope.pageNum2)
						   $scope.pageDataAft2.push(i);
					}
				
						
						$scope.$apply();
						
					console.log('-----guideList -OK= ');

				} else {
					msg(message);
				}

			

				//$('#refresh').removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, function error(data) {
				msg("网络异常!");

			
			//	$("#refresh").removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, false, "json"

			);
		}
		
		
		$scope.getTList=function(){
		    
		    $scope.getList("homepage_","首页",'pchart1');
		    $scope.getList("detailpag_","详情",'pchart2');
		    $scope.getList("G搜索_","搜索",'pchart3');
		    $scope.getList("attack_","异常",'pchart4');
		    $scope.getList("about_","关于",'pchart5');
		};
		
		$scope.getList = function(type,title,ele, id, fucOnFinished, clear) {

			
			
			
			$scope.page = (id != null) ? id :1;
			
			
			if($scope.page>$scope.pageNum)
				$scope.page=$scope.page-1;
			
			if($scope.page<=0)
				$scope.page=1;
			
			

			var http = getImUrl();// "";

			var obj = new Object();
			obj.date1 = $("#effectDate").val();// "12345678";
			obj.date2 = $("#effectDate2").val();// "12345678";
			obj.date_type = $("#dateType").val();
			obj.qName = $("#s_type ").find("option:selected").text();
			
			obj.qType =type || $("#s_type").val();
			//obj.showall = $scope.showall?"false":"true";
			
			//msg(obj.showall);
			
			
			obj.page = $scope.page;// 1;// "12345678";
			obj.rows = $scope.rows;// 10;// "12345678";
			SZUMWS(http + "statistics/getAppStaticData.action", JSON
					.stringify(obj), function succsess(json) {
				// var json = JSON.parse(decryData);
				var code = json.ResponseCode;
				var message = json.ResponseMsg;
				console.log('-----return -code= ' + code + ';message= '
						+ message);
				if (code == 200) {

			
					$scope.datalist =eval(json.rows);
					
					setchartdata(json.rows,title,ele);
					
					
					$("#"+ele).attr("type",obj.qType);
					$("#"+ele).attr("title",title);
					

					$scope.total = json.total;
					$scope.pageDataPre=[];
					$scope.pageDataAft=[];
					$scope.pageNum=Math.ceil( $scope.total/$scope.rows);// + ($scope.total%$scope.rows)>0?1:0;
					
					for(var i =$scope.page-3;i<$scope.page;i++){
						if(i>0)
						   $scope.pageDataPre.push(i);
					}
					for(var i =$scope.page+1;i<$scope.page+3;i++){
						if(i<=$scope.pageNum)
						   $scope.pageDataAft.push(i);
					}
				
						
						$scope.$apply();
						
					console.log('-----guideList -OK= ');

				} else {
					msg(message);
				}

			

				//$('#refresh').removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, function error(data) {
				msg("网络异常!");

			
			//	$("#refresh").removeClass('visible');
				//$('#refresh2').removeClass('visible');

			}, false, "json"

			);

		}
		//$scope.apply();
		
		//$scope.getList();
		
		
		return;
		

	});

}

// filter
app.filter("sanitize", [ '$sce', function($sce) {
	return function(htmlCode) {
		return htmlCode ? $sce.trustAsHtml(htmlCode) : "";
	}
} ]);
