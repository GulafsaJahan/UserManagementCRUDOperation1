<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management System</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
</head>
<body>
<header>
	<nav class="navbar navbar-expand-md navbar-dark" style="background-color:green">
		<div>
			<a href="#" class="navbar-brand">User Management Application</a>
		</div>
		
		<ul class="navbar-nav">
			<li><a href="<%= request.getContextPath() %>/user-list" class="nav-link" style="color:white">Users</a> </li>
		</ul>
	</nav>
</header>

<br>
<div class="container col-md-5">
	<div class="card">
		<div class="card-body">
			<c:if test="${user != null }">
			<form action="update" method="post">
			
			</c:if>
			<c:if test="${user == null }">
			<form action="insert" method="post">
			</c:if>
			
			
			<c:if test="${user != null }">
				<input type="hidden" name="id" value="<c:out value='${user.id }' />" />	
				
			</c:if>
			
			<fieldset class="form-group">
				<label> Name : </label>
				<input type="text" value="<c:out value='${user.name }'/>" class="form-control" name="name" required="required" />
			</fieldset>
			<fieldset class="form-group">
				<label> Email : </label>
				<input type="text" value="<c:out value='${user.email }'/>" class="form-control" name="email" />
			</fieldset>
			<fieldset class="form-group">
				<label> Country : </label>
				<input type="text" value="<c:out value='${user.country }'/>" class="form-control" name="country"/>
			</fieldset>
			<fieldset class="form-group">
				<label> Password : </label>
				<input type="password" value="<c:out value='${user.password }'/>" class="form-control" name="password"  />
			</fieldset>
			
			<button type="submit" class="btn btn-success">Save</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>