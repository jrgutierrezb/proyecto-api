package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	
	List<Inventory> findByCatalogCodeAndStateTrue(String code);

}
