package example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class storedata 
{
	public static void main(String[]args)
	{
		try
		{
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			employ e1 = new employ();
			e1.setId(104);
			e1.setName("yuvaraj");
			e1.setSalary(200000);
			session.persist(e1);
			t.commit();
			session.close();
			System.out.println("successfully saved");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
