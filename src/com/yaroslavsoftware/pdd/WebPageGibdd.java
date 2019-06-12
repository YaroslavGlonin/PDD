package com.yaroslavsoftware.pdd;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yaroslavsoftware.pdd.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class WebPageGibdd extends SherlockActivity {

	WebView myBrowser;

	   /** Called when the activity is first created. */
	@SuppressLint("SetJavaScriptEnabled")
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.web);
	       Intent intent = getIntent();
	       String head = intent.getStringExtra("head");
	    //   String num = intent.getStringExtra("num");
	       overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
	       ActionBar ab = getSupportActionBar();
	        ab.setDisplayHomeAsUpEnabled(true);
	        ab.setTitle(head);
	        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1B2E32")));
	       myBrowser = (WebView)findViewById(R.id.mybrowser);
	       WebSettings webSettings = myBrowser.getSettings();
	       webSettings.setJavaScriptEnabled(true);
	       myBrowser.setWebChromeClient(new WebChromeClient());
	       myBrowser.loadUrl("http://www.gibdd.ru/check/fines/");
		      AdView mAdView = (AdView) findViewById(R.id.adView);
				if(GetPref().contains("Open"))
				{
					mAdView.setVisibility(View.GONE);
				}
				else
				{
				       
				       AdRequest adRequest = new AdRequest.Builder()
				      // .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				      // .addTestDevice("D6EC39A6C182A466E3FE8FD21C567B15")
				      .build();
				        mAdView.loadAd(adRequest);
				}

	   }
	   @Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		        case android.R.id.home:
		        	onBackPressed();
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
		@Override
		public void onBackPressed() 
		{
		    super.onBackPressed();
		    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
		}
		SharedPreferences preferences;
		final String STATUS = "status_ads";
		public String GetPref()
		{
			preferences = PreferenceManager.getDefaultSharedPreferences(this);
			  String name = preferences.getString(STATUS,"");
			  return name;
		}
	}
