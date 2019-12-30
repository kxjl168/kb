/**
 * @(#)NoNeedAuthorization.java  2018-11-14
 *
 * Copyright (C),2017-2018, kxjl 
 * Co.,Ltd. All Rights Reserved.
 * kxjl PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.kxjl.web.system.action.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 外部api验证，验证apiid,api状态，
 * OutApiAuthorization.java.
 * 
 * @author zj
* @version 1.0.1 2019年7月2日
* @revision zj 2019年7月2日
* @since 1.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OutApiAuthorization {
    String value() default "";
    
    UrlType uType() ;
    
    /**
     * url类型
     * OutApiAuthorization.java.
     * 
     * @author zj
    * @version 1.0.1 2019年7月3日
    * @revision zj 2019年7月3日
    * @since 1.0.1
     */
    public enum UrlType {

    	NeedAdmin("1","需要管理员权限"),NeedLogin("2","需要登陆"),EveryOne("3","完全公开");
    	
    	
    	public String name;
    	public String desc;
    	private UrlType(String name,String desc)
    	{
    		this.name=name;
    		this.desc=desc;
    	}
    }
}
