<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
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
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  
  </script>
 <title>Employee Home</title>
 <link rel="stylesheet" type="text/css" href="css/EmployeeHomeStyle.css" >
 <link rel="stylesheet" type="text/css" href="css/tableStyle.css" >
</head>
<body>

<h2 align="center" >Welcome <%= session.getAttribute("firstName") %> <%-- <%= session.getAttribute("person") %> --%> 
</h2>
<h3 align="right"><a href="Logout.jsp" ><b>Logout</b></a></h3>

<div class="loginBox1">
<img alt="please insert image" src="pictures/attendence.jpg" class="picture">
<h3>Mark Today's Attendance</h3>
<form action="home" method="POST">
				<div class="box">
				<label >Present</label>
				</div>
				<div class="box">
				<input type = "radio" name = "attendenceCall" value = "Present" > 
          		</div>
          		<div class="box">
          		<label >Absent</label>
          		</div>
          		<div class="box">
         		<input type = "radio" name = "attendenceCall" value = "Absent" >
         		</div>
         		<div class="box">
         		<label >Official out</label>
          		</div>
          		<div class="box">
         		<input type = "radio" name = "attendenceCall" value = "OfficialOut" >
         		</div>
         		
<input type="submit" value="Submit" onclick="submit" />
</form>

 <%if("alreadyGiven".equals(session.getAttribute("rollCall"))){ %>
 <h2 style="color:yellow">You have already marked your attendance for this day!</h2>
 <%}else if(session.getAttribute("rollCall")!=null){ %>
 <h2 style="color:cyan">Your Attendance has been marked as <%=session.getAttribute("rollCall") %> successfully!</h2>
 <%} %>

</div>


 
 <div class="loginBox2">
<img alt="please insert image" src="pictures/attendence.jpg" class="picture">
<h3>See monthly view</h3>
<form action="home" method="POST">
				<div class="box">
				<label >Table view</label>
				</div>
				<div class="box">
				<input type = "radio" name = "view" value = "intable" > 
          		</div>
          		<div class="box">
          		<label >Calendar format</label>
          		</div>
          		<div class="box">
          		<input type = "radio" name = "view" value = "calendar">
          		</div>
          		
<input type="submit" value="Submit" onclick="submit" />
</form>


<%if(session.getAttribute("pass")!=null){%>
<form action="home" method="POST">
Select a month to view in table format <select name = "tableFormat">
            <option value="null" selected>choose</option>
            <option value = "01">January</option>
            <option value = "02">February</option>
            <option value = "03">March</option>
            <option value = "04">April</option>
            <option value = "05">May</option>
            <option value = "06">June</option>
            <option value = "07">July</option>
            <option value = "08">August</option>
            <option value = "09">September</option>
            <option value = "10">October</option>
            <option value = "11">November</option>
            <option value = "12">December</option>
         	</select>
         	<input type="submit" value="Submit" onclick="submit" />
         	</form>
         	<%}if(session.getAttribute("pass1")!=null){%>
         	<form action="home" method="POST">
         	<div class="box">
			<p>Select a day from Calendar--></p>
			
			</div>
			<div class="box">
			<input  type="text" name = "calendarFormat" id="datepicker" title="click me" >
			</div>
			<input type="submit" value="Submit" onclick="submit" />
			</form>
<%} %>

</div>
 
 
 
 
<%
if(session.getAttribute("tableSelected")!=null || session.getAttribute("calendarSelected")!=null){
String x="";
if(session.getAttribute("batchDate")!=null){
	x="batchDate";
	}
else if(session.getAttribute("singleDate")!=null){
		x="singleDate";
		}
Object object=session.getAttribute(x);
if(object==null){%>
<div class="tablebox">
<img alt="please insert image" src="pictures/attendence.jpg" class="picture">
<h2 align="center" style="color:cyan;">Attendance information not entered for this period!</h2>
</div>
<%	
}else{
%>
<div class="tablebox">
<img alt="please insert image" src="pictures/attendence.jpg" class="picture">
<h1 align="center" style="color:white;">Attendance information of employee</h1>
<%
String fullRecord=(String)object;
if(x.equals("batchDate")){
String[] records=fullRecord.split(",");%>


<table border = "1" cellpadding = "6" cellspacing = "1"  align="center">
<tr>
<th>Date(YYYY-MM-DD)</th>
<th>Attendance</th>
</tr>
<%for(int i=0;i<=records.length-1;i++){%>
<tr>
<td> <%=records[i].substring(0,10)%></td>
<td><%=records[i].substring(11)%> </td>
</tr>
<%} %>
</table>
<%}if(x.equals("singleDate")){%>
<table border = "1" cellpadding = "6" cellspacing = "1"  align="center">
<tr>
<th>Date(YYYY-MM-DD)</th>
<th>Attendance</th>
</tr>
<tr>
<td> <%=fullRecord.substring(0,10)%></td>
<td><%=fullRecord.substring(11)%> </td>
</tr> 
</table>
<%}}}%>
</div>



</body>
</html>