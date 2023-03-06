package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Profile;

public interface ProfileService {
	// Save operation
	Profile saveProfile(Profile profile);
 
    // Read operation
    List<Profile> fetchProfileList();
 
    // Update operation
    Profile updateProfile(Profile profile, Integer profileId);
 
    // Delete operation
    void deleteProfileById(Integer profileId);
    
    // find
    Profile findById(Integer profileId);
    
    List<Profile> findByName(String name);
}
