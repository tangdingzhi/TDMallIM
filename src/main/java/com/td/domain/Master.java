package com.td.domain;

import java.io.Serializable;

/**
 * @ClassName: Master
 * @Description: 管理员实体类
 * @author 米雪铭
 * @date: 2017年10月13日 上午11:26:36
 */
public class Master implements Serializable {
	/**
	 * @Fields serialVersionUID: 序列化
	 */
	private static final long serialVersionUID = -2092955129232466382L;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Master [id=" + id + ", acc=" + acc + ", password=" + password + "]";
	}

}