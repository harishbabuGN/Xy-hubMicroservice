package com.example.XyhubTraining.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum TrainingTopicStatus {
	
	COMPLETED("COMPLETED"),
	ONGOING("ONGOING"),
	ONHOLD("ONHOLD"),
	PENDING("PENDING"),
	INPROGRESS("INPROGRSS"),
	INITIATED("INITIATED");
	
	private String value;

	TrainingTopicStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static TrainingTopicStatus toEnum(String value) {
		Optional<TrainingTopicStatus> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}

}
