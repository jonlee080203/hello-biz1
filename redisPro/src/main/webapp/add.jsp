<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
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

<title>增加一个学生</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<!-- 根据判断学生是否存在跳转到不同的servlet -->
	<c:if test="${null==requestScope.student|| empty requestScope.student}">
		<c:set value="./AddServlet" scope="session" var="servlet"></c:set>
	</c:if>
	<c:if test="${not empty requestScope.student}">
		<c:set value="./UpdateServlet" scope="session" var="servlet"></c:set>
	</c:if>
	<c:out value="${requestScope.errorInfo}"></c:out>
	<form action="<c:out value="${servlet }"></c:out>" method="post">
		<table>
			<tr>
				<td>id</td>
				<td><input type="text" name="id"
					value="${requestScope.student.id}" /></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="name"
					value="${requestScope.student.name}" /></td>
			</tr>
			<tr>
				<td>生日</td>
				<td><input type="text" name="birthday"
					value="${requestScope.student.birthday}" /></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="description"
					value="${requestScope.student.description}" /></td>
			</tr>
			<tr>
				<td>平均分</td>
				<td><input type="text" name="avgscore"
					value="${requestScope.student.avgscore}" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交" /></td>
				<td><input type="reset" value="重置" /></td>
			</tr>
		</table>


	</form>
</body>
</html>
