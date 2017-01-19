package com.techteamuk.app.android_boxes.services;

import android.content.Context;

/**
 * Created by Jim on 18/01/2017.
 */

public interface IGameTimer {
    void timerStart(Context context);
    void timerStop();
    void cycleColour();
}
