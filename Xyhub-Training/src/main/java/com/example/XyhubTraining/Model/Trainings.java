package com.example.XyhubTraining.Model;

import java.util.Date;

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
import com.example.XyhubTraining.enumType.TrainingSessionStatus;

@Entity
@Table(name="training")
public class Trainings extends AuditModel {
	
	@Id
	@IdPrefix(value = "TRAI_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name="training_id")
	private String id;
	
	@Column(name="training_name")
	private String trainingName;
	
	@Column(name="training_lead")
	private String trainingLead;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;	
	
	@Column(name="type")
	private String type;
	
	@Column(name="training_link")
	private String trainingLink;
	
	@Column(name="session_start_time")
	private String sessionStartTime;
	
	@Column(name="session_end_time")
	private String sessionEndTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private TrainingSessionStatus status=TrainingSessionStatus.INITIATED;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTrainingLink() {
		return trainingLink;
	}

	public void setTrainingLink(String trainingLink) {
		this.trainingLink = trainingLink;
	}

	
	
	public TrainingSessionStatus getStatus() {
		return status;
	}

	public void setStatus(TrainingSessionStatus status) {
		this.status = status;
	}

	public String getTrainingLead() {
		return trainingLead;
	}

	public void setTrainingLead(String trainingLead) {
		this.trainingLead = trainingLead;
	}

	public String getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public String getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	
	
	
	
	

	}
