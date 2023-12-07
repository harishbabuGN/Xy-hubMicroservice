package com.example.XyhubHelpdesk.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import com.example.XyhubCommon.IdGenerator.IdGenerator;
import com.example.XyhubCommon.IdGenerator.IdPrefix;
import com.example.XyhubHelpdesk.basedata.Model.AuditModel;
import com.example.XyhubHelpdesk.enumType.TicketStatus;

@Entity
@Table(name = "ticket_history")
public class HelpDeskHistory extends AuditModel {

	@Id
	@IdPrefix(value = "TICS_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name = "id")
	private String id;

	@Column(name = "ticket_id")
	private String ticketId;

	@Enumerated(EnumType.STRING)
	@Column(name = "ticket_Status")
	private TicketStatus ticketStatus = TicketStatus.OPEN;

	@Column(name = "message")
	private String message;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

//	public TicketStatus getTicketStatus() {
//		return ticketStatus;
//	}
//
//	public void setTicketStatus(TicketStatus ticketStatus) {
//		this.ticketStatus = ticketStatus;
//	}

}
