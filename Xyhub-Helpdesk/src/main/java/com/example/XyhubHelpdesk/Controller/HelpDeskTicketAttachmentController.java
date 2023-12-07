package com.example.XyhubHelpdesk.Controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Service.HelpDeskTicketAttachmentService;



@CrossOrigin
@RestController
public class HelpDeskTicketAttachmentController {
		
		@Autowired
		HelpDeskTicketAttachmentService helpDeskTicketAttachmentService;
		
		private final Logger logger = LoggerFactory.getLogger(HelpDeskTicketAttachmentController.class);

//		@PostMapping(value="/addTicketAttachement/{ticketId}")
//		public ApiResponse addTicketAttachement(@RequestPart(name = "files", required = false) MultipartFile[] files,@PathVariable String ticketId) throws Exception {
//			logger.info("inside create Ticket Attachement");
//			return helpDeskTicketAttachmentService.addTicketAttachement(files,ticketId);
//
//		}


		@GetMapping("/getTicketAttachementByTicketId/{ticketId}")
		public ApiResponse getTicketAttachementByTicketId(@PathVariable String ticketId) {
			logger.info("inside get ticket attachement");
			return helpDeskTicketAttachmentService.getTicketAttachementByTicketId(ticketId);
		}
		
		
		@DeleteMapping("/removeTicketAttachment/{ticketId}/{Id}")
		public ApiResponse removeTicketAttachment(@PathVariable String ticketId, @PathVariable String Id) throws Exception {

			logger.info("inside delete Vendor  document");
			return helpDeskTicketAttachmentService.removeTicketAttachment(ticketId, Id);
		}
	

}
