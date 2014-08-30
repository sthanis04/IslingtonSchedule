package com.anis.IslingtonSchedule;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {
	Context context;
	
	public ConnectionDetector(Context con)
	{
		context=con;
	}
	
	public boolean getConnection()
	{
		ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork !=null && activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
	

}
