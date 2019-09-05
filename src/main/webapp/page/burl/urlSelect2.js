/**
 * 房间界面 select2相关
 */

//查询条件 小区选择select
function initTypeSelect() {

	$("#q_type").select2({
		// dropdownParent : $("#myModal"),
		placeholder : "选择分类",
		minimumInputLength : 0,
		maximumSelectionLength : 2,
		// minimumResultsForSearch: Infinity,
		theme : "bootstrap",
		multiple : false,
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
				return "载入更多结果…"
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
			url : getImUrl()+"kurl/getSelectGroupInfoList.do",
			dataType : "json",
			data : function(params) {

				//var params=eval(strparams);
				
				var page = params.page || 0;

				var query = {
						url_name : params.term,
					page : page,

					pageCount : 1000,

				}

				// Query parameters will be ?search=[term]&type=public
				return query;
			},

			processResults : function(data, params) {
				var selectdatas = [];
				
			
				$.each( data, function(index, itemstr) {
					
					item=eval("("+itemstr+")");
					
					var clds= eval(item.child);
					
					var clditems=[];
					
					$.each( clds, function(index2, item2) {
						
						clditems.push({
							id : item2.id,
							text : item2.url_name,
							icon: item.httppath+ item2.icon,
						});
					});
					
					
					
					selectdatas.push({
						
						text : item.url_name,
						children : clditems,
						
					});
				});

				return {
					results : selectdatas,
					pagination : {
						more : (params.page * 10 >= data.total) ? false : true
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
		
		var cids = $("#q_type").val();
		var id=(cids == null || cids.length == 0) ? ""
				: cids[0];
		
		id=cids;
		var text=$("#q_type").select2('data')[0].text;
		setTimeout(function() {
			
			//window.location.hash="#"+id;
			//window.location.hash="#"+text;
			
		
			$("html, body").animate({scrollTop: $("#"+text).offset().top -20+ "px"}, 500);
			
			
			//alert(location.hash);
		}, 50);
		
		
		
		//destoryRoomQ_SelectBuildingquery();
		//initRoomQ_SelectBuildingquery();
	});



}


function formatRepo(repo) {
	if (repo.loading) {
		return repo.text;
	}

	var markup = repo.text;

	if (repo.id == -1) {
		markup = '<button type="button "  style=" width: 100%;" class=" btn btn-danger btn-warning " data-dismiss="modal ">'
				+ repo.text + '</button>';
	}

	return markup;
}
