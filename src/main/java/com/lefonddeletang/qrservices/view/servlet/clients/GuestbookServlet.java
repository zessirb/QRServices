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
 * Servlet de consomation du guestbook par l'utilisateur final
 */
@WebServlet(name="GuestbookService", urlPatterns="/service/guestbook/*")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1845717282725450732L;

	/**
	 * Requête GET : Affichage d'un livre d'or
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final int id = (Integer)request.getAttribute("id");


        List<String[]> comments = GuestbookAction.getGuestbookCommentsbyServiceId(id).isPresent()?
                GuestbookAction.getGuestbookCommentsbyServiceId(id).get() : new ArrayList<String[]>();
        request.setAttribute("url",(String) request.getAttribute("url"));
        request.setAttribute("comments",comments);
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "guestbook" +".jsp").forward(request, response);
    }

    /**
     * Requête POST : Ajout d'un commentaire au livre d'or
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (Integer)request.getAttribute("id");
        String title = request.getParameter("title");
        String message= request.getParameter("message");
        GuestbookAction.createComment(id,title,message,"Guest");

        response.sendRedirect("/services/"+request.getAttribute("url"));
    }
}
