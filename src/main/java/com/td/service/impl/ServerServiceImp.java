package com.td.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.bean.SocketJson;
import com.td.domain.Server;
import com.td.mapper.BaseMapper;
import com.td.mapper.ServerMapper;
import com.td.service.IServerService;
import com.td.util.ClientUtil;
import com.td.util.StringUtil;

@Service
public class ServerServiceImp extends BaseServiceImpl<Server> implements IServerService {

	@Autowired
	ServerMapper serverMapper;

	@Override
	protected BaseMapper<Server> getMapper() {
		return serverMapper;
	}

	@Override
	public Server getServer(SocketJson bean) {
		// 获得该商铺所有客服
		List<Server> servers = serverMapper.getServers(bean.getToID());
		List<Server> online = new ArrayList<>();
		// 获得所有在线客服
		for (Server server : servers) {
			if (null != ClientUtil.getClientChannel(server.getId()))
				online.add(server);
		}
		int size = online.size();
		// 没客户在线则返回空，有客服在线就随机返回一个在线客服
		if (size == 0)
			return null;
		return online.get(new Random().nextInt(size));
	}

	@Override
	public Integer fakeDel(String id) {
		if (StringUtil.isNull(id))
			return 0;
		Server server = new Server();
		server.setId(id);
		server.setStatus(0);
		return update(server);
	}

	@Override
	public Server login(String acc, String password) {
		return serverMapper.login(acc, password);
	}

}
