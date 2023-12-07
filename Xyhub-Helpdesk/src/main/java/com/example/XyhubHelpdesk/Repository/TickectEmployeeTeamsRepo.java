package com.example.XyhubHelpdesk.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.XyhubHelpdesk.Entity.TicketEmployeeTeam;



public interface TickectEmployeeTeamsRepo extends JpaRepository<TicketEmployeeTeam, String> {

	@Query("Select t from TicketEmployeeTeam t where  t.teamMemberId = :memberId "
			+ "and t.hostId = :hostId")
	TicketEmployeeTeam getByMemberId(String memberId, String hostId);
//
//	@Query("SELECT DISTINCT NEW map(t.id as id, t.teamMemberId as teamMemberId, t.hostId as hostId, h.name AS hostName, m.name AS memberName , "
//			+ "t.createdBy as createdBy , t.createdAt as createdAt ) " +
//		       "FROM TicketEmployeeTeam t " +
//		       "LEFT JOIN User h ON t.hostId = h.id " +
//		       "LEFT JOIN User m ON t.teamMemberId = m.id " +
//		       "WHERE t.hostId = :hostId ORDER BY t.createdAt DESC")
//		List<Map> getAllMyTeamMembersByHost(@Param("hostId") String hostId);
//
//

	@Query("Select t from TicketEmployeeTeam t where t.teamMemberId =:id")
	List<TicketEmployeeTeam> getTeamIds(String id);

}
