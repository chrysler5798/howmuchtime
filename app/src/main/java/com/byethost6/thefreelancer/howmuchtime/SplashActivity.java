package com.byethost6.thefreelancer.howmuchtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.animation;
import static android.R.transition.fade;
import static java.lang.Thread.sleep;

/**
 * Created by Chrysler on 11/24/2016.
 * <p>
 * howmuchtime
 */

public class SplashActivity extends AppCompatActivity {

    Animation zoomout, moveup, fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        zoomout = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        moveup = AnimationUtils.loadAnimation(this, R.anim.translate);
        fade = AnimationUtils.loadAnimation(this, R.anim.alpha);

        final TextView txt1 = (TextView) findViewById(R.id.textHowmuch);
        final TextView txt2 = (TextView) findViewById(R.id.textTime);
        final ImageView logo = (ImageView) findViewById(R.id.logoSplash);

        logo.setAnimation(moveup);
        txt1.setAnimation(fade);

        moveup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(zoomout);
                txt2.setAlpha(1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent myIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
