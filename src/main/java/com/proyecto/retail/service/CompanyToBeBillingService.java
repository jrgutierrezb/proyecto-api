package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Companytobebilling;

public interface CompanyToBeBillingService {
	List<Companytobebilling> fetchCompanyToBeBillingList();
	Companytobebilling findById(Integer agencyId);
}
