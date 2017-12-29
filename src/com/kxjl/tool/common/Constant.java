package com.kxjl.tool.common;

import org.springframework.context.ApplicationContext;

public class Constant {

	
	
	
	
/**
 * 定义常量类	
 */
	/**登录成功 ；用户正常状态*/
	public static final int login_status = 1; //登录成功
	/**登录失败*/
	public static final int login_status_unsuces=2;//登录失败
	/**账号锁定；用户状态锁定*/
	public static final int login_status_lock=3;//账号锁定
	/**账号解锁*/
	public static final int login_status_unlock=4;//账号解锁
	/**登出*/
	public static final int logout_status = 0;//登出
	
	public static final String  localAuth = "1";//本地鉴权
	
	public static final String  IAMAuth = "2";//第三方鉴权
	
	public static final String SESSION_USER = "sessionUser"; //session保存
	
	public static final String NO_INTERCEPTOR_PATH = ".*/((loginin)|(logout)|(code)).*";	//不对匹配该值的访问路径拦截（正则）
	
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	
	/*第一次加解密秘钥*/
	public static final String FIRST_AES_KEY="b2xkcGhvdG9vbGRw";
	
	  /**
     * 处理OK的返回码
     */
    public static final String OKCode = "200";
    
    /**
     * 数据库访问异常错误码
     */
    public static final String DataBaseErrorCode = "201";
    
    /**
     * 失败返回码
     */
    public static final String FailCode = "201";
    
    /**
     * 字段校验返回码
     */
    public static final String CheckCode = "202";
    
    /**
     * 空值异常
     */
    public static final String NullErrorCode = "202";
    
    /**
     * 密码连续错误
     */
    public static final String PasswordForErrorCode = "203";
    
    /**
     * 物理机达到最大并发数，请稍后再登录
     */
    public static final String PhysicalMachineMaximumConcurrency = "204";
    
    /**
     * 虚拟机开户异常，请联系管理员
     */
    public static final String VMAccountCode = "205";
    
    /**
     * 虚拟机发生变化，请重新登录
     */
    public static final String VMIsUsed = "206";
    
    /**
     * 物理机容量已满，请联系管理员
     */
    public static final String PhysicalMachineCapacityFull = "207";
    
    /**
     * 该用户使用的虚拟机被异常删除
     */
    public static final String VMNullErrorCode = "208";
    
    /**
     * 正在创建虚拟机准备环境
     */
    public static final String CreateNewVM = "209";
    
    /**
     * 虚拟机正在启动
     */
    public static final String VMStarting = "210";
    
    /**
     * 请求报文异常
     */
    public static final String JSONERROR = "211";
    
    /**
     * 请求任务数超过最大值
     */
    public static final String TaskOverMax = "212";
    
    /**
     * 执行虚拟手机不足
     */
    public static final String VMPnoneShortage = "213";

    
    /**
     * 系统异常错误码
     */
    public static final String SystemError = "500";
    
    /**
     * 共享用户
     */
    public static final Integer PublicUser = 1;
    
    /**
     * 专用用户
     */
    public static final Integer PrivateUser = 2;
    
    /**
     * 设备任务状态
     */
    public static final int StatusToBeIssued = 0;//待下发
    public static final int StatusIssued = 1;//已下发 
    public static final int StatusSuccess = 2;//执行成功
    public static final int StatusFailedUnknown = 3;//执行失败，未知原因
    public static final int StatusParametersAbnormal = 4;//执行参数异常
    public static final int StatusSendSMSFailed = 5;//发送短信验证码失败
    public static final int StatusSMSReadyFailed = 6;//获取短信验证码准备失败
    public static final int StatusSMSGetFailed = 7;//无法获取到短信验证码
    public static final int StatusPhoneNoConnect = 8;//手机插件未连接
    public static final int StatusTimeOut = 9;//执行超时
    public static final int StatusSetDeviceInfoFailed = 10;//执行失败，因设置设备信息异常，设备无法执行任务
    public static final int StatusSetAddressFailed = 11;//执行失败，因设置位置异常，需重新设置位置。    
    public static final int StatusIsRunning = 20;//任务正在执行
    public static final int StatusIsReRunning = 21;//正在执行中的任务循环重试
    public static final int StatusIsRunOverTime = 22;//执行超时
    public static final int StatusIsArrange = 22;//已安排
    
    /**
     * 镜像的状态
     */
    
    public static final Integer ImageStatusIsNotUsed = 0;
    public static final Integer ImageStatusSetInfo = 1;//设置信息 
    public static final Integer ImageStatusDispatchTask = 2;//下发任务
    public static final Integer ImageStatusIsClose = 3;//正在关闭
    public static final Integer ImageStatusIsYundun = 4;//云盾客户端连接
    public static final String ImageStatusIsNotWork = "1";
    public static final String ImageStatusIsWorking = "2";
    public static final String ImageStatusIsNonexistence = "3";
    
    /**
     * 获取短信验证码模式，需要获取短信验证码时有效：1、手机设备模式，从手机设备获取；2、猫模式，从猫设备获取。
     */
    public static final Integer SmsCodeModeMobile = 1;
    public static final Integer SmsCodeModeModel = 2;
    
    /**
     * 通知宿主机任务执行完成
     */
    public static final Integer NotifyHomed = 1;
    public static final Integer NoNotifyHomed = 0;
    
    /**
     * 网络出口方式
     */
    public static final int ExportMobile = 1;
    public static final int ExportPC = 2;
    public static final int NoExport = 3;
    
    /**
     * PC网络出口状态
     */
    public static final int Connect = 1;
    public static final int DisConnect = 0;
    
    /**
     * 客户端类型
     */
    public static final int ClientTypeAndroid = 1;//Android
    public static final int ClientTypeIOS = 2;//iOS
    public static final int ClientTypePC = 3;//PC
    
    /**
     * 网络等级
     */
    public static final int NetLevelPoor = 1;//差
    public static final int NetLevelGeneral = 2;//一般
    public static final int NetLevelGood = 3;//好
    
    /**
     * 网络类型
     */
    public static final int NetTypeUnknown = 0;//未知网络类型； 1：WiFi； 2:2G； 3:3G； 4:4G。
    public static final int NetTypeWifi = 1;//WiFi
    public static final int NetType2G = 2;//2G
    public static final int NetType3G = 3;//3G
    public static final int NetType4G = 4;//4G
    
}
