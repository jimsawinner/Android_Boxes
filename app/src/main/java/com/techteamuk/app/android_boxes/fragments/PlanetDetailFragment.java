package com.techteamuk.app.android_boxes.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.constant.Constant;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetDetailFragment extends Fragment {
    private final String TAG = getClass().toString();

    IntentFilter filter = new IntentFilter(Constant.TIMER_ACTION);
    BroadcastReceiver broadcastReceiver;

    private Chronometer chronometer;

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.broadcastReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.broadcastReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.planet_frag, container, false);

        // Get a reference to chronometer_1
        chronometer = (Chronometer) result.findViewById(R.id.chronometer_1);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Debug log to console
                Log.d(TAG,"Broadcast message rec'd in "+TAG);

                if(intent.hasExtra(Constant.TIMER_PLAYER_1)) {
                    String action = intent.getStringExtra(Constant.TIMER_PLAYER_1);
                    switch(action) {
                        case "start":
                            chronometer.setTextColor(Color.RED);
                            chronometer.start();
                            break;
                        case "stop":
                            chronometer.setTextColor(Color.BLUE);
                            chronometer.stop();
                            break;
                    }
                }
            }
        };

        return(result);
    }

    public void setImage(int resourceID){
        ImageView imageView = (ImageView)getView().findViewById(R.id.planet_image);
        imageView.setImageResource(resourceID);
    }
}
