package com.techteamuk.app.android_boxes.fragments;

import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.constant.Constant;
import com.techteamuk.app.android_boxes.services.GameTimer;
import com.techteamuk.app.android_boxes.utility.GameData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetListFragment extends ListFragment {
    List<String> updateList;

    IntentFilter filter = new IntentFilter(Constant.LIST_MSG);
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
//    ArrayList<> updateList;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        updateList = new ArrayList<>();
        String[] emptyList = {};

//        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Planet.names));
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, emptyList));

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
                String planet = intent.getStringExtra(Constant.PLANET_DETAIL);
                updateList(planet);
            }
        };
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        // Popup 'toast' at bottom of screen
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();

        // Update facts fragment
        ((PlanetFactsFragment)getFragmentManager().findFragmentById(R.id.fragment_right)).populatePlanetFacts(position);

        // Update image fragment
        ((PlanetDetailFragment)getFragmentManager().findFragmentById(R.id.fragment_centre)).setImage(Planet.planetResource[position]);

        // Broadcast message to LOBBY_MSG register listeners
//        broadcastIntent();

//        GameData.resetGameData();
//        callServiceTimerExample();

        Log.d("TAG","Broadcast message sent");
    }

    private void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction(Constant.LOBBY_MSG);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getActivity().sendBroadcast(intent);
    }

    public void updateList(String s){
        updateList.add(s);
        String[] newList = updateList.toArray(new String[updateList.size()]);

        ListAdapter mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, newList);
        setListAdapter(mAdapter);
    }

    public void callServiceTimerExample() {
        Intent intent = new Intent(getActivity(), GameTimer.class);
        intent.setAction(Constant.GAME_START);
        getActivity().startService(intent);
    }
}
