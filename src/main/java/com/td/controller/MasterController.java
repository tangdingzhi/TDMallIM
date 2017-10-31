package com.td.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.td.service.IMasterService;

/**
 * @ClassName: MasterController
 * @Description: 管理员控制器
 * @author 米雪铭
 * @date: 2017年10月23日 下午3:07:12
 */
@RequestMapping("/manage/master")
@Controller
public class MasterController {
	@Autowired
	IMasterService masterService;

	@RequestMapping(value = "/web", method = RequestMethod.GET)
	public String web() {
		return "system";
	}
}
