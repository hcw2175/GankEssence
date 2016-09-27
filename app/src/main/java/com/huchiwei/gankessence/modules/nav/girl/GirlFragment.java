package com.huchiwei.gankessence.modules.nav.girl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.commonui.base.BaseFragment;

/**
 * 导航-妹纸Fragment
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class GirlFragment extends BaseFragment {

    public static GirlFragment newInstance() {
        return new GirlFragment();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.nav_girl_frag;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected void onAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
