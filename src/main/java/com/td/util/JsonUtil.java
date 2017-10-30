package com.td.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.td.bean.SocketJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: JsonUtil
 * @Description: json的处理类
 * @author 米雪铭
 * @date: 2017年10月16日 上午10:04:04
 */
public class JsonUtil {

	/**
	 * @Title: toBean
	 * @Description: 将json字符串转换为指定的bean
	 * @author 米雪铭
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static Object toBean(String str, Class<?> clazz) {
		return JSONObject.toBean(JSONObject.fromObject(str), clazz);
	}

	/**
	 * @Title: toJsonStr
	 * @Description: 将bean转换为json字符串
	 * @author 米雪铭
	 * @param object
	 * @return
	 */
	public static String toJsonStr(Object object) {
		if (object instanceof List<?>) {
			JSONArray jsonArray = JSONArray.fromObject(object);
			JSONArray reArray = new JSONArray();
			// long time = System.nanoTime();
			for (Object jsonObject : jsonArray) {
				JSONObject reObject = discard((JSONObject) jsonObject);
				reArray.add(reObject);
			}
			// LogUtil.debug("处理耗时：" + (System.nanoTime() - time) / 1000000);
			return reArray.toString();
		}
		JSONObject jsonObject = JSONObject.fromObject(object);
		if (object instanceof SocketJson) {
			jsonObject = discard(jsonObject);
		}
		return jsonObject.toString();
	}

	private static JSONObject discard(JSONObject jsonObject) {
		return jsonObject.discard("timeStamp").discard("shopName").discard("shopID").discard("id");
	}

	/**
	 * 返回JSON数据（成功）
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject reJsonObject(Object object) {
		return reJsonObject(object, "100", null);
	}

	/**
	 * 返回JSON数据（失败）
	 * 
	 * @return
	 */
	public static JSONObject reJsonFailure() {
		return reJsonObject(null, "500", null);
	}

	/**
	 * 返回JSON数据（自定义状态）
	 * 
	 * @param object
	 * @param status
	 * @param msg
	 * @return
	 */
	public static JSONObject reJsonObject(Object object, String status, String msg) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", status);
		resultMap.put("message", msg);
		if (object != null) {
			resultMap.put("data", object);
		}
		return reJsonMap(resultMap);
	}

	/**
	 * 返回错误信息
	 * 
	 * @param msg
	 * @return
	 */
	public static JSONObject reJsonMsg(String msg) {
		return reJsonObject(null, "500", msg);
	}

	/**
	 * 返回指定状态和信息
	 * 
	 * @param status
	 * @param msg
	 * @return
	 */
	public static JSONObject reJsonMsg(String status, String msg) {
		return reJsonObject(null, status, msg);
	}

	/**
	 * 返回JSON数据（分页）
	 * 
	 * @param object
	 * @param page
	 * @return
	 */
	public static JSONObject reJsonObject(Object object, Integer page) {
		Map<String, Object> resultMap = new HashMap<>();
		if (object != null) {
			resultMap.put("data", object);
		}
		if (page != null && page >= 0) {
			resultMap.put("page", page);
		}
		return reJsonMap(resultMap);
	}

	/**
	 * 返回JSON数据
	 * 
	 * @param resultMap
	 * @return
	 */
	public static JSONObject reJsonMap(Map<String, Object> resultMap) {
		if (resultMap == null) {
			resultMap = new HashMap<>();
		}
		if (resultMap.get("status") == null) {
			resultMap.put("status", "100");
		}
		if (resultMap.get("message") == null) {
			if ("100".equals(resultMap.get("status"))) {
				resultMap.put("message", "success");
			} else {
				resultMap.put("message", "failure");
			}
		}
		JSONObject reObject = JSONObject.fromObject(resultMap);
		return reObject;
	}
}
