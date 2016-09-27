package com.huchiwei.gankessence;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.huchiwei.gankessence.commonui.base.BaseActivity;
import com.huchiwei.gankessence.commonui.base.BaseFragment;
import com.huchiwei.gankessence.core.utils.LogUtil;
import com.huchiwei.gankessence.modules.nav.NavViewPaper;
import com.huchiwei.gankessence.modules.nav.NavViewPaperAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * App主Activity
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nav_bottomBar)
    BottomBar mBottomBar;

    @BindView(R.id.nav_content)
    NavViewPaper mNavContent;

    private NavViewPaperAdapter mViewPaperAdapter;
    private BaseFragment mCurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_act);

        ButterKnife.bind(this);

        // 初始化页面ViewPaper
        mViewPaperAdapter = new NavViewPaperAdapter(getSupportFragmentManager());
        mNavContent.setAdapter(mViewPaperAdapter);
        mNavContent.setOffscreenPageLimit(4);

        // 获取当前Fragment
        mCurFragment = (BaseFragment) mViewPaperAdapter.getItem(mNavContent.getCurrentItem());

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int position = mBottomBar.findPositionForTabWithId(tabId);
                updateCurNav(position);
            }
        });
    }

    private void updateCurNav(int position){
        if(null != mCurFragment)
            mCurFragment.fadeOut();
        mNavContent.setCurrentItem(position, false);
        mCurFragment = (BaseFragment) mViewPaperAdapter.getItem(mNavContent.getCurrentItem());
        mCurFragment.fadeIn();
    }
}
