package com.example.guannan.recyclerview_operation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guannan.recyclerview_operation.R;
import com.example.guannan.recyclerview_operation.bean.ItemData;

import java.util.ArrayList;

/**
 * Created by guannan on 2017/9/11.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {

    private ArrayList<ItemData> mList;
    private Context mContext;
    private final LayoutInflater mInflater;

    public ItemAdapter(ArrayList<ItemData> list, Context context){
        this.mList = list;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = mInflater.inflate(R.layout.item_layout, parent,false);
        return new Holder(convertView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        int itemValue = mList.get(position).getDes();
        String des = null;
        Drawable drawable = null;
        switch (itemValue % 5) {
            case 0:
                des = "XFL";
                drawable = mContext.getResources().getDrawable(R.mipmap.icon_five);
                break;
            case 1:
                des = "HOT";
                drawable = mContext.getResources().getDrawable(R.mipmap.icon_four);
                break;
            case 2:
                des = "BOLTS";
                drawable = mContext.getResources().getDrawable(R.mipmap.icon_three);
                break;
            case 3:
                des = "SHEEP";
                drawable = mContext.getResources().getDrawable(R.mipmap.icon_two);
                break;
            case 4:
                des = "LION";
                drawable = mContext.getResources().getDrawable(R.mipmap.icon_one);
                break;
        }
        holder.mTvDes.setText(des);
        holder.mIvIcon.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ArrayList<ItemData> getDataList() {
        return mList;
    }

    public class Holder extends RecyclerView.ViewHolder{

        private TextView mTvDes;
        private ImageView mIvIcon;
        public Holder(View itemView) {
            super(itemView);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            mTvDes = (TextView) itemView.findViewById(R.id.tv_des);
        }
    }
}
