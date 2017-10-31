package com.td.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.td.domain.Master;
import com.td.domain.Server;
import com.td.service.IMasterService;
import com.td.service.IServerService;
import com.td.util.JsonUtil;
import com.td.util.SHAUtil;
import com.td.util.UserContext;

import net.sf.json.JSONObject;

/**
 * @ClassName: LoginController
 * @Description: 登陆控制器
 * @author 米雪铭
 * @date: 2017年10月30日 上午10:47:56
 */
@Controller
public class LoginController {
	@Autowired
	IMasterService masterService;
	@Autowired
	IServerService serverService;

	@RequestMapping(value = "/loginWeb", method = RequestMethod.GET)
	public String loginWeb() {
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject login(String acc, String password, String type, HttpServletRequest request) {
		String shaPass = SHAUtil.SHA512(password);
		Object re = null;
		switch (type) {
		// 1管理员
		case "1":
			re = masterService.login(acc, shaPass);
			UserContext.setSessionUser((Master) re);
			break;
		// 2客服
		case "2":
			re = serverService.login(acc, shaPass);
			UserContext.getSession().setAttribute("server", (Server) re);
			break;
		default:
			break;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("data", re);
		map.put("type", type);
		return null == re ? JsonUtil.reJsonMsg("账号或密码错误！") : JsonUtil.reJsonMap(map);
	}
}
