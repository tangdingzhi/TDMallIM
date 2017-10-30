package com.td.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.td.bean.BaseQuery;
import com.td.service.ILogService;
import com.td.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName: ShopController
 * @Description: 商铺控制器
 * @author 米雪铭
 * @date: 2017年10月23日 下午3:07:01
 */
@RequestMapping("/manage/log")
@Controller
public class LogController {
	@Autowired
	ILogService logService;

	/**
	 * @Title: selShop
	 * @Description: 分页查询商铺
	 * @author 米雪铭
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selLog", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject selLog(BaseQuery query) {
		return JsonUtil.reJsonObject(logService.findByQuery(query));
	}

}
