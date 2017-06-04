package com.lefonddeletang.qrservices.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lefonddeletang.qrservices.model.beans.CommentBean;
import com.lefonddeletang.qrservices.model.util.HibernateUtil;

public class CommentDao {
	
	/**
	 * Requête la base et renvoie les commentaires d'un livre d'or
	 * 
	 * @param guestbookId Id du livre d'or
	 * @return Liste (optionelle) des commentaires associés
	 */
	public Optional<List<CommentBean>> getCommentsByGuestbook(int guestbookId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(CommentBean.class);
		criteria.add(Restrictions.eq("guestbookId", guestbookId));
		try {
			List<CommentBean> comments = (List<CommentBean>) criteria.list();
			return Optional.ofNullable(comments);
		} catch (IndexOutOfBoundsException e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Ajoute un commentaire en base
	 * 
	 * @param comment Commentaire à créer
	 */
	public void addComment(CommentBean comment) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.save(comment);
		session.getTransaction().commit();
	}
	
	/**
	 * Supprime un commentaire en base
	 * 
	 * @param comment Commentaire à supprimer
	 */
	public void deleteComment(CommentBean comment) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.delete(comment);
		session.getTransaction().commit();
	}
	
}
