package com.example.fxjzzyo.recycleviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.example.fxjzzyo.recycleviewdemo.Adapter.MySimpleAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MySimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeActionOverFlowMenuShown();//使菜单能变成右上角三点显示
        initView();
        initData();
        mSimpleAdapter = new MySimpleAdapter(this, mDatas);
        mRecyclerView.setAdapter(mSimpleAdapter);
        //设置为线性listview式布局
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //设置更改动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //来自githun库上的动画，有listview滑动上下错位bug
//        mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
//        mRecyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(mRecyclerView));
//        mRecyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(mRecyclerView));
        //设置监听
        mSimpleAdapter.setmOnitemClickListener(new MySimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "long click " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {

        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add((char)i+"");
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
                mSimpleAdapter.addData(1);
                break;
            case R.id.action_delete:
                mSimpleAdapter.deleteData(1);
                break;
            case R.id.action_GridView://网格布局

                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.action_listview:
                LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                mRecyclerView.setLayoutManager(llm);
                break;
            case R.id.action_StaggerView://瀑布流布局
                Intent intent = new Intent(this, StaggerActivity.class);
                startActivity(intent);
//                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * 利用反射，使菜单能变成右上角三点显示
     */
    private void makeActionOverFlowMenuShown() {
        ViewConfiguration configuration = ViewConfiguration.get(this);
        try {
            Field menuField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuField != null) {
                menuField.setAccessible(true);
                menuField.setBoolean(configuration, false);
            }


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
