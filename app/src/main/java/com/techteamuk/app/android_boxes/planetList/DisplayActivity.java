package com.techteamuk.app.android_boxes.planetList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.techteamuk.app.android_boxes.R;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ImageView imageView = (ImageView) findViewById(R.id.planet_image);
        Intent intent = getIntent();
        int planetImageSource = intent.getIntExtra(PlanetActivity.PLANET_EXTRA,0);
        imageView.setImageResource(planetImageSource);
    }
}
