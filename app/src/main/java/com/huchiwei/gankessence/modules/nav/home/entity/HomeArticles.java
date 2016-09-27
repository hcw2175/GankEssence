package com.huchiwei.gankessence.modules.nav.home.entity;

import com.huchiwei.gankessence.core.response.IdEntity;

/**
 * Gank网站HTML数据
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class HomeArticles extends IdEntity {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
