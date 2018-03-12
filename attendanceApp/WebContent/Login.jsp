<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendance App Login</title>
<link rel="stylesheet" type="text/css" href="css/loginStyle.css" >
</head>
<body>
<h1 align="center">Attendance App</h1>
<div class="loginBox">
<img alt="please insert image" src="pictures/person.png" class="picture">
<h3>Login</h3>
<form action="login" method="POST">
<p>username</p>
<input type="text" name='name'>
<p>password</p>
<input type="password" name='password'>
<p>Select an Employee Type below</p>
<div class="box">
	<label >Employee</label>
</div>
<div class="box">
	<input class="radio" type = "radio" name = "person" value = "employee" required> 
</div>
<div class="box">
        <label >HR</label>
</div>
<div class="box">
     	<input type = "radio" name = "person" value = "hr" required>
</div>
<input type="submit" value="Submit" />
</form>
<%Object msg= session.getAttribute("error");
			if(msg!=null){%>
<p style="color:red">
<%=msg.toString() %>
</p>
<%} %>
<h3>Not Registered? <a href="Registration.html"><u>Register</u></a></h3>
</div>

</body>
</html>