package com.example.guannan.recyclerview_operation.recyclerhelper;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.guannan.recyclerview_operation.ItemAdapter;

import java.util.Collections;

/**
 * RecyclerView的拖动和滑动删除
 * Created by guannan on 2017/9/12.
 */

public class ItemCallback extends android.support.v7.widget.helper.ItemTouchHelper.Callback {

    private ItemAdapter mAdapter;

    public ItemCallback(ItemAdapter adapter) {
        this.mAdapter = adapter;
    }

    /**
     * 当长按itemView移动的时候调用这个方法
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) { //因为GridLayout有上下左右四个方向，所以dragFlags设置为上下左右
            int dragFlags = android.support.v7.widget.helper.ItemTouchHelper.UP | android.support.v7.widget.helper.ItemTouchHelper.DOWN
                    | android.support.v7.widget.helper.ItemTouchHelper.LEFT | android.support.v7.widget.helper.ItemTouchHelper.RIGHT;
            int swipeFlags = 0; //设置为零表示不能够侧滑删除
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            int dragFlags = android.support.v7.widget.helper.ItemTouchHelper.UP | android.support.v7.widget.helper.ItemTouchHelper.DOWN;    //线性布局只要上下两个方向
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    /**
     * 长按移动itemView的时候调用这个方法
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        int fromPosition = viewHolder.getAdapterPosition(); //按下的位置
        int targetPosition = target.getAdapterPosition();   //移动到的位置
        if(recyclerView.getLayoutManager() instanceof GridLayoutManager){   //如果是GridLayoutManager，在第0位不能拖动和被覆盖掉
            if (targetPosition == 0) {
                return false;
            }
        }
        if (fromPosition < targetPosition) {
            for (int i = fromPosition; i < targetPosition; i++) {
                Collections.swap(mAdapter.getDataList(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > targetPosition; i--) {
                Collections.swap(mAdapter.getDataList(), i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, targetPosition);
        return true;
    }

    /**
     * 侧滑删除的时候调用这个方法
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        int adapterPosition = viewHolder.getAdapterPosition();
        mAdapter.notifyItemRemoved(adapterPosition);
        mAdapter.getDataList().remove(adapterPosition);
    }

    /**
     * 是否能够长按拖动itemView  false:不能  true:能拖动
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    /**
     * 当前itemView长按拖动的时候，改变背景色
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE && actionState!=ItemTouchHelper.ACTION_STATE_SWIPE) {  //侧滑状态不设置背景
            viewHolder.itemView.setBackgroundColor(Color.DKGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 手指离开拖动itemView的时候调用，移除itemView设置的背景色
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
    }

}
