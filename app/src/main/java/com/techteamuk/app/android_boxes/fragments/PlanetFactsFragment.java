package com.techteamuk.app.android_boxes.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.constant.Constant;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetFactsFragment extends Fragment {
    private final String TAG = getClass().toString();

    View view;

    IntentFilter filter = new IntentFilter(Constant.LOBBY_MSG);
    BroadcastReceiver broadcastReceiver;

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
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG,"Broadcast message rec'd in "+TAG);
            }
        };

        view = inflater.inflate(R.layout.planet_fact_frag, container, false);
        ((Button)view.findViewById(R.id.add_list_item_btn)).setOnClickListener(
                new View.OnClickListener() {
                    int planetNum = 0;
                    @Override
                    public void onClick(View view){
                        Log.d(TAG,"Button Clicked...");
                        broadcastIntent("Planet_"+planetNum++);
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
        ((TextView)view.findViewById(R.id.moons)).setText("Moons =: "+p.getName());
        ((TextView)view.findViewById(R.id.orbitperiod)).setText("Solar Year =: "+p.getOrbitPeriod());
        ((TextView)view.findViewById(R.id.maxtemp)).setText("Maximum Temperature =: "+p.getMaxTemp());
        ((TextView)view.findViewById(R.id.orbitperiod)).setText("Earth Month =: "+p.calcEarthMonth());
    }

    private void broadcastIntent(String planet) {
        Intent intent = new Intent();
        intent.setAction(Constant.LIST_MSG);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra(Constant.PLANET_DETAIL,planet);
        getActivity().sendBroadcast(intent);
    }
}
