

function getRPath() {
	var http = basePath ;

	return http;
}



function test(){
	
	
	var url = getRPath()+"/manager/myip/test";
	
	$.ajax({
		type : "post",
		url : url,
		//data : data,
		async : false,
		dataType : "json",
		success : function(response) {
			success("操作成功！");
		}
	});
}



function Main(props)
{
//	const[searchrst,setsearchrst]=useState<any>("");
//	const[page,setpage]=useState<any>("");

	return (
		
			<input />
			
			
	);
}


$(function() {
	debugger;
	const e = React.createElement;

	const domContainer = document.querySelector('#pmain');
	
	ReactDOM.render(<Main/>, domContainer);
	
	//InitQuery_item();

	//initmenu($("#menuul"), "manager/myip/manager/");
});



