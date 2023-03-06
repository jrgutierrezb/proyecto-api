package com.proyecto.retail.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the profiles database table.
 * 
 */
@Entity
@Table(name="profiles")
public class Profile extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	private String name;

	//bi-directional many-to-one association to User
	@JsonIgnore
	@OneToMany(mappedBy="profile")
	private List<User> users;

	//bi-directional many-to-one association to Profiletransactionpermission
	@JsonIgnore
	@OneToMany(mappedBy="profile")
	private List<Profiletransaction> profiprofiletransactionsletransactionpermissions;

	public Profile() {
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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setProfile(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setProfile(null);

		return user;
	}

	public List<Profiletransaction> getProfiprofiletransactionsletransactionpermissions() {
		return profiprofiletransactionsletransactionpermissions;
	}

	public void setProfiprofiletransactionsletransactionpermissions(
			List<Profiletransaction> profiprofiletransactionsletransactionpermissions) {
		this.profiprofiletransactionsletransactionpermissions = profiprofiletransactionsletransactionpermissions;
	}

}