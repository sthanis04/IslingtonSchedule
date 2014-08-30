package com.anis.IslingtonSchedule;

import com.anis.FYP_test1.R;

import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener,AdapterView.OnItemSelectedListener {
	Button submit;
	EditText etgroup;
	String faculty;
	String year;
	String group;
	DataSource ds;
	TimetableSource ts;
	
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("Login","Login reached");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Spinner sFaculty=(Spinner) findViewById(R.id.spinnerfaculty); 
		Spinner sYear=(Spinner) findViewById(R.id.spinneryear);
		etgroup=(EditText) findViewById(R.id.etgroup);
		
		ArrayAdapter fAdapt=ArrayAdapter.createFromResource(this, R.array.faculty, android.R.layout.simple_spinner_dropdown_item);
		sFaculty.setAdapter(fAdapt);
		sFaculty.setOnItemSelectedListener(this);
		
		ArrayAdapter yAdapt=ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_dropdown_item);
		sYear.setAdapter(yAdapt);
		sYear.setOnItemSelectedListener(this);
		
		submit=(Button) findViewById(R.id.Submit);
		submit.setOnClickListener(this);
		
		}
	
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		Spinner spinner = (Spinner) parent;
		
		if(spinner.getId()==R.id.spinnerfaculty)
		{
			TextView tFaculty= (TextView) view;
			faculty=tFaculty.getText().toString();
			System.out.println("Selected faculty is"+faculty);
		}
		
		if(spinner.getId()==R.id.spinneryear)
		{
			TextView yFaculty= (TextView) view;
			year=yFaculty.getText().toString();
			System.out.println("Selected year is"+year);
		}
		
	}

	public void onClick(View v) 
	{
		System.out.println("submit clicked");
		group=etgroup.getText().toString().toUpperCase();
		System.out.println(faculty + year + group);
		if ( group.equals(""))
			{
				Toast.makeText(Login.this,"Group cannot be empty",Toast.LENGTH_LONG).show();
			}
		else{
		
			insertIntoUsers();	
		
		}
	}
	
	
	public void insertIntoUsers()
	{
		ts=new TimetableSource(this);
		int count=ts.countTimetableLogin(year, group);
		
		   if (count>0)
		   {
			   ds=new DataSource(this);
			   int countUser=ds.countUser();
			   if (countUser>0)
			   {
				   ds.deleteUser();
			   }
			   ds.insertUser(faculty, year, group);
			   Intent intent = new Intent(Login.this,Grid.class);
			   startActivity(intent);
				
			   
		   }
		   
		   else
		   {
			   Toast.makeText(Login.this,"Invalid group and year.",Toast.LENGTH_LONG).show();
		   }
	}



	
	public void onNothingSelected(AdapterView<?> arg0)
	{
		Toast.makeText(Login.this,"You must select the items",Toast.LENGTH_LONG).show();
	}



	
	
}
