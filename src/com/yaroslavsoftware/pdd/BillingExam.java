package com.yaroslavsoftware.pdd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.actionbarsherlock.app.SherlockActivity;
import com.yaroslavsoftware.pdd.util.IabHelper;
import com.yaroslavsoftware.pdd.util.IabResult;
import com.yaroslavsoftware.pdd.util.Inventory;
import com.yaroslavsoftware.pdd.util.Purchase;

public class BillingExam  extends SherlockActivity
{

	public IabHelper mHelper;

	public void purchaseLauncher()
	{

		if (mHelper != null) mHelper.flagEndAsync();
        	mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,mPurchaseFinishedListener, "mypurchasetoken");
	}

	/*The finished, query and consume listeners should also be implemented in here*/
	 Fragment fragment9 = new Fragment9();
	   SharedPreferences preferences;
	   final String STATUS = "status";
	//	private static final String TAG1 = "<your domain>.inappbilling";
		private static final String TAG = "com.yaroslavsoftware.pdd.bilets";
		//IabHelper mHelper;
		static final String ITEM_SKU = "android.test.purchased";
		static final String ITEM_SKU1 = "com.yaroslavsoftware.pdd.bilets";
		@Override
		public void onStart() {
			super.onStart();
			Log.d(TAG, "onStart");
			
			if(GetPref().contains("Open"))
			{
				try{
					
					//onBackPressed();
					}
				catch(Exception Ex){}
			}
			else
			{
				Log.d(TAG, GetPref());
			String base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt/63B2hnX/Cq+5N1ut/t1eiI5l8BKVg9kK3UHckQRZK831rnQQ9mSyI56n2X7ZHo8AHNn8UBtZ3MXJONAx7CGdFL7HeKZ96x27jj0qtsi48B2Gjy7okCFwgVI17scrVc7UxcObLAoS5CIyIU+fNxebDJ6DDe3pNyt2btqyIDbDdWVbrnXqDqi4x/od2FFOlknjQ5PrmosVf3PNPRbiUtIkzvayw1RG8ycw650GqHuG7Zdk5Vt8BX3FFmmXt7jHvTlTOf3aim7Tyvjeqe447IC0OgO8PNumkMou8G1ShHXdKAw5kB7/HDCRGOvxSHYhNNj4+AeQ0Ct7IjJ2iUh04UdwIDAQAB";
	        
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
	        	});
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
		    	  Log.d(TAG, "Handle failure");
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
		public String GetPref()
		{
			preferences = PreferenceManager.getDefaultSharedPreferences(this);
			  String name = preferences.getString(STATUS,"");
			  return name;
		}
}
