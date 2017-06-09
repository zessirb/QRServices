package com.lefonddeletang.qrservices.model.dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.util.HibernateUtil;
import com.lefonddeletang.qrservices.model.beans.NewsletterBean;

/**
 * Classe requêtant la base de données pour interagir avec une Newsletter
 */
public class NewsletterDao {
	
	/**
	 * Requête la base et renvoie une Newsletter d'après l'id du service
	 * 
	 * @param serviceId Id du service
	 * @return NewsletterBean optionel
	 */
	public Optional<NewsletterBean> getNewsletterByService(int serviceId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(NewsletterBean.class);
		criteria.add(Restrictions.eq("serviceId", serviceId));
		criteria.setFirstResult(0).setMaxResults(1);
		try {
			NewsletterBean newsletter = (NewsletterBean) criteria.list().get(0);
			return Optional.ofNullable(newsletter);
		} catch (IndexOutOfBoundsException e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Met à jour une Newsletter en base
	 * 
	 * @param newsletter Newsletter mis à jour
	 */
	public void updateNewsletter(NewsletterBean newsletter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(newsletter);
		session.getTransaction().commit();
	}
	
	/**
	 * Crée une Newsletter en base
	 * 
	 * @param Newsletter Newsletter à créer
	 */
	public void addNewsletter(NewsletterBean newsletter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(newsletter);
		session.getTransaction().commit();
	}
	
	/**
	 * Supprime une Newsletter en base
	 * 
	 * @param newsletter Newsletter à supprimer
	 */
	public void deleteNewsletter(NewsletterBean newsletter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.delete(newsletter);
		session.getTransaction().commit();
	}
}
