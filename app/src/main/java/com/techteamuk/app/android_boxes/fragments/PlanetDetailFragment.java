package com.techteamuk.app.android_boxes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.techteamuk.app.android_boxes.R;

/**
 * Created by jim on 16/01/2017.
 */

public class PlanetDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.planet_frag, container, false);
        return(result);
    }

    public void setImage(int resourceID){
        ImageView imageView = (ImageView)getView().findViewById(R.id.planet_image);
        imageView.setImageResource(resourceID);
    }
}
