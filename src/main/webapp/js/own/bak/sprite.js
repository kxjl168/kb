(function(window) {
	
	 $.fn.extend({
		
		 gotoabout:  function(me) {
	            if (this[0]) {
	                var b = this.click(function() {
	                	 window.location.href= basePath+"/public/about";
	                    me.hideActionPanel();
	                });
	                
	                this.hover(function() {
	                	 msgs = ["了解一下我的主人？","关于？"];
	                     var i = Math.floor(Math.random() * msgs.length);
	                     me.showMessage(msgs[i]);
	                  
	                });
	            }
	        },
	        gotosearch:  function(me) {
	            if (this[0]) {
	                var b = this.click(function() {
	                	 window.location.href= basePath+"/public/search/";
	                    me.hideActionPanel();
	                });
	                
	                this.hover(function() {
	                	 msgs = ["开始翻墙？","Google？","度娘搞不定就找点我啦","当然你要能翻墙就自己玩咯"];
	                     var i = Math.floor(Math.random() * msgs.length);
	                     me.showMessage(msgs[i]);
	                  
	                });
	            }
	        },
	        gotohome:  function(me) {
	            if (this[0]) {
	                var b = this.click(function() {
	                	 window.location.href= basePath+"/public/index/";
	                    me.hideActionPanel();
	                });
	                
	                this.hover(function() {
	                	 msgs = ["点击返回首页","首页","回主页吗？"];
	                     var i = Math.floor(Math.random() * msgs.length);
	                     me.showMessage(msgs[i]);
	                  
	                });
	            }
	        },
      	 getcat:  function(me) {
            if (this[0]) {
                var b = this.click(function() {
                    
                   window.location.href=basePath+"/public/cat/";
                    
                    me.hideActionPanel();
                });
                
                this.hover(function() {
                	   msgs = ["想来一只喵吗？", "卖喵咯~", "肚子饿呢,快来养一只吧", "喵！？~~~"];
                     var i = Math.floor(Math.random() * msgs.length);
                     me.showMessage(msgs[i]);
                  
                });
            }
        },
	        returntop: function(me) {
	            if (this[0]) {
	                var b = this.click(function() {
	                    $("html, body").animate({
	                        scrollTop: 0
	                    },
	                    300);
	                    msgs = ["坐火箭飞咯~~~", "哇，又飞走了啦~~", "老婆，快出来看月亮~", "嗖嗖嗖~~~", "还要继续飞嘛。。。", "飞上瘾了啊啊啊啊！？"];
	                    var i = Math.floor(Math.random() * msgs.length);
	                    me.showMessage(msgs[i]);
	                    me.hideActionPanel();
	                });
	                
	                this.hover(function() {
	                	 msgs = ["点击返回顶部","想体验嗖的感觉嘛>_<！", "免费过山车哦~", "试试吧", "Let's Go !"];
	                     var i = Math.floor(Math.random() * msgs.length);
	                     me.showMessage(msgs[i]);
	                  
	                });
	            }
	        }

	    });
	

    function Sprite(options) {
        this.default = {
            movetime: 40000,
            /* ms随机移动间隔40s */
            scontainer: "#spig",
            smsg: "#message",
            sid: "#mumu",
            initmsg: "欢迎访问～,偶是喵小喵-_-！",
            caturl: "/cat/",
            searchurl: "/search/",
            indexurl: '/index/',
            abouturl: '/about',

            s_text: "#s_text",
            s_uid: "#s_uid",
            s_ublog: "#s_ublog",
            actionpanelshow:false,

        };
        /* 拖动 */
        this._move = false;
        this._ismove = false; /* 移动标记 */
        this._x, this._y; /* 鼠标离控件左上角的相对位置 */
        this.options = $.extend({}, this.default, options);

        this._timer = null; /* 动画 */

    };

    Sprite.prototype.showMessage = function(a, b) {

        /* 显示消息函数 */
        if (b == null) b = 10000;
        jQuery(this.default.smsg).hide().stop();
        jQuery(this.default.smsg).html(a);
        jQuery(this.default.smsg).fadeIn();
        jQuery(this.default.smsg).fadeTo("1", 3);
        jQuery(this.default.smsg).fadeOut(b);

    };

    Sprite.prototype.init = function() {
        var me = this;
        $(document).ready(function() {

            me.createDom();
            me.showWelcome();
            me.initEvent();
            me.initClick();
            me.initScroll();
            me.initScroll();
           me.initRandomMove();
            me.helpcommet();
            me.initHit();
            me.initHover();
        });
    };

    Sprite.prototype.startAnimate = function() {



        var me = this;


        me.stopAnimate();

        $(me.default.sid).toggleClass("active");

        var index = 1;
        var isadd = true;

        me._timer = setInterval(function() {

            /* 循环切换css,css中切换png图片 1234-》4321 */
            $(me.default.sid).toggleClass("o" + index);
            if (isadd)
                index++;
            else
                index--;
            if (index == 5) {
                isadd = false;
                index--;
            }
            if (index == 0) {
                isadd = true;
                index++;
            }



        }, 100);
    };
    Sprite.prototype.stopAnimate = function() {
        var me = this;
        $(me.default.sid).removeClass("active").removeClass("o1").removeClass("o2").removeClass("o3").removeClass("o4");
        clearInterval(me._timer);
    };
    
    Sprite.prototype.showTop=function(){
    	var me=this;
    	$("body").find('#gotop').css("visibility",'visible');
    	 $("body").find('#gotop').stop().animate({
				color: "#A0410D",
			 right:'-50px',
			 opacity:1,
			 
			 width:'37px',
			}, 400,function(){
				me.default.actionpanelshow=true;
		});
    	 
    	
    	 
    	 event.stopPropagation();
    	 return false;
    };
    
    Sprite.prototype.hideTop=function(){
    	var me=this;
    	$("body").find('#gotop').stop().animate({
			// color: "#A0410D",
		 right:'0px',
		 opacity:0,
		 
		 width:'0px',
		 
		}, 400,function(){
			$("body").find('#gotop').css("visibility",'collapse');
			me.default.actionpanelshow=false;
		});
    };
    
    
    Sprite.prototype.showGetCat=function(){
    	var me=this;
    	$("body").find('#getonecat').css("visibility",'visible');
    	 $("body").find('#getonecat').stop().animate({
				color: "#A0410D",
			 right:'-60px',
			/* top:'-10px', */
			 opacity:1,
			 
			 width:'37px',
			}, 400,function(){
				me.default.actionpanelshow=true;
		});
    	 
    	
    	 
    	 event.stopPropagation();
    	 return false;
    };
    
    Sprite.prototype.hideGetCat=function(){
    	var me=this;
    	$("body").find('#getonecat').stop().animate({
			// color: "#A0410D",
		 right:'0px',
	/* top:'0px', */
		 opacity:0,
		 
		 width:'0px',
		 
		}, 400,function(){
			$("body").find('#getonecat').css("visibility",'collapse');
			me.default.actionpanelshow=false;
		});
    };
    
    
    Sprite.prototype.showSearch=function(){
    	var me=this;
    	$("body").find('#gotosearch').css("visibility",'visible');
    	 $("body").find('#gotosearch').stop().animate({
				color: "#A0410D",
			 right:'60px',
			/* top:'-10px', */
			 opacity:1,
			 
			 width:'37px',
			}, 400,function(){
				me.default.actionpanelshow=true;
		});
    	 
    	
    	 
    	 event.stopPropagation();
    	 return false;
    };
    
    Sprite.prototype.hideSearch=function(){
    	var me=this;
    	$("body").find('#gotosearch').stop().animate({
			// color: "#A0410D",
		 right:'0px',
	/* top:'0px', */
		 opacity:0,
		 
		 width:'0px',
		 
		}, 400,function(){
			$("body").find('#gotosearch').css("visibility",'collapse');
			me.default.actionpanelshow=false;
		});
    };
    
    
    
    Sprite.prototype.showHome=function(){
    	var me=this;
    	$("body").find('#gotohome').css("visibility",'visible');
    	 $("body").find('#gotohome').stop().animate({
				color: "#A0410D",
			 right:'60px',
			/* top:'-10px', */
			 opacity:1,
			 
			 width:'37px',
			}, 400,function(){
				me.default.actionpanelshow=true;
		});
    	 
    	
    	 
    	 event.stopPropagation();
    	 return false;
    };
    
    Sprite.prototype.hideHome=function(){
    	var me=this;
    	$("body").find('#gotohome').stop().animate({
			// color: "#A0410D",
		 right:'0px',
	/* top:'0px', */
		 opacity:0,
		 
		 width:'0px',
		 
		}, 400,function(){
			$("body").find('#gotohome').css("visibility",'collapse');
			me.default.actionpanelshow=false;
		});
    };
    
    
    
    
    Sprite.prototype.showAbout=function(){
    	var me=this;
    	$("body").find('#getabout').css("visibility",'visible');
    	 $("body").find('#getabout').stop().animate({
				color: "#A0410D",
			 right:'-50px',
			/* top:'-10px', */
			 opacity:1,
			 
			 width:'37px',
			}, 400,function(){
				me.default.actionpanelshow=true;
		});
    	 
    	
    	 
    	 event.stopPropagation();
    	 return false;
    };
    
    Sprite.prototype.hideAbout=function(){
    	var me=this;
    	$("body").find('#getabout').stop().animate({
			// color: "#A0410D",
		 right:'0px',
	/* top:'0px', */
		 opacity:0,
		 
		 width:'0px',
		 
		}, 400,function(){
			$("body").find('#getabout').css("visibility",'collapse');
			me.default.actionpanelshow=false;
		});
    };
    
    Sprite.prototype.showActionPanel=function(){
    	var me=this;
    	me.showGetCat();
    	me.showTop();
    	me.showAbout();
    	me.showSearch();
    	me.showHome();
    };
    
    Sprite.prototype.hideActionPanel=function(){
    	var me=this;
    	me.hideGetCat();
    	me.hideTop();
    	me.hideAbout();
    	me.hideSearch();
    	me.hideHome();
    };
    
    

    Sprite.prototype.initHover = function() {
        var me = this;
        var timer = null;
        $(me.default.sid).mouseover(function() {

            msgs = ["经常来来看我，我就跟你玩~", "本喵可远观不可亵玩！", "我会隐身哦！嘿嘿！", "别动手动脚的，把手拿开！！", "再不把手拿开小心我横竖竖你！！", "主人，他摸我，呜呜呜呜~~~", "你把手拿开我就出来！"];
            var i = Math.floor(Math.random() * msgs.length);
            me.showMessage(msgs[i]);
            if (i == 2 || i == 6)
                $(me.default.sid).fadeTo("300", 0.6);

            me.startAnimate();


            
            me.showActionPanel();
			
            

        });
        $(me.default.sid).mouseout(function() {
            $(me.default.sid).fadeTo("300", 1);
            me.stopAnimate();
        });
        

		 $(this.default.scontainer).hover(function() {
			 
				},function() {
					me.hideActionPanel();
			});
		 
    };

    Sprite.prototype.initHit = function() {
        var me = this;

        setTimeout(function() {

            $('a.ptitle').click(function() { /* 标题被点击时 */
                me.showMessage('正在用吃奶的劲加载《<span >' + $(this).text() + '</span>》请稍候');
            });

            $('a.ptitle').mouseover(function() {

                me.showMessage('要看看《<span style="">' + $(this).text() + '</span>》这篇文章么？');
            });
            
            $('body').on("mouseover",".catsay",function(e) {

                me.showMessage('' +$(this).text());
            });
            
            
            /*
			 * $('#prev-page').mouseover(function() { showMessage('要翻到上一页吗?');
			 * }); $('#next-page').mouseover(function() {
			 * showMessage('要翻到下一页吗?'); });
			 */
            $('.rblock a').mouseover(function() {
                me.showMessage('看看 <span style="">' + $(this).text() + '</span> 的文章？ ');
            });
            $('#rpdiv').mouseover(function() {
                me.showMessage('<span style="">' + '</span> 向评论栏出发吧！');
            });
            $('#sbtn').mouseover(function() {
                me.showMessage('准备提交了么？');
            });
        }, 1000);
        /*
		 * $('#s').focus(function() {
		 * showMessage('输入你想搜索的关键词再按Enter(回车)键就可以搜索啦!'); });
		 * $('#go-prev').mouseover(function() { showMessage('点它可以后退哦！'); });
		 * $('#go-next').mouseover(function() { showMessage('点它可以前进哦！'); });
		 * $('#refresh').mouseover(function() { showMessage('点它可以重新载入此页哦！'); });
		 * $('#go-home').mouseover(function() { showMessage('点它就可以回到首页啦！'); });
		 * $('#addfav').mouseover(function() { showMessage('点它可以把此页加入书签哦！'); });
		 * $('#nav-two a').mouseover(function() {
		 * showMessage('嘘，从这里可以进入控制面板的哦！'); }); $('.post-category
		 * a').mouseover(function() { showMessage('点击查看此分类下得所有文章'); });
		 * $('.post-heat a').mouseover(function() {
		 * showMessage('点它可以直接跳到评论列表处.'); }); $('#tho-shareto span
		 * a').mouseover(function() { showMessage('你知道吗?点它可以分享本文到' +
		 * $(this).attr('title')); }); $('#switch-to-wap').mouseover(function() {
		 * showMessage('点击可以切换到手机版博客版面'); });
		 */
    };

    Sprite.prototype.helpcommet = function() {
        var me = this;
        $(me.default.s_uid).click(function() {
            me.showMessage("留下你的尊姓大名！");
            $(me.default.scontainer).animate({
                top: $(me.default.s_uid).offset().top - 70,
                left: $(me.default.s_uid).offset().left + 270
            }, {
                queue: false,
                duration: 1000
            });
        });
        $("#email").click(function() {
            me.showMessage("留下你的邮箱，不然你的头像是小怪物喽！");
            $(me.default.scontainer).animate({
                top: $("#email").offset().top - 70,
                left: $("#email").offset().left + 270
            }, {
                queue: false,
                duration: 1000
            });
        });
        $(me.default.s_ublog).click(function() {

            me.showMessage("快快告诉我你的家在哪里，好让我去参观参观！");
            $(me.default.scontainer).animate({
                top: $(me.default.s_ublog).offset().top - 70,
                left: $(me.default.s_ublog).offset().left + 270
            }, {
                queue: false,
                duration: 1000
            });
        });
        $(me.default.s_text).click(function() {
            me.showMessage("认真填写哦！不然会被认作垃圾评论的！我的乖乖~");
            $(me.default.scontainer).animate({
                top: $(me.default.s_text).offset().top - 70,
                left: $(me.default.s_text).offset().left + 270
            }, {
                queue: false,
                duration: 1000
            });
        });
    };

    Sprite.prototype.showWelcome = function() {

        var reg = new RegExp(".*" + this.default.indexurl + ".*", "i");
        var me = this;
        var r = window.location.href.match(reg);

        var visitor = "喵``，";

        if (r != null) { /* 如果是主页 */
            var now = (new Date()).getHours();
            if (now > 0 && now <= 6) {
                me.showMessage(visitor + ' 你是夜猫子呀？还不睡觉，明天起的来么你？', 6000);
            } else if (now > 6 && now <= 11) {
                me.showMessage(visitor + ' 早上好，早起的鸟儿有虫吃噢！早起的虫儿被鸟吃，你是鸟儿还是虫儿？嘻嘻！', 6000);
            } else if (now > 11 && now <= 14) {
                me.showMessage(visitor + ' 中午了，吃饭了么？不要饿着了，饿死了谁来挺我呀！', 6000);
            } else if (now > 14 && now <= 18) {
                me.showMessage(visitor + ' 中午的时光真难熬！还好有你在！', 6000);
            } else {
                me.showMessage(visitor + ' 快来逗我玩吧！我好无聊啊~~', 6000);
            }
        } else if (window.location.href.match(".*" + this.default.caturl + ".*", "i")) {
            me.showMessage('欢迎' + ',这就是喵喵的本尊了啦～～ ', 6000);
        } 
        else if (window.location.href.match(".*" + this.default.searchurl + ".*", "i")) {
            me.showMessage('' + '可以翻墙的Google哦，试试吧～～ ', 6000);
        } 
        else if (window.location.href.match(".*" + this.default.abouturl + ".*", "i")) {
            me.showMessage('' + '这就是偶的铲屎官咯，中年大叔一只,喵～～ ', 6000);
        } 
        else {
            me.showMessage('欢迎' + '访问<span style="">KxのBOOK</span> ', 6000);
        }

        $(me.default.scontainer).animate({

            top: (window.pageYOffset ||
                    document.documentElement.scrollTop || document.body.scrollTop || 0) + 250
                /* top:$(window).scrollTop() + f +300 */
        }, {
            queue: false,
            duration: 1000
        });
    };

    Sprite.prototype.initRandomMove = function() {
        var me = this;
        window.setInterval(function() {
            msgs = ["喵～～！",
                "喵乌～～～...喵..！",
                "乾坤大挪移！", "我飘过来了！~",
                "我飘过去了", "我得意地飘！~飘！~"
            ];
            var i = Math.floor(Math.random() * msgs.length);

            s = [0.1, 0.2, 0.3, 0.4, 0.5, -0.1, -0.2, -0.3, -0.4, -0.5];
            var i1 = Math.floor(Math.random() * s.length);
            var i2 = Math.floor(Math.random() * s.length);
            $(me.default.scontainer).animate({
                left: document.body.offsetWidth / 2 * (1 + s[i1]),
                top: document.body.offsetheight / 2 * (1 + s[i1])
            }, {
                duration: 2000,
                complete: me.showMessage(msgs[i])
            });

            me.startAnimate();

            setTimeout(function() {
                me.stopAnimate();
            }, 3000);

        }, me.default.movetime);
    };

    Sprite.prototype.createDom = function() {

        var dom = " <div id=\"spig\" class=\"spig\" style=\"top: 225px; left: 50.625px;\"> " +
        
        
            "<div class='mdiv'>" +
            
            "<div id='gotop' title='返回顶部'><span class='fa-stack fa-lg'> " +
            ' <i class="fa fa-circle fa-stack-2x topcircle2"></i> '+
            '   <i class="fa fa-rocket fa-stack-1x fa-inverse"></i> '+
            '  </span> '+
            '</div>  ' +
            
            
            "<div id='getonecat' title='领养一只吗？'><span class='fa-stack fa-lg'> " +
            ' <i class="fa fa-circle fa-stack-2x topcircle"></i> '+
            '   <i class="fa fa-paw fa-stack-1x fa-inverse"></i> '+
            '  </span> '+
            '</div>  ' +
            
            "<div id='gotosearch' title='Goooooogle？'><span class='fa-stack fa-lg'> " +
            ' <i class="fa fa-circle fa-stack-2x topcircle4"></i> '+
            '   <i class="fa fa-google fa-stack-1x fa-inverse"></i> '+
            '  </span> '+
            '</div>  ' +
            
            "<div id='gotohome' title='主页？'><span class='fa-stack fa-lg'> " +
            ' <i class="fa fa-circle fa-stack-2x topcircle5"></i> '+
            '   <i class="fa fa-home fa-stack-1x fa-inverse"></i> '+
            '  </span> '+
            '</div>  ' +
            
            
            "<div id='getabout' title='关于博主？'><span class='fa-stack fa-lg'> " +
            ' <i class="fa fa-circle fa-stack-2x topcircle3"></i> '+
            '   <i class="fa fa-info fa-stack-1x fa-inverse"></i> '+
            '  </span> '+
            '</div>  ' +
            
            " <div id=\"message\" style=\"display: block; opacity: 0.498272;\">" + this.default.initmsg + "</div> " +
            
            " <div id=\"mumu\" class=\"mumu\" style=\"opacity: 1;\"></div>" +
            "</div>"+
            " </div> ";

        var spig=$("#spig");
        if(spig.length<=0)
        	{
     
        	$("body").append(dom);
        	}
        this.default.scontainer = $("body").find(this.default.scontainer);
        this.default.smsg = $("body").find(this.default.smsg);
        this.default.mumu = $("body").find(this.default.mumu);
        
        // 回顶部
        $("body").find('#gotop').returntop(this);
        
        // about
        $("body").find('#getabout').gotoabout(this);
        
        // tocat
        $("body").find('#getonecat').getcat(this);
        $("body").find('#gotosearch').gotosearch(this);
        $("body").find('#gotohome').gotohome(this);
    };
    
    




    Sprite.prototype.initScroll = function() {


        var me = this;

        var f = 0; /* $(me.default.scontainer).offset().top; */

        $(window).scroll(function() {
            $(me.default.scontainer).animate({


                top: (window.pageYOffset ||
                        document.documentElement.scrollTop || document.body.scrollTop || 0) + f + 250
                    /* top:$(window).scrollTop() + f +300 */
            }, {
                queue: false,
                duration: 1000
            });

        });
    };

    Sprite.prototype.initClick = function() {
        var stat_click = 0;
        var me = this;
        $("body").find(me.default.sid).click(function() {
        	
        	if(!me.default.actionpanelshow)
            me.showActionPanel();
        	else
        		me.hideActionPanel();
        	
            if (!me._ismove) {
                stat_click++;
                if (stat_click > 4) {
                    msgs = ["你有完没完呀？", "你已经摸我" + stat_click + "次了，人家脸都红色...", "非礼呀！救命！OH，My ladygaga"];
                    var i = Math.floor(Math.random() * msgs.length);

                } else {
                    msgs = ["筋斗云！~我飞！", "我跑呀跑呀跑！~~", "别摸我了，再摸我就脸红了！", "惹不起你，我还躲不起你么？", "不要摸我了，我会告诉主人哥哥来打你的哦！", "干嘛动我呀！小心我咬你！"];
                    var i = Math.floor(Math.random() * msgs.length);

                }

                s = [0.1, 0.2, 0.3, 0.4, 0.5, -0.1, -0.2, -0.3, -0.4, -0.5];
                var i1 = Math.floor(Math.random() * s.length);
                var i2 = Math.floor(Math.random() * s.length);
                $(me.default.scontainer).animate({
                    left: document.body.offsetWidth / 2 * (1 + s[i1]),
                    top: document.body.offsetheight / 2 * (1 + s[i1])
                }, {
                    duration: 500,
                    complete: me.showMessage(msgs[i])
                });
            } else {
                me._ismove = false;
            }
        });
        
       /*
		 * $('body').click(function(){ me.hideTop(); });
		 */
    };


    Sprite.prototype.initEvent = function() {

        var me = this;

        function startMove(event) {

            var sx = event.clientX || event.originalEvent.targetTouches[0].pageX;
            var sy = event.clientY || event.originalEvent.targetTouches[0].pageY;

            me._move = true;
            me._x = sx - parseInt($(me.default.scontainer).css("left"));
            me._y = sy - parseInt($(me.default.scontainer).css("top"));
        };

        function Move(event) {

            var sx = event.clientX || event.originalEvent.targetTouches[0].pageX;
            var sy = event.clientY || event.originalEvent.targetTouches[0].pageY;


            event.preventDefault();
            if (me._move) {



                var x = sx - me._x;
                var y = sy - me._y;
                var wx = $(window).width() - $(me.default.scontainer).width();
                var dy = $(document).height() - $(me.default.scontainer).height();
                if (x >= 0 && x <= wx && y > 0 && y <= dy) {
                    $(me.default.scontainer).css({
                        top: y,
                        left: x
                    }); // 控件新位置
                    me._ismove = true;
                }
            }
        };


        function endMove(e) {
            me._move = false;

            /*
			 * $(document).off('mousemove', null, Move);
			 * $(document).off('mouseup', null, endMove);
			 * 
			 * $(me.default.scontainer).off('touchmove', null, Move);
			 * $(me.default.scontainer).off('touchend', null, endMove);
			 */
        };
        $(me.default.scontainer).on('mousedown', null, startMove);
        $(me.default.scontainer).on('touchstart', null, startMove);

        $(me.default.scontainer).on('mousemove', null, Move);
        $(me.default.scontainer).on('touchmove', null, Move);


        $(me.default.scontainer).on('mouseup', null, endMove);
        $(me.default.scontainer).on('touchend', null, endMove);
    };



    window.$sprite = new Sprite();
    window.$sprite.init();

})(window);