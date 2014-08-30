package com.anis.IslingtonSchedule;

import java.sql.Connection;

import com.anis.FYP_test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DownloadFiles extends Activity {
	TextView tv;
	ConnectionDetector cd;
	Boolean check;

	protected void onCreate(Bundle savedInstanceState) {
		Log.d("Download","Download all reached");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank);
		tv=(TextView) findViewById(R.id.connection);
		
		cd = new ConnectionDetector(getApplicationContext());
		check=cd.getConnection();
		if(check)
		{
		DownloadTimetable dT=new DownloadTimetable(DownloadFiles.this, "download all");
		}
		else 
		{
			String str="No internet connection. Pleaes connect to internet";
			Toast.makeText(DownloadFiles.this,str,Toast.LENGTH_LONG).show();
			tv.setText("No internet connection. Please connect to internet and restart the application");
		}
	}
	

	

}