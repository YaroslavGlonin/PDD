package com.yaroslavsoftware.pdd;

import java.net.CookieManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.loopj.android.http.*;

import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.yaroslavsoftware.pdd.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment5 extends SherlockFragment   {//SherlockListFragment   {
EditText reg_num,svid,capt;
Cursor cursor;
private SQLiteAdapter mySQLiteAdapter;
CursorAdapter cursorAdapter;
PersistentCookieStore myCookieStore;
EditText editText;
String hash,token;
ImageView img;
ProgressDialog pd;
Animation rotation;
AlertDialog dialog;
int item_cur;
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	    {
	    	setHasOptionsMenu(true);  
	    	final View view = inflater.inflate(R.layout.close, container, false);
    		Button add = (Button) view.findViewById(R.id.getinfo);
    		add.setText("Официальный сайт ГИБДД");
    		add.setOnClickListener(new OnClickListener() 
    		{
    			@Override
					public void onClick(View v) 
    				{  
        				//dialog.dismiss();
        		        Intent i = new Intent(getActivity(), WebPageGibdd.class);  
        	        	i.putExtra("head", "Проверка авто");
        	        //	i.putExtra("num", "");
        	        	startActivity(i);
    				
					}
			});
	    	 return view;
	    	//return super.onCreateView(inflater, container, savedInstanceState);
	    }
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	     /*    mySQLiteAdapter = new SQLiteAdapter(getActivity());
	         mySQLiteAdapter.openToWrite();
	         cursor = mySQLiteAdapter.queueAll();
	         String[] from = new String[]{SQLiteAdapter.KEY_ID, SQLiteAdapter.KEY_CONTENT1, SQLiteAdapter.KEY_CONTENT2, SQLiteAdapter.KEY_CONTENT3, SQLiteAdapter.KEY_CONTENT4};
	         int[] to = new int[]{R.string.id, R.id.tv_1, R.id.tv_2, R.id.normal, R.id.normal};
	         cursorAdapter =new SimpleCursorAdapter(getActivity(), R.layout.rows, cursor, from, to);
	         setListAdapter(cursorAdapter);*/

	     }
	    AsyncHttpClient client = new AsyncHttpClient();
	    
	    @Override
	    public void onActivityCreated(Bundle savedState) {
	        super.onActivityCreated(savedState);/*
	        myCookieStore = new PersistentCookieStore(getActivity());
			  client.setEnableRedirects(true);
	        getListView().setOnItemClickListener(new OnItemClickListener()
	        {
	            @Override 
	            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
	            { 
    				LayoutInflater inflater = getActivity().getLayoutInflater();
	        		final View alertLayout = inflater.inflate(R.layout.captcha, null);
	        		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
	        		alert.setView(alertLayout);
	        		capt = (EditText) alertLayout.findViewById(R.id.dsvid);
	        		capt.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
	        		item_cur = cursor.getInt(cursor.getColumnIndex(SQLiteAdapter.KEY_ID));
	        		final String num_db = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.KEY_CONTENT1));
	        		final String svid_db = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.KEY_CONTENT2));
	        		dialog = alert.create();
	        		dialog.getWindow().setSoftInputMode(
	        			    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	        		dialog.requestWindowFeature((int) Window.FEATURE_NO_TITLE);
	        		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	        		dialog.show();
	        		getcaptcha(alertLayout);
	        		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String date = df.format(Calendar.getInstance().getTime());
					//--------------------------------------------------
					//mySQLiteAdapter.insert_data(item_id,json,date);
					Log.d("All","OK  "+date);
					Intent i = new Intent(getActivity(), Shtrafs.class);  
					i.putExtra("json", "dd");
					i.putExtra("data", date);
					i.putExtra("error", false);
					i.putExtra("errormsg", "");
		        	startActivity(i);*/
		        	//-----------------------------------------------------
	        		
	        		
	        /*		img = (ImageView) alertLayout.findViewById(R.id.imageView1);
	        		rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);
	        	    img.startAnimation(rotation);
	        	    Button cn = (Button) dialog.findViewById(R.id.button2);
	        		cn.setOnClickListener(new OnClickListener() 
	        		{
	        			@Override
							public void onClick(View v) 
	        				{  
								dialog.dismiss();
							}
					});
	        		Button ok = (Button) dialog.findViewById(R.id.button1);
	        		ok.setOnClickListener(new OnClickListener() 
	        		{
	        			@Override
							public void onClick(View v) 
	        				{ 
	        				dialog.dismiss();
	        				pd = ProgressDialog.show(getActivity(), "", "Подождите идет загрузка данных с сервера ГИБДД. Это может занять около минуты...", false);
	        				String num = num_db.substring(0, 6);
	        				String reg = num_db.substring(6, num_db.length());
	        				//Toast.makeText(getActivity(), num+"<-->"+reg, Toast.LENGTH_SHORT).show();//num_db.length();
	        				RequestParams params = new RequestParams();
	        				try
	        				{
	        			    params.put("req", "fines:"+num+":"+reg+":"+svid_db);
	        			    params.put("captchaWord", capt.getText().toString());
	        			    params.put("captchaCode", hash);
	        			    params.put("token", "");
	        			    params.put("regnum", num);
	        			    params.put("regreg", reg);
	        			    params.put("stsnum", svid_db);
	        			    params.wait();
	        				}
	        				catch(Exception Ex){}
	        			    BasicClientCookie newCookie = new BasicClientCookie("BITRIX_SM_METOD", "GEO");
	        			    BasicClientCookie newCookie1 = new BasicClientCookie("siteType", "deleted");
	        			    newCookie.setVersion(1);
	        			    newCookie.setDomain("www.gibdd.ru");
	        			    newCookie.setPath("/");
	        			    myCookieStore.addCookie(newCookie);
	        			    newCookie1.setVersion(1);
	        			    newCookie1.setDomain("www.gibdd.ru");
	        			    newCookie1.setPath("/");
	        			    myCookieStore.addCookie(newCookie1);
	        			    client.setCookieStore(myCookieStore);
	        			    client.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:25.0) Gecko/20100101 Firefox/25.0");
	        			    client.addHeader("Accept-Encoding", "gzip");  
	        			    client.addHeader("X-Csrf-Token", token);
	        			    client.addHeader("Content-Type:application/x-www-form-urlencoded", "charset=UTF-8");
	        			    client.addHeader("X-Requested-With", "XMLHttpRequest");
	        			    client.addHeader("Referer", "http://www.gibdd.ru/check/fines/");
	        			    client.setCookieStore(myCookieStore);
	        			    client.setTimeout(30000);
	        			    client.post("http://www.gibdd.ru/bitrix/templates/.default/components/gai/check/fines_1.6/ajax/client.php",params, new AsyncHttpResponseHandler() {

								@Override
								public void onFailure(int arg0, Header[] arg1,
										byte[] arg2, Throwable arg3) {
									// TODO Auto-generated method stub
									pd.dismiss();
								}

								@Override
								public void onSuccess(int arg0, Header[] arg1,
										byte[] arg2) 
								{
									pd.dismiss();
									try
									{
									String json = new String(arg2, "UTF-8");
									JSONObject reader = new JSONObject(json);
									//JSONObject request = reader.getJSONObject("request");
									Log.d("status",String.valueOf(reader.getInt("status")));
										if(Integer.parseInt(reader.getString("status"))==1)
												{
													setmsgwindow(getActivity(),"Введенная информация с картинки не верна, попробуйте еще раз...");
												}
										else if(Integer.parseInt(reader.getJSONObject("request").getString("error"))==1)
												{
													setmsgwindow(getActivity(),"Введенный Государственный регистрационный знак или серия и номер свидетельства о регистрации не верны.");
												}
										else if(Integer.parseInt(reader.getString("status"))==200 && "null"!=reader.getString("request"))
												{
													SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
													String date = df.format(Calendar.getInstance().getTime());
													mySQLiteAdapter.insert_data(item_cur,json,date);
													Log.d("All","OK  "+date);
													Intent i = new Intent(getActivity(), Shtrafs.class);  
													i.putExtra("json", json);
													i.putExtra("data", date);
													i.putExtra("error", false);
													i.putExtra("errormsg", "");
										        	startActivity(i);
												}
										else
											{
												setmsgwindow(getActivity(),"Ошибка запроса к базе данных. Попробуйте зайти позже");
											}
									}
									catch(Exception Ex){Log.d("Some","Error2");setmsgwindow(getActivity(),"Ошибка запроса к базе данных. Попробуйте зайти позже");}
									myCookieStore.clear();
									client.removeAllHeaders();
									client.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:25.0) Gecko/20100101 Firefox/25.0");
									client.get("http://www.gibdd.ru/check/fines/", new AsyncHttpResponseHandler() {


										@Override
										public void onFailure(int arg0, Header[] arg1,
												byte[] arg2, Throwable arg3) {
											// TODO Auto-generated method stub
											setmsgwindow(getActivity(),"Произошла неизвестная ошибка сервера, просим повторить попытку позже.");
										}

										
										@Override
										public void onSuccess(int arg0, Header[] arg1,byte[] arg2) 
										{
											
										}
									});
									
								}
	        			    	
	        			    });

	        				
	        				
	        				}
	        				
					});
	            }
	        });
		        getListView().setOnItemLongClickListener(new OnItemLongClickListener() 
		        {
		            @Override
		            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
		            {
		            	final int item_id = cursor.getInt(cursor.getColumnIndex(SQLiteAdapter.KEY_ID));
		            	LayoutInflater inflater = getActivity().getLayoutInflater();
		        		View alertLayout = inflater.inflate(R.layout.del_cont, null);
		        		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		        		alert.setView(alertLayout);
		        		reg_num = (EditText) alertLayout.findViewById(R.id.dmun);
		        		svid = (EditText) alertLayout.findViewById(R.id.dsvid);
		        		final AlertDialog dialog = alert.create();
		        		dialog.requestWindowFeature((int) Window.FEATURE_NO_TITLE);
		        		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		        		dialog.show();
		        	    
		        		Button add = (Button) dialog.findViewById(R.id.button1);
		        		add.setOnClickListener(new OnClickListener() 
		        		{
		        			@Override
								public void onClick(View v) 
		        				{  
		        				dialog.dismiss();
		        				final int item_id = cursor.getInt(cursor.getColumnIndex(SQLiteAdapter.KEY_ID));
		        				String num_db = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.KEY_CONTENT1));
		           	            String svid_db = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.KEY_CONTENT2));
		        				LayoutInflater inflater = getActivity().getLayoutInflater();
		    	        		View alertLayout = inflater.inflate(R.layout.edit_cont, null);
		    	        		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		    	        		alert.setView(alertLayout);
		    	        		reg_num = (EditText) alertLayout.findViewById(R.id.dmun);
		    	        		reg_num.setInputType(android.text.InputType.TYPE_CLASS_TEXT
		    		                    + android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
		    	        		regex(reg_num); 
		    	        		svid = (EditText) alertLayout.findViewById(R.id.dsvid);
		    	        		svid.setInputType(android.text.InputType.TYPE_CLASS_TEXT
		    		                    + android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
		    	        		reg_num.setText(num_db);
		    	        		svid.setText(svid_db);
		    	        		final AlertDialog dialog = alert.create();
		    	        		dialog.getWindow().setSoftInputMode(
		    	        			    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		    	        		dialog.requestWindowFeature((int) Window.FEATURE_NO_TITLE);
		    	        		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		    	        		dialog.show();
		    	        	    
		    	        		Button add = (Button) dialog.findViewById(R.id.button1);
		    	        		add.setOnClickListener(new OnClickListener() 
		    	        		{
		    	        			@Override
		    							public void onClick(View v) 
		    	        				{  
		    								String reg = reg_num.getText().toString();
		    		        				String sid = svid.getText().toString();
		    		        				mySQLiteAdapter.update(item_id,reg_num.getText().toString(),svid.getText().toString());
		    		        				updateList();
		    		        				dialog.dismiss();
		    							}
		    					});
		    	        		Button cancel = (Button) dialog.findViewById(R.id.button2);
		    		        	cancel.setOnClickListener(new OnClickListener() 
		    		        		{
		    						@Override
		    							public void onClick(View v) {dialog.dismiss();}
		    						});
					        	TextView txtv1 = (TextView) dialog.findViewById(R.id.textView1);
					        	txtv1.setOnClickListener(new OnClickListener() 
					        		{
									@Override
										public void onClick(View v) 
										{
											Intent i = new Intent(getActivity(), WebPage.class);  
								        	i.putExtra("head", "Информация");
								        	i.putExtra("num", "shtraf");
								        	startActivity(i);
										}
									});
					        	TextView txtv2 = (TextView) dialog.findViewById(R.id.textView2);
					        	txtv2.setOnClickListener(new OnClickListener() 
					        		{
									@Override
										public void onClick(View v) 
										{
											Intent i = new Intent(getActivity(), WebPage.class);  
								        	i.putExtra("head", "Информация");
								        	i.putExtra("num", "shtraf");
								        	startActivity(i);
										}
									});
								}
						});
		        		Button cancel = (Button) dialog.findViewById(R.id.button2);
			        	cancel.setOnClickListener(new OnClickListener() 
			        		{
							@Override
								public void onClick(View v) 
								{
									mySQLiteAdapter.delete_byID(item_id);
	           	                	updateList();
	           	                	dialog.dismiss();
								}
							});
		                return true;
		            }
		        });
	    */}
	    @Override
	    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	      //  inflater.inflate(R.menu.menu_sample, menu);
	        super.onCreateOptionsMenu(menu,inflater);
	    }

	/*    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.edit_item:
	            	LayoutInflater inflater = getActivity().getLayoutInflater();
	        		View alertLayout = inflater.inflate(R.layout.add_cont, null);
	        		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
	        		alert.setView(alertLayout);
	        		reg_num = (EditText) alertLayout.findViewById(R.id.dmun);
	        		reg_num.setInputType(android.text.InputType.TYPE_CLASS_TEXT
		                    + android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
	        		regex(reg_num); 
	        		
	        		svid = (EditText) alertLayout.findViewById(R.id.dsvid);
	        		svid.setInputType(android.text.InputType.TYPE_CLASS_TEXT
		                    + android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
	        		dialog = alert.create();
	        		dialog.getWindow().setSoftInputMode(
	        			    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	        		dialog.requestWindowFeature((int) Window.FEATURE_NO_TITLE);
	        		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	        		dialog.show();
	        	    
	        		Button add = (Button) dialog.findViewById(R.id.button1);
	        		add.setOnClickListener(new OnClickListener() 
	        		{
	        			@Override
							public void onClick(View v) 
	        				{  
								String reg = reg_num.getText().toString();
		        				String sid = svid.getText().toString();
		        				mySQLiteAdapter.insert(reg, sid,"Still no response from the server","-");
		        				updateList();
		        				dialog.dismiss();
							}
					});
	        		Button cancel = (Button) dialog.findViewById(R.id.button2);
		        	cancel.setOnClickListener(new OnClickListener() 
		        		{
						@Override
							public void onClick(View v) {dialog.dismiss();}
						});
		        	TextView txtv1 = (TextView) dialog.findViewById(R.id.textView1);
		        	txtv1.setOnClickListener(new OnClickListener() 
		        		{
						@Override
							public void onClick(View v) 
							{
								Intent i = new Intent(getActivity(), WebPage.class);  
					        	i.putExtra("head", "Информация");
					        	i.putExtra("num", "shtraf");
					        	startActivity(i);
							}
						});
		        	TextView txtv2 = (TextView) dialog.findViewById(R.id.textView2);
		        	txtv2.setOnClickListener(new OnClickListener() 
		        		{
						@Override
							public void onClick(View v) 
							{
								Intent i = new Intent(getActivity(), WebPage.class);  
					        	i.putExtra("head", "Информация");
					        	i.putExtra("num", "shtraf");
					        	startActivity(i);
							}
						});
		        	
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }*/
		private void updateList()
	    {
	    	  cursor.requery();
	    }
		public void setmsgwindow(Context ctx, String str)
		{
        	LayoutInflater inflater = getActivity().getLayoutInflater();
    		View alertLayout = inflater.inflate(R.layout.warning, null);
    		AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
    		alert.setView(alertLayout);
    		TextView textv = (TextView) alertLayout.findViewById(R.id.textctx);
    		textv.setText(str);
    		final AlertDialog dialog = alert.create();
    		dialog.requestWindowFeature((int) Window.FEATURE_NO_TITLE);
    		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    		dialog.show();
    		Button add = (Button) dialog.findViewById(R.id.button1);
    		add.setOnClickListener(new OnClickListener() 
    		{
    			@Override
					public void onClick(View v) 
    				{  
        				dialog.dismiss();
        		        Intent i = new Intent(getActivity(), WebPage.class);  
        	        	i.putExtra("head", "Проверка штрафов");
        	        	i.putExtra("num", "");
        	        	startActivity(i);
    				
					}
			});
		}
		boolean ret;
		/*public boolean getcaptcha(final View alertLayout)
		{
			ret=false; 
			myCookieStore.clear();
			client.setTimeout(30000);
			client.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:25.0) Gecko/20100101 Firefox/25.0");
			client.get("http://www.gibdd.ru/check/fines/", new AsyncHttpResponseHandler() {
				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					Log.d("Server","Error");
					setmsgwindow(getActivity(),"Сервер ГИБДД не доступен. Возможно на сервере ведутся профилактические работы. Просим повторить попытку позже.");
				}

				
				@Override
				public void onSuccess(int arg0, Header[] arg1,byte[] arg2) 
				{	try
					{
					
					token = new String(arg2, "UTF-8");

					
					}	
					catch(Exception Ex){}
					if(token.contains("временно приостановлена"))
					{
						dialog.dismiss();
						Log.d("Server","Error DB");
						setmsgwindow(getActivity(),"Сервер ГИБДД не доступен. Возможно на сервере ведутся профилактические работы. Просим повторить попытку позже.");
						
					}
					else
					{
						int startIndex = token.indexOf("ent.URL))?'");
						int endIndex = token.indexOf("':'sucke");
						token=token.substring(startIndex+11,endIndex);
					
					 	client.setCookieStore(myCookieStore);
					 	client.setTimeout(30000);
					 	client.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:25.0) Gecko/20100101 Firefox/25.0");
	    				client.get("http://www.gibdd.ru/bitrix/templates/.default/components/gai/check/fines_1.6/ajax/captchaReload.php", new AsyncHttpResponseHandler() {
	
	
							@Override
							public void onFailure(int arg0, Header[] arg1,
									byte[] arg2, Throwable arg3) {
								// TODO Auto-generated method stub
							}
	
							@Override
							public void onSuccess(int arg0, Header[] arg1,
									byte[] arg2) {
								try{
								String str = new String(arg2, "UTF-8");
								Log.d("Response--->",str);
								JSONObject reader = new JSONObject(str);
								hash=reader.getString("code");
								Log.d("Response--->JSON",reader.getString("code"));
								client.setCookieStore(myCookieStore);
								client.setTimeout(30000);
		        				client.get("http://www.gibdd.ru/bitrix/tools/captcha.php?captcha_sid="+reader.getString("code"), new AsyncHttpResponseHandler() {
	
	
									@Override
									public void onFailure(int arg0, Header[] arg1,
											byte[] arg2, Throwable arg3) {
										// TODO Auto-generated method stub
										
									}
	
									@Override
									public void onSuccess(int arg0, Header[] arg1,
											byte[] arg2) {
										try
										{
											rotation.cancel();
											img = (ImageView) alertLayout.findViewById(R.id.imageView1);
											Bitmap bitmap = BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
											img.setImageBitmap(bitmap);
											img.refreshDrawableState();
											ret=true;
										}
										catch(Exception Ex){}
										
									}
		        				});
								}
								catch(Exception Ex){}
								
							}
	    				});
					}
				}
			});
			if(ret)
			return true;
			else return false;
		}*/
		private void regex(EditText tx)
		{
			final String regex = "[А-Я]{1}[0-9]{3}[А-Я]{2}[0-9]{2,3}";
            
			tx.setFilters(
    		    new InputFilter[] {
    		        new PartialRegexInputFilter(regex)
    		    }
    		);
    		        
			tx.addTextChangedListener(
    		    new TextWatcher(){

    		            @Override
    		            public void afterTextChanged(Editable s) {
    		                String value  = s.toString();
    		                if(value.matches(regex))
    		                	reg_num.setTextColor(Color.WHITE);
    		                else
    		                	reg_num.setTextColor(Color.RED);
    		            }

    		            @Override
    		            public void beforeTextChanged(CharSequence s, int start,
    		                int count, int after) {}

    		            @Override
    		            public void onTextChanged(CharSequence s, int start,
    		               int before, int count) {}
    		           
    		         }
    		);
		}
		public class PartialRegexInputFilter implements InputFilter {
			 
		    private Pattern mPattern;
		 
		    public PartialRegexInputFilter(String pattern){
		      mPattern = Pattern.compile(pattern);
		    } 

		    @Override
		    public CharSequence filter(CharSequence source,
		            int sourceStart, int sourceEnd,
		            Spanned destination, int destinationStart,
		            int destinationEnd) 
		    {  
		        String textToCheck = destination.subSequence(0, destinationStart).
		            toString() + source.subSequence(sourceStart, sourceEnd) +
		            destination.subSequence(
		            destinationEnd, destination.length()).toString();
		  
		        Matcher matcher = mPattern.matcher(textToCheck);
		  
		        // Entered text does not match the pattern
		        if(!matcher.matches()){
		   
		            // It does not match partially too
		             if(!matcher.hitEnd()){
		                 return "";
		             }
		   
		        }
		  
		        return null;
		    }

		}


}
