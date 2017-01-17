package com.techteamuk.app.android_boxes.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.techteamuk.app.android_boxes.R;

/**
 * Created by jim on 17/01/2017.
 */

public class OtherActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "extra_message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
    }
}
