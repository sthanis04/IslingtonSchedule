package com.anis.IslingtonSchedule;

import com.anis.FYP_test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	
	private static int SPLASH_TIME_OUT=2000;
	DataSource source;
	int count;
	Intent intent;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		source=new DataSource(this);
		source.getReadableDatabase();
		count=source.countUser();
		
		
		new Handler().postDelayed(new Runnable()
		{
			public void run() {
                
				if (count>0)
				{
                intent = new Intent(Splash.this, Grid.class);
				}
				else
				{
					intent = new Intent(Splash.this, DownloadFiles.class);
				}
                startActivity(intent);
                finish();
                
			
			}
			
		},SPLASH_TIME_OUT);	
				
				
	}

	
}