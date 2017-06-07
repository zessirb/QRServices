package com.lefonddeletang.qrservices.model.dao;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.util.HibernateUtil;
import com.lefonddeletang.qrservices.model.beans.UserBean;

public class UserDao {

	/**
	 * Renvoie un User d'après son id
	 * 
	 * @param id Identifiant du User
	 * @return User optionnel
	 */
	public Optional<UserBean> getUser(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			UserBean user = session.get(UserBean.class, id);
			return Optional.ofNullable(user);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	/**
	 * Requête la base et renvoie un User si les identifiants sont bons
	 * 
	 * @param login Login du User
	 * @param password Mot de passe du User
	 * @return User optionnel (vide si login incorrect)
	 */
	public Optional<UserBean> getUserByCredentials(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(UserBean.class);
		criteria.add(Restrictions.eq("name", login));
		criteria.add(Restrictions.eq("password", password));
		criteria.setFirstResult(0).setMaxResults(1);
		try {
			if (!criteria.list().isEmpty() && criteria.list().size() > 0) {
				UserBean user = (UserBean) criteria.list().get(0);
				return Optional.ofNullable(user);
			} else {
				return Optional.empty();
			}
		} catch (IndexOutOfBoundsException e) {
			return Optional.empty();
		}
	}

	/**
	 * Met à jour un User en base
	 * 
	 * @param user User mis à jour
	 */
	public void updateUser(UserBean user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
	}

	/**
	 * Crée un User en base
	 * 
	 * @param user User à créer
	 */
	public void addUser(UserBean user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(user);
		session.getTransaction().commit();
	}

	/**
	 * Supprime un User en base
	 * 
	 * @param user User à supprimer
	 */
	public void deleteUser(UserBean user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.delete(user);
		session.getTransaction().commit();
	}
}
