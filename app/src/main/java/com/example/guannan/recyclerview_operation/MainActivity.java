package com.example.guannan.recyclerview_operation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.guannan.recyclerview_operation.bean.ItemData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<ItemData> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        ItemAdapter itemAdapter = new ItemAdapter(mItemList, this);
        mRecyclerView.setAdapter(itemAdapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
//        mRecyclerView.addItemDecoration(new TimeLineDecoration(this,mItemList));

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemCallback(itemAdapter));   //滑动交换RecyclerView的ItemVeiw的位置
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(this,mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {

                int adapterPosition = holder.getAdapterPosition();
                Integer integer = mItemList.get(adapterPosition).getDes();
                Toast.makeText(MainActivity.this,integer+"短按",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongPress(RecyclerView.ViewHolder holder) {
                int adapterPosition = holder.getAdapterPosition();
                Integer integer = mItemList.get(adapterPosition).getDes();
                Toast.makeText(MainActivity.this,integer+"长按",Toast.LENGTH_SHORT).show();
                if(adapterPosition!=0){
                    itemTouchHelper.startDrag(holder);
                }
            }
        });
    }

    private void initData(){

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

}
