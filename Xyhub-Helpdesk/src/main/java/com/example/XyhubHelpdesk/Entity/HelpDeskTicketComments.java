package com.example.XyhubHelpdesk.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.XyhubCommon.IdGenerator.IdGenerator;
import com.example.XyhubCommon.IdGenerator.IdPrefix;
import com.example.XyhubHelpdesk.basedata.Model.AuditModel;


@Entity
@Table(name = "help_desk_ticket_comments")
//@TypeDefs({ @TypeDef(name = "StringJsonObject", typeClass = JSONObjectUserType.class) })
public class HelpDeskTicketComments extends AuditModel {

	@Id
	@IdPrefix(value = "TIC_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name = "ticketComments_id")
	private String Id;

	@Column(name = "ticket_id")
	private String ticketId;

//	@Enumerated(EnumType.STRING)
//	@Column(name = "ticketCommentsStatus")
//	private TicketCommentsStatus status = TicketCommentsStatus.ACTIVE;
//	
	@Column(name = "description")
	private String description;

	@Column(name = "commented_by")
	private String commentedBy;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "commented_on")
	private Date commentedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "updated_on")
	private Date updatedOn;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}

	public Date getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
