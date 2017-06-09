package com.lefonddeletang.qrservices.view.servlet.manager;

import com.lefonddeletang.qrservices.controller.NewsletterAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet permetaant l'accès à la gestion des newsletter
 * la method doPost permet d'ajouter un service newsletter.
 */
@WebServlet(name="newsLetterManager", urlPatterns="/newslettermanager")
public class NewsletterManagerServlet extends HttpServlet {
	private static final long serialVersionUID = -1813070091028443643L;

	/**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final int id = (int) session.getAttribute("credentials");
        final String titre = request.getParameter("titre");

        String description = request.getParameter("description");
        NewsletterAction.createNewsletter(id,titre,description);
        response.sendRedirect("/manage");
    }
}
