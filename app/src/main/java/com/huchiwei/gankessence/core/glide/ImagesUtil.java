package com.huchiwei.gankessence.core.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.core.glide.transform.CircleImageTransform;
import com.huchiwei.gankessence.core.glide.transform.RoundedCornersTransformation;
import com.huchiwei.gankessence.core.util.ResourceUtil;

/**
 * 图片辅助类
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class ImagesUtil {
    private static final int ERROR_IMAGE = R.drawable.common_placehoder;
    private static final int AVATAR_IMAGE = R.drawable.common_avatar;

    /**
     * 显示图片
     * @param context   上下文
     * @param imageView 图片view
     * @param uri       图片URL
     */
    public static void displayImage(Context context, ImageView imageView, String uri){
        display(context, imageView, uri, null, false);
    }

    /**
     * 显示圆形图片
     * @param context   上下文
     * @param imageView 图片view
     * @param uri       图片URL
     */
    public static void displayCircleImage(Context context, ImageView imageView, String uri){
        display(context, imageView, uri, new CircleImageTransform(context), false);
    }

    /**
     * 显示圆形图片
     * @param context   上下文
     * @param imageView 图片view
     * @param uri       图片URL
     * @param isAvatar  是否用户头像,显示错误时设为默认头像
     */
    public static void displayCircleImage(Context context, ImageView imageView, String uri, boolean isAvatar){
        display(context, imageView, uri, new CircleImageTransform(context), isAvatar);
    }

    /**
     * 显示圆角图片
     * @param context   上下文
     * @param imageView 图片view
     * @param uri       图片URL
     */
    public static void displayRoundImage(Context context, ImageView imageView, String uri){
        int radius = ResourceUtil.getDiments(R.dimen.cardview_default_radius);
        display(context, imageView, uri, new RoundedCornersTransformation(context, 20, 0), false);
    }

    /**
     * 显示资源图片
     * @param context    上下文
     * @param imageView  图片view
     * @param resourceId 图片资源id
     */
    public static void displayImage(Context context, final ImageView imageView, int resourceId){
        Glide
                .with(context)
                .load(resourceId)
                .centerCrop()
                .placeholder(ERROR_IMAGE)
                .error(ERROR_IMAGE)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 显示图片
     * @param context     上下文
     * @param imageView   图片view
     * @param uri         图片路径
     * @param bitmapTransformation bitmap转换器
     */
    private static void display(Context context, final ImageView imageView, String uri, BitmapTransformation bitmapTransformation, boolean isAvatar){
        DrawableTypeRequest<String> drawableTypeRequest = Glide.with(context).load(uri);
        drawableTypeRequest
                .centerCrop()
                .placeholder(ERROR_IMAGE)
                .error(isAvatar ? AVATAR_IMAGE : ERROR_IMAGE)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        // 添加转换器
        if(null != bitmapTransformation){
            drawableTypeRequest.bitmapTransform(bitmapTransformation);
        }

        // 设置图片
        drawableTypeRequest.into(imageView);
    }
}
