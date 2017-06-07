package com.lefonddeletang.qrservices.model.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * JavaBean représentant un livre d'or
 */
@Entity
@Table(name="guestbook")
public class GuestbookBean implements Serializable {
	private static final long serialVersionUID = -6276893650357809430L;

	/** Identifiant du guestbook **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Identifiant du service générique associé **/
	@Column(name="serviceId")
	private int serviceId;
	
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
}
