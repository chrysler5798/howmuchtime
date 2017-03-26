package com.khrys.howmuchtime;

/*
 * Created by Khrys.
 *
 * App : howmuchtime
 * Info : 3/22/2017 [4:20 PM]
*/

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jesusm.holocircleseekbar.lib.HoloCircleSeekBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    HoloCircleSeekBar seekBar;
    TextView timesDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.mainbutton1);
        Button button2 = (Button) findViewById(R.id.mainbutton2);
        Button button4 = (Button) findViewById(R.id.mainbutton4);

        timesDisplay = (TextView) findViewById(R.id.textView2);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int SCORE = sharedPreferences.getInt("SCORE_TOTAL",0);
        timesDisplay.setText(String.valueOf(SCORE));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_timepick);

                Button playB = (Button) dialog.findViewById(R.id.button2);
                Button closeB = (Button) dialog.findViewById(R.id.button);
                seekBar = (HoloCircleSeekBar) dialog.findViewById(R.id.picker);

                playB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent mIntent = new Intent(MainActivity.this, GameActivity.class);
                        mIntent.putExtra("count",seekBar.getValue()*1000);
                        dialog.cancel();
                        startActivity(mIntent);
                    }
                });

                closeB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StatsActivity.class));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int SCORE = sharedPreferences.getInt("SCORE_TOTAL",0);
        timesDisplay.setText(String.valueOf(SCORE));
    }
}