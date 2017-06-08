package com.lefonddeletang.qrservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.CommentBean;
import com.lefonddeletang.qrservices.model.beans.GuestbookBean;
import com.lefonddeletang.qrservices.model.beans.ServiceBean;
import com.lefonddeletang.qrservices.model.dao.CommentDao;
import com.lefonddeletang.qrservices.model.dao.GuestbookDao;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;

public class GuestbookAction {
	static GuestbookDao guestbookDao = new GuestbookDao();
	static CommentDao commentDao = new CommentDao();
	static ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie les textes des commentaires d'un guestbook
	 * 
	 * @param serviceId Id du service du guestbook
	 * @return Liste (optionnelle) contenant un tableau de String par commentaire (composé du titre, du contenu et de la signature)
	 */
	static public Optional<List<String[]>> getGuestbookCommentsbyServiceId(int serviceId) {
		int guestbookId = guestbookDao.getGuestbookByService(serviceId).get().getId();
		Optional<List<CommentBean>> optionalComments = commentDao.getCommentsByGuestbook(guestbookId);
		if (optionalComments.isPresent()) {
			List<CommentBean> comments = optionalComments.get();
			List<String[]> commentsContent = new ArrayList<String[]>();
			for (CommentBean comment : comments) {
				String[] commentContent = new String[3];
				commentContent[0] = comment.getTitle();
				commentContent[1] = comment.getContent();
				commentContent[2] = comment.getAuthor();
				commentsContent.add(commentContent);
			}
			return Optional.ofNullable(commentsContent);
		} else {
			return Optional.empty();
		}
	}


	/**
	 * Crée un commentaire pour le livre d'or ciblé
	 * 
	 * @param serviceId Identifiant du service
	 * @param title Titre du commentaire
	 * @param content Contenu du commentaire
	 * @param author Auteur ou signature du commentaire
	 * @return Booléen vérifiant que le Guestbook a été trouvé et qu'aucune exception n'a été soulevée
	 */
	static public boolean createComment(int serviceId, String title, String content, String author) {
		Optional<GuestbookBean> guestbook = guestbookDao.getGuestbookByService(serviceId);
		if (guestbook.isPresent()) {
			try {
				CommentBean comment = new CommentBean();
				comment.setGuestbookId(guestbook.get().getId());
				comment.setTitle(title);
				comment.setContent(content);
				comment.setAuthor(author);
				commentDao.addComment(comment);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Crée un Guestbook et le service associé
	 * 
	 * @param userId Id de l'utilisateur créateur
	 * @param serviceName Titre donné au service
	 * @param serviceDescription Description donnée au service
	 * @return Booléen renvoyant vrai si aucune exception n'a été levée
	 */
	static public boolean createGuestbook(int userId, String serviceName, String serviceDescription) {
		try {
			ServiceBean service = new ServiceBean();
			service.setUserId(userId);
			service.setName(serviceName);
			service.setType("guestbook");
			Optional<String> optionalUrl = ServiceAction.generateUrl();
			if (!optionalUrl.isPresent()) {
				return false;
			} else {
				service.setUrl(optionalUrl.get());
				service.setDescription(serviceDescription);
				serviceDao.addService(service);
				GuestbookBean guestbook = new GuestbookBean();
				guestbook.setServiceId(service.getId());
				guestbookDao.addGuestbook(guestbook);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Supprime un livre d'or et le service associé
	 * 
	 * @param serviceId Id du service associé au livre d'or
	 * @return Boléen attestant que le sondage existait
	 */
	static public boolean deleteGuestbook(int serviceId) {
		Optional<GuestbookBean> optionalGuestbook = guestbookDao.getGuestbookByService(serviceId);
		Optional<ServiceBean> optionalService = serviceDao.getService(serviceId);
		if (optionalGuestbook.isPresent() && optionalService.isPresent()) {
			guestbookDao.deleteGuestbook(optionalGuestbook.get());
			serviceDao.deleteService(optionalService.get());
			return true;
		} else {
			return false;
		}
	}
}
