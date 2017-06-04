package com.lefonddeletang.qrservices.model.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * JavaBean représentant un service générique
 */
@Entity
@Table(name="service")
public class ServiceBean implements Serializable {
	private static final long serialVersionUID = -6818546609936948142L;
	
	/** Identifiant du service **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Identifiant de l'utilisateur associé **/
	@Column(name="userId")
	private int userId;
	/** Nom donné par l'utilisateur au service **/
	@Column(name="name")
	private String name;
	/** Code à 5 lettres/chiffres utilisés dans l'URL d'accès **/
	@Column(name="url")
	private String url;
	/** Description donnée par l'utilisateur au service **/
	@Column(name="description")
	private String description;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
