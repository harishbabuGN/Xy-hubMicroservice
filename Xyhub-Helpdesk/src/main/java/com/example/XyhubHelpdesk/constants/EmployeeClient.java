package com.example.XyhubHelpdesk.constants;

import java.io.Serializable;

import javax.persistence.JoinColumn;

import org.springframework.stereotype.Component;

import com.example.XyhubCommon.enumType.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeClient implements Serializable  {


		@JsonProperty("id")
	    private String id;
	    
		@JsonProperty("eId")
	    private String eId;
		
		@JsonProperty("email")
	    private String email;
		
		@JsonProperty("firstName")
	    private String firstName;
		
		@JsonProperty("lastName")
	    private String lastName;
		
		@JsonProperty("middleName")
	    private String middleName;
		
		@JsonProperty("mobileNumber")
	    private String mobileNumber;
		
		@JsonProperty("user_id")
		private UserClient userCredientials;
		
		@JsonProperty("userStatus")
		private UserStatus userStatus;
	    
	    
	    public EmployeeClient() {
			// TODO Auto-generated constructor stub
		}


		public UserClient getUserCredientials() {
			return userCredientials;
		}


		public void setUserCredientials(UserClient userCredientials) {
			this.userCredientials = userCredientials;
		}


		public UserStatus getUserStatus() {
			return userStatus;
		}


		public void setUserStatus(UserStatus userStatus) {
			this.userStatus = userStatus;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String geteId() {
			return eId;
		}


		public void seteId(String eId) {
			this.eId = eId;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getMiddleName() {
			return middleName;
		}


		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}


		public String getMobileNumber() {
			return mobileNumber;
		}


		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
	    

}
