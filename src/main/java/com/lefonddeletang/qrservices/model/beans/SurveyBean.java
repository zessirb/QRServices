package com.lefonddeletang.qrservices.model.beans;

import javax.persistence.*;

import java.io.Serializable;

/**
 * JavaBean représentant un sondage
 */
@Entity
@Table(name="survey")
public class SurveyBean implements Serializable {
	private static final long serialVersionUID = 7428431917548750820L;
	
	/** Identifiant du sondage **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** Identifiant du service générique associé **/
	@Column(name="serviceId")
	private int serviceId;
	/** Quantité de votes positifs **/
	@Column(name="voteYes")
	private int voteYes;
	/** Quantité de votes négatifs **/
	@Column(name="voteNo")
	private int voteNo;
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
	public int getVoteYes() {
		return this.voteYes;
	}
	public void setVoteYes(int voteYes) {
		this.voteYes = voteYes;
	}
	public int getVoteNo() {
		return this.voteNo;
	}
	public void setVoteNo(int voteNo) {
		this.voteNo = voteNo;
	}
	public String getLoggedIp() {
		return this.loggedIp;
	}
	public void setLoggedIp(String loggedIp) {
		this.loggedIp = loggedIp;
	}
}
