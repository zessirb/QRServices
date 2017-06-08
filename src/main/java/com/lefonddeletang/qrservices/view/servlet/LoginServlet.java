package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.UserAction;

import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by hugo on 08/06/2017.
 */
@WebServlet(name="login", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String url = req.getParameter("url");
            String username= req.getParameter("username");
            String password = req.getParameter("password");


            Optional<Integer> userid = UserAction.getUserIdFromCredentials(username,password);

        if(userid.isPresent()){
            HttpSession session = req.getSession();
            session.setAttribute("credentials",userid.get());
        }
        resp.sendRedirect(url);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url= req.getParameter("url");
        HttpSession session = req.getSession();
        if(session.getAttribute("credentials")!=null){
            session.removeAttribute("credentials");
        }
        resp.sendRedirect(url);
    }
}
