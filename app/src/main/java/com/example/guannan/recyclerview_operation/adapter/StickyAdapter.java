package com.example.guannan.recyclerview_operation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guannan.recyclerview_operation.R;
import com.example.guannan.recyclerview_operation.bean.Sticky;

import java.util.ArrayList;

/**
 * @author guannan
 * @date 2018/2/28 16:55
 */

public class StickyAdapter extends RecyclerView.Adapter<StickyAdapter.StickyViewHolder> {

    private ArrayList<Sticky> mList;
    private final LayoutInflater mInflater;

    public StickyAdapter(Context context, ArrayList<Sticky> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public StickyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_sticky_layout, null);
        StickyViewHolder stickyViewHolder = new StickyViewHolder(view);
        return stickyViewHolder;
    }

    @Override
    public void onBindViewHolder(StickyViewHolder holder, int position) {
        String content = mList.get(position).getContent();
        if(!TextUtils.isEmpty(content)){
            holder.mTextView.setText(content);
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class StickyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public StickyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
