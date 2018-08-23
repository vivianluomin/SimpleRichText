package com.example.asus1.simplerichtext.Main.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.asus1.simplerichtext.Main.Adapters.DraftBoxAdapter;
import com.example.asus1.simplerichtext.Main.Adapters.PublishedCaptureAdater;
import com.example.asus1.simplerichtext.R;

import java.util.List;

public class DraftFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private PublishedCaptureAdater mAdapter;
    private RelativeLayout mRelativeBg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_draft_box,container,false);
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mAdapter = new PublishedCaptureAdater(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRelativeBg = mView.findViewById(R.id.fragment_no_data);
        return mView;
    }

    public void setData(){

    }

}
