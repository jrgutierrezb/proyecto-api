package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Mailsetting;
import com.proyecto.retail.repository.MailsettingRepository;

@Service
public class MailsettingServiceImp implements MailsettingService {
	
	@Autowired
	private MailsettingRepository mailsettingRepository;


	@Override
	public Mailsetting saveMailSetting(Mailsetting mailsetting) {
		// TODO Auto-generated method stub
		return this.mailsettingRepository.save(mailsetting);
	}

	@Override
	public List<Mailsetting> fetchMailSettingList() {
		// TODO Auto-generated method stub
		return this.mailsettingRepository.findByStateTrue();
	}

	@Override
	public Mailsetting findByMailSetting() {
		// TODO Auto-generated method stub
		List<Mailsetting> mailSettings = this.mailsettingRepository.findByStateTrue();
		if(mailSettings.isEmpty())
			return null;
		else
			return mailSettings.get(0);
	}

	@Override
	public Mailsetting updateMailSetting(Mailsetting mailsetting, Integer mailsettingId) {
		// TODO Auto-generated method stub
		Optional<Mailsetting> mail = this.mailsettingRepository.findById(mailsettingId);
		Mailsetting mailUpdate = null;
		if(mail.isPresent())
		{
			mailUpdate = mail.get();
		}
		if(mailUpdate != null) {
			mailUpdate.setAuth(mailsetting.getAuth());
			mailUpdate.setHost(mailsetting.getHost());
			mailUpdate.setPass(mailsetting.getPass());
			mailUpdate.setPort(mailsetting.getPort());
			mailUpdate.setUsermail(mailsetting.getUsermail());
			return this.mailsettingRepository.save(mailUpdate);
		}
		
		return null;
	}

	@Override
	public Mailsetting findByStateTrueAndUsermail(String usermMail) {
		// TODO Auto-generated method stub
		List<Mailsetting> mailSettings = this.mailsettingRepository.findByStateTrueAndUsermail(usermMail);
		if(mailSettings.isEmpty())
			return null;
		else
			return mailSettings.get(0);
	}

}
