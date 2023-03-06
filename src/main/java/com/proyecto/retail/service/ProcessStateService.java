package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Processstate;

public interface ProcessStateService {
	List<Processstate> fechAll();
	List<Processstate> findByCode(String code);
	List<Processstate> findByCodeIn(List<String> codes);
}
