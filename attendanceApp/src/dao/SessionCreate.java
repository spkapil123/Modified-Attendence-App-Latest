package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import beans.Attendance;
import beans.User;

public class SessionCreate {
	
	public static Session createSession(){
		AnnotationConfiguration config= new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Attendance.class);
		config.configure("hibernate.cfg.xml");
		
		
		SessionFactory factory=config.buildSessionFactory();
		Session session=factory.openSession();
		
		return session;
		
	}
	

}
