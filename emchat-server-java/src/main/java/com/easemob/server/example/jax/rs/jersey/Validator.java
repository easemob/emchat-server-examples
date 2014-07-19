package com.easemob.server.example.jax.rs.jersey;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	/**
	 * Check email
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return match(regex, str);
	}

	/**
	 * Check IP
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIP(String str) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
		return match(regex, str);
	}

	/**
	 * Check URL
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isUrl(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}

	/**
	 * Check Telphone
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTelephone(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		String result;
		String regex = "^[0-9]+?[0-9-()]*[0-9()]+?$|^[0-9]+?$|^$";
		String regex1 = "\\-{2,}";
		String regex2 = "\\({2,}";
		String regex3 = "\\){2,}";
		result = str.replaceAll(regex1, " ");
		result = result.replaceAll(regex2, " ");
		result = result.replaceAll(regex3, " ");
		return match(regex, str) && str.length() == result.length();
	}

	/**
	 * Check IDCard
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIDcard(String str) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return match(regex, str);
	}

	/**
	 * Check Month
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMonth(String str) {
		String regex = "^(0?[[1-9]|1[0-2])$";
		return match(regex, str);
	}

	/**
	 * Check Day
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDay(String str) {
		String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
		return match(regex, str);
	}

	/**
	 * Check Date
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
		return match(regex, str);
	}

	public static boolean isNumber(String str) {
		String regex = "^[0-9]*$";
		return match(regex, str);
	}

	public static boolean isIntNumber(String str) {
		if ("0".equals(str))
			return true;
		String regex = "^[\\-\\+]?[1-9][0-9]*$";
		return match(regex, str);
	}

	public static boolean isUpChar(String str) {
		String regex = "^[A-Z]+$";
		return match(regex, str);
	}

	public static boolean isLowChar(String str) {
		String regex = "^[a-z]+$";
		return match(regex, str);
	}

	public static boolean isChinese(String str) {
		String regex = "^[\u4e00-\u9fa5],{0,}$";
		return match(regex, str);
	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.lookingAt();
	}

	public static boolean isBlank(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] == null || "".equals(strs[i])) {
				return true;
			}
		}
		return false;
	}

}
