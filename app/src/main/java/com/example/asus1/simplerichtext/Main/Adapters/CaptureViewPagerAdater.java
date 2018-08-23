package com.example.asus1.simplerichtext.Main.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.asus1.simplerichtext.R;

import java.util.ArrayList;
import java.util.List;

public class CaptureViewPagerAdater extends FragmentPagerAdapter {

    private List<Fragment> mFragments ;
    private String[] mTitles ;

    public CaptureViewPagerAdater(FragmentManager fm, List<Fragment> fragments,
                                  String[] titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
