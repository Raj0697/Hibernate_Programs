package candidatedata;

public class candidate 
{
	int cId;
	String Firstname;
	String Lastname;
	long Phoneno;
	String emailId;
	int EntranceMark;
	
	int getcId()
	{
		return cId;
	}
	void setcId(int cId)
	{
		this.cId = cId;
	}
										
	String getFirstname()
	{
		return Firstname;
	}
	void setFirstname(String Firstname)
	{
		this.Firstname = Firstname;
	}
	
	String getLastname()
	{
		return Lastname;
	}
	void setLastname(String Lastname)
	{
		this.Lastname = Lastname;
	}
	
	long getPhoneno()
	{
		return Phoneno;
	}
	void setPhoneno(long Phoneno)
	{
		this.Phoneno = Phoneno;
	}
	
	String getemailId()
	{
		return emailId;
	}
	void setemailId(String emailId)
	{
		this.emailId = emailId;
	}
	
	int getEntranceMark()
	{
		return EntranceMark;
	}
	void setEntranceMark(int EntranceMark)
	{
		this.EntranceMark = EntranceMark;
	}
}
