package com.proyecto.retail.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
public class Transaction extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	private String name;

	private String url;
	
	@Column(name="idmodule", insertable=false, updatable=false, nullable=false)
	private Integer idmodule;

	//bi-directional many-to-one association to Module
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idmodule")
	private Module module;

	//bi-directional many-to-one association to Profiletransactionpermission
	@JsonIgnore
	@OneToMany(mappedBy="transaction")
	private List<Profiletransaction> profiletransactions;

	public Transaction() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Integer getIdmodule() {
		return idmodule;
	}

	public void setIdmodule(Integer idmodule) {
		this.idmodule = idmodule;
	}
	
	public List<Profiletransaction> getProfiletransactions() {
		return this.profiletransactions;
	}

	public void setProfiletransactions(List<Profiletransaction> profiletransactions) {
		this.profiletransactions = profiletransactions;
	}

}