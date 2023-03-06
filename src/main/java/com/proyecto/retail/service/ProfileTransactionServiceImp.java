package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Profile;
import com.proyecto.retail.model.Profiletransaction;
import com.proyecto.retail.model.Transaction;
import com.proyecto.retail.repository.ProfileRepository;
import com.proyecto.retail.repository.ProfileTransactionRepository;
import com.proyecto.retail.repository.TransactionRepository;

@Service
public class ProfileTransactionServiceImp implements ProfileTransactionService {
	
	@Autowired
	private ProfileTransactionRepository profileTransactionRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Profiletransaction findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Profiletransaction> profiletransaction = profileTransactionRepository.findById(id);
		if(profiletransaction.isPresent()) {
			return profiletransaction.get();
		}
		return null;
	}

	@Override
	public List<Profiletransaction> findByProfileid(Integer profileid) {
		// TODO Auto-generated method stub
		return profileTransactionRepository.findByProfileidAndStateTrue(profileid);
	}

	@Override
	public List<Profiletransaction> findByProfileidAndTransactionid(Integer profileid, Integer transactionid) {
		// TODO Auto-generated method stub
		return profileTransactionRepository.findByProfileidAndTransactionidAndStateTrue(profileid, transactionid);
	}

	
	@Override
	public Profiletransaction updateProfiletransaction(
			Profiletransaction profiletransaction, Integer id) {
		// TODO Auto-generated method stub
		Optional<Profiletransaction> profiletransactionexists = profileTransactionRepository.findById(id);
		if(!profiletransactionexists.isPresent()) {
			return null;
		}
		
		Profiletransaction profiletransactionupdate =  profiletransactionexists.get();
		
		Profile profile = this.profileRepository.getById(profiletransaction.getProfileid());
		if(profile == null) {
			return null;
		}
		
		Transaction transaction = transactionRepository.getById(profiletransaction.getTransactionid());
		if(transaction == null) {
			return null;
		}
		
		profiletransactionupdate.setProfile(profile);
		profiletransactionupdate.setTransaction(transaction);
		
		return this.profileTransactionRepository.save(profiletransactionupdate);
	}

	@Override
	public boolean deletedById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Profiletransaction> profiletransaction = profileTransactionRepository.findById(id);
		if(profiletransaction.isPresent()) {
			Profiletransaction deleted = profiletransaction.get();
			deleted.setState(false);
			profileTransactionRepository.save(deleted);
			return true;
		}
		return false;
	}

	@Override
	public Profiletransaction saveProfiletransaction(Profiletransaction profiletransaction) {
		// TODO Auto-generated method stub
		Profile profile = this.profileRepository.getById(profiletransaction.getProfileid());
		if(profile == null) {
			return null;
		}
		
		Transaction transaction = transactionRepository.getById(profiletransaction.getTransactionid());
		if(transaction == null) {
			return null;
		}
		
		profiletransaction.setProfile(profile);
		profiletransaction.setTransaction(transaction);
		
		return profileTransactionRepository.save(profiletransaction);
	}

}
