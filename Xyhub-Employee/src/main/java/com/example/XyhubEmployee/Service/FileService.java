package com.example.XyhubEmployee.Service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.XyhubEmployee.Entity.TicketFile;


public interface FileService  {
	
	Map saveFileToDB(MultipartFile file);
	
	TicketFile getFileByFileName(String fileName);
}