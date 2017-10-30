package com.td.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String md5(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// 二进制转字符串
			StringBuffer strHexString = new StringBuffer();
			byte[] byteBuffer = md5.digest(str.getBytes("utf-8"));
			String hex;
			for (int i = 0; i < byteBuffer.length; i++) {
				hex = Integer.toHexString(0xff & byteBuffer[i]);
				if (hex.length() == 1) {
					strHexString.append('0');
				}
				strHexString.append(hex);
			}
			return strHexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LogUtil.error(e.getMessage());
		}
		return str;
	}

}
