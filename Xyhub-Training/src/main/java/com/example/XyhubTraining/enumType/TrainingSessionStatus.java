package com.example.XyhubTraining.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum TrainingSessionStatus {
	ONGOING("ONGOING"),
	ONHOLD("ONHOLD"),
	COMPLETED("COMPLETED"),
	INITIATED("INITIATED");
	private String value;

	TrainingSessionStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static TrainingSessionStatus toEnum(String value) {
		Optional<TrainingSessionStatus> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}
	
	
}
