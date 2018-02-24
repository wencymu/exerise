package com.wency.android.exerise;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wency on 2018/1/19.
 */

public class App extends Application {
    private static final App ourInstance = new App();

    public static App getInstance() {
        return ourInstance;
    }

    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
