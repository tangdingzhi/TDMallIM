package com.td.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.td.service.IMasterService;
import com.td.service.IServerService;
import com.td.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName: LoginController
 * @Description: 登陆控制器
 * @author 米雪铭
 * @date: 2017年10月30日 上午10:47:56
 */
@RequestMapping("/manage")
@Controller
public class LoginController {
	@Autowired
	IMasterService masterService;
	@Autowired
	IServerService serverService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject login(String acc, String password, String type) {
		Object re = null;
		// 1管理员
		switch (type) {
		case "1":
			re = masterService.login(acc, password);
			break;
		case "2":
			re = serverService.login(acc, password);
			break;
		default:
			break;
		}
		return null == re ? JsonUtil.reJsonMsg("账号或密码错误！") : JsonUtil.reJsonObject(re);
	}
}
