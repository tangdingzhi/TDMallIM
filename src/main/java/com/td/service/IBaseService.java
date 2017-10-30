package com.td.service;

import java.util.List;

import com.td.bean.BaseQuery;
import com.td.bean.PageList;

/**
 * @ClassName: IBaseService
 * @Description: 公共service类
 * @author 米雪铭
 * @date: 2017年10月17日 下午3:58:35
 * @param <T>
 */
public interface IBaseService<T> {

	public Integer save(T t);

	public Integer update(T t);

	public Integer delete(String id);

	public T get(String id);

	public List<T> getAll();

	public PageList<T> findByQuery(BaseQuery baseQuery);

	/**
	 * @Title: fakeDel
	 * @Description: 假删除
	 * @author 米雪铭
	 * @param id
	 * @return
	 */
	public Integer fakeDel(String id);

}