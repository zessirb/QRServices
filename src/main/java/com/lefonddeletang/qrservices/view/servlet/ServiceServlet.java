package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.LikeMeterAction;
import com.lefonddeletang.qrservices.view.handler.ServiceHander;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hugo on 02/06/2017.
 */
@WebServlet(name="Service", urlPatterns="/services/*")
public class ServiceServlet extends HttpServlet {

    private final String PATH_VIEW = "/WEB-INF/view/";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String)req.getAttribute("id");
        req.setAttribute("id",id);

        LikeMeterAction.createLikeMeter(1,"likeMeter","Azer1","compteur de like");
        req.setAttribute("liked",true);
        req.setAttribute("count",495);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "likeMeter" +".jsp").forward(req, resp);

    }
}
