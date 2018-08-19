package com.example.asus1.simplerichtext.RichTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;

import com.example.asus1.simplerichtext.R;

public class SliderFont extends View {

    private Context mContext;
    private Drawable mThumb;
    private Paint mThumbPaint;
    private Paint mProgressPaint;

    private int mWidth;
    private int mHeight;
    private  int i = 0;
    private int mFontSize = 19;
    private int mSpec = 0;

    public SliderFont(Context context) {
        this(context,null);
    }

    public SliderFont(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SliderFont(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init(){
        mThumb = mContext.getResources().getDrawable(R.drawable.slider_thumb_normal);
        mThumbPaint = new Paint();
        mProgressPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST){
            mWidth = 500;
        }else {
            mWidth = width;
        }

        if(heightMode == MeasureSpec.AT_MOST){
            mHeight = 10;
        }else {
            mHeight = height;
        }

        mSpec = mWidth/7;

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mProgressPaint.setColor(mContext.getResources().getColor(R.color.divide_color));
        mProgressPaint.setStyle(Paint.Style.FILL);
        for(i = 0;i<7;i++){

        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
