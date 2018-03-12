package appServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession=request.getSession();
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String person=request.getParameter("person");
		
		User user=new User();
		user.setUserName(name);
		user.setPassword(password);
		user.setEmpType(person);
		
		LoginService validation=new LoginService();
		User user1=validation.loginValidation(user);
		if(user1!=null){
			httpSession.setAttribute("user", user1.getUserName());
			httpSession.setAttribute("firstName", user1.getFirstName());
			httpSession.setAttribute("person", user1.getEmpType());
			if(person.equals("employee"))
				response.sendRedirect("EmployeeHome.jsp");
			else if(person.equals("hr")){
				List<String[]> employees=validation.getEmployees();
				httpSession.setAttribute("employees", employees);
				response.sendRedirect("Hr.jsp");
			}
		}else{
			httpSession.setAttribute("error", "Invalid Credentials!!");
			response.sendRedirect("Login.jsp");
		}
	}

}
