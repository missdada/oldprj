package manytag.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String patternLong = "yyyy-MM-dd HH:mm:ss";
	public static final String patternShort = "yyyy-MM-dd";

	public final static int INTERVAL_DAY = 1;
	public final static int INTERVAL_WEEK = 2;
	public final static int INTERVAL_MONTH = 3;
	public final static int INTERVAL_YEAR = 4;
	public final static int INTERVAL_HOUR = 5;
	public final static int INTERVAL_MINUTE = 6;
	public final static int INTERVAL_SECOND = 7;

	/**
	 * 增加时间
	 * 
	 * @param interval [INTERVAL_DAY,INTERVAL_WEEK,INTERVAL_MONTH,INTERVAL_YEAR,
	 *            INTERVAL_HOUR,INTERVAL_MINUTE]
	 * @param date
	 * @param n 可以为负数
	 * @return
	 */
	public static Date dateAdd(int interval, Date date, int n) {
		long time = (date.getTime() / 1000); // 单位秒
		switch (interval) {
		case INTERVAL_DAY:
			time = time + n * 86400;// 60 * 60 * 24;
			break;
		case INTERVAL_WEEK:
			time = time + n * 604800;// 60 * 60 * 24 * 7;
			break;
		case INTERVAL_MONTH:
			time = time + n * 2678400;// 60 * 60 * 24 * 31;
			break;
		case INTERVAL_YEAR:
			time = time + n * 31536000;// 60 * 60 * 24 * 365;
			break;
		case INTERVAL_HOUR:
			time = time + n * 3600;// 60 * 60 ;
			break;
		case INTERVAL_MINUTE:
			time = time + n * 60;
			break;
		case INTERVAL_SECOND:
			time = time + n;
			break;
		default:
		}

		Date result = new Date();
		result.setTime(time * 1000);
		return result;
	}

	public static String longToDateStr(long value) {
		return longToDateStr(value, patternLong);
	}

	/*
	public static String longToDateShortStr(long value) {
		return longToDateStr(value, patternShort);
	}
	*/

	public static String longToDateStr(long value, boolean out0) {
		if (out0) {
			int ymd = (int) (value / 1000000);
			long ymdl = ymd * 1000000L;
			if (ymdl != value) {
				return longToDateStr(value);
			} else {
				return longToDateStryMd(value);
			}
		} else {
			return longToDateStr(value);
		}
	}

	public static String longToDateStryMd(long value) {
		return longToDateStr(value, patternShort);
	}

	public static String longToDateStr(long value, String pattern) {
		if (value == 0) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(value);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}

	public static long dateStrToLong(String str) {
		if (str == null || "".equals(str.trim())) {
			return 0;
		}
		if (str.trim().length() == 10) {
			str = str.trim() + " 00:00:00";
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(patternLong);
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date.getTime();
	}
	
	public static Date StrToDate(String str) {
		if (str == null || "".equals(str.trim())) {
			return null;
		}
		if (str.trim().length() == 10) {
			str = str.trim() + " 00:00:00";
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(patternLong);
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	public static String DateToStr(Date date) {
		if (date == null) {
			return null;
		}
		String dateStr="";
		SimpleDateFormat sdf = new SimpleDateFormat(patternLong);
		dateStr = sdf.format(date);
		return dateStr;
	}

	public static String getNow(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}
}