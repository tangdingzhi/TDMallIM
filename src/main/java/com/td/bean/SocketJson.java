package com.td.bean;

import java.io.Serializable;
import java.util.Date;

import com.td.domain.Log;

/**
 * @ClassName: WebSocketJson
 * @Description: 用于通讯的json实体类
 * @author 米雪铭
 * @date: 2017年10月17日 上午10:38:45
 */
public class SocketJson extends Log implements Serializable {
	/**
	 * @Fields serialVersionUID: 序列化
	 */
	private static final long serialVersionUID = 7052541727373260801L;
	/**
	 * 消息类型
	 */
	private int type;
	/**
	 * 发送时间
	 */
	private long sendTime;
	/**
	 * 通讯状态
	 */
	private int status;

	public SocketJson() {
	}

	public SocketJson(String fromID, String content, int type) {
		status = 500;
		this.type = type;
		this.setFromID(fromID);
		this.setContent(content);
	}

	public SocketJson(String fromID, String content, int type, int status) {
		this.status = status;
		this.type = type;
		this.setFromID(fromID);
		this.setContent(content);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	@Override
	public Date getTimeStamp() {
		if (super.getTimeStamp() == null)
			return new Date(getSendTime());
		else
			return super.getTimeStamp();
	}

	@Override
	public void setTimeStamp(Date timeStamp) {
		if (timeStamp != null && getSendTime() == 0)
			setSendTime(timeStamp.getTime());
		super.setTimeStamp(timeStamp);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SocketJson [type=" + type + ", sendTime=" + sendTime + ", status=" + status + "]" + super.toString();
	}

}
