<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>User Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/user.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>User Management</h1>
				<form action="User_Managemenet.jsp" id="formUser" name="formUser" method="post">
					firstName:
					<input id="firstName" name="firstName" type="text" class="form-control form-control-sm">
					<br>
					lastName:
					<input id="lastName" name="lastName" type="text" class="form-control form-control-sm">
					<br>
					email:
					<input id="email" name="email" type="email" class="form-control form-control-sm">
					<br>
					role:
					<input id="role" name="role" type="text" class="form-control form-control-sm">
					<br>
					password:
					<input id="password" name="password" type="password" class="form-control form-control-sm">
					<br>
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					 <input type="hidden" id="hididSave" name="hididSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divUserGrid">
					 <%
					 User userMgmt = new User();
					 out.print(userMgmt.readUser());
					 %>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>