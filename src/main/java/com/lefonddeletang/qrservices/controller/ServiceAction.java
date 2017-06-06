package com.lefonddeletang.qrservices.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.lefonddeletang.qrservices.model.beans.ServiceBean;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;

public class ServiceAction {
	/** Constante définissant la taille des URL générées **/
	static private int URLSIZE = 5;
	/** Chaîne de caractères constante contenant tous les caractères autorisés dans les URL **/
	static private String AUTHORIZEDURLCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	/** Tableau contenant tous les types de services (minuscules) existant **/
	static private List<String> SERVICETYPES = Arrays.asList("likemeter", "guestbook");
	
	static private ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie l'identifiant d'un service dont l'URL est fournie
	 * 
	 * @param url Section de l'URL correspondant au service
	 * @return Identifiant (optionnel) du service, ou empty si inexistant
	 */
	static public Optional<Integer> getServiceIdFromUrl(String url) {
		Optional<List<ServiceBean>> serviceList = serviceDao.getServicesByUrl(url);
		if (serviceList.isPresent() && serviceList.get().size() > 0) {
			try {
				ServiceBean service = serviceList.get().get(0);
				int serviceId = service.getId();
				return Optional.ofNullable(serviceId);
			} catch (Exception e) {
				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Renvoie le type d'un service à partir de son id, si le type est reconnu
	 * 
	 * @param id Identifiant du service
	 * @return String (optionnel) désignant le type de service
	 */
	static public Optional<String> getServiceType(int id) {
		Optional<ServiceBean> optionalService = serviceDao.getService(id);
		if (optionalService.isPresent()) {
			try {
				ServiceBean service = optionalService.get();
				String type = service.getType();
				if (isServiceTypeExistant(type.toLowerCase())) {
					return Optional.ofNullable(type.toLowerCase());
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
	 * Renvoie un tableau contenant les textes concernant un service
	 * 
	 * @param id Identifiant du service
	 * @return Tableau (optionnel) contenant l'URL, le nom et la description du service
	 */
	static public Optional<String[]> getServiceTexts(int id) {
		Optional<ServiceBean> optionalService = serviceDao.getService(id);
		if (optionalService.isPresent()) {
			try {
				ServiceBean service = optionalService.get();
				String[] textArray = new String[3];
				textArray[1] = service.getUrl();
				textArray[2] = service.getName();
				textArray[3] = service.getDescription();
				return Optional.ofNullable(textArray);
			} catch (Exception e) {
				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Valide l'existance du type de service correspondant au String fourni
	 * 
	 * @param type Type de service à vérifier
	 * @return Booléen d'existance
	 */
	static public boolean isServiceTypeExistant(String type) {
		return SERVICETYPES.contains(type.toLowerCase());
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
