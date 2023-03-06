package com.proyecto.retail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Processstate;
import com.proyecto.retail.repository.ProcessStateRepository;

@Service
public class ProcessStateServiceImp implements ProcessStateService {
	
	@Autowired
	private ProcessStateRepository processStateRepository;
	
	public List<Processstate> fechAll() {
		return this.processStateRepository.findByStateTrue();
	}

	@Override
	public List<Processstate> findByCode(String code) {
		// TODO Auto-generated method stub
		return this.processStateRepository.findByCodeAndStateTrue(code);
	}

	@Override
	public List<Processstate> findByCodeIn(List<String> codes) {
		// TODO Auto-generated method stub
		return this.processStateRepository.findByCodeInAndStateTrue(codes);
	}

}
