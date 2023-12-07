package com.example.XyhubEmployee.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum SalaryStatus {
	
	ACTIVE("ACTIVE"),

	INACTIVE("INACTIVE"),
	
	OFFLINE("OFFLINE");

	private String value;
	
	
	SalaryStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static SalaryStatus toEnum(String value) {
		Optional<SalaryStatus> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}

}
