package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import model.User;
import redis.clients.jedis.Jedis;

public class Main {
	private static String constr = "118.89.49.14";
	private final static String USER_AGE_20 = "USER_AGE_20";
	private final static String USER_AGE_21 = "USER_AGE_21";
	private final static String USER_TABLE = "USER_TABLE";

	public static Jedis getRedis() {
		// 单独连接一台redis
		Jedis jedis = new Jedis(constr, 6379);
		return jedis;
	}

	public static void main(String[] args) {
		Jedis j = Main.getRedis();
		// List temp = j.mget("name", "age");
		// Iterator ltem = temp.iterator();
		// while (ltem.hasNext()) {
		// System.out.println(ltem.next());
		// }

		// Map<String, String> lnj = j.hgetAll("lnj");
		// Set<String> keys = lnj.keySet();
		// System.out.println(lnj);
		//
		// Iterator temp = keys.iterator();
		// while (temp.hasNext()) {
		// System.out.println(lnj.get((String) temp.next()));
		// }

		// Map map = new HashMap();
		// map.put("name", "wyq");
		// map.put("age", "21");
		// j.hmset("wyq", map);

		Map<String, String> map = new HashMap<>();

		String uuid = UUID.randomUUID().toString();
		User user = new User(uuid, "lnj", "20");
		map.put(uuid, JSON.toJSONString(user));
		j.sadd(USER_AGE_20, uuid);

		String uuidTwo = UUID.randomUUID().toString();
		User userTwo = new User(uuidTwo, "wyq", "21");
		map.put(uuidTwo, JSON.toJSONString(userTwo));
		j.sadd(USER_AGE_21, uuidTwo);

		String uuidThree = UUID.randomUUID().toString();
		User userThree = new User(uuidThree, "sl", "20");
		map.put(uuidThree, JSON.toJSONString(userThree));
		j.sadd(USER_AGE_20, uuidThree);

		j.hmset(USER_TABLE, map);

		Set set = j.smembers(USER_AGE_20);
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String object = (String) iterator.next();
			String temp = j.hget(USER_TABLE, object);
			System.out.println(temp);

		}
	}
}