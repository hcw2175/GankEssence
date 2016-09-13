/*
 * Copyright (c) 2015 - 广州小橙信息科技有限公司 
 * All rights reserved.
 *
 * Created on 2016-08-11
 */
package com.huchiwei.gankessence.commonui.imagesview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 图片扩展,修复android:tint无效的BUG
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class TintImageView extends ImageView {

    public TintImageView(Context context) {
        super(context);

        initView();
    }

    public TintImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public TintImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList imageTintList = getImageTintList();
            if (imageTintList == null) {
                return;
            }

            setColorFilter(imageTintList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
        }
    }
}
