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
import com.example.XyhubTraining.enumType.TrainingMembersStatus;


@Entity
@Table(name="tr_members")
public class TrainingMembers extends AuditModel {

	@Id
	@IdPrefix(value="TRM_")
	@GeneratedValue(generator=IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy ="com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name="member_id")
	private String id;
	
	@Column(name="emp_id")
	private String employeeId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="memb_status")
	private TrainingMembersStatus memberStatus=TrainingMembersStatus.ACTIVE;
	
	@Column(name="training_id")
	private String trainingId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	

	public String getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public TrainingMembersStatus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(TrainingMembersStatus memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	
}
