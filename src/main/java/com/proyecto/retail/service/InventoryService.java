package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Inventory;

public interface InventoryService {
	List<Inventory> findByCatalogCode(String code);
}
