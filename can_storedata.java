package candidatedata;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class can_storedata 
{
	public static void main(String args[])
	{
		
		int ch = 0;

		try
		{
			Scanner out = new Scanner(System.in);
			Configuration cfg = new Configuration();
			cfg.configure("candidate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession(); 
			
			int choice=0,cId,EntranceMark;
			String Firstname,Lastname,emailId;
			long Phoneno;
			
			do
			{
				System.out.println("1.INSERT\n 2.DELETE\n 3.UPDATE\n 4.DISPLAY NAME,PHONENO,EMAIL\n 5.cutoff-70\n 6.NAME OF STD WHO HAS HIGHEST SCORE\n 7.COUNT NO.OF.STD WHO HAS SCORE>90\n 8.SUM OF SCORE OF ENTIRE BATCH\n 9.EXIT");
				
				System.out.print("Enter your choice : ");
				ch = out.nextInt();
				switch(ch)
				{
					case 1:		Session ses = factory.openSession();
								Transaction t = ses.beginTransaction();
								System.out.print("Enter the Id: ");
								cId = out.nextInt();
								System.out.print("Enter the Firstname: ");
								Firstname = out.next();
								System.out.print("Enter the Lastname : ");
								Lastname = out.next();
								System.out.print("Enter the Phoneno: ");
								Phoneno = out.nextLong();
								if(Phoneno == 10)
								{
									System.out.println("succcess");
								}
								else
								{
									System.out.println("Please enter only 10 digits!!");
									System.out.print("Enter the Phoneno : ");
									Phoneno = out.nextLong();
								}
								System.out.print("Enter the emailId: ");
								emailId = out.next();
								System.out.print("Enter the EntranceMark: ");
								EntranceMark = out.nextInt();
								candidate c1 = new candidate();
								c1.setcId(cId);
								c1.setFirstname(Firstname);
								c1.setLastname(Lastname);
								c1.setPhoneno(Phoneno);
								c1.setemailId(emailId);
								c1.setEntranceMark(EntranceMark);
								ses.persist(c1);
								t.commit();
								ses.close();
								System.out.println("successfully saved");
								
								break;
						
				case 2: 		Session session1 = factory.openSession();
								System.out.print("Enter the id to delete : ");
								cId = out.nextInt();
								String hql = "delete from candidate where cId = "+cId+"";
								Query q = session1.createQuery(hql);
								int result = q.executeUpdate();
								if(result==0)
								{
									System.out.println("no records found");
								}
								else
								{
									System.out.println("rows affectd: "+result);
								}
								session1.close();
								break;
								
				case 3:			Session ses1 = factory.openSession();
								System.out.print("Enter the id to update : ");
								cId = out.nextInt();
								System.out.print("Enter the Firstname to update : ");
								Firstname = out.next();
								System.out.print("Enter the Phoneno to update : ");
								Phoneno = out.nextLong();
								
								String hq2 = "update candidate set Firstname = '"+Firstname+"',Phoneno="+Phoneno+" where id="+cId+"";
								Query q6 = ses1.createQuery(hq2);
								int r = q6.executeUpdate();
								if(r==0)
								{
									System.out.println("no records found");
								}
								else
								{
									System.out.println("rows affected: "+r);
								}
								ses1.close(); 
								
								break;
			
								
				case 4:			String hq = "from candidate as c1 ";
								Query qu = session.createQuery(hq);
								List l = qu.list();
								System.out.println("FIRSTNAME\t LASTNAME\t PHONENO\t EMAIL");
								
								for(Iterator i=l.iterator(); i.hasNext();)
								{
									candidate c11 = (candidate)i.next();
									
									System.out.print(c11.getFirstname());
									
									System.out.print("\t"+c11.getLastname());
									
									System.out.print("\t"+c11.getPhoneno());
									
									System.out.println("\t"+c11.getemailId());
									
								}
								break;
				
				case 5:			String h = "from candidate c1 where c1.EntranceMark > 70 order by c1.EntranceMark desc";
								Query q1 = session.createQuery(h);
								List l1 = q1.list();
								System.out.println(" FIRSTNAME\t LASTNAME\t PHONENO\t EMAILID\t ENTRANCEMARK");
								
								for(Iterator i=l1.iterator(); i.hasNext();)
								{
									candidate c111 = (candidate)i.next();
									
									System.out.print(c111.getFirstname());
									System.out.print("\t"+c111.getLastname());
									System.out.print("\t"+c111.getPhoneno());
									System.out.print("\t"+c111.getemailId());
									System.out.println("\t"+c111.getEntranceMark());
									
								}
								break;
								
				case 6:			String h1 = " from candidate  where EntranceMark=(select max(EntranceMark) from candidate)";
								Query q2 = session.createQuery(h1);
								List l2 = q2.list();
								System.out.println("FIRSTNAME");
								for(Iterator i=l2.iterator(); i.hasNext();)
								{
									candidate c12 = (candidate)i.next();
									
									System.out.println(c12.getFirstname());
								}
								break;
								
				case 7:			String h2 ="select count(EntranceMark) from candidate where EntranceMark > 90 ";
								Query q3 = session.createQuery(h2);
								List l3 = q3.list();
								System.out.println("Total no.of students who have score above 90");
													
								System.out.println("count :\t"+l3.get(0));
								break;
								
				case 8:			String h3 = "select sum(EntranceMark) from candidate";
								Query q4 = session.createQuery(h3);
								List l4 = q4.list();
								System.out.println("sum of score of entire batch");

								System.out.println("sum : \t"+l4.get(0));
								break;
								
				case 9:			System.out.println("Program is terminated");
								System.exit(0);
								break;
				
								
				default:		System.out.println("Enter the correct choice : ");
				
				}
			}
			while(choice!=1);
			 out.close();
			 															
	}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
