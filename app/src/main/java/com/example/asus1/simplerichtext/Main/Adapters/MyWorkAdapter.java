package com.example.asus1.simplerichtext.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus1.simplerichtext.Main.Holders.MyWorkHolder;
import com.example.asus1.simplerichtext.R;

public class MyWorkAdapter extends RecyclerView.Adapter<MyWorkHolder> {

    private Context mContext;

    public MyWorkAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyWorkHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).
                inflate(R.layout.layout_main_recycler_item,parent,false);

        return new MyWorkHolder(mContext,view);
    }

    @Override
    public void onBindViewHolder(MyWorkHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
