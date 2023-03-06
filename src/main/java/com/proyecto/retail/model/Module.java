package com.proyecto.retail.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the modules database table.
 * 
 */
@Entity
@Table(name="modules")
public class Module extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	private String icon;

	private String name;
	
	private String url;
	
	@Column(name="idmodule", insertable=false, updatable=false, nullable=false)
	private Integer idmodule;

	//bi-directional many-to-one association to Module
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idmodule")
	private Module module;

	//bi-directional many-to-one association to Module
	@JsonIgnore
	@OneToMany(mappedBy="module")
	private List<Module> modules;

	//bi-directional many-to-one association to Transaction
	@JsonIgnore
	@OneToMany(mappedBy="module")
	private List<Transaction> transactions;

	public Module() {
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

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
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

	public List<Module> getModules() {
		return this.modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Module addModule(Module module) {
		getModules().add(module);
		module.setModule(this);

		return module;
	}

	public Module removeModule(Module module) {
		getModules().remove(module);
		module.setModule(null);

		return module;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setModule(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setModule(null);

		return transaction;
	}

	public Integer getIdmodule() {
		return idmodule;
	}

	public void setIdmodule(Integer idmodule) {
		this.idmodule = idmodule;
	}

}