package com.lefonddeletang.qrservices.controller;

import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.LikeMeterBean;
import com.lefonddeletang.qrservices.model.dao.LikeMeterDao;

public class LikeMeterAction {
	static LikeMeterDao dao = new LikeMeterDao();
	
	/**
	 * Renvoie le compte actuel du LikeMeter
	 * 
	 * @param serviceId Id du service du LikeMeter
	 * @return Entier optionnel (empty si erreur lors de la récupération du compteur)
	 */
	static Optional<Integer> getLikeCount(int serviceId) {
		Optional<LikeMeterBean> optionalLikeMeter = dao.getLikeMeter(serviceId);
		if (optionalLikeMeter.isPresent()) {
			LikeMeterBean likeMeter = optionalLikeMeter.get();
			return Optional.ofNullable(likeMeter.getCount());
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
		Optional<LikeMeterBean> optionalLikeMeter = dao.getLikeMeter(serviceId);
		if (optionalLikeMeter.isPresent()) {
			LikeMeterBean likeMeter = optionalLikeMeter.get();
			String ipList = likeMeter.getLoggedIp();
			if (!ipList.contains(userIp)) {
				ipList += ";"+userIp;
				likeMeter.setLoggedIp(ipList);
				likeMeter.setCount(likeMeter.getCount()+1);
				dao.updateLikeMeter(likeMeter);
				return Optional.ofNullable(true);
			} else {
				return Optional.ofNullable(false);
			}
		} else {
			return Optional.empty();
		}
	}
}
