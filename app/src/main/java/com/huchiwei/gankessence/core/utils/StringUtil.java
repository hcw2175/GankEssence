/**
 * Copyright (c) 2015 - 广州小橙信息科技有限公司
 * All rights reserved.
 *
 * Created on 2015-09-09
 */
package com.huchiwei.gankessence.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @author huchiwei
 * @since 1.0.0
 */
public class StringUtil {

    /**
     * 判断是否空字符串
     * @param str 目标字符串
     * @return 返回true则为空，否则为false
     */
    public static boolean isNull(String str){
        return null == str || str.equals("") || str.equals("null");
    }

    /**
     * 获取其中的图片
     * @param content 字符串
     * @return
     */
    public static List<String> extractedImages(String content) {
        List<String> imageList = new ArrayList<>();
        if(StringUtil.isNull(content))
            return imageList;

        // 解析图片地址
        Matcher matcher = Pattern.compile("<img.*src=(.*?)[^>]*?>", Pattern.CASE_INSENSITIVE).matcher(content);
        while(matcher.find()){
            String matchText = matcher.group();
            //匹配src
            Matcher m  = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)",Pattern.CASE_INSENSITIVE).matcher(matchText);
            while(m.find()){
                imageList.add(m.group().replace("src=\"", "").replace("\"", ""));
            }
        }
        return imageList;
    }
}