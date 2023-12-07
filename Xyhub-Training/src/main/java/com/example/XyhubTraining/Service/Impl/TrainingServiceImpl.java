//package com.example.XyhubTraining.Service.Impl;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.example.XyhubTraining.Service.TrainingService;
//
//
//
//@Service
//public class TrainingServiceImpl implements TrainingService {
//
//	@Autowired
//	EmployeePermissionConfig empPerConfig;
//
//	@Autowired
//	EmployeeRepository empRepository;
//
//	@Autowired
//	CurrentUser currentUser;
//
//	@Autowired
//	TrainingsRepository trainingRepository;
//	@Autowired
//	TrainingMembersRepo trainingMembersRepo;
//	@Autowired
//	TrainingTopicRepo trainingTopicRepo;
//
//	@Autowired
//	EnrollmentsRepo enrollmentsRepo;
//
//	@Override
//	public ApiResponse addTraining(Trainings training) throws Exception {
//		ApiResponse response = new ApiResponse(false);
//		if (!empPerConfig.isHavingpersmission("trainingAdmin")) {
//			response.setSuccess(false);
//			response.setMessage("Not authorised to add Training");
//			return response;
//		}
//
//		response = validateTraining(training);
//		if (response.getMessage() != null && response.getMessage() != "") {
//			return response;
//		}
//
//		training.setCreatedBy(currentUser.getUserId());
//		training.setCreatedAt(new Date());
//		training.setLastUpdatedAt(new Date());
//		training.setUpdatedBy(currentUser.getUserId());
//		trainingRepository.save(training);
//		response.setSuccess(true);
//		response.setMessage(ResponseMessages.training_added);
//		return response;
//	}
//
//	private ApiResponse validateTraining(Trainings training) {
//		ApiResponse response = new ApiResponse(false);
//		if (training.getTrainingName() == null) {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.course_man);
//			return response;
//		} else if (training.getTrainingName().length() < 3) {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.course_len);
//			return response;
//		}
//		if (training.getSessionStartTime() == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Session start time is mandatory");
//
//		}
//		if (training.getSessionEndTime() == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Session end time is mandatory");
//
//		}
//		if (training.getStartDate() == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date is mandatory");
//
//		} else if (training.getEndDate() == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date is mandatory");
//
//		} else {
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String startDate = dateFormat.format(training.getStartDate());
//			String endDate = dateFormat.format(training.getEndDate());
//
//			try {
//				Date dos = dateFormat.parse(startDate);
//				Date doe = dateFormat.parse(endDate);
//
//			} catch (ParseException e) {
//				e.printStackTrace();
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date format should be 'yyyy-MM-dd'");
//			}
//
//		}
//
//		if (training.getTrainingLead() == null) {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.Author_man);
//			return response;
//		} else {
//			Employee emp = empRepository.getbyUserId(training.getTrainingLead());
//			if (emp == null) {
//				response.setSuccess(false);
//				response.setMessage(ResponseMessages.Author_val);
//				return response;
//			}
//		}
//
//		if (training.getStartDate() == null || training.getEndDate() == null) {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.DATE_FEILD);
//			return response;
//		}
//		if (training.getType() == null) {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.Training_typ_man);
//			return response;
//		} else {
//			if (training.getType().equals("ONLINE")) {
//				if (training.getTrainingLink() == null || training.getTrainingLink().equals("")) {
//					response.setSuccess(false);
//					response.setMessage(ResponseMessages.Training_link_man);
//					return response;
//				}
//			}
//		}
//		response.setSuccess(true);
//		return response;
//	}
//
////	@Override
////	public ApiResponse getAllTrainings(Pageable pageable) {
////		ApiResponse response = new ApiResponse(false);
////		Page<Map> trainings = trainingRepository.getAllTrainings(pageable);
////		Map content = new HashMap();
////		if (trainings != null) {
////			response.setSuccess(true);
////			content.put("Trainings", trainings);
////			response.setContent(content);
////			response.setMessage(ResponseMessages.Trainings_fetch);
////			return response;
////		} else {
////			response.setSuccess(false);
////			response.setContent(null);
////			response.setMessage(ResponseMessages.Trainings_not_found);
////			return response;
////		}
////	}
//
////	@Override
////	public ApiResponse getTrainings(Map<String, Object> filter, Pageable pageable) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public ApiResponse getAllTrainings(Map<String, Object> filter, Pageable pageable) {
////		Page<Map> trainings = null;
////		if (filter == null) {
////			trainings = trainingRepository.getAllTrainings(pageable);
////
////		} else {
////
////			String searchString = filter.containsKey("searchString") ? ((String) filter.get("searchString")) : null;
////			String status = filter.containsKey("status") ? ((String) filter.get("status")).toUpperCase() : null;
////
////			TrainingSessionStatus Sessionstatus = null;
////			if (status != null) {
////				try {
////					Sessionstatus = status != null ? TrainingSessionStatus.toEnum(status) : null;
////				} catch (IllegalArgumentException e) {
////					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
////							filter.get("status").toString() + " is not a valid status");
////				}
////			}
////
////			trainings = trainingRepository.getAllTrainingsFilters(searchString, Sessionstatus, pageable);
////
////		}
////		ApiResponse response = new ApiResponse(false);
////		Map content = new HashMap();
////		if (trainings != null) {
////			response.setSuccess(true);
////			content.put("Trainings", trainings);
////			response.setContent(content);
////			response.setMessage(ResponseMessages.Trainings_fetch);
////			return response;
////		} else {
////			response.setSuccess(false);
////			response.setContent(null);
////			response.setMessage(ResponseMessages.Trainings_not_found);
////			return response;
////		}
////	}
////
////	@Override
////	public ApiResponse updateTrainings(Trainings trainingRequest, String trainingId) throws Exception {
////
////		ApiResponse response = new ApiResponse(false);
////		if (!empPerConfig.isHavingpersmission("trainingAdmin")) {
////			response.setSuccess(false);
////			response.setMessage("Not authorised to add Training");
////			return response;
////		}
////
////		response = validateTraining(trainingRequest);
////		if (response.getMessage() != null && response.getMessage() != "") {
////			return response;
////		}
////		Trainings trainings = trainingRepository.updateTraining(trainingId);
////		if (trainings != null) {
////			trainings.setEndDate(trainingRequest.getEndDate());
////			trainings.setSessionStartTime(trainingRequest.getSessionStartTime());
////			trainings.setSessionEndTime(trainingRequest.getSessionEndTime());
////			trainings.setStartDate(trainingRequest.getStartDate());
//////			trainings.setStatus(trainingRequest.getStatus());
////			trainings.setTrainingLead(trainingRequest.getTrainingLead());
////			trainings.setTrainingLink(trainingRequest.getTrainingLink());
////			trainings.setTrainingName(trainingRequest.getTrainingName());
////			trainings.setType(trainingRequest.getType());
////			trainings.setLastUpdatedAt(new Date());
////			trainings.setUpdatedBy(currentUser.getUserId());
////			if (trainingRepository.save(trainings) != null) {
////				response.setMessage("updated Successfully");
////				response.setSuccess(true);
////			} else {
////				response.setMessage(" not updated");
////				response.setSuccess(false);
////			}
////
////		} else {
////			response.setMessage("Invalid training Id");
////			response.setSuccess(false);
////		}
////		return response;
////	}
////
////	@Override
////	public ApiResponse changeTrainingStatus(String trainingId, TrainingSessionStatus sessionStatus) {
////		ApiResponse response = new ApiResponse(false);
////		Trainings trainings = trainingRepository.updateTraining(trainingId);
////		if (trainings != null) {
////			trainings.setStatus(sessionStatus);
////			trainings.setUpdatedBy(currentUser.getUserId());
////			trainings.setLastUpdatedAt(new Date());
////			if (trainingRepository.save(trainings) != null) {
////				response.setSuccess(true);
////				response.setMessage(ResponseMessages.TRAINING_STATUS_CHANGED);
////
////			} else {
////				response.setSuccess(false);
////				response.setMessage(ResponseMessages.TRAINING_STATUS_NOT_CHANGED);
////
////			}
////
////		} else {
////			response.setSuccess(false);
////			response.setMessage("Invalid Id");
////		}
////		return response;
////	}
////
////	@Override
////	public ApiResponse getTrainingById(String trainingId) {
////		ApiResponse response = new ApiResponse(false);
////		Map trainings = trainingRepository.getTrainingDeatils(trainingId, currentUser.getUserId());
////		if (trainings != null) {
////			response.setSuccess(true);
////			response.setMessage(ResponseMessages.TRAINING_DETAILS_RETREIVED);
////			Map content = new HashMap();
////			content.put("Detail", trainings);
////			response.setContent(content);
////		} else {
////			response.setSuccess(false);
////			response.setMessage(ResponseMessages.TRAINING_DETAILS_NOT_RETREIVED);
////		}
////
////		return response;
////	}
////
////	@Override
////	public ApiResponse addTrainingMembers(List<TrainingMembers> trainingMembers, String trainingId) {
////		ApiResponse response = new ApiResponse(false);
////		if (trainingMembers != null) {
////			if (trainingMembers.size() > 0) {
////				for (TrainingMembers trainingMem : trainingMembers) {
////					Employee emp = empRepository.getbyUserId(trainingMem.getEmployeeId());
////					if (emp == null) {
////						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
////								"Invalid Employee id: " + emp.getFirstName());
////					} else {
////						TrainingMembers training = new TrainingMembers();
////						training.setTrainingId(trainingId);
////						training.setEmployeeId(trainingMem.getEmployeeId());
////						training.setCreatedAt(new Date());
////						training.setCreatedBy(currentUser.getUserId());
////						training.setUpdatedBy(currentUser.getUserId());
////						training.setLastUpdatedAt(new Date());
////						trainingMembersRepo.save(training);
////
////					}
////				}
////
////				response.setMessage("Training Memebers added successfully");
////				response.setSuccess(true);
////
////			} else {
////				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atleast one team member should add");
////			}
////		} else {
////			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atleast one team member should add");
////		}
////
////		return response;
////	}
////
////	@Override
////	public ApiResponse removeTrainingMembers(String memId, String trainingId) {
////		ApiResponse response = new ApiResponse(false);
////
////		TrainingMembers trainingMembers = trainingMembersRepo.getByMemId(memId, trainingId);
////
////		if (trainingMembers != null) {
////
////			int result = trainingMembersRepo.removeMember(trainingMembers.getId());
////
////			if (result > 0) {
////				response.setMessage("Member removed successfully");
////				response.setSuccess(true);
////			} else {
////				response.setMessage("Unable to remove member");
////				response.setSuccess(false);
////			}
////
////		} else {
////			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member is not available in that training");
////		}
////
////		return response;
////	}
////
////	@Override
////	public ApiResponse updateTrainingMemberStatus(String memId, String trainingId, TrainingMembersStatus status) {
////		ApiResponse response = new ApiResponse(false);
////
////		TrainingMembers trainingMembers = trainingMembersRepo.getByMemId(memId, trainingId);
////
////		if (trainingMembers != null) {
////
////			trainingMembers.setMemberStatus(status);
////
////			if (trainingMembersRepo.save(trainingMembers) != null) {
////				response.setMessage("Member updated successfully");
////				response.setSuccess(true);
////			} else {
////				response.setMessage("Unable to update member");
////				response.setSuccess(false);
////			}
////
////		} else {
////			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member is not available in that training");
////		}
////
////		return response;
////	}
////
////	@Override
////	public ApiResponse getTrainingMembers(String trainingId) {
////		ApiResponse response = new ApiResponse(false);
////
////		Trainings trainings = trainingRepository.updateTraining(trainingId);
////		if (trainings == null) {
////			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Training id");
////
////		}
////
////		List<Map> triMembers = trainingMembersRepo.getMembersByTrainingId(trainingId);
////
////		Map members = new HashMap<>();
////
////		members.put("members", triMembers);
////
////		response.setContent(members);
////		response.setMessage("List Retrieved successfully");
////		response.setSuccess(true);
////
////		return response;
////	}
////
////	@Override
////	public ApiResponse addTrainingTopics(TrainingTopics trainingTopics) throws Exception {
////		ApiResponse response = new ApiResponse(false);
////		response = validateTrainingTopics(trainingTopics);
////		if (response.getMessage() != null && response.getMessage() != "") {
////			return response;
////		}
////
////		
////		if (trainingTopics != null) {
////
////			trainingTopics.setCreatedBy(currentUser.getUserId());
////			trainingTopics.setCreatedAt(new Date());
////			trainingTopics.setLastUpdatedAt(new Date());
////			trainingTopics.setUpdatedBy(currentUser.getUserId());
////			trainingTopicRepo.save(trainingTopics);
////			response.setSuccess(true);
////			response.setMessage("Topics added Successfully");
////		} else {
////			response.setSuccess(false);
////			response.setMessage("Topics not added");
////		}
////		return response;
////
////	}
////
////	private ApiResponse validateTrainingTopics(TrainingTopics trainingTopics) {
////		ApiResponse response = new ApiResponse(false);
////		if (trainingTopics.getTopicName() == null) {
////			response.setSuccess(false);
////			response.setMessage("Topic name is mandatory");
////			return response;
////
////		} else if (trainingTopics.getTopicName().length() < 3) {
////			response.setSuccess(false);
////			response.setMessage("Topic name should be minimum 3 characters");
////			return response;
////
////		}
////
////
////		if (trainingTopics.getTrainingId() == null) {
////			response.setSuccess(false);
////			response.setMessage("Training Id is mandatory");
////			return response;
////
////		}
////		response.setSuccess(true);
////		return response;
////
////	}
////
////	@Override
////	public ApiResponse updateTrainingTopics(TrainingTopics trainingTopics, String id) throws Exception {
////		ApiResponse response = new ApiResponse(false);
////		if (!empPerConfig.isHavingpersmission("trainingAdmin")) {
////			response.setSuccess(false);
////			response.setMessage("Not authorised to add Training");
////			return response;
////		}
////		response = validateTrainingTopics(trainingTopics);
////		if (response.getMessage() != null && response.getMessage() != "") {
////			return response;
////		}
////		TrainingTopics trainingTopic = trainingTopicRepo.updateTrainingTopics(id);
////		if (trainingTopic != null) {
////			trainingTopic.setTopicName(trainingTopics.getTopicName());
////			trainingTopic.setTopicDesc(trainingTopics.getTopicDesc());
////			trainingTopic.setTrainingId(trainingTopics.getTrainingId());
////			trainingTopic.setLastUpdatedAt(new Date());
////			trainingTopic.setUpdatedBy(currentUser.getUserId());
////			if (trainingTopicRepo.save(trainingTopic) != null) {
////				response.setMessage("updated Successfully");
////				response.setSuccess(true);
////			} else {
////				response.setMessage(" not updated");
////				response.setSuccess(false);
////			}
////
////		} else {
////			response.setMessage("Invalid training Id");
////			response.setSuccess(false);
////		}
////		return response;
////
////	}
////
////	@Override
////	public ApiResponse getAllTrainingTTopics(Pageable pageable, String id) {
////		ApiResponse response = new ApiResponse(false);
////		List<Map> trainiTopics = null;
////		trainiTopics = trainingTopicRepo.getAllTrainingTopics(pageable, id);
////		Map content = new HashMap<>();
////		if (trainiTopics != null) {
////			response.setSuccess(true);
////			content.put("Trainings", trainiTopics);
////			response.setContent(content);
////			response.setMessage("Training topics retrieved successfully");
////			return response;
////		} else {
////			response.setSuccess(false);
////			response.setContent(null);
////			response.setMessage("Training topics not retrieved");
////			return response;
////		}
////	}
////
////	@Override
////	public ApiResponse deleteTrainingTopics(String id) {
////		ApiResponse response = new ApiResponse(false);
////
////		TrainingTopics trainingTopicsObj = trainingTopicRepo.deleteTopics(id);
////		if (trainingTopicsObj != null) {
////			try {
////				trainingTopicRepo.delete(trainingTopicsObj);
////				response.setSuccess(true);
////				response.setMessage("Topics deleted successfully");
////
////			} catch (Exception e) {
////				response.setSuccess(false);
////				response.setMessage("Topics not deleted " + " " + e.getMessage());
////			}
////		} else {
////			response.setSuccess(false);
////			response.setMessage("Topics not deleted ");
////		}
////		return response;
////	}
////
////	@Override
////	public ApiResponse changeTopicStatus(TrainingTopicStatus status, String id) throws Exception {
////		ApiResponse response = new ApiResponse(false);
////		if (!empPerConfig.isHavingpersmission("trainingAdmin")) {
////			response.setSuccess(false);
////			response.setMessage("Not authorised to add Training");
////			return response;
////		}
////
////		TrainingTopics trainingTopic = trainingTopicRepo.updateTrainingTopics(id);
////		if (trainingTopic != null) {
////			trainingTopic.setStatus(status);
////
////			trainingTopic.setLastUpdatedAt(new Date());
////			trainingTopic.setUpdatedBy(currentUser.getUserId());
////			if (trainingTopicRepo.save(trainingTopic) != null) {
////				response.setMessage("updated Successfully");
////				response.setSuccess(true);
////			} else {
////				response.setMessage(" not updated");
////				response.setSuccess(false);
////			}
////
////		} else {
////			response.setMessage("Invalid training Id");
////			response.setSuccess(false);
////		}
////		return response;
////	}
////
////	@Override
////	public ApiResponse changeAllTopicStatus(List<TrainingTopics> trainingTopics) throws Exception {
////		ApiResponse response = new ApiResponse(false);
////		if (!empPerConfig.isHavingpersmission("trainingAdmin")) {
////			response.setSuccess(false);
////			response.setMessage("Not authorised to add Training");
////			return response;
////		}
////
////		List<Map> failureIds = new ArrayList<>();
////
////		if (trainingTopics.size() > 0) {
////			for (int i = 0; i < trainingTopics.size(); i++) {
////				TrainingTopics trainingTopic = trainingTopicRepo.updateTrainingTopics(trainingTopics.get(i).getId());
////				if (trainingTopic != null) {
////					trainingTopic.setStatus(trainingTopics.get(i).getStatus());
////
////					trainingTopic.setLastUpdatedAt(new Date());
////					trainingTopic.setUpdatedBy(currentUser.getUserId());
////					if (trainingTopicRepo.save(trainingTopic) == null) {
////						Map map = new HashMap<>();
////						map.put("Invalid", trainingTopics.get(i).getId());
////						failureIds.add(map);
////					}
////
////				} else {
////					Map map = new HashMap<>();
////					map.put("Invalid Id", trainingTopics.get(i).getId());
////					failureIds.add(map);
////				}
////			}
////		}
////
////		Map content = new HashMap<>();
////		content.put("failureIds", failureIds);
////		response.setContent(content);
////
////		response.setMessage("updated Successfully");
////		response.setSuccess(true);
////		return response;
////	}
////
////	private boolean emailValidation(String email) {
////		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
////				Pattern.CASE_INSENSITIVE);
////
////		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
////		return matcher.find();
////	}
////
////	@Override
////	public ApiResponse trainingEnrollment(TrainingEnrollments trainingEnrollments) {
////		ApiResponse response = new ApiResponse(false);
////
//////		response = validateTrainingEnrollments(trainingEnrollments);
//////		if (response.getMessage() != null && response.getMessage() != "") {
//////			return response;
//////		}
////		TrainingEnrollments trEnrollments = enrollmentsRepo.getByUserId(trainingEnrollments.getEmployeeId(),
////				trainingEnrollments.getTrainingId());
////
////		if (trEnrollments != null) {
////			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already member requested");
////		}
////
////		trainingEnrollments.setCreatedBy(currentUser.getUserId());
////		trainingEnrollments.setCreatedAt(new Date());
////		trainingEnrollments.setLastUpdatedAt(new Date());
////		trainingEnrollments.setUpdatedBy(currentUser.getUserId());
////		enrollmentsRepo.save(trainingEnrollments);
////		response.setSuccess(true);
////		response.setMessage(ResponseMessages.enrollment_added);
////		return response;
////	}
////
////	private ApiResponse validateTrainingEnrollments(TrainingEnrollments trainingEnrollments) {
////		ApiResponse response = new ApiResponse(false);
////		String regex = "[a-z A-Z]+";
////		if (!emailValidation(trainingEnrollments.getEmail())) {
////			response.setMessage(ResponseMessages.EMAIL_INVALID);
////			response.setSuccess(false);
////			return response;
////		}
////
////		if (!emailValidation(trainingEnrollments.getOfficialEmail())) {
////			response.setMessage(ResponseMessages.EMAIL_INVALID);
////			response.setSuccess(false);
////			return response;
////		}
////
////		if (trainingEnrollments.getEnrollName() == null) {
////			response.setSuccess(false);
////			response.setMessage(ResponseMessages.enroll_name);
////			return response;
////		} else if (trainingEnrollments.getEnrollName().length() < 3) {
////			response.setSuccess(false);
////			response.setMessage(ResponseMessages.enroll_len);
////			return response;
////		}
////
////		if (trainingEnrollments.getEnrollMode() == null) {
////			response.setSuccess(false);
////			response.setMessage(ResponseMessages.enroll_mode);
////			return response;
////		}
////
////		if (trainingEnrollments.getEnrollProficiency() == null) {
////			response.setSuccess(false);
////			response.setMessage(ResponseMessages.enroll_profi);
////			return response;
////		}
////
////		if (trainingEnrollments.getTrainingId() == null) {
////			response.setSuccess(false);
////			response.setMessage("Training Id is mandatory");
////			return response;
////		}
////
////		Trainings trainings = trainingRepository.validateTraining(trainingEnrollments.getTrainingId());
////		if (trainings == null) {
////			response.setSuccess(false);
////			response.setMessage("Invalid training id or training may completed");
////			return response;
////		}
////
////		if (trainingEnrollments.getEmployeeId() == null) {
////			response.setSuccess(false);
////			response.setMessage("Employee id is mandatory");
////			return response;
////		}
////		Employee emp = empRepository.getbyUserId(trainingEnrollments.getEmployeeId());
////		if (emp == null) {
////			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Employee id: " + emp.getFirstName());
////		}
////
////		return response;
////	}
////
////	@Override
////	public ApiResponse getAllTrainingEnrollments(Pageable pageable, String id) {
////		ApiResponse response = new ApiResponse(false);
////		List<Map> trainiEnrollments = null;
////		trainiEnrollments = enrollmentsRepo.getAllTrainingEnrollments(pageable, id);
////		Map content = new HashMap<>();
////		if (trainiEnrollments != null) {
////			response.setSuccess(true);
////			content.put("TrainingEnrollments", trainiEnrollments);
////			response.setContent(content);
////			response.setMessage("Training enrollments retrieved successfully");
////			return response;
////		} else {
////			response.setSuccess(false);
////			response.setContent(null);
////			response.setMessage("Training enrollments not retrieved");
////			return response;
////		}
////
////	}
////
////	@Override
////	public ApiResponse getAllTrainingEnrollments(Pageable pageable) {
////		ApiResponse response = new ApiResponse(false);
////		List<Map> trainiEnrollments = null;
////		trainiEnrollments = enrollmentsRepo.getAllTrainingEnrollment(pageable);
////		Map content = new HashMap<>();
////		if (trainiEnrollments != null) {
////			response.setSuccess(true);
////			content.put("TrainingEnrollments", trainiEnrollments);
////			response.setContent(content);
////			response.setMessage("Training enrollments retrieved successfully");
////			return response;
////		} else {
////			response.setSuccess(false);
////			response.setContent(null);
////			response.setMessage("Training enrollments not retrieved");
////			return response;
////		}
////	}
////
////	@Override
////	public ApiResponse changeEnrollmentStatus(EnrollmentStatus status, String id) {
////		ApiResponse response = new ApiResponse(false);
////
////		TrainingEnrollments trainingEnrollments = enrollmentsRepo.getById(id);
////
////		if (trainingEnrollments != null) {
////			trainingEnrollments.setEnrollStatus(status);
////			enrollmentsRepo.save(trainingEnrollments);
////			trainingEnrollments.setLastUpdatedAt(new Date());
////			trainingEnrollments.setUpdatedBy(currentUser.getUserId());
////			if (enrollmentsRepo.save(trainingEnrollments) != null) {
////				if (trainingEnrollments.getEnrollStatus() == EnrollmentStatus.ACCEPTED) {
////					TrainingMembers training = new TrainingMembers();
////					training.setTrainingId(trainingEnrollments.getTrainingId());
////					training.setEmployeeId(trainingEnrollments.getEmployeeId());
////					training.setCreatedAt(new Date());
////					training.setCreatedBy(currentUser.getUserId());
////					training.setUpdatedBy(currentUser.getUserId());
////					training.setLastUpdatedAt(new Date());
////					trainingMembersRepo.save(training);
////					response.setMessage("Enrollment accepted successfully");
////					response.setSuccess(true);
////				} else {
////					response.setMessage("Enrollment rejected successfully");
////					response.setSuccess(true);
////				}
////			}
////		} else {
////			response.setMessage("Invalid enrollment id");
////			response.setSuccess(false);
////		}
////
////		return response;
////	}
//
//}
