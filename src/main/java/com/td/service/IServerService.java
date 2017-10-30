package com.td.service;

import com.td.bean.SocketJson;
import com.td.domain.Server;

/**
 * @ClassName: IServerServer
 * @Description: 客服业务处理类
 * @author 米雪铭
 * @date: 2017年10月17日 下午4:51:13
 */
public interface IServerService extends IBaseService<Server> {
	/**
	 * @Title: getServer
	 * @Description: 给客户分配一个客服
	 * @author 米雪铭
	 * @param bean
	 * @return
	 */
	public Server getServer(SocketJson bean);

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
