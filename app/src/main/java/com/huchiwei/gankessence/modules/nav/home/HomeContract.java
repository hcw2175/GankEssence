package com.huchiwei.gankessence.modules.nav.home;

import com.huchiwei.gankessence.modules.articles.entity.Article;

import java.util.List;

/**
 * 首页Contract
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class HomeContract {

    public interface Presenter{
        /**
         * 拉取指定日期数据
         * @param date 日期，格式：yyyy/MM/dd
         */
        void fetchArticles(String date);
    }

    public interface View{

        /**
         * 更新妹纸图片
         * @param imageUrl 图片URL
         */
        void updateGirlImage(String imageUrl);

        /**
         * 更新指定日期文章数据
         * @param articles 指定日期文章数据
         */
        void updateArticles(List<Article> articles);
    }
}
