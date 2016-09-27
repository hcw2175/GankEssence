package com.huchiwei.gankessence.core.response;

import java.util.List;

/**
 * API返回的普通响应数据，格式是：
 * {
 *     error: false,
 *     results: [obj, obj2, ... , obj5]
 * }
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class SimpleResult<T> extends ResponseData {

    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
