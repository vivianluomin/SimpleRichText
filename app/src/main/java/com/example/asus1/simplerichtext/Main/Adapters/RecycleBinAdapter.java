package com.example.asus1.simplerichtext.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus1.simplerichtext.Main.Holders.RecycleBinHolder;
import com.example.asus1.simplerichtext.R;

import java.util.logging.Handler;

public class RecycleBinAdapter extends RecyclerView.Adapter<RecycleBinHolder> {


    private Context mContext;

    public RecycleBinAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecycleBinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_recycle_bin_item,parent,false);
        RecycleBinHolder holder = new RecycleBinHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleBinHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
