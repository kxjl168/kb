/**
 * Licnese MIT
 * 顶部模拟进度条 
 * 直接在body下方引入js及css即可
 * @author zj 
 * @date 2018-07-14
 * @site http://www.256kb.cn
 */
(function(w){

	Function.prototype.method = function (name, func) {
	    if (!this.prototype[name])
	        this.prototype[name] = func;
	    return this;
	};

	function Ktop(options){
		this.default={
				cur_file_id:1,
			
				
		};
		
	};
	
	Ktop.method("init",function(options) {
		  this.options = $.extend({}, this.default, options);
		  
		  
			var html='<div class="kprodiv"> ' 
				+'<hr class="hrback"> '
				+'		<hr class="hrpro">' 
				+'		<span class="blink"></span>'
				+'</div>';
			$(html).prependTo($("body"));
			
	});
	

	Ktop.method("showpro",function(jsonStr) {
		var me = this;
		
		$("body").find(".kprodiv .hrpro").css("opacity",1)
	});
	
	Ktop.method("hidepro", function(jsonStr) {
		var me = this;
		
		$("body").find(".kprodiv .hrpro").css("opacity",0);
		$("body").find(".kprodiv .blink").hide();
		
		//$("body").find(".kprodiv .hrpro").css("opacity",1);
		//$("body").find(".kprodiv .hrpro").css("width",40 + "%");
	});

	
	Ktop.method("start",function(per) {
		var me=this;
		me.showpro();
		me.proval=0;
		me.animationInterval=20;
			
		me.inter=setInterval(() => {
				me.updatepro(me.proval,me);
				
		/*
		 * if(me.proval==100) clearInterval(inter); me.proval+=5;
		 */
				
			}, me.animationInterval);
		
	});
	
	
	   var pageStatus = null;
	  window.document.addEventListener("readystatechange", function(){
	        if(document.readyState == "complete"){
	            pageStatus = "complete";
	        }
	    }, false);
	  
	
	  /**
		 * 更新进度/监听完成事件。等待完完成，加载间隔快-》慢
		 */
	Ktop.method("updatepro",function(progress,bean) {
		var me = bean;
		if(pageStatus == "complete"){
            me.pro(100);
        }
		else
			{
		if(typeof(me.inter)!="undefined")
			{
			clearInterval(me.inter);
			me.inter=null;
			}
		
			progress+=5;
			me.pro(progress);
			  if(progress >= 0 && progress <= 30){
				  me.animationInterval += 1;
				
	            }
	            else if(progress > 30 && progress <= 60){
	            	me.animationInterval += 2;
	            	
	            }
	            else if(progress > 60 && progress <= 80){
	            	me. animationInterval += 3;
	            	
	            }
	            else if(progress > 80 && progress <= 90){
	            	me.animationInterval += 4;
	             
	            }
	            else if(progress > 90 && progress <= 95){
	            	me. animationInterval += 80;
	             
	            }
	            else if(progress > 95 && progress <= 99){
	            	me.animationInterval += 150;
	              
	            }
	            else if(progress >= 100){
	            	me.pro(99);
	            }
	            setTimeout(function(){me.updatepro(me.proval,me)}, me.animationInterval); 
			}    
			
			
	});
	
	
	Ktop.method("pro",function(per) {
		var me = this;
	
			
			// var per = Math.round(evt.loaded / evt.total * 100);
		
			$("body").find(".kprodiv .hrpro").css("width",
					per + "%");

			if (per == 100)
				me.hidepro();

		
	});

	var ktop=new Ktop();
	ktop.init({});
	ktop.start();
	window.$ktop=ktop;

	
	
	
})(window);


