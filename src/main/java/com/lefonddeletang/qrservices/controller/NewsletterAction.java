package com.lefonddeletang.qrservices.controller;

import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.NewsletterBean;
import com.lefonddeletang.qrservices.model.beans.ServiceBean;
import com.lefonddeletang.qrservices.model.dao.NewsletterDao;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;

/**
 * Controller de méthodes statiques gérant les actions réalisées par rapport à une Newsletter
 */
public class NewsletterAction {
	static private NewsletterDao newsletterDao = new NewsletterDao();
	static private ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie les emails inscrits à une Newsletter
	 * 
	 * @param serviceId Id du service de la Newsletter
	 * @return Tableau d'email optionnel (empty si erreur lors de la récupération de la Newsletter)
	 */
	static public Optional<String[]> getNewsletterEmails(int serviceId) {
		Optional<NewsletterBean> optionalNewsletter = newsletterDao.getNewsletterByService(serviceId);
		if (optionalNewsletter.isPresent()) {
			NewsletterBean newsletter = optionalNewsletter.get();
			String emailString = newsletter.getEmails();
			String emailArray[] = emailString.split(";");
			return Optional.ofNullable(emailArray);
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Renvoie un booléen attestant la présence d'un email d'un utilisateur pour une newsletter
	 *
	 * @param serviceId Id du service de la newsletter
	 * @param email Email à inscrire
	 * @return Booléen optionnel (true : email loggé, false : email non loggé, empty : erreur lors de la récupération de la newsletter)
	 */
	static public Optional<Boolean> isEmailLogged(int serviceId, String email) {
		Optional<NewsletterBean> optionalNewsletter = newsletterDao.getNewsletterByService(serviceId);
		if (optionalNewsletter.isPresent()) {
			NewsletterBean newsletter = optionalNewsletter.get();
			String emailList = newsletter.getEmails() == null ? "" : newsletter.getEmails();
			return Optional.ofNullable(emailList.contains(email));
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Ajoute une adresse email à la Newsletter
	 * 
	 * @param serviceId Id du service de la Newsletter
	 * @param email Email de l'utilisateur
	 * @return Booléen optionnel (true : email ajouté, false : email déjà présent, empty : erreur lors de la récupération de la Newsletter
	 */
	static public Optional<Boolean> addEmail(int serviceId, String email) {
		Optional<NewsletterBean> optionalNewsletter = newsletterDao.getNewsletterByService(serviceId);
		if (optionalNewsletter.isPresent()) {
			NewsletterBean newsletter = optionalNewsletter.get();
			String emailList = newsletter.getEmails() !=null? newsletter.getEmails():"";
			if (!emailList.contains(email)) {
				emailList += ";"+email;
				newsletter.setEmails(emailList);
				newsletterDao.updateNewsletter(newsletter);
				return Optional.ofNullable(true);
			} else {
				return Optional.ofNullable(false);
			}
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Crée une Newsletter et le service associé
	 * 
	 * @param userId Id de l'utilisateur créateur
	 * @param serviceName Titre donné au service
	 * @param serviceDescription Description donnée au service
	 * @return Booléen renvoyant vrai si aucune exception n'a été levée
	 */
	static public boolean createNewsletter(int userId, String serviceName, String serviceDescription) {
		try {
			ServiceBean service = new ServiceBean();
			service.setUserId(userId);
			service.setName(serviceName);
			Optional<String> optionalUrl = ServiceAction.generateUrl();
			if (!optionalUrl.isPresent()) {
				return false;
			} else {
				service.setUrl(optionalUrl.get());
				service.setDescription(serviceDescription);
				service.setType("newsletter");
				serviceDao.addService(service);
				NewsletterBean newsletter = new NewsletterBean();
				newsletter.setServiceId(service.getId());
				newsletterDao.addNewsletter(newsletter);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Supprime une Newsletter et le service associé
	 * 
	 * @param serviceId Id du service associé au Newsletter
	 * @return Boléen attestant que la Newsletter existait
	 */
	static public boolean deleteNewsletter(int serviceId) {
		
		Optional<NewsletterBean> optionalNewsletter = newsletterDao.getNewsletterByService(serviceId);
		Optional<ServiceBean> optionalService = serviceDao.getService(serviceId);
		if (optionalNewsletter.isPresent() && optionalService.isPresent()) {
			newsletterDao.deleteNewsletter(optionalNewsletter.get());
			serviceDao.deleteService(optionalService.get());
			return true;
		} else {
			return false;
		}
	}
}
