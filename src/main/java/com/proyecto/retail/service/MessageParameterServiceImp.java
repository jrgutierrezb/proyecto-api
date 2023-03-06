package com.proyecto.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Messageparameter;
import com.proyecto.retail.repository.MessageParameterRepository;

@Service
public class MessageParameterServiceImp implements MessageParameterService {
	
	@Autowired
	private MessageParameterRepository messageParameterRepository;

	@Override
	public Messageparameter saveMessageParameter(Messageparameter messageparameter) {
		// TODO Auto-generated method stub
		return this.messageParameterRepository.save(messageparameter);
	}

}
