package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.GuestbookAction;
import com.lefonddeletang.qrservices.controller.LikeMeterAction;
import com.lefonddeletang.qrservices.controller.NewsletterAction;
import com.lefonddeletang.qrservices.controller.ServiceAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;

/**
 * Servlet de gestion des services (suppression)
 */
@WebServlet(name="ServiceManager", urlPatterns = "/servicemanager")
public class ServiceManagerServlet extends HttpServlet {
    /**
     *
     * @param request requète http
     * @param response reponse http
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        int id = ServiceAction.getServiceIdFromUrl(url).get();
        String type = ServiceAction.getServiceType(id).get();
        switch (type){
            case "likemeter":
                LikeMeterAction.deleteLikeMeter(id);
                break;
            case "guestbook":
                GuestbookAction.deleteGuestbook(id);
                break;
            case "newsletter":
                NewsletterAction.deleteNewsletter(id);
                break;
        }
        response.sendRedirect("/manage");
    }
}
