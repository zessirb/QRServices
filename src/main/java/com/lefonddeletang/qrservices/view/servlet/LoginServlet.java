package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.UserAction;

import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet permettetant d'acceder a l'identification des utilisateurs créateur de service.
 * la methode post permet de s'identifier
 * la methode get permet de se deconnecter
 */
@WebServlet(name="login", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost( final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = request.getParameter("url");
        final String username= request.getParameter("username");
        final String password = request.getParameter("password");
        final Optional<Integer> userid = UserAction.getUserIdFromCredentials(username,password);

        if(userid.isPresent()){
            HttpSession session = request.getSession();
            session.setAttribute("credentials",userid.get());
        }
        response.sendRedirect(url);
    }
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url= request.getParameter("url");
        final HttpSession session = request.getSession();

        if(session.getAttribute("credentials")!=null){
            session.removeAttribute("credentials");
        }
        response.sendRedirect(url);
    }
}
