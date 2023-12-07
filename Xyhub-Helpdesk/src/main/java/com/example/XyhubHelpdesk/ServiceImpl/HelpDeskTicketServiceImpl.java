package com.example.XyhubHelpdesk.ServiceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Config.RabbitMqConfig;
import com.example.XyhubHelpdesk.Entity.CurrentUser;
import com.example.XyhubHelpdesk.Entity.HelpDeskHistory;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;
import com.example.XyhubHelpdesk.HelpdeskVo.HelpdeskVo;
import com.example.XyhubHelpdesk.Repository.HelpDeskHistoryRepository;
//import com.example.XyhubHelpdesk.Repository.HelpDeskTicketCommentRepo;
import com.example.XyhubHelpdesk.Repository.HelpDeskTicketRepo;
import com.example.XyhubHelpdesk.Service.HelpDeskTicketService;
import com.example.XyhubHelpdesk.constants.EmployeeClient;
import com.example.XyhubHelpdesk.enumType.HelpDeskTickectStatus;
import com.example.XyhubHelpdesk.restclient.restEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HelpDeskTicketServiceImpl implements HelpDeskTicketService {

	@Autowired
	CurrentUser currentUser;

//	@Autowired
//	EmployeeRepository employeeRepository;
	
	@Autowired
	restEmployee restEmployee;

//	@Autowired
//	EmailService emailService;

//	@Autowired
//	HelpDeskTicketCommentRepo helpDeskTicketCommentRepo;

	@Autowired
	HelpDeskTicketRepo healpDeskTicketRepo;

	@Autowired
	HelpDeskHistoryRepository helpDeskHistoryRepository;

//	@Autowired
//	ProjectRepository projectRepository;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Override
	public ApiResponse createTicket(HelpDeskTicket helpDeskTicket) throws JsonProcessingException {
		ApiResponse response = new ApiResponse();

		response = validateTicket(helpDeskTicket);
		if (response.isSuccess()) {
//			if (employeeRepository.isEmployeeActive(currentUser.getUserId()) == null) {
//				response.setSuccess(false);
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not exists Or Inactive");
//			}

			HelpDeskTicket deskTicket = new HelpDeskTicket();
			deskTicket.setAssignedTo(helpDeskTicket.getAssignedTo().trim());
			deskTicket.setPriority(helpDeskTicket.getPriority());
			deskTicket.setTitle(helpDeskTicket.getTitle().trim());
			deskTicket.setStatus(helpDeskTicket.getStatus());
			deskTicket.setTicketDescription(helpDeskTicket.getTicketDescription().trim());
			deskTicket.setCreatedBy(currentUser.getUserId());
			deskTicket.setType(helpDeskTicket.getType().trim());
			deskTicket.setProjetId(helpDeskTicket.getProjetId().trim());
			deskTicket.setCreatedAt(new Date());

//			Integer maxPoNumber = helpDeskHistoryRepository.getMaxTicketNumber();
//			int nextPoNumber = (maxPoNumber != null) ? maxPoNumber + 1 : 1;
//
//			Long latestTicket = helpDeskHistoryRepository.findMaxTicketNumber();
//
//			if (latestTicket != null && latestTicket.getTicketNo() != null) {
//			    String latestTicketNumber = latestTicket.getTicketNo();
//			    // Extract numeric part from the latest ticket number
//			    String numericPart = latestTicketNumber.substring(latestTicketNumber.lastIndexOf('-') + 1);
//			    // Increment the numeric part and create the new ticket number
//			    nextPoNumber = Integer.parseInt(numericPart) + 1;
//			}
//
//			String ticketNo = "Ticket_Id-" + nextPoNumber;
//			deskTicket.setTicketNo(ticketNo);

			HelpDeskTicket latestHelpDeskTicket = healpDeskTicketRepo.findLatestTicket(helpDeskTicket.getId());
			Integer maxTickNo = healpDeskTicketRepo.getMaxTicketNo();
			int nextTickNo = (maxTickNo != null) ? maxTickNo + 1 : 1;
			String ticketNo = "Ticket_No-" + nextTickNo;

			if (latestHelpDeskTicket != null && latestHelpDeskTicket.getTicketNo() != null) {
			    String latestTicketNo = latestHelpDeskTicket.getTicketNo();
			    String numericPart = latestTicketNo.substring(latestTicketNo.lastIndexOf('-') + 1);
			    nextTickNo = Integer.parseInt(numericPart) + 1;
			    ticketNo = "Ticket_No-" + nextTickNo;
			}

			deskTicket.setTicketNo(ticketNo);
			HelpDeskTicket savedDeskTicket = healpDeskTicketRepo.save(deskTicket);
			if (savedDeskTicket != null) {
				HelpDeskHistory history = new HelpDeskHistory();
				history.setCreatedAt(new Date());
				history.setCreatedBy(currentUser.getUserId());
//				history.setMessage("Ticket created by " + currentUser.getFirstName());
				history.setTicketId(deskTicket.getId());
				helpDeskHistoryRepository.save(history);
				if (helpDeskHistoryRepository.save(history) != null) {
					EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(savedDeskTicket.getAssignedTo());
//					EmployeeClient ticketCreatedEmployee = restEmployee.isEmployeeActive(currentUser.getUserId());
					String subject = "New Ticket Assigned: " + savedDeskTicket.getTitle();
					String message = String.format("Hi %s,\n",
							assignedEmployee.getFirstName() + " " + assignedEmployee.getLastName())
							+ "Greetings from Xyram Software Solutions..!\n\n"
							+ "New ticket has been assigned to you. Please find the below ticket information.\n"
							+ "Title : " + savedDeskTicket.getTitle() + "\n" + "Description : "
							+ savedDeskTicket.getTicketDescription() + "\n" + "Assigned By : "
//							+ ticketCreatedEmployee.getFirstName() + " " + ticketCreatedEmployee.getLastName() + "\n"
							+ "Priority : " + savedDeskTicket.getPriority() + "\n" + "Project/Non project : "
							+ savedDeskTicket.getType() + "\n\n";

					ObjectMapper objectMapper = new ObjectMapper();
					HelpdeskVo helpdeskVo = new HelpdeskVo();
					helpdeskVo.setAssignedTo(savedDeskTicket.getAssignedTo());
					helpdeskVo.setCreatedBy(savedDeskTicket.getCreatedBy());
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
					response.setMessage("Ticket created successfully");
					return response;
				}
			} else {
				response.setSuccess(false);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket Not Created");
			}
		}
		return response;
	}

	@Override
	public ApiResponse updateTicketStatus(String ticketId, HelpDeskTickectStatus newStatus, String message) {
		ApiResponse response = new ApiResponse();
//		if (restEmployee.isEmployeeActive(currentUser.getUserId()) == null) {
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not exists Or Inactive");
//		}
		HelpDeskTicket deskTicket = healpDeskTicketRepo.getById(ticketId);
		if (deskTicket == null) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Ticket is not present with this id :" + deskTicket);
		}

		HelpDeskTickectStatus previousStatus = deskTicket.getStatus();

//		if (isStatusChangeValid(deskTicket, newStatus)) {
			deskTicket.setStatus(newStatus);
//			deskTicket.setUpdatedBy(currentUser.getUserId());
			deskTicket.setLastUpdatedAt(new Date());
			HelpDeskTicket savedDeskTicket = healpDeskTicketRepo.save(deskTicket);
			if (savedDeskTicket != null) {

				HelpDeskHistory history = new HelpDeskHistory();
				history.setCreatedAt(new Date());
//				history.setCreatedBy(currentUser.getFirstName());
//				history.setMessage(currentUser.getFirstName() + " changed ticket status from " + previousStatus + " to "
//						+ newStatus);
				history.setTicketId(ticketId);
				HelpDeskHistory saveHistory = helpDeskHistoryRepository.save(history);

				if (saveHistory != null) {
					EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(savedDeskTicket.getAssignedTo());
					EmployeeClient ticketCreatedEmployee = restEmployee
							.isEmployeeActive(savedDeskTicket.getCreatedBy());

					String subject = "Ticket Status changed. ";
					String mailMessage = String.format("Hi %s,\n",
							assignedEmployee.getFirstName() + " " + assignedEmployee.getLastName())
							+ "Greetings from Xyram Software Solutions..!\n\n"
							+ "The ticket status has been changed from : " + previousStatus + " to "
							+ savedDeskTicket.getStatus() + "\n"
							+ "For further information about the ticket, please check the description below." + "\n"
							+ "Title :" + savedDeskTicket.getTitle() + "\n" + "Description : "
							+ savedDeskTicket.getTicketDescription() + "\n" + "Assigned By : "
							+ ticketCreatedEmployee.getFirstName() + " " + ticketCreatedEmployee.getLastName() + "\n"
							+ "Priority : " + savedDeskTicket.getPriority() + "\n" + "Project/Non project : "
							+ savedDeskTicket.getType() + "\n\n";
					ObjectMapper objectMapper = new ObjectMapper();
					HelpdeskVo helpdeskVo = new HelpdeskVo();
					helpdeskVo.setAssignedTo(savedDeskTicket.getAssignedTo());
					helpdeskVo.setCreatedBy(savedDeskTicket.getCreatedBy());
					helpdeskVo.setMessage(mailMessage);
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
					response.setMessage("Ticket status updated successfully");
					return response;
				} else {
					response.setSuccess(false);
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket History Not Saved");
				}
			} else {
				response.setSuccess(false);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket Not Saved");
			}
//		} else {
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Ticket Status");
//		}

	}

	private boolean isStatusChangeValid(HelpDeskTicket deskTicket, HelpDeskTickectStatus newStatus) {
		if (currentUser.getUserId().equals(deskTicket.getCreatedBy())
				|| currentUser.getUserId().equals(deskTicket.getAssignedTo())) {
			if (deskTicket.getStatus() == HelpDeskTickectStatus.OPEN) {
				return (currentUser.getUserId().equals(deskTicket.getCreatedBy())
						&& newStatus == HelpDeskTickectStatus.CANCELLED)
						|| (currentUser.getUserId().equals(deskTicket.getAssignedTo())
								&& (newStatus == HelpDeskTickectStatus.INPROGRESS
										|| newStatus == HelpDeskTickectStatus.COMPLETED));
			} else if (deskTicket.getStatus() == HelpDeskTickectStatus.INPROGRESS) {
				return (currentUser.getUserId().equals(deskTicket.getCreatedBy())
						&& newStatus == HelpDeskTickectStatus.CANCELLED)
						|| (currentUser.getUserId().equals(deskTicket.getAssignedTo())
								&& newStatus == HelpDeskTickectStatus.COMPLETED);
			} else if (deskTicket.getStatus() == HelpDeskTickectStatus.COMPLETED) {
				return (currentUser.getUserId().equals(deskTicket.getCreatedBy())
						&& (newStatus == HelpDeskTickectStatus.RESOLVED || newStatus == HelpDeskTickectStatus.REOPEN));
			} else if (deskTicket.getStatus() == HelpDeskTickectStatus.REOPEN) {
				return (currentUser.getUserId().equals(deskTicket.getCreatedBy())
						&& newStatus == HelpDeskTickectStatus.CANCELLED)
						|| (currentUser.getUserId().equals(deskTicket.getAssignedTo())
								&& (newStatus == HelpDeskTickectStatus.INPROGRESS
										|| newStatus == HelpDeskTickectStatus.COMPLETED));
			}
		}
		return false;
	}

	@Override
	public ApiResponse updateTicket(String ticketId, HelpDeskTicket healpDeskTicket) {
		ApiResponse response = new ApiResponse();

//		if (restEmployee.isEmployeeActive(currentUser.getUserId()) == null) {
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not exists Or Inactive");
//		}
//		if ((restEmployee.isEmployeeActive(healpDeskTicket.getAssignedTo()) == null)) {
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//					"Ticket Assigned Employee not exists Or Inactive");
//		}

		HelpDeskTicket deskTicket = healpDeskTicketRepo.getById(ticketId);

		if (deskTicket == null) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket is not Present with this id.");
		}
		deskTicket.setAssignedTo(healpDeskTicket.getAssignedTo());
		deskTicket.setCreatedAt(healpDeskTicket.getCreatedAt());
		deskTicket.setPriority(healpDeskTicket.getPriority());
		deskTicket.setTicketDescription(healpDeskTicket.getTicketDescription());
		deskTicket.setStatus(healpDeskTicket.getStatus());
		deskTicket.setLastUpdatedAt(new Date());
//		deskTicket.setUpdatedBy(currentUser.getUserId());

		HelpDeskTicket savedDeskTicket = healpDeskTicketRepo.save(deskTicket);

		if (savedDeskTicket != null) {
			HelpDeskHistory history = new HelpDeskHistory();
			history.setCreatedAt(new Date());
//			history.setCreatedBy(currentUser.getFirstName());
//			history.setMessage(currentUser.getFirstName() + " update a ticket ");
			history.setTicketId(ticketId);

			if (helpDeskHistoryRepository.save(history) != null) {
				EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(savedDeskTicket.getAssignedTo());
				EmployeeClient ticketCreatedEmployee = restEmployee.isEmployeeActive(savedDeskTicket.getCreatedBy());

				String subject = "Ticket has been updated. ";
				String mailMessage = String.format("Hi %s,\n",
						assignedEmployee.getFirstName() + " " + assignedEmployee.getLastName())
						+ "Greetings from Xyram Software Solutions..!\n\n"
						+ "The ticket has been updated. Please find the ticket information below.\n" + "Title : "
						+ savedDeskTicket.getTitle() + "\n" + "Description : " + savedDeskTicket.getTicketDescription()
						+ "\n" + "Assigned By : " + ticketCreatedEmployee.getFirstName() + " "
						+ ticketCreatedEmployee.getLastName() + "\n" + "Priority : " + savedDeskTicket.getPriority()
						+ "\n" + "Project/Non project : " + savedDeskTicket.getType() + "\n\n";

				ObjectMapper objectMapper = new ObjectMapper();
				HelpdeskVo helpdeskVo = new HelpdeskVo();
				helpdeskVo.setAssignedTo(savedDeskTicket.getAssignedTo());
				helpdeskVo.setCreatedBy(savedDeskTicket.getCreatedBy());
				helpdeskVo.setMessage(mailMessage);
				helpdeskVo.setSubject(subject);

				String jsonData = null;
				try {
					jsonData = objectMapper.writeValueAsString(helpdeskVo);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

//				rabbitTemplate.convertAndSend(RabbitMqConfig.HELPDESK_EXCHANGE, RabbitMqConfig.HELPDESK_ROUTING_KEY,
//						jsonData);
				response.setSuccess(true);
				response.setMessage("Ticket edited Successfully.");
				return response;
			}
		} else {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket not edited Successfully.");
		}

		return response;
	}

	@Override
	public ApiResponse getAllTicketHistory() {
		ApiResponse response = new ApiResponse();
		List<Map> historyList = null;
		historyList = helpDeskHistoryRepository.getAllTicketHistory();
		if (historyList != null) {
			Map content = new HashMap();
			content.put("ticketHistoryList", historyList);
			response.setMessage("TicketHistory is Retrived successfully");
			response.setSuccess(true);
			response.setContent(content);
			return response;
		} else {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Retrieve Ticket History.");
		}

	}

//	@Override
//	public ApiResponse getAllTickets(Map<String, Object> filter) {
//		ApiResponse response = new ApiResponse(false);
//
//		String toDateStr = filter.containsKey("toDate") ? filter.get("toDate") != null
//				? !((String) filter.get("toDate")).trim().equals("") ? ((String) filter.get("toDate")) : null
//				: null : null;
//		String fromDateStr = filter.containsKey("fromDate") ? filter.get("fromDate") != null
//				? !((String) filter.get("fromDate")).trim().equals("") ? ((String) filter.get("fromDate")) : null
//				: null : null;
//		String searchString = filter.containsKey("searchString") ? filter.get("searchString") != null
//				? !((String) filter.get("searchString")).trim().equals("") ? ((String) filter.get("searchString"))
//						: null
//				: null : null;
//
//		String priority = filter.containsKey("priority") ? filter.get("priority") != null
//				? !((String) filter.get("priority")).trim().equals("") ? ((String) filter.get("priority")) : null
//				: null : null;
//
//		String ticketStatus = filter.containsKey("status") ? filter.get("status") != null
//				? !((String) filter.get("status")).equals("") ? ((String) filter.get("status")) : null
//				: null : null;
//		HelpDeskTickectStatus status = null;
//		if (ticketStatus != null) {
//			try {
//				status = ticketStatus != null ? HelpDeskTickectStatus.toEnum(ticketStatus) : null;
//			} catch (IllegalArgumentException e) {
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//						filter.get("status").toString() + " is not a valid status");
//			}
//		}
//
//		Date fromDate = null;
//		if (fromDateStr != null) {
//			try {
//				fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateStr);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//
//		Date toDate = null;
//		if (toDateStr != null) {
//			try {
//				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateStr);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		List<Map<String, Object>> ticketList = null;
//		if ("ALL".equalsIgnoreCase(priority)) {
//			priority = "All";
//		}
//
//		if (priority != null && priority.equalsIgnoreCase("All")) {
//			ticketList = helpDeskHistoryRepository.getAllTicketsForAll(fromDateStr, toDateStr, searchString, status,
//					currentUser.getUserId());
//
//		} else {
//			ticketList = helpDeskHistoryRepository.getAllTickets(fromDateStr, toDateStr, priority, searchString, status,
//					currentUser.getUserId());
//		}
//
//		HashMap map = new HashMap<>();
//		if (ticketList != null || !ticketList.isEmpty()) {
//			map.put("ticketList", ticketList);
//			response.setContent(map);
//			response.setSuccess(true);
//			response.setMessage(" List Retrived Successfully");
//		}
//		return response;
//	}

////	@Override
////	public ApiResponse downloadAllTickets(Map<String, Object> filter) {
////		ApiResponse response = new ApiResponse(false);
////
////		String priority = filter.containsKey("priority") ? ((String) filter.get("priority")) : null;
////		// String ticketDescription = filter.containsKey("ticketDescription") ?
////		// ((String) filter.get("ticketDescription")) : null;
////
////		String toDateStr = filter.containsKey("toDate") ? filter.get("toDate") != null
////				? !((String) filter.get("toDate")).trim().equals("") ? ((String) filter.get("toDate")) : null
////				: null : null;
////		String fromDateStr = filter.containsKey("fromDate") ? filter.get("fromDate") != null
////				? !((String) filter.get("fromDate")).trim().equals("") ? ((String) filter.get("fromDate")) : null
////				: null : null;
////		String searchString = filter.containsKey("searchString") ? filter.get("searchString") != null
////				? !((String) filter.get("searchString")).trim().equals("") ? ((String) filter.get("searchString"))
////						: null
////				: null : null;
////		String belongsTo = filter.containsKey("belongsTo") ? filter.get("belongsTo") != null
////				? !((String) filter.get("belongsTo")).trim().equals("") ? ((String) filter.get("belongsTo")) : null
////				: null : null;
////
////		Date fromDate = null;
////		if (fromDateStr != null) {
////			try {
////				fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateStr);
////			} catch (ParseException e) {
////				e.printStackTrace();
////			}
////		}
////
////		Date toDate = null;
////		if (toDateStr != null) {
////			try {
////				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateStr);
////			} catch (ParseException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
////
////		List<Map> ticketList = null;
////
////		if (belongsTo.equalsIgnoreCase("Raised-By-Me")) {
////
////			ticketList = helpDeskHistoryRepository.getTicketRaisedByMe(priority, toDateStr, fromDateStr,
////					currentUser.getUserId(), searchString);
////		}
////
////		if (belongsTo.equalsIgnoreCase("ALL")) {
////
////			ticketList = helpDeskHistoryRepository.getAllTickets();
////		}
////		if (belongsTo.equalsIgnoreCase("Raised-To-Me")) {
////
////			ticketList = helpDeskHistoryRepository.getTicketRaisedToMe(priority, toDateStr, fromDateStr,
////					currentUser.getUserId(), searchString);
////		}
////		Map<String, Object> fileResponse = new HashMap<>();
////
////		Workbook workbook = prepareExcelWorkBook(ticketList);
////
////		byte[] blob = ExcelUtil.toBlob(workbook);
////
////		try {
////			ExcelUtil.saveWorkbook(workbook, "stockDetailsreports.xlsx");
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////
////		fileResponse.put("fileName", "stockDetails-report.xlsx");
////		fileResponse.put("type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
////		fileResponse.put("blob", blob);
////		response.setFileDetails(fileResponse);
////		response.setSuccess(true);
////		response.setMessage("stockDetails exported Successfully");
////		return response;
////	}
////
////	private Workbook prepareExcelWorkBook(List<Map> ticketList) {
////		List<String> headers = Arrays.asList("Priority", "Title", "From Date", "To Date", "ticketDescription");
////
////		List data = new ArrayList<>();
////
////		for (Map ticketDetails : ticketList) {
////			Map row = new HashMap<>();
////			row.put("Priority", ticketDetails.get("priority") != null ? ticketDetails.get("priority").toString() : "");
////			row.put("Title", ticketDetails.get("title") != null ? ticketDetails.get("title").toString() : "");
////			row.put("From Date",
////					ticketDetails.get("createdAt") != null ? ticketDetails.get("createdAt").toString() : "");
////			row.put("To Date", ticketDetails.get("createdAt") != null ? ticketDetails.get("createdAt").toString() : "");
////			row.put("ticketDescription",
////					ticketDetails.get("ticketDescription") != null ? ticketDetails.get("ticketDescription").toString()
////							: "");
////
////			data.add(row);
////
////		}
////		Workbook workbook = ExcelUtil.createSingleSheetWorkbook(ExcelUtil.createSheet("Ticket list", headers, data));
////
////		return workbook;
////
////	}
//
	@Override
	public ApiResponse reassignTicket(String ticketId, String memberId) {
		ApiResponse response = new ApiResponse(false);

		HelpDeskTicket existingTicket = healpDeskTicketRepo.getById(ticketId);
		EmployeeClient employee = restEmployee.isEmployeeActive(memberId);
		if (employee == null) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member does not exist.");
		}
		if (existingTicket != null) {
			existingTicket.setAssignedTo(memberId);
			existingTicket.setLastUpdatedAt(new Date());
			existingTicket.setCreatedAt(existingTicket.getCreatedAt());
			existingTicket.setId(existingTicket.getId());
			existingTicket.setPriority(existingTicket.getPriority());
			existingTicket.setTicketDescription(existingTicket.getTicketDescription());
			existingTicket.setStatus(existingTicket.getStatus());
			existingTicket.setTitle(existingTicket.getTitle());
			existingTicket.setCreatedBy(existingTicket.getCreatedBy());
			existingTicket.setUpdatedBy(currentUser.getUserId());
			HelpDeskTicket savedDeskTicket = healpDeskTicketRepo.save(existingTicket);

			if (savedDeskTicket != null) {
				HelpDeskHistory history = new HelpDeskHistory();
				history.setCreatedAt(new Date());
//				history.setCreatedBy(currentUser.getFirstName());
//				history.setMessage(
//						currentUser.getFirstName() + " as Reassigned a ticket to  " + employee.getFirstName());
				history.setTicketId(ticketId);
				if (helpDeskHistoryRepository.save(history) != null) {
					EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(savedDeskTicket.getAssignedTo());
//					EmployeeClient ticketCreatedEmployee = restEmployee.isEmployeeActive(currentUser.getUserId());

					String subject = "The Ticket has been reassigned: " + savedDeskTicket.getTitle();
					String message = String.format("Hi %s,\n",
							assignedEmployee.getFirstName() + " " + assignedEmployee.getLastName())
							+ "Greetings from Xyram Software Solutions..!\n\n"
							+ "The ticket has been reassigned to you. Please find the information below for further details about the ticket.\n"
							+ "Title : " + savedDeskTicket.getTitle() + "\n" + "Description : "
							+ savedDeskTicket.getTicketDescription() + "\n" + "Assigned By : "
//							+ ticketCreatedEmployee.getFirstName() + " " + ticketCreatedEmployee.getLastName() + "\n"
							+ "Priority : " + savedDeskTicket.getPriority() + "\n" + "Project/Non project : "
							+ savedDeskTicket.getType() + "\n\n";
					ObjectMapper objectMapper = new ObjectMapper();
					HelpdeskVo helpdeskVo = new HelpdeskVo();
					helpdeskVo.setAssignedTo(savedDeskTicket.getAssignedTo());
					helpdeskVo.setCreatedBy(savedDeskTicket.getCreatedBy());
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

					response.setMessage("Ticket has been Re-Assigned to " + employee.getFirstName());
					response.setSuccess(true);
					return response;
				}
			} else {
				response.setSuccess(false);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to reassign the ticket.");
			}

		} else {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no Ticket with this id");
		}
		return response;
	}

	private ApiResponse validateTicket(HelpDeskTicket helpDeskTicket) {
		ApiResponse response = new ApiResponse();
		if (helpDeskTicket.getTitle() == null || helpDeskTicket.getTitle().trim().isEmpty()) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is mandatory");

		} else if (helpDeskTicket.getTitle().trim().length() < 3 || helpDeskTicket.getTitle().trim().length() > 225) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title should be between 3 and 225 characters");
		}

		if (helpDeskTicket.getTicketDescription() == null || helpDeskTicket.getTicketDescription().trim().isEmpty()) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description is mandatory");

		} else if (helpDeskTicket.getTicketDescription().trim().length() < 15
				|| helpDeskTicket.getTicketDescription().trim().length() > 5000) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Description should be between 15 and 5000 characters");

		}
		String pattern = "^[a-zA-Z0-9 !@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?]+";
		if (!helpDeskTicket.getTitle().trim().matches(pattern)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title contains invalid characters");
		}

		if (helpDeskTicket.getStatus() == null || helpDeskTicket.getStatus().toString().trim().isEmpty()) {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is mandatory");

		}

		if (helpDeskTicket.getPriority() == null || helpDeskTicket.getPriority().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Select the Priority from Dropdown");
		}

		if (helpDeskTicket.getType() == null || helpDeskTicket.getType().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Select the project/non-project name from Dropdown");
		}

		if (helpDeskTicket.getProjetId() == null || helpDeskTicket.getProjetId().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ProjectId is Missing.");
		}

		if (helpDeskTicket.getAssignedTo() == null || helpDeskTicket.getAssignedTo().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Select the employee to assign ticket. ");
		}

//		if ((employeeRepository.isEmployeeActive(helpDeskTicket.getAssignedTo()) == null)) {
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not exists Or Inactive");
//		}

		response.setSuccess(true);
		return response;
	}

//	private void sendTicketNotification(HelpDeskTicket helpDeskTicket, String message, String subject) {
//		EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(helpDeskTicket.getAssignedTo());
//		EmployeeClient ticketCreatedEmployee = restEmployee.isEmployeeActive(helpDeskTicket.getCreatedBy());
//
//		// Create a set of mail details specific to this user
//		Map<Object, Object> mailDetails = new HashMap<>();
//		mailDetails.put("toEmail", assignedEmployee.getEmail());
//		mailDetails.put("subject", subject);
//		mailDetails.put("message", message);
//
//		// Send the email to this user
////		emailService.sendMail(mailDetails);
//	}

//	@Override
//	public ApiResponse getTicketByTicketId(String ticketId) {
//		ApiResponse response = new ApiResponse();
//
//		Map<String, Object> ticketMap = healpDeskTicketRepo.getTicketByTicketId(ticketId);
//		if (ticketMap != null && !ticketMap.isEmpty()) {
//			Map content = new HashMap();
//			content.put("tickets", ticketMap);
//			response.setMessage("Ticket Retrived Successfully");
//			response.setSuccess(true);
//			response.setContent(content);
//			return response;
//		} else {
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//					"There is no ticket with this ticket ID :" + ticketId);
//		}
//	}

	@Override
	public ApiResponse getTicketHistoryByTicketId(String ticketId) {
		ApiResponse response = new ApiResponse();

		List<HelpDeskHistory> ticketMap = helpDeskHistoryRepository.getTicketHistoryByTicketId(ticketId);
		if (ticketMap != null && !ticketMap.isEmpty()) {
			Map content = new HashMap();
			content.put("ticketHistory", ticketMap);
			response.setMessage("TicketHistory Retrived Successfully");
			response.setSuccess(true);
			response.setContent(content);
			return response;
		} else {
			response.setSuccess(false);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"There is no Ticket History with this ticket ID :" + ticketId);
		}
	}

	@Override
	public ApiResponse getCountOfTicketStatus() {
		ApiResponse response = new ApiResponse();

		List<HelpDeskTickectStatus> allStatusValues = Arrays.asList(HelpDeskTickectStatus.values());

		List<Object[]> ticketList = helpDeskHistoryRepository.getStatusCounts(currentUser.getUserId());

		Map<HelpDeskTickectStatus, Long> statusCountMap = new HashMap<>();

		for (HelpDeskTickectStatus status : allStatusValues) {
			statusCountMap.put(status, 0L);
		}

		for (Object[] statusCount : ticketList) {
			HelpDeskTickectStatus statusEnum = (HelpDeskTickectStatus) statusCount[0];
			Long count = (Long) statusCount[1];
			statusCountMap.put(statusEnum, count);
		}

		List<Map<String, Object>> formattedStatusCounts = new ArrayList<>();
		for (HelpDeskTickectStatus status : allStatusValues) {
			String statusName = status.name();
			Long count = statusCountMap.get(status);

			Map<String, Object> statusMap = new HashMap<>();
			statusMap.put("status", statusName);
			statusMap.put("count", count);
			formattedStatusCounts.add(statusMap);
		}

		Map<String, Object> content = new HashMap<>();
		content.put("statusCounts", formattedStatusCounts);

		response.setMessage("Status Counts Retrieved Successfully");
		response.setSuccess(true);
		response.setContent(content);

		return response;

	}


}
