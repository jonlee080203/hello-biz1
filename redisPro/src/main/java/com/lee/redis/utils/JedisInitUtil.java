package com.lee.redis.utils;

import redis.clients.jedis.Jedis;

public class JedisInitUtil {
	public static Jedis getInitJedis(){
		return new Jedis("localhost");
	} 
}
