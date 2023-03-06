package com.proyecto.retail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Inventory;
import com.proyecto.retail.repository.InventoryRepository;

@Service
public class InventoryServiceImp implements InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public List<Inventory> findByCatalogCode(String code) {
		// TODO Auto-generated method stub
		return inventoryRepository.findByCatalogCodeAndStateTrue(code);
	}

}
