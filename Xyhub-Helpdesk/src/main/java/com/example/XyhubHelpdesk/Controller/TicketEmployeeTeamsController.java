package com.example.XyhubHelpdesk.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Service.TicketEmployeeTeamsService;



@RestController
@CrossOrigin
public class TicketEmployeeTeamsController {

	private final Logger logger = LoggerFactory.getLogger(TicketEmployeeTeamsController.class);

	@Autowired
	TicketEmployeeTeamsService ticketEmployeeTeamsService;

	@PostMapping("/addMemberToMyTeam/{teamMemberId}")
	public ApiResponse addMemberToMyTeam(@PathVariable String teamMemberId) throws Exception {
		logger.info("Received request to add tickets");
		return ticketEmployeeTeamsService.addMemberToMyTeam(teamMemberId);
	}
	
//	@DeleteMapping("/removeMemberFromTeam/{memberId}")
//	public ApiResponse removeMemberFromTeam(@PathVariable String memberId) throws Exception {
//		logger.info("Received request to add tickets");
//		return ticketEmployeeTeamsService.removeMemberFromTeam(memberId);
//	}
//	
//	@GetMapping("/getAllMyTeamMembers")
//	public ApiResponse getAllMyTeamMembers() {
//		return ticketEmployeeTeamsService.getAllMyTeamMembers();
//	}
	
	@GetMapping("/getTeamIds/{Id}")
	public ApiResponse getTeamIds(@PathVariable String Id) {
		return ticketEmployeeTeamsService.getTeamIds(Id);
	}
}
