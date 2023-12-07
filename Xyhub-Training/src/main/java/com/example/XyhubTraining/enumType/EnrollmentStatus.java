package com.example.XyhubTraining.enumType;

import java.util.Arrays;
import java.util.Optional;
public enum EnrollmentStatus {
	PENDING("PENDING"),
	ACCEPTED("ACCEPTED"),
	REJECTED("REJECTED");
	private String value;

	EnrollmentStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static EnrollmentStatus toEnum(String value) {
		Optional<EnrollmentStatus> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}

}
