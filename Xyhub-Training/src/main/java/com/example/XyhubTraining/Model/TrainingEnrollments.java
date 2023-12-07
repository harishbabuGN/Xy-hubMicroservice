package com.example.XyhubTraining.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubCommon.BaseModel.AuditModel;
import com.example.XyhubCommon.IdGenerator.IdGenerator;
import com.example.XyhubCommon.IdGenerator.IdPrefix;
import com.example.XyhubTraining.enumType.EnrollmentStatus;
import com.example.XyhubTraining.enumType.TrainingEnrollmentProficiency;


@Entity
@Table(name="trai_enroll")
public class TrainingEnrollments extends AuditModel{
	
	@Id
	@IdPrefix(value = "TRAE_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name="enroll_id")
	private String id;
	@Column(name = "enroll_email")
	private String email;
	
	@Column(name = "enroll_official_email", nullable = false)
	private String officialEmail;
	
	@Column(name="enroll_name")
	private String enrollName;
	
	@Column(name="enroll_mode")
	private String enrollMode;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enroll_proficiency")
	private TrainingEnrollmentProficiency enrollProficiency;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enroll_status")
	private EnrollmentStatus enrollStatus=EnrollmentStatus.PENDING;
	
	
	@Column(name="enroll_description",length=500)
	private String enrollDescription;

	@Column(name="training_id")
	private String trainingId;
	
	@Column(name="employee_id")
	private String employeeId;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getOfficialEmail() {
		return officialEmail;
	}


	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}


	public String getEnrollName() {
		return enrollName;
	}


	public void setEnrollName(String enrollName) {
		this.enrollName = enrollName;
	}


	public String getEnrollMode() {
		return enrollMode;
	}


	public void setEnrollMode(String enrollMode) {
		this.enrollMode = enrollMode;
	}


	public TrainingEnrollmentProficiency getEnrollProficiency() {
		return enrollProficiency;
	}


	public void setEnrollProficiency(TrainingEnrollmentProficiency enrollProficiency) {
		this.enrollProficiency = enrollProficiency;
	}


	public String getEnrollDescription() {
		return enrollDescription;
	}


	public void setEnrollDescription(String enrollDescription) {
		this.enrollDescription = enrollDescription;
	}


	public EnrollmentStatus getEnrollStatus() {
		return enrollStatus;
	}


	public void setEnrollStatus(EnrollmentStatus enrollStatus) {
		this.enrollStatus = enrollStatus;
	}


	public String getTrainingId() {
		return trainingId;
	}


	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	

}
