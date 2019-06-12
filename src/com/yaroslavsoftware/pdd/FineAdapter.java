package com.yaroslavsoftware.pdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
 


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
 
public class FineAdapter extends BaseAdapter {
 
	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<PropertyFine> worldpopulationlist = null;
	private ArrayList<PropertyFine> arraylist;
 
	public FineAdapter(Context context, List<PropertyFine> worldpopulationlist) {
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<PropertyFine>();
		this.arraylist.addAll(worldpopulationlist);
	}
 
	public class ViewHolder {
		TextView name;
		TextView text;
		TextView fine;
	}
 
	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}
 
	@Override
	public PropertyFine getItem(int position) {
		return worldpopulationlist.get(position);
	}
 
	@Override
	public long getItemId(int position) {
		return position;
	}
 
	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.rows_fine, null);
			// Locate the TextViews in listview_item.xml
			holder.name= (TextView) view.findViewById(R.id.stat);
			holder.text = (TextView) view.findViewById(R.id.viderj);
			holder.fine = (TextView) view.findViewById(R.id.fine);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.text.setText(worldpopulationlist.get(position).gettext());
		holder.name.setText(worldpopulationlist.get(position).getname());
		holder.fine.setText(worldpopulationlist.get(position).getfine());
		//String str = 
		if (holder.fine.getText().toString().contains("руб"))
		{
			holder.fine.setTextColor(Color.RED);
			holder.fine.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sregred, 0, 0, 0);
		}
 
		// Listen for ListView Item Click

 
		return view;
	}
 
	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arraylist);
		} 
		else 
		{
			for (PropertyFine wp : arraylist) 
			{
				if (wp.gettext().toLowerCase(Locale.getDefault()).contains(charText)) 
				{
					worldpopulationlist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}
 
}
