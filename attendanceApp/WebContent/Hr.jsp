<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.*" %>
     <%@ page import="beans.Attendance" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
  	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
   	response.setHeader("Pragma", "no-cache");
   	response.setHeader("Expires","0");
  	if(session.getAttribute("user")==null){
		response.sendRedirect("Login.jsp");
	}
  %>
<title>HR HOME</title>
<link rel="stylesheet" type="text/css" href="css/tableStyle.css" >
<link rel="stylesheet" type="text/css" href="css/HrStyle.css" >
</head>
<body>
<h1 align="center" style="color:white;">Welcome <%=session.getAttribute("firstName") %></h1>

<h3 align="right" ><a href="Logout.jsp" style="color:orange;"><b >Logout</b></a></h3>

<div class="tablebox1">
<h1 align="center" style="color:white;">Employees in Company</h1>
<form action="HrHome" method="POST">
<table border = "1" cellpadding = "6" cellspacing = "2" align="center">
            <tr><th>Select an Employee to view Attendance</th></tr>
            <%! @SuppressWarnings("unchecked") %>
            <%Object object=session.getAttribute("employees");
            	if(object!=null){
            	List<String[]> fullRecord=(List<String[]>)object;
				for(String[] record:fullRecord){
			%>
            <tr><td><input type = "radio" name = "empName" value = <%=record[0]%> onclick="submit();"><%=record[1]%></td></tr>
            <%} }%>
 </table>
 </form>
 </div>
 
 <div class="tablebox2">
 <%Object str=session.getAttribute("personRecord");
 if(str!=null){%>
 <h1 align="center" style="color:white;">Attendance information of employee</h1>
 <table border="1" align="center">
 <tr>
<th>Date(MM/DD/YYYY)</th>
<th>Attendance</th>
</tr>
<%
List<Attendance> attendances=(List<Attendance>)str;
for(Attendance attendanceData:attendances){
%>
<tr>
<td> <%=attendanceData.getAttendanceDate()%></td>
<td><%=attendanceData.getAttendanceStatus()%> </td>
<tr>
<%}}%>
</table>
<% if(str==null){ %>
	<h1 align="center" style="color:cyan">Attendance data has not been entered for this employee yet!</h1>
<%} %>
</div>
 
 
</body>
</html>