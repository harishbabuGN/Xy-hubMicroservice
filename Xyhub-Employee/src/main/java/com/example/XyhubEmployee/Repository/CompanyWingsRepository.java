package com.example.XyhubEmployee.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.XyhubEmployee.Entity.CompanyWings;

public interface CompanyWingsRepository extends JpaRepository<CompanyWings, String> {
	@Query("SELECT j from CompanyWings j WHERE j.Id = :id ")
	CompanyWings getWingById(String id);

	@Query("Select new map(e.Id as wing_id,e.wingName as wing_name,e.status as status) from CompanyWings e  where "
			+ "(:searchString is null Or lower(e.wingName) LIKE %:searchString% ) order by e.wingName ASC")
	Page<Map> getAllWing( String searchString,Pageable pageable);
	
	@Query("Select new map(e.Id as wing_id,e.wingName as wing_name,e.status as status) from CompanyWings e  where "
			+ "e.status='1' and "
			+ "(:searchString is null Or lower(e.wingName) LIKE %:searchString% ) order by e.wingName ASC")
	List<Map> getAllActiveWing( String searchString);
	
	@Query(value = "SELECT j from CompanyWings j WHERE j.Id = :wing_id ")
	CompanyWings getWingByIds(String wing_id);
	
	
	@Query("select j from CompanyWings j where j.wingName =:wingName")
	CompanyWings getWingName(String wingName) ;
	
	@Query("select distinct new map(c.Id as wing_id,c.wingName as wing_name,c.status as status) from CompanyWings c where c.status='1' and "
			+ "lower(c.wingName) LIKE %:searchString% ")
			List<Map> searchActiveWing(String searchString);
	@Query("select distinct new map(c.Id as wing_id,c.wingName as wing_name,c.status as status) from CompanyWings c where "
			+ "lower(c.wingName) LIKE %:searchString% ")
			List<Map> searchWing(String searchString);
	}