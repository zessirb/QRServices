package com.lefonddeletang.qrservices.view.servlet.clients;

import com.lefonddeletang.qrservices.controller.GuestbookAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 06/06/2017.
 */
@WebServlet(name="GuestbookService", urlPatterns="/service/guestbook/*")
public class GuestbookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = (Integer)req.getAttribute("id");


        List<String[]> comments = GuestbookAction.getGuestbookCommentsbyServiceId(id).isPresent()?
                GuestbookAction.getGuestbookCommentsbyServiceId(id).get() : new ArrayList<String[]>();
        req.setAttribute("url",(String) req.getAttribute("url"));
        req.setAttribute("comments",comments);
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "guestbook" +".jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer)req.getAttribute("id");
        String title = req.getParameter("title");
        String message= req.getParameter("message");
        GuestbookAction.createComment(id,title,message,"Guest");

        resp.sendRedirect("/services/"+req.getAttribute("url"));
    }
}
