package dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import beans.Attendance;
import beans.User;

public class LoginDao {
	
	@SuppressWarnings("unchecked")
	public User userValidator(User user){
		Session session=SessionCreate.createSession();
		try {
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("userName", user.getUserName()));
			List<User> results = cr.list();
			
			if (!results.isEmpty() && results!=null) {
				if(user.getPassword().equals(results.get(0).getPassword()) &&
						user.getEmpType().equals(results.get(0).getEmpType())){
					User user1=new User();
					user1.setFirstName(results.get(0).getFirstName());
					user1.setLastName(results.get(0).getLastName());
					user1.setEmpType(results.get(0).getEmpType());
					user1.setUserName(results.get(0).getUserName());
					user1.setEmailId(results.get(0).getEmailId());
					user1.setPassword(results.get(0).getPassword());
					return user1;
				}else{
					return null;
				}
				
			}else{
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String[]> extractEmployees(){
		Session session=SessionCreate.createSession();
		List<String[]> firstNameList=new ArrayList<String[]>();
		try {
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("empType", "employee"));
			ProjectionList p1=Projections.projectionList();
	        p1.add(Projections.distinct(Projections.property("userName")));
	        p1.add(Projections.property("firstName")); 
	        cr.setProjection(p1);
			List<Object[]> results = cr.list();
			if(results!=null && !results.isEmpty()){
				for (Object[] field : results) {
					String[] records={field[0].toString(),field[1].toString()};
					firstNameList.add(records);
				}
				return firstNameList;
			}else{
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String insertAttendance(String call,String userName){
		Session session=SessionCreate.createSession();
		try {
			User user=new User();
			user.setUserName(userName);
			Criteria cr = session.createCriteria(Attendance.class);
			cr.add(Restrictions.eq("attendanceDate", Calendar.getInstance().getTime()))
			.add(Restrictions.eq("user",user));
			
			List<Attendance> attendanceList=cr.list();
			if(attendanceList!=null && !attendanceList.isEmpty()){
				return "alreadyGiven";
			}else{
				session.beginTransaction();
				Attendance attendance=new Attendance();
				attendance.setAttendanceStatus(call);
				attendance.setAttendanceDate(Calendar.getInstance().getTime());
				attendance.setUser(user);
				session.save(attendance);
				session.getTransaction().commit();
				return attendance.getAttendanceStatus();
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Attendance> getMonthlyAttendance(String userName,String value){
		Session session=SessionCreate.createSession();
		try {
			User user=new User();
			user.setUserName(userName);
			String hql ="from Attendance where attendanceDate like '____-"+value+"-__' and user = :userName";
			Query query = session.createQuery(hql);
			query.setParameter("userName", user);
			List<Attendance> attendanceList=query.list();
			if(attendanceList!=null && !attendanceList.isEmpty()){
				return attendanceList;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Attendance getCalendarAttendance(String userName,String value){
		Session session=SessionCreate.createSession();
		try {
			Calendar calendar=Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(value.substring(6)));
			calendar.set(Calendar.MONTH, Integer.parseInt(value.substring(0, 2))-1);
			calendar.set(Calendar.DATE, Integer.parseInt(value.substring(3,5)));
			User user=new User();
			user.setUserName(userName);
			Criteria cr = session.createCriteria(Attendance.class);
			cr.add(Restrictions.eq("attendanceDate", calendar.getTime()))
			.add(Restrictions.eq("user",user));
			List<Attendance> attendanceList=cr.list();
			if(attendanceList!=null && !attendanceList.isEmpty()){
				Attendance attendance=attendanceList.get(0);
				return attendance;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Attendance> getEmployeeAttendance(String empName){
		Session session=SessionCreate.createSession();
		try {
			User user=new User();
			user.setUserName(empName);
			Criteria cr = session.createCriteria(Attendance.class);
			cr.add(Restrictions.eq("user",user));
			List<Attendance> attendanceList=cr.list();
			if(attendanceList!=null && !attendanceList.isEmpty()){
				return attendanceList;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	
	}

}
