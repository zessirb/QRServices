package com.lefonddeletang.qrservices.controller;

import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.LikeMeterBean;
import com.lefonddeletang.qrservices.model.beans.ServiceBean;
import com.lefonddeletang.qrservices.model.dao.LikeMeterDao;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;

public class LikeMeterAction {
	static LikeMeterDao likeMeterDao = new LikeMeterDao();
	static ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie le compte actuel du LikeMeter
	 * 
	 * @param serviceId Id du service du LikeMeter
	 * @return Entier optionnel (empty si erreur lors de la récupération du compteur)
	 */
	static Optional<Integer> getLikeCount(int serviceId) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeter(serviceId);
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
	 * @param serviceId Id du service du LikeMeter
	 * @param userIp Ip de l'internaute
	 * @return Booléen optionnel (true : utilisateur loggé, false : utilisateur non loggé, empty : erreur lors de la récupération du compteur)
	 */
	static Optional<Boolean> isUserLogged(int serviceId, String userIp) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeter(serviceId);
		if (optionalLikeMeter.isPresent()) {
			LikeMeterBean likeMeter = optionalLikeMeter.get();
			String ipList = likeMeter.getLoggedIp();
			return Optional.ofNullable(ipList.contains(userIp));
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Incrémente le compteur d'un LikeMeter si l'ip est inconnue
	 * 
	 * @param serviceId Id du service du LikeMeter
	 * @param userIp Ip de l'internaute
	 * @return Booléen optionnel (true : like ajouté, false : ip déjà loggée, empty : erreur lors de la récupération du compteur
	 */
	static Optional<Boolean> addLike(int serviceId, String userIp) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeter(serviceId);
		if (optionalLikeMeter.isPresent()) {
			LikeMeterBean likeMeter = optionalLikeMeter.get();
			String ipList = likeMeter.getLoggedIp();
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
	 * @param serviceUrl Url fixée au service (A MODIFIER POUR RECUPERER D'UNE FONCTION)
	 * @param serviceDescription Description donnée au service
	 * @return
	 */
	static boolean createLikeMeter(int userId, String serviceName, String serviceUrl, String serviceDescription) {
		try {
			ServiceBean service = new ServiceBean();
			service.setUserId(userId);
			service.setName(serviceName);
			service.setUrl(serviceUrl);
			service.setDescription(serviceDescription);
			serviceDao.addService(service);
			LikeMeterBean likeMeter = new LikeMeterBean();
			likeMeter.setServiceId(service.getId());
			likeMeterDao.addLikeMeter(likeMeter);
			return true;
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
	static boolean deleteLikeMeter(int serviceId) {
		Optional<LikeMeterBean> optionalLikeMeter = likeMeterDao.getLikeMeter(serviceId);
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
