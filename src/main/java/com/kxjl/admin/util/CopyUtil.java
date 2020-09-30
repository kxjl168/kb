
package com.kxjl.admin.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class CopyUtil {

    private static final Logger logger = LoggerFactory.getLogger(CopyUtil.class);

    
    /* 
     * 将父类所有的属性COPY到子类中。 
     * 类定义中child一定要extends father； 
     * 而且child和father一定为严格javabean写法，属性为deleteDate，方法为getDeleteDate 
     */  
    public static void fatherToChild (Object father,Object child){  
        if(!(child.getClass().getSuperclass()==father.getClass())){  
            System.err.println("child不是father的子类"); 
        }  
        Class fatherClass= father.getClass();  
        Field ff[]= fatherClass.getDeclaredFields();  
        
        //设置可达性
        AccessibleObject.setAccessible(ff, true);
       
        for(int i=0;i<ff.length;i++){  
            Field f=ff[i];//取出每一个属性，如deleteDate  
            Class type=f.getType();  
            try {
                Method m = fatherClass.getMethod("get"+upperHeadChar(f.getName()));//方法getDeleteDate  
                Object obj=m.invoke(father);//取出属性值               
                f.set(child,obj);
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        }  
    }  
    /** 
     * 首字母大写，in:deleteDate，out:DeleteDate 
     */  
    private static String upperHeadChar(String in){  
        String head=in.substring(0,1);  
        String out=head.toUpperCase()+in.substring(1,in.length());  
        return out;  
    } 
    
    /**
     * 浅拷贝，同名属性拷贝
     * @param src
     * @param target
     * @param <T>
     * @param <V>
     */
    public static <T, V> void copySameName(T src, V target) {
        //获取class对象
        Class srcClass = src.getClass();
        Class targetClass = target.getClass();

        //获得类属性
        Field[] srcFields = srcClass.getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();

        //设置可达性
        AccessibleObject.setAccessible(srcFields, true);
        AccessibleObject.setAccessible(targetFields, true);

        //进行拷贝
        for (Field srcField : srcFields) {
            String srcFieldName = srcField.getName();
            for (Field targetField : targetFields) {
                String targetFieldName = targetField.getName();
                if (srcFieldName.equals(targetFieldName)) {
                    try {
                        targetField.set(target, srcField.get(src));
                    } catch (Exception e) {
                        logger.error(srcClass.getName() + "." + srcFieldName + "与"
                                + targetClass.getName() + "." + targetFieldName + "参数同名但类型不同，拷贝失败！", e);
                    }
                }
            }
        }
    }
}
