package com.lee.redis.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import com.lee.redis.pojo.Student;
import com.lee.redis.utils.JedisInitUtil;
import com.lee.redis.utils.StudentUtil;

public class UpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Jedis jedis = JedisInitUtil.getInitJedis();

		Student student = new Student(Integer.valueOf(request
				.getParameter("id")), request.getParameter("name"),
				Date.valueOf(request.getParameter("birthday")),
				request.getParameter("description"), Integer.valueOf(request
						.getParameter("avgscore")));
		jedis.zrem(
				"students",
				StudentUtil.getStudentExist(
						Integer.valueOf(request.getParameter("id"))).toString());
		jedis.zadd("students", student.getAvgscore(), student.toString());
		request.getRequestDispatcher("/InitServlet").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
