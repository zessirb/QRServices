package com.lefonddeletang.qrservices.view.filter;

import com.lefonddeletang.qrservices.controller.ServiceAction;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hugo on 02/06/2017.
 */
@WebFilter(filterName = "services",urlPatterns={"/services/*"})
public class ServiceFilter implements  javax.servlet.Filter  {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String[] url = request.getRequestURI().split("/");
        String urlpart = url[url.length-1];
        if (urlpart == null || urlpart== "") {
            response.sendError(400);
        } else if (!ServiceAction.getServiceIdFromUrl(urlpart).isPresent()){
            response.sendError(404);
        } else {

            int id = ServiceAction.getServiceIdFromUrl(urlpart).get();
            String type = ServiceAction.getServiceType(id).get();
            request.setAttribute("url",urlpart);
            request.setAttribute("id", id);

            String newURI= "/service/"+type+"/"+urlpart;
            request.getRequestDispatcher(newURI).forward(request, response);
           // filterChain.doFilter(request,response);
        }
    }


    public void destroy() {

    }
}
