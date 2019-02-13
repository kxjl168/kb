function setchartdata(jdata,title,ele) {


	

	var option = {
	    title : {
	        text: '分类统计',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        type: 'scroll',
	        orient: 'vertical',
	        right: 10,
	        top: 20,
	        bottom: 20,
	        data: [],

	        selected: {}
	    },
	    series : [
	        {
	            name: '分类',
	            type: 'pie',
	            radius : '55%',
	            center: ['40%', '50%'],
	            data: [],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};

		
		option.series[0].data = [];
		
		option.legend.data = [];

		//var queryDate1 = $("#effectDate").val();
		//var queryDate2 = $("#effectDate2").val();
		
		option.title.text = "分类统计";
		
		
		var sdata=[];
		sdata.push({name:"分类1",value:1});
		sdata.push({name:"分类2",value:2});
		sdata.push({name:"分类3",value:4});
		option.series[0].data=sdata;
		
		var ldata=[];
		ldata.push("分类1");
		ldata.push("分类2");
		ldata.push("分类3");
		option.legend.data =ldata;
		
		var selected={};
		selected["分类1"]=true;
		selected["分类2"]=true;
		selected["分类3"]=true;
		option.legend.selected =selected;
		
		var id=ele||'pchart';
		
		$("#"+id).html('');
		myChart = echarts.init(document.getElementById(id));
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		myChart.on('click', function(params) {
			// 控制台打印数据的名称
			 //msg(params.name);
			// getDetailList(1,params.name,id);
		});
	}

