package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Profiletransaction;
import com.proyecto.retail.model.Transaction;

public interface TransactionService {
	
	Transaction SaveTransaction(Transaction transaction);
	
	Transaction UpdateTransaction(Transaction transaction, Integer transactionId);
	
	boolean DeleteTransactionById(Integer transactionId);
	
	Transaction findById(Integer transactionId);
	
	List<Transaction> findByName(String name);
	
	List<Transaction> fetchTransactionList();
	
	List<Transaction> transactionWithModuleidList(Integer profileId, List<Integer> moduleid);

    List<Transaction> fetchTransactionList(Integer profileId);
    
    List<Transaction> findByNameAndModuleId(String name, Integer moduleid);
	
	List<Transaction> findByUrlAndModuleId(String url, Integer moduleid);
	
	List<Profiletransaction> findByProfileidAndIdmodule(Integer profileid, Integer idmodule);
}
