package com.example.XyhubHelpdesk.Controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;
import com.example.XyhubHelpdesk.Service.HelpDeskTicketService;
import com.example.XyhubHelpdesk.enumType.HelpDeskTickectStatus;


@RestController
@CrossOrigin
public class HelpDeskTicketController {
	private final Logger logger = LoggerFactory.getLogger(HelpDeskTicketController.class);
	
	@Autowired
	HelpDeskTicketService helpDeskTicketService;

	@PostMapping("/createHelpDeskTicket")
	public ApiResponse createTicket(@RequestBody HelpDeskTicket healpDeskTicket) 
			throws Exception {
		logger.info("Received request to add tickets");
		return helpDeskTicketService.createTicket(healpDeskTicket);
	}

	@PutMapping("/updateTicketStatus/{ticketId}")
    public ApiResponse updateTicketStatus(@PathVariable String ticketId, @RequestParam HelpDeskTickectStatus newStatus,@RequestParam String message) {
        	return helpDeskTicketService.updateTicketStatus(ticketId, newStatus, message);
           
    }
	
	@PostMapping("/updateHelpDeskTicket/{ticketId}")
	public ApiResponse updateHelpDeskTicket(@RequestBody HelpDeskTicket healpDeskTicket ,@PathVariable String ticketId)
			throws Exception {
		logger.info("Received request to add tickets");
		return helpDeskTicketService.updateTicket(ticketId,healpDeskTicket);
	}
	
	@GetMapping("/getAllTicketHistory")
	public ApiResponse getAllTicketHistory() {
		ApiResponse response = new ApiResponse();
		return helpDeskTicketService.getAllTicketHistory();
	}
	
	@GetMapping("/getTicketHistoryByTicketId/{ticketId}")
	public ApiResponse getTicketHistoryByTicketId(@PathVariable String ticketId) {
		ApiResponse response = new ApiResponse();
		return helpDeskTicketService.getTicketHistoryByTicketId(ticketId);
	}
	
//	@PostMapping("/getAllTickets")
//	public ApiResponse getAllTickets(@RequestBody (required = false)Map<String, Object>filter  ) {
//		return helpDeskTicketService.getAllTickets(filter);
//	}

////	@PostMapping("/downloadAllTickets")
////	public ApiResponse downloadAllTickets(@RequestBody Map<String, Object> filter) throws Exception {
////		return helpDeskTicketService.downloadAllTickets(filter);
////	}
	
	@PutMapping("/reassignTicket/{ticketId}/{memberId}")
	public ApiResponse reassignTicket (@PathVariable String ticketId , @PathVariable String memberId)
			throws Exception {
		logger.info("Received request to add tickets");
		return helpDeskTicketService.reassignTicket(ticketId,memberId);
	}
 
//	@GetMapping("/getTicketByTicketId/{ticketId}")
//	public ApiResponse getTicketByTicketId (@PathVariable String ticketId  )
//			throws Exception {
//		logger.info("Received request to get tickets");
//		return helpDeskTicketService.getTicketByTicketId(ticketId );
//	}
	
	@GetMapping("/getCountOfTicketStatus")
	public  ApiResponse getCountOfTicketStatus() {
	logger.info("Received request to get the status count ");
	return helpDeskTicketService.getCountOfTicketStatus();
}
 
}
