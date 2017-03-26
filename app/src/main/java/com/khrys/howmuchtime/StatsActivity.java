package com.khrys.howmuchtime;
/*
 * Created by Khrys.
 *
 * App : howmuchtime
 * Info : 3/24/2017 [4:47 PM]
*/

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    TextView txtVscore, txtVtime, txtVmoyenne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats);

        txtVscore = (TextView) findViewById(R.id.textVscoreTotal);
        txtVtime = (TextView) findViewById(R.id.textVtimeTotal);
        txtVmoyenne = (TextView) findViewById(R.id.textVavg);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int score = sharedPreferences.getInt("SCORE_TOTAL",0);
        int time = sharedPreferences.getInt("TIME_TOTAL",0);

        time /= 1000;
        float average = (float) score/time;
        txtVscore.setText("Total score\n"+String.valueOf(score));
        txtVtime.setText("Total time\n"+String.valueOf(time)+" sec");
        txtVmoyenne.setText("Average\n"+String.format("%.2f", average)+" points per sec");

    }
}
