package com.proyecto.retail.Dto;

import java.util.List;

public class ProformDto {
	private Integer id;
	
	private Boolean approved = false;

	private String fileproforma;
	
	private String supplier;

	private List<ProductproformDto> productproforms;
	
	private Integer assetrequestid;

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

	public List<ProductproformDto> getProductproforms() {
		return productproforms;
	}

	public void setProductproforms(List<ProductproformDto> productproforms) {
		this.productproforms = productproforms;
	}

	public Integer getAssetrequestid() {
		return assetrequestid;
	}

	public void setAssetrequestid(Integer assetrequestid) {
		this.assetrequestid = assetrequestid;
	}

}
