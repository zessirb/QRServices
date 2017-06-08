package com.lefonddeletang.qrservices.view.servlet.details;

import com.lefonddeletang.qrservices.controller.GuestbookAction;
import com.lefonddeletang.qrservices.controller.ServiceAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hugo on 08/06/2017.
 */
@WebServlet(name = "GuestbookDetails", urlPatterns = "/detail/guestbook/*")
public class GuestbookDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer)req.getAttribute("id");



        String[] info = ServiceAction.getServiceTexts(id).get();
        req.setAttribute("url",(String) req.getAttribute("url"));
        req.setAttribute("service",info);

        req.setAttribute("type","guestbook");
        req.setAttribute("comments",GuestbookAction.getGuestbookCommentsbyServiceId(id).get());
        getServletContext().getRequestDispatcher("/WEB-INF/view/" + "details" +".jsp").forward(req, resp);

    }
}
