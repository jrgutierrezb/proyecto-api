package com.proyecto.retail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="profiletransactions")
public class Profiletransaction extends BaseEntity {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="profileid", insertable=false, updatable=false, nullable=false)
	private Integer profileid;

	//bi-directional many-to-one association to Profile
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="profileid")
	private Profile profile;
	
	@Column(name="transactionid", insertable=false, updatable=false, nullable=false)
	private Integer transactionid;

	//bi-directional many-to-one association to Transaction
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="transactionid")
	private Transaction transaction;

	public Profiletransaction() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProfileid() {
		return profileid;
	}

	public void setProfileid(Integer profileid) {
		this.profileid = profileid;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Integer getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
