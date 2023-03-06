package com.proyecto.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Initialconfig;
import com.proyecto.retail.repository.InitialConfigRepository;

@Service
public class InitialConfigServiceImp implements InitialConfigService {
	
	@Autowired
	private InitialConfigRepository initialConfigRepository;
	
	@Override
	public Initialconfig saveInitialConfig(Initialconfig initialConfig) {
		// TODO Auto-generated method stub
		return this.initialConfigRepository.save(initialConfig);
	}

	@Override
	public Initialconfig findByStateTrueAndName(String name) {
		// TODO Auto-generated method stub
		return this.initialConfigRepository.findByStateTrueAndName(name);
	}

}
