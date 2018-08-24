package com.example.asus1.simplerichtext.Main.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class CardRecyclerView extends RecyclerView {

    private Context mContext;
    private final  float SCALEV = 0.2f;

    private float mDownX;
    private int mWidth;
    private int mCardWidth;

    private static final String TAG = "CardRecyclerView";

    public CardRecyclerView(Context context) {
        this(context,null);
    }

    public CardRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CardRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        mWidth = MeasureSpec.getSize(widthSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCardWidth = mWidth/getChildCount();

    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX*=SCALEV;
        return super.fling(velocityX, velocityY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean result  = super.onTouchEvent(e);

        if(e.getAction() == MotionEvent.ACTION_DOWN){
            mDownX = e.getRawX();
        }

        if(e.getAction() == MotionEvent.ACTION_UP){
           float delatX = mDownX - e.getRawX();
           if(Math.abs(delatX)>=mCardWidth/3){
               int index = ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
               Log.d(TAG, "onTouchEvent: "+index);
               if(delatX<0){
                   smoothScrollBy((int) Math.ceil(mCardWidth+delatX+30)*-1,0);
               }else {
                   smoothScrollBy((int)Math.ceil(mCardWidth-delatX+30),0);
               }

           }else {

               smoothScrollBy((int) Math.floor(delatX)*-1,0);

           }
        }

        return result;
    }



    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
