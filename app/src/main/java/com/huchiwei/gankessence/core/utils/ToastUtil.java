package com.huchiwei.gankessence.core.utils;

import android.widget.Toast;

/**
 * Toast辅助类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class ToastUtil {

    public static void show(String msg){
        Toast.makeText(AppUtil.getContext(), msg, Toast.LENGTH_SHORT).show();    }
}
