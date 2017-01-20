package com.techteamuk.app.android_boxes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.techteamuk.app.android_boxes.R;
import com.techteamuk.app.android_boxes.game.GameBoardFragmentsActivity;

/**
 * Created by jim on 17/01/2017.
 */

public class StaticFragmentsDemoActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_frag_main);
    }

    public void showGameBoard(View v) {
        Intent gameBoardFragments=new Intent(this, GameBoardFragmentsActivity.class);

        gameBoardFragments.putExtra("ready", true);
        startActivity(gameBoardFragments);
    }

    public void showOther(View v) {
        Intent other=new Intent(this, OtherActivity.class);

        other.putExtra(OtherActivity.EXTRA_MESSAGE,"bonjour le monde de ci362");
        startActivity(other);
    }
}
