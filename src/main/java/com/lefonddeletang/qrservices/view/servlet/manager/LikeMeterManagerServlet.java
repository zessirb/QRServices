package com.lefonddeletang.qrservices.view.servlet.manager;

import com.lefonddeletang.qrservices.controller.LikeMeterAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hugo on 07/06/2017.
 */
@WebServlet(name="LikeMeterManager", urlPatterns="/likemetermanager")
public class LikeMeterManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("credentials");


        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        LikeMeterAction.createLikeMeter(id,titre,description);
        resp.sendRedirect("/manage");
    }
}
