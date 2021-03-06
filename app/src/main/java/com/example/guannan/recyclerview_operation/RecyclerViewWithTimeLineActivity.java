package com.example.guannan.recyclerview_operation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.guannan.recyclerview_operation.adapter.ItemAdapter;
import com.example.guannan.recyclerview_operation.bean.ItemData;
import com.example.guannan.recyclerview_operation.recyclerhelper.RecyclerItemTouchListener;
import com.example.guannan.recyclerview_operation.recyclerhelper.TimeLineDecoration;

import java.util.ArrayList;

/**
 * 带时间轴和分隔线
 * Created by guannan on 2017/9/13.
 */

public class RecyclerViewWithTimeLineActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ArrayList<ItemData> mItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_timeline);
        initData();
        initViews();
    }

    private void initData() {
        mItemList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ItemData itemData = new ItemData();
            itemData.setDes(i);
            if(i<=31){
                itemData.setTime("2017-08-"+i);
            }else
                itemData.setTime("2017-09-"+(i-30));
            mItemList.add(itemData);
        }
    }


    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        final ItemAdapter adapter = new ItemAdapter(mItemList, this);
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.addItemDecoration(new TimeLineDecoration(this,mItemList));    //添加时间轴和分隔线

        mRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(this,mRecyclerView) {    //添加单击，长按操作
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                int adapterPosition = holder.getAdapterPosition();
                Toast.makeText(RecyclerViewWithTimeLineActivity.this,"点击：选中第"+adapterPosition+"条",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongPress(RecyclerView.ViewHolder holder) {
                int adapterPosition = holder.getAdapterPosition();
                Toast.makeText(RecyclerViewWithTimeLineActivity.this,"长按：选中第"+adapterPosition+"条",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
