package com.td.mapper;

import java.util.List;

import com.td.bean.BaseQuery;

/**
 * @ClassName: BaseMapper
 * @Description: 公共mapper
 * @author 米雪铭
 * @date: 2017年10月16日 上午10:14:46
 * @param <T>
 */
public interface BaseMapper<T> {
	public Integer save(T t);

	public Integer update(T t);

	public Integer delete(String id);

	public T get(String id);

	public List<T> getAll();

	public Integer findCountByQuery(BaseQuery baseQuery);// 查总条数的

	public List<T> findLimitByQuery(BaseQuery baseQuery);// 查数据的
}
