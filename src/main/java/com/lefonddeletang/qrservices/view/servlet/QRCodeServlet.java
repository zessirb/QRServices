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
@WebServlet(name="qrcode", urlPatterns="/services/qrcode/*")
public class QRCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ByteArrayOutputStream out = BarcodeHandler.generateBarcodeFromUrl("test").get();

        resp.setContentType("image/png");
        resp.setContentLength(out.size());

        OutputStream outStream = resp.getOutputStream();

        outStream.write(out.toByteArray());

        outStream.flush();
        outStream.close();
    }
}
