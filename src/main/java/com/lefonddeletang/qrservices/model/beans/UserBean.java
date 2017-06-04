package com.lefonddeletang.qrservices.model.beans;

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

import java.io.Serializable;
import java.util.List;

/**
 * JavaBean représentant un utilisateur identifié
 */
@Entity
@Table(name="user")
public class UserBean implements Serializable {
	private static final long serialVersionUID = -6799034576915238724L;
	
	/** Identifiant de l'utilisateur **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Pseudo de l'utilisateur **/
	@Column(name="name")
	private String name;
	/** Email de l'utilisateur **/
	@Column(name="email")
	private String email;
	/** Mot de passe hashé de l'utilisateur **/
	@Column(name="password")
	private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
	private List<ServiceBean> services;
	
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
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ServiceBean> getServices() {
		return this.services;
	}
	public void setServices(List<ServiceBean> services) {
		this.services = services;
	}
}
