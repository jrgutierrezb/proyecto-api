package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Mailsetting;

public interface MailsettingService {
	
	Mailsetting saveMailSetting(Mailsetting mailsetting);
	
	Mailsetting updateMailSetting(Mailsetting mailsetting, Integer mailsettingId);
	
	List<Mailsetting> fetchMailSettingList();
	
	Mailsetting findByMailSetting();
	
	Mailsetting findByStateTrueAndUsermail(String usermMail);
}
