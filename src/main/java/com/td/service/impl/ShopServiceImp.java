package com.td.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.domain.Shop;
import com.td.mapper.BaseMapper;
import com.td.mapper.ShopMapper;
import com.td.service.IShopService;

@Service
public class ShopServiceImp extends BaseServiceImpl<Shop> implements IShopService {

	@Autowired
	ShopMapper shopMapper;

	@Override
	protected BaseMapper<Shop> getMapper() {
		return shopMapper;
	}

}
