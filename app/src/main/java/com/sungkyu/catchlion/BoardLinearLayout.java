package com.sungkyu.catchlion;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by sungkyu on 2017-11-08.
 */

public class BoardLinearLayout extends LinearLayout {
    public BoardLinearLayout(Context context) {
        super(context);
    }

    public BoardLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BoardLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        int max_width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width=height*3/4;

        if(width>max_width){
            width=max_width;
            height=width*4/3;
        }

        setMeasuredDimension(width, height);

    }

}
