package com.td.mapper;

import java.util.List;

import com.td.bean.SocketJson;
import com.td.domain.Log;

/**
 * @ClassName: LogMapper
 * @Description: 日志mapper
 * @author 米雪铭
 * @date: 2017年10月17日 下午4:50:26
 */
public interface LogMapper extends BaseMapper<Log> {

	/**
	 * @Title: clientLog
	 * @Description: 获取客户和客服的聊天记录
	 * @author 米雪铭
	 * @param id1
	 *            分别传入客服和客户的id
	 * @param id2
	 *            不用区分先后顺序
	 * @return
	 */
	public List<SocketJson> clientLog(String id1, String id2);
}
