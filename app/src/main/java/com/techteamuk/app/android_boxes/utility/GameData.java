package com.techteamuk.app.android_boxes.utility;

import com.techteamuk.app.android_boxes.constant.Constant;

/**
 * Created by jim on 18/01/2017.
 */

public class GameData {
    private static boolean nextPlayer;
    private static int turnCount = Constant.TURNS;
    private static int currentColours = 1;

    public static boolean getNextPlayer() {
        return nextPlayer;
    }

    public static void updateNextPlayer() {
        nextPlayer = !nextPlayer;
    }

    public static int getTurnCount() {
        return turnCount;
    }

    public static void adjustTurnCount() {
        turnCount--;
    }

    public static void resetGameData() {
        turnCount = Constant.TURNS;
    }

    public static void changeColours() {
        currentColours = (++currentColours) % Constant.MAX_COLOURS;
    }

    public static int colourActive() {
        return currentColours;
    }

    public static int colourPassive() {
        return (currentColours+1) % Constant.MAX_COLOURS;
    }
}
