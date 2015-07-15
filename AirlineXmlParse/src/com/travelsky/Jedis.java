package com.travelsky;

import java.util.List;


public class Jedis {
	public static void main(String[] args) {
		redis.clients.jedis.Jedis jedis = new redis.clients.jedis.Jedis("172.27.18.82", 6379);
//		jedis.set("send", "woni");
//		String value = jedis.get("send");
//		System.out.println(value);
//		jedis.del("send");
//		System.out.println(jedis.get("send"));
		List<String> rsmap=jedis.hmget("GM", "CN_NAME","AIRLINE_TYPE","EN_NAME");
		System.out.println(rsmap);
	}
}
