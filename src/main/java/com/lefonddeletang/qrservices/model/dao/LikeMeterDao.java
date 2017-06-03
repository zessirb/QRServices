package com.lefonddeletang.qrservices.model.dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.util.HibernateUtil;
import com.lefonddeletang.qrservices.model.beans.LikeMeterBean;

public class LikeMeterDao {
	
	/**
	 * Requête la base et renvoie un LikeMeter d'après l'id du service
	 * 
	 * @param serviceId Id du service
	 * @return LikeMeterBean optionel
	 */
	public Optional<LikeMeterBean> getLikeMeterByService(int serviceId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(LikeMeterBean.class);
		criteria.add(Restrictions.eq("serviceId", serviceId));
		criteria.setFirstResult(0).setMaxResults(1);
		try {
			LikeMeterBean likeMeter = (LikeMeterBean) criteria.list().get(0);
			return Optional.ofNullable(likeMeter);
		} catch (IndexOutOfBoundsException e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Met à jour un LikeMeter en base
	 * 
	 * @param likeMeter LikeMeter mis à jour
	 */
	public void updateLikeMeter(LikeMeterBean likeMeter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(likeMeter);
		session.getTransaction().commit();
	}
	
	/**
	 * Crée un LikeMeter en base
	 * 
	 * @param likeMeter LikeMeter à créer
	 */
	public void addLikeMeter(LikeMeterBean likeMeter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(likeMeter);
		session.getTransaction().commit();
	}
	
	/**
	 * Supprime un LikeMeter en base
	 * 
	 * @param likeMeter LikeMeter à supprimer
	 */
	public void deleteLikeMeter(LikeMeterBean likeMeter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.delete(likeMeter);
		session.getTransaction().commit();
	}
}
