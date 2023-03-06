package com.proyecto.retail.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="products")
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Timestamp adquisiondate;

	private Integer quantity;

	private String technicaldescription;

	private BigDecimal totalvalue;

	private BigDecimal value;

	//bi-directional many-to-one association to Productproform
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Productproform> productproforms;
	
	@Column(name="assetrequestid", insertable=false, updatable=false, nullable=false)
	private Integer assetrequestid;

	//bi-directional many-to-one association to Assetrequest
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="assetrequestid")
	private Assetrequest assetrequest;
	
	@Column(name="catalogid", insertable=false, updatable=false, nullable=false)
	private Integer catalogid;

	//bi-directional many-to-one association to Catalog
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="catalogid")
	private Catalog catalog;
	
	@Column(name="catalogproductid", insertable=false, updatable=false, nullable=false)
	private Integer catalogproductid;
	
	//bi-directional many-to-one association to Catalog
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="catalogproductid")
	private Catalog catalogproduct;
	
	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Inventoryproduct> inventoryproducts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getAdquisiondate() {
		return adquisiondate;
	}

	public void setAdquisiondate(Timestamp adquisiondate) {
		this.adquisiondate = adquisiondate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTechnicaldescription() {
		return technicaldescription;
	}

	public void setTechnicaldescription(String technicaldescription) {
		this.technicaldescription = technicaldescription;
	}

	public BigDecimal getTotalvalue() {
		return totalvalue;
	}

	public void setTotalvalue(BigDecimal totalvalue) {
		this.totalvalue = totalvalue;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
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

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Integer getAssetrequestid() {
		return assetrequestid;
	}

	public void setAssetrequestid(Integer assetrequestid) {
		this.assetrequestid = assetrequestid;
	}

	public Integer getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(Integer catalogid) {
		this.catalogid = catalogid;
	}

	public Integer getCatalogproductid() {
		return catalogproductid;
	}

	public void setCatalogproductid(Integer catalogproductid) {
		this.catalogproductid = catalogproductid;
	}

	public Catalog getCatalogproduct() {
		return catalogproduct;
	}

	public void setCatalogproduct(Catalog catalogproduct) {
		this.catalogproduct = catalogproduct;
	}

	public List<Inventoryproduct> getInventoryproducts() {
		return inventoryproducts;
	}

	public void setInventoryproducts(List<Inventoryproduct> inventoryproducts) {
		this.inventoryproducts = inventoryproducts;
	}
}
