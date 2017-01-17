package com.techteamuk.app.android_boxes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techteamuk.app.android_boxes.R;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetFactsFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.planet_fact_frag, container, false);
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
}
