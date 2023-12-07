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
import com.example.XyhubTraining.enumType.TrainingTopicStatus;


@Entity
@Table(name = "training_topics")
public class TrainingTopics extends AuditModel {
	@Id
	@IdPrefix(value = "TRP")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name = "topic_id")
	private String id;

	@Column(name = "topic_name")
	private String topicName;
	@Column(name = "topic_no")
	private Integer topicNo;

	@Column(name = "training_id")
	private String trainingId;
	
	@Column(name = "topic_desc", length = 500)
	private String topicDesc;

	@Enumerated(EnumType.STRING)
	@Column(name = "topic_status")
	private TrainingTopicStatus status = TrainingTopicStatus.INITIATED;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public Integer getTopicNo() {
		return topicNo;
	}

	public void setTopicNo(Integer topicNo) {
		this.topicNo = topicNo;
	}

	public TrainingTopicStatus getStatus() {
		return status;
	}

	public void setStatus(TrainingTopicStatus status) {
		this.status = status;
	}

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	
	
}
