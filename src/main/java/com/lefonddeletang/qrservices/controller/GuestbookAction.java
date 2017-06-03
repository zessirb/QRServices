package com.lefonddeletang.qrservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lefonddeletang.qrservices.model.beans.CommentBean;
import com.lefonddeletang.qrservices.model.beans.GuestbookBean;
import com.lefonddeletang.qrservices.model.dao.CommentDao;
import com.lefonddeletang.qrservices.model.dao.GuestbookDao;
import com.lefonddeletang.qrservices.model.dao.ServiceDao;

public class GuestbookAction {
	static GuestbookDao guestbookDao = new GuestbookDao();
	static CommentDao commentDao = new CommentDao();
	static ServiceDao serviceDao = new ServiceDao();
	
	/**
	 * Renvoie les textes des commentaires d'un guestbook
	 * 
	 * @param guestbookId Id du service du guestbook
	 * @return Liste (optionnelle) contenant un tableau de String par commentaire
	 */
	static Optional<List<String[]>> getGuestbookComments(int serviceId) {
		Optional<List<CommentBean>> optionalComments = commentDao.getCommentsByGuestbook(serviceId);
		if (optionalComments.isPresent()) {
			List<CommentBean> comments = optionalComments.get();
			List<String[]> commentsContent = new ArrayList<String[]>();
			for (CommentBean comment : comments) {
				String[] commentContent = new String[3];
				commentContent[0] = comment.getTitle();
				commentContent[1] = comment.getContent();
				commentContent[2] = comment.getAuthor();
				commentsContent.add(commentContent);
			}
			return Optional.ofNullable(commentsContent);
		} else {
			return Optional.empty();
		}
	}

	/*static Optional<Boolean> addComment(int guestbookId, String title, String content, String author) {
		Optional<GuestbookBean> guestbook = guestbookDao.getGuestbook(guestbookId);
		CommentBean comment = new CommentBean();
		comment.setGuestbookId(guestbookId);
		comment.setTitle(title);
		comment.setContent(content);
		comment.setAuthor(author);
		commentDao.addComment(comment);
	}*/
}
