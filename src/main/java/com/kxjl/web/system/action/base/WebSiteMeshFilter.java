package com.kxjl.web.system.action.base;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import com.kxjl.web.config.CustomTagRuleBundle;


public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
    	
    	
    	builder.addDecoratorPath("/", "/decorators/template.jsp");
    	
    	builder.addDecoratorPath("/public/*", "/decorators/frontend/index.jsp");
    	
    	
    	
      /*  builder.addDecoratorPath("/manager/*", "/decorators/admin/admin_index.ftl");

        builder.addDecoratorPath("/privilege/*", "/decorators/permission/admin_index.ftl");
        builder.addDecoratorPath("/user/*", "/decorators/user/user_index.ftl");
        builder.addDecoratorPath("/public/index.html", "/decorators/public/index.ftl");
        builder.addDecoratorPath("/public/*", "/decorators/public/index.ftl");

        builder.addDecoratorPath("/userCenter/compliant/index", "/decorators/public/index.ftl");
        builder.addDecoratorPath("/userCenter/*", "/decorators/public/userCenter.ftl");

        builder.addDecoratorPath("/front/*", "/decorators/public/index1.ftl");

        builder.addDecoratorPath("/login.action", "/decorators/login_index.ftl");
        builder.addDecoratorPath("/", "/decorators/default_index.ftl");*/
        builder .addExcludedPath("/images/*")
                .addExcludedPath("/file/*")
                .addExcludedPath("/cool/*")
                .addExcludedPath("/css/*")
                .addExcludedPath("/js/*")
                .addExcludedPath("/public/html/*")
                .addExcludedPath("/UploadFileXhr")
                .addExcludedPath("/logoUploadFile")
                .addExcludedPath("/UploadCKFile")
                .addExcludedPath("/UploadFile")
                
                .addExcludedPath("/FileSvr/*")
               .addExcludedPath("/**.action")
               .addExcludedPath("/**.do")
             
           
                ;

        
        //customer decorator  
        builder.addTagRuleBundle(new CustomTagRuleBundle()); 
    }
    
   
}
