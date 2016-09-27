package com.huchiwei.gankessence.commonui.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.huchiwei.gankessence.R;

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

        // 始终竖屏
        disableLandOrientation();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        setToolBar();
    }


    /**
     * 禁止水平旋转
     */
    private void disableLandOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void setToolBar(){
        if(!isNeedToolbar())
            return;

        // 设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // 设置toolbar
        Toolbar mToolBar = (Toolbar) findViewById(R.id.widget_toolbar);
        if (null != mToolBar) {
            setSupportActionBar(mToolBar);

            ActionBar actionBar = getSupportActionBar();
            if(null != actionBar){
                // 获取并设置标题, 0表示不设置标题
                int titleResourceId = getToolbarTitle();
                if(titleResourceId > 0){
                    actionBar.setTitle(titleResourceId);
                } else {
                    actionBar.setTitle(null);
                }

                // 是否显示返回按钮
                if(this.isShowBackButton()){
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                }
            }
        }
    }

    // ==================================================================
    // protected methods ================================================
    protected boolean isNeedToolbar(){
        return true;
    }

    protected boolean isShowBackButton(){
        return false;
    }

    protected int getToolbarTitle(){
        return R.string.app_name;
    }
}
