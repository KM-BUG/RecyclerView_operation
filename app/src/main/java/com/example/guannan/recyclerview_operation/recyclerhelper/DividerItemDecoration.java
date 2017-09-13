package com.example.guannan.recyclerview_operation.recyclerhelper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 自定义LinearLayoutManager和GridLayoutManager的分隔线
 * Created by guannan on 2017/9/11.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private final Paint mPaint;
    private float dividerHeight;    //分割线的高度

    /**
     * @param color         分隔线的颜色
     * @param dividerHeight 分隔线的粗细
     */
    public DividerItemDecoration(int color, float dividerHeight) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);    //根据itemView获取在RecyclerView中的位置
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {   //网格布局
            if (isLastColunm(parent,position)) {
                outRect.bottom = (int) dividerHeight;   //是最后一列的话，只绘制底部的分隔线
            } else if(isLastRow(parent,position)){
                outRect.right = (int) dividerHeight;    //是最后一行的话，只绘制右侧的分隔线
            }else{
                outRect.bottom = (int) dividerHeight;
                outRect.right = (int) dividerHeight;
            }
        } else {        //线性布局
            if (position != 0) {
                outRect.top = (int) dividerHeight;
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        int childCount = parent.getChildCount();
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            for (int i = 0; i < childCount; i++) {

                View childView = parent.getChildAt(i);
                int left = childView.getLeft();
                int top = childView.getTop();
                int right = childView.getRight();
                int bottom = childView.getBottom();
                int childAdapterPosition = parent.getChildAdapterPosition(childView);
                if (isLastColunm(parent,childAdapterPosition)) {    //最后一列的分隔线
                    canvas.drawRect(left, bottom, right, bottom+dividerHeight, mPaint);
                } else if(isLastRow(parent,childAdapterPosition)){ //最后一行的分隔线
                    canvas.drawRect(right, top, right+dividerHeight, bottom, mPaint);
                }else{
                    canvas.drawRect(left, bottom, right+dividerHeight, bottom+dividerHeight, mPaint);
                    canvas.drawRect(right, top, right+dividerHeight, bottom, mPaint);
                }
            }
        } else {
            //绘制线性布局的itemView之间的分隔线
            for (int i = 0; i < childCount; i++) {
                View itemView = parent.getChildAt(i);
                int childAdapterPosition = parent.getChildAdapterPosition(itemView);
                if (childAdapterPosition == 0) {    //如果是第一条，不绘制分割线
                    continue;
                }
                int dividerLeft = parent.getPaddingLeft();
                int dividerTop = (int) (itemView.getTop() - dividerHeight);
                int dividerRight = parent.getWidth() - parent.getPaddingRight();
                int dividerBottom = itemView.getTop();
                canvas.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, mPaint);
            }
        }
    }

    /**
     * 是否是最后一行
     * @param parent
     * @param position
     * @return
     */
    public boolean isLastRow(RecyclerView parent,int position){

        int itemCount = parent.getAdapter().getItemCount(); //itemView的总数
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = gridLayoutManager.getSpanCount();   //gridLayoutManager有多少列
        itemCount = itemCount - itemCount % spanCount;
        if(position>=itemCount)
            return true;
        else
            return false;
    }

    /**
     * 是否是最后一列
     * @param parent
     * @param position
     * @return
     */
    public boolean isLastColunm(RecyclerView parent,int position){

        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = gridLayoutManager.getSpanCount();
        if((position+1) % spanCount == 0)
            return true;
        else
            return false;
    }

}
