package com.kxjl.base.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect    // for aop
@Component // for auto scan
@Order(0)  // execute before @Transactional
public class KDataSourceInterceptor {    
    @Pointcut("execution( * com.kxjl.admin.persistence..*.*(..)  )")
    public void dataSourceKg(){};
    
    @Before("dataSourceKg()")
    public void beforekg(JoinPoint jp) {
    	//System.out.println("kg...");
        KDataSourceTypeManager.set(KDataSourceType.KG);
    }
    // ... ...

    
    @Pointcut("execution( * com.kxjl.web..*.*(..)  )")
    public void dataSourceKb(){};
    
   
    @After("dataSourceKg()")
    public void AfterKg(JoinPoint jp) {
    	//System.out.println("kg...");
        KDataSourceTypeManager.set(KDataSourceType.KB);
    }
    

}