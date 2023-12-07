package com.example.XyhubEmployee.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DateUtils {

	public static String getTimeDifference(Date startTime, Date endTime) {

		long timeDifference = endTime.getTime() - startTime.getTime();

		long seconds = timeDifference / 1000;
		long minutes = seconds / 60;
		int hours = (int) (minutes % 60);

		return String.format("%02d", minutes / 60) + ":" + String.format("%02d", minutes % 60);

	}

	public static boolean getDateDifference(Date startDate, Date endDate) {

		long timeDifference = endDate.getTime() - startDate.getTime();

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;
		long mothsInMilli = daysInMilli * 30;
		long yearInMilli = mothsInMilli * 12;

		long elapsedYear = timeDifference / yearInMilli;

		LocalDate start = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(startDate));
		LocalDate end = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(endDate));

		int years = Period.between(start, end).getYears();

		if (years < 14) {
			return true;
		}
		return false;
	}

	public static String parseAndFormatDate(String date) {
		String formatedDate = "";
		try {
			Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			formatedDate = new SimpleDateFormat("dd-MM-yyyy").format(parsedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatedDate;
	}

	public static Date parseDate(String date) {
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parsedDate;
	}

	public static boolean calculateFifteenMinutesTimeDIfference(String date) {
		Date reminderDate = parseDate(date);
		if (reminderDate != null) {
			long elapsedTime = new Date().getTime() - reminderDate.getTime();

			long diffMinutes = elapsedTime / (60 * 1000) % 60;

			if (diffMinutes <= 15) {
				return true;
			}

		}

		return false;
	}
}