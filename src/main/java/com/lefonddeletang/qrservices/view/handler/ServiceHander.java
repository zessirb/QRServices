package com.lefonddeletang.qrservices.view.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hugo on 02/06/2017.
 */
public class ServiceHander {

    private final String PATH_VIEW = "WEB-INF/view/";

    private static ServiceHander serviceHandler =null;


    private ServiceHander (){

    }

    public static ServiceHander getInstance(){
      if (serviceHandler == null){
          return new ServiceHander();
      }
        return serviceHandler;
    }


    public void dispatche(ServletContext servletContext, HttpServletRequest req , HttpServletResponse resp){
      String type = (String)req.getAttribute("id");
        try{
          switch (type){
              case("likeMeter"):
                  //servletContext.getRequestDispatcher(PATH_VIEW+ "likemeter" +".jsp").forward(req, resp);
                  break;
          }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
}
