package com.example.XyhubEmployee.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubEmployee.basedata.model.AuditModel;
import com.example.XyhubEmployee.enumType.SalaryStatus;
import com.example.XyhubEmployee.idgenerator.IdGenerator;
import com.example.XyhubEmployee.idgenerator.IdPrefix;


@Entity
@Table(name = "employee_salary")
public class EmployeeSalary extends AuditModel{
	
	@Id
	@IdPrefix(value = "EMP_SAL_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubEmployee.idgenerator.IdGenerator")
	@Column(name = "emp_sal_id")
	private String empSalId;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "emp_gross_sal")
	private String grossSalary;
	
	@Column(name = "emp_net_sal")
	private String netSalary;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sal_status")
	private SalaryStatus salStatus = SalaryStatus.ACTIVE;
	
	@Column(name = "tds")
	private String tds;

	public String getEmpSalId() {
		return empSalId;
	}

	public void setEmpSalId(String empSalId) {
		this.empSalId = empSalId;
	}
	
	

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(String netSalary) {
		this.netSalary = netSalary;
	}

	public SalaryStatus getSalStatus() {
		return salStatus;
	}

	public void setSalStatus(SalaryStatus salStatus) {
		this.salStatus = salStatus;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}
	
	

	
	
	
	
	
	

}
