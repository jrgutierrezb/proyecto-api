package com.proyecto.retail.Dto;

public class InventoryProducDto {
	
	private Integer id;
    private Integer yearwarranty;
    private Integer inventoryid;
    private Integer productid;
    private Integer agencyid;
    private Integer billingid;
    private Integer workdepartmentid;
    private Integer productstateid;
    private String invoicenumber;
	private String brand;
	private String  model;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYearwarranty() {
		return yearwarranty;
	}
	public void setYearwarranty(Integer yearwarranty) {
		this.yearwarranty = yearwarranty;
	}
	public Integer getInventoryid() {
		return inventoryid;
	}
	public void setInventoryid(Integer inventoryid) {
		this.inventoryid = inventoryid;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
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
	public Integer getWorkdepartmentid() {
		return workdepartmentid;
	}
	public void setWorkdepartmentid(Integer workdepartmentid) {
		this.workdepartmentid = workdepartmentid;
	}
	public Integer getProductstateid() {
		return productstateid;
	}
	public void setProductstateid(Integer productstateid) {
		this.productstateid = productstateid;
	}
	public String getInvoicenumber() {
		return invoicenumber;
	}
	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
    
}
