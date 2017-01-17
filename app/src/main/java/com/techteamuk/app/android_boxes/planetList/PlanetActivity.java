package com.techteamuk.app.android_boxes.planetList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.techteamuk.app.android_boxes.R;

public class PlanetActivity extends AppCompatActivity {
    public final static String PLANET_EXTRA = "planet";
    // ListView ref
    ListView mListview;

    // Text elements to be displayed in the list
    String[] planets = new String[]{"Jupiter", "Mercury", "Venus", "Earth", "Saturn", "Neptune", "Pluto", "Mars"};

    // Resource IDs of Drawable images in res/drawable folder
    int[] planetResource = new int[] {R.drawable.jupiter, R.drawable.mercury, R.drawable.venus, R.drawable.earth, R.drawable.saturn, R.drawable.neptune, R.drawable.pluto, R.drawable.mars};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        mListview = (ListView) findViewById(R.id.planet_list);
        // Declare array adapter
        ArrayAdapter<String> planetAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,planets);
        mListview.setAdapter(planetAdapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // item clicked obtained from parameter i
                int itemPosition = i;
                // get string value of the item where the user clicked
                String itemValue = (String) mListview.getItemAtPosition(itemPosition);
                // need an intent to call a new activity
                Intent intent = new Intent(getApplicationContext(),DisplayActivity.class);
                // pack ID of image as extra
                intent.putExtra(PLANET_EXTRA,planetResource[itemPosition]);
                startActivity(intent);
            }
        });
    }
}
