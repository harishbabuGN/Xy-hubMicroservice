package com.example.XyhubHelpdesk.Service;

import java.util.List;

import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;



public interface TicketEmployeeTeamsService {

	ApiResponse addMemberToMyTeam(String memberId);

//	ApiResponse removeMemberFromTeam(String memberId);
	
//	ApiResponse getAllMyTeamMembers();

	ApiResponse getTeamIds(String id);

}
