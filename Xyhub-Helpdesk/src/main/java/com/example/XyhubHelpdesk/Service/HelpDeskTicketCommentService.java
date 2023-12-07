package com.example.XyhubHelpdesk.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicketComments;



public interface HelpDeskTicketCommentService {

	ApiResponse addTicketComment(String ticketId, HelpDeskTicketComments deskTicketComments);

	ApiResponse removeTicketcomment(String ticketCommentId);
	
	ApiResponse updateTicketComments(String ticketCommentId, HelpDeskTicketComments deskTicketComments);
	
//	ApiResponse getTicketCommentsByTicketId(String ticketId, Pageable pageable);

}
