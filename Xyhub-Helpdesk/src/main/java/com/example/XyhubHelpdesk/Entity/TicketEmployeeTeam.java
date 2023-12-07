package com.example.XyhubHelpdesk.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.XyhubCommon.IdGenerator.IdGenerator;
import com.example.XyhubCommon.IdGenerator.IdPrefix;
import com.example.XyhubHelpdesk.basedata.Model.AuditModel;



@Entity
@Table(name = "ticket_employee_team")
public class TicketEmployeeTeam extends AuditModel{

	@Id
	@IdPrefix(value = "HOS")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name="id")
	private String id;
	
	@Column(name = "host_id")
	private String hostId;
	
	@Column(name = "team_member_id")
	private String teamMemberId;

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(String teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
