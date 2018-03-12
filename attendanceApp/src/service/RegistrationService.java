package service;

import beans.User;
import dao.RegistrationDao;

public class RegistrationService {
	
	public boolean usernameAvailabilityCheck(String userName){
		RegistrationDao dao=new RegistrationDao();
		boolean validity=dao.checkUsernameAvailability(userName);
		return validity;
	}
	
	public boolean registerDetails(User user){
		RegistrationDao dao=new RegistrationDao();
		boolean success=dao.enterDetailsToTable(user);
		return success;
	}

}
