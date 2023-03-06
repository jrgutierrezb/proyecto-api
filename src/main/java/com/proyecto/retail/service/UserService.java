package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.User;

public interface UserService {
	// Save operation
    User saveUser(User user);
 
    // Read operation
    List<User> fetchUserList();
 
    // Update operation
    User updateUser(User user, Integer userId);
 
    // Delete operation
    void deleteUserById(Integer userId);
    
    // find
    User findById(Integer userId);
    
    User getCurrentUser();
    
    List<User> findByUserName(String username);
    
	List<User> findByMail(String mail);
	
	List<User> findByIdentification(String identification);
	
	List<User> findByUsernameAndIstemporalpassword(String username, Boolean istemporalpassword);
	
	List<User> findByUsernameAndPassword(String username, String pass);
    
}
