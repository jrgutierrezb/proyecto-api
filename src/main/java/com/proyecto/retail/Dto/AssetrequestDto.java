package com.proyecto.retail.Dto;

import java.util.List;

import org.hibernate.annotations.Type;

public class AssetrequestDto {
	private Integer id;

	private String casenumber;
	
	private String description;

	@Type(type = "org.hibernate.type.TextType")
	private String filerequest;
	
	@Type(type = "org.hibernate.type.TextType")
	private String filereferralguide;
	
	private String numberguide;

	private String sapcode;

	private Integer useridrequested;
	
	private Integer useridassigned;
	
	private Integer useridapproved;
	
	private Integer useriddenied;
	
	private String deniedobservation;
	
	private Integer useriddeniedproform;
	
	private String deniedproformobservation;
	
	private Integer useridproform;
	
	private Integer useridapprovedproform;
	
	private Integer useridguide;
	
	private Integer useridregister;
	
	private Integer useridwarehouse;
	
	private Integer useridreceived;
	
	private Integer useridexited;
	
	private String usernamerequested;
	
	private Integer catalogid;
	
	private Integer prioritytypeid;

	private Integer agencyid;
	
	private Integer billingid;
	
	private Integer processstateid;
	
	private Integer workdepartmentid;
	
	private Integer inventoryproductid;
	
	private List<ProductDto> products;
	
	private List<ProformDto> proforms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCasenumber() {
		return casenumber;
	}

	public void setCasenumber(String casenumber) {
		this.casenumber = casenumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilerequest() {
		return filerequest;
	}

	public void setFilerequest(String filerequest) {
		this.filerequest = filerequest;
	}

	public String getSapcode() {
		return sapcode;
	}

	public void setSapcode(String sapcode) {
		this.sapcode = sapcode;
	}

	public Integer getUseridrequested() {
		return useridrequested;
	}

	public void setUseridrequested(Integer useridrequested) {
		this.useridrequested = useridrequested;
	}

	public Integer getUseridassigned() {
		return useridassigned;
	}

	public void setUseridassigned(Integer useridassigned) {
		this.useridassigned = useridassigned;
	}

	public Integer getUseridapproved() {
		return useridapproved;
	}

	public void setUseridapproved(Integer useridapproved) {
		this.useridapproved = useridapproved;
	}

	public Integer getUseriddenied() {
		return useriddenied;
	}

	public void setUseriddenied(Integer useriddenied) {
		this.useriddenied = useriddenied;
	}

	public Integer getUseridproform() {
		return useridproform;
	}

	public void setUseridproform(Integer useridproform) {
		this.useridproform = useridproform;
	}

	public Integer getUseridapprovedproform() {
		return useridapprovedproform;
	}

	public void setUseridapprovedproform(Integer useridapprovedproform) {
		this.useridapprovedproform = useridapprovedproform;
	}

	public String getUsernamerequested() {
		return usernamerequested;
	}

	public void setUsernamerequested(String usernamerequested) {
		this.usernamerequested = usernamerequested;
	}

	public Integer getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(Integer catalogid) {
		this.catalogid = catalogid;
	}

	public Integer getPrioritytypeid() {
		return prioritytypeid;
	}

	public void setPrioritytypeid(Integer prioritytypeid) {
		this.prioritytypeid = prioritytypeid;
	}

	public Integer getAgencyid() {
		return agencyid;
	}

	public void setAgencyid(Integer agencyid) {
		this.agencyid = agencyid;
	}


	public Integer getBillingid() {
		return billingid;
	}

	public void setBillingid(Integer billingid) {
		this.billingid = billingid;
	}

	public Integer getProcessstateid() {
		return processstateid;
	}

	public void setProcessstateid(Integer processstateid) {
		this.processstateid = processstateid;
	}

	public Integer getWorkdepartmentid() {
		return workdepartmentid;
	}

	public void setWorkdepartmentid(Integer workdepartmentid) {
		this.workdepartmentid = workdepartmentid;
	}

	public Integer getInventoryproductid() {
		return inventoryproductid;
	}

	public void setInventoryproductid(Integer inventoryproductid) {
		this.inventoryproductid = inventoryproductid;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public List<ProformDto> getProforms() {
		return proforms;
	}

	public void setProforms(List<ProformDto> proforms) {
		this.proforms = proforms;
	}

	public String getFilereferralguide() {
		return filereferralguide;
	}

	public void setFilereferralguide(String filereferralguide) {
		this.filereferralguide = filereferralguide;
	}

	public String getDeniedobservation() {
		return deniedobservation;
	}

	public void setDeniedobservation(String deniedobservation) {
		this.deniedobservation = deniedobservation;
	}

	public Integer getUseriddeniedproform() {
		return useriddeniedproform;
	}

	public void setUseriddeniedproform(Integer useriddeniedproform) {
		this.useriddeniedproform = useriddeniedproform;
	}

	public String getDeniedproformobservation() {
		return deniedproformobservation;
	}

	public void setDeniedproformobservation(String deniedproformobservation) {
		this.deniedproformobservation = deniedproformobservation;
	}

	public Integer getUseridguide() {
		return useridguide;
	}

	public void setUseridguide(Integer useridguide) {
		this.useridguide = useridguide;
	}

	public Integer getUseridregister() {
		return useridregister;
	}

	public void setUseridregister(Integer useridregister) {
		this.useridregister = useridregister;
	}

	public String getNumberguide() {
		return numberguide;
	}

	public void setNumberguide(String numberguide) {
		this.numberguide = numberguide;
	}

	public Integer getUseridexited() {
		return useridexited;
	}

	public void setUseridexited(Integer useridexited) {
		this.useridexited = useridexited;
	}

	public Integer getUseridwarehouse() {
		return useridwarehouse;
	}

	public void setUseridwarehouse(Integer useridwarehouse) {
		this.useridwarehouse = useridwarehouse;
	}

	public Integer getUseridreceived() {
		return useridreceived;
	}

	public void setUseridreceived(Integer useridreceived) {
		this.useridreceived = useridreceived;
	}

}
