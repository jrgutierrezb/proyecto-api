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
@Table(name="messageparameters")
public class Messageparameter extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String value;

	@Column(name="messageboxid", insertable=false, updatable=false, nullable=false)
	private Integer messageboxid;
	
	//bi-directional many-to-one association to Messagebox
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="messageboxid")
	private Messagebox messagebox;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getMessageboxid() {
		return messageboxid;
	}

	public void setMessageboxid(Integer messageboxid) {
		this.messageboxid = messageboxid;
	}

	public Messagebox getMessagebox() {
		return messagebox;
	}

	public void setMessagebox(Messagebox messagebox) {
		this.messagebox = messagebox;
	}
}
