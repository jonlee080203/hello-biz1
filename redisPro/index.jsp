<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学生数据管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--<link rel="stylesheet" type="text/css" href="styles.css">-->
<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>
</head>

<body>
	<a href="add.jsp">新增</a>
	<table class="hovertable" border="1">
		<tr>
			<th>ID</th>
			<th>姓名</th>
			<th>生日</th>
			<th>备注</th>
			<th>平均分</th>
		</tr>
		<c:forEach items="${requestScope.students}" var="student">
			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td>${student.id}</td>
				<td>${student.name }</td>
				<td>${student.birthday }</td>
				<td>${student.description }</td>
				<td>${student.avgscore }</td>
				<td><a href="./DeleteServlet?id=${student.id }">删除</a>
				</td>
				<td><a href="./UpdateServlet?id=${student.id }">修改</a>
				</td>
			</tr>
			<p>
		</c:forEach>

	</table>
	<c:forEach var="i" begin="1" end="${requestScope.pageCount}">
		<a href="TestServlet?page=${i}">${i}</a>
	</c:forEach>
</body>
</html>
