package com.proyecto.retail.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.User;
import com.proyecto.retail.repository.UserRepository;


@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return this.userRepository.save(user);
	}

	@Override
	public List<User> fetchUserList() {
		// TODO Auto-generated method stub
		return this.userRepository.findByStateTrue();
	}

	@Override
	public User updateUser(User user, Integer userId) {
		// TODO Auto-generated method stub
		User userExists = userRepository.getById(userId);
		user.setPassword(userExists.getPassword());
		user = this.userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUserById(Integer userId) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(userId);
	}

	@Override
	public User findById(Integer userId) {
		// TODO Auto-generated method stub
		Optional<User> user = null;
		user = this.userRepository.findById(userId);
		if(user.isPresent()) 
			return user.get();
		else 
			return null;
	}

	@Override
	public List<User> findByUserName(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsernameAndStateTrue(username);
	}

	@Override
	public List<User> findByMail(String mail) {
		// TODO Auto-generated method stub
		return this.userRepository.findByMailAndStateTrue(mail);
	}

	@Override
	public List<User> findByIdentification(String identification) {
		// TODO Auto-generated method stub
		return this.userRepository.findByIdentificationAndStateTrue(identification);
	}


	@Override
	public List<User> findByUsernameAndPassword(String username, String pass) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsernameAndPasswordAndStateTrue(username, pass);
	}

	@Override
	public List<User> findByUsernameAndIstemporalpassword(String username, Boolean istemporalpassword) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsernameAndIstemporalpasswordAndStateTrue(username, istemporalpassword);
	}
	
    public String generateRandomPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }

	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		String userName = ((UserDetails) authentication.getPrincipal()).getUsername();
		
		List<User> users = this.userRepository.findByUsernameAndStateTrue(userName);
		
		if(users.isEmpty()) {
			return null;
		}
		
        return users.get(0);
	}
}
