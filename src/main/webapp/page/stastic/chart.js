
 var myCharts=[];
function setchartdata(jdata, title, ele) {
	// if (jdata == null)
	// jdata = {
	// Time : "9月13号",
	// rows : [ {
	// typeName : "app1",
	// pv : "61",
	// uv : "44"
	// }, {
	// typeName : "app2",
	// pv : "63",
	// uv : "42"
	// }, {
	// typeName : "app3",
	// pv : "83",
	// uv : "22"
	// }, {
	// typeName : "app4",
	// pv : "33",
	// uv : "12"
	// }, {
	// typeName : "app5",
	// pv : "23",
	// uv : "12"
	// } ]
	//
	// }

	// 指定图表的配置项和数据
	var option = {
		title : {
			text : '天粒度统计'
		},
		tooltip : {

			trigger : 'axis'

		},
		toolbox : {
			show : true,
			feature : {

				magicType : {
					type : [ 'line', 'bar' ]
				},
				restore : {},
				saveAsImage : {}
			}
		},
		legend : {
			data : [ '累计访问数据（pv）-9月1号', '累计访问数据（uv）-9月1号' ],
			type : 'scroll',
			top : 30,
		},
		xAxis : {

			// type:'time',
			axisLabel : {
				rotate : 0
			// formatter : function(value, index) {
			// if (value !== undefined)
			// return value + '时';
			// }
			// interval:0,
			// formatter: function (value, index) {
			// // 格式化成月/日，只在第一个刻度显示年份
			// var date = new Date(value);
			// var texts =date.getHours();// [(date.getMonth() + 1),
			// date.getDate()];
			// if (index == 0) {
			// // texts.unshift(date.getYear());
			// texts=date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
			// }
			// return texts;//.join('/');
			// }
			},
			data : [ "水费查询", "公积金", "社保查询", "云盾", "MOA", "APP1" ]
		},
		yAxis : {},
		series : [ {
			name : '累计访问数据（pv）-9月1号',
			type : 'bar',
			smooth : true,
			// stack : '总量',
			data : [ 35, 25, 36, 10, 33, 29 ],
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'top',
						formatter : '{c} '
					}
				}
			}
		}, {
			name : '累计访问数据（uv）-9月1号',
			type : 'bar',
			smooth : true,
			// stack : '总量',
			data : [ 22, 10, 26, 6, 20, 20 ],
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'top',
						formatter : '{c} '
					}
				}
			}
		} ]
	};

	option.xAxis.data = [];
	option.series[0].data = [];
	option.series[1].data = [];
	option.legend.data = [];

	var queryDate1 = $("#effectDate").val();
	var queryDate2 = $("#effectDate2").val();

	var ctitle = title || $("#dateType option:selected").text()

	option.title.text = ctitle + "访问统计";
	option.legend.data.push('累计访问量（pv）');
	option.legend.data.push('24小时独立访问量（uv）');
	option.series[0].name = '累计访问量（pv）';
	option.series[1].name = '24小时独立访问量（uv）';

	// 首页
	if (ele == "pchart1" || ele == "pchart2") {
		option.series[0].type = 'line';
		option.series[1].type = 'line';

		option.series[2] = {
			data : [],
			type : 'bar',
			name : '用户pv',
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'top',
						formatter : '{c} '
					}
				}
			}
		}; // ,itemStyle:{color:'blue'}
		option.series[3] = {
			data : [],
			type : 'bar',
			name : '用户uv',
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'top',
						formatter : '{c} '
					}
				}
			}
		}; // ,itemStyle:{color:'green'}

		option.legend.data.push("用户pv");
		option.legend.data.push("用户uv");

	}

	if (jdata.length != 0) {

		$.each(jdata, function(index, data) {
			var i = data;

			option.xAxis.data.push(i.typeName);
			option.series[0].data.push(i.pv);
			option.series[1].data.push(i.uv);

			if (ele == "pchart1" || ele == "pchart2") {
				option.series[2].data.push(i.upv);
				option.series[3].data.push(i.uuv);
			}

		});
	}
	
	
	if(ele=="pchart8") //订阅
		{
		option.series.splice(1,1);//删除第二个
		option.series[0].type="line";
		}
	
	if(ele=="pchart6"||ele=="pchart7") //爬虫统计
		{
		
		var xdatas=[];
		var serialnames=[];
		var lgselected={};
		var lseries=[];
		var attrs=[];
		
		$.each(jdata, function(index, data) {
			
			if(!contains(xdatas,data.typeName))
				xdatas.push(data.typeName);
			
			var sname=data.type_second.substr(7);
			if(!contains(serialnames,sname))
				serialnames.push(sname);
		
		});
		
		
		
		$.each(serialnames,function(index,aitem){
		var spidername=	aitem;
			
						
			var seriesdata={};
			
			
			seriesdata.data=[];
			seriesdata.smooth=true;
			seriesdata.name=spidername; //spider_
			seriesdata.type="line";//  typeof(aitem.stype)=="undefined"?"line":aitem.stype;

			$.each(xdatas, function(index, time) {
				seriesdata.data.push(getSpiderData(jdata,time,"spider_"+aitem));
			})
			
			lseries.push(seriesdata);

		});
		
		option.xAxis.data=xdatas;
		option.series=lseries;
		option.legend.data = serialnames;
		
		
		}
	
	
	
	
	
	

	var id = ele || 'pchart';

	
	
	var myChart=myCharts[id];
	if(!myChart)
	{
	$("#"+id).html('');
	myChart = echarts.init(document.getElementById(id));
	myChart.on('click', function(params) {
		// 控制台打印数据的名称
		// msg(params.data.id);
		// getDetailList(1,params.name,id);
		
		refreshHourTable(1, params.name, id);
		
	});
	myCharts[id]=myChart;
	
	}

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
}


function contains(arr, obj) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === obj) {
            return true;
        }
    }
    return false;
}

function getSpiderData(data,time,name)
{
	var value="";
  	$.each(data,function(index,item){
  		if(item.typeName==time &&item.type_second==name)
  			value=item.pv;
  	});
  	return value;
}
