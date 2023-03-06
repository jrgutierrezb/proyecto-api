package com.proyecto.retail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Profiletransaction;

public interface ProfileTransactionRepository extends JpaRepository<Profiletransaction, Integer> {
	
	Optional<Profiletransaction> findById(Integer id);

	List<Profiletransaction> findByProfileidAndStateTrue(Integer profileid);
	
	List<Profiletransaction> findByProfileidAndTransactionidAndStateTrue(Integer profileid,Integer transactionid);
	
	List<Profiletransaction> findByProfileidAndTransactionIdmoduleAndStateTrue(Integer profileid, Integer idmodule);
}
