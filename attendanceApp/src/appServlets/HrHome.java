package appServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Attendance;
import service.LoginService;

/**
 * Servlet implementation class HrHome
 */
@WebServlet("/HrHome")
public class HrHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession=request.getSession();
		String empName=request.getParameter("empName");
		httpSession.setAttribute("employeeName", empName);
		LoginService provider=new LoginService();
		System.out.println("Heyy got the employee :"+empName);
		List<Attendance> attendanceRecord=provider.getEmployeeAttandance(empName);
		httpSession.setAttribute("personRecord", attendanceRecord);
		response.sendRedirect("Hr.jsp");
	}

}
