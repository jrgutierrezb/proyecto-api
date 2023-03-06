package com.proyecto.retail.Dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ProductDto {

	private Integer id;

	private Timestamp adquisiondate;

	private Integer quantity;

	private String technicaldescription;

	private BigDecimal totalvalue;

	private BigDecimal value;

	private List<ProductproformDto> productproforms;
	
	private List<InventoryProducDto> inventoryproducts;
	
	private Integer assetrequestid;
	
	private Integer catalogid;
	
	private Integer catalogproductid;

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

	public List<ProductproformDto> getProductproforms() {
		return productproforms;
	}

	public void setProductproforms(List<ProductproformDto> productproforms) {
		this.productproforms = productproforms;
	}

	public List<InventoryProducDto> getInventoryproducts() {
		return inventoryproducts;
	}

	public void setInventoryproducts(List<InventoryProducDto> inventoryproducts) {
		this.inventoryproducts = inventoryproducts;
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
}
