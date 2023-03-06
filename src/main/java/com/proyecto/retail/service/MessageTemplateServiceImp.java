package com.proyecto.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Messagetemplate;
import com.proyecto.retail.repository.MessageTemplateRepository;

@Service
public class MessageTemplateServiceImp implements MessageTemplateService {
	
	@Autowired
	private MessageTemplateRepository messageTemplateRepository;
	
	@Override
	public Messagetemplate saveMessageTemplate(Messagetemplate messageTemplate) {
		// TODO Auto-generated method stub
		return this.messageTemplateRepository.save(messageTemplate);
	}

	@Override
	public Messagetemplate findByName(String name) {
		// TODO Auto-generated method stub
		return this.messageTemplateRepository.findByNameAndStateTrue(name);
	}

	

}
