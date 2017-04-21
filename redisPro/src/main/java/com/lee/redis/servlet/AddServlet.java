package com.lee.redis.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee.redis.pojo.Student;
import com.lee.redis.utils.JedisInitUtil;
import com.lee.redis.utils.StudentUtil;

import redis.clients.jedis.Jedis;

public class AddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 初始化jedis
		Jedis jedis = JedisInitUtil.getInitJedis();

		// 获取添加的参数
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		Date birthday = Date.valueOf(request.getParameter("birthday"));
		String description = request.getParameter("description");
		int avgscore = Integer.valueOf(request.getParameter("avgscore"));

		// 将数据封装到Student实体类中
		Student student = new Student(id, name, birthday, description, avgscore);

		// 通过id判断该学生是否存在，不存在就添加学生信息，存在就直接跳转到TestServlet
		if (null == StudentUtil.getStudentExist(student.getId())) {
			jedis.zadd("students", student.getAvgscore(), student.toString());
		}
		request.getRequestDispatcher("/InitServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
