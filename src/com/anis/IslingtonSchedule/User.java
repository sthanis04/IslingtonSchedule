package com.anis.IslingtonSchedule;


public class User { 

	String faculty;
	String year;
	String group1;
	
	public User(String fac, String yr, String gp)
	{
		faculty=fac;
		year=yr;
		group1=gp;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGroup1() {
		return group1;
	}

	public void setGroup1(String group1) {
		this.group1 = group1;
	}
	
	
	
}
