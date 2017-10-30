package com.td.domain;

import java.io.Serializable;
import java.util.Date;

import com.td.util.UUIDUtil;

/**
 * @ClassName: Shop
 * @Description: 商铺实体类
 * @author 米雪铭
 * @date: 2017年10月13日 下午2:04:57
 */
public class Shop implements Serializable {
	/**
	 * @Fields serialVersionUID: 序列化
	 */
	private static final long serialVersionUID = 8268386518249117722L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 商铺名称
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private Date timeStamp;

	public Shop() {
	}

	public Shop(String name) {
		this.name = name;
		id = UUIDUtil.getUUID();
	}

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

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", timeStamp=" + timeStamp + "]";
	}

}