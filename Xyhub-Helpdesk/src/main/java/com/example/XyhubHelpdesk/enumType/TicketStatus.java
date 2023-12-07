package com.example.XyhubHelpdesk.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum TicketStatus {
	INITIATED("INITIATED"),
	ASSIGNED("ASSIGNED"),
	
//	ONREVIEW("INPROGRESS"),
	ONHOLD("ONHOLD"),
	OPEN("OPEN"),
	COMPLETED("COMPLETED"),
	REOPEN("REOPEN"),
	CANCELLED("CANCELLED"),
	NOTCOMPLETED("NOTCOMPLETED"),
	INPROGRESS("INPROGRESS"),
	EDITED("EDITED");

	private String value;

	TicketStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static TicketStatus toEnum(String value) {
		Optional<TicketStatus> optional = Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		else
			throw new IllegalArgumentException(value + " is not a valid status");
	}
}