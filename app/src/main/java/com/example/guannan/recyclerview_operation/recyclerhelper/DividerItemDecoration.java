package com.example.guannan.recyclerview_operation.recyclerhelper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 自定义分隔线
 * Created by guannan on 2017/9/11.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private final Paint mPaint;
    private float dividerHeight;    //分割线的高度

    /**
     * @param color 分隔线的颜色
     * @param dividerHeight 分隔线的粗细
     */
    public DividerItemDecoration(int color,float dividerHeight){

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);    //根据itemView获取在RecyclerView中的位置
        if(position!=0){
            outRect.top = (int) dividerHeight;
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);

        //绘制itemView之间的分隔线
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);

            int childAdapterPosition = parent.getChildAdapterPosition(itemView);
            if(childAdapterPosition==0){    //如果是第一条，不绘制分割线
                continue;
            }
            int dividerLeft = parent.getPaddingLeft();
            int dividerTop = (int) (itemView.getTop() - dividerHeight);
            int dividerRight = parent.getWidth() - parent.getPaddingRight();
            int dividerBottom = itemView.getTop();
            canvas.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,mPaint);
        }

    }

}
