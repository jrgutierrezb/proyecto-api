package com.proyecto.retail.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
	@JsonIgnore
	@CreatedBy
	@Column(updatable = false)
	private String useridcreate;

	@JsonIgnore
	@LastModifiedBy
	private String useridupdate;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(updatable = false)
	private Date datecreate;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
	private Date dateupdate;
	
	@JsonIgnore
	private Boolean state = true;

	public String getUseridcreate() {
		return useridcreate;
	}

	public void setUseridcreate(String useridcreate) {
		this.useridcreate = useridcreate;
	}

	public String getUseridupdate() {
		return useridupdate;
	}

	public void setUseridupdate(String useridupdate) {
		this.useridupdate = useridupdate;
	}

	public Date getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	public Date getDateupdate() {
		return dateupdate;
	}

	public void setDateupdate(Date dateupdate) {
		this.dateupdate = dateupdate;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

}
