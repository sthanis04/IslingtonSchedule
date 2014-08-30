package com.anis.IslingtonSchedule;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ExamSource {
	
	private SQLiteDatabase db;
	private DataSource source;
	private String exam=DataSource.exam;
	private String examDate=DataSource.examDate;
	private String examTime=DataSource.examTime;
	private String module=DataSource.module;
	private String year=DataSource.year;
	private String faculty=DataSource.faculty;
	
	private String userYear;
	private String userFaculty;
	User user;
	
	public ExamSource(Context context)
	{
			source=new DataSource(context);
			System.out.println("Exam constructor reached");
			
	}
	
	
	public void close()
	{
		source.close();
	}
	
	public void insertExam
	(String examDate1,String examTime1,String module1,String year1,String faculty1)
	{
		System.out.println("Insert exam reached");
		SQLiteDatabase db = source.getWritableDatabase();
		ContentValues records = new ContentValues();
		records.put(examDate,examDate1);
		records.put(examTime,examTime1);
		records.put(module,module1);
		records.put(year,year1);
		records.put(faculty,faculty1);
		
		db.insert(exam, null, records);
		db.close();
		
	}
	
	public int countExam()
	
		{
			SQLiteDatabase db = source.getWritableDatabase();
			String query="Select * from "+exam;
			Cursor cursor = db.rawQuery(query, null);
			int count = cursor.getCount();
			cursor.close();
			db.close();
			return count;
		
		}
	
	public List<Exam> getExam()
	{
		user=source.getUser1();
		System.out.println(user.getFaculty()+user.getYear());
		userYear=user.getYear();
		userFaculty=user.getFaculty();
		SQLiteDatabase db = source.getWritableDatabase();
		List<Exam> exams= new ArrayList<Exam>();
		String query="Select * from "+exam+" where "+year+" like'%"+userYear+"%'"+" and "
				+faculty+" like'%"+userFaculty+"%'";
		Cursor cursor=db.rawQuery(query,null);
		int count = cursor.getCount();
		if (count>0)
		{
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
			{
				Exam ex = new Exam();
				ex.setExamDate(cursor.getString(0));
				ex.setExamTime(cursor.getString(1));
				ex.setModule(cursor.getString(2));
				exams.add(ex);
				cursor.moveToNext();
			
			}
		}
		else{
			Exam ex=new Exam();
			ex.setExamDate("");
			ex.setExamTime("");
			ex.setModule("You don't have any exams. Enjoy.");
			exams.add(ex);
		}
		cursor.close();
		db.close();
		return exams;
	}

	public void deleteExam()
		{
			System.out.println("Delete exam reached");
			SQLiteDatabase db = source.getWritableDatabase();
			db.delete(exam, null, null);
			db.close();
		}


}
