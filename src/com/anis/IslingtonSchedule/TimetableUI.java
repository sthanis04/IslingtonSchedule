package com.anis.IslingtonSchedule;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.anis.FYP_test1.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class TimetableUI extends TabActivity
{
	ActionBar actionBar;
	
	int defaultTab;
	TabHost tabhost;
	
	
	protected void onCreate(Bundle i)
	{
		super.onCreate(i);
		setContentView(R.layout.timetabledisplay);
		setTitle("Class Timetable");
		
		tabhost = getTabHost();
		
		TabSpec sunday = tabhost.newTabSpec("Sun");
		sunday.setIndicator("SUN");
		Intent sundayIntent = new Intent(this, Sunday.class);
		sunday.setContent(sundayIntent);

		TabSpec monday = tabhost.newTabSpec("Mon");
		monday.setIndicator("MON");
		Intent mondayIntent = new Intent(this, Monday.class);
		monday.setContent(mondayIntent);
		
		TabSpec tuesday = tabhost.newTabSpec("Tues");
		tuesday.setIndicator("TUE");
		Intent tuesdayIntent = new Intent(this, Tuesday.class);
		tuesday.setContent(tuesdayIntent);
		
		TabSpec wednesday = tabhost.newTabSpec("Wed");
		wednesday.setIndicator("WED");
		Intent wednesdayIntent = new Intent(this, Wednesday.class);
		wednesday.setContent(wednesdayIntent);
		
		TabSpec thursday = tabhost.newTabSpec("Thu");
		thursday.setIndicator("THU");
		Intent thursdayIntent = new Intent(this, Thursday.class);
		thursday.setContent(thursdayIntent);
		
		TabSpec friday = tabhost.newTabSpec("Fri");
		friday.setIndicator("FRI");
		Intent fridayIntent = new Intent(this, Friday.class);
		friday.setContent(fridayIntent);
		
		tabhost.addTab(sunday); 
		tabhost.addTab(monday); 
		tabhost.addTab(tuesday); 
		tabhost.addTab(wednesday); 
		tabhost.addTab(thursday);
		tabhost.addTab(friday); 

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Date d = new Date();
		String dayOfTheWeek = sdf.format(d);

		// selecting tab for current day
		if (dayOfTheWeek.equals("Sunday")) {
			tabhost.setCurrentTab(0);
		} else if (dayOfTheWeek.equals("Monday")) {
			tabhost.setCurrentTab(1);
		} else if (dayOfTheWeek.equals("Tuesday")) {
			tabhost.setCurrentTab(2);
		} else if (dayOfTheWeek.equals("Wednesday")) {
			tabhost.setCurrentTab(3);
		} else if (dayOfTheWeek.equals("Thursday")) {
			tabhost.setCurrentTab(4);
		} else if (dayOfTheWeek.equals("Friday")) {
			tabhost.setCurrentTab(5);
		}

		
	}

	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.menu1, menu);
	    
		return true;

	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.update1:
			ConnectionDetector cd=new ConnectionDetector(this);
			boolean check=cd.getConnection();
			
			
			if (check==true)
			{
			DownloadTimetable dT=new DownloadTimetable(this,"only timetable");
			break;
			}
			else 
			{
				Toast.makeText(this,"No internet Connection", Toast.LENGTH_LONG).show();
			}

	}
		return false;
	}
	

	public void onBackPressed()
	{
	     Intent intent = new Intent(this,Grid.class);
	     startActivity(intent);
	}
	
}


