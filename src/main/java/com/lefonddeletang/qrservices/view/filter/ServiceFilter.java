package com.lefonddeletang.qrservices.view.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hugo on 02/06/2017.
 */
@WebFilter(filterName = "services",urlPatterns={"/service/*"})
public class ServiceFilter implements  javax.servlet.Filter  {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String[] url = request.getRequestURI().split("/");
        String id = url[url.length-1];
        if (id == null) {
            response.sendError(400);
        } else {
             request.setAttribute("id", id);
            filterChain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}
