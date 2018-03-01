package com.example.guannan.recyclerview_operation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.guannan.recyclerview_operation.adapter.ItemAdapter;
import com.example.guannan.recyclerview_operation.bean.ItemData;
import com.example.guannan.recyclerview_operation.recyclerhelper.DividerItemDecoration;
import com.example.guannan.recyclerview_operation.recyclerhelper.ItemCallback;
import com.example.guannan.recyclerview_operation.recyclerhelper.RecyclerItemTouchListener;

import java.util.ArrayList;

/**
 * 带分隔线还有实现线性布局侧滑删除，托动排序等
 * Created by guannan on 2017/9/13.
 */

public class RecyclerViewWithDividerAndMoveActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ArrayList<ItemData> mItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_divider);
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

        mRecyclerView.addItemDecoration(new DividerItemDecoration(Color.RED,2));    //分隔线

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemCallback(adapter));   //侧滑删除
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(this,mRecyclerView) {    //添加单击，长按操作
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                int adapterPosition = holder.getAdapterPosition();
                Toast.makeText(RecyclerViewWithDividerAndMoveActivity.this,"点击：选中第"+adapterPosition+"条",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongPress(RecyclerView.ViewHolder holder) {
                int adapterPosition = holder.getAdapterPosition();
                if(mRecyclerView.getLayoutManager() instanceof GridLayoutManager){  //GridLayoutManager长按的时候如果是第0位，不能移动和覆盖
                    if(adapterPosition!=0){
                        itemTouchHelper.startDrag(holder);
                    }
                }else
                    itemTouchHelper.startDrag(holder);
            }
        });
    }

    /**
     * 线性布局
     * @param view
     */
    public void linearLayoutClick(View view){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * 网格布局
     * @param view
     */
    public void gridLayoutClick(View view){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }
}
