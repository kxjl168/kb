package com.kxjl.tool.prerenderio;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.web.translog.dao.SpiderlogDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.view.jasperreports.ConfigurableJasperReportsView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreRenderSEOFilter implements Filter {
    public static final List<String> PARAMETER_NAMES = Lists.newArrayList("preRenderEventHandler", "proxy", "proxyPort",
            "prerenderToken", "forwardedURLHeader", "crawlerUserAgents", "extensionsToIgnore", "whitelist",
            "blacklist", "prerenderServiceUrl");
    private PrerenderSeoService prerenderSeoService;

 @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.prerenderSeoService = new PrerenderSeoService(toMap(filterConfig),filterConfig.getServletContext());
        
    	
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        boolean isPrerendered = prerenderSeoService.prerenderIfEligible(
                (HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        if (!isPrerendered) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        prerenderSeoService.destroy();
    }

    protected void setPrerenderSeoService(PrerenderSeoService prerenderSeoService) {
        this.prerenderSeoService = prerenderSeoService;
    }

    protected Map<String, String> toMap(FilterConfig filterConfig) {
        Map<String, String> config = Maps.newHashMap();
        for (String parameterName : PARAMETER_NAMES) {
            config.put(parameterName, filterConfig.getInitParameter(parameterName));
        }
        
        String prerenderServiceUrl= ConfigReader.getInstance().getProperty("preurl",filterConfig.getInitParameter("prerenderServiceUrl"));
        config.put("prerenderServiceUrl",prerenderServiceUrl);
        
        
        return config;
    }
}

