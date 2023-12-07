package com.example.XyhubEmployee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.XyhubEmployee.Entity.TicketFile;

public interface FileRepository extends JpaRepository<TicketFile,String> {
	
	@Query("SELECT  f FROM TicketFile f WHERE f.fileName = :fileName")
	TicketFile getFileByFileName(String fileName);

}