package com.lefonddeletang.qrservices.controller;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * Classe utilitaire de génération de QR Codes
 */
public class BarcodeHandler {
	/** Constante définissant la résolution (côté) du QRCode **/
	static private int BARCODESIDESIZE = 250;
	/** Constante définissant le type d'image à renvoyer **/
	static private ImageType IMAGETYPE = ImageType.PNG;
	
	/**
	 * Renvoie un QRCode en binaire à partir d'une URL
	 * 
	 * @param url URL à encoder
	 * @return Tableau (optionnel) de bytes
	 */
	static public Optional<ByteArrayOutputStream> generateBarcodeFromUrl(String url) {
		ByteArrayOutputStream stream = QRCode.from(url)
				.withSize(BARCODESIDESIZE, BARCODESIDESIZE)
				.to(IMAGETYPE)
				.stream();
		try {
			//byte[] bytes = stream.toByteArray();
			return Optional.ofNullable(stream);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
