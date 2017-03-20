package com.example.fxjzzyo.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fxjzzyo.recycleviewdemo.Adapter.MySimpleAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MySimpleAdapter mSimpleAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        mSimpleAdapter = new MySimpleAdapter(this, mDatas);
        mRecyclerView.setAdapter(mSimpleAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    private void initData() {


        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add((char)i+"");
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rlv);
    }
}
