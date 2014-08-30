package com.anis.IslingtonSchedule;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import au.com.bytecode.opencsv.CSVReader;

import com.anis.IslingtonSchedule.DownloadExam.ExamDownload;

public class DownloadCoursework {

	private ProgressDialog pDialog;
	private Context context;
	CourseworkSource cs;
	private String message;
	public  DownloadCoursework(Context c, String message1)
	{
		System.out.println("Download Coursework reached");
		context=c;
		message=message1;
		cs=new CourseworkSource(context);
		int count = cs.countCoursework();
		if (count>0){
			 cs.deleteCoursework();
		}
		new CourseworkDownload().execute();
	}
	
	
	class CourseworkDownload extends AsyncTask<Void,Void,Boolean>
	{
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Loading Coursework...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			downloadFile();
			System.out.println("finished");
			return null;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			pDialog.dismiss();
			if(message.equals("download all"))
			{
			Intent intent=new Intent(context,Login.class);
			context.startActivity(intent);
			}
			else
			{
				Intent intent=new Intent(context,CourseworkUI.class);
				context.startActivity(intent);
				
			}

		}
		
	}
	
	
	private void downloadFile() {
		
	    System.out.println("download coursework reached");
	    
	    FTPClient ftpClient = new FTPClient();
	    try {
	    	System.out.println("ftp client for coursework");
	        ftpClient.connect("islingtonproject.comxa.com", 21);
	        ftpClient.login("a5608539","anish123");
	        ftpClient.enterLocalPassiveMode();
	        System.out.println("ftpclient connected for coursework");
	        
	        ftpClient.changeWorkingDirectory("/islington");
	        InputStream inStream = ftpClient.retrieveFileStream("coursework.csv");
	       
	        CSVReader reader = new CSVReader(new InputStreamReader(inStream),',','"',1);
            for(;;) {
                String[] next = reader.readNext();
                if(next != null) {
                    String module1=next[0];
                    String task1=next[1];
                    String year1=next[2];
                    String faculty1=next[3];
                    String deadline1=next[4];
                    
                    cs.insertCoursework(module1, task1, year1, faculty1,deadline1);
                    
                } else {
                    break;
                }
                System.out.println("");
            }
            
            System.out.println("downloaded coursework successfully");

            
            ftpClient.logout();
            ftpClient.disconnect();
	        
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e){
	        e.printStackTrace();
	    }
		
	}
	
}
	

