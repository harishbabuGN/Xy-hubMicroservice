package com.example.XyhubEmployee.Repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.XyhubEmployee.Entity.Employee;




@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	
	@Query("Select e.email from Employee e where e.email = :email")
	String filterByEmail(String email);

	@Query("Select distinct new map(e.eId as id,e.email as email,e. profileUrl as profileUrl, e.userCredientials.id as userId , e.firstName as firstName,e.lastName as lastName,e.middleName as middleName ,e.password as password,e.designationId as designationId,e.dateOfJoin as dateOfJoin,"
			+ "e.status as status,e.mobileNumber as mobileNumber,d.designationName as designationName,e.profileUrl as profileUrl,e.createdAt as createdAt,e.createdBy as createdBy,e.location as location,c.locationName as locationName,e.position as position,e.wings as wings,e.reportingTo as reportingTo,CONCAT(ee.firstName ,' ', ee.lastName) as ReporterName) from Employee e "
			+ "left JOIN Employee ee ON ee.userCredientials.id = e.reportingTo  "
			+ " left JOIN  CompanyLocation c On e.location = c.Id  left JOIN  Designation d On e.designationId=d.Id where e.eId=:userId")
	List<Map> getbyEmpId(String userId);
	
	
	
	/*
	 * @Query("Select new map(e.eId as id,e.email as email,e.userCredientials.uid as uid,e.firstName as firstName,e. profileUrl as profileUrl, e.lastName as lastName,e.middleName as middleName ,e.roleId as roleId ,e.designationId as designationId,e.dateOfJoin as dateOfJoin,"
	 * +
	 * "e.status as status,e.mobileNumber as mobileNumber,r.roleName as rolename,d.designationName as designationName,e.reportingTo as reportingTo,CONCAT(ee.firstName ,' ', ee.lastName) as ReporterName,ee.profileUrl as ReporterURL) from Employee e left join Employee ee On ee.eId = e.reportingTo  "
	 * +
	 * "left JOIN Role r On e.roleId = r.Id left JOIN  Designation d On e.designationId=d.Id  where e.eId=:reportingTo"
	 * )
	 */
	@Query("Select new map(e.eId as id,e.email as email,e.userCredientials.uid as uid,e.firstName as firstName,e. profileUrl as profileUrl, e.lastName as lastName,e.middleName as middleName ,e.designationId as designationId,e.dateOfJoin as dateOfJoin,"
			+ "e.status as status,e.mobileNumber as mobileNumber,d.designationName as designationName,e.reportingTo as reportingTo,CONCAT(ee.firstName ,' ', ee.lastName) as ReporterName,ee.profileUrl as ReporterURL) from Employee e left join Employee ee On ee.userCredientials.id = e.reportingTo  "
			+ "left JOIN  Designation d On e.designationId=d.Id  where e.userCredientials.id=:reportingTo")
	List<Map> getEmployeeBYReportingToId(String reportingTo);
	
	
	@Query("select e from Employee e where e.userCredientials.id =:eId")
	Employee getByEmpIdE(String eId);
	
	@Query("select e from Employee e where e.userCredientials.id =:userId and  e.status = 'ACTIVE'")
	Employee isEmployeeActive(String userId);
	
	@Query("SELECT e from Employee e where e.userCredientials.id = :userId")
	Employee getbyUserId(String userId);

}
