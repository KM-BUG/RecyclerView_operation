package com.example.guannan.recyclerview_operation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.guannan.recyclerview_operation.bean.ItemData;

import java.util.ArrayList;

/**
 * 自定义的时间轴和分隔线
 * Created by guannan on 2017/9/12.
 */

public class TimeLineDecoration extends RecyclerView.ItemDecoration {

    private final float mOffsetLeft;    //条目的左边边距
    private final float mOffsetRight;   //条目的右边边距
    private float dividerHeight;    //分隔线的高度
    private final Paint mPaint;
    private ArrayList<ItemData> mItemDatas;
    private Context mContext;

    public TimeLineDecoration(Context context, ArrayList<ItemData> itemDatas) {

        this.mContext = context;
        mOffsetLeft = context.getResources().getDimension(R.dimen.offset_left);
        mOffsetRight = context.getResources().getDimension(R.dimen.offset_right);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        this.mItemDatas = itemDatas;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition != 0) {
            dividerHeight = 2;
            outRect.top = 2;
        }
        outRect.left = (int) mOffsetLeft;   //设置itemView左边的边距，留出空间绘制时间轴
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {  //遍历RecyclerView的每一个itemView进行时间轴的计算和绘制
            View childView = parent.getChildAt(i);
            int childAdapterPosition = parent.getChildAdapterPosition(childView);   //获取当前ItemView的位置position
            drawTimeLine(canvas, parent, childView, childAdapterPosition);
            drawDividerLine(canvas, parent, childView, childAdapterPosition);
        }
    }

    /**
     * 绘制分隔线
     * @param canvas
     * @param parent
     * @param childView
     * @param childAdapterPosition
     */
    private void drawDividerLine(Canvas canvas, RecyclerView parent, View childView, int childAdapterPosition) {
        if(childAdapterPosition != 0){
            int dividerLeft = childView.getLeft();
            int dividerTop = (int) (childView.getTop() - dividerHeight);
            int dividerRight = parent.getWidth() - parent.getPaddingRight();
            int dividerBottom = childView.getTop();
            canvas.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,mPaint);
        }
    }

    /**
     * 绘制时间轴
     * @param canvas
     * @param parent
     * @param childView
     * @param childAdapterPosition
     */
    private void drawTimeLine(Canvas canvas, RecyclerView parent, View childView, int childAdapterPosition) {
        int timeLineTop;
        if (childAdapterPosition == 0) { //第一个itemView不设置分隔线，所以高度为零
            timeLineTop = childView.getTop();
        } else
            timeLineTop = (int) (childView.getTop() - dividerHeight);
        //时间轴的bottom
        int timeLineBottom = childView.getBottom();
        //时间轴的中心点坐标
        int centerX = (int) (parent.getPaddingLeft() + mOffsetLeft / 2);
        int centerY = (timeLineTop + timeLineBottom) / 2;
        //获取时间轴图片
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.time_point);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //绘制图片上方的直线
        canvas.drawLine(centerX, timeLineTop, centerX, centerY + height / 2, mPaint);
        //图片所占大小的矩形
        Rect rect = new Rect();
        rect.left = centerX - width / 2;
        rect.top = centerY - height / 2;
        rect.right = centerX + width / 2;
        rect.bottom = centerY + height / 2;
        //绘制时间轴图片
        canvas.drawBitmap(bitmap, null, rect, mPaint);
        //绘制图片下方的直线
        canvas.drawLine(centerX, centerY + height / 2, centerX, timeLineBottom, mPaint);
    }
}
