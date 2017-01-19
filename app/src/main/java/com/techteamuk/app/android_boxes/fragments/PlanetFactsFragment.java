package com.techteamuk.app.android_boxes.fragments;

import android.app.Application;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.constant.Constant;
import com.techteamuk.app.android_boxes.services.GameTimerBoundService;
import com.techteamuk.app.android_boxes.services.IGameTimer;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetFactsFragment extends Fragment implements ServiceConnection {
    private final String TAG = getClass().toString();

    IntentFilter filter = new IntentFilter(Constant.TIMER_ACTION);
    BroadcastReceiver broadcastReceiver;
    private IGameTimer binding=null;
    private Chronometer chronometer;
    private Application appContext=null;

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Log.d(TAG,"Binding service in "+TAG);

        appContext=(Application)getActivity().getApplicationContext();
        appContext.bindService(new Intent(getActivity(), GameTimerBoundService.class), this, Context.BIND_AUTO_CREATE);
        Log.d(TAG,"Service bound in "+TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
//        getActivity().registerReceiver(this.broadcastReceiver, filter);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
//        getActivity().unregisterReceiver(this.broadcastReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.planet_fact_frag, container, false);

        // Get a reference to chronometer_2
        chronometer = (Chronometer) view.findViewById(R.id.chronometer_2);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Debug log to console
                Log.d(TAG,"Broadcast message rec'd in "+TAG);

                if(intent.getAction().equals(Constant.TIMER_ACTION)) {
                    if(intent.hasExtra(Constant.GAME_STOP)) {
                        chronometer.stop();
                        return;
                    }

                    if(intent.hasExtra(Constant.TIMER_PLAYER_2)) {
                        String action = intent.getStringExtra(Constant.TIMER_PLAYER_2);
                        switch(action) {
                            case "start":
                                chronometer.setTextColor(Color.RED);
                                chronometer.setBase(SystemClock.elapsedRealtime());
                                chronometer.start();
                                break;
                            case "stop":
                                chronometer.setTextColor(Color.BLUE);
                                chronometer.stop();
                                break;
                        }
                    }
                }
            }
        };

        ((Button)view.findViewById(R.id.add_list_item_btn)).setOnClickListener(
                new View.OnClickListener() {
                    int planetNum = 0;
                    @Override
                    public void onClick(View view){
                        if(planetNum < Planet.names.length) {
                            Log.d(TAG, "Button Clicked in "+TAG);
                            broadcastIntent(Planet.names[planetNum++]);
                        }else{
                            // Popup 'toast' at bottom of screen
                            Toast.makeText(getActivity(), "Not possible.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        return(view);
    }

    public void populatePlanetFacts(int planet){
        // static call to planet class to get planet
        Planet p = Planet.planetFacts[planet];
        ((TextView)view.findViewById(R.id.page_header)).setText("Planet facts for: "+p.getName());
        ((TextView)view.findViewById(R.id.circumference)).setText("Circumference =: "+p.getEqCircumference());
        ((TextView)view.findViewById(R.id.moons)).setText("Moons =: "+p.getMoons());
        ((TextView)view.findViewById(R.id.orbitperiod)).setText("Solar Year =: "+p.getOrbitPeriod());
        ((TextView)view.findViewById(R.id.maxtemp)).setText("Maximum Temperature =: "+p.getMaxTemp());
        ((TextView)view.findViewById(R.id.earthmonth)).setText("Earth Month =: "+p.calcEarthMonth());
    }

    private void broadcastIntent(String planet) {
        Intent intent = new Intent();
        intent.setAction(Constant.LIST_MSG);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra(Constant.PLANET_DETAIL,planet);
        getActivity().sendBroadcast(intent);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder iBinder) {
        Log.d(TAG,"Service connected in "+TAG);
        binding=(IGameTimer) iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG,"Service disconnected in "+TAG);
        binding.timerStop();
    }
}
