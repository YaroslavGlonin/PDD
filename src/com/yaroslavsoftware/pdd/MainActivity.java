package com.yaroslavsoftware.pdd;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.yaroslavsoftware.pdd.R;
import com.yaroslavsoftware.pdd.util.IabHelper;
import com.yaroslavsoftware.pdd.util.IabResult;
import com.yaroslavsoftware.pdd.util.Inventory;
import com.yaroslavsoftware.pdd.util.Purchase;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.view.GravityCompat;

public class MainActivity extends SherlockFragmentActivity {

	// Declare Variables
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	Fragment fragment1 = new Fragment1();
	Fragment fragment2 = new Fragment2();
	Fragment fragment3 = new Fragment3();
	Fragment fragment4 = new Fragment4();
	Fragment fragment5 = new Fragment5();
	Fragment fragment6 = new Fragment6();
	Fragment fragment7 = new Fragment7();
	Fragment fragment8 = new Fragment8();
	Fragment fragment9 = new Fragment9();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from drawer_main.xml
		setContentView(R.layout.drawer_main);
		// Get the Title
		mTitle = mDrawerTitle = getTitle();
		// Generate title
		title = new String[] { "ПДД", "Дорожные знаки",
				"Дорожная разметка", "Основные положения", "Проверить штрафы","Коды регионов","Штрафы","Проверить авто","Билеты ПДД"};

		// Generate subtitle
		subtitle = new String[] { "Subtitle Fragment 1", "Subtitle Fragment 2",
				"Subtitle Fragment 3", "Subtitle Fragment 4", "Subtitle Fragment 5", "Subtitle Fragment 6", "Subtitle Fragment 7", "Subtitle Fragment 8", "Subtitle Fragment 9" };

		// Generate icon
		icon = new int[] { R.drawable.book, R.drawable.znak,
				R.drawable.road, R.drawable.police, R.drawable.wallet, R.drawable.regn, R.drawable.ruble, R.drawable.lock, R.drawable.teacher};

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.listview_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass string arrays to MenuListAdapter
		mMenuAdapter = new MenuListAdapter(MainActivity.this, title, subtitle,
				icon);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture listview menu item click
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1B2E32")));
		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				// Set the title on the action when drawer open
				getSupportActionBar().setTitle(mDrawerTitle);
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
		mDrawerLayout.openDrawer(mDrawerList);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(GetPref().contains("Open"))
		{

		}
		else
		{
		 /*   MenuItem logoutMI= menu.add(0,1,0, "Отключить рекламу");
		    logoutMI.setIcon(R.drawable.buy);
		    logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		    logoutMI.setOnMenuItemClickListener(new OnMenuItemClickListener() {
	
		        @Override
		        public boolean onMenuItemClick(MenuItem item) {
		        	if(isOnline())
		        	{
		        	if (mHelper != null) mHelper.flagEndAsync();
		            	mHelper.launchPurchaseFlow(MainActivity.this, ITEM_SKU, 10001,mPurchaseFinishedListener, "mypurchasetoken");
		        	}
		        	else
		        	{
		        		Toast.makeText(getBaseContext(),"Проверьте интернет соединение.", Toast.LENGTH_SHORT).show();
		        	}
		            return true;
		        }
		    });*/
		}
	    // Inflate the menu; this adds items to the action bar if it is present.
	   // getMenuInflater().inflate(R.menu.action_items, menu);
	    return true;
	}
	 public boolean isOnline() {
		    ConnectivityManager cm =
		        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
		        return true;
		    }
		    return false;
		}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}
	// ListView click listener in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}
	private void selectItem(int position) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		// Locate Position
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, fragment1).commit();
			//ft.addToBackStack(null);
			break;
		case 1:
			ft.replace(R.id.content_frame, fragment2).commit();
			break;
		case 2:
			ft.replace(R.id.content_frame, fragment3).commit();
			break;
		case 3:
			ft.replace(R.id.content_frame, fragment4).commit();
			break;
		case 4:
			ft.replace(R.id.content_frame, fragment5).commit();
			break;
		case 5:
			ft.replace(R.id.content_frame, fragment6).commit();
			break;
		case 6:
			ft.replace(R.id.content_frame, fragment7).commit();
			break;
		case 7:
			ft.replace(R.id.content_frame, fragment8).commit();
			break;
		case 8:
				ft.replace(R.id.content_frame, fragment9).commit();
			break;
		}
		//.ft.commit();
		mDrawerList.setItemChecked(position, true);

		// Get the title followed by the position
		setTitle(title[position]);
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	SharedPreferences preferences;
	final String STATUS = "status_ads";
	public String GetPref()
	{
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		  String name = preferences.getString(STATUS,"");
		  return name;
	}
	//	private static final String TAG1 = "<your domain>.inappbilling";
		private static final String TAG = "com.yaroslavsoftware.pdd.bilets";
		IabHelper mHelper;
		static final String ITEM_SKU1 = "android.test.purchased";
		static final String ITEM_SKU = "com.yaroslavsoftware.pdd.checkauto";
	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
		
		if(GetPref().contains("Open"))
		{

		}
		else
		{
		//Log.d(TAG, GetPref());
		/*String base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt/63B2hnX/Cq+5N1ut/t1eiI5l8BKVg9kK3UHckQRZK831rnQQ9mSyI56n2X7ZHo8AHNn8UBtZ3MXJONAx7CGdFL7HeKZ96x27jj0qtsi48B2Gjy7okCFwgVI17scrVc7UxcObLAoS5CIyIU+fNxebDJ6DDe3pNyt2btqyIDbDdWVbrnXqDqi4x/od2FFOlknjQ5PrmosVf3PNPRbiUtIkzvayw1RG8ycw650GqHuG7Zdk5Vt8BX3FFmmXt7jHvTlTOf3aim7Tyvjeqe447IC0OgO8PNumkMou8G1ShHXdKAw5kB7/HDCRGOvxSHYhNNj4+AeQ0Ct7IjJ2iUh04UdwIDAQAB"; 	
        
        	mHelper = new IabHelper(this, base64EncodedPublicKey);
        
        	mHelper.startSetup(new 
			IabHelper.OnIabSetupFinishedListener() {
        	   	 public void onIabSetupFinished(IabResult result) 
			 {
        	           if (!result.isSuccess()) {
        	             Log.d(TAG, "In-app Billing setup failed: " + 
					result);
        	           } else {             
        	      	     Log.d(TAG, "In-app Billing is set up OK");
		           }
        	         }
        	});*/
		}
        	
	} 
	@Override
	public void onActivityResult(int requestCode, int resultCode,Intent data) 
	{

		Log.d(TAG, "onActivityResult requestCode");
	      if (!mHelper.handleActivityResult(requestCode, resultCode, data)) 
	      {     
	    	  Log.d(TAG, "onActivityResult");
	    	super.onActivityResult(requestCode, resultCode, data);
	      }
	      else {
	          Log.d(TAG, "onActivityResult handled by IABUtil.");
	      }
	}		
	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener 
	= new IabHelper.OnIabPurchaseFinishedListener() {
	public void onIabPurchaseFinished(IabResult result, 
                    Purchase purchase) 
	{
	   if (result.isFailure()) {
	      // Handle error
	  Log.d(TAG, "isFailure");
	      return;
	 }      
	 else if (purchase.getSku().equals(ITEM_SKU)) {
	     consumeItem();
	     Log.d(TAG, "consumeItem");
	}
	 else{Log.d(TAG, "SUCCESS PURCHASE!");}
	      
   }
};
public void consumeItem() {
	mHelper.queryInventoryAsync(mReceivedInventoryListener);
	Log.d(TAG, "consumeItem()");
}
	
IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener 
   = new IabHelper.QueryInventoryFinishedListener() {
	   public void onQueryInventoryFinished(IabResult result,
	      Inventory inventory) {

		   		   
	      if (result.isFailure()) {
		  // Handle failure
	    	  Toast.makeText(getBaseContext(),"Отмена покупки.", Toast.LENGTH_SHORT).show();
	      } else {
                 mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU), 
			mConsumeFinishedListener);
                 Log.d(TAG, "consumeAsync");
	      }
    }
};
public IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
		  new IabHelper.OnConsumeFinishedListener() {
		   public void onConsumeFinished(Purchase purchase, 
	             IabResult result) {

		 if (result.isSuccess()) {		    	 
		   	//  clickButton.setEnabled(true);
			 SetPref();

			// Intent intent = new Intent(ShtrafiTest.this, Fragment9.class);
			//    startActivity(intent);
			   Log.d(TAG, "isSuccess()");
		 } else {
		         // handle error
			 Log.d(TAG, "error");
		 }
	  }
	};
	@Override
	public void onDestroy() {
		super.onDestroy();
		 Log.d(TAG, "onDestroy");
		if (mHelper != null) mHelper.dispose();
		mHelper = null;
	}
	public void SetPref()
	{
		   preferences = PreferenceManager.getDefaultSharedPreferences(this);
		   SharedPreferences.Editor editor = preferences.edit();
		   editor.putString(STATUS,"Open");
		   editor.apply();
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
}
