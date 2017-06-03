package com.lefonddeletang.qrservices.model.dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.beans.GuestbookBean;
import com.lefonddeletang.qrservices.model.util.HibernateUtil;

public class GuestbookDao {
	
	/**
	 * Requête la base et renvoie un Guestbook d'après son id
	 * 
	 * @param serviceId Id du service
	 * @return GuestbookBean optionel
	 */
	public Optional<GuestbookBean> getGuestbook(int guestbookId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			GuestbookBean guestbook = session.get(GuestbookBean.class, guestbookId);
			return Optional.ofNullable(guestbook);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Requête la base et renvoie un Guestbook d'après l'id du service
	 * 
	 * @param serviceId Id du service
	 * @return GuestbookBean optionel
	 */
	public Optional<GuestbookBean> getGuestbookByService(int serviceId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(GuestbookBean.class);
		criteria.add(Restrictions.eq("serviceId", serviceId));
		criteria.setFirstResult(0).setMaxResults(1);
		try {
			GuestbookBean guestbook = (GuestbookBean) criteria.list().get(0);
			return Optional.ofNullable(guestbook);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Crée un Guestbook en base
	 * 
	 * @param guestbook Guestbook à créer
	 */
	public void addGuestbook(GuestbookBean guestbook) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(guestbook);
		session.getTransaction().commit();
	}
	
	/**
	 * Supprime un Guestbook en base
	 * 
	 * @param guestbook Guestbook à supprimer
	 */
	public void deleteGuestbook(GuestbookBean guestbook) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.delete(guestbook);
		session.getTransaction().commit();
	}
	
}
