package com.huchiwei.gankessence.modules.articles.domain;

import com.huchiwei.gankessence.core.response.ResponseData;

import java.util.List;
import java.util.Map;

/**
 * 某日推荐文章信息
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class DailyArticles extends ResponseData{

    // ============================================================
    // fields =====================================================
    private List<String> category;
    private Map<String, List<Article>> results;


    // ==================================================================
    // methods ==========================================================
    private List<Article> getArticles(String category){
        return this.results.get(category);
    }

    // ==================================================================
    // setter/getter ====================================================
    public List<String> getCategory() {
        return category;
    }
    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Map<String, List<Article>> getResults() {
        return results;
    }
    public void setResults(Map<String, List<Article>> results) {
        this.results = results;
    }
}
