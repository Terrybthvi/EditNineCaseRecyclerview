package com.example.editninecaserecyclerview.util;

import android.util.Log;

/**
 * 输出日志工具类
 *
 * create by bthvi  2018/06/01
 */
public class Logs {
    public static void I(String title, String str) {
        Log.i("eee", title + " --- " + str);
    }

    public static void I(String str) {
        Log.i("eee", str);
    }
}
