package com.yaroslavsoftware.pdd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


import org.json.JSONArray;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Window;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.yaroslavsoftware.pdd.R;
import com.yaroslavsoftware.pdd.util.IabHelper;
import com.yaroslavsoftware.pdd.util.IabResult;
import com.yaroslavsoftware.pdd.util.Inventory;
import com.yaroslavsoftware.pdd.util.Purchase;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment8 extends SherlockFragment {

	PersistentCookieStore myCookieStore;
	EditText vintext,capt;
	String hash,token;
	ImageView img;
	AlertDialog dialog;
	Animation rotation;
	ProgressDialog pd;
	Button button;
	EditText edt;
	//AsyncHttpClient client = new AsyncHttpClient();
	  /* @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        final View view = inflater.inflate(R.layout.vin, container, false);
	      
	        	   //--
	        	             
	        return view;
	    } */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
    	setHasOptionsMenu(true);  
    	final View view = inflater.inflate(R.layout.vinex, container, false);
		Button add = (Button) view.findViewById(R.id.getinfovin);
		add.setText("Официальный сайт ГИБДД");
		add.setOnClickListener(new OnClickListener() 
		{
			@Override
				public void onClick(View v) 
				{  
    				//dialog.dismiss();
    		        Intent i = new Intent(getActivity(), WebPageUgon.class);  
    	        	i.putExtra("head", "Проверка штрафов");
    	        //	i.putExtra("num", "");
    	        	startActivity(i);
				
				}
		});
    	 return view;
    	//return super.onCreateView(inflater, container, savedInstanceState);
    }
	/*	public void setmsgwindow(Context ctx, String str)
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
					}
			});
		}
	   public void getcaptcha(final View alertLayout)
		{
			myCookieStore.clear();
			client.setTimeout(30000);
			client.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:25.0) Gecko/20100101 Firefox/25.0");
			client.get("http://www.gibdd.ru/check/auto/", new AsyncHttpResponseHandler() {
				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
					// TODO Auto-generated method stub
					Log.d("Server","Error");
					dialog.dismiss();
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
						Log.d("Server","Error DB");
						dialog.dismiss();
						setmsgwindow(getActivity(),"Сервер ГИБДД не доступен. Возможно на сервере ведутся профилактические работы. Просим повторить попытку позже.");
						
					}
					else
					{
						int startIndex = token.indexOf("'X-Csrf-Token': '");
						int endIndex = token.indexOf("error: function()");
						token=token.substring(startIndex+17,endIndex-13);
						Log.d("Response--->",token);
						client.setCookieStore(myCookieStore);
					 	client.setTimeout(30000);
					 	client.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:25.0) Gecko/20100101 Firefox/25.0");
	    				client.get("http://www.gibdd.ru/bitrix/templates/.default/components/gai/check/auto_1.1/ajax/captchaReload.php", new AsyncHttpResponseHandler() {
	
	
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
		        					//http://www.gibdd.ru/bitrix/tools/captcha.php?captcha_sid=009c73edea5a09983572f11f411ad3a2
	
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
		}
		 public boolean isOnline() {
			    ConnectivityManager cm =
			        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo netInfo = cm.getActiveNetworkInfo();
			    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			        return true;
			    }
			    return false;
			}
		 private String generateInt(int digits) {
			    StringBuilder str = new StringBuilder();
			    Random random = new Random();
			    for(int i = 0; i < digits; i++) {
			        str.append(random.nextInt(10));
			    }
			    return str.toString();
			}
			@Override
			public void onStart() {
				super.onStart();
				
				  myCookieStore = new PersistentCookieStore(getActivity());
				  client.setEnableRedirects(true,true,true);
				  vintext = (EditText) getActivity().findViewById(R.id.egetibfo);
		        button = (Button) getActivity().findViewById(R.id.getinfo);
		        edt = (EditText) getActivity().findViewById(R.id.egetibfo);
					edt.setEnabled(true);
					button.setText("Проверить");
		        	   button.setOnClickListener(new OnClickListener()
		        	   {
		        	             @Override
		        	             public void onClick(View v)
		        	             {
		        	     	        if(vintext.getText().length()>=17&&isOnline())
		        	    	        {
		        	            	//vintext = (EditText) view.findViewById(R.id.egetibfo);
		        	            	
		        	                LayoutInflater inflater = getActivity().getLayoutInflater();
		        	        		final View alertLayout = inflater.inflate(R.layout.captcha, null);
		        	        		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		        	        		alert.setView(alertLayout);
		        	        		capt = (EditText) alertLayout.findViewById(R.id.dsvid);
		        	        		capt.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
		        	        		dialog = alert.create();
		        	        		dialog.getWindow().setSoftInputMode(
		        	        			    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		        	        		dialog.requestWindowFeature((int) Window.FEATURE_NO_TITLE);
		        	        		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		        	        		dialog.show();
		        	        		getcaptcha(alertLayout);
		        	        		img = (ImageView) alertLayout.findViewById(R.id.imageView1);
		        	        		rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);
		        	        	    img.startAnimation(rotation);
		        	               // Log.d("Click", text.getText().toString());
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
		        	        				//Toast.makeText(getActivity(), num+"<-->"+reg, Toast.LENGTH_SHORT).show();//num_db.length();
		        	        				RequestParams params = new RequestParams();
		        	        				try
		        	        				{
		        	        			    params.put("vin", vintext.getText().toString());
		        	        			    params.put("captchaWord", capt.getText().toString());
		        	        			    params.put("captchaCode", hash);
		        	        			    params.wait();
		        	        				}
		        	        				catch(Exception Ex){}
		        	        				//_ga=GA1.2.1057113462.1424434414; _gat=1; PHPSESSID=bp8afkb5dt0l6evpp6s5400gr5; BITRIX_SM_SVC_CHECK_VIN=X9F5XXEED57C57197; BITRIX_SM_REGKOD=00
		        	        			   // BasicClientCookie newCookie = new BasicClientCookie("BITRIX_SM_METOD", "GEO");
		        	        			   // BasicClientCookie newCookie1 = new BasicClientCookie("siteType", "deleted");
		        	        			    BasicClientCookie newCookie = new BasicClientCookie("_ga", "GA1.2."+generateInt(10)+"."+generateInt(10));
		        	        			    BasicClientCookie newCookie1 = new BasicClientCookie("_gat", "1");
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
		        	        			    client.addHeader("Referer", "http://www.gibdd.ru/check/auto/");
		        	        			    client.setCookieStore(myCookieStore);
		        	        			    client.setTimeout(30000);
		        	        			    client.post("http://www.gibdd.ru/bitrix/templates/.default/components/gai/check/auto_1.1/ajax/client.php",params, new AsyncHttpResponseHandler() {
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
		        									String msg_rozisk = "Розыск:\n";
		        									String msg_zalog = "Залог:\n";
		        									//JSONObject request = reader.getJSONObject("request");
		        									Log.d("status",String.valueOf(reader.getInt("error")));
		        										if(Integer.parseInt(reader.getString("error"))==201)
		        												{
		        													setmsgwindow(getActivity(),"Введенная информация с картинки не верна, попробуйте еще раз...");
		        												}
		        										else if(Integer.parseInt(reader.getJSONObject("restricted").getString("error"))==1)
		        												{
		        													setmsgwindow(getActivity(),"Введенный Государственный регистрационный VIN не верен.");
		        												}
		        										else if(Integer.parseInt(reader.getString("error"))==0 && "null"!=reader.getString("restricted"))
		        												{
		        												JSONObject restricted  = reader.getJSONObject("restricted");
		        												JSONObject wanted  = reader.getJSONObject("wanted");
		        												if(Integer.parseInt(restricted.getString("count"))==0&&Integer.parseInt(wanted .getString("count"))==0)
		        												{
		        													setmsgwindow(getActivity(),"Информации о розыске транспортного средства и наложении ограничений на регистрационные действия, имеющего VIN (номер кузова или шасси) "+vintext.getText().toString()+", в федеральной информационной системе МВД России не найдено.");
		        													
		        												}
		        												else
		        												{ 
		        												//	TextView textctx = (TextView) getActivity().findViewById(R.id.textctx);
		        												//	textctx.setTextColor(Color.RED);
		        													String w_model = null,w_god_vyp = null,w_reg_inic = null,w_data_pu = null;
		        													if(Integer.parseInt(wanted .getString("count"))!=0)
		        													{
		        														JSONArray array = wanted.getJSONArray("records");
		        														for(int i = 0 ; i < array.length() ; i++){
		        															w_model = array.getJSONObject(i).getString("w_model");
		        															w_god_vyp = array.getJSONObject(i).getString("w_god_vyp");
		        															w_reg_inic = array.getJSONObject(i).getString("w_reg_inic");
		        															w_data_pu = array.getJSONObject(i).getString("w_data_pu");
		        														}
		        														msg_rozisk += "Модель: "+ w_model+"\n"+
		        														"Год выпуска: "+w_god_vyp+"\n"+
		        														"Регион: "+w_reg_inic+"\n"+
		        														"Дата постоянного учета: "+w_data_pu;
		        														
		        													}
		        													else
		        													{
		        														msg_rozisk+="Данное транспортное средство не числится в розыске.";
		        													}
		        													if(Integer.parseInt(restricted .getString("count"))!=0)
		        													{
		        														msg_zalog+="Имеются наложения ограничения на регистрационные действия с транспортным средством.";
		        													}
		        													else
		        													{
		        														msg_zalog+="Наложения ограничений на регистрационные действия с транспортным средством, не имеются.";
		        													}
		        													setmsgwindow(getActivity(),msg_rozisk+"\n"+msg_zalog);
		        													
		        												}
		        											
		        												}
		        										else
		        											{
		        												setmsgwindow(getActivity(),"Ошибка запроса к базе данных. Попробуйте зайти позже");
		        											}
		        									}
		        									catch(Exception Ex){Log.d("Some",Ex.toString());setmsgwindow(getActivity(),"Ошибка запроса к базе данных. Попробуйте зайти позже");}
		        									myCookieStore.clear();
		        									client.removeAllHeaders();
		        								}
		        	        			    	
		        	        			    });
		        	        				}
		        	        		
		        	             });
		        	             }
		        	     	        else if(!isOnline())
		        	     	        {
		        	     	        	setmsgwindow(getActivity(),"Вы не можете проверить (VIN), номер кузова(шасси), т.к. у вас нет доступа к интернету");
		        	     	        }
			        	             else 
			        	             {
			        	            	 setmsgwindow(getActivity(),"Идентификационный номер (VIN), номер кузова(шасси) должен состоять минимум из 17 символов");
			        	             }
		        	            }

		        	   }); 
				}
*/
		        	
			
			
}
