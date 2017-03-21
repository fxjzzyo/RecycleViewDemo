package com.example.fxjzzyo.recycleviewdemo.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxjzzyo on 2017/3/21.
 */

public class StaggerAdapter extends MySimpleAdapter {

    private List<Integer> mHeights;

    public StaggerAdapter(Context mContext, List<String> mDatas) {
        super(mContext, mDatas);
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.mTextView.getLayoutParams();
//        layoutParams.height = (int) (100+Math.random()*300);
        layoutParams.height = mHeights.get(position);
        holder.mTextView.setLayoutParams(layoutParams);
        holder.mTextView.setText(mDatas.get(position));
        //调用设置监听
        setUpItemEvent(holder);

    }

}

