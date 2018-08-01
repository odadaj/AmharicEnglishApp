package com.example.odadaj.amharicenglishkidstutor;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;


public class ImageSlider extends AppCompatActivity {
    String choseType;
    private Integer[] IMAGES;
    private String[] ENGTEXTS;
    private String[] AMHATEXTS;
    private String[] CHARTEXTS;
    private int[] mAudio;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private ArrayList<String> EngTextArray = new ArrayList<String>();
    private ArrayList<String> AmhaTextArray = new ArrayList<String>();
    private ArrayList<String> CharArray = new ArrayList<String>();


    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_slider);
        choseType = getIntent().getStringExtra("choseType");
        switch (choseType) {
            case "textABC":
               IMAGES = new Integer[]{R.drawable.ant,R.drawable.banana,R.drawable.cat,R.drawable.dog,R.drawable.elephant,R.drawable.fish,R.drawable.giraffe,R.drawable.horse,R.drawable.iceberg,R.drawable.jam,R.drawable.key,R.drawable.lion,R.drawable.monkey,R.drawable.nuts,R.drawable.onion,R.drawable.pineapples,R.drawable.queen,R.drawable.rabbit,R.drawable.stars,R.drawable.tomatoes,R.drawable.umbrella,R.drawable.vehicle,R.drawable.water,R.drawable.xray,R.drawable.yoghurt,R.drawable.zebra};
               ENGTEXTS = new String[]{"Ant","Banana","Cat","Dog","Elephant","Fish","Giraffe","Horse","Ice","Jam","Key","Lion","Monkey","Nuts","Onion","Pineapple","Queen","Rabbit","Star","Tomato","Umbrella","Vehicle","Water","XRay","Yogurt","Zebra"};
               AMHATEXTS = new String[]{"ጉንዳን", "ሙዝ", " ድመት", "ውሻ", "ዝሆን","ጉንዳን", "ሙዝ", " ድመት", "ውሻ", "ዝሆን","ጉንዳን", "ሙዝ", " ድመት", "ውሻ", "ዝሆን","ጉንዳን", "ሙዝ", " ድመት", "ውሻ", "ዝሆን","ሙዝ", " ድመት", "ውሻ", "ዝሆን","ጉንዳን", "ሙዝ"};
               CHARTEXTS = new String[]{"A a", "B b", "C c", "D d", "E e", "F f","G g", "H h", "I i", "J j", "K k","L l", "M m", "N n", "O o", "P p", "Q q", "R r", "S s", "T t", "U u", "V v", "W w", "X x", "Y y", "Z z"};
               mAudio = new int[]{R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e,R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j,R.raw.k,R.raw.l, R.raw.m, R.raw.n, R.raw.o,R.raw.p, R.raw.q, R.raw.r, R.raw.s, R.raw.w,R.raw.u, R.raw.v, R.raw.w, R.raw.x, R.raw.y,R.raw.z};
               break;
            case "numbers123":
                IMAGES = new Integer[]{R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five,R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten};
                ENGTEXTS = new String[]{"One", "Two", "Three", "Four", "Five","Six", "Seven", "Eight", "Nine", "Ten"};
                AMHATEXTS = new String[]{"አንድ", "ሁለት", " ሶስት", "አራት", "አምስት","ስድስት", "ሰባት", "ስምት", "ዘጠኝ", " አስር"};
                CHARTEXTS = new String[]{"1", "2", "3", "4", "5","6", "7", "8", "9", "10"};
                mAudio = new int[]{R.raw.one, R.raw.two, R.raw.three, R.raw.four, R.raw.five,R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine, R.raw.ten};
                break;
        }
        init();
        mp = MediaPlayer.create(ImageSlider.this, mAudio[0]);
        mp.start();
    }


    private void init() {


        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        for (int i = 0; i < ENGTEXTS.length; i++)
            EngTextArray.add(ENGTEXTS[i]);

        for (int i = 0; i < AMHATEXTS.length; i++)
            AmhaTextArray.add(AMHATEXTS[i]);

        for (int i = 0; i < CHARTEXTS.length; i++)
            CharArray.add(CHARTEXTS[i]);


        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                mp.stop();
                mp = MediaPlayer.create(ImageSlider.this, mAudio[position]);
                mp.start();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mPager.setAdapter(new SlidingImage_Adapter(ImageSlider.this, ImagesArray, EngTextArray, AmhaTextArray, CharArray));


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
        intent.putExtra("startPage",FALSE);
        this.startActivity(intent);

        /*
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("startPage",FALSE);
                this.startActivity(intent);
                break;
            case R.id.item2:
                Intent intent2 = new Intent(this, MainActivity.class);
                intent2.putExtra("startPage",FALSE);
                this.startActivity(intent2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        return true;
    }

}