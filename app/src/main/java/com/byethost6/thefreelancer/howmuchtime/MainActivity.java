package com.byethost6.thefreelancer.howmuchtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button4 = (Button) findViewById(R.id.mainbutton4);
        TextView timesDisplay = (TextView) findViewById(R.id.textView2);


        Random random = new Random();
        int randomInt = random.nextInt(500);
        timesDisplay.setText(String.valueOf(randomInt));

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
}
