package com.lefonddeletang.qrservices.model.dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.util.HibernateUtil;
import com.lefonddeletang.qrservices.model.beans.SurveyBean;

public class SurveyDao {
	/**
	 * Requête la base et renvoie un sondage d'après l'id du service
	 *
	 * @param serviceId Id du service
	 * @return SurveyBean optionel
	 */
	public Optional<SurveyBean> getSurveyByService(int serviceId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(SurveyBean.class);
		criteria.add(Restrictions.eq("serviceId", serviceId));
		criteria.setFirstResult(0).setMaxResults(1);
		try {
			SurveyBean survey = (SurveyBean) criteria.list().get(0);
			return Optional.ofNullable(survey);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Met à jour un sondage en base
	 * 
	 * @param survey Survey mis à jour
	 */
	public void updateSurvey(SurveyBean survey) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(survey);
		session.getTransaction().commit();
	}
	
	/**
	 * Crée un sondage en base
	 * 
	 * @param survey Survey à créer
	 */
	public void addSurvey(SurveyBean survey) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(survey);
		session.getTransaction().commit();
	}
	
	/**
	 * Supprime un sondage en base
	 * 
	 * @param survey Survey à supprimer
	 */
	public void deleteSurvey(SurveyBean survey) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.delete(survey);
		session.getTransaction().commit();
	}
}
