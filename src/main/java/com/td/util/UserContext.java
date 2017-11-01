package com.td.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.td.domain.Master;

public class UserContext {
	public static final String SESSION_USERNAME = "master";

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static void setSessionUser(Master master) {
		getSession().setAttribute(UserContext.SESSION_USERNAME, master);
	}

	public static Master getSessionUser() {
		try {
			Object object = getSession().getAttribute(UserContext.SESSION_USERNAME);
			return (Master) object;
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		return null;
	}

	public static HttpSession getSession() {
		// LogUtil.debug("session:" + getRequest().getSession());
		return getRequest().getSession();
	}

	public static void remove() {
		getSession().removeAttribute(SESSION_USERNAME);
	}

}
