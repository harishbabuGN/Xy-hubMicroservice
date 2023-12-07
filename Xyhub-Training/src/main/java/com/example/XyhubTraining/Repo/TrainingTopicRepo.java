//package com.xyram.ticketingTool.Repository;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.xyram.ticketingTool.entity.TrainingTopics;
//import com.xyram.ticketingTool.entity.Trainings;
//
//public interface TrainingTopicRepo extends JpaRepository<TrainingTopics, String>{
//	
//	
//	@Query("select t from TrainingTopics t where t.id=:id")
//	TrainingTopics updateTrainingTopics(String id);
//	
//	
//	@Query("select  new Map(tp.id as id, tp.topicName as topicName,tp.trainingId as trainingId,tp.topicNo as topicNo, tp.status as status, tp.topicDesc as topicDesc, "			
//			+ "tp.createdBy as createdBy , tp.createdAt as createdAt, tp.UpdatedBy as UpdatedBy, tp.lastUpdatedAt as lastUpdatedAt) "
//			+ "from TrainingTopics tp where tp.trainingId=:id"
//			)
//	List<Map> getAllTrainingTopics(Pageable pageable,String id);
//	
//	@Query("Select tp from TrainingTopics tp where tp.id=:id")
//	TrainingTopics deleteTopics(String id);
//	
//	@Query(value = "SELECT count(tp.id) from TrainingTopics tp "
//			+ "where tp.trainingId = :trainingId")
//	Integer getTotalTopicsCount(String trainingId);
//	
//	
//	@Query("select t from TrainingTopics t where t.trainingId=:trainingId and t.topicNo=:topicNo")
//	TrainingTopics getTrainingTopic(String trainingId,Integer topicNo);
//	
//
//}
