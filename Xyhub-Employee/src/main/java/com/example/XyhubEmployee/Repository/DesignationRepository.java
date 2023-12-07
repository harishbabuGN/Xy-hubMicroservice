package com.example.XyhubEmployee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.XyhubEmployee.Entity.Designation;

public interface DesignationRepository extends JpaRepository<Designation, String> {

	
	@Query("select e from Designation e where e.Id =:designationName")
	Designation getDesignationNames(String designationName);
}
