package com.techteamuk.app.android_boxes.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.constant.Constant;
import com.techteamuk.app.android_boxes.services.IGameTimer;

/**
 * Created by jim on 20/01/2017.
 */

public class GameBoardFragment extends Fragment {
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
        view = inflater.inflate(R.layout.game_board_fragment, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Remember our initial down event location.
                        float startX = event.getRawX();
                        float startY = event.getRawY();

                        Log.d(TAG, "StartX: "+startX + " StartY: "+startY);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float x = event.getRawX();
                        float y = event.getRawY();
                        break;
                }
                return true; //done with this event so consume it
            }
        });

        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frame_board_layout);
//        frameLayout.addView(new Rectangle(getActivity()));

        view.setBackgroundColor(Color.RED);
        return(view);
    }
}
