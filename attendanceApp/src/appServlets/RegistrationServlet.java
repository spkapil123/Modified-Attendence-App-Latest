package appServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.RegistrationService;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession=request.getSession();
		
		String userName=request.getParameter("userName");
		RegistrationService registrationService=new RegistrationService();
		boolean valid=registrationService.usernameAvailabilityCheck(userName);
		System.out.println(valid);
		if(valid){
            response.getWriter().write("True");
		}else{
            response.getWriter().write("False");
		}
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String empType=request.getParameter("person");
		
		User user=new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmpType(empType);
		user.setUserName(userName);
		user.setEmailId(email);
		user.setPassword(password);
		
		if(email!=null){
			boolean detailsEntered=registrationService.registerDetails(user);
			if(detailsEntered){
				httpSession.setAttribute("message", "Registration Successful!!");
			}
			response.sendRedirect("Login.jsp");
		}
		
		
	}

}
