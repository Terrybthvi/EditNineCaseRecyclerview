package com.example.editninecaserecyclerview.interfaces;

import com.example.editninecaserecyclerview.model.ApplyTable;

import java.util.ArrayList;


/**
 * 加载应用接口
 *
 * create by bthvi  2018/06/01
 *
 */
public interface LoadApplyInterface {
    void lodeApplyRequest();

    void onItemSwap(ArrayList<ApplyTable> applyTableList);

    void onItemAddOrRemove(ArrayList<ApplyTable> mineApplyTableList, ArrayList<ApplyTable> moreApplyTableList);
}
