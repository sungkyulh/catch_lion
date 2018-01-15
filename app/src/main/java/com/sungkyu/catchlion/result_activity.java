package com.sungkyu.catchlion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.sungkyu.catchlion.MainActivity.valus;

/**
 * Created by sungkyu on 2017-11-12.
 */

public class result_activity extends Activity implements View.OnTouchListener {

    LinearLayout screen;
    int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        count=0;
        ImageView result2 = (ImageView)findViewById(R.id.resultImageView2);
        ImageView result1 = (ImageView)findViewById(R.id.resultImageView1);
        screen = (LinearLayout)findViewById(R.id.resultlayout);
        screen.setOnTouchListener(this);

        if(valus.getGame_mode()==1) {
            if (valus.getWinFlag() == 1) {
                result2.setImageResource(R.drawable.defeat);
                result1.setImageResource(R.drawable.victory);
            } else if (valus.getWinFlag() == 2) {
                result2.setImageResource(R.drawable.victory);
                result1.setImageResource(R.drawable.defeat);
            } else if (valus.getWinFlag() == 5) {
                result2.setImageResource(R.drawable.draw);
                result1.setImageResource(R.drawable.draw);
            }
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        count++;
        if(count>2) finish();

        return false;
    }
}
