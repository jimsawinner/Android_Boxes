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
import android.widget.ImageView;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.constant.Constant;
import com.techteamuk.app.android_boxes.services.GameTimerBoundService;
import com.techteamuk.app.android_boxes.services.IGameTimer;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetDetailFragment extends Fragment implements View.OnClickListener, ServiceConnection {
    private final String TAG = getClass().toString();

    IntentFilter filter = new IntentFilter(Constant.TIMER_ACTION);
    BroadcastReceiver broadcastReceiver;
    private IGameTimer binding=null;
    private Chronometer chronometer;
    private Button goBtn=null;
    private Button colCycleBtn=null;
    private Application appContext=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        appContext=(Application)getActivity().getApplicationContext();
        appContext.bindService(new Intent(getActivity(), GameTimerBoundService.class), this, Context.BIND_AUTO_CREATE);
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
        View result=inflater.inflate(R.layout.planet_frag, container, false);

        goBtn = (Button) result.findViewById(R.id.go_btn);
        goBtn.setOnClickListener(this);
        goBtn.setEnabled(binding !=null);

        colCycleBtn = (Button) result.findViewById(R.id.colourCycle_btn);
        colCycleBtn.setEnabled(false);
        colCycleBtn.setOnClickListener(this);

        // Get a reference to chronometer_1
        chronometer = (Chronometer) result.findViewById(R.id.chronometer_1);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Debug log to console
                Log.d(TAG,"Broadcast message rec'd in "+TAG);

                if(intent.getAction().equals(Constant.TIMER_ACTION)) {
                    if(intent.hasExtra(Constant.GAME_STOP)) {
                        Log.d(TAG,Constant.GAME_STOP);
                        colCycleBtn.setEnabled(false);
                        chronometer.stop();
                        goBtn.setEnabled(true);
                        return;
                    }

                    if(intent.hasExtra(Constant.TIMER_PLAYER_1)) {
                        String action = intent.getStringExtra(Constant.TIMER_PLAYER_1);
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

        return(result);
    }

    public void setImage(int resourceID){
        ImageView imageView = (ImageView)getView().findViewById(R.id.planet_image);
        imageView.setImageResource(resourceID);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        binding=(IGameTimer) iBinder;
        goBtn.setEnabled(true);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        disconnect();
    }

    private void disconnect() {
        binding.timerStop();
        goBtn.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        binding.timerStart(appContext);
        goBtn.setEnabled(false);
        colCycleBtn.setEnabled(true);
    }
}
