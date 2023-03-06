package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Profiletransaction;
import com.proyecto.retail.model.Transaction;
import com.proyecto.retail.repository.ProfileTransactionRepository;
import com.proyecto.retail.repository.TransactionRepository;

@Service
public class TransactionServiceImp implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ProfileTransactionRepository profileTransactionRepository;

	@Override
	public List<Transaction> fetchTransactionList(Integer profileId) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findDistinctByStateTrueAndProfiletransactionsProfileid(profileId);
	}
	
	@Override
	public List<Transaction> transactionWithModuleidList(Integer profileId, List<Integer> moduleid) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findDistinctByStateTrueAndProfiletransactionsProfileidAndIdmoduleNotIn(profileId, moduleid);
	}

	@Override
	public Transaction SaveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction UpdateTransaction(Transaction transaction, Integer transactionId) {
		// TODO Auto-generated method stub
		Optional<Transaction> transactionDal = transactionRepository.findById(transactionId);
		if(!transactionDal.isPresent()) {
			return null;
		}
		Transaction transactionUpdate = transactionDal.get();
		transactionUpdate.setName(transaction.getName());
		transactionUpdate.setDescription(transaction.getDescription());
		transactionUpdate.setUrl(transaction.getUrl());
		transactionUpdate.setIdmodule(transaction.getIdmodule());
		transactionUpdate.setModule(transaction.getModule());
		return transactionRepository.save(transactionUpdate);
	}

	@Override
	public boolean DeleteTransactionById(Integer transactionId) {
		// TODO Auto-generated method stub
		Optional<Transaction> transactionDal = transactionRepository.findById(transactionId);
		if(!transactionDal.isPresent()) {
			return false;
		}
		Transaction transactionUpdate = transactionDal.get();
		transactionUpdate.setState(false);
		transactionRepository.save(transactionUpdate);
		return false;
	}

	@Override
	public Transaction findById(Integer transactionId) {
		// TODO Auto-generated method stub
		Optional<Transaction> transactionDal = transactionRepository.findById(transactionId);
		if(!transactionDal.isPresent()) {
			return null;
		}
		return transactionDal.get();
	}

	@Override
	public List<Transaction> fetchTransactionList() {
		// TODO Auto-generated method stub
		return transactionRepository.findByStateTrue();
	}

	@Override
	public List<Transaction> findByNameAndModuleId(String name, Integer moduleid) {
		// TODO Auto-generated method stub
		return transactionRepository.findByNameAndStateTrueAndModuleId(name, moduleid);
	}

	@Override
	public List<Transaction> findByUrlAndModuleId(String url, Integer moduleid) {
		// TODO Auto-generated method stub
		return transactionRepository.findByUrlAndStateTrueAndModuleId(url, moduleid);
	}

	@Override
	public List<Transaction> findByName(String name) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findByNameAndStateTrue(name);
	}
	
	@Override
	public List<Profiletransaction> findByProfileidAndIdmodule(Integer profileid, Integer idmodule) {
		// TODO Auto-generated method stub
		return this.profileTransactionRepository.findByProfileidAndTransactionIdmoduleAndStateTrue(profileid, idmodule);
	}


}
