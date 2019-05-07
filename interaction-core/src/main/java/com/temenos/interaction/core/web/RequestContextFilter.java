package com.temenos.interaction.core.web;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * @author Mattias Hellborg Arthursson
 * @author Kalle Stenflo
 */
public class RequestContextFilter implements Filter {
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        long requestTime = System.currentTimeMillis();
        final HttpServletRequest servletRequest = (HttpServletRequest) request;
        final HttpServletResponse servletResponse = (HttpServletResponse) response;

        servletResponse.setHeader("Server", "SecureServer");
        servletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        String requestURI = servletRequest.getRequestURI();
        requestURI = StringUtils.removeStart(requestURI, servletRequest.getContextPath() + servletRequest.getServletPath());
        String baseURL = StringUtils.removeEnd(servletRequest.getRequestURL().toString(), requestURI);

        Map<String, List<String>> headersMap = new HashMap<>();
        Enumeration<String> headerNames = servletRequest.getHeaderNames();
        if(headerNames != null) {
            while(headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                List<String> valuesList = Collections.list(servletRequest.getHeaders(headerName));
                headersMap.put(headerName, valuesList);
            }
        }

        RequestContext.Builder reqCtxBuilder = new RequestContext.Builder()
                                                .setBasePath(baseURL)
                                                .setRequestUri(servletRequest.getRequestURI())
                                                .setVerbosityHeader(servletRequest.getHeader(RequestContext.HATEOAS_OPTIONS_HEADER))
                                                .setHeaders(headersMap)
                                                .setRequestTime(requestTime)
                                                .setRequestId(UUID.randomUUID().toString());    // Trace the request recieved
        Principal userPrincipal = servletRequest.getUserPrincipal();
        if (userPrincipal != null) {
            reqCtxBuilder.setUserPrincipal(userPrincipal);
        }
        RequestContext.setRequestContext(reqCtxBuilder.build());
        
        try {
            chain.doFilter(request, response);
        } finally {
            RequestContext.clearRequestContext();
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
