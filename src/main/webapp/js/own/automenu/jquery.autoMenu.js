/* 
 * blogMenu plugin 1.0   2017-09-01 by cary
 * modidfy kxjl168 20180704 MIT lincese.
 * http://www.256kb.cn
 * 说明：自动根据标签（h1,h2）生成博客目录
 */
(function ($) {

    var Menu = (function () {
        /**
         * 插件实例化部分，初始化时调用的代码可以放这里
         * @param element 传入jq对象的选择器，如 $("#J_plugin").plugin() ,其中 $("#J_plugin") 即是 element
         * @param options 插件的一些参数神马的
         * @constructor
         */
        var Plugin = function(element, options) {
            //将dom jquery对象赋值给插件，方便后续调用
            this.$element = $(element);
            //this.$eleid=element;
            //将插件的默认参数及用户定义的参数合并到一个新的obj里
            this.settings = $.extend({}, $.fn.autoMenu.defaults, typeof options === 'object' && options)
            //如果将参数设置在dom的自定义属性里，也可以这样写
            //this.settings = $.extend({}, $.fn.plugin.defaults, this.$element.data(), options);

            this.init();
        }


        /**
         * 将插件所有函数放在prototype的大对象里
         * 插件的公共方法，相当于接口函数，用于给外部调用
         * @type {{}}
         */
        Plugin.prototype = {
            init: function () {
                var opts = this.settings;

                //console.log(opts)
                this.$element.parent().find(".autoMenu").remove();
                this.$element.before(this.createHtml());
                this.setActive();
                this.bindEvent();
                
            },
            createHtml: function(){
                var that = this;
                var opts = that.settings;
                var width = typeof opts.width === 'number' && opts.width;
                var height = typeof opts.height === 'number' && opts.height;
                var padding = typeof opts.padding === 'number' && opts.padding;
                //that.$element.width(width+padding*2);
                var html = '<div class="autoMenu "><span class="title">文章目录:</span><a href="javascript:void(0);" title="隐藏目录" class="btn-box">'
                    +'<span class="icon-minus-sign"></span>'
                    +'</a>'
                    +' <a href="javascript:void(0);" title="目录固定" class="btn-box">'
                    +'<span class="fa fa-2x fa-compress"></span>'
                    +'</a>'
                    +'<ul >';
                var num = 0;
                $(that.$element).find("*") .each(function(){
                    var _this = $(this);
                    if(opts.levelOne.toUpperCase().indexOf( _this.get(0).tagName)>-1 ){
                        _this.attr('id',num);
                        var nodetext = that.handleTxt(_this.html());
                        html += '<li name="'+ num +'"><a href="#'+ num +'">'+ nodetext +'</a></li>';
                        num++;
                    }else if(_this.get(0).tagName == opts.levelTwo.toUpperCase()){
                        _this.attr('id',num);
                        var nodetext = that.handleTxt(_this.html());
                        html += '<li class="sub" name="'+ num +'"><a href="#'+ num +'">'+ nodetext +'</a></li>';
                        num++;
                    }
                })
                html += '</ul></div>';
                if(num==0)
                	html="";
                return html;   
            },
            handleTxt: function(txt){
                //正则表达式去除HTML的标签
                return txt.replace(/<\/?[^>]+>/g,"").trim();
            },
            setActive: function(){
                var $el = this.$element,
                    opts = this.settings,
                    items = opts.levelOne + ',' + opts.levelTwo,
                    $items = $el.find(items),
                    offTop = opts.offTop,
                    top = $(document).scrollTop(),
                    currentId;
                if($(document).scrollTop()==0){
                    //初始化active
                    $el.parents().find('.autoMenu li').removeClass('active').eq(0).addClass('active');
                    return;
                }
                $items.each(function(){
                    var m = $(this),
                        itemTop = m.offset().top;
                    if(top > itemTop-offTop){
                        currentId = m.attr('id');
                    }else{
                        return false;
                    }
                });
                
                $el.parents().find('.autoMenu li').removeClass('active');
                $el.parents().find('.autoMenu li[name='+currentId+']').addClass('active');
                
                
                
            },
            bindEvent: function(){
                var _this = this;
                $(window).scroll(function(){
                    _this.setActive();
                });
                _this.$element. parents().find(".autoMenu .btn-box").on('click',function(){
                    if($(this).find('span').hasClass('icon-minus-sign')){
                        $(this).find('span').removeClass('icon-minus-sign').addClass('icon-plus-sign');
                        _this.$element.parents().find('.autoMenu  ul').fadeOut();
                        $(this).attr("title",'显示目录');
                    }else  if($(this).find('span').hasClass('icon-plus-sign')){
                        $(this).find('span').removeClass('icon-plus-sign').addClass('icon-minus-sign');
                        _this.$element.parents().find('.autoMenu  ul').fadeIn();
                        $(this).attr("title",'隐藏目录');
                    }
                    else  if($(this).find('span').hasClass('fa-compress')){
                        $(this).find('span').removeClass('fa-compress').addClass('fa-expand');
                        _this.$element.parents().find('.autoMenu ').toggleClass("fix");
                        $(this).attr("title",'浮动目录');
                    }
                    else  if($(this).find('span').hasClass('fa-expand')){
                        $(this).find('span').removeClass('fa-expand').addClass('fa-compress');
                        _this.$element.parents().find('.autoMenu ').toggleClass("fix");
                        $(this).attr("title",'固定目录');
                    }
                    
                })
            }

        };

        return Plugin;

    })();


    /**
     * 这里是将Plugin对象 转为jq插件的形式进行调用
     * 定义一个插件 plugin
     */
    $.fn.autoMenu = function (options) {
        return this.each(function () {
            var $el = $(this),
                menu = $el.data('autoMenu'),
                option = $.extend({}, $.fn.autoMenu.defaults, typeof options === 'object' && options);
            if (!menu) {
                //将实例化后的插件缓存在dom结构里（内存里）
                $el.data('autoMenu',new Menu(this, option));
            }

            /**
             * 如果插件的参数是一个字符串，则 调用 插件的 字符串方法。
             * 如 $('#id').plugin('doSomething') 则实际调用的是 $('#id).plugin.doSomething();
             */
            if ($.type(options) === 'string') menu[option]();
        });
    };

    /**
     * 插件的默认值
     */
    $.fn.autoMenu.defaults = {
        levelOne : 'h1,ul', //一级标题
        levelTwo : 'h2',  //二级标题（暂不支持更多级）
        width : 200, //容器宽度
        height : 400, //容器高度
        padding: 20, //内部间距
        offTop : 100, //滚动切换导航时离顶部的距离

    };

    /**
     * 优雅处： 通过data-xxx 的方式 实例化插件。
     * 这样的话 在页面上就不需要显示调用了。
     * 可以查看bootstrap 里面的JS插件写法
     */
    $(function () {
        if($('[data-autoMenu]').length>0){
            new Menu($('[data-autoMenu]'));
        }
        
    });

})(jQuery);


	function otherScroll2(){
		  var scrollTop = document.documentElement.scrollTop + document.body.scrollTop;
		  var page=$(".pageText").offset().top;
		  var height=$(".autoMenu").height();
		  if(browser&&!browser.versions.mobile)
			  {
		  if(scrollTop>height){
			  //mobile, declare in other common js
			
			  $(".autoMenu").addClass('fix');
		  }
		  else{
			  $(".autoMenu").removeClass('fix');
		  }
			  }
	}

