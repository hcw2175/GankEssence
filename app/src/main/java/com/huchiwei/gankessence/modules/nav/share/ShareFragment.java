package com.huchiwei.gankessence.modules.nav.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.commonui.base.BaseFragment;

/**
 * 导航-分享Fragment
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class ShareFragment extends BaseFragment {

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.nav_share_frag;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected void onAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
