package com.lefonddeletang.qrservices.model.dataobject;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * DataObject représentant un service générique
 */
@Entity
@Table(name="service")
public class Service {
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
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
