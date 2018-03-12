package appServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession=request.getSession();
		httpSession.setAttribute("pass", null);
		httpSession.setAttribute("pass1", null);
		httpSession.setAttribute("singleDate", null);
		httpSession.setAttribute("batchDate", null);
		
		String attendenceCall=request.getParameter("attendenceCall");
		String monthlyView=request.getParameter("view");
		String tableFormat=request.getParameter("tableFormat");
		String calendarFormat=request.getParameter("calendarFormat");
		httpSession.setAttribute("tableSelected", tableFormat);
		httpSession.setAttribute("calendarSelected", calendarFormat);
		String userName=(String) httpSession.getAttribute("user");
		if(attendenceCall!=null){
				LoginService loginService=new LoginService();
				String call=loginService.markTodaysAttendance(attendenceCall, userName);
				httpSession.setAttribute("rollCall", call);
		}
		if(monthlyView!=null){
			if(monthlyView.equals("intable")){
			
			httpSession.setAttribute("pass", "ok");
			}else{
			httpSession.setAttribute("pass1", "ok");
			}
		}
		LoginService serviceProvider=new LoginService();
		if(tableFormat!=null){
			String fullRecord=serviceProvider.setMonthlytableView(userName,tableFormat);
			httpSession.setAttribute("batchDate", fullRecord);
		}
		if(calendarFormat!=null){
			String fullRecord=serviceProvider.setCalendarView(userName,calendarFormat);
			httpSession.setAttribute("singleDate", fullRecord);
		}
		
		response.sendRedirect("EmployeeHome.jsp");
	}

}
