package com.yaroslavsoftware.pdd;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.yaroslavsoftware.pdd.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment9 extends SherlockListFragment {
	String data[] = new String[] { 
			  "Билет 1",
			  "Билет 2",
			  "Билет 3",
			  "Билет 4",
			  "Билет 5",
			  "Билет 6",
			  "Билет 7",
			  "Билет 8",
			  "Билет 9",
			  "Билет 10",
			  "Билет 11",
			  "Билет 12",
			  "Билет 13",
			  "Билет 14",
			  "Билет 15",
			  "Билет 16",
			  "Билет 17",
			  "Билет 18",
			  "Билет 19",
			  "Билет 20",
			  "Билет 21",
			  "Билет 22",
			  "Билет 23",
			  "Билет 24",
			  "Билет 25",
			  "Билет 26",
			  "Билет 27",
			  "Билет 28",
			  "Билет 29",
			  "Билет 30",
			  "Билет 31",
			  "Билет 32",
			  "Билет 33",
			  "Билет 34",
			  "Билет 35",
			  "Билет 36",
			  "Билет 37",
			  "Билет 38",
			  "Билет 39",
			  "Билет 40",
			  };

	//    @Override
	  //  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	        /** Creating an array adapter to store the list of countries **/
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,data);
	 
	        /** Setting the list adapter for the ListFragment */
	        setListAdapter(adapter);
	       // return super.onCreateView(inflater, container, savedInstanceState);
	    }
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        super.onListItemClick(l, v, position, id);
			if(!GetPref().contains("Open"))
			{
		     /*   interstitial = new InterstitialAd(getActivity());
				interstitial.setAdUnitId("ca-app-pub-2638991009658616/2017145882");
				AdRequest adRequest = new AdRequest.Builder().build();
				interstitial.loadAd(adRequest);
				interstitial.setAdListener(new AdListener() {
					public void onAdLoaded() {
						displayInterstitial();
					}
				});*/
			}
	      //  Intent i = new Intent(getActivity(), WebPage.class);  
			Intent i = new Intent(getActivity(), GridPage.class);  
        	i.putExtra("head", data[position]);
        	i.putExtra("num", "pddex/bilet"+Integer.toString(position+1));
        	startActivity(i);
	      }
	    private InterstitialAd interstitial;
	    public void displayInterstitial() {
	        if (interstitial.isLoaded()) {
	          interstitial.show();
	        }
	    }
		SharedPreferences preferences;
		final String STATUS = "status_ads";
		public String GetPref()
		{
			preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
			  String name = preferences.getString(STATUS,"");
			  return name;
		}

}
