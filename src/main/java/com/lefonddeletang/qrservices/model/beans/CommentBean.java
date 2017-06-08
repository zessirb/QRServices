package com.lefonddeletang.qrservices.model.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * JavaBean représentant un commentaire d'un livre d'or
 */
@Entity
@Table(name="comment")
public class CommentBean implements Serializable {
	private static final long serialVersionUID = -6276893650357809430L;

	/** Identifiant du commentaire **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Identifiant du livre d'or associé **/
	@Column(name="guestbookId")
	private int guestbookId;
	/** Titre du commentaire (phrase mise en avant) **/
	@Column(name="title")
	private String title;
	/** Contenu du commentaire **/
	@Column(name="content")
	private String content;
	/** Signature de l'auteur **/
	@Column(name="author")
	private String author;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGuestbookId() {
		return this.guestbookId;
	}
	public void setGuestbookId(int guestbookId) {
		this.guestbookId = guestbookId;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
