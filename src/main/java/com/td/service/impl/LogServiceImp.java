package com.td.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.bean.SocketJson;
import com.td.domain.Log;
import com.td.mapper.BaseMapper;
import com.td.mapper.LogMapper;
import com.td.service.ILogService;

@Service
public class LogServiceImp extends BaseServiceImpl<Log> implements ILogService {

	@Autowired
	LogMapper logMapper;

	@Override
	protected BaseMapper<Log> getMapper() {
		return logMapper;
	}

	@Override
	public List<SocketJson> clientLog(String id1, String id2) {
		return logMapper.clientLog(id1, id2);
	}

}
