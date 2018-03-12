<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogOut Page</title>
<link rel="stylesheet" type="text/css" href="css/Logout.css" >
</head>
<body>
<h1 align="center" style="color:white;font-size:xx-large;">Logging out..........</h1>
 
<%
	request.getSession().invalidate();
  	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
   	response.setHeader("Pragma", "no-cache");
   	response.setHeader("Expires","0");
  %>
  <script type="text/javascript">
  window.setTimeout(function(){window.location.href = "Login.jsp";}, 500);
  </script>
</body>
</html>