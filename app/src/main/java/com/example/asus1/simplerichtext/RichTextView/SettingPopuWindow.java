package com.example.asus1.simplerichtext.RichTextView;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.example.asus1.simplerichtext.R;

public class SettingPopuWindow extends PopupWindow implements View.OnTouchListener{
    private Activity mContext;
    private View mRoot;
    private SeekBar mLightSeekBar;
    private callBack mCallBack;
    private SliderFont mFontSlider;
    private LinearLayout mFontP;
    private int mScrennWidth;
    private boolean mChangeFont = false;
    private float mLastX;
    private int mFontIndex;
    private static final String TAG = "SettingPopuWindow";

    public SettingPopuWindow(Activity context,
                             int width, int height,int mIndex) {
        super(width, height);
        mContext = context;
        mScrennWidth = width;
        mLastX = mScrennWidth/2;
        mFontIndex = mIndex;
        init();
        initView();
        setTouchInterceptor(this);
    }

    private void init(){
       mRoot =  LayoutInflater.from(mContext).inflate(R.layout.layout_richtext_setting,null,false);
       setContentView(mRoot);
       setFocusable(true);
       setOutsideTouchable(true);
       setTouchable(true);
       setAnimationStyle(R.style.setting_popu_anim);
    }

    private void initView(){
        mLightSeekBar = mRoot.findViewById(R.id.seekbar_light);
        mFontSlider = mRoot.findViewById(R.id.seekbar_font);
        mFontSlider.setScreemWidth(mScrennWidth);
        mFontP = mRoot.findViewById(R.id.ll_font);
        mLightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(mCallBack!=null){
                   mCallBack.setLight((float) progress/10);
               }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        float light = getScreenBrightness();
        if(light <1){
            mLightSeekBar.setProgress((int) (getScreenBrightness()*10));
        }else {
            mLightSeekBar.setProgress((int) (getScreenBrightness()/255*10));
        }


    }


    public void initThumb(){
        mFontSlider.initCenter(mFontIndex);
    }

    private float getScreenBrightness() {

        Window window = mContext.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if(params.screenBrightness!=-1){
            return params.screenBrightness;
        }

        try {
            return Settings.System.getInt(mContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        }catch (Settings.SettingNotFoundException e){
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public void setTouchInterceptor(View.OnTouchListener l) {
        super.setTouchInterceptor(l);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_MOVE){

            if(y>mFontP.getY() &&y<mFontP.getY()+mFontP.getHeight()+30 || mChangeFont){
                mChangeFont = true;
                float specX = x-mLastX;
                mFontSlider.move(specX);
                mLastX = x;
                mFontSlider.invalidate();
            }

        }else if(event.getAction() == MotionEvent.ACTION_DOWN){

            if(y>mFontP.getY() &&y<mFontP.getY()+mFontP.getHeight()){
                mChangeFont = true;
                mFontSlider.setCenter(x);
                mLastX = x;
                mFontSlider.invalidate();
            }

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            if(mChangeFont){
               mFontIndex =  mFontSlider.adJustCenter(x);
               float fontSize = mFontSlider.getFontSize( mFontIndex);
               mCallBack.setFontSize(fontSize, mFontIndex);
                mLastX = x;
            }
            mChangeFont = false;


        }
        return false;
    }

    public void setCallback(callBack callback){
        mCallBack = callback;
    }

    public interface callBack{

        void setLight(float light);
        void setFontSize(float font,int index);
        void changeBackgound(int id);
    }
}
