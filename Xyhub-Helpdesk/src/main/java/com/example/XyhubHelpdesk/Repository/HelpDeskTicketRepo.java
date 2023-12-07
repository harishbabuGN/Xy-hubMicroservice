package com.example.XyhubHelpdesk.Repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;



@EnableJpaRepositories
public interface HelpDeskTicketRepo extends JpaRepository<HelpDeskTicket, String> {

//	@Query("SELECT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority,"
//	        + " hdk.status as status, hdk.title as title, hdk.ticketNo as ticketNo , hdk.createdAt as createdDate , hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, gi.genIssues as genericIssue , gi.Id as issueId, u.name as reportee , a.name as assignee , p.pId as projectId,p.projectName as projectName, hdk.createdBy as createdBy, hdk.lastUpdatedAt as lastUpdatedAt ) FROM HelpDeskTicket hdk "
//	        + "LEFT JOIN User u ON hdk.createdBy = u.id "
//	        + "LEFT JOIN User a ON hdk.assignedTo = a.id "
//	        + "LEFT JOIN Projects p ON hdk.projetId = p.pId "
//	        + "LEFT JOIN GenericIssues gi ON hdk.projetId = gi.Id "
//	        + "WHERE hdk.id = :ticketId ORDER BY hdk.createdAt DESC")
//	Map<String, Object> getTicketByTicketId(String ticketId);

	@Query(value = "SELECT * FROM help_desk_ticket_info po WHERE po.ticket_id = :id ORDER BY po.created_at DESC LIMIT 1", nativeQuery = true)
    HelpDeskTicket findLatestTicket(@Param("id") String id);

    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING(po.ticket_no, LOCATE('-', po.ticket_no) + 1) AS UNSIGNED)), 0) FROM help_desk_ticket_info po", nativeQuery = true)
    Integer getMaxTicketNo();

    @Query(value ="SELECT * FROM help_desk_ticket_info po WHERE po.ticket_id=:ticketId",nativeQuery=true)
	HelpDeskTicket getById(String ticketId);

//	HelpDeskTicket getById(String ticketId);


}
