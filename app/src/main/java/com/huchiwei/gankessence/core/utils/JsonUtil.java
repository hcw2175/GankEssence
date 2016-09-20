/*
 * Copyright (c) 2015 - 广州小橙信息科技有限公司 
 * All rights reserved.
 *
 * Created on 2016-08-04
 */
package com.huchiwei.gankessence.core.utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSON工具类,基于Gson操作
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class JsonUtil {

    /**
     * 从json转换为指定对象
     * @param json     json
     * @param target   指定对象
     * @return 转换对象
     */
    public static <T> T fromJson(String json, Class<T> target){
        Gson gson = new Gson();
        return gson.fromJson(json, target);
    }

    /**
     * 将对象转换为JSONObject
     * @param target   指定对象
     * @return 转换对象
     */
    public static JSONObject toJsonObject(Object target){
        try {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(target);
            return new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象转换为JSONObject
     * @param target   指定对象
     * @return 转换对象
     */
    public static String toJson(Object target){
        Gson gson = new Gson();
        return gson.toJson(target);
    }
}
