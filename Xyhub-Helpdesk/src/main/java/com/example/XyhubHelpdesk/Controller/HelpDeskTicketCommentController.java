package com.example.XyhubHelpdesk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicketComments;
import com.example.XyhubHelpdesk.Service.HelpDeskTicketCommentService;



@RestController
@CrossOrigin
public class HelpDeskTicketCommentController {
	
	@Autowired
	HelpDeskTicketCommentService helpDeskTicketCommentService;

	@PostMapping("/addTicketComment/{ticketId}")
	public ApiResponse addTicketComment(@PathVariable String ticketId , @RequestBody HelpDeskTicketComments deskTicketComments) {
		ApiResponse response = new ApiResponse();
		
		return helpDeskTicketCommentService.addTicketComment(ticketId,deskTicketComments);
		
		//return response;
	}
	
	@DeleteMapping("/removeTicketcomment/{ticketCommentId}")
	public ApiResponse removeTicketcomment(@PathVariable String ticketCommentId ) {
		ApiResponse response = new ApiResponse();
		
		return helpDeskTicketCommentService.removeTicketcomment(ticketCommentId);
		
		//return response;
	}

	@PutMapping("/updateTicketComments/{ticketCommentId}")
	public ApiResponse updateTicketComments(@PathVariable String ticketCommentId ,@RequestBody HelpDeskTicketComments deskTicketComments) {
		ApiResponse response = new ApiResponse();
		
		return helpDeskTicketCommentService.updateTicketComments(ticketCommentId,deskTicketComments);
		
		//return response;
	}
	
//	@GetMapping("getTicketCommentsByTicketId/{ticketId}")
//	public ApiResponse getTicketCommentsByTicketId(@PathVariable String ticketId,Pageable pageable  ) {
//		return helpDeskTicketCommentService.getTicketCommentsByTicketId(ticketId,pageable);
//
//	}
}
