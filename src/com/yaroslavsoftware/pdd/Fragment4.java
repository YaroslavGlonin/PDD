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

public class Fragment4 extends SherlockListFragment {
	String data[] = new String[] { 
			  "● Основные положения по допуску транспортных средств к эксплуатации и обязанности должностных лиц по обеспечению безопасности дорожного движения",
			  "● Перечень неисправностей и условий, при которых запрещается эксплуатация транспортных средств"
			  };

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	        /** Creating an array adapter to store the list of countries **/
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,data);
	 
	        /** Setting the list adapter for the ListFragment */
	        setListAdapter(adapter);
	        return super.onCreateView(inflater, container, savedInstanceState);
	    }
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        super.onListItemClick(l, v, position, id);
			if(!GetPref().contains("Open"))
			{
		       /* interstitial = new InterstitialAd(getActivity());
				interstitial.setAdUnitId("ca-app-pub-2638991009658616/2017145882");
				AdRequest adRequest = new AdRequest.Builder().build();
				interstitial.loadAd(adRequest);
				interstitial.setAdListener(new AdListener() {
					public void onAdLoaded() {
						displayInterstitial();
					}
				});*/
			}
	        Intent i = new Intent(getActivity(), WebPage.class);  
        	i.putExtra("head", data[position]);
        	i.putExtra("num", "4_"+Integer.toString(position+1));
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
