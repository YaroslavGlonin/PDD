package com.yaroslavsoftware.pdd;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;


//import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
//import android.widget.SearchView;









import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;
import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnCloseListener;



@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Fragment6 extends SherlockListFragment 
{
	
	ListView listView;
	ArrayList<WorldPopulation> arraylist = new ArrayList<WorldPopulation>();
	ListViewAdapter adapter;
	String data[] = new String[] { "Республика Адыгея",
			"Республика Башкортостан (1993-2006 гг)",
			"Республика Бурятия",
			"Республика Алтай",
			"Республика Дагестан",
			"Республика Ингушетия",
			"Кабардино-Балкарская Республика",
			"Республика Калмыкия",
			"Карачаево-Черкесская Республика",
			"Республика Карелия",
			"Республика Коми",
			"Республика Марий Эл",
			"Республика Мордовия,",
			"Республика Саха (Якутия)",
			"Республика Северная Осетия - Алания",
			"Республика Татарстан (1993-2006 гг)",
			"Республика Тыва",
			"Удмуртская Республика",
			"Республика Хакасия",
			"Чеченская республика (1993-2000 гг)",
			"Чувашская Республика",
			"Алтайский край",
			"Краснодарский край (1993-2005 гг)",
			"Красноярский край (1993-2009 гг)",
			"Приморский край (1993-2005 гг)",
			"Ставропольский край",
			"Хабаровский край",
			"Амурская область",
			"Архангельская область",
			"Астраханская область",
			"Белгородская область",
			"Брянская область",
			"Владимирская область",
			"Волгоградская область (1993-2012 гг)",
			"Вологодская область",
			"Воронежская область",
			"Ивановская область",
			"Иркутская область",
			"Калининградская область",
			"Калужская область",
			"Камчатский край",
			"Кемеровская область (1993-2011 гг)",
			"Кировская область",
			"Костромская область",
			"Курганская область",
			"Курская область",
			"Ленинградская область",
			"Липецкая область",
			"Магаданская область",
			"Московская область (1993-2001 гг)",
			"Мурманская область",
			"Нижегородская область (1993-2009 гг)",
			"Новгородская область",
			"Новосибирская область (1993-2010 гг)",
			"Омская область",
			"Оренбургская область",
			"Орловская область",
			"Пензенская область",
			"Пермский край (1993-2010 гг)",
			"Псковская область",
			"Ростовская область (1993-2007 гг)",
			"Рязанская область",
			"Самарская область (1993-2007 гг)",
			"Саратовская область",
			"Сахалинская область",
			"Свердловская область (1993-2006 гг)",
			"Смоленская область",
			"Тамбовская область",
			"Тверская область",
			"Томская область",
			"Тульская область",
			"Тюменская область",
			"Ульяновская область",
			"Челябинская область (1993-2007 гг)",
			"Забайкальский край",
			"Ярославская область",
			"Москва (1993-1998 гг)",
			"Санкт-Петербург (1993-2004 гг)",
			"Еврейская автономная область",
			"Забайкальский край",
			"Пермский край",
			"Республика Крым (с 2014 г)",
			"Ненецкий автономный округ",
			"Красноярский край",
			"Иркутская область",
			"Ханты-Мансийский АО (1993-2012 гг)",
			"Чукотский автономный округ",
			"Красноярский край",
			"Ямало-Ненецкий автономный округ",
			"Московская область (2001-2006 гг)",
			"Калининградская область",
			"Севастополь (с 2014 г)",
			"Краснодарский край (2005-2011 гг)",
			"Байконур",
			"Чеченская республика (после 2000 г)",
			"Свердловская область (2006-2013 гг)",
			"Москва (2002-2005 гг)",
			"Санкт-Петербург (2004-2010 гг)",
			"Москва (1998-2002 гг)",
			"Республика Адыгея",
			"Республика Башкортостан (после 2006 г)",
			"Республика Бурятия",
			"Карачаево-Черкесская Республика",
			"Республика Мордовия",
			"Республика Татарстан (после 2006 г)",
			"Удмуртская Республика",
			"Чувашская Республика",
			"Краснодарский край (после 2011 г)",
			"Красноярский край (после 2009 г)",
			"Приморский край (после 2005 г)",
			"Волгоградская область (после 2012 г)",
			"Воронежская область",
			"Иркутская область",
			"Кемеровская область (после 2011 г)",
			"Московская область (2006-2009 гг)",
			"Нижегородская область (после 2009 г)",
			"Новосибирская область (после 2010 г)",
			"Пермский край (после 2010 г)",
			"Ростовская область (после 2007 г)",
			"Самарская область (после 2007 г)",
			"Саратовская область",
			"Ульяновская область",
			"Челябинская область (после 2007 гг)",
			"Москва (2005-2007 гг)",
			"Санкт-Петербург (после 2010 г)",
			"Ханты-Мансийский АО (после 2012 г)",
			"Московская область (2009-2013 гг)",
			"Свердловская область (после 2013 г)",
			"Москва (2010-2013 гг)",
			"Москва (2007-2010 гг)",
			"Московская область (после 2013 г)",
			"Москва (после 2013 г)",
			"Московская область",
			"Москва"};


public static final Integer[] images = {   R.drawable.r1
	, R.drawable.r2
	, R.drawable.r3
	, R.drawable.r4
	, R.drawable.r5
	, R.drawable.r6
	, R.drawable.r7
	, R.drawable.r8
	, R.drawable.r9
	, R.drawable.r10
	, R.drawable.r11
	, R.drawable.r12
	, R.drawable.r13
	, R.drawable.r14
	, R.drawable.r15
	, R.drawable.r16
	, R.drawable.r17
	, R.drawable.r18
	, R.drawable.r19
	, R.drawable.r20
	, R.drawable.r21
	, R.drawable.r22
	, R.drawable.r23
	, R.drawable.r24
	, R.drawable.r25
	, R.drawable.r26
	, R.drawable.r27
	, R.drawable.r28
	, R.drawable.r29
	, R.drawable.r30
	, R.drawable.r31
	, R.drawable.r32
	, R.drawable.r33
	, R.drawable.r34
	, R.drawable.r35
	, R.drawable.r36
	, R.drawable.r37
	, R.drawable.r38
	, R.drawable.r39
	, R.drawable.r40
	, R.drawable.r41
	, R.drawable.r42
	, R.drawable.r43
	, R.drawable.r44
	, R.drawable.r45
	, R.drawable.r46
	, R.drawable.r47
	, R.drawable.r48
	, R.drawable.r49
	, R.drawable.r50
	, R.drawable.r51
	, R.drawable.r52
	, R.drawable.r53
	, R.drawable.r54
	, R.drawable.r55
	, R.drawable.r56
	, R.drawable.r57
	, R.drawable.r58
	, R.drawable.r59
	, R.drawable.r60
	, R.drawable.r61
	, R.drawable.r62
	, R.drawable.r63
	, R.drawable.r64
	, R.drawable.r65
	, R.drawable.r66
	, R.drawable.r67
	, R.drawable.r68
	, R.drawable.r69
	, R.drawable.r70
	, R.drawable.r71
	, R.drawable.r72
	, R.drawable.r73
	, R.drawable.r74
	, R.drawable.r75
	, R.drawable.r76
	, R.drawable.r77
	, R.drawable.r78
	, R.drawable.r79
	, R.drawable.r80
	, R.drawable.r81
	, R.drawable.r82
	, R.drawable.r83
	, R.drawable.r84
	, R.drawable.r85
	, R.drawable.r86
	, R.drawable.r87
	, R.drawable.r88
	, R.drawable.r89
	, R.drawable.r90
	, R.drawable.r91
	, R.drawable.r92
	, R.drawable.r93
	, R.drawable.r94
	, R.drawable.r95
	, R.drawable.r96
	, R.drawable.r97
	, R.drawable.r98
	, R.drawable.r99
	, R.drawable.r101
	, R.drawable.r102
	, R.drawable.r103
	, R.drawable.r109
	, R.drawable.r113
	, R.drawable.r116
	, R.drawable.r118
	, R.drawable.r121
	, R.drawable.r123
	, R.drawable.r124
	, R.drawable.r125
	, R.drawable.r134
	, R.drawable.r136
	, R.drawable.r138
	, R.drawable.r142
	, R.drawable.r150
	, R.drawable.r152
	, R.drawable.r154
	, R.drawable.r159
	, R.drawable.r161
	, R.drawable.r163
	, R.drawable.r164
	, R.drawable.r173
	, R.drawable.r174
	, R.drawable.r177
	, R.drawable.r178
	, R.drawable.r186
	, R.drawable.r190
	, R.drawable.r196
	, R.drawable.r197
	, R.drawable.r199
	, R.drawable.r750
	, R.drawable.r777
	, R.drawable.r790
	, R.drawable.r797};

	 @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);	    
	  }
	 public void onCreate(Bundle savedInstanceState) {
	        setHasOptionsMenu(true);
	        super.onCreate(savedInstanceState);
	        listView = (ListView) getActivity().findViewById(R.id.listview_drawer);
	        arraylist.clear();
			for (int i = 0; i < data.length; i++) 
			{
				WorldPopulation wp = new WorldPopulation(images[i], data[i]);
				arraylist.add(wp);
			}
			adapter = new ListViewAdapter(getActivity(), arraylist);
			 setListAdapter(adapter);
	}
	@Override
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		 super.onCreateOptionsMenu(menu, inflater);
		 inflater.inflate(R.menu.my_swipe_tabbed, menu);
	     MenuItem searchViewItem = menu.findItem(R.id.action_search);
	     final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	     searchView.setIconifiedByDefault(false);
	     searchView.setQuery("", false);
	     searchViewItem.collapseActionView();
	      searchViewItem.setOnActionExpandListener(new OnActionExpandListener() {
	            @Override
	            public boolean onMenuItemActionCollapse(MenuItem item) {
	            	searchView.setQuery("", false);
	                return true;
	            }
	            @Override
	            public boolean onMenuItemActionExpand(MenuItem item) {
	               searchView.setQuery("", false);
	                return true;
	            }
	        });

	     final SearchView.OnQueryTextListener queryTextListener = new    SearchView.OnQueryTextListener() {
	            @Override
	            public boolean onQueryTextChange(String newText) {
	                adapter.filter(newText);
	                return true;
	            }
	            @Override
	            public boolean onQueryTextSubmit(String query) {
	                return true;
	            }
	        };
	        searchView.setOnQueryTextListener(queryTextListener);
	 }
}

