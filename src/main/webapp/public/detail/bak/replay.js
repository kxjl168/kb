$(function() {

	initReplayModel();

	var $scope = angular.element(ngSection).scope();
	kvalidate.init($("#fm"), {
		s_uid : {
			required : true,
			maxlength : 40,

		},
		s_ublog : {
			required : false,
			maxlength : 100,

		},
		s_text : {
			required : true,
			minlength : 5,
			maxlength : 500,
		},
		s_email : {
			
			maxlength : 100,
			email :true,
			required : true,
		}

	}, {
		s_uid : {
			required : "大侠请留名！",
			maxlength : "字数有点太多了-_-！",
		},
		s_ublog : {

			maxlength : "字数有点太多了-_-！",
		},
		s_text : {
			required : "写几个字吧 ",
			minlength : "再多写两个字呗 ",
			maxlength : "字数有点太多了-_-！",

		},
		s_email : {
			required : "留下邮箱呗~ ",
			maxlength : "您的邮箱看起来非常非常的大... -_-!",
		}
	}, $scope.doupdate, "", "");

});

pass = function(event, id) {

	var obj = {};
	var http = getImUrl();
	obj.id = id;
	obj.state = "1";

	$("#myModal3").modal("show");
	$("#btnconfirm")
			.one(
					"click",

					function() {
						SZUMWS(http + "replay/updateState.action", JSON
								.stringify(obj),
								function succsess(json) {

									var code = json.ResponseCode;
									var message = json.ResponseMsg;
									//console.log('-----return -code= ' + code
									//		+ ';message= ' + message);
									if (code == 200) {

										var $scope = angular.element(ngSection)
												.scope();

										setTimeout(function() {
											$("#myModal3").modal("hide");
										}, 10);

										$scope.getReplayList();

									} else {
										msg(message);
									}

								}, function error(data) {
									msg("net work error！");

								}, false, false

						);

					});

};

del = function(event, id) {
	var obj = {};
	var http = getImUrl();
	obj.id = id;
	obj.state = "1";

	$("#myModal3").modal("show");
	$("#btnconfirm")
			.one(
					"click",

					function() {
						SZUMWS(http + "replay/del.action", JSON.stringify(obj),
								function succsess(json) {

									var code = json.ResponseCode;
									var message = json.ResponseMsg;
									//console.log('-----return -code= ' + code
									//		+ ';message= ' + message);
									if (code == 200) {

										var $scope = angular.element(ngSection)
												.scope();

										setTimeout(function() {
											$("#myModal3").modal("hide");
										}, 10);

										$scope.getReplayList();

									} else {
										msg(message);
									}

								}, function error(data) {
									msg("net work error！");

								}, false, false

						);

					});
};

canc = function(e, x) {
	var row = $("#rpdiv");

	kvalidate.resetForm("#fm");
	$("#rdivc").after(row);
	$("#cbtn").addClass("hide");
};


beginReplay = function(e, mainid, preid) {
	var $scope = angular.element(ngSection)
	.scope();
	$scope.preplay={};
	$scope.preplay.recordid=mainid;
	
	$scope.preplay_main ={};
	$scope.preplay_main.recordid =preid;
	
	var row = $("#rpdiv");

	($(e).parent()).after(row);

	$("#cbtn").removeClass("hide");

};

function initReplayModel() {
	
	
	$("#btnChangeName").click(function(){
		if($(".nameinfo").is(':visible'))
			$(".nameinfo").hide();
		else
			$(".nameinfo").show();
	});
			
	
	
	var $scope = angular.element(ngSection).scope();
	$scope
			.$apply(function() {
				$scope.replay = function(x) {

					kvalidate.validate("#fm");

				};

				var http = getImUrl();

				

				$scope.getCookie = function() {

					var obj = {};

		
					SZUMWS(http + "sysBaseInfo/getCookie.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						//console.log('-----return -code= ' + code + ';message= '
						//		+ message);
						if (code == 200) {

							//msg("感谢您的关注,评论等待审核中...");

							var uemail=json.uemail;
							var uname=json.uname;
							var usite=json.usite;
							
							$("#s_uid").val(unescape(uname));
							$("#s_ublog").val(unescape( usite));
							$("#s_email").val(uemail);
							$("#s_icon").val(json.uicon);
							
							$scope.s_uid=unescape(uname);
							$scope.s_ublog=unescape( usite);
							$scope.s_email=uemail;
							$scope.s_icon=json.uicon;
						
							if(uname!=null)
							{
								$(".nameinfo").hide();
								$(".ckdisplay").show();
							}
							else
								{
								$(".nameinfo").show();
								$(".ckdisplay").hide();
								}

						} else {
						//	msg(message);
						}

					}, function error(data) {
						//msg("net work error！");

					}, false, false

					);

				};
				$scope.getCookie();
				
				
				
				
				
				$scope.doupdate = function(fm) {

					var obj = {};

					if (typeof ($scope.preplay) == "undefined") {

					} else {
						obj.pid = $scope.preplay.recordid;
					}

					if (typeof ($scope.preplay_main) == "undefined") {

					} else {
						obj.ppid = $scope.preplay_main.recordid;
					}

					obj.imei = $scope.x.imei;
					obj.userid = escape($scope.s_uid);
					obj.context = escape($scope.s_text);
					obj.ublog = $scope.s_ublog ? escape($scope.s_ublog) : "";
					obj.email = escape($scope.s_email);

					SZUMWS(http + "replay/addOrUpdate.action", JSON
							.stringify(obj), function succsess(json) {

						var code = json.ResponseCode;
						var message = json.ResponseMsg;
						//console.log('-----return -code= ' + code + ';message= '
						//		+ message);
						if (code == 200) {

							msg("感谢您的关注,评论等待审核中...");

							setTimeout(function() {
								//$scope.s_uid = "";
								$scope.s_text = "";
								//$scope.s_ublog = "";
								//$scope.s_email = "";
								$scope.canc();

								$scope.$apply();
								
								$scope.getReplayList();
								
								$(".nameinfo").hide();
								$(".ckdisplay").show();
								
							}, 10);

						

						} else {
							msg(message);
						}

					}, function error(data) {
						msg("net work error！");

					}, false, false

					);

				};

				$scope.gourl = function(url) {
					var row = $("#rpdiv");

					if (url.indexOf("http") > -1) {
						$("#myModal4").modal("show");
						$("#btnconfirm4").one("click", function() {

							window.open(url, 'new', "")
						});
					} else {
						return false;
						// window.location.hash = "#commet";
					}
				};

				$scope.canc = function(event, x) {
					var row = $("#rpdiv");

					kvalidate.resetForm("#fm");
					$("#rdivc").after(row);
					$("#cbtn").addClass("hide");
				};

				$scope.beginReplay = function(event, x, t) {
					$scope.preplay = x;
					$scope.preplay_main = t;
					var row = $("#rpdiv");

					($(event.target).parent()).after(row);

					$("#cbtn").removeClass("hide");

				};

				$scope.getReplayList = function() {

					var http = getImUrl();

					var obj = new Object();

					obj.imei = $scope.x.imei;

					obj.page = $scope.rpage;
					obj.rows = $scope.rrows;
					SZUMWS(
							http + "replay/getInfoList.action",
							JSON.stringify(obj),
							function succsess(json) {

								var code = json.ResponseCode;
								var message = json.ResponseMsg;
								//console.log('-----return -code= ' + code
							//			+ ';message= ' + message);
								if (code == 200) {
									
									
									

									$scope.rdatalist = eval(json.datalist);
									$scope.rnum = $scope.rdatalist.length;
									$scope.root = json.isRoot;
									
									
									

									$
											.each(
													$scope.rdatalist,
													function(index, item) {
														$scope.rdatalist[index].content = unescape(item.content);
														$scope.rdatalist[index].userid = unescape(item.userid);
														//$scope.rdatalist[index].icon ="https://www.gravatar.com/avatar/"+item.icon+"?s=100&r=pg&d=identicon";//unescape(item.userid);
														$scope.rdatalist[index].user_blog = item.user_blog ? unescape(item.user_blog)
																: "";
														
														
														$
																.each(
																		$scope.rdatalist[index].reback,
																		function(
																				index2,
																				item2) {
																			$scope.rdatalist[index].reback[index2].content = unescape(item2.content);
																			$scope.rdatalist[index].reback[index2].userid = unescape(item2.userid);
																			$scope.rdatalist[index].reback[index2].user_blog = item2.user_blog ? unescape(item2.user_blog)
																					: "";
																			$scope.rdatalist[index].reback[index2].tuid = unescape(item2.tuid);
																			$scope.rdatalist[index].reback[index2].tuser_blog = item2.tuser_blog ? unescape(item2.tuser_blog)
																					: "";

																		});

													});
									$scope.$apply();
									
									$scope.buildReplay();

								} else {
									msg(message);
								}

							}, function error(data) {
								msg("net work error！");

							}, false, false

					);

				};
				
				
				
				$scope.buildReplay = function() {
					
			
					var html="";
					
					$.each($scope.rdatalist,function(index,item){
						
						var isauthor=false;
	            		var authorhtml="";
	            		if(item.email=="kxjl168@foxmail.com")
	            			{
	            			isauthor=true;
	            			authorhtml="<span class='author'>博主</span> ";
	            			}
	            		
						var icon ="https://www.gravatar.com/avatar/"+item.icon+"?s=40&r=pg&d=identicon";//unescape(item.userid);
						
						html+='<div class="row col-xs-12 ">'
						 

			             +'    <div class="row col-lg-11 replayblock b1"> '

			             +'<div class="pull-left">'
			             +'<a id="f'+(item.recordid)+'"   href="'+item.user_blog+'"   onclick="return gourl(this);"  title="'+item.userid+'-'+item.user_blog+'"><img class="rheadpic" src="'+icon+'">'+' </a>'
			             +'</div>'
			             
			             +'<div class="col-xs-11">'
			             
			           

			             +'        <div class="hide" >'+($scope.rnum-index)+'楼&nbsp;<a  href="'+item.user_blog+'" title="'+item.user_blog+'">'+item.userid+'</a> <span class="pull-right text-right">&nbsp;'+item.create_date+'</span> </div> '

			             +'        <div > <a id="f'+(item.recordid)+'"   href="'+item.user_blog+'"   onclick="return gourl(this);"  title="'+item.userid+'-'+item.user_blog+'">'+item.userid+authorhtml+' </a><span class="rptime text-right">&nbsp;'+item.create_date+' </span> ';
			                    
			             if($scope.root)
			            	 {
			            	 html+='       <span  >【  ';
			            	  if(item.state!=1)
			            		  html+='<a   href="javascript:void(0);" class="   text-success margin-right-20" onclick="pass(this,\''+item.recordid+'\')">审核通过</a>&nbsp; ';
			            	  html+='                          <a href="javascript:void(0);" class="   text-warning margin-right-20" onclick="del(this,\''+item.recordid+'\')">删除</a>'
				             +'】 '
				             +'                           </span> ';	
				            
			            	 }
			             
			             html+='    </div> '

			             +'    		<div class="rcct">'+item.content+'</div> '
			             +'         <div class="row  margin-bottom-5"> '

			             +'         <a href="#f'+(item.recordid)+'" class="ba1 replaybtn  text-info margin-right-20" onclick="beginReplay(this,\''+item.recordid+'\')">回复</a> '
			                    
			             +'       	</div> '

			             +'   		<div class=" rcblock"> ';
			             
			             
			             $.each(item.reback,function(iindex,t)
			            		 {
			            	 
			            		var ricon ="https://www.gravatar.com/avatar/"+t.icon+"?s=35&r=pg&d=identicon";//unescape(item.userid);
			            		var authorhtmlReplay=" ";
			            		var isauthorReplay=false;
			            		
			            		if(t.email=="kxjl168@foxmail.com")
			            			{
			            			isauthorReplay=true;
			            			var authorhtmlReplay="<span class='author'>博主</span> ";
			            			}
			            			
			            		
			            		
			            	   html+= '    <div  class="replayblock b2 row"> '
			            		   
			            		
			            		   +'<div class="pull-left">'
						             +'<a id="f'+(t.recordid)+'"  href="'+t.user_blog+'"  onclick="return gourl(this)"   title="'+t.userid+'"><img class="rheadpic" src="'+ricon+'">'+'</a> '
						             +'</div>'
						             
						             +'<div class="col-xs-11">'
			            		   
					             +'                <div ><a id="f'+(t.recordid)+'"  href="'+t.user_blog+'"  onclick="return gourl(this)"   title="'+t.userid+'">'+t.userid+authorhtmlReplay+'</a> '
					             +' <span class="rptime text-right">&nbsp;'+t.create_date+'</span> ';
					         
					             if($scope.root)
				            	 {
				            	 html+='       <span  >【  ';
				            	  if(t.state!=1)
				            		  html+='<a     href="javascript:void(0);" class="   text-success margin-right-20" onclick="pass(this,\''+t.recordid+'\')">审核通过</a>&nbsp; ';
				            	  html+='                          <a href="javascript:void(0);" class="   text-warning margin-right-20" onclick="del(this,\''+t.recordid+'\')">删除</a>'
					             +'】 '
					             +'                           </span>';
					          			
				            	 }
			            	  
					             
					             
			            	   html+= '       		</div>'
					             +'              	<div class="rcct"><span class="at">@</span> <a href="'+t.tuser_blog+'" title="'+t.tuser_blog+'-'+t.tuid+'">'+t.tuid+'</a>'+t.content+'</div> '
					             +'              	 <div class="row  margin-bottom-5"> '

					             +'                    <a href="javascript:void(0)" class=" replaybtn text-info margin-right-20" onclick="beginReplay(this,\''+t.recordid+'\',\''+item.recordid+'\')">回复</a> '
					                            
					             +'                 	</div> '
					             +'                 	</div> '
					             +'            </div> ';
					           
					             
			            		 });
			             
			          
			             html+= '     </div> '
			            	  +"</div>"
			             +'    </div> '
			            +'    </div>  ';
						
					});
					
					$("#cblock").html(html);
					setTimeout(function() {
						var tp=window.location.hash;
						window.location.hash="#1";
						window.location.hash=tp;
						
						//alert(location.hash);
					}, 1000);
					
					
					
					
				};


			});
	
	
	

};




