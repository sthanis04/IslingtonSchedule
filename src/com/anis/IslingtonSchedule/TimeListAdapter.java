package com.anis.IslingtonSchedule;

import java.util.List;


import com.anis.FYP_test1.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TimeListAdapter extends ArrayAdapter<DaySchedule>
{

	private LayoutInflater inflater;

	public TimeListAdapter(Context context, List<DaySchedule> daySchedule) 
	{
		
		super(context,R.layout.timetableadapter,daySchedule);
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
		
		DaySchedule ds = getItem(position);
		tv1.setText(ds.getModule());
		tv2.setText("(" + ds.getType()+")");
		tv3.setText(ds.getStartTime());
		tv4.setText(" - "+ds.getEndTime());
		tv5.setText(ds.getLecturer());
		tv6.setText(ds.getRoom());
		return view;


	}
	


}
