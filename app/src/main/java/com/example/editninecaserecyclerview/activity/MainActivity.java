package com.example.editninecaserecyclerview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.editninecaserecyclerview.R;
import com.example.editninecaserecyclerview.adapter.MainAdapter;
import com.example.editninecaserecyclerview.app.App;
import com.example.editninecaserecyclerview.constant.Constant;
import com.example.editninecaserecyclerview.model.ApplyTable;
import com.example.editninecaserecyclerview.model.MainModel;
import com.example.editninecaserecyclerview.util.ACache;
import com.example.editninecaserecyclerview.util.ApplyTableManager;

import java.util.ArrayList;
import java.util.List;
/***
 * 首页
 *
 * create by bthvi  2018/06/01
 */
public class MainActivity extends Activity {
    private static MainActivity ma;
    private SwipeRefreshLayout mSrl;
    private RecyclerView mRv;
    private List<ApplyTable> mTables = new ArrayList<>();
    private List<MainModel> mainModels = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ma = this;
        findView();
        initData();
    }

    public static MainActivity getMa() {
        return ma;
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mainModels.add(new MainModel(i, "假数据：" + 1));
        }
        adapter = new MainAdapter(this, mainModels, mTables);
        mRv.setAdapter(adapter);
        applyRequest();
    }

    private void findView() {
        mSrl = findViewById(R.id.mSrl);
        mRv = findViewById(R.id.mRv);
        mRv.setHasFixedSize(true);//作用就是确保尺寸是通过用户输入从而确保RecyclerView的尺寸是一个常数,当不是瀑布流时，设置这个可以避免重复的增删造成而外的浪费资源
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 更新保存首页数据
     */
    public void applyRequest() {
        ArrayList<ApplyTable> appayTableMine = (ArrayList<ApplyTable>) ACache.get(App.getAppContext()).getAsObject(Constant.APPLY_MINE);
        if (appayTableMine == null) {
            appayTableMine = (ArrayList<ApplyTable>) ApplyTableManager.loadNewsChannelsStatic();
            ACache.get(App.getAppContext()).put(Constant.APPLY_MINE, appayTableMine);
        }
        returnMineApply(appayTableMine);
    }

    /**
     * 头部里面的频道个数发生改变时调用该方法修改头部的频道个数
     *
     * @param tables
     */
    public void returnMineApply(List<ApplyTable> tables) {
        System.out.println(tables);
        System.out.println("频道个数:" + tables.size());
        mTables.clear();
        mTables.addAll(tables);
        addAllChannel();
        if (adapter != null) {
            adapter.setTables(mTables);
            adapter.notifyItemChanged(0);
        }
    }

    /**
     * 添加跳转到管理新闻频道的界面
     */
    private void addAllChannel() {
        if (mTables != null) {
            ApplyTable allChannel = new ApplyTable("全部", "", "", "", 0, true, R.mipmap.quanbu,0);
            mTables.add(allChannel);
        }
    }
}
