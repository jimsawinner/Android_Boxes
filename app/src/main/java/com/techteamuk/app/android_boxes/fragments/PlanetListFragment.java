package com.techteamuk.app.android_boxes.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.constant.Constant;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetListFragment extends ListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Planet.names));
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
        broadcastIntent();
    }

    private void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction(Constant.LOBBY_MSG);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getActivity().sendBroadcast(intent);
    }
}
