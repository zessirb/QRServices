package com.lefonddeletang.qrservices.view.servlet.details;

import com.lefonddeletang.qrservices.controller.LikeMeterAction;
import com.lefonddeletang.qrservices.controller.ServiceAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Ce servlet permet d'acceder au details d'un likemeter par son proprietaire
 */
@WebServlet(name="likeMeterDetails", urlPatterns = "/detail/likemeter/*")
public class LikeMeterDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = (Integer)request.getAttribute("id");
        final String[] info = ServiceAction.getServiceTexts(id).get();

        request.setAttribute("url",(String) request.getAttribute("url"));
        request.setAttribute("service",info);
        request.setAttribute("type","likemeter");
        request.setAttribute("count",LikeMeterAction.getLikeCount(id).get());

        getServletContext().getRequestDispatcher("/WEB-INF/view/" + "details" +".jsp").forward(request, response);
    }
}
