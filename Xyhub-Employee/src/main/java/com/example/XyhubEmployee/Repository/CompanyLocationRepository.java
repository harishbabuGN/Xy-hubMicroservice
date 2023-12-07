package com.example.XyhubEmployee.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.XyhubEmployee.Entity.CompanyLocation;

@Repository
@Transactional
public interface CompanyLocationRepository extends JpaRepository<CompanyLocation, String> {
	
	@Query("select l from CompanyLocation l where l.Id =:locationId")
	CompanyLocation getCompanyLocations(String locationId);
}