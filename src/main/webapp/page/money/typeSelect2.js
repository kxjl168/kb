/**
 * 房间界面 select2相关
 */

//查询条件 小区选择select
function initTypeSelect() {

	$("#mType").select2({
		// dropdownParent : $("#myModal"),
		placeholder : "选择分类",
		minimumInputLength : 0,
		//maximumSelectionLength : 1,
		// minimumResultsForSearch: Infinity,
		theme : "bootstrap",
		//multiple : false,
		language : {
			errorLoading : function() {
				return "无法载入结果。"
			},
			inputTooLong : function(e) {
				var t = e.input.length - e.maximum, n = "请删除" + t + "个字符";
				return n
			},
			inputTooShort : function(e) {
				var t = e.minimum - e.input.length, n = "请再输入至少" + t + "个字符";
				return n
			},
			loadingMore : function() {
				return ""
			},
			maximumSelected : function(e) {
				var t = "";// "最多只能选择" + e.maximum + "个";
				return t
			},
			noResults : function() {
				return "未找到结果"
			},
			searching : function() {
				return "搜索中…"
			}

		},

		ajax : {
			type : "post",
			url : basePath+"/manager/money/moneyTypeList.do",
			dataType : "json",
			data : function(params) {

				//var params=eval(strparams);
				
				var page = params.page || 0;

				var query = {
						name : params.term,
					page : page,

					pageCount : 1000,

				}

				// Query parameters will be ?search=[term]&type=public
				return query;
			},

			processResults : function(data, params) {
				var selectdatas = [];

				/*{ 
				      "text": "Group 1", 
				      "children" : [
				        {
				            "id": 1,
				            "text": "Option 1.1"
				        },
				        {
				            "id": 2,
				            "text": "Option 1.2"
				        }
				      ]
				    },*/
				
				
				
				/*
				 * if(typeof(params.page)=="undefined") selectdatas.push({ id :
				 * -1, text : '新建属性' });
				 */

				$.each( data, function(index, itemstr) {
					
					item=eval("("+itemstr+")");
					
					var clds= eval(item.child);
					
					var clditems=[];
					
					$.each( clds, function(index2, item2) {
						
						clditems.push({
							id : item2.id,
							text : item2.dict_name,
							icon: item.httppath+ item2.val1,
						});
					});
					
					
					
					selectdatas.push({
						
						text : item.dict_name,
						children : clditems,
						
					});
				});

				return {
					results : selectdatas,
					pagination : {
						more :false,// (params.page * 10 >= data.total) ? false : true
					}
				};
			},
		},

		templateResult : formatRepo,
		escapeMarkup : function(markup) {
			return markup;
		}, // let our custom formatter work
		templateSelection : function(data, container) {
			// Add custom attributes to the <option> tag for the selected option
			// $(data.element).attr('data-custom-attribute', data.customValue);

			// $(container).parents("tr").find(".attr_id").val(data.id);
			return data.text;
		},

	});

	// Bind an event
	$('#q_type').on('select2:select', function(e) {
		
		/*var cids = $("#q_type").val();
		var id=(cids == null || cids.length == 0) ? ""
				: cids[0];
		
		id=cids;
		setTimeout(function() {
			
			window.location.hash="#"+id;
			//alert(location.hash);
		}, 50);*/
		
		
		
		//destoryRoomQ_SelectBuildingquery();
		//initRoomQ_SelectBuildingquery();
	});



}


function formatRepo(repo) {
	if (repo.loading) {
		return repo.text;
	}

	var markup = repo.text;

	if (repo.id) {
		markup = '<div class=" row"><img class="pull-left img-responsive" style="max-width:25px;" src="'+repo.icon+'">'
				+ '<div class="col-xs-10">'+repo.text +"</div><div>";
	}

	return markup;
}
