package com.td.bean;

import java.io.Serializable;

import io.netty.channel.Channel;

/**
 * @ClassName: OnlineClient
 * @Description: 在线客户类
 * @author 米雪铭
 * @date: 2017年10月17日 下午3:41:03
 */
public class OnlineClient implements Serializable {
	/**
	 * @Fields serialVersionUID: 序列化
	 */
	private static final long serialVersionUID = -599794207008210717L;
	/**
	 * 客户id
	 */
	private String id;
	/**
	 * 客户名称
	 */
	private String name;
	/**
	 * 连接类
	 */
	private Channel channel;
	/**
	 * 接收方id
	 */
	private String toID;
	/**
	 * 商铺id
	 */
	private String shopID;
	/**
	 * 商铺名称
	 */
	private String shopName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
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

	@Override
	public String toString() {
		return "OnlineClient [id=" + id + ", name=" + name + ", channel=" + channel + ", toID=" + toID + ", shopID="
				+ shopID + ", shopName=" + shopName + "]";
	}

}
