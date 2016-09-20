package com.huchiwei.gankessence.core.utils;

import android.support.v4.content.ContextCompat;

/**
 * APP静态资源文件辅助类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class ResourceUtil {
    /**
     * 获取字符串
     * @param id 资源id
     * @return 字符串
     */
    public static String getString(int id){
        return AppUtil.getContext().getResources().getString(id);
    }

    /**
     * 获取带参数字符串
     * @param id   资源id
     * @param args 参数值
     * @return 字符串
     */
    public static String getString(int id, Object... args){
        return AppUtil.getContext().getResources().getString(id, args);
    }

    /**
     * 获取颜色值
     * @param id 颜色id
     * @return 颜色值
     */
    public static int getColor(int id){
        return ContextCompat.getColor(AppUtil.getContext(), id);
    }

    /**
     * 获取常量
     * @param id 常量id
     * @return 常量值
     */
    public static int getDiments(int id){
        return AppUtil.getContext().getResources().getDimensionPixelSize(id);
    }
}
