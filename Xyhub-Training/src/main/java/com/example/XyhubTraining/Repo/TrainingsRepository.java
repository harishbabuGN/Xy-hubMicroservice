//package com.xyram.ticketingTool.Repository;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.xyram.ticketingTool.entity.Trainings;
//import com.xyram.ticketingTool.enumType.TrainingSessionStatus;
//
//@Repository
//public interface TrainingsRepository extends JpaRepository<Trainings, String> {
//
//	@Query("select  new Map(t.id as id, t.trainingName as trainingName,t.trainingLead as trainingLead,t.startDate as startDate,t.endDate as endDate,"
//			+ "t.type as type, t.trainingLink as trainingLink ,t.sessionStartTime as sessionStartTime, t.sessionEndTime as sessionEndTime ,"
//			+ "t.createdBy as createdBy , t.createdAt as createdAt, t.UpdatedBy as UpdatedBy, t.lastUpdatedAt as lastUpdatedAt, t.status as status,"
//			+ "concat(e.firstName,' ', e.lastName) as trainerName) "
//			+ "from Trainings t "
//			+ "left join Employee e on t.trainingLead=e.userCredientials.id "
//			+ "ORDER BY t.createdAt DESC")
//	Page<Map> getAllTrainings(Pageable pageable);
//	
//	@Query("select distinct new Map(t.id as id, t.trainingName as trainingName,t.trainingLead as trainingLead,t.startDate as startDate,t.endDate as endDate,"
//			+ "t.type as type, t.trainingLink as trainingLink ,t.sessionStartTime as sessionStartTime, t.sessionEndTime as sessionEndTime ,"
//			+ "t.createdBy as createdBy , t.createdAt as createdAt, t.UpdatedBy as UpdatedBy, t.lastUpdatedAt as lastUpdatedAt, t.status as status, "
//			+ "concat(e.firstName,' ', e.lastName) as trainerName) "
//			+ "from Trainings t "
//			+ "left join Employee e on t.trainingLead=e.userCredientials.id "
//			+ "where (:status is null or t.status=:status) and (:searchString is null or t.trainingName like %:searchString%) "
//		
//			)
//	Page<Map> getAllTrainingsFilters(String searchString, TrainingSessionStatus status, Pageable pageable);
//	
//
////	@Query("select new Map(t.id as id, t.trainingName as trainingName,t.trainingLead as trainingLead,t.startDate as startDate,t.endDate as endDate,"
////			+ "t.type as type, t.trainingLink as trainingLink ,t.sessionStartTime as sessionStartTime, t.sessionEndTime as sessionEndTime ,"
////			+ "t.createdBy as createdBy , t.createdAt as createdAt, t.UpdatedBy as UpdatedBy, t.lastUpdatedAt as lastUpdatedAt, t.status as status, "
////			+ "(Select tm.id from TrainingMembers tm where tm.trainingId=t.id and tm.employeeId=:userId) as memberExists, "
////			+ "concat(e.firstName,' ', e.lastName) as trainerName) "
////			+ "from Trainings t "
////			+ "left join Employee e on t.trainingLead=e.userCredientials.id left join TrainingMembers tm on tm.trainingId"
////			+ "where (:status is null or t.status=:status) and (:searchString is null or t.trainingName like %:searchString%) "
////		
////			)
////	Page<Map> getAllTrainingsForMemebers(String searchString, TrainingSessionStatus status, Pageable pageable);
//	
//	@Query("select t from Trainings t where t.id=:trainingId")
//	Trainings updateTraining(String trainingId);
//
//	
//	@Query("select new Map(t.id as id, t.trainingName as trainingName,t.trainingLead as trainingLead,t.startDate as startDate,t.endDate as endDate, "
//			+ "t.type as type, t.trainingLink as trainingLink ,t.sessionStartTime as sessionStartTime, t.sessionEndTime as sessionEndTime, "
//			+ "t.createdBy as createdBy , t.createdAt as createdAt, t.UpdatedBy as UpdatedBy, t.lastUpdatedAt as lastUpdatedAt, t.status as status, "
//			+ "concat(e.firstName,' ', e.lastName) as trainerName, (Select count (te) from TrainingEnrollments te where te.trainingId=t.id and te.employeeId=:userId and (te.enrollStatus='PENDING' OR te.enrollStatus='ACCEPTED')) as isMemberRequested) "
//			+ "from Trainings t "
//			+ "left join Employee e on t.trainingLead=e.userCredientials.id where t.id=:trainingId")
//	Map getTrainingDeatils(String trainingId,String userId);
//	
//	@Query("select t from Trainings t where t.id=:id  and t.status!='COMPLETED'")
//	Trainings validateTraining(String id);
//
//}
