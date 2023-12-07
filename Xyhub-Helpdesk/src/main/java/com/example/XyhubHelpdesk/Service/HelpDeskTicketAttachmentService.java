package com.example.XyhubHelpdesk.Service;


import org.springframework.web.multipart.MultipartFile;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;



public interface HelpDeskTicketAttachmentService {

//	ApiResponse addTicketAttachement(MultipartFile[] files, String ticketId);

	ApiResponse removeTicketAttachment(String ticketId, String id);

	ApiResponse getTicketAttachementByTicketId(String ticketId);

}
