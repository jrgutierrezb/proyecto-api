package com.proyecto.retail.model;

import java.math.BigDecimal;

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
@Table(name="productproforms")
public class Productproform extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal totalvalue;

	private BigDecimal value;
	
	@Column(name="productid", insertable=false, updatable=false, nullable=false)
	private Integer productid;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;
	
	@Column(name="proformid", insertable=false, updatable=false, nullable=false)
	private Integer proformid;

	//bi-directional many-to-one association to Proform
	@ManyToOne
	@JoinColumn(name="proformid")
	private Proform proform;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getProformid() {
		return proformid;
	}

	public void setProformid(Integer proformid) {
		this.proformid = proformid;
	}

	public Proform getProform() {
		return proform;
	}

	public void setProform(Proform proform) {
		this.proform = proform;
	}
}
