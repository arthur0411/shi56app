package com.flf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	public static final String EMPTY = "";

	public static final String NULL = "null";

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param str
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 同 {@link notEmpty} 检测字符串是否不为空(null,"","null")
	 * 
	 * @author SevenWong<br>
	 * @date 2016年9月1日下午2:28:35
	 * @param str
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param str
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || EMPTY.equals(str) || NULL.equals(str);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (isNotEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return EMPTY;
		}
	}

	/**
	 * 将emoji表情处理成"?"保存
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月30日上午11:32:53
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("?");
			}
		}
		return source;
	}
}
