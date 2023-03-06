package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Module;

public interface ModuleService {
	
	Module SaveModule(Module module);
	
	Module UpdateModule(Module module, Integer moduleId);
	
	boolean DeleteModuleById(Integer moduleId);
	
	Module findById(Integer moduleId);
	
	List<Module> fetchModuleList();
	
	List<Module> findByUrl(String url);
	
	List<Module> findByName(String name);
}
