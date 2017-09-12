package com.example.guannan.recyclerview_operation;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;

/**
 * Created by guannan on 2017/9/12.
 */

public class ItemCallback extends android.support.v7.widget.helper.ItemTouchHelper.Callback {

    private ItemAdapter mAdapter;

    public ItemCallback(ItemAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            int dragFlags = android.support.v7.widget.helper.ItemTouchHelper.UP | android.support.v7.widget.helper.ItemTouchHelper.DOWN
                    | android.support.v7.widget.helper.ItemTouchHelper.LEFT | android.support.v7.widget.helper.ItemTouchHelper.RIGHT;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            int dragFlags = android.support.v7.widget.helper.ItemTouchHelper.UP | android.support.v7.widget.helper.ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        int fromPosition = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();
        if (targetPosition == 0) {
            return false;
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        int adapterPosition = viewHolder.getAdapterPosition();
        mAdapter.notifyItemRemoved(adapterPosition);
        mAdapter.getDataList().remove(adapterPosition);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.DKGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
    }

}
