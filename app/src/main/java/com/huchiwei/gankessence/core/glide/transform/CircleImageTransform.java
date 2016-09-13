/*
 * Copyright (c) 2015 - 广州小橙信息科技有限公司 
 * All rights reserved.
 *
 * Created on 2016-07-19
 */
package com.huchiwei.gankessence.core.glide.transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 圆形图片
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class CircleImageTransform extends BitmapTransformation {

    public CircleImageTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int width = (source.getWidth() - size) / 2;
        int height = (source.getHeight() - size) / 2;

        // 获取源文件
        Bitmap sourceBitMap = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (null == sourceBitMap) {
            sourceBitMap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        // 绘制圆形图片
        Canvas canvas = new Canvas(sourceBitMap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        if (width != 0 || height != 0) {
            // source isn't square, move viewport to center
            Matrix matrix = new Matrix();
            matrix.setTranslate(-width, -height);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return sourceBitMap;
    }

    @Override
    public String getId() {
        return "CircleImageTransform";
    }
}
