package service;

import java.util.List;

import beans.Attendance;
import beans.User;
import dao.LoginDao;


public class LoginService {
	
	public User loginValidation(User user){
		if(user.getUserName()!=null && !user.getUserName().matches("")
				&& user.getPassword()!=null && !user.getPassword().matches("")
				&& user.getEmpType()!=null){
			LoginDao dao=new  LoginDao();
			User user1=dao.userValidator(user);
			return user1;
		}else return null;
		
	}
	
	public List<String[]> getEmployees(){
		LoginDao dao=new LoginDao();
		List<String[]> employeesList=dao.extractEmployees();
		
		return employeesList;
	}
	
	public String markTodaysAttendance(String call,String userName){
		LoginDao dao=new LoginDao();
		String attendanceCall=dao.insertAttendance(call, userName);
		return attendanceCall;
	}
	
	public List<Attendance> getEmployeeAttandance(String userName){
		LoginDao dao=new LoginDao();
		List<Attendance> attendances= dao.getEmployeeAttendance(userName);
		return attendances;
	}	
	
	public String setMonthlytableView(String userName,String value){
		String record=null;
		LoginDao dao=new LoginDao();
		List<Attendance> attendanceList=dao.getMonthlyAttendance(userName, value);
		if(attendanceList!=null){
			record="";
			for (Attendance attendance : attendanceList) {
				record=record.concat(attendance.getAttendanceDate()+"-"+attendance.getAttendanceStatus()+",");
			}
			record=record.substring(0,record.length()-1);
		}
		return record;
	}
	public String setCalendarView(String userName,String value){
		String record=null;
		LoginDao dao=new LoginDao();
		Attendance attendance=dao.getCalendarAttendance(userName, value);
		if(attendance!=null){
			record=attendance.getAttendanceDate()+"-"+attendance.getAttendanceStatus();
		}
		return record;
	}
	
}
