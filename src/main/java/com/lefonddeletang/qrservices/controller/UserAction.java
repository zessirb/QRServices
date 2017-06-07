package com.lefonddeletang.qrservices.controller;

import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.UserBean;
import com.lefonddeletang.qrservices.model.dao.UserDao;

public class UserAction {
	static private UserDao userDao = new UserDao();
	
	/**
	 * Renvoie l'identifiant d'un User si les identifiants fournis sont corrects
	 * 
	 * @param login Identifiant de l'utilisateur
	 * @param password Mot de passe de l'utilisateur
	 * @return Identifiant (optionnel) du user, ou empty si non trouvé
	 */
	static public Optional<Integer> getUserIdFromCredentials(String login, String password) {
		Optional<UserBean> optionalUser = userDao.getUserByCredentials(login, password);
		if (optionalUser.isPresent()) {
			return Optional.ofNullable(optionalUser.get().getId());
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Renvoie l'email d'un User d'après son id
	 * 
	 * @param id Identifiant de l'utilisateur
	 * @return String (optionnel) de l'email de l'utilisateur
	 */
	static public Optional<String> getUserEmail(int id) {
		Optional<UserBean> optionalUser = userDao.getUser(id);
		if (optionalUser.isPresent()) {
			return Optional.ofNullable(optionalUser.get().getEmail());
		} else {
			return Optional.empty();
		}
	}
}
