package com.lefonddeletang.qrservices.view.servlet.manager;

import com.lefonddeletang.qrservices.controller.GuestbookAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet permetant l'accès à la gestion des guestbook (livres d'or)
 */
@WebServlet(name="GuestbookManager", urlPatterns="/guestbookmanager")
public class GuestbookManagerServlet extends HttpServlet {
	private static final long serialVersionUID = -3723149978492225546L;

	/**
	 * Requête POST : Création d'un service Guestbook
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(final HttpServletRequest request, final  HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final int id = (int) session.getAttribute("credentials");
        final String titre = request.getParameter("titre");
        final String description = request.getParameter("description");
        GuestbookAction.createGuestbook(id,titre,description);
        response.sendRedirect("/manage");
    }
}
