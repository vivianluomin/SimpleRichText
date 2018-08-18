package com.example.asus1.simplerichtext.RichTextView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class LinearScrollView extends ScrollView {

    private Context mContext;
    private static final String TAG = "LinearScrollView";
    private onScrollLinstener mListener;
    private int mPreLocalY = 0;
    private float mLastX = 0;
    private float mLastY = 0;

    public LinearScrollView(Context context) {
        this(context,null);
    }

    public LinearScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinearScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setScorllListener(onScrollLinstener listener){
        mListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mListener!=null){
            mListener.onScrollChange();
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);

    }

    public interface onScrollLinstener{
        void onScrollChange();
    }
}
