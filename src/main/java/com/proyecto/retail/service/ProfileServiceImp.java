package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Profile;
import com.proyecto.retail.repository.ProfileRepository;

@Service
public class ProfileServiceImp implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Profile saveProfile(Profile profile) {
		// TODO Auto-generated method stub
		return this.profileRepository.save(profile);
	}

	@Override
	public List<Profile> fetchProfileList() {
		// TODO Auto-generated method stub
		return this.profileRepository.findByStateTrue();
	}

	@Override
	public Profile updateProfile(Profile profile, Integer profileId) {
		// TODO Auto-generated method stub
		return this.profileRepository.save(profile);
	}

	@Override
	public void deleteProfileById(Integer profileId) {
		// TODO Auto-generated method stub
		this.profileRepository.deleteById(profileId);
	}

	@Override
	public Profile findById(Integer profileId) {
		// TODO Auto-generated method stub
		Optional<Profile> profile = null;
		profile = this.profileRepository.findById(profileId);
		if(profile.isPresent())
			return profile.get();
		else
			return null;
	}

	@Override
	public List<Profile> findByName(String name) {
		// TODO Auto-generated method stub
		return this.profileRepository.findByStateTrueAndName(name);
	}

}
