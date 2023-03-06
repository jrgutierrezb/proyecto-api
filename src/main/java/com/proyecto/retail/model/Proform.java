package com.proyecto.retail.model;

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

@Entity
@Table(name="proforms")
public class Proform extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Boolean approved = false;

	private String fileproforma;
	
	private String supplier;

	//bi-directional many-to-one association to Productproform
	@OneToMany(mappedBy="proform")
	private List<Productproform> productproforms;
	
	@Column(name="assetrequestid", insertable=false, updatable=false, nullable=false)
	private Integer assetrequestid;

	//bi-directional many-to-one association to Assetrequest
	@ManyToOne
	@JoinColumn(name="assetrequestid")
	private Assetrequest assetrequest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getFileproforma() {
		return fileproforma;
	}

	public void setFileproforma(String fileproforma) {
		this.fileproforma = fileproforma;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public List<Productproform> getProductproforms() {
		return productproforms;
	}

	public void setProductproforms(List<Productproform> productproforms) {
		this.productproforms = productproforms;
	}

	public Assetrequest getAssetrequest() {
		return assetrequest;
	}

	public void setAssetrequest(Assetrequest assetrequest) {
		this.assetrequest = assetrequest;
	}

	public Integer getAssetrequestid() {
		return assetrequestid;
	}

	public void setAssetrequestid(Integer assetrequestid) {
		this.assetrequestid = assetrequestid;
	}
	
}
