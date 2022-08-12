package hcql;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Emp_store 
{
	public static void main(String args[])
	{
		int ch=0;
		
		try
		{
			Scanner s = new Scanner(System.in);
			Configuration cfg = new Configuration();
			cfg.configure("Employee.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			
			int choice = 0,emp_id,yr_experience,salary;
			String emp_name;
			
			do
			{
				System.out.println("1.INSERT\t 2.salary from desc\t 3.salary>2000\t 4.delete emp who has exp<1year");
				System.out.println("Enter the choice : ");
				ch = s.nextInt();
				
				switch(ch)
				{
				case 1:		Session ses = factory.openSession();
							Transaction t = ses.beginTransaction();
							System.out.println("Enter the emp_id : ");
							emp_id = s.nextInt();
							System.out.println("Enter the emp_name : ");
							emp_name = s.next();
							System.out.println("Enter the year of experience : ");
							yr_experience = s.nextInt();
							System.out.println("Enter the salary of an Employee : ");
							salary = s.nextInt();
							
							Employee e = new Employee();
							e.setemp_id(emp_id);
							e.setemp_name(emp_name);
							e.setyr_experience(yr_experience);
							e.setsalary(salary);
							ses.persist(e);
							t.commit();
							ses.close();
							System.out.println("successfully saved");
							
							break;
							
				case 2:		System.out.println("name of employee salary from highest");
							Criteria cr = session.createCriteria(Employee.class);
							cr.addOrder(Order.desc("salary"));
							List l2 = cr.list();
							for(Iterator i=l2.iterator(); i.hasNext();)
							{
								Employee e3 = (Employee)i.next();
								System.out.print(e3.getemp_name()+"\t");
								System.out.print(e3.getsalary());
							}
							break;
				
				case 3:		Session sess = factory.openSession();
							System.out.println("display details of employee whose salary above 20000");
							Criteria cr2 = sess.createCriteria(Employee.class);
							Integer inte = new Integer(20000);
							cr2.add(Restrictions.gt("salary", inte));
							List l4 = cr2.list();
							System.out.println("ID\t NAME\t EXPR\t SALARY");
							for(Iterator i=l4.iterator(); i.hasNext();)
							{
								Employee e4 = (Employee)i.next();
								System.out.print(e4.getemp_id()+"\t");
								System.out.print(e4.getemp_name()+"\t");
								System.out.print(e4.getyr_experience()+"\t");
								System.out.println(e4.getsalary());
							}
							
							break;
							
				case 4: 	Session s2 = factory.openSession();
							System.out.println("Display id and name of the employee who has the highest salary");
							Criteria cr3 = s2.createCriteria(Employee.class);
							cr3.setProjection(Projections.max("salary"));
							List l5 = cr3.list();
							for(Iterator i=l5.iterator(); i.hasNext();)
							{
								Employee e6 = (Employee)i.next();
								System.out.print(e6.getemp_id()+"\t");
								System.out.print(e6.getemp_name()+"\t");
								System.out.print(e6.getyr_experience()+"\t");
								System.out.println(l5.get(0));
							}
							break;
							
				case 5:		Session ss = factory.openSession();
							Criteria cr5 = ss.createCriteria(Employee.class);
							cr5.setProjection(Projections.rowCount());
							List rowcount = cr5.list();
							System.out.println("Total count of employees : "+rowcount.get(0));
							break;
							
				case 6:		Session ses5 = factory.openSession();
							Criteria cr7 = ses5.createCriteria(Employee.class);
							cr7.setProjection(Projections.avg("salary"));
							List avg  = cr7.list();
							System.out.println("The average salary of the company is : "+avg.get(0));
							break;
							
				case 7:		Session ses1 = factory.openSession();
							String hq = "delete from Employee where yr_experience < 1";
							Query que = ses1.createQuery(hq);
							List li = que.list();
							int res = que.executeUpdate();
							if(res==0)
							{
								System.out.println("no records");
							}
							else
							{
								System.out.println("deleted : "+res);
							}
							break;
							
				case 8:		System.out.println("program is terminated");
							System.exit(0);
							break;
							
				default: 	System.out.println("Enter the correct choice :");
				}
				
			}
			while(choice!=11);
			s.close();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
				
		}
	}

