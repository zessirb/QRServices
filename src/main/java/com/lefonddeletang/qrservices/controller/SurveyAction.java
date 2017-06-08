package com.lefonddeletang.qrservices.controller;

import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.ServiceBean;
import com.lefonddeletang.qrservices.model.beans.SurveyBean;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;
import com.lefonddeletang.qrservices.model.dao.SurveyDao;

public class SurveyAction {
	static private SurveyDao surveyDao = new SurveyDao();
	static private ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie un tableau contenant le nombre de votes positifs et de votes négatifs
	 *
	 * @param serviceId Id du LikeMeter
	 * @return Tableau optionnel de deux entiers (vote yes / vote no)
	 */
	static public Optional<Integer[]> getVotes(int serviceId) {
		Optional<SurveyBean> optionalSurveyBean = surveyDao.getSurveyByService(serviceId);
		if (optionalSurveyBean.isPresent()) {
			SurveyBean survey = optionalSurveyBean.get();
			int voteYes = survey.getVoteYes();
			int voteNo = survey.getVoteNo();
			return Optional.ofNullable(new Integer[]{voteYes, voteNo});
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Renvoie un booléen attestant la présence d'un vote d'un utilisateur pour un sondage
	 *
	 * @param serviceId Id du service du sondage
	 * @param userIp Ip de l'internaute
	 * @return Booléen optionnel (true : utilisateur loggé, false : utilisateur non loggé, empty : erreur lors de la récupération du sondage)
	 */
	static public Optional<Boolean> isUserLogged(int serviceId, String userIp) {
		Optional<SurveyBean> optionalSurveyBean = surveyDao.getSurveyByService(serviceId);
		if (optionalSurveyBean.isPresent()) {
			SurveyBean survey = optionalSurveyBean.get();
			String ipList = (survey.getLoggedIp() != null) ? survey.getLoggedIp() : "";
			return Optional.ofNullable(ipList.contains(userIp));
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Incrémente les votes positifs si l'ip est inconnue
	 * 
	 * @param serviceId Id du service du sondage
	 * @param userIp Ip de l'internaute
	 * @return Booléen optionnel (true : like ajouté, false : ip déjà loggée, empty : erreur lors de la récupération du sondage
	 */
	static public Optional<Boolean> addYes(int serviceId, String userIp) {
		Optional<SurveyBean> optionalSurvey = surveyDao.getSurveyByService(serviceId);
		if (optionalSurvey.isPresent()) {
			SurveyBean survey = optionalSurvey.get();
			String ipList = (survey.getLoggedIp() != null) ? survey.getLoggedIp() : "";
			if (!ipList.contains(userIp)) {
				ipList += ";"+userIp;
				survey.setLoggedIp(ipList);
				survey.setVoteYes(survey.getVoteYes()+1);
				surveyDao.updateSurvey(survey);
				return Optional.ofNullable(true);
			} else {
				return Optional.ofNullable(false);
			}
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Incrémente les votes négatifs si l'ip est inconnue
	 * 
	 * @param serviceId Id du service du sondage
	 * @param userIp Ip de l'internaute
	 * @return Booléen optionnel (true : like ajouté, false : ip déjà loggée, empty : erreur lors de la récupération du sondage
	 */
	static public Optional<Boolean> addNo(int serviceId, String userIp) {
		Optional<SurveyBean> optionalSurvey = surveyDao.getSurveyByService(serviceId);
		if (optionalSurvey.isPresent()) {
			SurveyBean survey = optionalSurvey.get();
			String ipList = (survey.getLoggedIp() != null) ? survey.getLoggedIp() : "";
			if (!ipList.contains(userIp)) {
				ipList += ";"+userIp;
				survey.setLoggedIp(ipList);
				survey.setVoteYes(survey.getVoteYes()-1);
				surveyDao.updateSurvey(survey);
				return Optional.ofNullable(true);
			} else {
				return Optional.ofNullable(false);
			}
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Crée un sondage et le service associé
	 * 
	 * @param userId Id de l'utilisateur créateur
	 * @param serviceName Titre donné au service
	 * @param serviceDescription Description donnée au service
	 * @return Booléen renvoyant vrai si aucune exception n'a été levée
	 */
	static public boolean createSurvey(int userId, String serviceName, String serviceDescription) {
		try {
			ServiceBean service = new ServiceBean();
			service.setUserId(userId);
			service.setName(serviceName);
			service.setType("survey");
			Optional<String> optionalUrl = ServiceAction.generateUrl();
			if (!optionalUrl.isPresent()) {
				return false;
			} else {
				service.setUrl(optionalUrl.get());
				service.setDescription(serviceDescription);
				serviceDao.addService(service);
				SurveyBean survey = new SurveyBean();
				survey.setServiceId(service.getId());
				surveyDao.addSurvey(survey);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Supprime un sondage et le service associé
	 * 
	 * @param serviceId Id du service associé au sondage
	 * @return Boléen attestant que le sondage existait
	 */
	static public boolean deleteSurvey(int serviceId) {
		Optional<SurveyBean> optionalSurvey = surveyDao.getSurveyByService(serviceId);
		Optional<ServiceBean> optionalService = serviceDao.getService(serviceId);
		if (optionalSurvey.isPresent() && optionalService.isPresent()) {
			surveyDao.deleteSurvey(optionalSurvey.get());
			serviceDao.deleteService(optionalService.get());
			return true;
		} else {
			return false;
		}
	}
}
