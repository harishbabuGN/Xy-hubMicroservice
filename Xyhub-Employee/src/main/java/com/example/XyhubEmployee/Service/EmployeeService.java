package com.example.XyhubEmployee.Service;

import com.example.XyhubEmployee.Entity.Employee;
import com.example.XyhubEmployee.apiresponses.ApiResponse;

public interface EmployeeService  {
	
//	Employee addemployee(Employee employee) throws Exception;
	ApiResponse addemployee(Employee employee) throws Exception;

	Employee isEmployeeActive(String userId);

	Employee getbyUserId(String userId);

	
}
