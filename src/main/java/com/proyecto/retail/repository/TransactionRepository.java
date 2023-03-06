package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	List<Transaction> findByStateTrue();
	
	List<Transaction> findByNameAndStateTrue(String name);
	
	List<Transaction> findByStateTrueAndModuleId(Integer moduleId);
	
	List<Transaction> findByNameAndStateTrueAndModuleId(String name, Integer moduleid);
	
	List<Transaction> findByUrlAndStateTrueAndModuleId(String url, Integer moduleid);
	
	List<Transaction> findDistinctByStateTrueAndProfiletransactionsProfileid(Integer profileid);
	
	List<Transaction> findByStateTrueAndProfiletransactionsProfileidAndModuleId( Integer profileid, Integer moduleId);
	
	List<Transaction> findDistinctByStateTrueAndProfiletransactionsProfileidAndIdmoduleNotIn(Integer profileid, List<Integer> moduleId);

}
