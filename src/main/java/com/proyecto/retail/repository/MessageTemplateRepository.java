package com.proyecto.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Messagetemplate;

public interface MessageTemplateRepository extends JpaRepository<Messagetemplate, Integer> {
	
	Messagetemplate findByNameAndStateTrue(String name);
}
