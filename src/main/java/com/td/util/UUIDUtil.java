package com.td.util;

import java.util.UUID;

/**
 * @ClassName: UUIDUtil 
 * @Description: UUID工具类
 * @author 米雪铭
 * @date: 2017年10月16日 上午10:53:41
 */
public class UUIDUtil {

	/**
	 * @Title: getUUID
	 * @Description: 获取32位UUID
	 * @author 米雪铭
	 * @return
	 */
	public static String getUUID() {
		return after(UUID.randomUUID().toString());
	}

	/**
	 * @Title: after
	 * @Description: 后处理，去掉-符号
	 * @author 米雪铭
	 * @param str
	 * @return
	 */
	private static String after(String str) {
		return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
	}

}
