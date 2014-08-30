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

public class DownloadTimetable
{
	private ProgressDialog pDialog;
	private Context context;
	TimetableSource ts;
	String message="message";
	public  DownloadTimetable(Context c, String message1)
	{
		System.out.println("Download timetable reached");
		context=c;
		message=message1;
		ts=new TimetableSource(context);
		int count = ts.countTimetable();
		if (count>0){
			 ts.deleteTimetable();
		}
		new TimetableDownload().execute();
	}
	
	
	class TimetableDownload extends AsyncTask<Void,Void,Boolean>
	{
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Loading Timetable...");
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
			System.out.println(message);
			if(message.equals("download all"))
			{
			DownloadExam dE=new DownloadExam(context,"download all");
			}
			else
			{
				Intent intent=new Intent(context,TimetableUI.class);
				context.startActivity(intent);
				
			}
		}
		
	}
	
	
	private void downloadFile() {
		
	    System.out.println("download file reached");
	    
	    FTPClient ftpClient = new FTPClient();
	    try {
	    	System.out.println("ftp client");
	        ftpClient.connect("islingtonproject.comxa.com", 21);
	        ftpClient.login("a5608539","anish123");
	        ftpClient.enterLocalPassiveMode();
	        System.out.println("ftpclient connected");
	        
	        ftpClient.changeWorkingDirectory("/islington");
	        InputStream inStream = ftpClient.retrieveFileStream("timetable.csv");
	       
	        CSVReader reader = new CSVReader(new InputStreamReader(inStream),',','"',1);
            for(;;) {
                String[] next = reader.readNext();
                if(next != null) {
                    String day1=next[0];
                    String startTime1=next[1];
                    String endTime1=next[2];
                    String type1=next[3];
                    String year1=next[4];
                    String module1=next[5];
                    String lecturer1=next[6];
                    String group1=next[7];
                    String room1=next[8];
                    
                    ts.insertTimetable(day1, startTime1, endTime1, type1, year1, module1, lecturer1, group1, room1);
                    
                } else {
                    break;
                }
                System.out.println("");
            }
            
            System.out.println("downloaded successfully");

            
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
