<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  	<form action="./AddServlet">
  		<table>
  			<tr>
  				<td>id</td>
  				<td><input type="text" name="id"/></td>
  			</tr>
  			<tr>
  				<td>姓名</td>
  				<td><input type="text" name="name"/></td>
  			</tr>
  			<tr>
  				<td>生日</td>
  				<td><input type="text" name="birthday"/></td>
  			</tr>
  			<tr>
  				<td>备注</td>
  				<td><input type="text" name="description"/></td>
  			</tr>
  			<tr>
  				<td>平均分</td>
  				<td><input type="text" name="avgscore"/></td>
  			</tr>
  		</table>
  		
  		<input type="submit" value="提交" />
  	</form>
  </body>
</html>
