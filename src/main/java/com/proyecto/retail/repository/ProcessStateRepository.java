package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Processstate;

public interface ProcessStateRepository extends JpaRepository<Processstate, Integer> {
	
	List<Processstate> findByStateTrue();
	
	List<Processstate> findByCodeAndStateTrue(String code);
	
	List<Processstate> findByCodeInAndStateTrue(List<String> codes);
}
