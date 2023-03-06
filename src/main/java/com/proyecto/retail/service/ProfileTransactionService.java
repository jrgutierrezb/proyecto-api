package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Profiletransaction;
import com.proyecto.retail.model.Transaction;

public interface ProfileTransactionService {
	
	Profiletransaction findById(Integer id);
	
	Profiletransaction saveProfiletransaction(Profiletransaction profiletransaction);
	
	Profiletransaction updateProfiletransaction(Profiletransaction profiletransaction, Integer id);
	
	boolean deletedById(Integer id);

	List<Profiletransaction> findByProfileid(Integer profileid);
	
	List<Profiletransaction> findByProfileidAndTransactionid(Integer profileid, Integer transactionid);
}
