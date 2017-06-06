package com.lefonddeletang.qrservices.model.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * JavaBean représentant une newsletter
 */
@Entity
@Table(name="newsletter")
public class NewsletterBean implements Serializable {
	private static final long serialVersionUID = -7814992500158878873L;
	
	/** Identifiant de la newsletter **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Identifiant du service générique associé **/
	@Column(name="serviceId")
	@OneToOne(cascade=CascadeType.ALL)
	private int serviceId;
	/** Concaténation des emails inscrits **/
	@Column(name="emails")
	private String emails;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getServiceId() {
		return this.serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getEmails() {
		return this.emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
}
