package com.example.editninecaserecyclerview.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
/**
 * 应用
 *
 * create by bthvi  2018/06/01
 */
public class App extends Application {
    private static App baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
