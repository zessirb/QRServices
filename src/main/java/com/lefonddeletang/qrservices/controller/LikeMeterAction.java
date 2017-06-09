package com.lefonddeletang.qrservices.controller;

import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.LikeMeterBean;
import com.lefonddeletang.qrservices.model.beans.ServiceBean;
import com.lefonddeletang.qrservices.model.dao.LikeMeterDao;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;

/**
 * Controller de méthodes statiques gérant les actions réalisées par rapport à un compteur de Like
 */
public class LikeMeterAction {
	static private LikeMeterDao likeMeterDao = new LikeMeterDao();
	static private ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie le compte actuel du LikeMeter
	 *
	 * @param serviceId Id du LikeMeter
	 * @return Entier optionnel (empty si erreur lors de la récupération du compteur)
	 */
	static public Optional<Integer> getLikeCount(int serviceId) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeterByService(serviceId);
		if (optionalLikeMeter.isPresent()) {
			LikeMeterBean likeMeter = optionalLikeMeter.get();
			return Optional.ofNullable(likeMeter.getCount());
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Renvoie un booléen attestant la présence d'un vote d'un utilisateur pour un LikeMeter
	 *
	 * @param serviceId Id du LikeMeter
	 * @param userIp Ip de l'internaute
	 * @return Booléen optionnel (true : utilisateur loggé, false : utilisateur non loggé, empty : erreur lors de la récupération du compteur)
	 */
	static public Optional<Boolean> isUserLogged(int serviceId, String userIp) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeterByService(serviceId);
		if (optionalLikeMeter.isPresent()) {
			LikeMeterBean likeMeter = optionalLikeMeter.get();
			String ipList = likeMeter.getLoggedIp()==null?"":likeMeter.getLoggedIp();
			return Optional.ofNullable(ipList.contains(userIp));
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Incrémente le compteur d'un LikeMeter si l'ip est inconnue
	 * 
	 * @param serviceId Id du LikeMeter
	 * @param userIp Ip de l'internaute
	 * @return Booléen optionnel (true : like ajouté, false : ip déjà loggée, empty : erreur lors de la récupération du compteur
	 */
	static public Optional<Boolean> addLike(int serviceId, String userIp) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeterByService(serviceId);
		if (optionalLikeMeter.isPresent()) {
			LikeMeterBean likeMeter = optionalLikeMeter.get();
			String ipList = (likeMeter.getLoggedIp() != null) ? likeMeter.getLoggedIp() : "";
			if (!ipList.contains(userIp)) {
				ipList += ";"+userIp;
				likeMeter.setLoggedIp(ipList);
				likeMeter.setCount(likeMeter.getCount()+1);
				likeMeterDao.updateLikeMeter(likeMeter);
				return Optional.ofNullable(true);
			} else {
				return Optional.ofNullable(false);
			}
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Crée un LikeMeter et le service associé
	 * 
	 * @param userId Id de l'utilisateur créateur
	 * @param serviceName Titre donné au service
	 * @param serviceDescription Description donnée au service
	 * @return Booléen renvoyant vrai si aucune exception n'a été levée
	 */
	static public boolean createLikeMeter(int userId, String serviceName, String serviceDescription) {
		try {
			ServiceBean service = new ServiceBean();
			service.setUserId(userId);
			service.setName(serviceName);
			service.setType("likemeter");
			Optional<String> optionalUrl = ServiceAction.generateUrl();
			if (!optionalUrl.isPresent()) {
				return false;
			} else {
				service.setUrl(optionalUrl.get());
				service.setDescription(serviceDescription);
				serviceDao.addService(service);
				LikeMeterBean likeMeter = new LikeMeterBean();
				likeMeter.setServiceId(service.getId());
				likeMeterDao.addLikeMeter(likeMeter);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Supprime un LikeMeter et le service associé
	 * 
	 * @param serviceId Id du service associé au LikeMeter
	 * @return Boléen attestant que le LikeMeter existait
	 */
	static public boolean deleteLikeMeter(int serviceId) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeterByService(serviceId);
		Optional<ServiceBean> optionalService = serviceDao.getService(serviceId);
		if (optionalLikeMeter.isPresent() && optionalService.isPresent()) {
			likeMeterDao.deleteLikeMeter(optionalLikeMeter.get());
			serviceDao.deleteService(optionalService.get());
			return true;
		} else {
			return false;
		}
	}
}
