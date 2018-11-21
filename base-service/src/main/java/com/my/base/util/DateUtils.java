package com.my.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 日期工具类
 * 
 *
 */
public abstract class DateUtils {

	/** yyyy-MM-dd HH:mm:ss */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** yyyy年MM月dd日 HH点mm分ss秒 */
	public static final String CN_DATE_TIME_FORMAT = "yyyy年MM月dd日 HH点mm分ss秒";

	/**
	 * 日期格式[yyyy-MM-dd]
	 */
	public static final String DATA_FORMAT_PATTERN_0 = "yyyy-MM-dd";

	/**
	 * 日期格式[yyyyMMddhhmmssSSSSSS]
	 */
	public static final String DATA_FORMAT_PATTERN_1 = "yyyyMMddhhmmssSSSSSS";

	/**
	 * 日期格式yyyyMMdd]
	 */
	public static final String DATA_FORMAT_PATTERN_2 = "yyyyMMdd";

	/**
	 * 日期格式HHmmss]
	 */
	public static final String DATA_FORMAT_PATTERN_3 = "HHmmss";

	/**
	 * 日期格式[yyyyMMddHHmmss]
	 */
	public static final String DATA_FORMAT_PATTERN_4 = "yyyyMMddHHmmss";

	/**
	 * 日期格式[yyyyMMddhhmmssSSSSSS]
	 */
	public static final String DATA_FORMAT_PATTERN_5 = "yyyyMMddhhmmssSSS";

	/** yyyy/MM/dd HH:mm:ss */
	public static final String DATA_FORMAT_PATTERN_7 = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 日期格式[yyyyMMdd HHmmss]
	 */
	public static final String DATA_FORMAT_PATTERN_6 = "yyyyMMdd HHmmss";

	/**
	 * 日期格式[yyyyMMddhhmmssSSSSSSS]
	 */
	public static final String DATA_FORMAT_PATTERN_8 = "yyyyMMddhhmmssSSSSSSS";

	/**
	 * 日期格式[HH:mm:ss]
	 */
	public static final String DATA_FORMAT_PATTERN_9 = "HH:mm:ss";

	/**
	 * 日期格式[yyyy/MM/dd]
	 */
	public static final String DATA_FORMAT_PATTERN_10 = "yyyy/MM/dd";

	/**
	 * 日期格式[yyyy-MM-dd'T'HH:mm:ss]
	 */
	public static final String DATA_FORMAT_PATTERN_11 = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * 日期格式[MM月dd日]
	 */
	public static final String DATA_FORMAT_PATTERN_12 = "MM月dd日";

	/** 日期格式[yyyy.MM.dd] */
	public static final String DATA_FORMAT_PATTERN_13 = "yyyy.MM.dd";

	public static final String getDateTime(Date date) {
		return DateFormatUtils.format(date, CN_DATE_TIME_FORMAT);
	}

	public static final String getDateTime(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 两个string类型的日期比较大小
	 * 
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static int compareDate(String date1, String date2, String pattern) throws ParseException {
		DateFormat df = new SimpleDateFormat(pattern);
		Date dt1 = df.parse(date1);
		Date dt2 = df.parse(date2);
		if (dt1.getTime() > dt2.getTime()) {
			return 1;
		} else if (dt1.getTime() < dt2.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 两个string类型的日期比较大小
	 * 
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateByString(String date, String pattern) throws ParseException {
		DateFormat df = new SimpleDateFormat(pattern);
		Date dt = df.parse(date);
		return dt;
	}

	public static Date getNowTime() {
		return Calendar.getInstance().getTime();
	}
	
	public static Date getNeedTime(int hour,int minute,int second,int addDay,int ...args){
	    Calendar calendar = Calendar.getInstance();
	    if(addDay != 0){
	        calendar.add(Calendar.DATE,addDay);
	    }
	    calendar.set(Calendar.HOUR_OF_DAY,hour);
	    calendar.set(Calendar.MINUTE,minute);
	    calendar.set(Calendar.SECOND,second);
	    if(args.length==1){
	        calendar.set(Calendar.MILLISECOND,args[0]);
	    }
	    return calendar.getTime();
	}

	public static String getToday() {
		return DateFormatUtils.format(getNowTime(), DATA_FORMAT_PATTERN_2);
	}

	/**
	 * 返回当天剩余秒数
	 * 
	 * @return
	 */
	public static int getRestSecond() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		int second = DateUtils.subSecond(cal.getTime(), new Date());
		if (second <= 0) {
			second = 1;
		}
		return second;
	}

	/**
	 * 返回date1-dat2相差的秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int subSecond(Date date1, Date date2) {
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		int sub = (int) ((d1 - d2) / 1000);
		return sub;
	}

	/**
	 * 获取减去天数的日期
	 * 
	 * @param minusDays
	 * @return Date
	 */
	public static Date getMinusDate(LocalDate date, int minusDays) {
		return Date.from(date.minusDays(minusDays).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static void main(String[] args) {
		//System.out.println(getMinusDate(LocalDate.now(), 30));
		// String date = DateUtils.getDateTime(new Date(),
		// DateUtils.DATA_FORMAT_PATTERN_5);
		//
		// String s1 = "2018-08-02 09:40:42";
		//
		// String s2 = "2008-01-29 09:12:11";
		// int result = compareDate(s1, s2, DATE_TIME_FORMAT);
		// System.out.println(result);
	}

	// public static void main(String[] args) {
	// String date = DateUtils.getDateTime(new Date(),
	// DateUtils.DATA_FORMAT_PATTERN_5);
	//
	// String s1 = "2018-08-02 09:40:42";
	//
	// String s2 = "2008-01-29 09:12:11";
	// int result = compareDate(s1, s2, DATE_TIME_FORMAT);
	// System.out.println(result);
	// }
}
