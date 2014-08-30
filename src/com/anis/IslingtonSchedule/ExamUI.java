package com.anis.IslingtonSchedule;

import java.util.ArrayList;
import java.util.List;

import com.anis.FYP_test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ExamUI extends Activity {
	
	ExamSource es;
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("exam UI reached");
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.day);
		 ListView lv=(ListView) findViewById(R.id.daylist);
		
		 es=new ExamSource(this);
		 List<Exam> exams =es.getExam();
		 
		 for (Exam ex : exams)
		 {
			 System.out.println(ex.getExamDate());
			 System.out.println(ex.getExamTime());
			 System.out.println(ex.getModule());
		 }
		
		ExamAdapter adapt = new ExamAdapter(this,exams);
		lv.setAdapter(adapt);
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
			DownloadExam dE=new DownloadExam(this,"only exam");
			break;
			}
			else 
			{
				Toast.makeText(this,"No internet Connection", Toast.LENGTH_LONG).show();
			}

	}
		return false;
	}
	
	
	public void onBackPressed(){
	     Intent intent = new Intent(this,Grid.class);
	     startActivity(intent);
	}

}
