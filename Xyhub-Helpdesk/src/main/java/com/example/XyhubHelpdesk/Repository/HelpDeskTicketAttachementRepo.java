package com.example.XyhubHelpdesk.Repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.XyhubHelpdesk.Entity.HelpDeskTicketAttachment;



@Repository
@Transactional
public interface HelpDeskTicketAttachementRepo extends JpaRepository<HelpDeskTicketAttachment, String>{

	@Query(value = "Select *  FROM help_desk_ticket_attachment tc WHERE tc.ticket_id = :ticketId AND tc.ticket_attachment_id = :id", nativeQuery = true)
	HelpDeskTicketAttachment getByAttachementAndId(String ticketId, String id);

	
	@Modifying
	@Transactional
	@Query(value = "DELETE  FROM help_desk_ticket_attachment tc WHERE tc.ticket_id = :ticketId AND tc.ticket_attachment_id = :id", nativeQuery = true)
	Integer deleteAttachement(@Param("ticketId") String ticketId, @Param("id") String id);

	@Query(value = "SELECT * FROM help_desk_ticket_attachment tc WHERE tc.ticket_id = :ticketId ORDER BY tc.created_at DESC", nativeQuery = true)
	List<HelpDeskTicketAttachment> getAttachmentByTicketId(String ticketId);


}
