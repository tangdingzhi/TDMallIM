package com.td.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * UtilsTest 时间日期工具类,封装工作中常用的一些时间日期计算方法等 还可以提供更多的重载方法,用于时间的转化等
 */
public class DateUtil {

	public static final String YYYYMMDDHHMMSS = "yyyyMMdd HHmmss";
	public static final String HHMMFORMAT = "HH:mm";
	public static final String MMDDFORMAT = "MM-dd";
	public static final String YYYYFORMAT = "yyyy";
	public static final String YYYYCHINESEFORMAT = "yyyy年";
	public static final String YYYYMMDDFORMAT = "yyyy-MM-dd";
	public static final String FULLFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MMDDCHINESEFORMAT = "MM月dd日";
	public static final String YYYYMMDDCHINESEFORMAT = "yyyy年MM月dd日";
	public static final String FULLCHINESEFORMAT = "yyyy年MM月dd日 HH时mm分ss秒";
	private static final String[] WEEKS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 得到指定时间的时间日期格式
	 * 
	 * @param date
	 *            指定的时间
	 * @param format
	 *            时间日期格式
	 * @return
	 */
	public static String getFormatDateTime(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 判断是否是润年
	 * 
	 * @param date
	 *            指定的时间
	 * @return true:是润年,false:不是润年
	 */
	public static boolean isLeapYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return isLeapYear(cal.get(Calendar.YEAR));
	}

	/**
	 * 判断是否是润年
	 * 
	 * @param date
	 *            指定的年
	 * @return true:是润年,false:不是润年
	 */
	public static boolean isLeapYear(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	/**
	 * 判断指定的时间是否是今天
	 * 
	 * @param date
	 *            指定的时间
	 * @return true:是今天,false:非今天
	 */
	public static boolean isInToday(Date date) {
		boolean flag = false;
		Date now = new Date();
		String fullFormat = getFormatDateTime(now, DateUtil.YYYYMMDDFORMAT);
		String beginString = fullFormat + " 00:00:00";
		String endString = fullFormat + " 23:59:59";
		DateFormat df = new SimpleDateFormat(DateUtil.FULLFORMAT);
		try {
			Date beginTime = df.parse(beginString);
			Date endTime = df.parse(endString);
			flag = date.before(endTime) && date.after(beginTime);
		} catch (ParseException e) {
			LogUtil.warn(e.getMessage());
		}
		return flag;
	}

	/**
	 * 判断两时间是否是同一天
	 * 
	 * @param from
	 *            第一个时间点
	 * @param to
	 *            第二个时间点
	 * @return true:是同一天,false:非同一天
	 */
	public static boolean isSameDay(Date from, Date to) {
		boolean isSameDay = false;
		DateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDDFORMAT);
		String firstDate = df.format(from);
		String secondDate = df.format(to);
		isSameDay = firstDate.equals(secondDate);
		return isSameDay;
	}

	/**
	 * 求出指定的时间那天是星期几
	 * 
	 * @param date
	 *            指定的时间
	 * @return 星期X
	 */
	public static String getWeekString(Date date) {
		return DateUtil.WEEKS[getWeek(date) - 1];
	}

	/**
	 * 求出指定时间那天是星期几
	 * 
	 * @param date
	 *            指定的时间
	 * @return 1-7
	 */
	public static int getWeek(Date date) {
		int week = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		week = cal.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	/**
	 * 取得指定时间离现在是多少时间以前，如：3秒前,2小时前等 注意：此计算方法不是精确的
	 * 
	 * @param date
	 *            已有的指定时间
	 * @return 时间段描述
	 */
	public static String getAgoTimeString(Date date) {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date agoTime = cal.getTime();
		long mtime = now.getTime() - agoTime.getTime();
		String str = "";
		long stime = mtime / 1000;
		long minute = 60l;
		long hour = 60 * minute;
		long day = 24 * hour;
		long weeks = 7 * day;
		long months = 100 * day;
		if (stime < minute) {
			long time_value = stime;
			if (time_value <= 0) {
				time_value = 1;
			}
			str = time_value + "秒前";
		} else if (stime >= minute && stime < hour) {
			long time_value = stime / minute;
			if (time_value <= 0) {
				time_value = 1;
			}
			str = time_value + "分前";
		} else if (stime >= hour && stime < day) {
			long time_value = stime / hour;
			if (time_value <= 0) {
				time_value = 1;
			}
			str = time_value + "小时前";
		} else if (stime >= day && stime < weeks) {
			long time_value = stime / day;
			if (time_value <= 0) {
				time_value = 1;
			}
			str = time_value + "天前";
		} else if (stime >= weeks && stime < months) {
			DateFormat df = new SimpleDateFormat(DateUtil.MMDDFORMAT);
			str = df.format(date);
		} else {
			DateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDDFORMAT);
			str = df.format(date);
		}
		return str;
	}

	/**
	 * 判断指定时间是否是周末
	 * 
	 * @param date
	 *            指定的时间
	 * @return true:是周末,false:非周末
	 */
	public static boolean isWeeks(Date date) {
		boolean isWeek = false;
		isWeek = (getWeek(date) - 1 == 0 || getWeek(date) - 1 == 6);
		return isWeek;
	}

	/**
	 * 得到今天的最开始时间
	 * 
	 * @return 今天的最开始时间
	 */
	public static Date getTodayBeginTime() {
		String beginString = DateUtil.YYYYMMDDFORMAT + " 00:00:00";
		DateFormat df = new SimpleDateFormat(beginString);
		Date beginTime = new Date();
		try {
			beginTime = df.parse(getFormatDateTime(beginTime, beginString));
		} catch (ParseException e) {
			LogUtil.warn(e.getLocalizedMessage());
		}
		return beginTime;
	}

	/**
	 * 得到今天的最后结束时间
	 * 
	 * @return 今天的最后时间
	 */
	public static Date getTodayEndTime() {
		String endString = DateUtil.YYYYMMDDFORMAT + " 23:59:59";
		DateFormat df = new SimpleDateFormat(endString);
		Date endTime = new Date();
		try {
			endTime = df.parse(getFormatDateTime(endTime, endString));
		} catch (ParseException e) {
			LogUtil.warn(e.getMessage());
		}
		return endTime;
	}

	/**
	 * 取得本周的开始时间
	 * 
	 * @return 本周的开始时间
	 */
	public static Date getThisWeekBeginTime() {
		Date beginTime = null;
		Calendar cal = Calendar.getInstance();
		int week = getWeek(cal.getTime());
		week = week - 1;
		int days = 0;
		if (week == 0) {
			days = 6;
		} else {
			days = week - 1;
		}
		cal.add(Calendar.DAY_OF_MONTH, -days);
		beginTime = cal.getTime();
		return beginTime;
	}

	/**
	 * 取得本周的开始日期
	 * 
	 * @param format
	 *            时间的格式
	 * @return 指定格式的本周最开始时间
	 */
	public static String getThisWeekBeginTimeString(String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(getThisWeekBeginTime());
	}

	/**
	 * 取得本周的结束时间
	 * 
	 * @return 本周的结束时间
	 */
	public static Date getThisWeekEndTime() {
		Date endTime = null;
		Calendar cal = Calendar.getInstance();
		int week = getWeek(cal.getTime());
		week = week - 1;
		int days = 0;
		if (week != 0) {
			days = 7 - week;
		}
		cal.add(Calendar.DAY_OF_MONTH, days);
		endTime = cal.getTime();
		return endTime;
	}

	/**
	 * 取得本周的结束日期
	 * 
	 * @param format
	 *            时间的格式
	 * @return 指定格式的本周结束时间
	 */
	public static String getThisWeekEndTimeString(String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(getThisWeekEndTime());
	}

	/**
	 * 取得两时间相差的天数
	 * 
	 * @param from
	 *            第一个时间
	 * @param to
	 *            第二个时间
	 * @return 相差的天数
	 */
	public static long getBetweenDays(Date from, Date to) {
		long days;
		long dayTime = 24l * 60l * 60l * 1000l;
		long fromTime = from.getTime();
		long toTime = to.getTime();
		long times = Math.abs(fromTime - toTime);
		days = times / dayTime;
		return days;
	}

	/**
	 * 取得两时间相差的小时数
	 * 
	 * @param from
	 *            第一个时间
	 * @param to
	 *            第二个时间
	 * @return 相差的小时数
	 */
	public static long getBetweenHours(Date from, Date to) {
		long hours;
		long hourTime = 60l * 60l * 1000l;
		long fromTime = from.getTime();
		long toTime = to.getTime();
		long times = Math.abs(fromTime - toTime);
		hours = times / hourTime;
		return hours;
	}

	/**
	 * 取得在指定时间上加减days天后的时间
	 * 
	 * @param date
	 *            指定的时间
	 * @param days
	 *            天数,正为加，负为减
	 * @return 在指定时间上加减days天后的时间
	 */
	public static Date addDays(Date date, int days) {
		Date time = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		time = cal.getTime();
		return time;
	}

	/**
	 * 取得在指定时间上加减months月后的时间
	 * 
	 * @param date
	 *            指定时间
	 * @param months
	 *            月数，正为加，负为减
	 * @return 在指定时间上加减months月后的时间
	 */
	public static Date addMonths(Date date, int months) {
		Date time = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, months);
		time = cal.getTime();
		return time;
	}

	/**
	 * 取得在指定时间上加减years年后的时间
	 * 
	 * @param date
	 *            指定时间
	 * @param years
	 *            年数，正为加，负为减
	 * @return 在指定时间上加减years年后的时间
	 */
	public static Date addYears(Date date, int years) {
		Date time = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, years);
		time = cal.getTime();
		return time;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(getFormatDateTime(new Date(), DateUtil.FULLCHINESEFORMAT));
//		System.out.println(isLeapYear(new Date()));
//		Calendar cal = Calendar.getInstance();
//		System.out.println(isInToday(cal.getTime()));
//		Calendar cal2 = Calendar.getInstance();
//		cal2.set(2011, 06, 05);
//		System.out.println(isSameDay(cal.getTime(), cal2.getTime()));
//		System.out.println(getWeekString(new Date()));
//		DateFormat df = new SimpleDateFormat(DateUtil.FULLFORMAT);
//		String fullString = "2011-06-03 22:37:20";
//		try {
//			Date fulldate = df.parse(fullString);
//			System.out.println(getBetweenDays(fulldate, cal.getTime()));
//			System.out.println(getAgoTimeString(fulldate));
//			System.out.println(isWeeks(fulldate));
//		} catch (ParseException e) {
//			LogUtil.warn(e.getMessage());
//		}
//
//		System.out.println(getThisWeekBeginTimeString(DateUtil.YYYYMMDDCHINESEFORMAT));
//		System.out.println(getThisWeekEndTimeString(DateUtil.YYYYMMDDCHINESEFORMAT));
//		System.out.println(addDays(new Date(), 3));
//		System.out.println(addDays(new Date(), -3));
//		System.out.println(addMonths(new Date(), 2));
//		System.out.println(addMonths(new Date(), -2));
//		System.out.println(addYears(new Date(), 1));
//		System.out.println(addYears(new Date(), -1));

	}
}