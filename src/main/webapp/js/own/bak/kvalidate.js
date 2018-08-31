(function () {

	var initValidate = function () {

		var vrules =
			{


				CHS: {
					validator: function (value, param) {
						return /^[\u0391-\uFFE5]+$/.test(value);
					},
					message: '请输入汉字'
				},
				english: {// 验证英语
					validator: function (value) {
						return /^[A-Za-z]+$/i.test(value);
					},
					message: '请输入英文'
				},
				ip: {// 验证IP地址
					validator: function (value) {
						return /\d+\.\d+\.\d+\.\d+/.test(value);
					},
					message: 'IP地址格式不正确'
				},
				ZIP: {
					validator: function (value, param) {
						return /^[0-9]\d{5}$/.test(value);
					},
					message: '邮政编码不存在'
				},
				QQ: {
					validator: function (value, param) {
						return /^[1-9]\d{4,10}$/.test(value);
					},
					message: 'QQ号码不正确'
				},
				mobile: {
					validator: function (value, param) {
						return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/
							.test(value);
					},
					message: '手机号码不正确'
				},
				tel: {
					validator: function (value, param) {
						return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/
							.test(value);
					},
					message: '电话号码不正确'
				},
				mobileAndTel: {
					validator: function (value, param) {
						return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/
							.test(value);
					},
					message: '请正确输入电话号码'
				},
				number: {
					validator: function (value, param) {
						return /^[0-9]+.?[0-9]*$/.test(value);
					},
					message: '请输入数字'
				},
				money: {
					validator: function (value, param) {
						return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)
							.test(value);
					},
					message: '请输入正确的金额'

				},
				mone: {
					validator: function (value, param) {
						return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)
							.test(value);
					},
					message: '请输入整数或小数'

				},
				integer: {
					validator: function (value, param) {
						return /^[+]?[1-9]\d*$/.test(value);
					},
					message: '请输入最小为1的整数'
				},
				integ: {
					validator: function (value, param) {
						return /^[+]?[0-9]\d*$/.test(value);
					},
					message: '请输入整数'
				},
				range: {
					validator: function (value, param) {
						if (/^[1-9]\d*$/.test(value)) {
							return value >= param[0] && value <= param[1]
						} else {
							return false;
						}
					},
					message: '输入的数字在{0}到{1}之间'
				},
				minLength: {
					validator: function (value, param) {
						return value.length >= param[0]
					},
					message: '至少输入{0}个字'
				},
			
				maxLength: {
					validator: function (value, param) {
						return value.length <= param[0]
					},
					message: '最多{0}个字'
				},
				// select即选择框的验证
				selectValid: {
					validator: function (value, param) {
						if (value == param[0]) {
							return false;
						} else {
							return true;
						}
					},
					message: '请选择'
				},
				idCode: {
					validator: function (value, param) {
						return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
							.test(value);
					},
					message: '请输入正确的身份证号'
				},
				loginName: {
					validator: function (value, param) {
						return /^[\u0391-\uFFE5\w]+$/.test(value);
					},
					message: '登录名称只允许汉字、英文字母、数字及下划线。'
				},
				equalTo: {
					validator: function (value, param) {
						return value == $(param[0]).val();
					},
					message: '两次输入的字符不一至'
				},
				englishOrNum: {// 只能输入英文和数字
					validator: function (value) {
						return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
					},
					message: '请输入英文、数字、下划线或者空格'
				},
				xiaoshu: {
					validator: function (value) {
						return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/
							.test(value);
					},
					message: '最多保留两位小数！'
				},
				ddPrice: {
					validator: function (value, param) {
						if (/^[1-9]\d*$/.test(value)) {
							return value >= param[0] && value <= param[1];
						} else {
							return false;
						}
					},
					message: '请输入1到100之间正整数'
				},
				portPrice: {
					validator: function (value, param) {
						if (/^[0-9]+.?[0-9]*$/.test(value)) {
							return value >= param[0] && value <= param[1];
						} else {
							return false;
						}
					},
					message: '请输入1000到65280整数'
				},
				portPhysicla: {
					validator: function (value, param) {
						if (/^[0-9]+.?[0-9]*$/.test(value)) {
							return value >= param[0] && value <= param[1];
						} else {
							return false;
						}
					},
					message: '请输入9500到9999整数'
				},
				jretailUpperLimit: {
					validator: function (value, param) {
						if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)) {
							return parseFloat(value) > parseFloat(param[0])
								&& parseFloat(value) <= parseFloat(param[1]);
						} else {
							return false;
						}
					},
					message: '请输入0到100之间的最多俩位小数的数字'
				},
				rateCheck: {
					validator: function (value, param) {
						if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)) {
							return parseFloat(value) > parseFloat(param[0])
								&& parseFloat(value) <= parseFloat(param[1]);
						} else {
							return false;
						}
					},
					message: '请输入0到1000之间的最多俩位小数的数字'
				},
				ipdesc: {
					validator: function (value, element) {
						var reg = /^\d+\.\d+\.\d+\.\d+-\d+\.\d+\.\d+\.\d+$/;

						return this.optional(element) || reg.test(value);
						//return reg.test(value);

					},
					message: "IP段格式错误,格式示例:192.168.1.1-192.168.1.20"
				},
				version4: {
					validator: function (value, element) {
						var reg = /^\d+\.\d+\.\d+\.\d+$/;

						return this.optional(element) || reg.test(value);
						//return reg.test(value);

					},
					message: "版本号格式 *.*.*.* "
				},
				// 判断版本号格式是否为1.1.1格式
				version3: {
					validator: function (versionNum) {
						// var reg =
						// /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
						var reg = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
						if (reg.exec(versionNum) != null) {
							if (RegExp.$1 < 0 || RegExp.$1 > 255)
								return false;
							if (RegExp.$2 < 0 || RegExp.$2 > 255)
								return false;
							if (RegExp.$3 < 0 || RegExp.$3 > 255)
								return false;
							// if(RegExp.$4<0 || RegExp.$4>255) return
							// false;
						} else {
							return false;
						}
						return true;
					},
					message: '版本号应为“x.x.x”格式！'
				},
				// 判断强密码格式，数字、大小写，符号，6-18位
				// ja.put(".*\\d+.*");
				// ja.put(".*[a-z]+.*");
				// ja.put(".*[A-Z]+.*");
				// ja.put(".*[~!@#$%^&*_+()?]+.*");
				// ja.put(".{6,18}");
				strongpass: {
					validator: function (pass) {
						// var reg =
						// /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
						var reg1 = /^.*\d+.*$/;
						var reg2 = /^.*[a-z]+.*$/;
						var reg3 = /^.*[A-Z]+.*$/;
						var reg4 = /^.*[~!@#$%^&*_+()?]+.*$/;
						var reg5 = /^.{6,18}$/;
						if (!reg1.test(pass)) {
							return false;
						}
						if (!reg2.test(pass)) {
							return false;
						}
						if (!reg3.test(pass)) {
							return false;
						}
						if (!reg4.test(pass)) {
							return false;
						}
						if (!reg5.test(pass)) {
							return false;
						}


						return true;
					},
					message: '密码需要包含数字、大小写字母、 以下特殊符号~!@#$%^&*()_?,并且 长度在6-18位'
				},
				email: {
					validator: function (value) {
					
						if(value==null||value.trim()=="")
							return true;
						var reg1 = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

						if (!reg1.test(value)) {
							return false;
						}

						return true;
					},
					message: '邮箱格式错误'


				},
				selectValueRequired: {
					validator: function (value, param) {
						return false;
						// if(param.length<0)
						// return fase;
						// else
						// {
						// console.info($(param[0]).find("option:contains('"+value+"')").val());
						// return
						// $(param[0]).find("option:contains('"+value+"')").val()
						// != '';
						// }
					},
					message: '请选择配置参数.'
				}
			};

		for (var rule in vrules) {

			jQuery.validator.addMethod(
				rule,
				vrules[rule].validator,
				vrules[rule].message
			);

		}

		/* jQuery.validator.addMethod("isPhone", function (value, element) {
			var length = value.length;
			return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
		}, "请正确填写您的手机号码。"); */
	};


	window.kvalidate={};
	window.kvalidate.validators = {};

	var initFormValidate = function (selector, rules, messages, submithanlder,debug,offsetclass) {
		var jqueryValidator = $(selector)
			.validate(
			{
				debug: debug,
				errorElement: 'span',
				errorClass: 'help-block',

				rules: rules
				/*
				 * { firstname : "required", email : { required :
				 * true, email : true }, password : { required :
				 * true, isPwd : true }, confirm_password : {
				 * required : true, isPwd : true, equalTo :
				 * "#password" }, phone : { required : true, isPhone :
				 * true }, tel : { isTel : true }, address : {
				 * minlength : 10 } }
				 */
				,
				messages: messages
				/*
				 * { firstname : "请输入姓名", email : { required :
				 * "请输入Email地址", email : "请输入正确的email地址" }, password : {
				 * required : "请输入密码", minlength :
				 * jQuery.format("密码不能小于{0}个字 符") },
				 * confirm_password : { required : "请输入确认密码",
				 * minlength : "确认密码不能小于5个字符", equalTo :
				 * "两次输入密码不一致不一致" }, phone : { required : "请输入手机号码" },
				 * tel : { required : "请输入座机号码" }, address : {
				 * required : "请输入家庭地址", minlength :
				 * jQuery.format("家庭地址不能少于{0}个字符") } }
				 */
				,
				// 自定义错误消息放到哪里
				errorPlacement: function (error, element) {
					//	element.next().remove();// 删除显示图标

					if (error[0].innerHTML != "") {

						var ofstclass="col-xs-offset-4";
						if(typeof(offsetclass)!="undefined")
							ofstclass=offsetclass;

						element.closest('.form-group').find(
							".error").remove();
						element
							.after('<span class="error glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
						element.closest('.form-group').append(
							"<div class=\" errormsg col-xs-12 row "+ofstclass+" \">"

							+ error[0].outerHTML

							+ "</div>"
						);// 显示错误消息提示
					}
				},
				// 给未通过验证的元素进行处理
				highlight: function (element) {
					$(element).closest('.form-group').addClass(
						'has-error has-feedback');
				},
				// 验证通过的处理
				success: function (label) {
					var el = label.closest('.form-group').find(
						".error");

					el
						.after('<span class="error glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');

					el.remove();// 与errorPlacement相似


					label.closest('.form-group').removeClass(
						'has-error').addClass(
						"has-feedback has-success");

					label.closest('.form-group').find(
						".errormsg").remove();

					label.remove();
				},
				submitHandler:submithanlder

			});


		
			window.kvalidate.validators[selector.selector] = jqueryValidator;
		

	};
	
	
	jQuery.validator.setDefaults({
		onsubmit:false
	}) ;
	

	initValidate();

	window.kvalidate
		.init = function (selector, rules, messages, submithanlder,debug,cls) {
			initFormValidate(selector, rules, messages, submithanlder,debug,cls);
		};
	window.kvalidate
		.validate = function (selector) {
			//if(typeof(selector)=="undefined")
		//	this.validators[selector].form();
			if (this.validators[selector] != null)
				{
				if(this.validators[selector].form())
				this.validators[selector].settings.submitHandler.call(this.validators[selector], this.validators[selector].currentForm );
				}
		};
	window.kvalidate
		.resetForm = function (selector) {
			if (this.validators[selector] != null)
	
				jqueryValidator = this.validators[selector];
				jqueryValidator.resetForm();
				$(jqueryValidator.currentForm).find(
					".error").remove();
				$(jqueryValidator.currentForm).find(
					".errormsg").remove();

				$(jqueryValidator.currentForm).find('.form-group').removeClass(
					"has-error has-feedback has-success");
			};

		




})(window);