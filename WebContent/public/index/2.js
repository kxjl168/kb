var app=angular.module("myApp",["ngSanitize"]);app.controller("eduCtrl",function(a){});$(function(){init();$("pre code").each(function(c,b){hljs.highlightBlock(b)});WdatePicker({eCont:"nDate"});var a=angular.element(ngSection).scope();kvalidate.init($("#fm"),{s_title:{required:true,},s_context:"required",},{s_title:{required:"标题必须填写",},s_context:"请输入内容",},a.doupdate,"")});function changerows(c){var b=$(c).val();var a=angular.element(ngSection).scope();a.$apply(function(){a.rows=b;a.getList()})}function init(){initmenu_p($("#menuul"),"public/index/");$(".modal").on("show.bs.modal",function(){$(this).draggable();$(this).css("overflow-y","scroll")});initQuery()}app.filter("sanitize",["$sce",function(a){return function(b){return b?a.trustAsHtml(b):""}}]);