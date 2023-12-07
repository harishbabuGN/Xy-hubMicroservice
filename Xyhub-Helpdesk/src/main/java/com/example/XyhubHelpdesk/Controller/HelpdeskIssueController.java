//package com.example.XyhubHelpdesk.Controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
//import com.example.XyhubHelpdesk.Entity.CurrentUser;
//import com.example.XyhubHelpdesk.Entity.HelpDeskIssueTypes;
//import com.example.XyhubHelpdesk.Service.HelpDeskIssueService;
//
//
//@CrossOrigin
//@RestController
//public class HelpdeskIssueController {
//
//	private final Logger logger = LoggerFactory.getLogger(HelpdeskIssueController.class);
//
//	@Autowired
//	HelpDeskIssueService helpDeskIssueService;
//	
//	@Autowired
//	CurrentUser currentUser;
//	
//	
//	@PostMapping("/addHelpDeskIssue")
//	public ApiResponse addIssue(@RequestBody HelpDeskIssueTypes helpDeskIssue) throws Exception {
//		logger.info("Received request to add issue");
//		System.out.println("---------"+currentUser.getName());
//		return helpDeskIssueService.addIssueType(helpDeskIssue);
//	}
//	
//	@PutMapping("/editIssues/{issueTypeId}")
//	public ApiResponse editIssue(@RequestBody HelpDeskIssueTypes Request,
//			@PathVariable String issueTypeId) throws Exception {
//		logger.info("received request to editIssue");
//		return helpDeskIssueService.editIssueType(Request, issueTypeId);
//	}
//	
//	/*@GetMapping(value = { AuthConstants.INFRA_ADMIN_BASEPATH + "/getIssues/{issue_Id}",
//			AuthConstants.INFRA_USER_BASEPATH + "/getIssues/{issue_Id}",
//			AuthConstants.ADMIN_BASEPATH + "/getIssues/{issue_Id}"})
//    public ApiResponse getIssues(@PathVariable String issue_Id, Pageable pageable) {
//	        logger.info("Received request to get issue by Id");
//			return helpDeskIssueService.getIssues(issue_Id, pageable);
//	}*/
//	
//	@GetMapping("/getAllIssues")
//	     public ApiResponse getAllIssues(Pageable pageable) {
//	 	        logger.info("Received request to get all issues");
//	 			return helpDeskIssueService.getAllissues(pageable);
//	 	 }
//	
//	@GetMapping("/searchhelpdeskIssueType/{searchString}")
//	 public ApiResponse searchhelpdeskIssueType(@PathVariable String searchString) {
//			logger.info("Received request to search IssueType");
//			return helpDeskIssueService.searchhelpdeskIssueType(searchString);
//	 }
//
//	
//	
//}
