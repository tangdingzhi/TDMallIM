package com.td.indexInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.td.domain.Master;
import com.td.util.JsonUtil;
import com.td.util.LogUtil;
import com.td.util.UserContext;

/**
 * @ClassName: AuthInterceptor
 * @Description: 登陆权限拦截器
 * @author 米雪铭
 * @date: 2017年10月27日 下午3:26:34
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {
			Master userWeb = UserContext.getSessionUser();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			if (userWeb == null)
				request.getRequestDispatcher("/index.html").forward(request, response);
			else
				response.getWriter().println(JsonUtil.reJsonObject(""));
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
