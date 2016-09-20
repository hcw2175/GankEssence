package com.huchiwei.gankessence.modules.articles.api;

import com.huchiwei.gankessence.modules.articles.domain.DailyArticles;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 文章请求api
 *
 * @author huchiwei
 * @version 1.0.0
 */
public interface ArticleServiceApi {

    @GET("day/{year}/{month}/{day}")
    Observable<DailyArticles> fetchByDate(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
