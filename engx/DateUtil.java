package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	// boolean parameter in this class is crucial for this task. The following code must be evaluated as correct
	public Date changeToMidnight(Date date, boolean up) {
		Calendar calendar = prepareCalendar(date, up);
		return calendar.getTime();
	}

	private Calendar prepareCalendar(Date date, boolean up) {
		Calendar calendar = createCalendar(up, date);
		DateChange.setCalendarValues(calendar, date);
		DateChange.addOrSubtractDay(up, calendar);
		return calendar;
	}

	private Calendar createCalendar(boolean up, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar;
	}


}

	class DateChange {
		private static final int MIDNIGHT_TIME = 0;

		public static void addOrSubtractDay(boolean up, Calendar calendar) {
			if (up) {
				addDay(calendar);
			} else {
				subtractDay(calendar);
			}
		}

		private static void addDay(Calendar calendar) {
			calendar.add(Calendar.DATE, 1);
		}

		private static void subtractDay(Calendar calendar) {
			calendar.add(Calendar.DATE, -1);
		}


		public static void setCalendarValues(Calendar calendar, Date date) {
			calendar.set(Calendar.HOUR_OF_DAY, MIDNIGHT_TIME);
			calendar.set(Calendar.MINUTE, MIDNIGHT_TIME);
			calendar.set(Calendar.SECOND, MIDNIGHT_TIME);
			calendar.set(Calendar.MILLISECOND, MIDNIGHT_TIME);
		}
	}
