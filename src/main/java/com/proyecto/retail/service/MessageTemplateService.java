package com.proyecto.retail.service;

import com.proyecto.retail.model.Messagetemplate;

public interface MessageTemplateService {
	
	public Messagetemplate saveMessageTemplate(Messagetemplate messageTemplate);
	public Messagetemplate findByName(String name);
}
