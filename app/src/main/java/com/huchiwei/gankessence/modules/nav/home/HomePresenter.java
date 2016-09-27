package com.huchiwei.gankessence.modules.nav.home;

import com.huchiwei.gankessence.core.response.SimpleResult;
import com.huchiwei.gankessence.core.retrofit.RetrofitHelper;
import com.huchiwei.gankessence.core.rx.RxJavaHelper;
import com.huchiwei.gankessence.core.rx.RxSubscriber;
import com.huchiwei.gankessence.core.utils.StringUtil;
import com.huchiwei.gankessence.modules.api.GankServiceApi;
import com.huchiwei.gankessence.modules.articles.entity.Article;
import com.huchiwei.gankessence.modules.articles.entity.DailyArticles;
import com.huchiwei.gankessence.modules.nav.home.entity.HomeArticles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 首页Presenter实现
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void fetchArticles(String date) {
        GankServiceApi gankServiceApi = RetrofitHelper.createApi(GankServiceApi.class);

        this.fetchArticlesByDate(gankServiceApi, date);

        // 获取某日的网站数据
        gankServiceApi
                .fetchHomeArticles(date)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<SimpleResult<HomeArticles>>() {
                    @Override
                    public void onSuccess(SimpleResult<HomeArticles> homeArticles) {
                        List<HomeArticles> homeArticlesContent = homeArticles.getResults();

                        String imageUrl = "";
                        if(null != homeArticlesContent && homeArticlesContent.size() > 0){
                            List<String> images = StringUtil.extractedImages(homeArticlesContent.get(0).getContent());
                            if(null != images && images.size() > 0){
                                imageUrl = images.get(0); // 网站数据第一个图片就是妹纸图片
                            }
                        }
                        mView.updateGirlImage(imageUrl);
                    }
                });
    }

    /**
     * 按日期加载文章数据
     * @param gankServiceApi api
     * @param date           指定日期
     */
    protected void fetchArticlesByDate(GankServiceApi gankServiceApi, String date){
        gankServiceApi
                .fetchByDate(date)
                .compose(RxJavaHelper.<DailyArticles>observeOnMainThread())
                .subscribe(new RxSubscriber<DailyArticles>() {
                    @Override
                    public void onSuccess(DailyArticles dailyArticles) {
                        List<Article> articles = new ArrayList<>();

                        List<String> categories = dailyArticles.getCategory();
                        Map<String, List<Article>> map = dailyArticles.getResults();
                        if(categories.size() > 0){
                            for(String category : categories){
                                List<Article> categoryArticles = map.get(category);
                                for(Article article : categoryArticles){
                                    article.setCategory(category);
                                    articles.add(article);
                                }
                            }
                        }

                        mView.updateArticles(articles);
                    }
                });
    }
}
