package com.techteamuk.app.android_boxes.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameBoardActivity extends AppCompatActivity {

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
            int radius;
            radius = 250;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#000000"));
//            canvas.drawCircle(x / 2, y / 2, 50, paint);

            // draw the dots on the game board

            // yPosition loop
            for(int i=0;i<10;i++) {
                // xPosition loop
                for (int j = 0; j < 20; j++) {
                    int xPos = 25 + (j * 100);
                    int yPos = 25 + (i * 100);
                    canvas.drawCircle(xPos, yPos, 20, paint);
                }
            }

//            for(int i=0; i<10;i++){
//                int xPos = 25 + (i * 100);
//                int yPos = 125;
//                canvas.drawCircle(xPos, yPos, 20, paint);
//            }
//            canvas.drawCircle(25, 25, 20, paint);
//            canvas.drawCircle(125, 25, 20, paint);
//            canvas.drawCircle(225, 25, 20, paint);
//            canvas.drawCircle(325, 25, 20, paint);
        }
    }
}
