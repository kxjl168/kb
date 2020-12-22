var myChart=null;
function setchartdata(jdata,title,ele) {


	

	var option = {
	    title : {
	        text: '年度统计',
	       // subtext: '',
	       // x:'center'
	    },
		tooltip : {

			trigger : 'axis'

		},
	    legend: {
	        type: 'scroll',
	        //orient: 'vertical',
	        top: 30,
	        //top: 20,
	        //bottom: 20,
	        data: [],

	       
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
	    xAxis : {

			// type:'time',
			axisLabel : {
				rotate : 0
		
			},
			data : [ "水费查询", ]
		},
		yAxis : {},
	    series : [
	        {
	            name: '收入',
	            type: 'line',
	            smooth : true,
		
	            data: [],
	            itemStyle: {
	            	normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c} '
						}
					}
	            }
	        },
	        {
	            name: '支出',
	            type: 'bar',
	         
	            data: [],
	            itemStyle: {
	            	normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c} '
						}
					}
	            }
	        }
	    ]
	};

		
		option.series[0].data = [];
		option.xAxis.data = [];
		option.legend.data = [];

		//var queryDate1 = $("#effectDate").val();
		//var queryDate2 = $("#effectDate2").val();
		
		option.title.text = title+" 收支";
		
		
		var sdata=[]; //数据
		var zhichudata=[];
		var ldata=[];//legend数据
		var selected={};//选中数据
		
		sdata.push({name:"分类1",value:1});
		sdata.push({name:"分类2",value:2});
		sdata.push({name:"分类3",value:4});
		option.series[0].data=sdata;
		
	
		ldata.push("分类1");
		ldata.push("分类2");
		ldata.push("分类3");
		option.legend.data =ldata;
		
	
		//selected["分类1"]=true;
		//selected["分类2"]=true;
		//selected["分类3"]=true;
		//option.legend.selected =selected;
		
		 sdata=[]; //数据
		 ldata=[];//legend数据
		// selected={};//选中数据
		$.each(jdata.rows,function(index,item){
			
			
			
			sdata.push({name:item.month,value:Math.abs(item.in_money)});

			zhichudata.push({name:item.month,value:Math.abs(item.out_money)});
			
			option.xAxis.data.push(item.mDate);
			
			ldata.push("收入","支出");
			
		});
		option.series[0].data=sdata;
		option.series[1].data=zhichudata;
		//option.legend.selected =selected;
		option.legend.data =ldata;
		
		var id=ele||'pchart';
		
		
		if(!myChart)
			{
			$("#"+id).html('');
			myChart = echarts.init(document.getElementById(id));
			myChart.on('click', function(params) {
				// 控制台打印数据的名称
				// msg(params.data.id);
				// getDetailList(1,params.name,id);
				
				return;
				
				var opt = {
						silent : true,
						query:{
						 mType:	params.data.id,
						}
					};
					$("#table_list_item").bootstrapTable('refresh', opt);
				
			});
			}
	
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		
	}

