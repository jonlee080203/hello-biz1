package com.lee.redis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import com.lee.redis.utils.JedisInitUtil;
import com.lee.redis.utils.StudentUtil;

public class DeleteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 初始化jedis
		Jedis jedis = JedisInitUtil.getInitJedis();

		// 获取需要删除的学生id
		int id = Integer.valueOf(request.getParameter("id"));

		// 通过id删除学生信息
		jedis.zrem("students", StudentUtil.getStudentExist(id).toString());

		request.getRequestDispatcher("/InitServlet").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
