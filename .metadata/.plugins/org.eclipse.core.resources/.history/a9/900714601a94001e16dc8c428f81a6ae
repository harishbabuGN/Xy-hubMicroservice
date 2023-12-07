package com.example.XyhubHelpdesk.Repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicketComments;



public interface HelpDeskTicketCommentRepo extends JpaRepository<HelpDeskTicketComments, String>{

//	@Query("SELECT DISTINCT NEW map(h.name as commentedByName, c.description as description, c.Id as Id , c.ticketId as ticketId , c.createdBy as createdBy, c.commentedOn as commentedOn) " +
//		       "FROM HelpDeskTicketComments c " +
//		       "LEFT JOIN User h ON c.commentedBy = h.id " +
//		       "WHERE c.ticketId = :ticketId ORDER BY c.commentedOn DESC ")
//		Page<Map<String, Object>> getTicketCommentsByTicketId(@Param("ticketId") String ticketId, Pageable pageable);
//	
	@Query("Select c from  HelpDeskTicketComments c where c.Id =:ticketCommentId")
	String getCommentId(String ticketCommentId);
	
	
    @Query(value ="SELECT * FROM help_desk_ticket_comments c WHERE c.ticket_comments_id =:ticketCommentId",nativeQuery=true)
	HelpDeskTicketComments getById(String ticketCommentId);
	}
