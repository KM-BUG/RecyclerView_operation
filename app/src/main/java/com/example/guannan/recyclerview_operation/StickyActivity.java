package com.example.guannan.recyclerview_operation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guannan.recyclerview_operation.adapter.StickyAdapter;
import com.example.guannan.recyclerview_operation.bean.Sticky;
import com.example.guannan.recyclerview_operation.recyclerhelper.TitleStickNavDecoration;

import java.util.ArrayList;

/**
 * @author guannan
 * @date 2018/2/28 16:45
 */

public class StickyActivity extends Activity {

    private ArrayList<Sticky> mStickyList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        setDatas();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new TitleStickNavDecoration(mStickyList,this));
        StickyAdapter stickyAdapter = new StickyAdapter(this, mStickyList);
        recyclerView.setAdapter(stickyAdapter);
    }

    private void setDatas() {

        mStickyList = new ArrayList<>();
        mStickyList.add(new Sticky("A", "apple苹果"));
        mStickyList.add(new Sticky("A", "Alpha透明"));
        mStickyList.add(new Sticky("A", "Age年龄"));
        mStickyList.add(new Sticky("B", "Band乐队"));
        mStickyList.add(new Sticky("B", "Brand商标"));
        mStickyList.add(new Sticky("B", "banana香蕉"));
        mStickyList.add(new Sticky("B", "Band乐队"));
        mStickyList.add(new Sticky("C", "Cute可爱"));
        mStickyList.add(new Sticky("C", "common普通"));
        mStickyList.add(new Sticky("C", "Cute可爱"));
        mStickyList.add(new Sticky("C", "common普通"));
        mStickyList.add(new Sticky("C", "Cute可爱"));
        mStickyList.add(new Sticky("C", "common普通"));
        mStickyList.add(new Sticky("C", "common普通"));
        mStickyList.add(new Sticky("C", "Cute可爱"));
        mStickyList.add(new Sticky("C", "common普通"));
        mStickyList.add(new Sticky("C", "Cute可爱"));
        mStickyList.add(new Sticky("C", "common普通"));
    }
}
