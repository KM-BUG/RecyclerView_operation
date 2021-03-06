package com.example.guannan.recyclerview_operation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //带分隔线和单击，长按
    public void clickWithDivider(View view){
        Intent intent = new Intent(this, RecyclerViewWithDividerActivity.class);
        startActivity(intent);
    }

    //带分隔线和时间轴,单击，长按
    public void clickWithTimeLine(View view){
        Intent intent = new Intent(this, RecyclerViewWithTimeLineActivity.class);
        startActivity(intent);
    }

    //侧滑删除，拖拽排序
    public void dragClick(View view){
        Intent intent = new Intent(this, RecyclerViewWithDividerAndMoveActivity.class);
        startActivity(intent);
    }

    //悬停效果
    public void stickyClick(View view){
        Intent intent = new Intent(this, StickyActivity.class);
        startActivity(intent);
    }


}
