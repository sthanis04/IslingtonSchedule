package com.anis.IslingtonSchedule;

import com.anis.FYP_test1.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter
	{
		private Context context;
		private final String[] itemName;
		
		public GridAdapter(Context context, String[] itemName) {
			this.context = context;
			this.itemName = itemName;
		}

		
		public int getCount() {
			
			return itemName.length;
		}

		
		public Object getItem(int position) {
			
			return null;
		}

		
		public long getItemId(int position) {
			
			return 0;
		}

		
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View gridView = null;
			
			if (convertView==null)
					{
						gridView = new View(context);
						gridView = inflater.inflate(R.layout.griditem, null);
						
						TextView textView = (TextView) gridView.findViewById(R.id.grid_label);
						textView.setText(itemName[position]);

						ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_image);
						
						String item = itemName[position];
						
						
					if (item.equalsIgnoreCase("timetable"))	
					{
						imageView.setImageResource(R.drawable.timetable);

					}
					
					else if (item.equalsIgnoreCase("coursework"))	
					{
						imageView.setImageResource(R.drawable.coursework);
					}
					
					else if (item.equalsIgnoreCase("exam"))	
					{
						imageView.setImageResource(R.drawable.test);
					}
					
					}
					else 
					{
						gridView = (View) convertView;

					}
					
					return gridView;
				
			
		}

	}
