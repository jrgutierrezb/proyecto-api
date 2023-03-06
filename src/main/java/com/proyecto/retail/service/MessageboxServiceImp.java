package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Mailsetting;
import com.proyecto.retail.model.Messagebox;
import com.proyecto.retail.model.User;
import com.proyecto.retail.repository.MessageBoxRepository;
import com.proyecto.retail.repository.MessageTemplateRepository;

@Service
public class MessageboxServiceImp implements MessageboxService {
	
	@Autowired
	private MessageBoxRepository messageBoxRepository;

	@Override
	public List<Messagebox> findBySend(Boolean send) {
		// TODO Auto-generated method stub
		return this.messageBoxRepository.findBySend(send);
	}
	
	@Override
	public Messagebox saveMessagebox(Messagebox messageBox) {
		// TODO Auto-generated method stub
		return this.messageBoxRepository.save(messageBox);
	}

	@Override
	public Messagebox updateMessagebox(Messagebox messageBox, Integer messageBoxId) {
		// TODO Auto-generated method stub
		Optional<Messagebox> message = this.messageBoxRepository.findById(messageBoxId);
		Messagebox messageBoxUpdate = null;
		if(message.isPresent())
		{
			messageBoxUpdate = message.get();
		}
		if(messageBoxUpdate != null) {
			messageBoxUpdate.setSend(true);
			return this.messageBoxRepository.save(messageBoxUpdate);
		}
				
		return null;
	}

}
