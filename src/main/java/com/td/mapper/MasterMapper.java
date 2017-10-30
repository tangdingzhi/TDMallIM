package com.td.mapper;

import com.td.domain.Master;

/**
 * @ClassName: MasterMapper
 * @Description: 管理员mapper
 * @author 米雪铭
 * @date: 2017年10月17日 下午4:50:46
 */
public interface MasterMapper extends BaseMapper<Master> {
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
