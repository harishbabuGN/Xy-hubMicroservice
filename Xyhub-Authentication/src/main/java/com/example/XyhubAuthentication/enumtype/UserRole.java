//package com.example.XyhubAuthentication.enumtype;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//public enum UserRole {
//
//	DAYDROP_ADMIN("DAYDROP_ADMIN"),
//	
//	CONSUMER("CONSUMER"),
//	
//	DELIVERY_EXECUTIVE("DELIVERY_EXECUTIVE");
//	
//	
//
//
//	private String value;
//
//	UserRole(String value) {
//		this.value = value;
//	}
//
//	public String value() {
//		return this.value;
//	}
//
//	public static UserRole toEnum(String value) {
//		Optional<UserRole> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
//				.findFirst();
//		if (optional.isPresent())
//			return optional.get();
//		else
//			throw new IllegalArgumentException(value + " is not a valid status");
//	}
//
//}
//
