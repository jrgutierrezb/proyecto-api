package com.proyecto.retail.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="catalog")
public class Catalog extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String code;
	
	private String description;
	
	private String name;
	
	//bi-directional many-to-one association to Assetrequest
	@JsonIgnore
	@OneToMany(mappedBy="catalog")
	private List<Assetrequest> assetrequests;
	
	//bi-directional many-to-one association to Assetrequest
	@JsonIgnore
	@OneToMany(mappedBy="prioritytype")
	private List<Assetrequest> assetrequestpriorities;
	
	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="catalog")
	private List<Product> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
