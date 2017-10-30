package com.td.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.td.util.FileUtil;
import com.td.util.JsonUtil;
import com.td.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName: FileController
 * @Description: 文件上传控制器
 * @author 米雪铭
 * @date: 2017年10月20日 下午1:49:44
 */
@RequestMapping("/manage")
@Controller
public class FileController {

	@Autowired
	private FileUtil fileUtil;

	/**
	 * @Title: upImg
	 * @Description: 上传聊天图片
	 * @author 米雪铭
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upimg", method = RequestMethod.POST)
	public JSONObject upImg(HttpServletRequest request) {
		List<String> upFile = fileUtil.upFile(request, "");
		return StringUtil.isNull(upFile) ? JsonUtil.reJsonMsg("上传图片失败")
				: JsonUtil.reJsonObject(upFile.get(upFile.size() - 1));
	}

}
