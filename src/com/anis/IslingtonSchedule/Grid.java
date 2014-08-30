package com.anis.IslingtonSchedule;

import com.anis.FYP_test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;



public class Grid extends Activity {
	
	static final String[] ITEM_LIST = new String[] { "Timetable", "Coursework",
		"Exam" };

	
	GridView gridView;
	Intent intent;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		gridView = (GridView) findViewById(R.id.gridview);
	
		gridView.setAdapter(new GridAdapter(this,ITEM_LIST ));
		
		gridView.setOnItemClickListener(new GridClick());
	
		
	}
	
	class GridClick implements OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			if (position==0)
			{
				intent = new Intent(Grid.this,TimetableUI.class);
				startActivity(intent);
				
			}
			else if (position==1)
			{
				intent = new Intent(Grid.this,CourseworkUI.class);
				startActivity(intent);
			}
			
			else if (position==2)
			{
				intent = new Intent(Grid.this,ExamUI.class);
				startActivity(intent);
			}
		}
		
	
	}
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.gridmenu, menu);
	    
		return true;

	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.update:
			Intent u=new Intent(this,DownloadFiles.class);
			startActivity(u);
			break;
			
		case R.id.changegroup:
			Intent p = new Intent(this, Login.class);
			startActivity(p);
			break;

		

	}
		return false;
	}
	
	
	public void onBackPressed()
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

}

