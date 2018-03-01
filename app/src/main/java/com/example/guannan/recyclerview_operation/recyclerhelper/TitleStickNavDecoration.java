package com.example.guannan.recyclerview_operation.recyclerhelper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.example.guannan.recyclerview_operation.bean.Sticky;

import java.util.ArrayList;

/**
 * @author guannan
 * @date 2018/2/28 16:43
 */

public class TitleStickNavDecoration extends RecyclerView.ItemDecoration {

    private ArrayList<Sticky> mList;
    private final Paint mPaint;
    private Context mContext;
    private final Paint mPaintBg;
    private int stickyHeight;

    public TitleStickNavDecoration(ArrayList<Sticky> list, Context context) {
        this.mList = list;
        this.mContext = context;
        //悬浮文字
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(dpTopx(16));
        //悬浮文字背景
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setAlpha(255);
        mPaintBg.setColor(Color.parseColor("#DFDFDF"));
        stickyHeight = dpTopx(30);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, recyclerView, state);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        int position = params.getViewLayoutPosition();
        if (isFirstVisibleItem(position)) {
            outRect.top = stickyHeight;
        }
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        //当前界面有多少个item
        int childCount = parent.getChildCount();
        //总共有多少个item
        int itemCount = state.getItemCount();

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        String curCategory = null;
        String preCategory;
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(itemView);

            preCategory = curCategory;
            curCategory = mList.get(position).getCategory();
            if(curCategory==null || TextUtils.equals(preCategory,curCategory)){
                //当前类型和之前类型相同，直接跳过当前操作
                continue;
            }
            //绘制悬浮
            int viewBottom = Math.max(stickyHeight, (itemView.getTop() + parent.getPaddingTop()));//决定当前顶部第一个悬浮Group的bottom
            int bottom = itemView.getBottom();
            if(position+1<itemCount){
                curCategory = mList.get(position).getCategory();
                String nextCategory = mList.get(position+1).getCategory();
                if (!curCategory.equals(nextCategory) && bottom < viewBottom) {
                    viewBottom = bottom;
                }
            }
            canvas.drawRect(left, viewBottom - stickyHeight, right, viewBottom, mPaintBg);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float textHeight = fontMetrics.bottom - fontMetrics.top;
            canvas.drawText(mList.get(position).getCategory(), (left + right) / 2, viewBottom - stickyHeight / 2 + textHeight / 2, mPaint);
        }
    }

    /**
     * 是否是同一组中第一个item
     *
     * @param position
     * @return
     */
    private boolean isFirstVisibleItem(int position) {
        if (position == 0) {
            return true;
        } else {
            String curCategory = mList.get(position).getCategory();
            String preCategory = mList.get(position - 1).getCategory();
            return !TextUtils.equals(curCategory, preCategory);
        }
    }

    /**
     * dp转换为px
     *
     * @param dp
     * @return
     */
    private int dpTopx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }

}
