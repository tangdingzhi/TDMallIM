package com.td.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PageList 
 * @Description: 公共分页
 * @author 米雪铭
 * @date: 2017年10月17日 上午10:38:32  
 * @param <T>
 */
public class PageList<T> {
	// 记录总数
	private Integer total;
	// 本页的数据
	private List<T> rows = new ArrayList<T>();

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageList [total=" + total + ", rows=" + rows + "]";
	}

}
