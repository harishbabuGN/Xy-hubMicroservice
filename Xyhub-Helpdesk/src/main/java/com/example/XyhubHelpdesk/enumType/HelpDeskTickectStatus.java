package com.example.XyhubHelpdesk.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum HelpDeskTickectStatus {

	OPEN("OPEN"), 
	COMPLETED("COMPLETED"),
	CANCELLED("CANCELLED"),
	INPROGRESS("INPROGRESS"),
	RESOLVED("RESOLVED"),
	REASSIGN("REASSIGN"),
	REOPEN("REOPEN");
	//Open, In-Progress, Cancelled, Resolved, Closed
	private String value;

	HelpDeskTickectStatus(String value) {
			this.value = value;
		}

	public String value() {
		return this.value;
	}

	public static HelpDeskTickectStatus toEnum(String value) {
		Optional<HelpDeskTickectStatus> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}
}