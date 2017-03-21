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
    protected List<String> mDatas;

    /**
     * item点击事件接口，
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnitemClickListener;

    public OnItemClickListener getmOnitemClickListener() {
        return mOnitemClickListener;
    }

    public void setmOnitemClickListener(OnItemClickListener mOnitemClickListener) {
        this.mOnitemClickListener = mOnitemClickListener;
    }

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
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.mTextView.setText(mDatas.get(position));
        setUpItemEvent(holder);

    }

    /**
     * 点击事件回调接口
     *
     * @param holder
     */
    protected void setUpItemEvent(final MyHolder holder) {
        if (mOnitemClickListener != null) {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemClick(holder.mTextView, layoutPosition);
                }
            });

            holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemLongClick(holder.mTextView, layoutPosition);
                    return false;
                }
            });
        }
    }

    public void addData(int pos) {
        mDatas.add(pos, "insert one");
//    notifyDataSetChanged();这里不用这个
        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        mDatas.remove(pos);

        notifyItemRemoved(pos);
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