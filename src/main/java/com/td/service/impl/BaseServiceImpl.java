package com.td.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.td.bean.BaseQuery;
import com.td.bean.PageList;
import com.td.mapper.BaseMapper;
import com.td.service.IBaseService;

@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	protected abstract BaseMapper<T> getMapper();

	@Override
	public Integer save(T t) {
		return getMapper().save(t);
	}

	@Override
	public Integer update(T t) {
		return getMapper().update(t);
	}

	@Override
	public Integer delete(String id) {
		return getMapper().delete(id);
	}

	@Override
	public T get(String id) {
		return getMapper().get(id);
	}

	@Override
	public List<T> getAll() {
		return getMapper().getAll();
	}

	@Override
	public PageList<T> findByQuery(BaseQuery baseQuery) {

		PageList<T> pageList = new PageList<>();

		Integer total = getMapper().findCountByQuery(baseQuery);
		if (total == null || total == 0) {
			return pageList;
		}

		List<T> rows = getMapper().findLimitByQuery(baseQuery);
		pageList.setTotal(total);
		pageList.setRows(rows);
		return pageList;
	}

	@Override
	public Integer fakeDel(String id) {
		return 0;
	}

}