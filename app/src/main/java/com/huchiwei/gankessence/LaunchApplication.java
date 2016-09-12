package com.huchiwei.gankessence;

import android.app.Application;

/**
 * Application实现类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class LaunchApplication extends Application {
    private static LaunchApplication mApplication;

    public static LaunchApplication getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
