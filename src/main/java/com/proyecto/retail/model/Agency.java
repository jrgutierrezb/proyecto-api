package com.proyecto.retail.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the agencies database table.
 * 
 */
@Entity
@Table(name="agencies")
public class Agency extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String code;

	private String description;

	private String name;
	
	//bi-directional many-to-one association to Assetrequest
	@JsonIgnore
	@OneToMany(mappedBy="agency")
	private List<Assetrequest> assetrequests;

	//bi-directional many-to-one association to User
	@JsonIgnore
	@OneToMany(mappedBy="agency")
	private List<User> users;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public List<Assetrequest> getAssetrequests() {
		return assetrequests;
	}

	public void setAssetrequests(List<Assetrequest> assetrequests) {
		this.assetrequests = assetrequests;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
