package com.example.XyhubHelpdesk.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.XyhubHelpdesk.Entity.HelpDeskHistory;
import com.example.XyhubHelpdesk.Entity.HelpDeskTicket;
import com.example.XyhubHelpdesk.enumType.HelpDeskTickectStatus;



public interface HelpDeskHistoryRepository extends JpaRepository<HelpDeskHistory, String> {

	@Query(value = "select * from ticket_history t ORDER BY t.created_at DESC", nativeQuery = true)
	List<Map> getAllTicketHistory();
//
//	@Query("SELECT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority,"
//			+ " hdk.status as status, hdk.title as title, hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, "
//			+ "hdk.createdBy as createdBy, hdk.lastUpdatedAt as lastUpdatedAt ) FROM HelpDeskTicket hdk "
//			+ "WHERE (:toDateStr is null OR DATE(hdk.createdAt) <= STR_TO_DATE(:toDateStr, '%Y-%m-%d')) AND "
//			+ "(:priority is null OR hdk.priority = :priority) AND "
//			+ "(:searchString is null OR hdk.title LIKE %:searchString%) AND "
//			+ "(:belongsTo is null OR hdk.createdBy = :belongsTo) AND "
//			+ "(:fromDateStr is null OR DATE(hdk.createdAt) >= STR_TO_DATE(:fromDateStr, '%Y-%m-%d'))")
//	Page<Map> getTicketRaisedByMe(String priority, String toDateStr, String fromDateStr, String belongsTo,
//			String searchString, Pageable pageable);
//
//	@Query("SELECT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority,"
//			+ " hdk.status as status, hdk.title as title, hdk.assignedTo as assignedTo, hdk.ticketNo as ticketNo ,hdk.createdAt as createdAt, "
//			+ "hdk.createdBy as createdBy, hdk.lastUpdatedAt as lastUpdatedAt ) FROM HelpDeskTicket hdk ")
//	Page<Map> getAllTickets(Pageable pageable);
//
//	@Query("SELECT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority,"
//			+ " hdk.status as status, hdk.title as title, hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, "
//			+ "hdk.createdBy as createdBy, hdk.lastUpdatedAt as lastUpdatedAt ) FROM HelpDeskTicket hdk "
//			+ "WHERE (:toDateStr is null OR DATE(hdk.createdAt) <= STR_TO_DATE(:toDateStr, '%Y-%m-%d')) AND "
//			+ "(:priority is null OR hdk.priority = :priority) AND "
//			+ "(:searchString is null OR hdk.title LIKE %:searchString%) AND "
//			+ "(:belongsTo is null OR hdk.assignedTo = :belongsTo) AND "
//			+ "(:fromDateStr is null OR DATE(hdk.createdAt) >= STR_TO_DATE(:fromDateStr, '%Y-%m-%d'))")
//	Page<Map> getTicketRaisedToMe(String priority, String toDateStr, String fromDateStr, String belongsTo,
//			String searchString, Pageable pageable);
//
//	@Query("SELECT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority,"
//			+ " hdk.status as status, hdk.title as title, hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, "
//			+ "hdk.createdBy as createdBy, hdk.lastUpdatedAt as lastUpdatedAt ) FROM HelpDeskTicket hdk "
//			+ "WHERE (:toDateStr is null OR DATE(hdk.createdAt) <= STR_TO_DATE(:toDateStr, '%Y-%m-%d')) AND "
//			+ "(:priority is null OR hdk.priority = :priority) AND "
//			+ "(:searchString is null OR hdk.title LIKE %:searchString%) AND "
//			+ "(:userId is null OR hdk.createdBy = :userId) AND "
//			+ "(:fromDateStr is null OR DATE(hdk.createdAt) >= STR_TO_DATE(:fromDateStr, '%Y-%m-%d'))")
//	List<Map> getTicketRaisedByMe(String priority, String toDateStr, String fromDateStr, String userId,
//			String searchString);
//
//	@Query("SELECT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority,"
//			+ " hdk.status as status, hdk.title as title, hdk.ticketNo as ticketNo , hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, "
//			+ "hdk.createdBy as createdBy, hdk.lastUpdatedAt as lastUpdatedAt ) FROM HelpDeskTicket hdk ")
//	List<Map> getAllTickets();
//
	@Query("SELECT hdk.status as status, COUNT(DISTINCT hdk) as statusCount " + "FROM HelpDeskTicket hdk "
			+ "LEFT JOIN TicketEmployeeTeam tt ON (hdk.createdBy = tt.hostId OR hdk.createdBy = tt.teamMemberId OR hdk.assignedTo = tt.teamMemberId OR hdk.assignedTo = tt.hostId) "
			+ "WHERE (tt.hostId = :userId OR tt.teamMemberId = :userId OR hdk.assignedTo = :userId OR hdk.createdBy = :userId) "
			+ "GROUP BY hdk.status")
	List<Object[]> getStatusCounts(@Param("userId") String userId);
//
//	@Query("SELECT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority,"
//			+ " hdk.status as status, hdk.title as title, hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, "
//			+ "hdk.createdBy as createdBy, hdk.lastUpdatedAt as lastUpdatedAt ) FROM HelpDeskTicket hdk "
//			+ "WHERE (:toDateStr is null OR DATE(hdk.createdAt) <= STR_TO_DATE(:toDateStr, '%Y-%m-%d')) AND "
//			+ "(:priority is null OR hdk.priority = :priority) AND "
//			+ "(:searchString is null OR hdk.title LIKE %:searchString%) AND "
//			+ "(:belongsTo is null OR hdk.createdBy = :belongsTo) AND "
//			+ "(:fromDateStr is null OR DATE(hdk.createdAt) >= STR_TO_DATE(:fromDateStr, '%Y-%m-%d'))")
//	List<Map> getTicketRaisedToMe(String priority, String toDateStr, String fromDateStr, String belongsTo,
//			String searchString);
//
////	@Query("SELECT Distinct new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority, "
////			+ "hdk.status as status, hdk.title as title, hdk.ticketNo as ticketNo , hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, "
////			+ "gi.genIssues as genericIssue, gi.Id as issueId, p.pId as projectId, p.projectName as projectName, "
////			+ "u.name as reportee, a.name as assignee, hdk.createdBy as createdBy, hdk.createdAt as createdDate, "
////			+ "hdk.lastUpdatedAt as lastUpdatedAt) FROM HelpDeskTicket hdk "
////			+ "LEFT JOIN User u ON hdk.createdBy = u.id " + "LEFT JOIN User a ON hdk.assignedTo = a.id "
////			+ "LEFT JOIN Projects p ON hdk.projetId = p.pId " + "LEFT JOIN GenericIssues gi ON hdk.projetId = gi.Id "
////			+ "LEFT JOIN TicketEmployeeTeam tt ON (hdk.createdBy = tt.hostId OR hdk.createdBy = tt.teamMemberId OR hdk.assignedTo = tt.teamMemberId  Or hdk.assignedTo = tt.hostId) "
////			+ "WHERE (:toDateStr is null OR DATE(hdk.createdAt) <= STR_TO_DATE(:toDateStr, '%Y-%m-%d')) AND "
////			+ "(:searchString is null OR hdk.title LIKE %:searchString% OR hdk.ticketDescription LIKE %:searchString% OR "
////			+ "hdk.id LIKE %:searchString% OR  hdk.assignedTo LIKE %:searchString%  OR p.pId LIKE %:searchString% OR"
////			+ " hdk.createdBy LIKE %:searchString% OR hdk.createdAt LIKE %:searchString% OR u.name LIKE %:searchString% OR"
////			+ " a.name LIKE %:searchString% OR p.projectName LIKE %:searchString%) AND "
////			+ "(:priority is null OR hdk.priority = :priority) AND " + "(:status is null or hdk.status = :status) AND "
////			+ "(tt.hostId = :userId OR tt.teamMemberId = :userId OR hdk.assignedTo = :userId OR hdk.createdBy = :userId) AND "
////			+ "(:fromDateStr is null OR DATE(hdk.createdAt) >= STR_TO_DATE(:fromDateStr, '%Y-%m-%d')) ORDER BY hdk.createdAt DESC")
////	List<Map<String, Object>> getAllTickets(String fromDateStr, String toDateStr, String priority, String searchString,
////			HelpDeskTickectStatus status, String userId);
//
	@Query(value = "SELECT * FROM ticket_history th WHERE th.ticket_id = :ticketId ORDER BY th.created_at DESC", nativeQuery = true)
	List<HelpDeskHistory> getTicketHistoryByTicketId(String ticketId);
//
//	@Query("SELECT DISTINCT new map(hdk.id as ticketId, hdk.ticketDescription as ticketDescription, hdk.priority as priority, "
//			+ "hdk.status as status, hdk.title as title, hdk.ticketNo as ticketNo , hdk.assignedTo as assignedTo, hdk.createdAt as createdAt, "
//			+ "gi.genIssues as genericIssue, gi.Id as issueId, p.pId as projectId, p.projectName as projectName, "
//			+ "u.name as reportee, a.name as assignee, hdk.createdBy as createdBy, hdk.createdAt as createdDate, "
//			+ "hdk.lastUpdatedAt as lastUpdatedAt) FROM HelpDeskTicket hdk "
//			+ "LEFT JOIN User u ON hdk.createdBy = u.id " + "LEFT JOIN User a ON hdk.assignedTo = a.id "
//			+ "LEFT JOIN Projects p ON hdk.projetId = p.pId " + "LEFT JOIN GenericIssues gi ON hdk.projetId = gi.Id "
//			+ "LEFT JOIN TicketEmployeeTeam tt ON (hdk.createdBy = tt.hostId OR hdk.createdBy = tt.teamMemberId OR hdk.assignedTo = tt.teamMemberId  Or hdk.assignedTo = tt.hostId) "
//			+ "WHERE (:toDateStr is null OR DATE(hdk.createdAt) <= STR_TO_DATE(:toDateStr, '%Y-%m-%d')) AND "
//			+ "(:searchString is null OR hdk.title LIKE %:searchString% OR hdk.ticketDescription LIKE %:searchString% OR "
//			+ "hdk.id LIKE %:searchString% OR hdk.assignedTo LIKE %:searchString% OR p.pId LIKE %:searchString% OR "
//			+ "hdk.createdBy LIKE %:searchString% OR hdk.createdAt LIKE %:searchString% OR u.name LIKE %:searchString% OR "
//			+ "a.name LIKE %:searchString% OR p.projectName LIKE %:searchString%) AND "
//			+ "(:status is null or hdk.status = :status) AND "
//			+ "(tt.hostId = :userId OR tt.teamMemberId = :userId OR hdk.assignedTo = :userId OR hdk.createdBy = :userId) AND "
//			+ "(:fromDateStr is null OR DATE(hdk.createdAt) >= STR_TO_DATE(:fromDateStr, '%Y-%m-%d')) ORDER BY hdk.createdAt DESC")
//	List<Map<String, Object>> getAllTicketsForAll(String fromDateStr, String toDateStr, String searchString,
//			HelpDeskTickectStatus status, String userId);
//
//	@Query(value = "SELECT * FROM help_desk_ticket_info po WHERE po.ticket_id = :id ORDER BY po.created_at DESC LIMIT 1", nativeQuery = true)
//	HelpDeskTicket findLatestTicketById(String id);
//
//	@Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING(po.ticket_no, 4) AS UNSIGNED)), 0) FROM help_desk_ticket_info po", nativeQuery = true)
//	public Integer getMaxTicketNumber();
//
	@Query("SELECT MAX(CAST(SUBSTRING(h.ticketNo, LENGTH('Ticket_Id-') + 1) AS long)) FROM HelpDeskTicket h")
	Long findMaxTicketNumber();

}
