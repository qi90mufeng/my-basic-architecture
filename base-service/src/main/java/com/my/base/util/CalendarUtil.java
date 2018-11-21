package com.my.base.util;

import java.util.Calendar;

public class CalendarUtil {

	public static Long getNowInMillis() {
		return Calendar.getInstance().getTimeInMillis();
	}
}
