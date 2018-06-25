package com.example.editninecaserecyclerview.interfaces;

import com.example.editninecaserecyclerview.model.ApplyTable;

import java.util.List;

/**
 * 通知界面更新接口
 *
 * create by bthvi  2018/06/01
 *
 */
public interface ApplyView {
    void returnMineApply(List<ApplyTable> newsChannelsMine);

    void returnMoreNewsApply(List<ApplyTable> newsChannelsMore);
}
