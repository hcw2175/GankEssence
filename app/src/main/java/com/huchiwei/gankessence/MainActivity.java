package com.huchiwei.gankessence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huchiwei.gankessence.core.response.ResponseData;
import com.huchiwei.gankessence.core.retrofit.RetrofitHelper;
import com.huchiwei.gankessence.core.rx.RxJavaHelper;
import com.huchiwei.gankessence.core.rx.RxSubscriber;
import com.huchiwei.gankessence.core.utils.LogUtil;
import com.huchiwei.gankessence.modules.articles.api.ArticleServiceApi;
import com.huchiwei.gankessence.modules.articles.domain.Article;
import com.huchiwei.gankessence.modules.articles.domain.DailyArticles;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitHelper.createApi(ArticleServiceApi.class)
                .fetchByDate("2016", "09", "01")
                .compose(RxJavaHelper.<DailyArticles>observeOnMainThread())
                .subscribe(new RxSubscriber<DailyArticles>() {
                    @Override
                    public void onSuccess(DailyArticles dailyArticles) {
                        LogUtil.debug("onSuccess: " + dailyArticles.getCategory().toString());
                    }
                });
    }
}
