package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.BarcodeHandler;
import net.glxn.qrgen.image.ImageType;

import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hugo on 06/06/2017.
 */
@WebServlet(name="qrcode", urlPatterns="/qrcode/*")
public class QRCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] url = req.getRequestURI().split("/");
        String urlpart = url[url.length-1];
        if (urlpart == null || urlpart== "") {
            resp.sendError(400);
        }



        ByteArrayOutputStream out = BarcodeHandler.generateBarcodeFromUrl(req.getServerName()+":"+req.getServerPort()+"/services/"+urlpart).get();

        resp.setContentType("image/png");
        resp.setContentLength(out.size());

        OutputStream outStream = resp.getOutputStream();

        outStream.write(out.toByteArray());

        outStream.flush();
        outStream.close();
    }
}
