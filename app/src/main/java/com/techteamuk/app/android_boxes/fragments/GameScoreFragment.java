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

public class GameScoreFragment extends Fragment {
    private final String TAG = getClass().toString();

    IntentFilter filter = new IntentFilter(Constant.TIMER_ACTION);
    BroadcastReceiver broadcastReceiver;
    private IGameTimer binding=null;
    private Chronometer chronometer;

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);

        Log.d(TAG,"Game score fragment created "+TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.game_score_fragment, container, false);

        ((Button)view.findViewById(R.id.add_list_item_btn)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
//                         Popup 'toast' at bottom of screen
                        Toast.makeText(getActivity(), "Not possible.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return(view);
    }
}
