package test;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import beans.Attendance;
import beans.User;

public class CreateTables {
	
	public static void main(String[] args){
		AnnotationConfiguration config= new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Attendance.class);
		config.configure("hibernate.cfg.xml");
		
		new SchemaExport(config).create(true, true);
		
		
		
	}

}
