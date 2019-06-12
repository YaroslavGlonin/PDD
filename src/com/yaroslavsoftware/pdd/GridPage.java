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
    		  "����� 1",
			  "����� 2",
			  "����� 3",
			  "����� 4",
			  "����� 5",
			  "����� 6",
			  "����� 7",
			  "����� 8",
			  "����� 9",
			  "����� 10",
			  "����� 11",
			  "����� 12",
			  "����� 13",
			  "����� 14",
			  "����� 15",
			  "����� 16",
			  "����� 17",
			  "����� 18",
			  "����� 19",
			  "����� 20",
			  "����� 21",
			  "����� 22",
			  "����� 23",
			  "����� 24",
			  "����� 25",
			  "����� 26",
			  "����� 27",
			  "����� 28",
			  "����� 29",
			  "����� 30",
			  "����� 31",
			  "����� 32",
			  "����� 33",
			  "����� 34",
			  "����� 35",
			  "����� 36",
			  "����� 37",
			  "����� 38",
			  "����� 39",
			  "����� 40"
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