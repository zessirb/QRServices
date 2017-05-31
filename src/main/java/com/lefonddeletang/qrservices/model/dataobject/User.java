package com.lefonddeletang.qrservices.model.dataobject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;

/**
 * DataObject représentant un utilisateur
 */
@Entity
@Table(name="user")
public class User {
	/** Identifiant de l'utilisateur **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Pseudo de l'utilisateur **/
	@Column(name="name")
	private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
	private List<Service> services;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Service> getServices() {
		return this.services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
}
