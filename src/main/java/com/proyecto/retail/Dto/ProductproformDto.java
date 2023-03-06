package com.proyecto.retail.Dto;

import java.math.BigDecimal;

public class ProductproformDto {
	
	private Integer id;

	private BigDecimal totalvalue;

	private BigDecimal value;
	
	private Integer productid;
	
	private Integer proformid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getProformid() {
		return proformid;
	}

	public void setProformid(Integer proformid) {
		this.proformid = proformid;
	}
}
