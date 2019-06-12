package com.yaroslavsoftware.pdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
 

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
 
public class ListViewAdapter extends BaseAdapter {
 
	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<WorldPopulation> worldpopulationlist = null;
	private ArrayList<WorldPopulation> arraylist;
 
	public ListViewAdapter(Context context, List<WorldPopulation> worldpopulationlist) {
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<WorldPopulation>();
		this.arraylist.addAll(worldpopulationlist);
	}
 
	public class ViewHolder {
		ImageView img;
		TextView text;
	}
 
	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}
 
	@Override
	public WorldPopulation getItem(int position) {
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
			view = inflater.inflate(R.layout.list_reg, null);
			// Locate the TextViews in listview_item.xml
			holder.img = (ImageView) view.findViewById(R.id.image_reg);
			holder.text = (TextView) view.findViewById(R.id.text_reg1);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.img.setImageResource(worldpopulationlist.get(position).getImg());
		holder.text.setText(worldpopulationlist.get(position).gettext());
 
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
			for (WorldPopulation wp : arraylist) 
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
