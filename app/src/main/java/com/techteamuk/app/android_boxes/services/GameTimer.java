package com.techteamuk.app.android_boxes.services;

import android.app.IntentService;
import android.content.Intent;

import com.techteamuk.app.android_boxes.constant.Constant;
import com.techteamuk.app.android_boxes.utility.GameData;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jim on 18/01/2017.
 */

public class GameTimer extends IntentService {
    Timer gameTimer;

    public GameTimer() { super("GameTimer"); }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent.getAction().equals(Constant.GAME_START)) {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    sendStartStopMsg();
                    GameData.adjustTurnCount();
                    if(GameData.getTurnCount() <1){
                        stopAllMsg();
                        this.cancel();
                    }
                }
            }, 0, 5000);
        }
    }

    private void sendStartStopMsg() {
        Intent intent = new Intent();
        intent.setAction(Constant.TIMER_ACTION);

        if(GameData.getNextPlayer()) {
            intent.putExtra(Constant.TIMER_PLAYER_1, "start");
            intent.putExtra(Constant.TIMER_PLAYER_2, "stop");
        }else{
            intent.putExtra(Constant.TIMER_PLAYER_2, "start");
            intent.putExtra(Constant.TIMER_PLAYER_1, "stop");
        }
        GameData.updateNextPlayer();
        sendBroadcast(intent);
    }

    // Stop message for both players
    private void stopAllMsg(){
        Intent intent = new Intent();
        intent.setAction(Constant.TIMER_ACTION);
        intent.putExtra(Constant.TIMER_PLAYER_1, "stop");
        intent.putExtra(Constant.TIMER_PLAYER_2, "stop");
        sendBroadcast(intent);
    }
}
