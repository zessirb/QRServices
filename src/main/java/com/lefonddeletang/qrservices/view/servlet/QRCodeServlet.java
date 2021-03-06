package com.lefonddeletang.qrservices.view.servlet;

import com.lefonddeletang.qrservices.controller.BarcodeHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

/**
 * Servlet d'affichage des QRCode
 */
@WebServlet(name="qrcode", urlPatterns="/qrcode/*")
public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = -4555555204351312719L;

	/**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
final String[] url = request.getRequestURI().split("/");
        final String urlpart = url[url.length-1];
    	final Optional<ByteArrayOutputStream> qrCodeBytesOpt = BarcodeHandler.generateBarcodeFromUrl(request.getServerName()+":"+request.getServerPort()+"/services/"+urlpart);

        if (urlpart == null || urlpart== "") {
            response.sendError(400);
        } else if (qrCodeBytesOpt.isPresent()) {
            ByteArrayOutputStream qrCodeBytes = qrCodeBytesOpt.get();
            response.setContentType("image/png");
            response.setContentLength(qrCodeBytes.size());
            OutputStream outStream = response.getOutputStream();
            outStream.write(qrCodeBytes.toByteArray());
            outStream.flush();
            outStream.close();
        } else {
            response.sendError(404);
        }
    }
}
