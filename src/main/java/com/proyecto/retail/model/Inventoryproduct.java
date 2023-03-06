package com.proyecto.retail.model;

import java.sql.Timestamp;

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
@Table(name="inventoryproducts")
public class Inventoryproduct extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Timestamp datein;

	private Timestamp dateout;
	
	private Integer yearwarranty;
	
	private String invoicenumber;
	
	private String brand;
	
	private String  model;
	
	@Column(name="productstateid", insertable=false, updatable=false, nullable=false)
	private Integer productstateid;
	
	//bi-directional many-to-one association to Catalog
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="productstateid")
	private Catalog productstate;
	
	@Column(name="inventoryid", insertable=false, updatable=false, nullable=false)
	private Integer inventoryid;

	//bi-directional many-to-one association to Inventory
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="inventoryid")
	private Inventory inventory;
	
	@Column(name="productid", insertable=false, updatable=false, nullable=false)
	private Integer productid;
		
	//bi-directional many-to-one association to Product
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;
	
	@Column(name="agencyid", insertable=false, updatable=false, nullable=false)
	private Integer agencyid;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="agencyid")
	private Agency agency;
	
	@Column(name="billingid", insertable=false, updatable=false, nullable=false)
	private Integer billingid;

	//bi-directional many-to-one association to Companytobebilling
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="billingid")
	private Companytobebilling companytobebilling;
	
	@Column(name="workdepartmentid", insertable=false, updatable=false, nullable=false)
	private Integer workdepartmentid;

	//bi-directional many-to-one association to Workdepartment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="workdepartmentid")
	private Workdepartment workdepartment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDatein() {
		return datein;
	}

	public void setDatein(Timestamp datein) {
		this.datein = datein;
	}

	public Timestamp getDateout() {
		return dateout;
	}

	public void setDateout(Timestamp dateout) {
		this.dateout = dateout;
	}

	public Integer getInventoryid() {
		return inventoryid;
	}

	public void setInventoryid(Integer inventoryid) {
		this.inventoryid = inventoryid;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getYearwarranty() {
		return yearwarranty;
	}

	public void setYearwarranty(Integer yearwarranty) {
		this.yearwarranty = yearwarranty;
	}

	public Integer getProductstateid() {
		return productstateid;
	}

	public void setProductstateid(Integer productstateid) {
		this.productstateid = productstateid;
	}

	public Catalog getProductstate() {
		return productstate;
	}

	public void setProductstate(Catalog productstate) {
		this.productstate = productstate;
	}

	public Integer getAgencyid() {
		return agencyid;
	}

	public void setAgencyid(Integer agencyid) {
		this.agencyid = agencyid;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Integer getBillingid() {
		return billingid;
	}

	public void setBillingid(Integer billingid) {
		this.billingid = billingid;
	}

	public Companytobebilling getCompanytobebilling() {
		return companytobebilling;
	}

	public void setCompanytobebilling(Companytobebilling companytobebilling) {
		this.companytobebilling = companytobebilling;
	}

	public Integer getWorkdepartmentid() {
		return workdepartmentid;
	}

	public void setWorkdepartmentid(Integer workdepartmentid) {
		this.workdepartmentid = workdepartmentid;
	}

	public Workdepartment getWorkdepartment() {
		return workdepartment;
	}

	public void setWorkdepartment(Workdepartment workdepartment) {
		this.workdepartment = workdepartment;
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
