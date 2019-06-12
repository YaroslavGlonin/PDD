package com.yaroslavsoftware.pdd;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridPage<GridViewImageTextActivity> extends SherlockActivity {  
GridView androidGridView;

    String[] gridViewString = {
    		  "Билет 1",
			  "Билет 2",
			  "Билет 3",
			  "Билет 4",
			  "Билет 5",
			  "Билет 6",
			  "Билет 7",
			  "Билет 8",
			  "Билет 9",
			  "Билет 10",
			  "Билет 11",
			  "Билет 12",
			  "Билет 13",
			  "Билет 14",
			  "Билет 15",
			  "Билет 16",
			  "Билет 17",
			  "Билет 18",
			  "Билет 19",
			  "Билет 20",
			  "Билет 21",
			  "Билет 22",
			  "Билет 23",
			  "Билет 24",
			  "Билет 25",
			  "Билет 26",
			  "Билет 27",
			  "Билет 28",
			  "Билет 29",
			  "Билет 30",
			  "Билет 31",
			  "Билет 32",
			  "Билет 33",
			  "Билет 34",
			  "Билет 35",
			  "Билет 36",
			  "Билет 37",
			  "Билет 38",
			  "Билет 39",
			  "Билет 40"
    } ;
    int[] gridViewImageId = {
            R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble
            ,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble
            ,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble
            ,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble,R.drawable.ruble
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(GridPage.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(GridPage.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });

    }
}