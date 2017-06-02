package filter;

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
            if(servletRequest.getParameter("id")== null){
          response.sendError(404,"pas d'id");
      }
        else{
                filterChain.doFilter(request,response);
            }

          String id = (String) servletRequest.getAttribute("id");

    }

    public void destroy() {

    }
}
