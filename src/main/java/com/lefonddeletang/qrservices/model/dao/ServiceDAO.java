package com.lefonddeletang.qrservices.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.util.HibernateUtil;
import com.lefonddeletang.qrservices.model.beans.ServiceBean;

public class ServiceDAO {
	
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
		List<ServiceBean> serviceList = (List<ServiceBean>) criteria.list();
		return Optional.ofNullable(serviceList);
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
	
}
