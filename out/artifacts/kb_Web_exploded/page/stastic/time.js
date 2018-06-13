
function initDateSelect()
{

	
	//---------------------------------------------------  
	// 日期格式化  
	// 格式 YYYY/yyyy/YY/yy 表示年份  
	// MM/M 月份  
	// W/w 星期  
	// dd/DD/d/D 日期  
	// hh/HH/h/H 时间  
	// mm/m 分钟  
	// ss/SS/s/S 秒  
	//---------------------------------------------------  
	Date.prototype.Format = function(formatStr)   
	{   
	    var str = formatStr;   
	    var Week = ['日','一','二','三','四','五','六'];  
	  
	    str=str.replace(/yyyy|YYYY/,this.getFullYear());   
	    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));   
	  
	    str=str.replace(/MM/,this.getMonth()+1>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));   
	    str=str.replace(/M/g,this.getMonth()+1);   
	  
	    str=str.replace(/w|W/g,Week[this.getDay()]);   
	  
	    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
	    str=str.replace(/d|D/g,this.getDate());   
	  
	    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
	    str=str.replace(/h|H/g,this.getHours());   
	    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
	    str=str.replace(/m/g,this.getMinutes());   
	  
	    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   
	    str=str.replace(/s|S/g,this.getSeconds());   
	  
	    return str;   
	}   

	
	function setTimeFormat(id,min,max) {
		var datetype = $("#dateType").val();

		var startDate = '%yyyy-%MM-%dd %HH';
		var dateFmt = 'yyyy-MM-dd HH';

		if (datetype == "HOUR") {
			startDate = '%yyyy-%MM-%dd %HH';
			dateFmt = 'yyyy-MM-dd HH';
		} else if (datetype == "DAY") {
			startDate = '%yyyy-%MM-%dd';
			dateFmt = 'yyyy-MM-dd';
		} else if (datetype == "MONTH") {
			startDate = '%yyyy-%MM';
			dateFmt = 'yyyy-MM';
		}
		
	
		var param={
			startDate : startDate,
			dateFmt : dateFmt,
			el:id
		};
		if(min!=null)
			param.minDate='#F{$dp.$D(\''+min+'\')}';
		if(max!=null)
			param.maxDate='#F{$dp.$D(\''+max+'\')}';
		
	
			WdatePicker(param);	
			
		
	}
	
	$('#effectDate').bind('focus', function() {
		setTimeFormat('effectDate',null,'effectDate2');
	
	});
	$('#effectDate2').bind('focus', function() {
		setTimeFormat('effectDate2','effectDate',null);
		// WdatePicker({ dateFmt: 'yyyy-MM', autoPickDate: true });
	});
	$('#effectDate_img').bind('click', function() {
		$("#effectDate").focus();
		// WdatePicker({ dateFmt: 'yyyy-MM', autoPickDate: true });
	});
	$('#effectDate2_img').bind('click', function() {
		$("#effectDate2").focus();
		// WdatePicker({ dateFmt: 'yyyy-MM', autoPickDate: true });
	});
	// 添加回车事件
	$("#dateType").bind('change', function(event) {

		var datetype = $("#dateType").val();
		var now=new Date();
		var before=new Date();
		var format = "yyyy-MM-dd HH";
		if (datetype == "HOUR")
			{
			format = "yyyy-MM-dd HH";
			before.setHours(before.getHours()-10);
			}
			
		else if (datetype == "DAY")
			{
			before.setDate(before.getDate()-10);
			format = "yyyy-MM-dd";
			}
			
		else if (datetype == "MONTH")
			{
			before.setMonth(now.getMonth()-3);
			format = "yyyy-MM";
			}
			

		var begindate =before.Format(format);
		var enddate = now.Format(format);
		
		$("#effectDate").val(begindate);
		$("#effectDate2").val(enddate);

		$('#effectDate').unbind('focus');
		$('#effectDate').bind('focus', function() {
			setTimeFormat('effectDate',null,'effectDate2');
			// WdatePicker({ dateFmt: 'yyyy-MM', autoPickDate: true });
		});

		$('#effectDate2').unbind('focus');
		$('#effectDate2').bind('focus', function() {
			setTimeFormat('effectDate2','effectDate',null);
			// WdatePicker({ dateFmt: 'yyyy-MM', autoPickDate: true });
		});

	
	});
}

$(function()
{
		
});
