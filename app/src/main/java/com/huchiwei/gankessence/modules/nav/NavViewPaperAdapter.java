package com.huchiwei.gankessence.modules.nav;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huchiwei.gankessence.modules.nav.discover.DiscoverFragment;
import com.huchiwei.gankessence.modules.nav.girl.GirlFragment;
import com.huchiwei.gankessence.modules.nav.home.HomeFragment;
import com.huchiwei.gankessence.modules.nav.share.ShareFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航页面Fragment适配器
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class NavViewPaperAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();

    public NavViewPaperAdapter(FragmentManager fm) {
        super(fm);

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(DiscoverFragment.newInstance());
        mFragments.add(GirlFragment.newInstance());
        mFragments.add(ShareFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
