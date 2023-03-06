package com.proyecto.retail.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Module;
import com.proyecto.retail.model.Profile;
import com.proyecto.retail.model.Transaction;
import com.proyecto.retail.service.ModuleServiceImp;
import com.proyecto.retail.service.TransactionServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransactionController {
	
	@Autowired
	private TransactionServiceImp transactionServiceImp;
	
	@Autowired
	private ModuleServiceImp moduleServiceImp;
	
	@PostMapping(value = "/transactions")
    public ResponseEntity<Object> saveUser(@RequestBody Transaction transaction)
    {
		try	{
			List<Transaction> existsTransaction = this.transactionServiceImp.findByNameAndModuleId(transaction.getName(), transaction.getIdmodule());
			if(!existsTransaction.isEmpty()) {
				return ResponseHandler.generateResponse(true, 1, "nombre allready exists", HttpStatus.OK, null);
			}
			
			existsTransaction = this.transactionServiceImp.findByUrlAndModuleId(transaction.getUrl(), transaction.getIdmodule());
			if(!existsTransaction.isEmpty()) {
				return ResponseHandler.generateResponse(true, 1, "Ruta allready exists", HttpStatus.OK, null);
			}
			
			Module existsModule = this.moduleServiceImp.findById(transaction.getIdmodule());
			
			if(existsModule == null) {
				return ResponseHandler.generateResponse(true, 1, "module does not exists", HttpStatus.OK, null);
			}
			
			transaction.setModule(existsModule);
			
			transaction = this.transactionServiceImp.SaveTransaction(transaction);
			
			return ResponseHandler.generateResponse(false, 0, "Successfully added data!", HttpStatus.OK, transaction);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
	
	// Read operation
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchTransactionList()
    {
        try {
        	
        	List<Transaction> transactions = null;
        	transactions = this.transactionServiceImp.fetchTransactionList();
        	if(transactions == null || transactions.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
        	}
        	
        	List<Map<String, Object>> listTransactions = transactions.stream().map(item -> {
        		Map<String, Object> transactionDto = new HashMap<String, Object>();
        		transactionDto.put("id", item.getId());
        		transactionDto.put("description", item.getDescription());
        		transactionDto.put("name", item.getName());
        		transactionDto.put("url", item.getUrl());
        		transactionDto.put("idmodule", item.getIdmodule());
        		transactionDto.put("namemodule", item.getModule().getName());
        		return transactionDto;
        	}).collect(Collectors.toList());
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, listTransactions);
        	
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") Integer transactionId)
    {
    	try {
    		Transaction transaction = null;
    		transaction = this.transactionServiceImp.findById(transactionId);
        	if(transaction == null) {
        		return ResponseHandler.generateResponse(true, 1, "user does not exists ", HttpStatus.OK, null);
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, transaction);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody Transaction transaction, @PathVariable("id") Integer transactionId)
    {
    	try {
    		
    		Module existsModule = this.moduleServiceImp.findById(transaction.getIdmodule());
			if(existsModule == null) {
				return ResponseHandler.generateResponse(true, 1, "module does not exists", HttpStatus.OK, null);
			}
			transaction.setModule(existsModule);
			
			transaction = this.transactionServiceImp.UpdateTransaction(transaction, transactionId);
    		
    		if(transaction == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "Successfully updated data!", HttpStatus.OK, transaction);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Delete operation
    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUserById(@PathVariable("id")
                                       Integer transactionId)
    {
    	try {
    		Boolean deleted = this.transactionServiceImp.DeleteTransactionById(transactionId);
    		
    		if(deleted) {
    			return ResponseHandler.generateResponse(true, 1, "Error deleted data!", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "Successfully deleted data!", HttpStatus.OK, null);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
        
    }

}
