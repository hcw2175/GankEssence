package com.huchiwei.gankessence.commonui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity基础封装类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLandOrientation();
    }

    /**
     * 禁止水平旋转
     */
    private void disableLandOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
