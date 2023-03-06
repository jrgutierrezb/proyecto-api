package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Messagebox;

public interface MessageboxService {
	List<Messagebox> findBySend(Boolean send);
	
	Messagebox saveMessagebox(Messagebox messageBox);
	
	Messagebox updateMessagebox(Messagebox messageBox, Integer messageBoxId);
}
