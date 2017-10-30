package com.td.service;

import com.td.domain.Master;

/**
 * @ClassName: IMasterService
 * @Description: 管理员业务处理类
 * @author 米雪铭
 * @date: 2017年10月17日 下午3:58:58
 */
public interface IMasterService extends IBaseService<Master> {
	/**
	 * @Title: login
	 * @Description: 管理员登陆
	 * @author 米雪铭
	 * @param acc
	 * @param password
	 * @return
	 */
	public Master login(String acc, String password);
}
