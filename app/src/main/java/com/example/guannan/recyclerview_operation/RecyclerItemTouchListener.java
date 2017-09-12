package com.example.guannan.recyclerview_operation;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 实现点击条目单击或者长按
 * 主要根据RecyclerView的OnItemTouchListener来监听RecyclerView的触摸事件，然后将事件传递给GestureDetecotr来判断是单击还是长按
 * Created by guannan on 2017/9/11.
 */

public abstract class RecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

    private final GestureDetectorCompat mGestureDetectorCompat;
    private RecyclerView mRecyclerView;

    public RecyclerItemTouchListener(Context context,RecyclerView recyclerView){
        this.mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new OnItemGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public class OnItemGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            //findChildViewUnder根据点击的x,y坐标找到点击的位置所属的view
            View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if(childViewUnder!=null){
                //找到RecyclerView点击x,y位置的View的ViewHolder
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(childViewUnder);
                onItemClick(viewHolder);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if(childViewUnder!=null){
                RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(childViewUnder);
                onItemLongPress(childViewHolder);
            }
        }
    }

    public abstract void onItemClick(RecyclerView.ViewHolder holder);

    public abstract void onItemLongPress(RecyclerView.ViewHolder holder);
}
