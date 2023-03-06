package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	List<Profile> findByStateTrue();
	List<Profile> findByStateTrueAndName(String name);
}
