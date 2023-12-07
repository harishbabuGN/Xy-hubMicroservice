package com.example.XyhubEmployee.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum DesignationEnum {

	
	ACTIVE("ACTIVE"),

	INACTIVE("INACTIVE");

	private String value;

	DesignationEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static DesignationEnum toEnum(String value) {
		Optional<DesignationEnum> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}

		
	}


