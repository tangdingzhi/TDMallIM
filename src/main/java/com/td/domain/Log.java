package com.td.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Log
 * @Description: 日志实体类
 * @author 米雪铭
 * @date: 2017年10月13日 下午2:06:36
 */
public class Log implements Serializable {
	/**
	 * @Fields serialVersionUID: 序列化
	 */
	private static final long serialVersionUID = 4541929348642561940L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 发送id
	 */
	private String fromID;
	/**
	 * 发送名称
	 */
	private String fromName;
	/**
	 * 接收id
	 */
	private String toID;
	/**
	 * 接收名称
	 */
	private String toName;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 内容类型
	 */
	private int contentType;
	/**
	 * 商铺id
	 */
	private String shopID;
	/**
	 * 商铺名称
	 */
	private String shopName;
	/**
	 * 创建时间
	 */
	private Date timeStamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", fromID=" + fromID + ", fromName=" + fromName + ", toID=" + toID + ", toName="
				+ toName + ", content=" + content + ", contentType=" + contentType + ", shopID=" + shopID
				+ ", shopName=" + shopName + ", timeStamp=" + timeStamp + "]";
	}

}
