package hcql;

public class Employee
{
	int emp_id;
	String emp_name;
	int yr_experience;
	int salary;
	
	int getemp_id()
	{
		return emp_id;
	}
	void setemp_id(int emp_id)
	{
		this.emp_id = emp_id;
	}
	
	String getemp_name()
	{
		return emp_name;
	}
	void setemp_name(String emp_name)
	{
		this.emp_name = emp_name;
	}
	
	int getyr_experience()
	{
		return yr_experience;
	}
	void setyr_experience(int yr_experience)
	{
		this.yr_experience = yr_experience;
	}
	
	int getsalary()
	{
		return salary;
	}
	void setsalary(int salary)
	{
		this.salary = salary;
	}

}
