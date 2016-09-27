package com.huchiwei.gankessence.modules.api;

import com.huchiwei.gankessence.core.response.SimpleResult;
import com.huchiwei.gankessence.modules.articles.entity.DailyArticles;
import com.huchiwei.gankessence.modules.nav.home.entity.HomeArticles;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 干货集中营公开api
 *
 * @author huchiwei
 * @version 1.0.0
 */
public interface GankServiceApi {

    /**
     * 获取发过文章数据的日期
     * @return
     */
    @GET("day/history")
    Observable<SimpleResult<String>> fetchDatesOfArticles();

    /**
     * 获取特定日期的网站数据
     * @param date 日期，格式为yyyy/month/date
     * @return
     */
    @GET("history/content/day/{date}")
    Observable<SimpleResult<HomeArticles>> fetchHomeArticles(@Path("date") String date);

    /**
     * 按日期请求文章数据
     * @param date 日期，格式为yyyy/month/date
     * @return
     */
    @GET("day/{date}")
    Observable<DailyArticles> fetchByDate(@Path("date") String date);
}
