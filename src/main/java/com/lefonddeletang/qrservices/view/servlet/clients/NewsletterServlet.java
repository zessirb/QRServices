package com.lefonddeletang.qrservices.view.servlet.clients;

import com.lefonddeletang.qrservices.controller.NewsletterAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet de consomation d'une newsletter par l'utilisateur final
 */
@WebServlet(name="NewsLetter", urlPatterns="/service/newsletter/*")
public class NewsletterServlet extends HttpServlet {
	private static final long serialVersionUID = -7089293732496005011L;
	
	/**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("url", request.getAttribute("url"));
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "newsletter" +".jsp").forward(request, response);
    }
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String mail = request.getParameter("mail");
        final int id = (Integer)request.getAttribute("id");
        if (!mail.contains("@")) {
            request.setAttribute("error",true);
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "newsletter" +".jsp").forward(request, response);
        } else {

            if (!NewsletterAction.addEmail(id,mail).get()) {
                request.setAttribute("error",true);
                this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "newsletter" +".jsp").forward(request, response);
            }
            else{
                this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "newslettersuscribed" +".jsp").forward(request, response);
            }
        }
    }
}
