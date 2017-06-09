package com.lefonddeletang.qrservices.view.servlet.details;

import com.lefonddeletang.qrservices.controller.NewsletterAction;
import com.lefonddeletang.qrservices.controller.ServiceAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Ce servlet permet d'acceder au details d'une newsletter par son proprietaire
 */
@WebServlet(name="NewsletterDetails", urlPatterns = "/detail/newsletter/*")
public class NewsletterDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = -6021474831334025206L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = (Integer)request.getAttribute("id");
        final String[] info = ServiceAction.getServiceTexts(id).get();

        request.setAttribute("url",(String) request.getAttribute("url"));
        request.setAttribute("service",info);
        request.setAttribute("type","newsletter");
        Optional<String[]> mail = NewsletterAction.getNewsletterEmails(id);
        if(mail.isPresent()){
            request.setAttribute("mails",mail.get());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/" + "details" +".jsp").forward(request, response);
    }
}
