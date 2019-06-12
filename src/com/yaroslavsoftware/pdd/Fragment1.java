package com.yaroslavsoftware.pdd;

import com.actionbarsherlock.app.SherlockListFragment;
import com.yaroslavsoftware.pdd.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.*;

public class Fragment1 extends SherlockListFragment {

	  String data[] = new String[] { 
			  "1. ����� ���������",
			  "2. ����� ����������� ���������",
			  "3. ���������� ����������� ��������",
			  "4. ����������� ���������",
			  "5. ����������� ����������",
			  "6. ������� ��������� � �������������",
			  "7. ���������� ��������� ������������ � ����� ��������� ���������",
			  "8. ������ ��������, ��������������",
			  "9. ������������ ������������ ������� �� �������� �����",
			  "10. �������� ��������",
			  "11. �����, ��������� �������",
			  "12. ��������� � �������",
			  "13. ������ ������������",
			  "14. ���������� �������� � ����� ��������� ���������� ������������ �������",
			  "15. �������� ����� ��������������� ����",
			  "16. �������� �� ���������������",
			  "17. �������� � ����� �����",
			  "18. ��������� ���������� ������������ �������",
			  "19. ����������� �������� ��������� ��������� � ��������� ���������",
			  "20. ���������� ������������ ������������ �������",
			  "21. ������� ����",
			  "22. ��������� �����",
			  "23. ��������� ������",
			  "24. �������������� ���������� � �������� �������������� � ��������� �������",
			  "25. �������������� ���������� � �������� ������� �������, � ����� � ������� ��������" };
	  private InterstitialAd interstitial;
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
		      /*  interstitial = new InterstitialAd(getActivity());
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
        	i.putExtra("num", "1_"+Integer.toString(position+1));
        	startActivity(i);
        	
	      }
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
