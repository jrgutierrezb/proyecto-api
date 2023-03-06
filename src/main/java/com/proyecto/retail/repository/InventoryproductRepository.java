package com.proyecto.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Inventoryproduct;

public interface InventoryproductRepository  extends JpaRepository<Inventoryproduct, Integer> {

}
