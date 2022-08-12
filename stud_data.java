package studentdata;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class stud_data
{
	public static void main(String[]args)
	{
		
		int ch = 0;

		try
		{
			Scanner s = new Scanner(System.in);
			Configuration cfg = new Configuration();
			cfg.configure("student.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			
			int choice=0,Id,CAmark = 0,ESEmark;
			String Name = null,Subject;
		
			do
			{
				System.out.println("1.Insert\n 2.Delete\n 3.update\n 4.display\n 5.from clause\n 6.groupby\n 7.display std whose mark > 50\n 8.display std record whose mark < 10\n 9.display std id & name who has highest marks\n 10.select top 10 students\n 11.Exit");
				
				System.out.println("Enter your choice :");
				ch = s.nextInt();
			
				switch(ch)
				{
				case 1:	
						Session ses = factory.openSession();
						Transaction t = ses.beginTransaction();
						System.out.println("Enter the Id: ");
						Id = s.nextInt();
						System.out.println("Enter the Name: ");
						Name = s.next();
						System.out.println("Enter the subject: ");
						Subject = s.next();
						System.out.println("Enter the camark: ");
						CAmark = s.nextInt();
						System.out.println("Enter the esemark: ");
						ESEmark = s.nextInt();
						int temp = CAmark+ESEmark;
						System.out.println("The total is: "+temp);
						
						student s1 = new student();
						s1.setId(Id);
						s1.setName(Name);
						s1.setSubject(Subject);
						s1.setCAmark(CAmark);
						s1.setESEmark(ESEmark);
						s1.settotal(temp);
						ses.persist(s1);
						t.commit();
						ses.close();
						System.out.println("successfully saved");
						
						break;
						
				case 2:
				
						Session sess = factory.openSession();
						int t1 = 0;
						System.out.println("Enter the id to delete: ");
						Id = s.nextInt();
						if(t1==0)
						{
							System.out.println("Id is not found in the database");
						}
						else
						{
							String hql = "delete from student where Id = "+Id+"";
							Query q = session.createQuery(hql);
							int result = q.executeUpdate();
							if(result==0)
							{
								System.out.println("no records found");
							}
							else
							{
								System.out.println("rows affectd: "+result);
							}
						}
						
						sess.close();
						
						break;
					
				case 3:
					
						Session se = factory.openSession();
						System.out.println("Enter the Id to Update: ");
						Id = s.nextInt();
						System.out.println("Enter the CAmark to update : ");
						CAmark = s.nextInt();
						
						String h = "update student set CAmark ="+CAmark+" where Id = "+Id+"";
						Query query = session.createQuery(h);
						int r = query.executeUpdate();
						if(r==0)
						{
							System.out.println("no records found");
						}
						else
						{
							System.out.println("rows affected: "+r);
						}
						se.close(); 
						
						break;
	
				case 4:
					
						String hq = "from student as s1 ";
						Query qu = session.createQuery(hq);
						List l = qu.list();
						System.out.print("ID\t NAME\t SUBJECT\t CAMARK\t ESEMARK\t TOTAL");
						
						for(Iterator i=l.iterator(); i.hasNext();)
						{	
							student s11 =(student)i.next();
																						
							System.out.print(s11.getId());
							System.out.print("\t");
							System.out.print(s11.getName());
							System.out.print("\t");
							System.out.print(s11.getSubject());
							System.out.print("\t");
							System.out.print(s11.getCAmark());
							System.out.print("\t");
							System.out.print(s11.getESEmark());
							System.out.print("\t");
							System.out.print(s11.gettotal());
							System.out.println("\t");
						}
						
						break;
					
				case 5:
					
						String h1 = "from student s1 where s1.total > 90 order by s1.Name"; 
						Query quer = session.createQuery(h1);
						List result1 = quer.list();
						for(Iterator i=result1.iterator(); i.hasNext();)
						{
							student s11 =(student)i.next();
							System.out.println(s11.getName());
						}
					
						break;
						
				case 6:		
					
						String h11 = "select total,Name from student group by Name";
						Query query1 = session.createQuery(h11);
						List l1 = query1.list();
						for(Iterator i=l1.iterator(); i.hasNext();)
						{
							student s11 = (student)i.next();
							System.out.println(s11.gettotal());
							System.out.println(s11.getName());
						}
						System.out.println("session terminated");
						break;
						
				case 7:
						Query q2 = session.createQuery("from student where total > 50 ");
						List l2 = q2.list();
						System.out.print("ID\t NAME\t TOTAL");
						for(Iterator i=l2.iterator(); i.hasNext();)
						{
							student s2 = (student)i.next();
							System.out.print(s2.getId());
							System.out.print("\t");
							System.out.print(s2.getName());
							System.out.print("\t");
							System.out.print(s2.gettotal());
						}
						break;
						
				case 10:
						System.out.println("TOP 10 STUDENTS");
						Query q3 = session.createQuery("from student where total desc");
						List l3 = q3.list();
						for(Iterator i=l3.iterator(); i.hasNext();)
						{
							student s3 = (student)i.next();
							System.out.print(s3.getId());
							System.out.print("\t");
							System.out.print(s3.getName());
							System.out.print("\t");
							System.out.print(s3.getCAmark());
							System.out.print("\t");
							System.out.print(s3.getESEmark());
							System.out.print("\t");
							System.out.print(s3.gettotal());
							
						}
						break;
						
				case 11:
						
						System.out.println("program is terminated");
						System.exit(0);
						break;
						
				default:
						System.out.println("Enter the correct choice");   
						
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
