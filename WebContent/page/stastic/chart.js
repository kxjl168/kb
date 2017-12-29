function setchartdata(jdata) {
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
				data : [ '累计访问数据（pv）-9月1号', '累计访问数据（uv）-9月1号' ]
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
				// stack : '总量',
				data : [ 35, 25, 36, 10, 33, 29 ],
				 itemStyle: {normal: {
			            label : {show:true,position:'top',formatter:'{c} '}
			        }}
			}, {
				name : '累计访问数据（uv）-9月1号',
				type : 'bar',
				// stack : '总量',
				data : [ 22, 10, 26, 6, 20, 20 ],
				 itemStyle: {normal: {
			            label : {show:true,position:'top',formatter:'{c} '}
			        }}
			} ]
		};

		option.xAxis.data = [];
		option.series[0].data = [];
		option.series[1].data = [];
		option.legend.data = [];

		var queryDate1 = $("#effectDate").val();
		var queryDate2 = $("#effectDate2").val();

		option.title.text = $("#dateType option:selected").text() + "访问统计";
		option.legend.data.push('累计访问量（pv）');
		option.legend.data.push('24小时独立访问量（uv）');
		option.series[0].name = '累计访问量（pv）';
		option.series[1].name = '24小时独立访问量（uv）';

		if (jdata.length != 0) {

			$.each(jdata, function() {
				var i = $(this)[0];

				option.xAxis.data.push(i.typeName);
				option.series[0].data.push(i.pv);
				option.series[1].data.push(i.uv);
			});
		}

		$("#pchart").html('');
		myChart = echarts.init(document.getElementById('pchart'));
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		myChart.on('click', function(params) {
			// 控制台打印数据的名称
			 //msg(params.name);
			 getDetailList(1,params.name);
		});
	}

