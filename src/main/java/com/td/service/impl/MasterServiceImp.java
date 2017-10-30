package com.td.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.domain.Master;
import com.td.mapper.BaseMapper;
import com.td.mapper.MasterMapper;
import com.td.service.IMasterService;

@Service
public class MasterServiceImp extends BaseServiceImpl<Master> implements IMasterService {

	@Autowired
	MasterMapper masterMapper;

	@Override
	protected BaseMapper<Master> getMapper() {
		return masterMapper;
	}

	@Override
	public Master login(String acc, String password) {
		return masterMapper.login(acc, password);
	}

}
