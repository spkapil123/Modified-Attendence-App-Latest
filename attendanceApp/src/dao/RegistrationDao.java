package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import beans.User;

public class RegistrationDao {
	
	@SuppressWarnings("unchecked")
	public boolean checkUsernameAvailability(String userName){
		
		Session session=SessionCreate.createSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("userName", userName));
		List<User> results = cr.list();
		if (results!=null && !results.isEmpty()) {
			return false;
		}else{
			return true;
		}
	}
	
	public boolean enterDetailsToTable(User user){
		boolean valid=false;
		
		Session session=SessionCreate.createSession();
		session.beginTransaction();
		
		User user2=new User();
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setEmpType(user.getEmpType());
		user2.setEmailId(user.getEmailId());
		user2.setUserName(user.getUserName());
		user2.setPassword(user.getPassword());
		
		session.save(user2);
		session.getTransaction().commit();
		
		valid=true;
		return valid;
	}
	
	

}
