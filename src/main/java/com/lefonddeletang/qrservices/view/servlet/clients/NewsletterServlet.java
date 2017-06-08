package com.lefonddeletang.qrservices.view.servlet.clients;

import com.lefonddeletang.qrservices.controller.NewsletterAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hugo on 06/06/2017.
 */
@WebServlet(name="NewsLetter", urlPatterns="/service/newsletter/*")
public class NewsletterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("url", req.getAttribute("url"));

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "newsletter" +".jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        int id = (Integer)req.getAttribute("id");
        if(!mail.contains("@")){
            req.setAttribute("error",true);
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "newsletter" +".jsp").forward(req, resp);
        }else{

           if(NewsletterAction.addEmail(id,mail).get()){
               req.setAttribute("error",true);
               this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "newsletter" +".jsp").forward(req, resp);
           }else{

           }
        }
    }
}
