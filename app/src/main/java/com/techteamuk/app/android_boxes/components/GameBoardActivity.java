package com.techteamuk.app.android_boxes.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.techteamuk.app.android_boxes.constant.Constant;

public class GameBoardActivity extends AppCompatActivity {
    private final String TAG = getClass().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
//        setContentView(R.layout.activity_game_board);
    }

    public class MyView extends View {
        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            Log.d(TAG,"X: "+ x + " Y: "+ y);
            int radius;
            radius = 250;

            Paint paint = new Paint();

            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);

            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            // Use Color.parseColor enables defining of HTML colors
            paint.setColor(Color.parseColor("#000000"));

            // draw the dots on the game board

            // board size of 5 x 5 squares
            int boardSize = Constant.LEVEL_HARD;

            // yPosition loop
            for(int i=0;i<boardSize+1;i++) {
                // xPosition loop
                for (int j = 0; j < boardSize+1; j++) {
                    int xPos = 150 + (j * 100);
                    int yPos = 200 + (i * 100);
                    canvas.drawCircle(xPos, yPos, 15, paint);
                }
            }
        }
    }
}
