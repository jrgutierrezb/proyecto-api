package com.proyecto.retail.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="messageboxes")
public class Messagebox extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Boolean addfiles = false;

	private String copies;
	
	private String filepath;

	private String subject;

	private String torecipients;
	
	private Boolean send = false;
	
	@Column(name="messagetemplateid", insertable=false, updatable=false, nullable=false)
	private Integer messagetemplateid;
	
	//bi-directional many-to-one association to Messagetempplate
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="messagetemplateid")
	private Messagetemplate messagetemplate;

	//bi-directional many-to-one association to Messageparameter
	@JsonIgnore
	@OneToMany(mappedBy="messagebox", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Messageparameter> messageparameters;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAddfiles() {
		return addfiles;
	}

	public void setAddfiles(Boolean addfiles) {
		this.addfiles = addfiles;
	}

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTorecipients() {
		return torecipients;
	}

	public void setTorecipients(String torecipients) {
		this.torecipients = torecipients;
	}

	public Boolean getSend() {
		return send;
	}

	public void setSend(Boolean send) {
		this.send = send;
	}

	public Integer getMessagetemplateid() {
		return messagetemplateid;
	}

	public void setMessagetemplateid(Integer messagetemplateid) {
		this.messagetemplateid = messagetemplateid;
	}

	public Messagetemplate getMessagetemplate() {
		return messagetemplate;
	}

	public void setMessagetemplate(Messagetemplate messagetemplate) {
		this.messagetemplate = messagetemplate;
	}

	public List<Messageparameter> getMessageparameters() {
		return messageparameters;
	}

	public void setMessageparameters(List<Messageparameter> messageparameters) {
		this.messageparameters = messageparameters;
	}
}
