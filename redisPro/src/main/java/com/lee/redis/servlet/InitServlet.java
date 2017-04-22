package com.lee.redis.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import com.lee.redis.pojo.Student;
import com.lee.redis.utils.JedisInitUtil;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Jedis jedis;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		jedis = JedisInitUtil.getInitJedis();
		
		//当学生信息条数能被10整除是剩余页设置为0，不能整除设置为1
		int restPage = (0 == jedis.zcard("students") % 10 ? 0 : 1);
		request.setAttribute("page", null == request.getParameter("page") ? 1
				: Integer.valueOf(request.getParameter("page")));
		request.setAttribute("students",
				getStudent((Integer) request.getAttribute("page")));
		request.setAttribute("pageCount", jedis.zcard("students") / 10
				+ restPage);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//获取10条学生信息
	private ArrayList<Student> getStudent(int page) {
		ArrayList<Student> students = new ArrayList<Student>();
		Set<String> zrange = jedis.zrange("students", (page - 1) * 10,
				page * 10 - 1);
		Iterator<String> iterator = zrange.iterator();
		while (iterator.hasNext()) {
			String[] all = iterator.next().split("&");
			Student student = new Student(Integer.valueOf(all[0]),
					all[1], Date.valueOf(all[2]), all[3],
					Integer.valueOf(all[4]));
			students.add(student);
		}
		return students;
	}
}
