package com.example.editninecaserecyclerview.util;

import com.example.editninecaserecyclerview.R;
import com.example.editninecaserecyclerview.app.App;
import com.example.editninecaserecyclerview.constant.Constant;
import com.example.editninecaserecyclerview.model.ApplyTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 九宫格数据缓存管理
 *
 * create by bthvi  2018/06/01
 */
public class ApplyTableManager {
    private static int[] fixed = new int[]{0, 1};

    /**
     * 编辑应用九宫格显示数据
     *
     * @return
     */
    public static List<ApplyTable> loadNewsChannelsMore() {
        List<String> channelName = Arrays.asList(App.getAppContext().getResources().getStringArray(R.array.all_apply_name));
        List<String> channelId = Arrays.asList(App.getAppContext().getResources().getStringArray(R.array.all_apply_id));
        List<Integer> channelImgRes = new ArrayList<Integer>();
        channelImgRes.add(R.mipmap.home);
        channelImgRes.add(R.mipmap.shangdian);
        channelImgRes.add(R.mipmap.baojie);
        channelImgRes.add(R.mipmap.ditie);
        channelImgRes.add(R.mipmap.gongjiao);
        channelImgRes.add(R.mipmap.gongnuan);
        channelImgRes.add(R.mipmap.lingyu);
        channelImgRes.add(R.mipmap.loupan);
        channelImgRes.add(R.mipmap.xiyiji);
        channelImgRes.add(R.mipmap.weiyu);
        channelImgRes.add(R.mipmap.xuexiao);
        channelImgRes.add(R.mipmap.yantai);
        channelImgRes.add(R.mipmap.yihuan);
        ArrayList<ApplyTable> newsChannelTables = new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            ApplyTable entity = new ApplyTable(
                    channelName.get(i),
                    channelId.get(i),
                    Constant.getType(channelId.get(i)),
                    "",
                    i,
                    isFixed(i),
                    channelImgRes.get(i),
                    i < 6 ? 0 : 1);
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }

    /**
     * 首页九宫格显示数据
     *
     * @return
     */

    public static List<ApplyTable> loadNewsChannelsStatic() {
        List<String> channelName = Arrays.asList(App.getAppContext().getResources().getStringArray(R.array.all_apply_name_s));
        System.out.println(channelName);
        List<String> channelId = Arrays.asList(App.getAppContext().getResources().getStringArray(R.array.all_apply_id_s));
        List<Integer> channelImgRes = new ArrayList<Integer>();
        channelImgRes.add(R.mipmap.home);
        channelImgRes.add(R.mipmap.shangdian);
        channelImgRes.add(R.mipmap.baojie);
        channelImgRes.add(R.mipmap.ditie);
        channelImgRes.add(R.mipmap.gongjiao);
        channelImgRes.add(R.mipmap.gongnuan);
        ArrayList<ApplyTable> newsChannelTables = new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            ApplyTable entity = new ApplyTable(channelName.get(i), channelId.get(i)
                    , Constant.getType(channelId.get(i)), "", i, isFixed(i), channelImgRes.get(i), 0);
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }

    public static boolean isFixed(int i) {
        for (int j = 0; j < fixed.length; j++) {
            return i == fixed[j] ? true : false;
        }
        return false;
    }
}
