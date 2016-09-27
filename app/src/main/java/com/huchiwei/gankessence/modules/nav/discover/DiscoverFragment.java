package com.huchiwei.gankessence.modules.nav.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.commonui.base.BaseFragment;

/**
 * 导航-发现Fragment
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class DiscoverFragment extends BaseFragment {

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.nav_discover_frag;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected void onAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
