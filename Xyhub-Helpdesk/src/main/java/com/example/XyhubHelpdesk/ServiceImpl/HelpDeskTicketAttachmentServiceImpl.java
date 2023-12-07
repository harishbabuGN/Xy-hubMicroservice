package com.example.XyhubHelpdesk.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Config.RabbitMqConfig;
import com.example.XyhubHelpdesk.Entity.CurrentUser;
import com.example.XyhubHelpdesk.Entity.HelpDeskHistory;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicketAttachment;
import com.example.XyhubHelpdesk.Fileupload.FileTransferService;
import com.example.XyhubHelpdesk.HelpdeskVo.HelpdeskVo;
import com.example.XyhubHelpdesk.Repository.HelpDeskHistoryRepository;
import com.example.XyhubHelpdesk.Repository.HelpDeskTicketAttachementRepo;
import com.example.XyhubHelpdesk.Repository.HelpDeskTicketRepo;
import com.example.XyhubHelpdesk.Service.HelpDeskTicketAttachmentService;
import com.example.XyhubHelpdesk.constants.EmployeeClient;
import com.example.XyhubHelpdesk.restclient.restEmployee;
//import com.example.XyhubHelpdesk.StorageService.S3BucketStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

@Service
public class HelpDeskTicketAttachmentServiceImpl implements HelpDeskTicketAttachmentService {

//	@Value("${ticket-attachment-base-url}")
//	private String ticketAttachmentUrl;

	@Autowired
	HelpDeskTicketAttachementRepo helpDeskTicketAttachmentRepo;

//	@Value("${file-key}")
//	private String keyName;

	@Value("${xyhub-preview}")
	private String previewKey;
	
//	@Autowired
//	S3BucketStorageService s3BucketStorageService;
	
	
	@Autowired
	restEmployee restEmployee;
//
//	@Autowired
//	EmailService emailService;

//	@Autowired
//	FileTransferService fileTransferService;

	@Autowired
	HelpDeskTicketRepo healpDeskTicketRepo;

//	@Autowired
//	TicketAssignRepository ticketAssigneeRepository;
//
//	@Autowired
//	NotificationRepository notificationsRepository;

//	@Autowired
//	EmployeeRepository employeeRepository;

//	@Autowired
//	PushNotificationCall pushNotificationCall;
//
//	@Autowired
//	PushNotificationRequest pushNotificationRequest;

	@Autowired
	HelpDeskHistoryRepository helpDeskHistoryRepository;

	@Autowired
	CurrentUser currentUser;

	@Autowired
	CurrentUser userDetail;

//	@Autowired
//	EmployeePermissionConfig empPerConfig;

//	@Autowired
//	RabbitTemplate rabbitTemplate;
//
//	@Value("${sftp.host}")
//	private String host;
//
//	static ChannelSftp channelSftp = null;
//	static Session session = null;
//	static Channel channel = null;
//	static String PATHSEPARATOR = "/";
//
//	String SFTPKEY = "/home/ubuntu/tomcat-be/webapps/Ticket_tool-0.0.1-SNAPSHOT/WEB-INF/classes/Covid-Phast-Prod.ppk";
//	String SFTPWORKINGDIRAADMIN = "/home/ubuntu/tomcat-be/webapps/image/ticket-attachment";

//	@Override
//	public ApiResponse addTicketAttachement(MultipartFile[] files, String ticketId) {
//		ApiResponse response = new ApiResponse();
//
//		// Check if ticketId is valid
//		HelpDeskTicket ticket = healpDeskTicketRepo.getById(ticketId);
//		if (ticket == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket ID is not valid");
//		}
////	    
////	    if(!currentUser.getUserId().equals(ticket.getCreatedBy()) && !currentUser.getUserId().equals(ticket.getAssignedTo())) {
////	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You do not have permission to add attachments in this ticket.");
////	    }
//
//		if (files != null && files.length > 0) {
//
//			for (MultipartFile contentFile : files) {
//				try {
//					// Process each file individually
//					String fileExtension = contentFile.getOriginalFilename()
//							.substring(contentFile.getOriginalFilename().lastIndexOf("."));
//					String filename = getRandomFileName() + fileExtension;
//
//					String key = keyName + ticketAttachmentUrl + "/" + contentFile.getOriginalFilename();
//					String s3BucketFileKey = s3BucketStorageService.uploadFile(contentFile, key);
//
//					if (s3BucketFileKey != null) {
//						// Create and save ticket attachment
//						HelpDeskTicketAttachment ticketAttachment = new HelpDeskTicketAttachment();
//						ticketAttachment.setCreatedBy(userDetail.getUserId());
//						ticketAttachment.setCreatedAt(new Date());
//						ticketAttachment.setUpdatedBy(userDetail.getUserId());
//						ticketAttachment.setLastUpdatedAt(new Date());
//						ticketAttachment.setTicketId(ticketId);
//						ticketAttachment.setImagePath(s3BucketFileKey);
//						ticketAttachment.setFileName(filename);
//
//						// Save the ticket attachment individually
//						ticketAttachment = helpDeskTicketAttachmentRepo.save(ticketAttachment);
//
//						if (ticketAttachment != null) {
//							// Create and save a history record
//							HelpDeskHistory history = new HelpDeskHistory();
//							history.setCreatedAt(new Date());
//							history.setCreatedBy(currentUser.getFirstName());
//							history.setMessage(
//									currentUser.getFirstName() + " added a ticket attachment to " + ticketId);
//							history.setTicketId(ticketId);
//
//							if (helpDeskHistoryRepository.save(history) != null) {
//								// Send notification and set response message
//								sendTicketNotification(ticket, "Attachment uploaded", "Updated ticket comment");
//								response.setSuccess(true);
//								response.setMessage("The attachment was uploaded successfully.");
//							} else {
//								// Handle history record save failure
//								response.setSuccess(false);
//								throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//										"Failed to save the history record.");
//							}
//						} else {
//							// Handle ticket attachment save failure
//							response.setSuccess(false);
//							throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//									"Failed to save ticket attachment.");
//						}
//					} else {
//						// Handle file upload failure
//						response.setSuccess(false);
//						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to upload attachment.");
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//				}
//			}
//		} else {
//			// No files to upload
//			response.setSuccess(false);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No files to upload");
//		}
//
//		return response;
//	}
//
//	public String getRandomFileName() {
//		int leftLimit = 97; // letter 'a'
//		int rightLimit = 122; // letter 'z'
//		int targetStringLength = 10;
//		Random random = new Random();
//
//		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
//				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
//
//		return generatedString;
//	}
//
	@Override
	public ApiResponse removeTicketAttachment(String ticketId, String id) {
		ApiResponse response = new ApiResponse();

		// HealpDeskTicketAttachment perchaseOrder =
		// helpDeskTicketAttachmentRepo.getByAttachementAndId(ticketId,id);
		HelpDeskTicket tickets = healpDeskTicketRepo.getById(ticketId);
		HelpDeskTicketAttachment attachement = helpDeskTicketAttachmentRepo.getByAttachementAndId(ticketId, id);
		Integer status = helpDeskTicketAttachmentRepo.deleteAttachement(ticketId, id);

		if (status > 0) {
			// ss3StorageService.deleteFile(filename, bucketName);
//			fileTransferService.deleteFile(
//					attachement.getImagePath().substring(attachement.getImagePath().lastIndexOf("/")).replace("/", ""),
//					ticketAttachmentUrl);
			HelpDeskHistory history = new HelpDeskHistory();
			history.setCreatedAt(new Date());
			history.setCreatedBy(currentUser.getFirstName());
			history.setMessage(currentUser.getFirstName() + " removed ticket attachment to " + ticketId);
			history.setTicketId(ticketId);

			if (helpDeskHistoryRepository.save(history) != null) {
				EmployeeClient assignedEmployee = restEmployee.isEmployeeActive(tickets.getAssignedTo());
//				EmployeeClient ticketCreatedEmployee = employeeRepository.isEmployeeActive(currentUser.getUserId());

				String subject = "Updated ticket comment\n\n ";
				String message = String.format("Hello %s,\n\n",
						assignedEmployee.getFirstName() + " " + assignedEmployee.getLastName())
//						+ "Greetings from Xyram Software Solutions..!\n\n" + ticketCreatedEmployee.getFirstName() + ""
//						+ ticketCreatedEmployee.getLastName()
						+ " as removed ticket attachement.For the further information check this ticket-Id -"
						+ ticketId;
				ObjectMapper objectMapper = new ObjectMapper();
				HelpdeskVo helpdeskVo = new HelpdeskVo();
				helpdeskVo.setAssignedTo(tickets.getAssignedTo());
				helpdeskVo.setCreatedBy(tickets.getCreatedBy());
				helpdeskVo.setMessage(message);
				helpdeskVo.setSubject(subject);

				String jsonData = null;
				try {
					jsonData = objectMapper.writeValueAsString(helpdeskVo);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

//				rabbitTemplate.convertAndSend(RabbitMqConfig.HELPDESK_EXCHANGE, RabbitMqConfig.HELPDESK_ROUTING_KEY,
//						jsonData);
				response.setMessage("Attachement removed succefully for  this ID :" + id);
				response.setSuccess(true);
				return response;
			}

		} else {
			response.setSuccess(false);

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to delete document for ID :" + id);

		}

		return response;
	}

	@Override
	public ApiResponse getTicketAttachementByTicketId(String ticketId) {
		String attachmentPreview = "https://" + previewKey;
		ApiResponse response = new ApiResponse();
		List<HelpDeskTicketAttachment> ticketAttachments = helpDeskTicketAttachmentRepo
				.getAttachmentByTicketId(ticketId);

		if (ticketAttachments != null && !ticketAttachments.isEmpty()) {
			List<Map<String, Object>> documents = new ArrayList<>();

			for (HelpDeskTicketAttachment attachment : ticketAttachments) {
				Map<String, Object> attachmentMap = new HashMap<>();
				attachmentMap.put("createdAt", attachment.getCreatedAt());
				attachmentMap.put("createdBy", attachment.getCreatedBy());
				attachmentMap.put("lastUpdatedAt", attachment.getLastUpdatedAt());
				attachmentMap.put("ticketId", attachment.getTicketId());
				attachmentMap.put("imagePath", attachment.getImagePath());
				attachmentMap.put("fileName", attachment.getFileName());
				attachmentMap.put("id", attachment.getId());
				attachmentMap.put("updatedBy", attachment.getUpdatedBy());
				attachmentMap.put("preview", attachmentPreview);

				documents.add(attachmentMap);
			}

			Map<String, Object> content = new HashMap<>();
			content.put("documents", documents);
			response.setContent(content);
			response.setMessage("Ticket Attachments Retrieved Successfully");
			response.setSuccess(true);
		} else {
			Map<String, Object> content = new HashMap<>();
			content.put("documents", new ArrayList<>());
			response.setContent(content);
			response.setSuccess(false);
			response.setMessage("No Ticket Attachments Found");

		}

		return response;
	}

//	private void sendTicketNotification(HelpDeskTicket helpDeskTicket, String message, String subject) {
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
