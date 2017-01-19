package com.techteamuk.app.android_boxes.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.techteamuk.app.android_boxes.constant.Constant;
import com.techteamuk.app.android_boxes.utility.GameData;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jim on 18/01/2017.
 */

public class GameTimerBoundService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new GameTimerBinder();
    }

    private static class GameTimerBinder extends Binder implements IGameTimer {
        @Override
        public void timerStart(Context context) {
            GameData.resetGameData();
            new GameTimerThread(context).start();
        }

        @Override
        public void timerStop() {
            GameTimerThread.stopThread();
        }

        @Override
        public void cycleColour() {
            GameData.changeColours();
        }
    }

    private static class GameTimerThread extends Thread {
        Context context;
        static boolean running=true;
        public GameTimerThread(Context context) {
            this.context = context;

        }

        public static void stopThread() {
            running=false;
        }

        @Override
        public void run() {
            if(!running) {
                return;
            }

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
            }, 0, 6000);
        }

        private void sendStartStopMsg() {
            Intent intent = new Intent();
            intent.setAction(Constant.TIMER_ACTION);

            if(GameData.getNextPlayer()) {
                intent.putExtra(Constant.TIMER_PLAYER_1, "start");
                intent.putExtra(Constant.COLOUR_ACTIVE, GameData.colourActive());
                intent.putExtra(Constant.TIMER_PLAYER_2, "stop");
                intent.putExtra(Constant.COLOUR_PASSIVE, GameData.colourPassive());
            }else{
                intent.putExtra(Constant.TIMER_PLAYER_2, "start");
                intent.putExtra(Constant.COLOUR_ACTIVE, GameData.colourActive());
                intent.putExtra(Constant.TIMER_PLAYER_1, "stop");
                intent.putExtra(Constant.COLOUR_PASSIVE, GameData.colourPassive());
            }
            GameData.updateNextPlayer();
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }

        // Stop message for both players
        private void stopAllMsg(){
            Intent intent = new Intent();
            intent.setAction(Constant.TIMER_ACTION);
                intent.putExtra(Constant.TIMER_PLAYER_1, "stop");
                intent.putExtra(Constant.TIMER_PLAYER_2, "stop");
//            intent.putExtra(Constant.GAME_STOP, Constant.GAME_STOP);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
