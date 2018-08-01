package com.example.odadaj.amharicenglishkidstutor;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Quiz_Adapter extends PagerAdapter {


    private ArrayList<Integer> IMAGES;
    private ArrayList<String> ENGTEXTS, ANSTEXTS, CHARTEXTS;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ArrayList<String>> RADIOTEXTS;


    public Quiz_Adapter(Context context, ArrayList<Integer> IMAGES, ArrayList<String> ENGTEXTS, ArrayList<String> ANSTEXTS, ArrayList<ArrayList<String>> RADIOTEXTS) {
        this.context = context;
        this.IMAGES = IMAGES;
        this.ENGTEXTS = ENGTEXTS;
        this.ANSTEXTS = ANSTEXTS;
        this.RADIOTEXTS = RADIOTEXTS;

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
    public Object instantiateItem(ViewGroup view, final int position) {
        final View imageLayout2 = inflater.inflate(R.layout.quiz_layout, view, false);


        assert imageLayout2 != null;
        final ImageView imageView = (ImageView) imageLayout2
                .findViewById(R.id.imgQuiz);

        final TextView engtextView = (TextView) imageLayout2
                .findViewById(R.id.quizLetter);

        final TextView bottomTextView = (TextView) imageLayout2
                .findViewById(R.id.amLetter);

       final RadioGroup radiogrp = (RadioGroup) imageLayout2
                .findViewById(R.id.radioQuiz);

        final RadioButton radio1 = (RadioButton) imageLayout2
                .findViewById(R.id.radio1);
        final RadioButton radio2 = (RadioButton) imageLayout2
                .findViewById(R.id.radio2);
        final RadioButton radio3 = (RadioButton) imageLayout2
                .findViewById(R.id.radio3);

        imageView.setImageResource(IMAGES.get(position));

        engtextView.setText(ENGTEXTS.get(position));

        radio1.setText(RADIOTEXTS.get(position).get(0));
        radio2.setText(RADIOTEXTS.get(position).get(1));
        radio3.setText(RADIOTEXTS.get(position).get(2));

        view.addView(imageLayout2, 0);

        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                String ansCheck = checkedRadioButton.getResources().getResourceEntryName(checkedRadioButton.getId());

                boolean isChecked = checkedRadioButton.isChecked();
                final String ansz = ANSTEXTS.get(position);

                boolean correct = ansCheck.equals(ansz);
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked && correct)
                {
                    Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show();
                    radiogrp.setVisibility(View.INVISIBLE);
                    bottomTextView.setText(checkedRadioButton.getText());
                    bottomTextView.setVisibility(View.VISIBLE);

                }else{
                    Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return imageLayout2;
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
