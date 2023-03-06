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
@Table(name="messagetemplates")
public class Messagetemplate extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String description;

	private String message;

	private String name;
	
	private int parameternumber;
	
	//bi-directional many-to-one association to Messageboxe
	@JsonIgnore
	@OneToMany(mappedBy="messagetemplate")
	private List<Messagebox> messageboxes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParameternumber() {
		return parameternumber;
	}

	public void setParameternumber(int parameternumber) {
		this.parameternumber = parameternumber;
	}

	public List<Messagebox> getMessageboxes() {
		return messageboxes;
	}

	public void setMessageboxes(List<Messagebox> messageboxes) {
		this.messageboxes = messageboxes;
	}
}
