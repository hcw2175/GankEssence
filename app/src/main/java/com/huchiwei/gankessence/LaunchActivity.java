package com.huchiwei.gankessence;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.huchiwei.gankessence.core.cache.CacheUtil;
import com.huchiwei.gankessence.core.glide.ImagesUtil;
import com.huchiwei.gankessence.core.response.SimpleResult;
import com.huchiwei.gankessence.core.retrofit.RetrofitHelper;
import com.huchiwei.gankessence.core.rx.RxJavaHelper;
import com.huchiwei.gankessence.core.rx.RxSubscriber;
import com.huchiwei.gankessence.core.utils.AppUtil;
import com.huchiwei.gankessence.core.utils.StringUtil;
import com.huchiwei.gankessence.modules.api.GankServiceApi;
import com.huchiwei.gankessence.modules.common.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * App启动Splash页面Activity
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class LaunchActivity extends AppCompatActivity {

    @BindView(R.id.launch_bg)
    ImageView mLaunchBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_launch_act);

        ButterKnife.bind(this);

        // 隐藏导航栏
        AppUtil.hideNavgationBar(getWindow());

        // 判断缓存中是否有启动图片
        String launbg = CacheUtil.getString(Constants.LAUNCH_BG_CACHE_KEY);
        if(!StringUtil.isNull(launbg)){
            ImagesUtil.displayImage(this, mLaunchBackground, launbg);
        }

        // 拉取已经发过干货的日期
        List<String> dates = CacheUtil.getObject(Constants.GANK_DATES_CACHE_KEY, List.class);
        if(null != dates && dates.size() > 0){
            fetchDates(false);
        }else{
            fetchDates(true);

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoMainActivity();
                }
            }, 3000);
        }
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void fetchDates(final boolean justCache){
        RetrofitHelper.createApi(GankServiceApi.class)
                .fetchDatesOfArticles()
                .compose(RxJavaHelper.<SimpleResult<String>>observeOnMainThread())
                .subscribe(new RxSubscriber<SimpleResult<String>>() {

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                        if(!justCache)
                            gotoMainActivity();
                    }

                    @Override
                    public void onSuccess(SimpleResult<String> resp) {
                        CacheUtil.put(Constants.GANK_DATES_CACHE_KEY, resp.getResults());

                        if(!justCache)
                            gotoMainActivity();
                    }
                });
    }
}
