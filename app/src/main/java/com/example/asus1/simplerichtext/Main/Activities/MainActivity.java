package com.example.asus1.simplerichtext.Main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus1.simplerichtext.Add.AddActivity;
import com.example.asus1.simplerichtext.Base.BaseActivity;
import com.example.asus1.simplerichtext.Main.Adapters.MyWorkAdapter;
import com.example.asus1.simplerichtext.R;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mBack;
    private RecyclerView mRecyclerView;
    private TextView mAddWork;
    private MyWorkAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new MyWorkAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mAdapter);
        mAddWork = findViewById(R.id.tv_add);
        mAddWork.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_add:
                startActivity(new Intent(MainActivity.this,
                        AddActivity.class));
                break;
        }
    }
}
