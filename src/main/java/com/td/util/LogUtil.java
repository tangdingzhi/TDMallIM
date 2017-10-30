package com.td.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;

/**
 * @ClassName: LogUtil
 * @Description: 日志工具类
 * @author 米雪铭
 * @date: 2017年6月30日 下午3:01:32
 */
public class LogUtil {

	@SuppressWarnings("deprecation")
	public static void debug(String str) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger.getLogger(stack[1].getClassName()).log(LogUtil.class.getName(), Priority.DEBUG, str, null);
	}

	@SuppressWarnings("deprecation")
	public static void info(String str) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger.getLogger(stack[1].getClassName()).log(LogUtil.class.getName(), Priority.INFO, str, null);
	}

	@SuppressWarnings("deprecation")
	public static void warn(String str) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger.getLogger(stack[1].getClassName()).log(LogUtil.class.getName(), Priority.WARN, str, null);
	}

	@SuppressWarnings("deprecation")
	public static void error(String str) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger.getLogger(stack[1].getClassName()).log(LogUtil.class.getName(), Priority.ERROR, str, null);
	}

	@SuppressWarnings("deprecation")
	public static void fatal(String str) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger.getLogger(stack[1].getClassName()).log(LogUtil.class.getName(), Priority.FATAL, str, null);
	}

	public static void main(String[] args) {
		// 测试字符串
		testString();

		// 测试数组
		// Object[] array = new Object[] { "测试array", "测试array1", "测试array2" };
		// logArray(array);

		// 测试List集合
		// //List放的是字符串
		// List<String> list = new ArrayList<String>();
		// list.add("测试list1");
		// list.add("测试list2");
		// list.add("测试list3");
		// //List放的是对象。
		// List<Student> list = new ArrayList<Student>();
		// for (int i = 0; i < 3; i++) {
		// Student s = new Student();
		// s.setId(i);
		// s.setName("name"+i);
		// list.add(s);
		// }
		// logList(list);

		// // 测试Map集合
		// Map<String, String> map = new HashMap<>();
		// map.put("1", "ss");
		// map.put("2", "@$#@%");
		// map.put("3", "中文");
		// map.put("4", "2342");
		// logMap(map);
	}

	private static void testString() {
		String debug = "debug信息";
		String warn = "warn信息";
		String info = "info信息";
		String error = "error信息";
		String fatal = "fatal信息";
		LogUtil.debug(debug);
		LogUtil.warn(warn);
		LogUtil.info(info);
		LogUtil.error(error);
		LogUtil.fatal(fatal);
	}

	/**
	 * @Title: logArray
	 * @Description: 将数组中的元素用log日志输出
	 * @author 米雪铭
	 * @param array
	 */
	public static void logArray(Object[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			LogUtil.debug("第" + i + "号元素的值：" + Array.get(array, i));
		}
	}

	/**
	 * @Title: logList
	 * @Description: 将List中的元素用log日志输出
	 * @author 米雪铭
	 * @param list
	 */
	public static <T> void logList(List<T> list) {
		if (list == null) {
			LogUtil.error("list=null");
		} else if (list.size() == 0) {
			LogUtil.info("list.size()为0");
		} else {
			StringBuffer sb = new StringBuffer();
			String s = new String("list中数据个数：" + list.size());
			sb.append("\r\n" + s + "\r\nstart:----------------------\r\n");
			for (T t : list) {
				sb.append(t.toString() + "\r\n");// 这里List包含的类要有自定义的toString方法
			}
			sb.append("end----------------------\r\n");
			LogUtil.debug(sb.toString());
		}
	}

	/**
	 * @Title: logMap
	 * @Description: 将Map中的元素用log日志输出
	 * @author 米雪铭
	 * @param map
	 */
	public static <V, K> void logMap(Map<K, V> map) {
		// Map<Student, Student> mapTemp = new HashMap<Student, Student>();
		if (map == null) {
			LogUtil.error("map=null");
		} else if (map.size() == 0) {
			LogUtil.info("map.size()为0");
		} else {
			StringBuffer sb = new StringBuffer();
			String s = new String("map中数据个数：" + map.size());
			sb.append("\r\n" + s + "\r\nstart:----------------------\r\n");
			for (K k : map.keySet()) {
				sb.append("key[" + k.toString() + "]所对应的value:[" + map.get(k).toString() + "]\r\n");// 这里List包含的类要有自定义的toString方法
			}
			sb.append("end----------------------\r\n");
			LogUtil.debug(sb.toString());
		}
	}

	public static void test2() {
		PropertyConfigurator.configure("config/log4j.properties");
		// Logger logger = Logger.getLogger(Log4jTest1.class);
		Logger logger = Logger.getRootLogger();
		// for (int i = 0; i < 1000; i++) {
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
		// }
	}
}
