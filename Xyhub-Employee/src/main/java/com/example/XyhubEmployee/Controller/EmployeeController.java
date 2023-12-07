package com.example.XyhubEmployee.Controller;

import java.awt.print.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.XyhubEmployee.Entity.Employee;
import com.example.XyhubEmployee.Entity.EmployeePermission;
import com.example.XyhubEmployee.Service.EmployeeService;
import com.example.XyhubEmployee.apiresponses.ApiResponse;



@RestController
@CrossOrigin
class EmployeeController {
	private static final Pageable Pageble = null;

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	RoleMasterRepository masterrepo;

	@PostMapping(value = "/createEmployee", consumes = "application/json")
	public ApiResponse addemployee(@RequestBody Employee employee) throws Exception {
		logger.info("Received request to add Employee");
		
		System.out.println();
		return employeeService.addemployee(employee);
	}
	
	@GetMapping("/isEmployeeActive/{userId}")
	public Employee addEmployeeActive(@PathVariable String userId) {
		
		return employeeService.isEmployeeActive(userId);
		
	}
	
	@GetMapping("/Employee/{userId}")
	public Employee getbyUserId(@PathVariable String userId) {
		
		System.out.println("userId"+userId);
		return employeeService.getbyUserId(userId);
	}

//	@PostMapping("/changeEmployeeAllPermission")
//	public ApiResponse changeEmployeeAllPermission(@RequestBody EmployeePermission employeePermission)
//			throws Exception {
//		logger.info("Changing Employee All Permission");
//		return employeeService.changeEmployeeAllPermission(employeePermission);
//	}
//
//	@PostMapping("/changeEmployeePermissions/{userId}/{permission}/{flag}")
//	public ApiResponse changeEmployeePermissions(@PathVariable String userId, @PathVariable String permission,
//			@PathVariable boolean flag) throws Exception {
//		logger.info("Changing Employee Permission");
//		return employeeService.changeEmployeePermission(userId, permission, flag);
//	}
//
//	@GetMapping("/getEmployeePermissions/{userId}")
//	public ApiResponse getEmployeePermission(@PathVariable String userId) throws Exception {
//		logger.info("Get Employee Permission");
//		return employeeService.getEmployeePermission(userId);
//	}
//	
//	@GetMapping("/getAllEmployeeDefaultPermissions")
//	public ApiResponse getAllEmployeeDefaultPermissions() throws Exception {
//		logger.info("Get All Employee Permission");
//		return employeeService.getAllEmployeeDefaultPermissions();
//	}
//
//	@PostMapping("/changeAllEmployeePermissionsToDefault")
//	public ApiResponse changeAllEmployeePermissionsToDefault() throws Exception {
//		logger.info("Changing All Employee Permission to default");
//		return employeeService.changeAllEmployeePermissionsToDefault();
//	}
//
//	@PostMapping("/createJobVendor")
//	public ApiResponse createJobVendor(@RequestBody JobVendorDetails vendorDetails) throws Exception {
//		logger.info("Received request to add vendor details");
//		return employeeService.createVendor(vendorDetails);
//	}
//
////	@GetMapping("/getJobVendor")
////	public ApiResponse getJobVendor(@RequestParam Map<String, Object> filter, Pageable pageable) throws Exception {
////		logger.info("Received request to add Employee");
////		return employeeService.getJobVendor(filter, pageable);
////	}
//
//	@GetMapping("/searchJobVendors/{vendorName}")
//	public ApiResponse searchJobVendors(@PathVariable String vendorName) throws Exception {
//		logger.info("Received request to search job Vendor");
//		return employeeService.serachJobVendor(vendorName);
//	}
//
//	@GetMapping("/getJobVendorType")
//	public ApiResponse getJobVendorType() throws Exception {
//		logger.info("Received request to add Employee");
//		return employeeService.getJobVendorType();
//	}
//
//	@GetMapping("/getJobVendor/{vendorId}")
//	public ApiResponse getJobVendorById(@PathVariable String vendorId) throws Exception {
//		logger.info("Received request to add Employee");
//		return employeeService.getJobVendorById(vendorId);
//	}
//
//	@PostMapping("/getAllEmployee")
//	public ApiResponse getAllEmployee(@RequestBody(required = false) Map<String, Object> filter, Pageable pageable)
//			throws Exception {
//		logger.info("inside getAllEmployee");
//		return employeeService.getAllEmployee(filter, pageable);
//	}
//
//	@GetMapping("/getAllEmployee/currentMonth")
//	public ApiResponse getAllEmployeeCurrentMonth(Pageable pageable) {
//		logger.info("indide CatagoryController :: getAllCatagory");
//		return employeeService.getAllEmployeeCurrentMonth(pageable);
//	}
//
//	@PutMapping("/profile/image/{userId}")
//	public ApiResponse updateProfileImage(@RequestPart(name = "file", required = true) MultipartFile file,
//			@PathVariable String userId) throws IOException {
//		logger.info("Received request for update doctor profile");
//		return employeeService.updateProfileImage(file, userId);
//	}
//
//	@PutMapping("/editEmployee/{userId}")
//	public ApiResponse editEmployee(@RequestBody Employee employeeRequest, @PathVariable String userId)
//			throws Exception {
//		logger.info("indide edit employee");
//		return employeeService.editEmployee(userId, employeeRequest);
//	}
//
//	@GetMapping("/getEmployee/{userId}")
//	public ApiResponse getEmployeeDetails(@PathVariable String userId) throws Exception {
//		logger.info("indide getEmployeeDetails");
//		return employeeService.getEmployeeDetails(userId);
//	}
//
//	@GetMapping("/getEmployeeDetails/{userId}")
//	public ApiResponse getEmployeeDetailsById(@PathVariable String userId) throws Exception{
//		logger.info("indide getEmployeeDetailsById ");
//		return employeeService.getEmployeeDetailsById(userId);
//	}
//
//	@PutMapping("/updateEmployeeStatus/{userId}/status/{userstatus}")
//	public ApiResponse updateEmployeeStatus(@PathVariable String userId, @PathVariable UserStatus userstatus)
//			throws Exception {
//		logger.info("Received request to change category status to: " + userstatus + "for employeeId: " + userId);
//		return employeeService.updateEmployeeStatus(userId, userstatus);
//	}
//
//	@GetMapping("/getAllEmpByProject/{projectid}/clientId/{clientid}")
//	public ApiResponse getAllEmpByProject(@PathVariable String projectid, @PathVariable String clientid) {
//		logger.info("inside EmployeeController :: getAllEmpByProject ");
//		return employeeService.getAllEmpByProject(projectid, clientid);
//	}
//
//	@GetMapping("/searchEmployeeNotAssignedToProject/{projectid}/clientId/{clientid}/searchString/{searchString}")
//	public ApiResponse searchEmployeeNotAssignedToProject(@PathVariable String projectid, @PathVariable String clientid,
//			@PathVariable String searchString) {
//		logger.info("inside EmployeeController :: getAllEmpByProject ");
//		return employeeService.searchEmployeeNotAssignedToProject(projectid, clientid, searchString);
//	}
//
//	@GetMapping("/searchInfraUser/{searchString}")
//	public ApiResponse searchInfraUser(@PathVariable String searchString) {
//		logger.info("inside EmployeeController :: searchInfraUser ");
//		return employeeService.searchInfraUser(searchString);
//	}
//
//	@GetMapping("/searchEmployee/{searchString}")
//	public ApiResponse searchEmployee(@PathVariable String searchString) throws Exception{
//		logger.info("inside EmployeeController :: searchEmployee ");
//		return employeeService.searchEmployee(searchString);
//	}
//	@GetMapping("/searchEmployeesByTeam/{searchString}")
//	public ApiResponse searchEmployees(@PathVariable String searchString) throws Exception{
//		logger.info("inside EmployeeController :: searchEmployee ");
//		return employeeService.searchEmployeeByTeam(searchString);
//	}
//
//	@GetMapping("/searchInfraUsersForInfraUser/{searchString}")
//	public ApiResponse searchInfraUsersForInfraUser(@PathVariable String searchString) {
//		logger.info("inside EmployeeController :: searchInfraUsersForInfraUser ");
//		return employeeService.searchInfraUsersForInfraUser(searchString);
//	}
//
//	@GetMapping("/getAllInfraUser")
//	public ApiResponse getAllInfraUser() {
//		logger.info("indside CatagoryController :: getAllInfraUser");
//		return employeeService.getAllInfraUser();
//	}
//
//	@GetMapping("/getAllPermissions")
//	public ApiResponse getAllPermissions() {
//		logger.info("indside :: getAllPermissions");
//		return employeeService.getAllPermissions();
//	}
//
//	@PutMapping("/updateProfile")
//	public ApiResponse editEmployee(@RequestBody Map employeeRequest) throws Exception {
//		logger.info("indide ProductController :: editEmployee");
//		return employeeService.updateEmployee(employeeRequest);
//	}
//
//	@GetMapping("/getEmployeeByAccessToken")
//	public ApiResponse getListByAccessToken() {
//		logger.info("Received request to getEmployeeByAccessToken");
//		return employeeService.getListByAccessToken();
//	}
//
//	@PutMapping("/editJobVendor/{vendorId}")
//	public ApiResponse editJobVendor(@RequestBody JobVendorDetails vendorRequest, @PathVariable String vendorId) throws Exception {
//		logger.info("indide ProductController :: editJobVendor");
//		return employeeService.editJobVendor(vendorId, vendorRequest);
//	}
//
//	@GetMapping("/getEmployeeByReportingId/{reportingId}")
//	public ApiResponse getEmployeeByReportingId(@PathVariable String reportingId)  throws Exception{
//		logger.info("Received request to add Employee");
//		return employeeService.getEmployeeByReportingId(reportingId);
//	}
//
//	@GetMapping("/getEmployeeByReportingId/{reportingId}/{searchString}")
//	public ApiResponse getEmployeeByReportingId(@PathVariable String reportingId, @PathVariable String searchString) {
//		logger.info("Received request to search Employee");
//		return employeeService.searchEmployeeByReportingId(reportingId, searchString);
//	}
//	
//	@GetMapping("/getInfraEmployee/{searchString}")
//	public ApiResponse getInfraEmployee(@PathVariable String searchString) {
//		logger.info("Received request to get Employee");
//		return employeeService.getInfraEmployee(searchString);
//	}
//
//	@GetMapping("/getAllRolePermissions/{roleId}")
//	public ApiResponse getAllRolePermissions(@PathVariable String roleId) {
//		logger.info("Received request to get rolepermission");
//		return employeeService.getAllRolePermissions(roleId);
//	}
//
//	@PutMapping("/updateRolePermissions")
//	public ApiResponse updateRolePermissions(@RequestParam String roleId, @RequestParam String modules,
//			@RequestBody RoleMasterTable request) throws Exception {
//		logger.info("Received request to add Employee");
//		return employeeService.updateRolePermissions(roleId, modules, request);
//	}
////	@PutMapping(value = { AuthConstants.ADMIN_BASEPATH + "/updateRolePermissions"})
////	public ApiResponse updateRolePermissions(@RequestBody RoleMasterTable request) {
////		logger.info("Received request to add Employee");
////		return employeeService.updateRolePermissions(null,null,request);
////	}
//
//	@PutMapping("/updateOfflineStatus/{infraUserId}")
//	public ApiResponse updateOfflineStatus(@PathVariable String infraUserId) throws Exception {
//		return employeeService.updateOfflineStatus(infraUserId);
//	}
//
//	@GetMapping("/getAllEmployeeList")
//	public ApiResponse getAllEmployeeList() {
//		logger.info("indide CatagoryController :: getAllCatagory");
//		return employeeService.getAllEmployeeList();
//	}
//
//	@GetMapping("/searchEmployeeNotAssignedToProject/{projectid}/searchString/{searchString}")
//	public ApiResponse searchEmployeeNotAssignedToByProject(@PathVariable String projectid,
//			@PathVariable String searchString) {
//		logger.info("inside EmployeeController :: searchEmployeeNotAssignedToProject ");
//		return employeeService.searchEmployeeNotAssignedToByProject(projectid, searchString);
//	}
//
//	@PostMapping("/employeeBulkUpload")
//	public Map<String, Object> employeeBulkUpload(MultipartFile file) throws Exception {
//		logger.info("Received request for get all employeeBulkUpload");
//		return employeeService.employeeBulkUpload(file);
//	}
//	
//	@PutMapping("/updateEmployeeMobileNo/{userId}")
//	public ApiResponse updateEmployeeMobileNo(@PathVariable String userId, @RequestParam String mobileNumber) {
//		logger.info("Received request for update Mobile Number");
//		return employeeService.updateEmployeeMobileNo(userId,mobileNumber);
//	}
//	
//	@GetMapping("/getEmployeeCount")
//	public ApiResponse getEmployeeCount() throws Exception{
//		logger.info("Received request for employee count");
//		return employeeService.getEmployeeCount();
//	}
//	
//	@GetMapping("/getHrEmployees/{searchString}")
//	public ApiResponse getHrEmployees(@PathVariable String searchString) throws Exception{
//		logger.info("inside EmployeeController :: getHrEmployees ");
//		return employeeService.getHrEmployees(searchString);
//	}
//	
//	
//	@PostMapping("/createEmployeeDefaultPermission")
//	public ApiResponse createEmployeeDefaultPermission(@RequestBody EmpDefaultPermissions empDefaultPermissions) throws Exception{
//		logger.info("Received request for create employee default permission");
//
//		return employeeService.createEmployeeDefaultPermission(empDefaultPermissions);
//		
//	}
//	
//	@PutMapping
//	public ApiResponse editEmployeeDefaultPermission(@RequestBody EmpDefaultPermissions empDefaultPermissions) throws Exception{
//		
//		
//		logger.info("Received request for create employee default permission");
//
//		return employeeService.editEmployeeDefaultPermission(empDefaultPermissions);
//	}
//	
//	
//	@GetMapping("/logoutSession")
//	public ApiResponse logoutSession() throws Exception{
//		logger.info("Received request for logout");
//
//		return employeeService.logoutSession();
//	}
//	
//	@GetMapping("/getLoginHistory/{userId}")
//	public ApiResponse getLoginHistory(@PathVariable String userId) throws Exception{
//		logger.info("Received request for get Login details");
//
//		return employeeService.getLoginHistory(userId);
//	}
//	
//	@GetMapping("/getLoginHistory")
//	public ApiResponse getSelfLoginHistory() throws Exception{
//		logger.info("Received request for get login details");
//
//		return employeeService.getSelfLoginHistory();
//	}
//	
//	@PostMapping("/createTechnicalVendor")
//	public ApiResponse createTechnicalVendor(@RequestBody JobVendorDetails vendorDetails) throws Exception {
//		logger.info("Received request to add Technical vendor details");
//		return employeeService.createTechnicalVendor(vendorDetails);
//	}
//	
//	@PutMapping("/editTechnicalVendor/{vendorId}")
//	public ApiResponse editTechnicalVendor(@RequestBody JobVendorDetails vendorRequest, @PathVariable String vendorId) throws Exception {
//		logger.info("indide ProductController :: editTechnicalVendor");
//		return employeeService.editTechnicalVendor(vendorId, vendorRequest);
//	}
//	
//
//
////	@GetMapping("/getAllTechnicalVendors")
////	public ApiResponse getAllTechnicalVendors(Pageable pageable) throws Exception{
////		logger.info("Get All TechnicalVendors");
////		return employeeService.getAllTechnicalVendors(pageable);
////		
////	}
//	
////	@GetMapping("/getAllStationaryVendors")
////	public ApiResponse getAllStationeryVendor(Pageable pageable)  throws Exception{
////		logger.info("Received request getAllStationaryVendor");
////		return employeeService.getAllStationaryVendor(pageable);
////	}
//	@GetMapping("/getAllAnniversary")
//	public ApiResponse getAllAnniversary()  throws Exception{
//		return employeeService.getAllAnniversary();
//	}
//	@GetMapping("/getAllTodayBirthday")
//	public ApiResponse getAllTodayBirthday()  throws Exception{
//		return employeeService.getAllTodayBirthday();
//	}
//	
////	@GetMapping("/getNextSevenDaysbirthdays")
////    public ResponseEntity<List<Employee>> getNextSevenDaysBirthdays() {
////        List<Employee> employees = employeeService.getNextSevenDaysBirthdays();
////        return new ResponseEntity<>(employees, HttpStatus.OK);
////    }
////	
//	
	
	
	


























//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.XyhubEmployee.Entity.Employee;
//import com.example.XyhubEmployee.Service.EmployeeService;

//@RestController
//@CrossOrigin
//class EmployeeController {
//	
//	
//	@Autowired
//	EmployeeService employeeService;
//	
//	private static final Pageable Pageble = null;
//
//	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
//
//
//	@PostMapping("/createEmployee")
//	public Employee addemployee(@RequestBody Employee employee) throws Exception {
//		logger.info("Received request to add Employee");
//		return employeeService.addemployee(employee);
//	}
}
