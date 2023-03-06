package com.proyecto.retail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.proyecto.retail.Dto.RequestAssignedDto;
import com.proyecto.retail.Dto.WarehouseRequestDto;
import com.proyecto.retail.model.Assetrequest;

public interface AssetRequestService {
	
	List<HashMap<String, Object>> findProductById(Integer id);
	
	Assetrequest findById(Integer id);
	
	List<HashMap<String, Object>> getById(Map<String, Object> parameters);
	
	Assetrequest findByIdAndProcessstateid(Integer id, Integer idState);
	
	Assetrequest SaveAssetRequest(Assetrequest assetrequest);
	
	Assetrequest UpdateAssetRequest(Assetrequest assetrequest, Integer assetRequestId);
	
	boolean DeleteAssetRequestById(Integer assetRequestId);
	
	Assetrequest AssignAssetRequest(RequestAssignedDto assetrequest, Integer assetRequestId);
	
	Assetrequest ApproveAssetRequest(Assetrequest assetrequest, Integer assetRequestId);
	
	Assetrequest DeniedAssetRequest(Assetrequest assetrequest, Integer assetRequestId);
	
	Assetrequest WareHouseAssetRequest(Assetrequest assetrequest, Integer assetRequestId);
	
	Assetrequest MaintenanceAssetRequest(WarehouseRequestDto assetrequest, Integer assetRequestId);
	
	Assetrequest DownAssetRequest(WarehouseRequestDto assetrequest, Integer assetRequestId);
	
	Assetrequest DevolutionAssetRequest(WarehouseRequestDto assetrequest, Integer assetRequestId);
	
	Assetrequest ProformAssetRequest(Assetrequest assetrequest);
	
	Assetrequest ApproveProformAssetRequest(Assetrequest assetrequest);
	
	Assetrequest DeniedProformAssetRequest(Assetrequest assetrequest);
	
	Assetrequest GuideAssetRequest(Assetrequest assetrequest);
	
	Assetrequest RegisterAssetRequest(Assetrequest assetrequest);
	
	Assetrequest SendAssetRequest(Assetrequest assetrequest, Integer assetRequestId);
	
	Assetrequest OutAssetRequest(Assetrequest assetrequest, Integer assetRequestId);
	
	Assetrequest ReceivedAssetRequest(Assetrequest assetrequest, Integer assetRequestId);
	
	List<Assetrequest> fetchAssetRequestList();
	
	List<Assetrequest> findByUseridrequested(Integer useridrequested);
	
	List<HashMap<String, Object>> fetchRequestAssignedFilter(Map<String, Object> parameters);
	
	List<HashMap<String, Object>> fetchRequestDeniedFilter(Map<String, Object> parameters);
	
	List<HashMap<String, Object>> fetchRequestWareHouseFilter(Map<String, Object> parameters);
	
	List<HashMap<String, Object>> fetchRequestManagerApprovedFilter(Map<String, Object> parameters);
	
	List<HashMap<String, Object>> fetchRequestInventoyFilter(Map<String, Object> parameters);
	
	List<Assetrequest> findByProcessstateidIn(List<Integer> processstateid);
	
	List<Assetrequest> findByUseridrequestedAndProcessstateidIn(Integer useridrequested, List<Integer> processstateid);
	
	List<Assetrequest> findByUseridupdateAndProcessstateidIn(String useridupdate, List<Integer> processstateid);
}
