package com.anis.IslingtonSchedule;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TimetableSource
{
	
	private SQLiteDatabase db;
	private DataSource source;
	private String timetable=DataSource.timetable;
	private String day=DataSource.day;
	private String startTime=DataSource.startTime;
	private String endTime=DataSource.endTime;
	private String type=DataSource.type;
	private String year=DataSource.year;
	private String module=DataSource.module;
	private String lecturer=DataSource.lecturer;
	private String group=DataSource.group;
	private String room=DataSource.room;

	private String userYear;
	private String userGroup;
	User user;
	public TimetableSource(Context context)
	{
			source=new DataSource(context);
			System.out.println("timetable reached");
			
	}
	
	public void close()
	{
		source.close();
	}
	
	
	public void insertTimetable
	(String day1,String startTime1, String endTime1,String type1,String year1,
			String module1,String lecturer1,String group1,String room1)
	{
		System.out.println("Insert Timetable reached");
		SQLiteDatabase db = source.getWritableDatabase();
		ContentValues records = new ContentValues();
		records.put(day,day1);
		records.put(startTime,startTime1);
		records.put(endTime,endTime1);
		records.put(type,type1);
		records.put(year,year1);
		records.put(module,module1);
		records.put(lecturer,lecturer1);
		records.put(group,group1);
		records.put(room,room1);
		
		db.insert(timetable, null, records);
		db.close();
		
	}
	
	public int countTimetable()
	
	{
		SQLiteDatabase db = source.getWritableDatabase();
		String query="Select * from "+timetable;
		Cursor cursor = db.rawQuery(query, null);
		int count = cursor.getCount();
		cursor.close();
		db.close();
		return count;
		
	}
	
	public int countTimetableLogin(String userYear, String userGroup)
	{
		
		SQLiteDatabase db = source.getWritableDatabase();
		String query="Select * from "+timetable+" where "+year+" like'%"+userYear+"%'"+" and "
				+group+" like'%"+userGroup+"%'";
		Cursor cursor = db.rawQuery(query, null);
		int count = cursor.getCount();
		cursor.close();
		db.close();
		return count;
		
	}
	
	public List<Timetable> getTimetable()
	{
		user=source.getUser1();
		System.out.println(user.getGroup1()+user.getYear());
		userYear=user.getYear();
		userGroup=user.getGroup1();
		SQLiteDatabase db = source.getWritableDatabase();
		List<Timetable> timetables= new ArrayList<Timetable>();
		String query="Select * from "+timetable+" where "+year+" like'%"+userYear+"%'"+" and "
				+group+" like'%"+userGroup+"%'";
		
		Cursor cursor=db.rawQuery(query,null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			Timetable tt = new Timetable();
			tt.setDay(cursor.getString(0));
			tt.setStartTime(cursor.getString(1));
			tt.setEndTime(cursor.getString(2));
			tt.setType(cursor.getString(3));
			tt.setModule(cursor.getString(5));
			tt.setLecturer(cursor.getString(6));
			tt.setRoom(cursor.getString(8));
			timetables.add(tt);
			cursor.moveToNext();
			
		}
		cursor.close();
		db.close();
		return timetables;
	}
	
	public void deleteTimetable()
	{
		System.out.println("Delete timetable reached");
		SQLiteDatabase db = source.getWritableDatabase();
		db.delete(timetable, null, null);
		db.close();
	}
	
	
}
