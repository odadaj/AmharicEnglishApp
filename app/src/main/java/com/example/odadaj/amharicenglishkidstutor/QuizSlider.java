package com.example.odadaj.amharicenglishkidstutor;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Boolean.FALSE;


public class QuizSlider extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    MediaPlayer mp;
    private Integer[] IMAGES;
    private String[] ENGTEXTS, ANSTEXTS;
    private String [][] RADIOTEXTS;
    private int[] mAudio;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private ArrayList<String> EngTextArray = new ArrayList<String>();
    private ArrayList<String> AnsArray = new ArrayList<String>();

    private ArrayList<ArrayList<String>> RadioArray = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_slider);

        IMAGES = new Integer[]{R.drawable.banana, R.drawable.equals9, R.drawable.cat,  R.drawable.equals2, R.drawable.equals3,R.drawable.queen, R.drawable.vehicle, R.drawable.equals10, R.drawable.pineapples,R.drawable.equals8};
        ENGTEXTS = new String[]{"Which fruit is this?", "What is the sum?","Which animal is this?","What is the sum?","What is the sum?","Who is this?", "What is this?","What is the sum?","Which fruit is this?","What is the sum?"};
        ANSTEXTS = new String[]{"radio3", "radio2", "radio3", "radio1", "radio2","radio1", "radio2", "radio2", "radio1", "radio3"};
        RADIOTEXTS = new String[][]{{"Tomato","Pineapple","Banana"},{"10","9","3"},{"Dog", "Giraffe","Cat"},{"2","4","7"},{"5","3","6"},{"Queen", "Police","Teacher"},{"Iceberg", "Vehicle","Umbrella"},{"8","10","1"},{"Pineapple","Orange","Mango"},{"3","6","8"}};
        mAudio = new int[]{R.raw.fruit, R.raw.sum, R.raw.animal, R.raw.sum, R.raw.sum, R.raw.who, R.raw.wthis, R.raw.sum, R.raw.fruit, R.raw.sum};

        init();
        //
        mp = MediaPlayer.create(QuizSlider.this, mAudio[0]);
        mp.start();

    }


    private void init() {

        for (int i = 0; i < IMAGES.length; i++)   ImagesArray.add(IMAGES[i]);

        for (int i = 0; i < ENGTEXTS.length; i++) EngTextArray.add(ENGTEXTS[i]);

        for (int i = 0; i < ANSTEXTS.length; i++)  AnsArray.add(ANSTEXTS[i]);

        for (int i = 0; i < RADIOTEXTS.length; i++)  {
            ArrayList<String> AngArray = new ArrayList<>(Arrays.asList(RADIOTEXTS[i]));
            RadioArray.add(AngArray);
            //Log.d("myTag", RADIOTEXTS[0][0]);

        }


        mPager = (ViewPager) findViewById(R.id.pager2);


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mp.stop();
                mp = MediaPlayer.create(QuizSlider.this, mAudio[position]);
                mp.start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        mPager.setAdapter(new Quiz_Adapter(QuizSlider.this, ImagesArray, EngTextArray, AnsArray, RadioArray));


        final float density = getResources().getDisplayMetrics().density;

        NUM_PAGES = IMAGES.length;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mp.stop();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("startPage", FALSE);
        this.startActivity(intent);

        return true;
    }

}