package com.example.XyhubEmployee.Service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.XyhubCommon.Communication.PushNotificationCall;
import com.example.XyhubCommon.Communication.PushNotificationRequest;
import com.example.XyhubCommon.Component.CurrentUser;
import com.example.XyhubCommon.Config.PermissionConfig;
import com.example.XyhubCommon.Util.ResponseMessages;
import com.example.XyhubCommon.enumType.NotificationType;
import com.example.XyhubCommon.enumType.UserStatus;
import com.example.XyhubEmployee.Entity.CompanyLocation;
import com.example.XyhubEmployee.Entity.CompanyWings;
import com.example.XyhubEmployee.Entity.Designation;
import com.example.XyhubEmployee.Entity.Employee;
import com.example.XyhubEmployee.Entity.EmployeePermission;
import com.example.XyhubEmployee.Entity.Notifications;
import com.example.XyhubEmployee.Entity.UserPermissions;
import com.example.XyhubEmployee.Repository.CompanyLocationRepository;
import com.example.XyhubEmployee.Repository.CompanyWingsRepository;
import com.example.XyhubEmployee.Repository.DesignationRepository;
import com.example.XyhubEmployee.Repository.EmployeePermissionRepository;
import com.example.XyhubEmployee.Repository.EmployeeRepository;
import com.example.XyhubEmployee.Repository.UserPermissionRepository;
import com.example.XyhubEmployee.Repository.UserRepository;
import com.example.XyhubEmployee.Service.EmployeeService;
import com.example.XyhubEmployee.Service.NotificationService;
//import com.example.XyhubEmployee.admin.model.CurrentUser;
import com.example.XyhubEmployee.admin.model.User;
import com.example.XyhubEmployee.apiresponses.ApiResponse;
import com.example.XyhubEmployee.config.EmployeePermissionConfig;
import com.example.XyhubEmployee.email.EmailService;
//import com.example.XyhubEmployee.enumType.UserStatus;
import com.example.XyhubEmployee.util.DateUtils;
import com.example.XyhubEmployee.util.EmployeeUtil;

@Service
public class EmpoloyeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmpoloyeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyLocationRepository companyLocationRepository;
	
	@Autowired
	EmployeePermissionConfig empPerConfig;
	
	
	@Autowired
	DesignationRepository designationRepository;
	
	@Autowired
	CurrentUser currentUser;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CurrentUser userDetail;
	
	@Autowired
	PermissionConfig permissionConfig;
	
	@Autowired
	PushNotificationCall pushNotificationCall;
	
	@Autowired
	PushNotificationRequest pushNotificationRequest;
	
	@Autowired
	NotificationService notificationService;
	
	
	@Autowired
	CompanyWingsRepository wingRepo;
	
//	@Value("${APPLICATION_URL}")
//	private String application_url;
//
//	@Value("${image-base-url}")
//	private String ticketAttachmentBaseUrl;
	
	@Autowired
	UserPermissionRepository userPermissionConfig;
	
	@Autowired
	EmployeePermissionRepository empPermissionRepo;
	

	@Autowired
	EmailService emailService;
	

	@SuppressWarnings("unused")
	@Override
	public ApiResponse addemployee(Employee employee) throws Exception {

		ApiResponse response = new ApiResponse(false);

//		if (!empPerConfig.isHavingpersmission("empAdmin")) {
//			response.setSuccess(false);
//			response.setMessage("Not authorised to create employee");
//			return response;
//		}

		response = validateEmployee(employee);

//		if (response.getMessage() != null && response.getMessage() != "") {
//			return response;
//		}
		// Email Validation starts here

		if (employee.getEmail() == null || employee.getEmail().equals("")) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.MAILID_MAN);
			return response;
		}
		if (!emailValidation(employee.getEmail())) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.INVAL_MAIL_ID);
			return response;
		}

		String email = employeeRepository.filterByEmail(employee.getEmail());
		if (email != null) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.MAIL_ID_EXI);
			return response;
		}

		String userId = userRepository.getUserId(employee.getEmail());
		if (userId != null) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.MAIL_ID_EXI);
			return response;
		}

		System.out.println("username::" + currentUser.getName());

//		ApiResponse salaryResponse = validateEmployeeSalary(employee);
//		
//		if (salaryResponse.getMessage() != null && salaryResponse.getMessage() != "") {
//			return salaryResponse;
//		}

//		if (response.isSuccess()) {
		try {

			if (!employeeRepository.getbyEmpId(employee.geteId()).isEmpty()) {

				response.setSuccess(false);
				response.setMessage(ResponseMessages.EMP_CODE);
				return response;
			}

			User user = new User();
			user.setUsername(employee.getEmail());
			String encodedPassword = new BCryptPasswordEncoder().encode(employee.getPassword());
			user.setPassword(encodedPassword);

			employee.setFirstName(employee.getFirstName().trim());
			String name = employee.getFirstName();
			String firstLetter = name.substring(0, 1);
			String remainingLetters = name.substring(1, name.length());
			firstLetter = firstLetter.toUpperCase();
			employee.setFirstName(firstLetter + remainingLetters);

			user.setName(employee.getFirstName() + " " + employee.getLastName());

			// Employee employeere=new Employee();
//				Role role = roleRepository.getById(employee.getRoleId());
//				user.setUserRole(role != null ? role.getRoleName() : null);

			Integer permission = permissionConfig.setDefaultPermissions("INFRA_ADMIN");
			user.setPermission(permission);
			user.setStatus(UserStatus.ACTIVE);
			//System.out.println(user.getEmail() + "::" + user.getUsername() + "::" + user.getCreatedAt());
			User newUser = userRepository.save(user);
			UserPermissions permissions = new UserPermissions();
			permissions.setEmpModPermission(permissionConfig.getEMPLOYEES_PERMISSION());
			permissions.setProjectModPermission(permissionConfig.getPROJECTS_PERMISSION());
			permissions.setTicketModPermission(permissionConfig.getTICKETS_PERMISSION());
			permissions.setJobOpeningModPermission(permissionConfig.getJOBOPENINGS_PERMISSION());
			permissions.setJobInterviewsModPermission(permissionConfig.getJOBINTERVIEWS_PERMISSION());
			permissions.setJobAppModPermission(permissionConfig.getJOBAPPLICATIONS_PERMISSION());
			permissions.setJobOfferModPermission(permissionConfig.getJOBOFFERS_PERMISSION());
			permissions.setJobVendorsModPermission(permissionConfig.getJOBVENDORS_PERMISSION());
			permissions.setUserId(newUser.getId());
			userPermissionConfig.save(permissions);
			employee.setCreatedBy(currentUser.getUserId());
			employee.setUpdatedBy(currentUser.getUserId());
			CompanyWings wing = wingRepo.getWingById(employee.getWings().getId());
			employee.setWings(wing);
			employee.setCreatedAt(new Date());
			employee.setLastUpdatedAt(new Date());
			employee.setUserCredientials(user);
//			employee.setProfileUrl(ticketAttachmentBaseUrl + "/user-default-pic.png");
			employee.setDateOfJoin(employee.getDateOfJoin());
			employee.setDateOfBirth(employee.getDateOfBirth());
			employee.setAlternateMobileNo(employee.getAlternateMobileNo());
			employee.setGender(employee.getGender());
			Employee employeeNew = employeeRepository.save(employee);
			User useredit = userRepository.getUserById(user.getId());
//					getById();
			useredit.setScopeId(employeeNew.getUserCredientials().getId());
			// user.setUserRole("HR"); // setting default role
			userRepository.save(useredit);

			// New Permissions
			EmployeePermission empPermission = new EmployeePermission();
			empPermission.setUserId(employeeNew.getUserCredientials().getId());
			empPermissionRepo.save(empPermission);

			// Salary Create
//				EmployeeSalary employeeSalary = new EmployeeSalary();
//				employeeSalary.setEmployeeId(employeeNew.getUserCredientials().getId());
//				employeeSalary.setSalStatus(SalaryStatus.ACTIVE);
//				employeeSalary
//						.setGrossSalary(AESEncryptionDecryption.encrypt(employee.getEmployeeSalary().getGrossSalary()));
//				employeeSalary
//						.setNetSalary(AESEncryptionDecryption.encrypt(employee.getEmployeeSalary().getNetSalary()));
//				employeeSalary.setCreatedBy(currentUser.getUserId());
//				employeeSalary.setUpdatedBy(currentUser.getUserId());
//				employeeSalary.setCreatedAt(new Date());
//				employeeSalary.setLastUpdatedAt(new Date());
//				employeeSalaryRepository.save(employeeSalary);

			// sending notification starts here..!

			List<Map> EmployeeList = employeeRepository.getEmployeeBYReportingToId(employee.getReportingTo());

			if (!EmployeeList.isEmpty()) {

				for (Map employeeNotification : EmployeeList) {
					Map request = new HashMap<>();
					request.put("id", employeeNotification.get("id"));
					request.put("uid", employeeNotification.get("uid"));
					request.put("title", "EMPLOYEE CREATED");
					request.put("body", " employee Created - " + employeeNew.getFirstName());
					pushNotificationCall.restCallToNotification(pushNotificationRequest.PushNotification(request, 12,
							NotificationType.EMPLOYEE_CREATED.toString()));

				}
				// inserting notification details
				Notifications notifications = new Notifications();
				notifications.setNotificationDesc("employee created - " + employeeNew.getFirstName());
				notifications.setNotificationType(NotificationType.EMPLOYEE_CREATED);
				notifications.setSenderId(employeeNew.getReportingTo());
				notifications.setReceiverId(userDetail.getUserId());
				notifications.setSeenStatus(false);
				notifications.setCreatedBy(userDetail.getUserId());
				notifications.setCreatedAt(new Date());
				notifications.setUpdatedBy(userDetail.getUserId());
				notifications.setLastUpdatedAt(new Date());

				notificationService.createNotification(notifications);
			}
			UUID uuid = UUID.randomUUID();
			String uuidAsString = uuid.toString();

			if (employeeNew != null & false) {
				String name2 = null;

				HashMap mailDetails = new HashMap();
				mailDetails.put("toEmail", employeeNew.getEmail());
				mailDetails.put("subject", name + ", " + "Here's your new PASSWORD");
				mailDetails.put("message", "Hi " + name2
						+ ", \n\n We received a request to reset the password for your Account. \n\n Here's your new PASSWORD Link is: "
//						+ application_url + "/update-password" + "?key=" + uuidAsString
						+ "\n\n Thanks for helping us keep your account secure.\n\n Xyram Software Solutions Pvt Ltd.");
				emailService.sendMail(mailDetails);
			}
			// end of the notification part...!

			response.setSuccess(true);
			response.setMessage(ResponseMessages.EMPLOYEE_ADDED);
			Map content = new HashMap();
			content.put("employeeId", employeeNew.geteId());
			response.setContent(content);

		} catch (ResponseStatusException re) {
			throw new ResponseStatusException(re.getStatus(), re.getReason());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
		}

		return response;

//		}
//		}
	}
	private ApiResponse validateEmployee(Employee employee) throws Exception {
		ApiResponse response = new ApiResponse(true);

		String regex = "[a-z A-Z]+";
		if (!emailValidation(employee.getEmail())) {
			response.setMessage(ResponseMessages.EMAIL_INVALID);
			response.setSuccess(false);
			return response;
		}

		if (employee.getFirstName() == null || employee.getFirstName().equals("")
				|| employee.getFirstName().length() < 3) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.FIRST_NAME_MAN);
			return response;
		}

		if (!employee.getFirstName().matches(regex)) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.FIRST_NAME_CHAR);
			return response;
		}

		if (employee.getFirstName().length() < 3) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.FIRST_NAME_LEN);
			return response;
		}

		if (employee.getLastName() == null || employee.getLastName().equals("")
				|| employee.getLastName().length() <= 0) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.LAST_NAME_MAN);
			return response;
		}
		if (!employee.getLastName().matches(regex)) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.LAST_NAME_CHAR);
			return response;
		}

		if (employee.getLastName().length() < 1) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.LAST_NAME_LEN);
			return response;
		}

		if (employee.getLocation() == null || employee.getLocation().equals("")) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.LOC_MAN);
			return response;
		} else {

//			CompanyLocation companyLocation = companyLocationRepository.getCompanyLocations(employee.getLocation());
//			if (companyLocation != null) {
//				employee.setLocation(employee.getLocation());
//			}
//			if (companyLocation == null) {
//				response.setSuccess(false);
//				response.setMessage(ResponseMessages.LOC_NOT_VALID);
//				return response;
//			}
		}

//		if (employee.getRoleId() == null || employee.getRoleId().equals("")) {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.ROLE_ID_MAN);
//		} else {
//
//			Role role = roleRepository.getRoleName(employee.getRoleId());
//			if (role == null) {
//				response.setSuccess(false);
//				response.setMessage(ResponseMessages.ROLE_ID_NOT_VAL);
//				return response;
//			}
//
//		}

		if (employee.getDesignationId() == null || employee.getDesignationId().equals("")) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.DES_MAN);
			return response;
		} else {

			Designation designation = designationRepository.getDesignationNames(employee.getDesignationId());
			if (designation == null) {
				response.setSuccess(false);
				response.setMessage(ResponseMessages.DES_ID_NOT_VAL);
				return response;
			}
		}

		if (employee.getPosition() == null || employee.getPosition().equals("")) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.POSITION_MAN);
			return response;
		}

		if (employee.getPosition() != null) {
			boolean isExist = false;

			for (String position : EmployeeUtil.position) {
				if (position.equalsIgnoreCase(employee.getPosition())) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				response.setSuccess(false);
				response.setMessage(ResponseMessages.POSITION_NOT_VAL);
				return response;
			}

		}

		if (employee.getReportingTo() != null && employee.getReportingTo().length() > 0) {
			Employee empObj = employeeRepository.getByEmpIdE(employee.getReportingTo());
			if (empObj == null) {
				response.setSuccess(false);
				response.setMessage(ResponseMessages.NOT_VALID);
				return response;
			}

		}

		if (employee.getWings() == null || employee.getWings().getId().equals("")) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.WING_MAN);
			return response;
		}
		if (employee.getWings() != null && employee.getWings().getId() != null) {
			CompanyWings companyWings = wingRepo.getWingByIds(employee.getWings().getId());

			if (companyWings != null) {
				employee.setWings(companyWings);
			} else {
				response.setSuccess(false);
				response.setMessage(ResponseMessages.WING_NOT_EXI);
				return response;
			}
		}

		if (employee.getMobileNumber() == null || employee.getMobileNumber().equals("")) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.MOB_NUM_MAN);
			return response;

		}

		else if (employee.getMobileNumber().length() != 10) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.INCORRECT_MOB);
			return response;
		}

		if (employee.getAlternateMobileNo() != null && !employee.getAlternateMobileNo().equals("")) {
			if (employee.getAlternateMobileNo().length() != 10) {
				response.setSuccess(false);
				response.setMessage(employee.getAlternateMobileNo() + " " + ResponseMessages.INCORRECT_MOB);
				return response;
			}
		}

		if (employee.getDateOfBirth() != null && !employee.getDateOfBirth().equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(employee.getDateOfBirth());
			Date tmDate = null;
			try {
				tmDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
				Date now = new Date();
				if (DateUtils.getDateDifference(tmDate, now)) {

					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Date of birth must be greater than 14 year");
				}

			} catch (ParseException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date format should be 'yyyy-MM-dd'");

				// response.setSuccess(false);
				// response.setMessage(ResponseMessages.DOJ_NOT_VAL);
				// return response;
			}
		} 
			//else {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.DOB_MAN);
//			return response;
//		}

//		if (employee.getDateOfJoin() != null && !employee.getDateOfJoin().equals("")) {
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String strDate = dateFormat.format(employee.getDateOfJoin());
//			String strDob = dateFormat.format(employee.getDateOfBirth());
//			Date doj = null;
//			Date dob = null;
//			try {
//				doj = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
//				dob = new SimpleDateFormat("yyyy-MM-dd").parse(strDob);
//				if (DateUtils.getDateDifference(dob, doj)) {
//
//					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//							"Difference between date of birth and date of join should be 14 years");
//
//				}
//
//			} catch (ParseException e) {
//				e.printStackTrace();
//				throw new Exception("Date format should be 'yyyy-MM-dd'");
//				// response.setSuccess(false);
//				// response.setMessage(ResponseMessages.DOJ_NOT_VAL);
//				// return response;
//			}
//		} else {
//			response.setSuccess(false);
//			response.setMessage(ResponseMessages.Join_date_man);
//			return response;
//		}

		if (employee.getGender() == null) {
			response.setSuccess(false);
			response.setMessage(ResponseMessages.GENDER_MAN);
			return response;
		}

		response.setSuccess(true);
		return response;
	}
	
	private boolean emailValidation(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
	@Override
	public Employee isEmployeeActive(String userId) {
		
		return employeeRepository.isEmployeeActive(userId);
	}
	@Override
	public Employee getbyUserId(String userId) {
		
		return employeeRepository.getbyUserId(userId);
	}

}















//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.XyhubEmployee.Entity.Employee;
//import com.example.XyhubEmployee.Repository.EmployeeRepository;
//import com.example.XyhubEmployee.Repository.UserRepository;
//import com.example.XyhubEmployee.Service.EmployeeService;
//import com.example.XyhubEmployee.admin.model.User;
//
//@Service
//public class EmpoloyeeServiceImpl implements EmployeeService {
//
//	@Autowired
//	EmployeeRepository employeeRepository;
//
//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Autowired
//	UserRepository userRepository;
//	public Employee addemployee(Employee employee) throws Exception {
//		System.out.println(this.passwordEncoder.encode(employee.getPassword()));
//
//		User user = new User();
//		user.setEmail(employee.getEmail());
//		user.setUsername(employee.getEmail());
//		user.setPassword(this.passwordEncoder.encode(employee.getPassword()));
//		userRepository.save(user);
//		employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));
//		return employeeRepository.save(employee);
//
//	}
//
//}
