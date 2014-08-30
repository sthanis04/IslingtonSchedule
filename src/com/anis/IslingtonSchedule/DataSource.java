package com.anis.IslingtonSchedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataSource extends SQLiteOpenHelper {

	private static final String databaseName="IslingtonSchedule";
	private static final int version =1;
	
	 static final String user="User";
	 static final String faculty="faculty";

	static final String timetable="Timetable";
	static final String day ="day";
	static final String startTime="startTime";
	static final String endTime="endTime";
	static final String type="classType";
	static final String year= "year";
	static final String module="module";
	static final String lecturer="lecturer";
	static final String group="group1";
	static final String room="room";
	
	static final String coursework="Coursework";
	static final String task="task";
	static final String deadline="deadline";
	
	static final String exam="Exam";
	static final String examDate="examDate";
	static final String examTime="examTime";
	
	public DataSource(Context context ) 
	{
		super(context, databaseName,null,version);
		
	}

	
	public void onCreate(SQLiteDatabase db) {
		
		Log.d("db","Creating the database");
		String create_user = "CREATE TABLE " + user + "(" + faculty + " TEXT, "
				+ year + " TEXT, " + group + " TEXT " + ");";
		
		String create_tt="CREATE TABLE " + timetable + "(" + day + " TEXT, "
				+ startTime + " TEXT, " + endTime + " TEXT, "
				+ type + " TEXT, "
				+ year + " TEXT, "
				+ module + " TEXT, "
				+ lecturer + " TEXT, "
				+ group + " TEXT, "
				+ room + " TEXT " + ")";
		
		String create_cw="CREATE TABLE " + coursework + "(" + module + " TEXT, "
				+ task + " TEXT, " + year + " TEXT, " + faculty + " TEXT, " + deadline + " TEXT " + ")";
		
		
		String create_ex="CREATE TABLE " + exam + "(" + examDate + " TEXT, "
				+ examTime + " TEXT, " + module + " TEXT, " + year + " TEXT, " + faculty + " TEXT " + ")";
		
		db.execSQL(create_user);
		db.execSQL(create_tt);
		db.execSQL(create_cw);
		db.execSQL(create_ex);
		System.out.println("Tables created");
		Log.d("db","tables created");
		
	}

	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		

	}
	
	//inserting values in table
	public void insertUser(String faculty1, String year1, String group1)
	{
		System.out.println("Insert user reached");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues records = new ContentValues();
		records.put(faculty,faculty1);
		records.put(year,year1);
		records.put(group,group1);
		
		db.insert(user, null, records);
		db.close();
		
	}
	
	public User getUser1()
	{
		System.out.println("getUser reached");
		String fac="";
		String yr="";
		String gp="";
		String uquery = "Select *from " + user;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery(uquery,null);
		cursor.moveToFirst();
		
			fac= cursor.getString(0);
			yr=cursor.getString(1);
			gp=cursor.getString(2);
			if (fac.equals(null)|| yr.equals(null)||gp.equals(null))
			{
				System.out.println("no data");
			}
			
			
			else
			{
				System.out.println(fac+yr+gp);
			}
		
		User user=new User(fac,yr,gp);
		cursor.close();
		db.close();
		return user;
		
	}
	
	public int countUser()
	{
		String uquery = "Select *from " + user;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery(uquery,null);
		int count = cursor.getCount();
		cursor.close();
		db.close();
		return count;
		
	}
	
	public void deleteUser()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(user, null, null);
		db.close();
	}
	
	


}
