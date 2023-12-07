package com.example.XyhubHelpdesk.Service;

import java.util.Map;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;
import com.example.XyhubHelpdesk.enumType.HelpDeskTickectStatus;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface HelpDeskTicketService {

	ApiResponse createTicket(HelpDeskTicket healpDeskTicket) throws JsonProcessingException;

	ApiResponse updateTicketStatus(String ticketId, HelpDeskTickectStatus newStatus, String message);

	ApiResponse updateTicket(String ticketId, HelpDeskTicket healpDeskTicket);

	ApiResponse getAllTicketHistory();

//	ApiResponse getAllTickets(Map<String, Object> filter);
//
////	ApiResponse downloadAllTickets(Map<String, Object> filter);
//
	ApiResponse reassignTicket(String ticketId ,String memberId);

//	ApiResponse getTicketByTicketId(String ticketId);
//
	ApiResponse getTicketHistoryByTicketId(String ticketId);

	ApiResponse getCountOfTicketStatus();

	
}
