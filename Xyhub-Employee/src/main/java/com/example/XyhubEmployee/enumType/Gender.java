package com.example.XyhubEmployee.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {

	
	MALE("MALE"),

	FEMALE("FEMALE"),
	
	OTHERS("OTHERS");
	
	
	private String value;

	Gender(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static Gender toEnum(String value) {
		Optional<Gender> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}
	
}
