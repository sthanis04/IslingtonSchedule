package com.anis.IslingtonSchedule;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseworkSource
{
	private SQLiteDatabase db;
	private DataSource source;
	private String coursework=DataSource.coursework;
	private String task=DataSource.task;
	private String deadline=DataSource.deadline;
	private String module=DataSource.module;
	private String year=DataSource.year;
	private String faculty=DataSource.faculty;

	
	private String userYear;
	private String userFaculty;
	User user;
	
	public CourseworkSource(Context context)
	{
			source=new DataSource(context);
			System.out.println("Coursework constructor reached");
			
	}
	
	
	public void close()
	{
		source.close();
	}
	
	public void insertCoursework
	(String module1,String task1,String year1,String faculty1,String deadline1 )
	{
		System.out.println("Insert coursework reached");
		SQLiteDatabase db = source.getWritableDatabase();
		ContentValues records = new ContentValues();
		records.put(module,module1);
		records.put(task,task1);
		records.put(year,year1);
		records.put(faculty,faculty1);
		records.put(deadline,deadline1);
		
		db.insert(coursework, null, records);
		db.close();
		
	}
	
	public int countCoursework()
	
	{
		SQLiteDatabase db = source.getWritableDatabase();
		String query="Select * from "+coursework;
		Cursor cursor = db.rawQuery(query, null);
		int count = cursor.getCount();
		cursor.close();
		db.close();
		return count;
	
	}
	
	
	public List<Coursework> getCoursework()
	{
		user=source.getUser1();
		System.out.println(user.getFaculty()+user.getYear());
		userYear=user.getYear();
		userFaculty=user.getFaculty();
		SQLiteDatabase db = source.getWritableDatabase();
		List<Coursework> courseworks= new ArrayList<Coursework>();
		String query="Select * from "+coursework+" where "+year+" like'%"+userYear+"%'"+" and "
				+faculty+" like'%"+userFaculty+"%'";
		Cursor cursor=db.rawQuery(query,null);
		int count = cursor.getCount();
		if (count>0)
		{
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
			{
				Coursework cw = new Coursework();
				cw.setModule(cursor.getString(0));
				cw.setTask(cursor.getString(1));
				cw.setDeadline(cursor.getString(4));
				courseworks.add(cw);
				cursor.moveToNext();
			
			}
		}
		else{
			Coursework cw=new Coursework();
			cw.setDeadline("");
			cw.setTask("");
			cw.setModule("Sorry. Coursework not uploaded");
			courseworks.add(cw);
		}
		cursor.close();
		db.close();
		return courseworks;
	}
	
	public void deleteCoursework()
	{
		System.out.println("Delete coursework reached");
		SQLiteDatabase db = source.getWritableDatabase();
		db.delete(coursework, null, null);
		db.close();
	}

	
	
}
