//package com.example.XyhubTraining.Controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.XyhubCommon.Apiresponses.ApiResponse;
//import com.example.XyhubTraining.Model.Trainings;
//import com.example.XyhubTraining.Service.TrainingService;
//
//
//
//@RestController
//public class TrainingController {
//
//	private final Logger logger = LoggerFactory.getLogger(TrainingController.class);
//
//	@Autowired
//	TrainingService trainingService;
//
//	@PostMapping("/addTraining")
//	public ApiResponse addTraining(@RequestBody Trainings training) throws Exception {
//		logger.info("received request to add Training");
//		return trainingService.addTraining(training);
//	}
//
////	@PostMapping("/getAllTrainings")
////	public ApiResponse getAllTrainings(@RequestBody(required = false) Map<String, Object> filter, Pageable pageable) {
////		logger.info("received request to add Training");
////		return trainingService.getAllTrainings(filter, pageable);
////	}
////
////	@PutMapping("/updateTraining/{trainingId}")
////	public ApiResponse updateTraining(@RequestBody Trainings trainingRequest, @PathVariable String trainingId)
////			throws Exception {
////		logger.info("received request to edit Training");
////		return trainingService.updateTrainings(trainingRequest, trainingId);
////	}
////
////	@PostMapping("/changeTrainingStatus/{trainingId}/status/{sessionStatus}")
////	public ApiResponse changeTrainingStatus(@PathVariable String trainingId,
////			@PathVariable TrainingSessionStatus sessionStatus) throws Exception {
////		logger.info(
////				"Received request to change Training status to: " + sessionStatus + "for trainingId: " + trainingId);
////		return trainingService.changeTrainingStatus(trainingId, sessionStatus);
////	}
////
////	@GetMapping("/getTrainingById/{trainingId}")
////	public ApiResponse getTrainingById(@PathVariable String trainingId) throws Exception {
////		logger.info("received request to get Training ");
////		return trainingService.getTrainingById(trainingId);
////	}
////
////	@PostMapping("/addTrainingMembers/{trainingId}")
////	public ApiResponse addTrainingMembers(@RequestBody List<TrainingMembers> trainingMembers,
////			@PathVariable String trainingId) throws Exception {
////		logger.info("received request to add trainingMembers");
////		return trainingService.addTrainingMembers(trainingMembers, trainingId);
////	}
////	
////	@PutMapping("/removeTrainingMembers/{memId}/{trainingId}")
////	public ApiResponse removeTrainingMembers(
////			@PathVariable String memId,@PathVariable String trainingId) throws Exception {
////		logger.info("received request to add trainingMembers");
////		return trainingService.removeTrainingMembers(memId,trainingId);
////	}
////	
////	@PutMapping("/updateTrainingMemberStatus/{memId}/{trainingId}/{status}")
////	public ApiResponse updateTrainingMemberStatus(
////			@PathVariable String memId,@PathVariable String trainingId, @PathVariable TrainingMembersStatus status) throws Exception {
////		logger.info("received request to add trainingMembers");
////		return trainingService.updateTrainingMemberStatus(memId,trainingId,status);
////	}
////	
////	
////	@GetMapping("/getTrainingMembers/{trainingId}")
////	public ApiResponse getTrainingMembers(@PathVariable String trainingId) throws Exception {
////		logger.info("received request to get Training ");
////		return trainingService.getTrainingMembers(trainingId);
////	}
////	
////	@PostMapping("/addTrainingTopics")
////	public ApiResponse addTrainingTopics(@RequestBody TrainingTopics trainingTopics) throws Exception {
////		logger.info("received request to add Training");
////		return trainingService.addTrainingTopics(trainingTopics);
////	}
////	
////	@GetMapping("/getAllTrainingTopics/{id}")
////	public ApiResponse getAllTrainingTTopics(Pageable pageable,@PathVariable String id) {
////		logger.info("received request to get Trainings");
////		return trainingService.getAllTrainingTTopics(pageable,id);
////	}
////	
////	@PutMapping("/updateTrainingTopics/{id}")
////	public ApiResponse updateTrainingTopics(@RequestBody TrainingTopics trainingTopics, @PathVariable String id)
////			throws Exception {
////		logger.info("received request to edit Training topics");
////		return trainingService.updateTrainingTopics(trainingTopics, id);
////	}
////	
////	
////	@PutMapping("/changeTopicStatus/{id}/{status}")
////	public ApiResponse changeTopicStatus(@PathVariable TrainingTopicStatus status, @PathVariable String id)
////			throws Exception {
////		logger.info("received request to edit Training topics");
////		return trainingService.changeTopicStatus(status, id);
////	}
////	
////	@PutMapping("/changeAllTopicStatus")
////	public ApiResponse changeAllTopicStatus(@RequestBody List<TrainingTopics> trainingTopics)
////			throws Exception {
////		logger.info("received request to edit Training topics");
////		return trainingService.changeAllTopicStatus(trainingTopics);
////	}
////
////	
////	
////	
////	@DeleteMapping("/deleteTrainingTopics/{id}")
////	public ApiResponse deleteTrainingTopics(@PathVariable String id) throws Exception {
////		logger.info("inside delete Training topics");
////		return trainingService.deleteTrainingTopics(id);
////	}
////	
////	
////	@PostMapping("/addtrainingEnrollment")
////	public ApiResponse trainingEnrollment(@RequestBody TrainingEnrollments trainingEnrollments) throws Exception {
////		logger.info("received request to  Training Enrollments");
////		return trainingService.trainingEnrollment(trainingEnrollments);
////	}
////	
////	@GetMapping("/getAllTrainingEnrollments")
////	public ApiResponse getAllTrainingEnrollments(Pageable pageable) {
////		logger.info("received request to get Training");
////		return trainingService.getAllTrainingEnrollments(pageable);
////	}
////
////	@GetMapping("/getAllTrainingEnrollments/{id}")
////	public ApiResponse getAllTrainingEnrollments(Pageable pageable,@PathVariable String id) {
////		logger.info("received request to get Trainings");
////		return trainingService.getAllTrainingEnrollments(pageable,id);
////	}
////	
////	@PutMapping("/changeEnrollmentStatus/{id}/{status}")
////	public ApiResponse changeEnrollmentStatus(@PathVariable EnrollmentStatus status, @PathVariable String id)
////			throws Exception {
////		logger.info("received request to  Training status");
////		return trainingService.changeEnrollmentStatus(status, id);
////	}
//}
