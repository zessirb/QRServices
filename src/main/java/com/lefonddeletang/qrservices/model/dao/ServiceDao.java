package com.lefonddeletang.qrservices.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.util.HibernateUtil;
import com.lefonddeletang.qrservices.model.beans.ServiceBean;

public class ServiceDao {
	
	/**
	 * Requête la base et renvoie un Service d'après son id
	 * 
	 * @param id Id du service
	 * @return ServiceBean optionel
	 */
	public Optional<ServiceBean> getService(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			ServiceBean service = session.get(ServiceBean.class, id);
			return Optional.ofNullable(service);
		} catch (IndexOutOfBoundsException e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Requête la base et renvoie les services appartenant à un utilisateur
	 * 
	 * @param userId Id de l'utilisateur
	 * @return Liste (optionnelle) des services associés
	 */
	public Optional<List<ServiceBean>> getServicesByUser(int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(ServiceBean.class);
		criteria.add(Restrictions.eq("userId", userId));
		try {
			List<ServiceBean> serviceList = (List<ServiceBean>) criteria.list();
			return Optional.ofNullable(serviceList);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Requête la base et renvoie le nombre de services utilisant une URL
	 * 
	 * @param url Code de l'URL à vérifier
	 * @return Nombre (optionnel) de services utilisant une URL (supposé être 0 ou 1)
	 */
	public Optional<List<ServiceBean>> getServicesByUrl(String url) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(ServiceBean.class);
		criteria.add(Restrictions.eq("url", url));
		try {
			List<ServiceBean> serviceList = (List<ServiceBean>) criteria.list();
			return Optional.ofNullable(serviceList);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Crée un Service en base
	 * 
	 * @param service Service à créer
	 */
	public void addService(ServiceBean service) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(service);
		session.getTransaction().commit();
	}
	
	/**
	 * Supprime un Service en base
	 * 
	 * @param service Service à supprimer
	 */
	public void deleteService(ServiceBean service) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.delete(service);
		session.getTransaction().commit();
	}
	
}
