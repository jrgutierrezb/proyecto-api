package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByStateTrue();
	List<User> findByUsernameAndStateTrue(String username);
	List<User> findByMailAndStateTrue(String mail);
	List<User> findByIdentificationAndStateTrue(String identification);
	List<User> findByUsernameAndIstemporalpasswordAndStateTrue(String username, Boolean istemporalpassword);
	List<User> findByUsernameAndPasswordAndStateTrue(String username, String pass);
}
