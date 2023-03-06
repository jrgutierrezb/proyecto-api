package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Module;
import com.proyecto.retail.model.Transaction;
import com.proyecto.retail.repository.ModuleRepository;
import com.proyecto.retail.repository.TransactionRepository;

@Service
public class ModuleServiceImp implements ModuleService {
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Module findById(Integer moduleId) {
		// TODO Auto-generated method stub
		Optional<Module> module = moduleRepository.findById(moduleId);
		if(!module.isPresent()) {
			return null;
		}
		return module.get();
	}

	@Override
	public Module SaveModule(Module module) {
		// TODO Auto-generated method stub
		return moduleRepository.save(module);
	}

	@Override
	public Module UpdateModule(Module module, Integer moduleId) {
		// TODO Auto-generated method stub
		Optional<Module> moduleDal = moduleRepository.findById(moduleId);
		if(!moduleDal.isPresent()) {
			return null;
		}
		
		Module moduleUpdate = moduleDal.get();
		moduleUpdate.setName(module.getName());
		moduleUpdate.setDescription(module.getDescription());
		moduleUpdate.setIcon(module.getIcon());
		moduleUpdate.setUrl(module.getUrl());
		return moduleRepository.save(moduleUpdate);
	}

	@Override
	public boolean DeleteModuleById(Integer moduleId) {
		// TODO Auto-generated method stub
		Optional<Module> moduleDal = moduleRepository.findById(moduleId);
		if(!moduleDal.isPresent()) {
			return false;
		}
		
		List<Transaction> transacctions = this.transactionRepository.findByStateTrueAndModuleId(moduleId);
		if(transacctions != null & !transacctions.isEmpty()) {
			return false;
		}

		Module moduleUpdate = moduleDal.get();
		moduleUpdate.setState(false);
		moduleRepository.save(moduleUpdate);
		return true;
	}

	@Override
	public List<Module> fetchModuleList() {
		// TODO Auto-generated method stub
		return moduleRepository.findAll();
	}

	@Override
	public List<Module> findByName(String name) {
		// TODO Auto-generated method stub
		return moduleRepository.findByNameAndStateTrue(name);
	}

	@Override
	public List<Module> findByUrl(String url) {
		// TODO Auto-generated method stub
		return moduleRepository.findByUrlAndStateTrue(url);
	}

	
}
