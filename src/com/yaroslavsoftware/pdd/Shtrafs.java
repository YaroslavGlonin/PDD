package com.yaroslavsoftware.pdd;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;
import com.yaroslavsoftware.pdd.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class Shtrafs extends SherlockActivity {


	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.show_shtraf);
	       Intent intent = getIntent();
	       String json = intent.getStringExtra("json");
	       String data = intent.getStringExtra("data");
	       boolean error = intent.getBooleanExtra("error",false);
	       String errormsg = intent.getStringExtra("errormsg");
	       overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
	       ActionBar ab = getSupportActionBar();
	        ab.setDisplayHomeAsUpEnabled(true);
	        ab.setTitle("ּמט רענאפû");
	        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1B2E32")));
	        

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

	}
