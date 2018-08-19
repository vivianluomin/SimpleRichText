package com.example.asus1.simplerichtext.RichTextView;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.example.asus1.simplerichtext.R;

public class SettingPopuWindow extends PopupWindow {
    private Activity mContext;
    private View mRoot;
    private SeekBar mLightSeekBar;
    private callBack mCallBack;

    private static final String TAG = "SettingPopuWindow";

    public SettingPopuWindow(Activity context, int width, int height) {
        super(width, height);
        mContext = context;
        init();
        initView();
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


    public void setCallback(callBack callback){
        mCallBack = callback;
    }

    public interface callBack{

        void setLight(float light);
        void setFontSize(int font);
        void changeBackgound(int id);
    }
}
