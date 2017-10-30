package com.td.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * 字符串常用操作公共方法
 * 
 * @author 米雪铭
 *
 */
public class StringUtil {

	public static final String EMPTY_STRING = "";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
			"E", "F" };
	/**
	 * 过滤通过页面表单提交的字符
	 */
	private static final String[][] FILTERCHARS = { { "<", "&lt;" }, { ">", "&gt;" }, { " ", "&nbsp;" },
			{ "\"", "&quot;" }, { "&", "&amp;" }, { "/", "&#47;" }, { "\\", "&#92;" }, { "\n", "<br>" } };
	/**
	 * 过滤通过javascript脚本处理并提交的字符
	 */
	private static final String[][] FILTERSCRIPTCHARS = { { "\n", "\'+\'\\n\'+\'" }, { "\r", " " },
			{ "\\", "\'+\'\\\\\'+\'" }, { "\'", "\'+\'\\\'\'+\'" } };
	/**
	 * 默认分隔符（逗号）
	 */
	public static final String DELIMITER = ",";
	/**
	 * 引号
	 */
	private static final String QUOTE = "'";
	/**
	 * 管理员ID，用于网页后台管理
	 */
	public static final String MQ_NAME = "tdmallim";

	/**
	 * 补充字符的方法
	 * 
	 * @param oldStr
	 *            ：原字符串
	 * @param strLen
	 *            ：返回字符串长度
	 * @param padChar
	 *            ：插入字符串
	 * @param direction
	 *            ：插入方向,取值为r(在原字符串右边补充)，l(在原字符串左边补充)
	 * @return
	 */
	public static String padString(String oldStr, int strLen, String padChar, char direction) {
		StringBuilder newStr = new StringBuilder(oldStr);
		try {
			if (oldStr.length() < strLen) {
				if (direction == 'r') {
					while (newStr.length() < strLen) {
						newStr.append(padChar);
					}
				} else {
					while (newStr.length() < strLen) {
						newStr.insert(0, padChar);
					}
				}
			}
			return newStr.toString();
		} catch (Exception e) {
			return oldStr;
		}
	}

	/**
	 * 提供字符串到ArrayList的转变，需要声明分隔符
	 * 
	 * @param tStr
	 *            要转变的字符串
	 * @param sStr
	 *            分割符
	 * @return
	 */
	public static List<String> Str2List(String tStr, String sStr) {
		List<String> list = new ArrayList<>();
		if (isNotNull(tStr)) {
			StringTokenizer st = new StringTokenizer(tStr, sStr);
			while (st.hasMoreTokens()) {
				list.add(st.nextToken());
			}
		}
		return list;
	}

	/**
	 * 提供字符串到ArrayList的转变，字符串使用逗号作为分隔符
	 * 
	 * @param tStr
	 *            要转变的字符串
	 * @return
	 */
	public static List<String> Str2List(String tStr) {
		return Str2List(tStr, DELIMITER);
	}

	/**
	 * 字符串以指定长度进行切割，结果放入ArrayList对象中
	 * 
	 * @param tStr
	 *            要转变的字符串
	 * @param nleng
	 *            长度
	 * @return
	 */
	public static List<String> Str2List(String tStr, int nleng) {
		int strLength = tStr.length();
		int ndiv = strLength / nleng;
		List<String> list = new ArrayList<>();
		if (isNotNull(tStr)) {
			// 能整除则不考虑上面除以后省略的小数部分
			if (strLength % nleng == 0)
				ndiv--;
			for (int i = 0; i < ndiv; i++) {
				list.add(tStr.substring(i * nleng, (i + 1) * nleng));
			}
			list.add(tStr.substring(ndiv * nleng, strLength));
		}
		return list;
	}

	/**
	 * 提供ArrayList到字符串的转变，转变后的字符串以sStr作为分割符
	 * 
	 * @param tList
	 *            要转变的ArrayList
	 * @param sStr
	 *            分隔符
	 * @return
	 */
	public static String List2Str(List<String> tList, String sStr) {
		StringBuilder reStr = new StringBuilder();
		if (isNotNull(tList)) {
			reStr.append(tList.get(0));
			for (int i = 1; i < tList.size(); i++) {
				reStr.append(sStr);
				reStr.append(tList.get(i));
			}
		}
		return reStr.toString();
	}

	/**
	 * 提供ArrayList到字符串的转变，转变后的字符串使用逗号(,)做分割符
	 * 
	 * @param tList
	 *            要转变的ArrayList
	 * @return
	 */
	public static String List2Str(List<String> tList) {
		return List2Str(tList, DELIMITER);
	}

	/**
	 * 提供字符串到字符串数组的转变,需要声明分隔符
	 * 
	 * @param tStr
	 *            要转变的字符串
	 * @param sStr
	 *            分割符
	 * @return
	 */
	public static String[] Str2Strs(String tStr, String sStr) {
		StringTokenizer st = new StringTokenizer(tStr, sStr);
		String[] reStrs = new String[st.countTokens()];
		int n = 0;
		while (st.hasMoreTokens()) {
			reStrs[n] = st.nextToken();
			n++;
		}
		return reStrs;
	}

	/**
	 * 提供字符串数组到字符串的转变，转变后的字符串以sStr作为分割符
	 * 
	 * @param tStrs
	 *            要转变的字符串数组
	 * @param sStr
	 *            分隔符
	 * @return
	 */
	public static String Strs2Str(String[] tStrs, String sStr) {
		StringBuilder reStr = new StringBuilder();
		if (isNotNull(tStrs)) {
			reStr.append(tStrs[0]);
			for (int i = 1; i < tStrs.length; i++) {
				reStr.append(sStr);
				reStr.append(tStrs[i]);
			}
		}
		return reStr.toString();
	}

	/**
	 * 提供字符串数组到字符串的转变，转变后的字符串以逗号(,)作为分割符
	 * 
	 * @param tStrs
	 * @return
	 */
	public static String Strs2Str(String[] tStrs) {
		return Strs2Str(tStrs, DELIMITER);
	}

	/**
	 * 提供字符串数组到字符串的转变，转变后的字符串以sStr作为分割符,每个元素用单引号('')包含
	 * 
	 * @param tStrs
	 *            要转变的字符串数组
	 * @param sStr
	 *            分隔符
	 * @return
	 */
	public static String Strs2StrSQL(String[] tStrs, String sStr) {
		StringBuilder reStr = new StringBuilder();
		if (isNotNull(tStrs)) {
			reStr.append(QUOTE);
			reStr.append(tStrs[0]);
			reStr.append(QUOTE);
			for (int i = 1; i < tStrs.length; i++) {
				reStr.append(sStr);
				reStr.append(QUOTE);
				reStr.append(tStrs[i]);
				reStr.append(QUOTE);
			}
		}
		return reStr.toString();
	}

	/**
	 * 对Str进行替换操作，将str1替换为str2
	 * 
	 * @param str
	 *            要进行替换的字符串
	 * @param str1
	 *            被替换的字符串
	 * @param str2
	 *            替换字符串
	 * @return
	 */
	public static String replace(String str, String str1, String str2) {
		int n = -1;
		String subStr = "";
		String re = "";
		if ((n = str.indexOf(str1)) > -1) {
			subStr = str.substring(n + str1.length(), str.length());
			re = str.substring(0, n) + str2 + replace(subStr, str1, str2);
		} else {
			re = str;
		}
		return re;
	}

	/**
	 * 
	 * @Title: toCodeString
	 * @Description: 改变字符串的编码格式
	 * @author 米雪铭
	 * @param string
	 * @param code
	 * @return
	 */
	public static String toCodeString(String string, String code) {
		StringBuilder sb = new StringBuilder();
		if (isNotNull(string) && isNotNull(code)) {
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;
					try {
						b = Character.toString(c).getBytes(code);
					} catch (Exception ex) {
						LogUtil.warn(ex.getMessage());
						b = new byte[0];
					}
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串转换成Utf-8编码格式
	 * 
	 * @param s
	 * @return
	 */
	public static String toUtf8String(String s) {
		return toCodeString(s, "utf-8");
	}

	/**
	 * 将字符串转换成GBK编码格式
	 * 
	 * @param s
	 * @return
	 */
	public static String toGbkString(String s) {
		return toCodeString(s, "GBK");
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return null == obj || "".equals(obj.toString());
	}

	/**
	 * 判断对象是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param l
	 * @return
	 */
	public static boolean isNull(List<?> l) {
		return null == l || l.isEmpty();
	}

	/**
	 * 判断集合是否不为空
	 * 
	 * @param l
	 * @return
	 */
	public static boolean isNotNull(List<?> l) {
		return !isNull(l);
	}

	/**
	 * 判断是字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return null == str || "".equals(str.trim());
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 判断字符串数组是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String[] strs) {
		return null == strs || strs.length == 0;
	}

	/**
	 * 判断字符串数组是否不为空
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isNotNull(String[] strs) {
		return !isNull(strs);
	}

	/**
	 * 简单的数字转中文，为空返回"0"
	 * 
	 * @param a
	 * @return
	 */
	public static String translateToChinese(String str) {
		if (isNotNull(str) && IsNumber(str)) {
			return translateToChinese(Integer.parseInt(str));
		} else {
			return "0";
		}
	}

	/**
	 * 简单的数字转中文
	 * 
	 * @param a
	 * @return
	 */
	public static String translateToChinese(int number) {
		int a = number;
		String[] units = { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String[] nums = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };

		StringBuilder result = new StringBuilder();
		if (a < 0) {
			result.append("负");
			a = Math.abs(a);
		}
		String t = String.valueOf(a);
		for (int i = t.length() - 1; i >= 0; i--) {
			int r = (int) (a / Math.pow(10, i));
			if (r % 10 != 0) {
				String s = String.valueOf(r);
				String l = s.substring(s.length() - 1, s.length());
				result.append(nums[Integer.parseInt(l) - 1]);
				result.append(units[i]);
			} else {
				if (result.lastIndexOf("零") == result.length() - 1)
					result.append("零");
			}
		}
		String num = Integer.toString(a);
		/*
		 * 因为方法对10-20之间的数字支持不好，比如11返回一十一，不能满足需求 所以这里单独判断
		 */
		if (a == 10) {
			return "十";
		} else if (a > 10 && a < 20) {
			return result.substring(1);
		} else if (num.endsWith("0")) {
			result = new StringBuilder(result.substring(0, result.length() - 1));
		}
		return result.toString();
	}

	/**
	 * 判断两个对象是否相同，都为空的时候是相同的
	 * 
	 * @param o1
	 *            可以是基本类型，包装类型，String，List等
	 * @param o2
	 *            自己写的类需要重写equals方法
	 * @return
	 */
	public static boolean eq(Object o1, Object o2) {
		if (o1 == null) {
			if (o2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return o1.equals(o2) || o1 == o2;
		}
	}

	/**
	 * 从头开始截取字符串
	 * 
	 * @param src
	 *            待截取字符串
	 * @param num
	 *            截取长度
	 * @return
	 */
	public static String subString(String src, int num) {
		return subString(src, 0, num);
	}

	/**
	 * 
	 * 截取字符串
	 * 
	 * @param src
	 *            待截取字符串
	 * @param str
	 *            开始位置
	 * @param num
	 *            截取长度
	 * @return
	 */
	public static String subString(String src, int str, int num) {
		if (isNull(src) || str < 0 || num < 0)
			return src;
		int len = src.length();
		int end = str + num;
		return src.substring(len > str ? str : len, len > end ? end : len);
	}

	/**
	 * 判断字符串是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean IsNumber(String str) {
		if (isNull(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断字符串是否为小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean IsFloat(String str) {
		if (isNull(str))
			return false;
		Pattern pattern = Pattern.compile("\\d+[.]?\\d*");
		return pattern.matcher(str).matches();
	}

	/**
	 * XML字符串中不能用这些字符 < > ' " &
	 * 
	 * @param str
	 * @return
	 */
	public static String replace4XML(String str) {
		if (str == null)
			return "";
		return str.replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;").replace("\"", "&quot;").replace("&",
				"&amp;");
	}

	/**
	 * 转换html符号为字符，使用前需要修改js代码
	 * 
	 * @param temp
	 * @return
	 */
	public static String HtmlTag2String(String temp) {
		temp = temp == null ? "" : temp;
		for (int i = 0; i < FILTERCHARS.length; i++) {
			temp = temp.replace(FILTERCHARS[i][1], FILTERCHARS[i][0]);
		}
		return temp;
	}

	/**
	 * 转换“<”、“>”等字符为html符号，使用前需要修改js代码
	 * 
	 * @param temp
	 * @return
	 */
	public static String String2HtmlTag(String temp) {
		temp = temp == null ? "" : temp;
		for (int i = 0; i < FILTERCHARS.length; i++) {
			temp = temp.replace(FILTERCHARS[i][0], FILTERCHARS[i][1]);
		}
		return temp;
	}

	/**
	 * 转换经过js处理的文本，使用前需要修改js代码
	 * 
	 * @param temp
	 * @return
	 */
	public static String HtmlStr2String(String temp) {
		temp = temp == null ? "" : temp;
		for (int i = 0; i < FILTERSCRIPTCHARS.length; i++) {
			temp = temp.replace(FILTERSCRIPTCHARS[i][1], FILTERSCRIPTCHARS[i][0]);
		}
		return temp;
	}

	/**
	 * 转换文本以提供给js处理，使用前需要修改js代码
	 * 
	 * @param temp
	 * @return
	 */
	public static String String2HtmlStr(String temp) {
		temp = temp == null ? "" : temp;
		for (int i = 0; i < FILTERSCRIPTCHARS.length; i++) {
			temp = temp.replace(FILTERSCRIPTCHARS[i][0], FILTERSCRIPTCHARS[i][1]);
		}
		return temp;
	}

	/**
	 * 给以.开头的小数前面增加0
	 * 
	 * @param str
	 * @return
	 */
	public static String zero(String str) {
		if (str.startsWith(".")) {
			str = "0".concat(str);
		}
		return str;
	}

	/**
	 * 保留X位小数
	 * 
	 * @param dou
	 * @param x
	 *            保留的位数，必须大于0
	 * @return
	 */
	public static double getDoubleXDigit(String dou, int x) {
		double tmpD = 0;
		if (IsFloat(dou) && x > 0) {
			tmpD = Double.parseDouble(dou);
		}
		return getDoubleXDigit(tmpD, x);
	}

	/**
	 * 保留X位小数
	 * 
	 * @param dou
	 * @param x
	 *            保留的位数，必须大于0
	 * @return
	 */
	public static double getDoubleXDigit(Double dou, int x) {
		double tmpD = 0;
		if (isNotNull(dou) && x > 0) {
			tmpD = dou;
		}
		double y = 10;
		y = Math.pow(y, x);
		tmpD = tmpD * y;
		tmpD = Math.round(tmpD);
		tmpD = tmpD / y;
		return tmpD;
	}

	/**
	 * 在模糊查询中过滤容易引发SQL语句执行异常的符号
	 * 
	 * @param strQuery
	 * @return
	 */
	public static String ReplaceSqlLike(String strQuery) {
		String strRet = strQuery;
		strRet = strRet.replace("/", "//");
		strRet = strRet.replace("'", "''");
		strRet = strRet.replace("%", "/%");
		strRet = strRet.replace("[", "/[");
		strRet = "'%" + strRet + "%' escape '/'";
		return strRet;
	}

	/**
	 * @Title: isNotNull
	 * @Description: 判断传入的参数是否都不为空
	 * @author 米雪铭
	 * @param objs
	 * @return
	 */
	public static boolean isNotNull(Object... objs) {
		for (Object object : objs) {
			if (isNull(object))
				return false;
		}
		return true;
	}

	/**
	 * @Title: isNull
	 * @Description: 传入的参数有一个为空则为true
	 * @author 米雪铭
	 * @param objs
	 * @return
	 */
	public static boolean isNull(Object... objs) {
		return !isNotNull(objs);
	}

	/**
	 * @Title: byteArrayToHexString
	 * @Description: 转换字节数组为16进制字串
	 * @author 米雪铭
	 * @param b
	 * @return
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * @Title: MD5Encode
	 * @Description: 转换字符串为MD5
	 * @author 米雪铭
	 * @param origin
	 * @return
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
