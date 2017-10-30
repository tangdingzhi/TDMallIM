package com.td.mapper;

import java.util.List;

import com.td.domain.Server;

/**
 * @ClassName: ServerMapper
 * @Description: 客服mapper
 * @author 米雪铭
 * @date: 2017年10月17日 下午4:50:11
 */
public interface ServerMapper extends BaseMapper<Server> {
	/**
	 * @Title: getServer
	 * @Description: 根据商铺id获得所有客服
	 * @author 米雪铭
	 * @param bean
	 * @return
	 */
	public List<Server> getServers(String shopID);

	/**
	 * @Title: login
	 * @Description: 客服登陆
	 * @author 米雪铭
	 * @param acc
	 * @param password
	 * @return
	 */
	public Server login(String acc, String password);
}
