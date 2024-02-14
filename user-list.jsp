<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> User List</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
</head>
<body>

<header>
	<nav class="navbar navbar-expand-md navbar-dark" style="background-color:green">
	<div>
		<a href="#" class="navbar-brand"> User Management System</a>
	</div>
		<ul class="navbar-nav">
			<li><a href="<%= request.getContextPath() %>/user-list" class="nav-link" style="color:white">Users List</a></li>
		</ul>
	</nav>
</header>
<br>
<div class="row">
	<div class="container">
		<h3 class="text-center">List of Users</h3>
		<hr>
		<div  class="container text-left">
			<a href="<%= request.getContextPath() %>/new" class="btn btn-success"> Add New User</a>
		</div>
		<br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Country</th>
					<th>Password</th>
					<th>Actions</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="user" items="${listUser}">
					<tr>
						<td><c:out value="${user.id }"></c:out></td>
						<td><c:out value="${user.name }"></c:out></td>
						<td><c:out value="${user.email }"></c:out></td>
						<td><c:out value="${user.country }"></c:out></td>
						<td><c:out value="${user.password }"></c:out></td>
						<td> <a href="edit?id=<c:out value='${user.id }'/>" >Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="delete?id=<c:out value='${user.id }' />"> Delete</a>
						 </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>