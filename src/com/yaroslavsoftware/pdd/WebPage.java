
package com.yaroslavsoftware.pdd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;
import com.yaroslavsoftware.pdd.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.*;

@SuppressLint("SetJavaScriptEnabled")
public class WebPage extends SherlockActivity {

	WebView webView;
	String num;
	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.web);
	       Intent intent = getIntent();
	       String head = intent.getStringExtra("head");
	       num = intent.getStringExtra("num");
	       this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	       overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
	       ActionBar ab = getSupportActionBar();
	        ab.setDisplayHomeAsUpEnabled(true);
	        ab.setTitle(head);
	       getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1B2E32")));

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
	       // mAdView.setVisibility(View.GONE);
	       webView = (WebView)findViewById(R.id.mybrowser);
	       webView.clearHistory();
	       webView.clearCache(true);
	       webView.getSettings().setBuiltInZoomControls(true);
	       webView.getSettings().setCacheMode(2);
	       webView.getSettings().setJavaScriptEnabled(true);
	       webView.getSettings().setAllowFileAccess(true);
	       webView.getSettings().setSupportZoom(true);
	       webView.getSettings().setUseWideViewPort(false);
	       webView.getSettings().setLoadWithOverviewMode(false);
	       webView.setWebChromeClient(new MyWebChromeClient());
	       webView.loadUrl("file:///android_asset/"+num+".html");	   
	       //--------------------file---------------------------
	       try {
	       File yourFile = new File("data.dat");
			yourFile.createNewFile();
	       FileOutputStream oFile = new FileOutputStream(yourFile, false); 
	      // writeToFile("asdasd",oFile,"dsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	////---------------file
	   private void writeToFile(String data,Context context,String file) {
		    try {
		        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE));
		        outputStreamWriter.write(data);
		        outputStreamWriter.close();
		    }
		    catch (IOException e) {
		        Log.e("Exception", "File write failed: " + e.toString());
		    } 
		}
	   //-------------------
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
		SharedPreferences preferences;
		final String STATUS = "status_ads";
		public String GetPref()
		{
			preferences = PreferenceManager.getDefaultSharedPreferences(this);
			  String name = preferences.getString(STATUS,"");
			  return name;
		}
		@Override
		public void onBackPressed() 
		{
		    super.onBackPressed();
		    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
		    //------get html----------
		    Log.d("------------------------------", "--------------------------");
		    webView.loadUrl("javascript:alert(Com())");
		   
		}
		private class MyWebChromeClient extends WebChromeClient {
		@Override
		public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
		    Log.d("LogTag", message);
		        result.confirm();
		        return true;
		    }
		}
		
	}
