package com.example.odadaj.amharicenglishkidstutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {
    TextView starStart;
    ImageView fab1, fab2, fab3, iv;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen, startPage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView3);
        fab1 = (ImageView) findViewById(R.id.fab1);
        fab2 = (ImageView) findViewById(R.id.fab2);
        fab3 = (ImageView) findViewById(R.id.fab3);
        starStart = (TextView) findViewById(R.id.starStart);

        startPage = getIntent().getBooleanExtra("startPage", TRUE);

        if (startPage) {

            final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
            iv.startAnimation(an);
            an.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //Toast.makeText(MainActivity.this, "Click on logo to start", Toast.LENGTH_SHORT).show();
                    starStart.setVisibility(View.VISIBLE);
                    animClick();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {
            animClick();

        }

    }

    private void animClick() {
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        if (!startPage) {
            animateFab();
            starStart.setVisibility(View.VISIBLE);
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });



        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                finish();
                Intent i = new Intent(getBaseContext(), QuizSlider.class);
                //i.putExtra("choseType","quiz");
                startActivity(i);


            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                finish();
                Intent i = new Intent(getBaseContext(), ImageSlider.class);
                i.putExtra("choseType", "textABC");
                startActivity(i);

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                finish();
                Intent i = new Intent(getBaseContext(), ImageSlider.class);
                i.putExtra("choseType", "numbers123");
                startActivity(i);
            }
        });

    }

    private void animateFab() {
        if (isOpen) {
            iv.startAnimation(rotateForward);
            fab1.startAnimation(fabClose);
            fab2.startAnimation(fabClose);
            fab3.startAnimation(fabClose);

            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);

            isOpen = false;
        } else {
            iv.startAnimation(rotateBackward);
            fab1.startAnimation(fabOpen);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);

            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);

            isOpen = true;
        }
    }

}
