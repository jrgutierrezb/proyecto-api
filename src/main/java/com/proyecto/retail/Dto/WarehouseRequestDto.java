package com.proyecto.retail.Dto;

public class WarehouseRequestDto {
	
	private Integer id;
	
	private Integer userid;
	
	private Integer inventoryproductid;
	
	private String observation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getInventoryproductid() {
		return inventoryproductid;
	}

	public void setInventoryproductid(Integer inventoryproductid) {
		this.inventoryproductid = inventoryproductid;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
}
