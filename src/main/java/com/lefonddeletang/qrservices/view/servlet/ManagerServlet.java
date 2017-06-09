package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.ServiceAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet de gestion des services d'un utilisateur depuis un manager.
 * D'ici, l'utilisateur peut créer, modifier et supprimer des services lui appartenant.
 */
@WebServlet(name="Manager", urlPatterns="/manage")
public class ManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 8802864716415469670L;

    public void doGet(final HttpServletRequest request,final HttpServletResponse response ) throws ServletException, IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding( "UTF-8" );

        boolean logged = false;
        HttpSession session = request.getSession();

        if (session.getAttribute("credentials") != null) {
            int userid =(int) session.getAttribute("credentials");
            logged = true;
            request.setAttribute("services",ServiceAction.getServicesTextsByUser(userid).get());
        }
        request.setAttribute("logged",logged);
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "manageService" +".jsp").forward(request, response);
    }
}
