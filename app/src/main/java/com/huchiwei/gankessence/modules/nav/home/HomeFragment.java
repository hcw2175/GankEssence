package com.huchiwei.gankessence.modules.nav.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.commonui.base.BaseFragment;
import com.huchiwei.gankessence.core.cache.CacheUtil;
import com.huchiwei.gankessence.core.glide.ImagesUtil;
import com.huchiwei.gankessence.core.utils.DateUtil;
import com.huchiwei.gankessence.core.utils.LogUtil;
import com.huchiwei.gankessence.core.utils.ResourceUtil;
import com.huchiwei.gankessence.core.utils.StringUtil;
import com.huchiwei.gankessence.modules.articles.entity.Article;
import com.huchiwei.gankessence.modules.articles.entity.DailyArticles;
import com.huchiwei.gankessence.modules.common.Constants;
import com.huchiwei.gankessence.modules.nav.home.apdater.HomeGankListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 导航-首页Fragment
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class HomeFragment extends BaseFragment implements HomeContract.View{

    @BindView(R.id.home_girl)
    ImageView mHomeGirl;
    @BindView(R.id.gank_date)
    TextView mGankDate;
    @BindView(R.id.gank_list)
    RecyclerView mGankList;

    private HomePresenter mPresenter;
    private HomeGankListAdapter mListAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.nav_home_frag;
    }

    @Override
    protected void fetchData() {
        if(null == mPresenter)
            mPresenter = new HomePresenter(this);

        List<String> dates = CacheUtil.getObject(Constants.GANK_DATES_CACHE_KEY, List.class);
        if(null != dates){
            String date = DateUtil.format(DateUtil.parseDate(dates.get(0)), "yyyy/MM/dd");
            mGankDate.setText(date);
            mPresenter.fetchArticles(date);
        }

        LogUtil.debug("fetchData: home fragment");
    }

    @Override
    protected void onAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setToolBar(view, R.id.widget_toolbar);

        ButterKnife.bind(this, view);

        // 初始化Adapter
        mListAdapter = new HomeGankListAdapter(getContext(), R.layout.nav_home_gank_list);

        // 最新活动
        mGankList.setHasFixedSize(true);
        mGankList.setLayoutManager(new LinearLayoutManager(getContext()));
        mGankList.setNestedScrollingEnabled(false);
        mGankList.setAdapter(mListAdapter);
    }

    @Override
    public void updateGirlImage(String imageUrl) {
        if(StringUtil.isNull(imageUrl)){
            ImagesUtil.displayImage(getContext(), mHomeGirl, R.drawable.launch);
        }else{
            ImagesUtil.displayImage(getContext(), mHomeGirl, imageUrl);
        }
        LogUtil.debug("updateGirlImage: " + imageUrl);
    }

    @Override
    public void updateArticles(List<Article> articles) {
        mListAdapter.setData(articles);
    }
}
