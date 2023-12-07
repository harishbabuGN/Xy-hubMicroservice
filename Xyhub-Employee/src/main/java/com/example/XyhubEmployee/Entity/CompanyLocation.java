package com.example.XyhubEmployee.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubCommon.IdGenerator.IdGenerator;
import com.example.XyhubCommon.IdGenerator.IdPrefix;
import com.example.XyhubEmployee.basedata.model.AuditModel;


@Entity
@Table(name = "company_location")
public class CompanyLocation extends AuditModel {

	@Id
	@IdPrefix(value = "LOC")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name = "id")
	private String Id;

	@Column(name = "location_name")
	private String locationName;
	
	@Column(name = "status")
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
