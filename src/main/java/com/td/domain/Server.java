package com.td.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Servcer
 * @Description: 客服实体类
 * @author 米雪铭
 * @date: 2017年10月13日 下午2:03:44
 */
public class Server implements Serializable {
	/**
	 * @Fields serialVersionUID: 序列化
	 */
	private static final long serialVersionUID = -1944664792214563851L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 账号
	 */
	private String acc;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 所属商铺id
	 */
	private String shopID;
	/**
	 * 所属商铺名称
	 */
	private String shopName;
	/**
	 * 创建时间
	 */
	private Date timeStamp;
	/**
	 * 状态
	 */
	private Integer status;

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", acc=" + acc + ", password=" + password + ", name=" + name + ", note=" + note
				+ ", shopID=" + shopID + ", shopName=" + shopName + ", timeStamp=" + timeStamp + ", status=" + status
				+ "]";
	}

}
