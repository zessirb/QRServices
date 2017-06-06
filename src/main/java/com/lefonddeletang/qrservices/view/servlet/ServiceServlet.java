package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.LikeMeterAction;
import com.lefonddeletang.qrservices.controller.ServiceAction;
import com.lefonddeletang.qrservices.view.handler.ServiceHander;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * Created by hugo on 02/06/2017.
 */
@WebServlet(name="Service", urlPatterns="/services/*")
public class ServiceServlet extends HttpServlet {

    private final String PATH_VIEW = "/WEB-INF/view/";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer)req.getAttribute("id");

        Optional<Integer> countOptional =LikeMeterAction.getLikeCount(id);
        Optional<Boolean> likedOptional = LikeMeterAction.isUserLogged(1,req.getRemoteAddr());
        int count = countOptional.isPresent()? countOptional.get() : 0;
        boolean liked = likedOptional.isPresent()? likedOptional.get():false;
        req.setAttribute("liked",liked);
        req.setAttribute("count",count);
        req.setAttribute("url",(String) req.getAttribute("url"));
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/" + "likeMeter" +".jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        String ip = req.getRemoteAddr();
        int id = (Integer)req.getAttribute("id");
        LikeMeterAction.addLike(id,ip);

        //resp.sendRedirect(req.getRequestURI());
    }


}
