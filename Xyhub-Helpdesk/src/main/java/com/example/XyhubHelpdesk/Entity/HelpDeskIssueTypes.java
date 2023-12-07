package com.example.XyhubHelpdesk.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubHelpdesk.IdGenerator.IdGenerator;
import com.example.XyhubHelpdesk.IdGenerator.IdPrefix;



@Entity
@Table(name = "helpdesk_issues")
public class HelpDeskIssueTypes {

	@Id
	@IdPrefix(value = "ISI")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
//	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubHelpdesk.IdGenerator.IdGenerator")
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubHelpdesk.IdGenerator.IdGenerator")
	@Column(name = "issueType_Id")
	private String issueTypeId;

	@Column(name = "helpdesk_status")
	private boolean helpdeskStatus;

	@Column(name = "helpdeskIssueType")
	private String helpdeskIssueType;

	public String getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(String issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public boolean isHelpdeskStatus() {
		return helpdeskStatus;
	}

	public void setHelpdeskStatus(boolean helpdeskStatus) {
		this.helpdeskStatus = helpdeskStatus;
	}

	public String getHelpdeskIssueType() {
		return helpdeskIssueType;
	}

	public void setHelpdeskIssueType(String helpdeskIssueType) {
		this.helpdeskIssueType = helpdeskIssueType;
	}

}
