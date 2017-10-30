package com.td.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.td.bean.BaseQuery;
import com.td.domain.Shop;
import com.td.service.IShopService;
import com.td.util.JsonUtil;
import com.td.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName: ShopController
 * @Description: 商铺控制器
 * @author 米雪铭
 * @date: 2017年10月23日 下午3:07:01
 */
@RequestMapping("/manage/shop")
@Controller
public class ShopController {
	@Autowired
	IShopService shopService;

	/**
	 * @Title: register
	 * @Description: 新增商铺
	 * @author 米雪铭
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject register(String name) {
		if (StringUtil.isNull(name))
			return JsonUtil.reJsonMsg("商铺名称不能为空 ！");
		Shop shop = new Shop(name);
		return shopService.save(shop) > 0 ? JsonUtil.reJsonObject(shop.getId())
				: JsonUtil.reJsonMsg("新增商铺失败，请检查商铺名称是否重复！");
	}

	/**
	 * @Title: selShop 
	 * @Description: 分页查询商铺
	 * @author 米雪铭
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/selShop", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject selShop(BaseQuery query) {
		return JsonUtil.reJsonObject(shopService.findByQuery(query));
	}

}
