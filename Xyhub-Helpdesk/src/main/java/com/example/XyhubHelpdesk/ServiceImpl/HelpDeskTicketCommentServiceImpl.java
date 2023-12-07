package com.example.XyhubHelpdesk.ServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Config.RabbitMqConfig;
import com.example.XyhubHelpdesk.Entity.CurrentUser;
import com.example.XyhubHelpdesk.Entity.HelpDeskHistory;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicketComments;
import com.example.XyhubHelpdesk.HelpdeskVo.HelpdeskVo;
import com.example.XyhubHelpdesk.Repository.HelpDeskHistoryRepository;
import com.example.XyhubHelpdesk.Repository.HelpDeskTicketCommentRepo;
//import com.example.XyhubHelpdesk.Repository.HelpDeskTicketCommentRepo;
import com.example.XyhubHelpdesk.Repository.HelpDeskTicketRepo;
import com.example.XyhubHelpdesk.Service.HelpDeskTicketCommentService;
import com.example.XyhubHelpdesk.constants.EmployeeClient;
import com.example.XyhubHelpdesk.restclient.restEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class HelpDeskTicketCommentServiceImpl implements HelpDeskTicketCommentService {

	@Autowired
	CurrentUser currentUser;

	@Autowired
	HelpDeskTicketCommentRepo helpDeskTicketCommentRepo;

	@Autowired
	HelpDeskHistoryRepository helpDeskHistoryRepository;
	
//	@Autowired
//	EmployeeRepository employeeRepository;

//	@Autowired
//	EmailService emailService;
	
	@Autowired
	HelpDeskTicketRepo healpDeskTicketRepo;
	
	@Autowired
	restEmployee restEmployee;
	
//	@Autowired
//	RabbitTemplate rabbitTemplate;

	@Override
	public ApiResponse addTicketComment(String ticketId, HelpDeskTicketComments deskTicketComments) {

		ApiResponse response = new ApiResponse();
		response = isValidDescription(deskTicketComments);
		HelpDeskTicket helpDeskTicket  =   healpDeskTicketRepo.getById(ticketId);
		if(helpDeskTicket == null || helpDeskTicket.toString().isEmpty()) {
			response.setSuccess(true);
			response.setMessage("Comments added Succesfully");
			return response;
		}
		if (response.isSuccess()) {
			HelpDeskTicketComments ticketComments = new HelpDeskTicketComments();
			ticketComments.setTicketId(ticketId);
//			ticketComments.setCommentedBy(currentUser.getUserId());
			ticketComments.setCreatedAt(new Date());
//			ticketComments.setCreatedBy(currentUser.getUserId());
			ticketComments.setCommentedOn(new Date());
			ticketComments.setDescription(deskTicketComments.getDescription());

			if (helpDeskTicketCommentRepo.save(ticketComments) != null) {
				HelpDeskHistory history = new HelpDeskHistory();
				history.setCreatedAt(new Date());
				history.setCreatedBy(currentUser.getFirstName());
				history.setMessage(currentUser.getFirstName() + " commented on " + ticketId);
				history.setTicketId(ticketId);
					
					if (helpDeskHistoryRepository.save(history) != null) {
						EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(helpDeskTicket.getAssignedTo());
//						EmployeeClient ticketCreatedEmployee = restEmployee.isEmployeeActive(currentUser.getUserId());
						
						String subject =  "Added comments to the ticket\n\n "  ;
						String message = String.format("Hello %s,\n\n", assignedEmployee.getFirstName() + " "+ assignedEmployee.getLastName())
								+ "Greetings from Xyram Software Solutions..!\n"
//								+ ticketCreatedEmployee.getFirstName() + "" + ticketCreatedEmployee.getLastName() + " as been commented on the ticket. check below for the further information"  
//								+ "Title : "+helpDeskTicket.getTitle() + "\n" + "Description : " + helpDeskTicket.getTicketDescription() + "\n"
//								+ "Assigned By : " +ticketCreatedEmployee.getFirstName() + " " + ticketCreatedEmployee.getLastName() + "\n" + "Priority : "
								+ helpDeskTicket.getPriority() + "\n" + "Project/Non project : " + helpDeskTicket.getType() + "\n\n"
								;
						ObjectMapper objectMapper = new ObjectMapper();
						HelpdeskVo helpdeskVo = new HelpdeskVo();
						helpdeskVo.setAssignedTo(helpDeskTicket.getAssignedTo());
						helpdeskVo.setCreatedBy(helpDeskTicket.getCreatedBy());
						helpdeskVo.setMessage(message);
						helpdeskVo.setSubject(subject);

						String jsonData = null;
						try {
							jsonData = objectMapper.writeValueAsString(helpdeskVo);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

//						rabbitTemplate.convertAndSend(RabbitMqConfig.HELPDESK_EXCHANGE, RabbitMqConfig.HELPDESK_ROUTING_KEY,
//								jsonData);
					response.setSuccess(true);
					response.setMessage("Comments added Succesfully");
					return response;
				} else {
					response.setSuccess(false);
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Comments have not been added.");
					}
			} else {
				response.setSuccess(false);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Comments have not been added.");
				}

		}
		return response;
	}

	private ApiResponse isValidDescription(HelpDeskTicketComments deskTicketComments) {
		ApiResponse response = new ApiResponse(false);

		if (deskTicketComments.getDescription().isEmpty()) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description is Mandatory.");
			}

		if (deskTicketComments.getDescription().trim().isEmpty()) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description should not be empty or consist of only spaces.");
			}

		if (deskTicketComments.getDescription().length() < 5 || deskTicketComments.getDescription().length() > 1000) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description must be between 5 and 1000 characters.");
			}

//	    String validation = "[!@#\\$%^&*()_+=\\-\\[\\]{}|\\\\:;\"<>,.?/~]";
//	    if (!deskTicketComments.getDescription().matches(validation)) {
//	        response.setSuccess(false); 
//	        response.setMessage("Description contains invalid characters.");
//	        return response;
//	    }

		response.setSuccess(true);
		return response;
	}

	@Override
	public ApiResponse removeTicketcomment(String ticketCommentId) {
		ApiResponse response = new ApiResponse();
		HelpDeskTicketComments ticketComments = helpDeskTicketCommentRepo.getById(ticketCommentId);
		if (ticketComments != null) {
			HelpDeskTicket helpDeskTicket = healpDeskTicketRepo.getById(ticketComments.getTicketId());
		
//			if (ticketComments.getCreatedBy().equals(currentUser.getUserId())) {
				helpDeskTicketCommentRepo.delete(ticketComments);

				HelpDeskHistory history = new HelpDeskHistory();
				history.setCreatedAt(new Date());
				history.setCreatedBy(currentUser.getFirstName());
				history.setMessage(currentUser.getFirstName() + " removed ticket comment ");
				history.setTicketId(ticketComments.getTicketId());

				if (helpDeskHistoryRepository.save(history) != null) {
					EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(helpDeskTicket.getAssignedTo());
//					Employee ticketCreatedEmployee = restEmployee.isEmployeeActive(currentUser.getUserId());
					
					String subject =  "Removed ticket comment\n\n "  ;
					String message = String.format("Hello %s,\n\n", assignedEmployee.getFirstName() + " "+ assignedEmployee.getLastName())
							+ "Greetings from Xyram Software Solutions..!\n\n"
//							+ ticketCreatedEmployee.getFirstName() + "" + ticketCreatedEmployee.getLastName() + " as removed the ticket comments :" + ticketComments.getDescription()  
							
							;
					ObjectMapper objectMapper = new ObjectMapper();
					HelpdeskVo helpdeskVo = new HelpdeskVo();
					helpdeskVo.setAssignedTo(helpDeskTicket.getAssignedTo());
					helpdeskVo.setCreatedBy(helpDeskTicket.getCreatedBy());
					helpdeskVo.setMessage(message);
					helpdeskVo.setSubject(subject);

					String jsonData = null;
					try {
						jsonData = objectMapper.writeValueAsString(helpdeskVo);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}

//					rabbitTemplate.convertAndSend(RabbitMqConfig.HELPDESK_EXCHANGE, RabbitMqConfig.HELPDESK_ROUTING_KEY,
//							jsonData);
					response.setSuccess(true);
					response.setMessage("Comments deleted Succesfully");
					return response;
				} else {
					response.setSuccess(false);
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to save history");
					}
			} else {
				response.setSuccess(false);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Only those who have made a post can delete their comments.");
				}
//		}else {
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Comments are not found with this Id :" +ticketCommentId);
//      } 
		
     }

	@Override
	public ApiResponse updateTicketComments(String ticketCommentId, HelpDeskTicketComments deskTicketComments) {
		ApiResponse response = new ApiResponse();
		HelpDeskTicketComments ticketComments = helpDeskTicketCommentRepo.getById(ticketCommentId);
		HelpDeskTicket helpDeskTicket = healpDeskTicketRepo.getById(deskTicketComments.getTicketId());
		if (ticketComments != null) {
//			if (currentUser.getUserId().equals(ticketComments.getCommentedBy())) {
				ticketComments.setTicketId(deskTicketComments.getTicketId());
				ticketComments.setCommentedBy(ticketComments.getCommentedBy());
				ticketComments.setCreatedAt(ticketComments.getCreatedAt());
				ticketComments.setUpdatedBy(currentUser.getUserId());
				ticketComments.setCommentedBy(ticketComments.getCommentedBy());
				ticketComments.setCreatedAt(ticketComments.getCreatedAt());
				ticketComments.setCreatedBy(ticketComments.getCreatedBy());
				ticketComments.setUpdatedOn(new Date());
				ticketComments.setCreatedBy(ticketComments.getCreatedBy());
				ticketComments.setLastUpdatedAt(new Date());
				ticketComments.setCommentedOn(ticketComments.getCommentedOn());
				ticketComments.setDescription(deskTicketComments.getDescription());
				if (helpDeskTicketCommentRepo.save(ticketComments) != null) {
					HelpDeskHistory history = new HelpDeskHistory();
					history.setCreatedAt(new Date());
					history.setCreatedBy(currentUser.getFirstName());
					history.setMessage(currentUser.getFirstName() + " updated ticket comment to ");
					history.setTicketId(deskTicketComments.getTicketId());
					if (helpDeskHistoryRepository.save(history) != null) {
						
						EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(helpDeskTicket.getAssignedTo());
//						Employee ticketCreatedEmployee = restEmployee.isEmployeeActive(currentUser.getUserId());
						
						String subject =  "Updated ticket comment\n\n "  ;
						String message = String.format("Hi %s,\n", assignedEmployee.getFirstName() + " "+ assignedEmployee.getLastName())
								+ "Greetings from Xyram Software Solutions..!\n\n"
//								+ ticketCreatedEmployee.getFirstName() + "" + ticketCreatedEmployee.getLastName() + " as updated the ticket comment :" + deskTicketComments.getDescription()  
								
								;
						ObjectMapper objectMapper = new ObjectMapper();
						HelpdeskVo helpdeskVo = new HelpdeskVo();
						helpdeskVo.setAssignedTo(helpDeskTicket.getAssignedTo());
						helpdeskVo.setCreatedBy(helpDeskTicket.getCreatedBy());
						helpdeskVo.setMessage(message);
						helpdeskVo.setSubject(subject);

						String jsonData = null;
						try {
							jsonData = objectMapper.writeValueAsString(helpdeskVo);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

//						rabbitTemplate.convertAndSend(RabbitMqConfig.HELPDESK_EXCHANGE, RabbitMqConfig.HELPDESK_ROUTING_KEY,
//								jsonData);
						response.setSuccess(true);
						response.setMessage("Ticket Comments updated Succesfully.");
						return response;
					} else {
						response.setSuccess(false);
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ticket History not added");
							}
			} else {
				response.setSuccess(false);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ticket Comments not added");
				}
		}else {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You are not authorized to update this comment");
			}
//		}else {
//				response.setSuccess(false);
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ticket comments are not present with this ID :"+ticketCommentId);
//			}
//		
	}

//	@Override
//	public ApiResponse getTicketCommentsByTicketId(String ticketId , Pageable pageable) {
//		ApiResponse response = new ApiResponse();
//		Page<Map<String, Object>> ticketComments = helpDeskTicketCommentRepo.getTicketCommentsByTicketId(ticketId , pageable);
//		
//		if (ticketComments != null && !ticketComments.isEmpty()) {
//			Map content = new HashMap();
//			content.put("ticketComments", ticketComments);
//			response.setMessage("Ticket Retrived Successfully");
//			response.setSuccess(true);
//			response.setContent(content);
//			return response;
//		} else {
//			Map content = new HashMap();
//			content.put("ticketComments", ticketComments);
//			response.setSuccess(false);
//			response.setMessage("Ticket comments with this ticket ID are not available.");
//			response.setContent(content);
//			return response;
//			}
//	
//	}
//	
//	private void sendTicketNotification(HelpDeskTicket helpDeskTicket , String message ,String subject) {
//		Employee assignedEmployee = employeeRepository.isEmployeeActive(helpDeskTicket.getAssignedTo());
//		Employee ticketCreatedEmployee = employeeRepository.isEmployeeActive(helpDeskTicket.getCreatedBy());
//
//		// Create a set of mail details specific to this user
//		Map<Object, Object> mailDetails = new HashMap<>();
//		mailDetails.put("toEmail", assignedEmployee.getEmail());
//		mailDetails.put("subject", subject);
//		mailDetails.put("message", message);
//
//		// Send the email to this user
//		emailService.sendMail(mailDetails);
//	}

}
