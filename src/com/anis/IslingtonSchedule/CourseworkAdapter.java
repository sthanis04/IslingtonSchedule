package com.anis.IslingtonSchedule;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anis.FYP_test1.R;

public class CourseworkAdapter extends ArrayAdapter<Coursework> {
	
	private LayoutInflater inflater;

	public CourseworkAdapter(Context context, List<Coursework> courseworks) 
	{
		
		super(context,R.layout.timetableadapter,courseworks);
		inflater= (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View view = inflater.inflate(R.layout.timetableadapter, parent,
				false);
		
		TextView tv1 = (TextView) view.findViewById(R.id.module);
		TextView tv2 = (TextView) view.findViewById(R.id.type);
		TextView tv3 = (TextView) view.findViewById(R.id.startime);
		TextView tv4 = (TextView) view.findViewById(R.id.endtime);
		TextView tv5 = (TextView) view.findViewById(R.id.lecturer);
		TextView tv6 = (TextView) view.findViewById(R.id.room);
		
		Coursework cw = getItem(position);
		tv1.setText(cw.getModule());
		tv2.setText("");
		tv3.setText(cw.getTask());
		tv4.setText(" ");
		tv5.setText(null);
		tv6.setText(cw.getDeadline());
		return view;


	}

}