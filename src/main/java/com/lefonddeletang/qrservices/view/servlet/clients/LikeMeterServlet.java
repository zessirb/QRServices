package com.lefonddeletang.qrservices.view.servlet.clients;

import com.lefonddeletang.qrservices.controller.LikeMeterAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet de consomation du likemeter par l'utilisateur final
 */
@WebServlet(name="LikeMeterService", urlPatterns="/service/likemeter/*")
public class LikeMeterServlet extends HttpServlet {
	private static final long serialVersionUID = 3681834966945075969L;

	/**
	 * Requête GET : Affichage de compteur de Like
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
        final int id = (Integer)request.getAttribute("id");
        final Optional<Integer> countOptional =LikeMeterAction.getLikeCount(id);
        final Optional<Boolean> likedOptional = LikeMeterAction.isUserLogged(id,request.getRemoteAddr());
        final int count = countOptional.isPresent()? countOptional.get() : 0;
        final boolean liked = likedOptional.isPresent()? likedOptional.get():false;

        request.setAttribute("liked",liked);
        request.setAttribute("count",count);
        request.setAttribute("url",(String) request.getAttribute("url"));
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "likeMeter" +".jsp").forward(request, response);
    }

    /**
     * Requête POST : Ajout d'un Like au compteur
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        final String ip = request.getRemoteAddr();
        final int id = (Integer)request.getAttribute("id");

        LikeMeterAction.addLike(id,ip);
        response.sendRedirect("/services/"+request.getAttribute("url"));
    }


}
