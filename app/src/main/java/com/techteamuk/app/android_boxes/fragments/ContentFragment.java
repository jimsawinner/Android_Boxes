package com.techteamuk.app.android_boxes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techteamuk.app.android_boxes.R;

/**
 * Created by jim on 17/01/2017.
 */

public class ContentFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.mainfrag, container, false);

        result.findViewById(R.id.showOther).setOnClickListener(this);
        return (result);
    }

    @Override
    public void onClick(View view) {
        ((StaticFragmentsDemoActivity)getActivity()).showOther(view);
    }
}
