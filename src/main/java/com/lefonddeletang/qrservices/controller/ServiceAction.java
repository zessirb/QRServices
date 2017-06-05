package com.lefonddeletang.qrservices.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.lefonddeletang.qrservices.model.beans.ServiceBean;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;

public class ServiceAction {
	/** Constante définissant la taille des URL générées **/
	static private int URLSIZE = 5;
	/** Chaîne de caractères constante contenant tous les caractères autorisés dans les URL **/
	static private String AUTHORIZEDURLCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	/** Map constante contenant tous les types de services (minuscules) et leur intitulé associé **/
	static private Map<String, String> SERVICETYPES = new HashMap<String, String>() {
		private static final long serialVersionUID = -7107276885097301553L;
	{
		put("likemeter", "likeMeter");
		put("guestbook", "guestbook");
	}};
	
	static private ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie un texte identifiant la nature du service dont l'URL est fournie
	 * 
	 * @param url Section de l'URL correspondant au service
	 * @return Texte (optionnel) identifiant le type de service, ou empty si non reconnu/inexistant
	 */
	static public Optional<String> getServiceType(String url) {
		Optional<List<ServiceBean>> serviceList = serviceDao.getServicesByUrl(url);
		if (serviceList.isPresent() && serviceList.get().size() > 0) {
			try {
				ServiceBean service = serviceList.get().get(0);
				String type = service.getType();
				if (SERVICETYPES.containsKey(type.toLowerCase())) {
					return Optional.ofNullable(SERVICETYPES.get(type));
				} else {
					return Optional.empty();
				}
			} catch (Exception e) {
				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Génère un morceau d'URL utilisé pour identifier un service.
	 * Réessaye si l'URL est déjà prise par un autre service (renvoie empty si erreur)
	 * 
	 * @return Code (optionnel) destiné à être appelé dans l'URL
	 */
	static public Optional<String> generateUrl() {
		Optional<String> url = Optional.empty();
		Optional<List<ServiceBean>> servicesByUrl = Optional.empty();
		do {
			StringBuilder generatedString = new StringBuilder();
			Random random = new Random();
			while (generatedString.length() < URLSIZE) {
				int index = (int) (random.nextFloat() * AUTHORIZEDURLCHARS.length());
				generatedString.append(AUTHORIZEDURLCHARS.charAt(index));
			}
			url = Optional.ofNullable(generatedString.toString());
			if (url.isPresent()) {
				servicesByUrl = serviceDao.getServicesByUrl(url.get());
			}
		} while (servicesByUrl.isPresent() && servicesByUrl.get().size() != 0);
		return url;
	}
}
