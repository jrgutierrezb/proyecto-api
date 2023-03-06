package com.proyecto.retail.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the initialconfigs database table.
 * 
 */
@Entity
@Table(name="initialconfigs")
public class Initialconfig extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Boolean booleanvalue;
	
	private String description;

	private Integer integervalue;

	private String name;
	
	private String stringvalue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getBooleanvalue() {
		return booleanvalue;
	}

	public void setBooleanvalue(Boolean booleanvalue) {
		this.booleanvalue = booleanvalue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIntegervalue() {
		return integervalue;
	}

	public void setIntegervalue(Integer integervalue) {
		this.integervalue = integervalue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStringvalue() {
		return stringvalue;
	}

	public void setStringvalue(String stringvalue) {
		this.stringvalue = stringvalue;
	}

}
