package com.example.asus1.simplerichtext.Main.Holders;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus1.simplerichtext.Main.Activities.NovelCaptureActivity;
import com.example.asus1.simplerichtext.R;

public class MyWorkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private View mRoot;
    private CardView mFront;
    private CardView mBack;
    private ImageView mSetting;
    private TextView mSeeWork;
    private TextView mClose;
    private Context mContext;

    private static final String TAG = "MyWorkHolder";


    public MyWorkHolder(Context context, View itemView) {
        super(itemView);
        mRoot = itemView;
        mContext = context;
        mFront = mRoot.findViewById(R.id.card_front);
        mBack = mRoot.findViewById(R.id.card_back);
        mSetting = mRoot.findViewById(R.id.iv_setting);
        mSetting.setOnClickListener(this);
        mClose = mRoot.findViewById(R.id.tv_close);
        mClose.setOnClickListener(this);
        mSeeWork = mRoot.findViewById(R.id.tv_see_work);
        mSeeWork.setOnClickListener(this);
        setCameraDistance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_setting:
                openBack();
                break;
            case R.id.tv_close:
                openFront();
                break;
            case R.id.tv_see_work:
                mContext.startActivity(new Intent(mContext, NovelCaptureActivity.class));
                break;
        }
    }

    private void openBack(){
        ObjectAnimator front = (ObjectAnimator) AnimatorInflater.
                loadAnimator(mContext,R.animator.animtor_card_front_gone);
        ObjectAnimator back = (ObjectAnimator)AnimatorInflater.
                loadAnimator(mContext,R.animator.animtor_card_back_show);
       front.setTarget(mFront);
       back.setTarget(mBack);
       front.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
           @Override
           public void onAnimationUpdate(ValueAnimator animation) {
               float value = (float)animation.getAnimatedValue();
               if(value >= 90){
                   mFront.setVisibility(View.GONE);
                   mBack.setVisibility(View.VISIBLE);
               }
           }
       });
        back.start();
       front.start();

    }

    private void openFront(){

        ObjectAnimator front = (ObjectAnimator) AnimatorInflater.
                loadAnimator(mContext,R.animator.animtor_card_front_show);
        ObjectAnimator back = (ObjectAnimator)AnimatorInflater.
                loadAnimator(mContext,R.animator.animtor_card_back_gone);
        front.setTarget(mFront);
        back.setTarget(mBack);
        back.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float)animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: "+value);
                if(value <= -90){
                    mFront.setVisibility(View.VISIBLE);
                    mBack.setVisibility(View.GONE);
                }
            }
        });
        front.start();
        back.start();

    }

    private void setCameraDistance() {
        int distance = 16000;
        float scale = mContext.getResources().getDisplayMetrics().density * distance;
        mFront.setCameraDistance(scale);
        mBack.setCameraDistance(scale);
    }

}
