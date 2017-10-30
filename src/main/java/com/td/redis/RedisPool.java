package com.td.redis;

import java.util.ResourceBundle;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisPool
 * @Description: redis连接池
 * @author 米雪铭
 * @date: 2017年10月27日 上午10:49:48
 */

public class RedisPool {
	
	private static JedisPool pool;

	private RedisPool() {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		// bundle类似一个map
		if (bundle == null) {
			throw new IllegalArgumentException("[redis.properties] is not find ");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));

		// 创建连接池
		String string = bundle.getString("redis.ip");
		String string2 = bundle.getString("redis.port");
		pool = new JedisPool(config, string, Integer.valueOf(string2));
	}

	public synchronized static JedisPool getPool() {
		if (pool == null) {
			new RedisPool();
		}
		return pool;
	}
}