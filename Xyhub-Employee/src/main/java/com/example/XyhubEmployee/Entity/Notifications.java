package com.example.XyhubEmployee.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubCommon.IdGenerator.IdGenerator;
import com.example.XyhubCommon.IdGenerator.IdPrefix;
import com.example.XyhubCommon.enumType.NotificationType;
import com.example.XyhubEmployee.basedata.model.AuditModel;



@Entity
@Table(name = "notifications")
public class Notifications extends AuditModel{

	@Id
	@IdPrefix(value = "NOT_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name="notification_id")
	private String notificationId;
	
	@Column(name = "notification_desc" ,length=5000)
	private String notificationDesc;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type")
	private NotificationType notificationType = NotificationType.PROJECT_ASSIGN_ACCCES;
	
	@Column(name = "ticket_id")
	private String ticketId;
	
	@Column(name = "sender_id")
	private String senderId;
	
	@Column(name = "receiver_id")
	private String receiverId;
	
	@Column(name = "seen_status")
	private boolean seenStatus;
	
	@Column(name = "seen_on")
	private String seen_On;

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public boolean isSeenStatus() {
		return seenStatus;
	}

	public void setSeenStatus(boolean seenStatus) {
		this.seenStatus = seenStatus;
	}

	public String getSeen_On() {
		return seen_On;
	}

	public void setSeen_On(String seen_On) {
		this.seen_On = seen_On;
	}
}