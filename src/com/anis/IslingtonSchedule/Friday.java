package com.anis.IslingtonSchedule;

import java.util.ArrayList;
import java.util.List;

import com.anis.FYP_test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class Friday extends Activity {

	TimetableSource ts;
	public void onCreate(Bundle savedInstanceState) 
	{
	  super.onCreate(savedInstanceState);
	  List<DaySchedule> ds = new ArrayList<DaySchedule>();
	  setContentView(R.layout.day);
	  ListView lv=(ListView) findViewById(R.id.daylist);
	  
	 ts = new TimetableSource(this);
	System.out.println("Friday");
	
	  List<Timetable> timetables = ts.getTimetable();
	  
	  for (Timetable tt : timetables) {
			String dayValue = tt.getDay();
		
			if (dayValue.equalsIgnoreCase("FRI"))
			{
				String sT =tt.getStartTime();
				String eT=tt.getEndTime();
				String tP=tt.getType();
				String md=tt.getModule();
				String lec=tt.getLecturer();
				String rm=tt.getRoom();
				
				DaySchedule ds1=new DaySchedule(sT,eT,tP,md,lec,rm);
				ds.add(ds1);
			}
			
	  }
	  
	  if (ds.size()==0)
	  {
		  ds.add(new DaySchedule(" ", "Islington College","Enjoy","No classes today"," "," "));
	  }
	  
	  
	  	TimeListAdapter adapt=new TimeListAdapter(this,ds);
	  	lv.setAdapter(adapt);
	}

	public void onBackPressed()
	{
	     Intent intent = new Intent(this,Grid.class);
	     startActivity(intent);
	}
}