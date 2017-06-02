package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

/**
 * Servlet d'interactions avec des services existants
 * D'ici, l'utilisateur peut utiliser un service existant
 */
@WebServlet(name="Service", urlPatterns="/services/likemeter")
public class LikeMeterServlet extends HttpServlet {
	private static final long serialVersionUID = 4744540075863839040L;
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


        String color = "blue";
        request.setAttribute("color" ,color);
        request.setAttribute("likeCount",526);
        RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/WEB-INF/view/likeMeter.jsp");
        RequetsDispatcherObj.forward(request, response);

    	/*response.setContentType("text/html");
        response.setCharacterEncoding( "UTF-8" );
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><body><h1>Service</h1></body></html>");*/
    }
}
