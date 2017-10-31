package com.td.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.td.bean.SocketJson;
import com.td.util.JsonUtil;
import com.td.util.MD5Util;
import com.td.util.SHAUtil;
import com.td.util.UUIDUtil;

public class PTest {
	public static void main(String[] args) {
		System.out.println(UUIDUtil.getUUID());
	}

	public static String password(String pass) {
		return SHAUtil.SHA512(MD5Util.md5(pass));
	}

	public static String getRealIp() {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	public static void doJS() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			String jsFileName = "expression.js"; // 读取js文件
			FileReader reader = new FileReader(jsFileName); // 执行指定脚本
			engine.eval(reader);
			engine.put("a", 4);
			engine.put("b", 3);
			Double result = (Double) engine.eval("a+b");
			System.out.println("result = " + result);
			engine.eval("c=a+b");
			Double c = (Double) engine.get("c");
			System.out.println("c = " + c);
		} catch (ScriptException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void jsonStr(String count, String fromID, String fromName, String toID) {
		SocketJson socketJson = new SocketJson();
		socketJson.setContent(count);
		socketJson.setFromID(fromID);
		socketJson.setFromName(fromName);
		socketJson.setToID(toID);
		socketJson.setSendTime(new Date().getTime());
		System.out.println(JsonUtil.toJsonStr(socketJson));
	}

	public static void jsonStr() {
		SocketJson socketJson = new SocketJson();
		socketJson.setContent("123132");
		socketJson.setFromID("sfs");
		socketJson.setFromName("233");
		socketJson.setShopID("sss");
		socketJson.setShopName("ddd");
		socketJson.setToID("fsf");
		socketJson.setToName("666");
		socketJson.setType(0);
		socketJson.setSendTime(new Date().getTime());
		System.out.println(JsonUtil.toJsonStr(socketJson));
	}
}
