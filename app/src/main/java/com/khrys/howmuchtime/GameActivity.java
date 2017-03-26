package com.khrys.howmuchtime;
/*
 * Created by Khrys.
 *
 * App : howmuchtime
 * Info : 3/23/2017 [6:43 PM]
*/

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import static java.lang.reflect.Array.getInt;

public class GameActivity extends AppCompatActivity {

    TextView txtCounter, txtVscore;
    Paint paint = new Paint();
    SurfaceView surfaceView;
    Bitmap bitmap;
    int nextColor = 0, score = 0, mHeight, mWidth, randomPlace, randomPlace2, time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        txtCounter = (TextView)findViewById(R.id.textVcountDown);
        txtVscore = (TextView) findViewById(R.id.textVscore);

        surfaceView = (SurfaceView) findViewById(R.id.surfView);

        surfaceView.post(new Runnable() {
            @Override
            public void run() {
                mWidth = surfaceView.getWidth();
                mHeight = surfaceView.getHeight();

                surfaceView.setBackgroundDrawable(new BitmapDrawable(roundSetup()));
            }
        });

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if((motionEvent.getX(0)>=(randomPlace-100)) && (motionEvent.getX(0)<=(randomPlace+100)) && (motionEvent.getY(0)>=(randomPlace2-100)) && (motionEvent.getY(0)<=(randomPlace2+100)))
                {
                    txtVscore.setText(String.valueOf(++score));
                    surfaceView.setBackgroundDrawable(new BitmapDrawable(roundSetup()));
                }
                return true;

            }
        });

        time = getIntent().getIntExtra("count",0);
        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                txtCounter.setText(millisUntilFinished / 1000+" seconds");
            }

            public void onFinish() {
                txtCounter.setText("DONE !");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                int scoreAvant = preferences.getInt("SCORE_TOTAL",0);
                int timeAvant = preferences.getInt("TIME_TOTAL",0);
                editor.putInt("TIME_TOTAL",timeAvant+time);
                editor.putInt("SCORE_TOTAL",scoreAvant+score);
                editor.apply();
                finish();
            }
        }.start();

    }

    Bitmap roundSetup()
    {
        int color = Color.BLACK;

        nextColor++;
        switch (nextColor)
        {
            case 1:
                color = Color.RED;
                break;

            case 2:
                color = Color.GREEN;
                break;

            case 3:
                color = Color.BLUE;
                nextColor = 0;
                break;
        }

        paint.setColor(color);

        bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        Random r = new Random();
        randomPlace = r.nextInt(mWidth-100 - 100) + 100;
        randomPlace2 = r.nextInt(mHeight-100 - 100) + 100;

        canvas.drawCircle(randomPlace, randomPlace2, 100, paint);

        return bitmap;
    }
}
