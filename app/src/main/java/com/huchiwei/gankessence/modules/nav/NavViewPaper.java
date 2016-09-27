/*
 * Copyright (c) 2015 - 广州小橙信息科技有限公司 
 * All rights reserved.
 *
 * Created on 2016-07-28
 */
package com.huchiwei.gankessence.modules.nav;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * NavViewPaper
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class NavViewPaper extends ViewPager {

    public NavViewPaper(Context context) {
        super(context);
    }

    public NavViewPaper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
