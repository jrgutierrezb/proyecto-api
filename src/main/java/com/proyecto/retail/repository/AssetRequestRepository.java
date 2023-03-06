package com.proyecto.retail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Assetrequest;

public interface AssetRequestRepository extends JpaRepository<Assetrequest, Integer> {
	
	Optional<Assetrequest> findById(Integer id);
	
	List<Assetrequest> findByStateTrue();
	
	Assetrequest findByIdAndProcessstateidAndStateTrue(Integer id, Integer processstateid);
	
	List<Assetrequest> findByUseridrequestedAndStateTrue(Integer useridrequested);
	
	List<Assetrequest> findByProcessstateidInAndStateTrue(List<Integer> processstateid);
	
	List<Assetrequest> findByUseridrequestedAndProcessstateidInAndStateTrue(Integer useridrequested, List<Integer> processstateid);
	
	List<Assetrequest> findByUseridupdateAndProcessstateidInAndStateTrue(String useridupdate, List<Integer> processstateid);
}
