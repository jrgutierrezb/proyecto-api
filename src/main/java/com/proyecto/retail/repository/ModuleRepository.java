package com.proyecto.retail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.retail.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
	
	Optional<Module> findById(Integer id);
	
	List<Module> findByUrlAndStateTrue(String url);
	
	List<Module> findByNameAndStateTrue(String name);
	
}
