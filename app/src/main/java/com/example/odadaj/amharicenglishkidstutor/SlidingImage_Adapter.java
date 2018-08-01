package com.example.odadaj.amharicenglishkidstutor;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<Integer> IMAGES;
    private ArrayList<String> ENGTEXTS, AMHATEXTS, CHARTEXTS;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<Integer> IMAGES, ArrayList<String> ENGTEXTS, ArrayList<String> AMHATEXTS, ArrayList<String> CHARTEXTS) {
        this.context = context;
        this.IMAGES = IMAGES;
        this.ENGTEXTS = ENGTEXTS;
        this.AMHATEXTS = AMHATEXTS;
        this.CHARTEXTS = CHARTEXTS;

        inflater = LayoutInflater.from(context);


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);


        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.imgMain);

        final TextView engtextView = (TextView) imageLayout
                .findViewById(R.id.alphaLetter);

        final TextView amhtextView = (TextView) imageLayout
                .findViewById(R.id.amhaLetter);


        imageView.setImageResource(IMAGES.get(position));

        engtextView.setText(ENGTEXTS.get(position));

        amhtextView.setText(AMHATEXTS.get(position));

        view.addView(imageLayout, 0);



        return imageLayout;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return CHARTEXTS.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
