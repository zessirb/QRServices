package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.GuestbookAction;
import com.lefonddeletang.qrservices.controller.NewsletterAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de gestion des services d'un utilisateur depuis un manager.
 * D'ici, l'utilisateur peut créer, modifier et supprimer des services lui appartenant.
 */
@WebServlet(name="Manager", urlPatterns="/manage")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 8802864716415469670L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	response.setContentType("text/html");
        response.setCharacterEncoding( "UTF-8" );

        NewsletterAction.createNewsletter(1,"pomme","soyez toujours a jours sur les pommes");
        boolean logged= true;

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "manageService" +".jsp").forward(request, response);
    }
}
