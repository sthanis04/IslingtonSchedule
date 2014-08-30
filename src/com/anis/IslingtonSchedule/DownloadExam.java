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

import com.anis.IslingtonSchedule.DownloadTimetable.TimetableDownload;

public class DownloadExam 
{

	private ProgressDialog pDialog;
	private Context context;
	ExamSource es;
	private String message;
	public  DownloadExam(Context c, String message1)
	{
		System.out.println("Download exam reached");
		context=c;
		message=message1;
		es=new ExamSource(context);
		int count = es.countExam();
		if (count>0){
			 es.deleteExam();
		}
		new ExamDownload().execute();
	}
	
	
	class ExamDownload extends AsyncTask<Void,Void,Boolean>
	{
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Loading Exam...");
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
			DownloadCoursework dC=new DownloadCoursework(context,"download all");
			}
			else
			{
				Intent intent=new Intent(context,ExamUI.class);
				context.startActivity(intent);
				
			}

		}
		
	}
	
	
	private void downloadFile() {
		
	    System.out.println("download exam reached");
	    
	    FTPClient ftpClient = new FTPClient();
	    try {
	    	System.out.println("ftp client for exam");
	        ftpClient.connect("islingtonproject.comxa.com", 21);
	        ftpClient.login("a5608539","anish123");
	        ftpClient.enterLocalPassiveMode();
	        System.out.println("ftpclient connected for exam");
	        
	        ftpClient.changeWorkingDirectory("/islington");
	        InputStream inStream = ftpClient.retrieveFileStream("exam.csv");
	       
	        CSVReader reader = new CSVReader(new InputStreamReader(inStream),',','"',1);
            for(;;) {
                String[] next = reader.readNext();
                if(next != null) {
                    String examDate1=next[0];
                    String examTime1=next[1];
                    String module1=next[2];
                    String year1=next[3];
                    String faculty1=next[4];
                    
                    es.insertExam(examDate1, examTime1, module1, year1, faculty1);
                    
                } else {
                    break;
                }
                System.out.println("");
            }
            
            System.out.println("downloaded exam successfully");

            
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
