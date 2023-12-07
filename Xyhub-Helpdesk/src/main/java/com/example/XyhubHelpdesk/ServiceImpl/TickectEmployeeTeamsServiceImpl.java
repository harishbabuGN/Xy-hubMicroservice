package com.example.XyhubHelpdesk.ServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XyhubCommon.enumType.UserStatus;
import com.example.XyhubHelpdesk.Apiresponses.ApiResponse;
import com.example.XyhubHelpdesk.Entity.CurrentUser;
import com.example.XyhubHelpdesk.Entity.TicketEmployeeTeam;
import com.example.XyhubHelpdesk.Repository.TickectEmployeeTeamsRepo;
import com.example.XyhubHelpdesk.Service.TicketEmployeeTeamsService;
import com.example.XyhubHelpdesk.constants.EmployeeClient;
import com.example.XyhubHelpdesk.restclient.restEmployee;



@Service
public class TickectEmployeeTeamsServiceImpl implements TicketEmployeeTeamsService {

	@Autowired
	TickectEmployeeTeamsRepo tickectEmployeeTeamsRepo;

//	@Autowired
//	EmployeeRepository employeeRepository;
	
	@Autowired
	restEmployee restEmployee;

	@Autowired
	CurrentUser currentUser;

	@Override
	public ApiResponse addMemberToMyTeam(String memberId) {

		ApiResponse response = new ApiResponse();
//		String hostId = currentUser.getUserId();
//		EmployeeClient hostUser = restEmployee.getbyUserId(hostId);
//
//		if (hostUser != null && (hostUser.getUserStatus() == UserStatus.ACTIVE)) {
			EmployeeClient memberUser = restEmployee.getbyUserId(memberId);

//			if (memberUser != null && (memberUser.getUserStatus() == UserStatus.ACTIVE)) {
//				TicketEmployeeTeam team = tickectEmployeeTeamsRepo.getByMemberId(memberId,hostId);
				
//				if (team == null) {
					TicketEmployeeTeam ticketEmployeeTeam = new TicketEmployeeTeam();
//					ticketEmployeeTeam.setHostId(hostId);
					ticketEmployeeTeam.setTeamMemberId(memberId);
					ticketEmployeeTeam.setCreatedAt(new Date());
					ticketEmployeeTeam.setCreatedBy(currentUser.getUserId());
					if (tickectEmployeeTeamsRepo.save(ticketEmployeeTeam) != null) {
						response.setSuccess(true);
						response.setMessage("User Added into team");
						return response;
					} else {
						response.setSuccess(false);
						response.setMessage("Not able to add into team");
						return response;
					}
//				}else {
//					response.setSuccess(false);
//					response.setMessage("Member already Exist");
//					return response;
//				}
//			}else {
//				response.setSuccess(false);
//				response.setMessage("Member id is not valid");
//				return response;
//			}
//		}else {
//			response.setSuccess(false);
//			response.setMessage("hostId is not valid");
//			return response;
//		}
	}
	

//	@Override
//	public ApiResponse removeMemberFromTeam(String memberId) {
//		ApiResponse response = new ApiResponse();
//		String hostId = currentUser.getUserId();
//		Employee hostUser = employeeRepository.getbyUserId(hostId);
//
//		if (hostUser != null && (hostUser.getStatus() == UserStatus.ACTIVE)) {
//			Employee memberUser = employeeRepository.getbyUserId(memberId);
//
//			if (memberUser != null && (memberUser.getStatus() == UserStatus.ACTIVE)) {
//				TicketEmployeeTeam team = tickectEmployeeTeamsRepo.getByMemberId(memberId,hostId);
//				if(team!=null) {
//					tickectEmployeeTeamsRepo.delete(team);
//				}else {
//					response.setSuccess(false);
//					response.setMessage("Team dosent Exist");
//					return response;
//				}
//				
//				}else {
//					response.setSuccess(false);
//					response.setMessage("member dosent Exist");
//					return response;
//				}
//		}else {
//			response.setSuccess(false);
//			response.setMessage("hostId dosent Exist");
//			return response;
//		}
//		response.setSuccess(true);
//		response.setMessage("Team Member Removed Successfully.");
//		return response;
//	}
//
//
//	@Override
//	public ApiResponse getAllMyTeamMembers() {
//		ApiResponse response = new ApiResponse();
////		List<Map> employeeTeam =  tickectEmployeeTeamsRepo.getAllMyTeamMembersByHost(currentUser.getUserId());
//		
//			Map content = new HashMap();
////			content.put("TeamMembers", employeeTeam);
//			response.setSuccess(true);
//			response.setContent(content);
//			response.setMessage("TeamMembers Retrieved successfully");
//			return response;
//		
//	}


	@Override
	public ApiResponse getTeamIds(String id) {
		ApiResponse response = new ApiResponse();
		List<TicketEmployeeTeam> employeeTeam =  tickectEmployeeTeamsRepo.getTeamIds(id);
		if(employeeTeam == null || employeeTeam.isEmpty()) {
		response.setSuccess(false);
		response.setMessage("This user("+id+") is not part of any other teams.");
		return response;
	}else {
			Map content = new HashMap();
			content.put("TeamMembers", employeeTeam);
			response.setSuccess(true);
			response.setContent(content);
			response.setMessage("User Retrieved successfully");
			return response;
		} 
	}
	
}

