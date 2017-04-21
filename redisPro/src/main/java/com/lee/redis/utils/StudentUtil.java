package com.lee.redis.utils;

import java.sql.Date;
import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.lee.redis.pojo.Student;

public class StudentUtil {
	//通过id查询redis是否存在该学生信息
	public static Student getStudentExist(int id) {
		Jedis jedis= JedisInitUtil.getInitJedis();
		Student student = null;
		Set<String> zrange = jedis.zrange("students", 0, -1);
		Iterator<String> iterator = zrange.iterator();
		while (iterator.hasNext()) {
			String value = iterator.next();
			if (value.startsWith(id + "&")) {
				String[] all = value.split("&");
				student = new Student(Integer.valueOf(all[0]), all[1],
						Date.valueOf(all[2]), all[3],
						Integer.valueOf(all[4]));
				break;
			}
		}
		return student;
	}
}
