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
 * JavaBean représentant un compteur de Like
 */
@Entity
@Table(name="likeMeter")
public class LikeMeterBean implements Serializable {
	private static final long serialVersionUID = 1761316118765547209L;
	
	/** Identifiant du like meter **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Identifiant du service générique associé **/
	@Column(name="serviceId")
	@OneToOne(cascade=CascadeType.ALL)
	private int serviceId;
	/** Quantité de Like **/
	@Column(name="count")
	private int count;
	/** Concaténation des IP ayant voté **/
	@Column(name="loggedIp")
	private String loggedIp;
	
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
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLoggedIp() {
		return this.loggedIp;
	}
	public void setLoggedIp(String loggedIp) {
		this.loggedIp = loggedIp;
	}
}
