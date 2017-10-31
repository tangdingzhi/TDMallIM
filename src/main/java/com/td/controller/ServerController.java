package com.td.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.td.bean.ServerQuery;
import com.td.domain.Server;
import com.td.service.IServerService;
import com.td.util.JsonUtil;
import com.td.util.MD5Util;
import com.td.util.SHAUtil;
import com.td.util.StringUtil;
import com.td.util.UUIDUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName: ServerController
 * @Description: 客服控制器
 * @author 米雪铭
 * @date: 2017年10月23日 下午3:06:19
 */
@RequestMapping("/manage/server")
@Controller
public class ServerController {
	@Autowired
	IServerService serverService;

	@RequestMapping(value = "/web", method = RequestMethod.GET)
	public String web() {
		return "chat";
	}

	/**
	 * @Title: save
	 * @Description: 新增/编辑客服
	 * @author 米雪铭
	 * @param server
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject save(Server server) {
		if (StringUtil.isNotNull(server.getId()))
			return serverService.update(server) > 0 ? JsonUtil.reJsonObject(server.getId())
					: JsonUtil.reJsonMsg("修改客服信息失败！");
		if (StringUtil.isNull(server.getName()))
			return JsonUtil.reJsonMsg("客服名称不能为空 ！");
		if (StringUtil.isNull(server.getAcc()))
			return JsonUtil.reJsonMsg("客服账号不能为空 ！");
		if (StringUtil.isNull(server.getShopID()))
			return JsonUtil.reJsonMsg("商铺id不能为空 ！");
		// 设置初始密码
		if (StringUtil.isNull(server.getPassword()))
			server.setPassword(MD5Util.md5("123456"));
		// 设置主键
		server.setId(UUIDUtil.getUUID());
		// 密码加密
		server.setPassword(SHAUtil.SHA256(server.getPassword()));
		return serverService.save(server) > 0 ? JsonUtil.reJsonObject(server.getId())
				: JsonUtil.reJsonMsg("新增客服失败，请检查客服账号是否重复！");
	}

	/**
	 * @Title: selServer
	 * @Description: 分页查询客服数据
	 * @author 米雪铭
	 * @param baseQuery
	 * @return
	 */
	@RequestMapping(value = "/selServer", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject selServer(ServerQuery query) {
		return JsonUtil.reJsonObject(serverService.findByQuery(query));
	}

	/**
	 * @Title: delServer
	 * @Description: 删除客服（假删除）
	 * @author 米雪铭
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delServer", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delServer(String id) {
		if (StringUtil.isNull(id))
			return JsonUtil.reJsonMsg("id不能为空！");
		return serverService.fakeDel(id) > 0 ? JsonUtil.reJsonObject(null) : JsonUtil.reJsonMsg("删除客服失败，请重试！");
	}

}
