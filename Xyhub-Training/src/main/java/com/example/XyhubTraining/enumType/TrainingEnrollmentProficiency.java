package com.example.XyhubTraining.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum TrainingEnrollmentProficiency {
	
	BEGINNER("BEGINNER"),
	INTERMEDIATE("INTERMEDIATE"),
	NEWBIE("NEWBIE"),
	ADVANCED("ADVANCED");
	private String value;

	TrainingEnrollmentProficiency(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static TrainingEnrollmentProficiency toEnum(String value) {
		Optional<TrainingEnrollmentProficiency> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}

}
