package com.example.XyhubEmployee.Entity;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubEmployee.basedata.model.AuditModel;
import com.example.XyhubEmployee.idgenerator.IdGenerator;
import com.example.XyhubEmployee.idgenerator.IdPrefix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_wings")
public class CompanyWings extends AuditModel {
	

	@Id
	@IdPrefix(value = "WIN_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubEmployee.idgenerator.IdGenerator")
	@Column(name = "wing_id")
	private String Id;

	@Column(name = "wing_name")
	private String wingName;

	@Column(name = "status")
	private boolean status;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getWingName() {
		return wingName;
	}

	public void setWingName(String wingName) {
		this.wingName = wingName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
