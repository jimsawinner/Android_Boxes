package com.techteamuk.app.android_boxes.utility;

import com.techteamuk.app.android_boxes.constant.Constant;

/**
 * Created by jim on 18/01/2017.
 */

public class GameData {
    private static boolean nextPlayer;
    private static int turnCount = Constant.TURNS;

    public static boolean getNextPlayer() { return nextPlayer; }

    public static void updateNextPlayer() { nextPlayer = !nextPlayer; }

    public static int getTurnCount() { return turnCount; }

    public static void adjustTurnCount() { turnCount--; }

    public static void resetGameData() {
        turnCount = Constant.TURNS;
    }
}
