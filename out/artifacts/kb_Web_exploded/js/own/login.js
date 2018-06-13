$(function() {
    
    
    
        $("#password").bind('keypress', function(event) {
            if (event.keyCode == "13") {
            /*	checkLogin();  */
            }
        });
        
    
        $("#code").bind('keypress', function(event) {
            if (event.keyCode == "13") {
                checkLogin();
            }
        });
    
        // 全局的ajax访问，处理ajax清求时sesion超时
        /*$.ajaxSetup({
            contentType : "application/x-www-form-urlencoded;charset=utf-8",
            complete : function(XMLHttpRequest, textStatus) {
                var sessionstatus = XMLHttpRequest
                        .getResponseHeader("sessionstatus"); 
                if (sessionstatus == "timeout") {
                //如果超时就处理 ，指定要跳转的页面
                    window.location.href("loginin.do");
                }
            }
        });*/
        
        /*code.js 验证码*/
    /*	createCode();  */
        
        $("#loginbtn").focus();
    });
    
    function checkLogin(cb) {
        
    
    /* 	if(!validate())
            return; */
        
        var userName = $("#username").val();
        var passWord = $("#password").val();
        if (userName.length == 0) {
            error( "请输入用户名！");
            $("#username").focus();
            if(typeof cb !='undefined')
            cb();
            return;
        } else if (passWord.length == 0) {
            error( "请输入密码！");
            if(typeof cb !='undefined')
            cb();
            $("#password").focus();
            return;
        } else {
            $.ajax({
                url : basePath + "/exlogin.action",
                type : "post",
                data : {
                    "username" : userName,
                    "password" : passWord
                },
                dataType : "json",
                success : function(data) {
                    if (data.sucess) {
                        
                        if(typeof(data.url)=="undefined")
                        window.location.href = "/";
                        else
                            window.location.href = data.url;
                    
                    } else {
                        error( data.msg);
                        
                        if(typeof cb !='undefined')
                            cb();
                    }
                }
            });
        }
    };