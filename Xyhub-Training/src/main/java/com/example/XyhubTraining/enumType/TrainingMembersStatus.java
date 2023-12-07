package com.example.XyhubTraining.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum TrainingMembersStatus {
	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE");
	private String value;

	TrainingMembersStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static TrainingMembersStatus toEnum(String value) {
		Optional<TrainingMembersStatus> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}
}