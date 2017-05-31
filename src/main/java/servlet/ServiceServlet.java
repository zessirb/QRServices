package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet d'interactions avec des services existants
 * D'ici, l'utilisateur peut utiliser un service existant
 */
@WebServlet(name="Service", urlPatterns="/service")
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 4744540075863839040L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	response.setContentType("text/html");
        response.setCharacterEncoding( "UTF-8" );
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><body><h1>Service</h1></body></html>");
    }
}
