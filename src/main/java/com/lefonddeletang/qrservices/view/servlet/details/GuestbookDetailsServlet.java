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
 * Ce servlet permet d'acceder au details d'un guestbook par son proprietaire
 */
@WebServlet(name = "GuestbookDetails", urlPatterns = "/detail/guestbook/*")
public class GuestbookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 9107932537441085538L;

	/**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        int id = (Integer)request.getAttribute("id");
        final String[] info = ServiceAction.getServiceTexts(id).get();

        request.setAttribute("url",(String) request.getAttribute("url"));
        request.setAttribute("service",info);
        request.setAttribute("type","guestbook");
        request.setAttribute("comments",GuestbookAction.getGuestbookCommentsbyServiceId(id).get());
        getServletContext().getRequestDispatcher("/WEB-INF/view/" + "details" +".jsp").forward(request, response);

    }
}
