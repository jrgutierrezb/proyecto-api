package com.proyecto.retail.service;

import com.proyecto.retail.model.Initialconfig;

public interface InitialConfigService {

	public Initialconfig saveInitialConfig(Initialconfig initialConfig);
	public Initialconfig findByStateTrueAndName(String name);
}
