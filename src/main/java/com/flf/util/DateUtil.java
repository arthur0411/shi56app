/**
 * 
 */
package com.flf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 * 
 * @author SevenWong<br>
 * @date 2016年8月10日下午3:37:13
 */
public class DateUtil {

	public static SimpleDateFormat sdf = new SimpleDateFormat();

	/**
	 * 
	 * @author SevenWong<br>
	 * @date 2016年8月10日下午3:21:02
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String datetime2Str(Date date) {
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 
	 * @author SevenWong<br>
	 * @date 2016年8月10日下午3:35:14
	 * @param date
	 * @param millisecond
	 *            为true时返回毫秒值
	 * @return yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd HH:mm:ss:SSS
	 */
	public static String datetime2Str(Date date, boolean millisecond) {
		if (millisecond) {
			sdf.applyPattern("yyyy-MM-dd HH:mm:ss:SSS");
			return sdf.format(date);
		} else {
			return datetime2Str(date);
		}
	}

	/**
	 * 返回中文格式日期时间(yyyy年MM月dd日HH时mm分ss秒)
	 * 
	 * @author SevenWong<br>
	 * @date 2016年8月10日下午3:27:17
	 * @param date
	 * @return
	 */
	public static String datetime2StrZH_CN(Date date) {
		sdf.applyPattern("yyyy年MM月dd日HH时mm分ss秒");
		return sdf.format(date);
	}

	/**
	 * 
	 * @author SevenWong<br>
	 * @date 2016年8月10日下午3:23:23
	 * @param date
	 * @return 返回指定日期的yyyy-MM-dd格式
	 */
	public static String date2Str(Date date) {
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String time2Str(Date date) {
		sdf.applyPattern("HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 
	 * 多个类中有调用此方法，因此未弃用，建议使用date2Str(Date date)
	 * 
	 * @author SevenWong<br>
	 * @date 2016年8月10日下午3:23:23
	 * @param date
	 * @return 返回指定日期的yyyy-MM-dd格式
	 */
	public static String dateToString(Date date) {
		return date2Str(date);
	}

	/**
	 * 
	 * @author SevenWong<br>
	 * @date 2016年8月10日下午3:20:57
	 * @param date
	 * @param separator
	 *            为true时返回yyyy-MM-dd，否则返回yyyyMMdd
	 * @return
	 */
	public static String date2Str(Date date, boolean separator) {
		if (separator) {
			return date2Str(date);
		} else {
			sdf.applyPattern("yyyyMMdd");
			return sdf.format(date);
		}
	}

	public static String dateformat(Date date, String fmt) {
		sdf.applyPattern(fmt);
		return sdf.format(date);
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}
	
	public static String dateAddInt(Date date,Integer i) {
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + i);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}
	
	public static int getInt() {
		String s = String.valueOf(new Date().getTime());
		int i = Integer.parseInt(s.substring(3));
		return i;
	}

	public static void main(String[] args) {
		//System.out.println(getSpecifiedDayAfter(DateUtil.date2Str(new Date())));
		Integer i =31;
		System.out.println(dateAddInt(new Date(), i));
	}
}
