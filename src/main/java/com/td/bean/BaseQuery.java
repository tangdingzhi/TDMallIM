package com.td.bean;

/**
 * @ClassName: BaseQuery
 * @Description: 公共查询条件
 * @author 米雪铭
 * @date: 2017年10月16日 上午10:14:37
 */
public class BaseQuery {

	private String name;
	private Integer page = 1; // 当前页
	private Integer rows = 10;// 当前页的数据
	private String order = "asc";// 是'asc'或'desc'
	private String sort = "ID";// 那个字段进行排序

	public Integer getBegin() {

		return (this.page - 1) * this.rows;

	}

	public Integer getEnd() {
		return this.rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BaseQuery [name=" + name + ", page=" + page + ", rows=" + rows + ", order=" + order + ", sort=" + sort
				+ "]";
	}

}
