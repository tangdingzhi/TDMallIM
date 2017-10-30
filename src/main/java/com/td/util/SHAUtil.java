package com.td.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: SHAUtil
 * @Description: SHA加密工具类
 * @author 米雪铭
 * @date: 2017年9月15日 上午11:54:49
 */
public class SHAUtil {

	/**
	 * @Title: SHA256
	 * @Description: 传入文本内容，返回 SHA-256 串
	 * @author 米雪铭
	 * @param strText
	 * @return
	 */
	public static String SHA256(final String strText) {
		return SHA(strText, "SHA-256");
	}

	/**
	 * @Title: SHA512
	 * @Description: 传入文本内容，返回 SHA-512 串
	 * @author 米雪铭
	 * @param strText
	 * @return
	 */
	public static String SHA512(final String strText) {
		return SHA(strText, "SHA-512");
	}

	/**
	 * @Title: SHA
	 * @Description: 加密具体方法
	 * @author 米雪铭
	 * @param strText
	 * @param strType
	 * @return
	 */
	private static String SHA(final String strText, final String strType) {
		// 返回值
		String strResult = null;
		// 是否是有效字符串
		if (strText != null && strText.length() > 0) {
			try {
				// SHA 加密开始
				MessageDigest messageDigest = MessageDigest.getInstance(strType);
				messageDigest.update(strText.getBytes());
				byte byteBuffer[] = messageDigest.digest();
				// 二进制转字符串
				StringBuffer strHexString = new StringBuffer();
				String hex;
				for (int i = 0; i < byteBuffer.length; i++) {
					hex = Integer.toHexString(0xff & byteBuffer[i]);
					if (hex.length() == 1) {
						strHexString.append('0');
					}
					strHexString.append(hex);
				}
				strResult = strHexString.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return strResult;
	}
}
