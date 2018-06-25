package com.example.editninecaserecyclerview.util;

import com.example.editninecaserecyclerview.activity.MainActivity;
import com.example.editninecaserecyclerview.activity.MoreActivity;
import com.example.editninecaserecyclerview.app.App;
import com.example.editninecaserecyclerview.constant.Constant;
import com.example.editninecaserecyclerview.interfaces.LoadApplyInterface;
import com.example.editninecaserecyclerview.model.ApplyTable;

import java.util.ArrayList;

/**
 * 通知更新数据类
 *
 * create by bthvi  2018/06/01
 */
public class LoadApplyImpl implements LoadApplyInterface {
    private MoreActivity moreActivity;
    public LoadApplyImpl(MoreActivity moreActivity){
        this.moreActivity = moreActivity;
    }
    @Override
    public void lodeApplyRequest() {
        ArrayList<ApplyTable> appayTableMine = (ArrayList<ApplyTable>) ACache.get(App.getAppContext()).getAsObject(Constant.APPLY_MINE);
        if(appayTableMine==null){
            appayTableMine= (ArrayList<ApplyTable>) ApplyTableManager.loadNewsChannelsStatic();
            ACache.get(App.getAppContext()).put(Constant.APPLY_MINE,appayTableMine);
        }
        moreActivity.returnMineApply(appayTableMine);
        ArrayList<ApplyTable> appayTableMore = (ArrayList<ApplyTable>) ACache.get(App.getAppContext()).getAsObject(Constant.APPLY_MORE);
        if(appayTableMore==null){
            appayTableMore = (ArrayList<ApplyTable>) ApplyTableManager.loadNewsChannelsMore();
            ACache.get(App.getAppContext()).put(Constant.APPLY_MORE,appayTableMore);
        }
        moreActivity.returnMoreNewsApply(appayTableMore);
    }

    @Override
    public void onItemSwap(ArrayList<ApplyTable> applyTableList) {

    }

    @Override
    public void onItemAddOrRemove(ArrayList<ApplyTable> mineApplyTableList, ArrayList<ApplyTable> moreApplyTableList) {
        ACache.get(App.getAppContext()).put(Constant.APPLY_MINE,mineApplyTableList);
        ACache.get(App.getAppContext()).put(Constant.APPLY_MORE,moreApplyTableList);
        MainActivity.getMa().applyRequest();
    }
}
