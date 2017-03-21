package com.example.fxjzzyo.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fxjzzyo.recycleviewdemo.Adapter.MySimpleAdapter;
import com.example.fxjzzyo.recycleviewdemo.Adapter.StaggerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StaggerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggerAdapter mstaggerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        mstaggerAdapter = new StaggerAdapter(this, mDatas);
        mRecyclerView.setAdapter(mstaggerAdapter);
        //设置为瀑布流式布局
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        //设置默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mstaggerAdapter.setmOnitemClickListener(new MySimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggerActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggerActivity.this, "long click and delete " + position, Toast.LENGTH_SHORT).show();
                //长按删除
                mstaggerAdapter.deleteData(position);
            }
        });
    }

    private void initData() {

        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add((char) i + "");
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rlv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                mstaggerAdapter.addData(1);

                break;
            case R.id.action_delete:
                mstaggerAdapter.deleteData(1);
                break;
        }


        return super.onOptionsItemSelected(item);
    }


}
