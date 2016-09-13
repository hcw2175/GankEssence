package com.huchiwei.gankessence;

import android.app.Application;

import com.huchiwei.gankessence.core.util.AppUtil;
import com.huchiwei.gankessence.core.util.ResourceUtil;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

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

        // 配置Logger
        Logger
                .init(ResourceUtil.getString(R.string.app_name_en))
                .methodCount(2)
                .hideThreadInfo()
                .logLevel(AppUtil.isDev() ? LogLevel.FULL : LogLevel.NONE)
                .methodOffset(2);
    }
}
