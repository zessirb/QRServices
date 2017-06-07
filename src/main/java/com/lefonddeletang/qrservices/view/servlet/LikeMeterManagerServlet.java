package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.LikeMeterAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hugo on 07/06/2017.
 */
@WebServlet(name="LikeMeterManager", urlPatterns="/likemetermanager")
public class LikeMeterManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        LikeMeterAction.createLikeMeter(1,titre,description);
    }
}
