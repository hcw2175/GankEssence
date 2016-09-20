package com.huchiwei.gankessence.core.response;

import java.util.List;

/**
 * 响应结果基类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class ResponseData {

    // ============================================================
    // fields =====================================================
    private boolean error;                             // 是否请求错误

    // ==================================================================
    // setter/getter ====================================================
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }
}
