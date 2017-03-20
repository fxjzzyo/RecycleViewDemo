package com.example.fxjzzyo.recycleviewdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fxjzzyo.recycleviewdemo.R;

import java.util.List;

/**
 * Created by fxjzzyo on 2017/3/20.
 */

public class MySimpleAdapter extends RecyclerView.Adapter<MyHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mDatas;

    public MySimpleAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override

    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_simple_textview,parent, false);
        MyHolder holder = new MyHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position));
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
class MyHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    public MyHolder(View itemView) {

        super(itemView);

        mTextView = (TextView) itemView.findViewById(R.id.tv);
    }
}