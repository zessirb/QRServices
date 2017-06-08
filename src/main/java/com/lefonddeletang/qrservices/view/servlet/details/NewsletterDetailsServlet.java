package com.lefonddeletang.qrservices.view.servlet.details;

import com.lefonddeletang.qrservices.controller.NewsletterAction;
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
@WebServlet(name="NewsletterDetails", urlPatterns = "/detail/newsletter/*")
public class NewsletterDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer)req.getAttribute("id");

        req.setAttribute("url",(String) req.getAttribute("url"));


        req.setAttribute("type","newsletter");
        req.setAttribute("mails", NewsletterAction.getNewsletterEmails(id).get());

        getServletContext().getRequestDispatcher("/WEB-INF/view/" + "details" +".jsp").forward(req, resp);
    }
}
