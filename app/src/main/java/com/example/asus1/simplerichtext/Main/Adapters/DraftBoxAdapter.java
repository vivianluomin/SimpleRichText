package com.example.asus1.simplerichtext.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus1.simplerichtext.Main.Holders.DraftBoxHolder;
import com.example.asus1.simplerichtext.R;

public class DraftBoxAdapter extends RecyclerView.Adapter<DraftBoxHolder> {
    private Context mContext;

    public DraftBoxAdapter(Context context) {
        mContext = context;
    }

    @Override
    public DraftBoxHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_draft_box,parent,false);
        DraftBoxHolder holder = new DraftBoxHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DraftBoxHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
