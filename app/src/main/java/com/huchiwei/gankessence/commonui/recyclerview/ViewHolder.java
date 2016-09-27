package com.huchiwei.gankessence.commonui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huchiwei.gankessence.core.glide.ImagesUtil;

/**
 * 通用ViewHolder,简化操作
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    public static final int VT_HEADER = 0;
    public static final int VT_ITEM = 1;
    public static final int VT_FOOTER = 2;

    // ============================================================
    // fields =====================================================
    private Context mContext;                                    // 上下文
    private View mCoverView;                                     // 列表每条项目对应的view
    private SparseArray<View> mViews;                            // itemView布局上子视图的集合,如TextView,ImageView等

    private int type;                                            // 类型,参考VT开头常量
    private boolean isLastItem;                                  // 是否最后一条

    // ==================================================================
    // constructor ======================================================
    public ViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mCoverView = itemView;
        mViews = new SparseArray<>();
        this.type = VT_ITEM;
    }

    public ViewHolder(Context context, View itemView, int type) {
        super(itemView);
        mContext = context;
        mCoverView = itemView;
        mViews = new SparseArray<>();
        this.type = type;
    }

    // ==================================================================
    // methods ==========================================================
    /**
     * 创建ViewHolder
     * @param context             上下文
     * @param parent              上级布局
     * @param itemViewLayoutId    布局id
     * @return CommonViewHolder
     */
    public static ViewHolder create(Context context, ViewGroup parent, int itemViewLayoutId){
        View itemView = LayoutInflater.from(context).inflate(itemViewLayoutId, parent, false);
        return new ViewHolder(context, itemView);
    }

    /**
     * 获取item布局
     * @return View
     */
    public View getItemView(){
        return mCoverView;
    }

    /**
     * 查找布局上的View
     * @param viewId  子View id
     * @return 如TextView,ImageView等
     */
    public View findViewById(int viewId){
        View view = mViews.get(viewId);
        if(null == view){
            view = mCoverView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 是否数据项类型
     * @return
     */
    public boolean isDataItem(){
        return this.type == VT_ITEM;
    }

    /**
     * 是否Header类型
     * @return
     */
    public boolean isHeader(){
        return this.type == VT_HEADER;
    }

    /**
     * 是否Footer类型
     * @return
     */
    public boolean isFooter(){
        return this.type == VT_FOOTER;
    }

    /**
     * 是否最后一项
     * @return
     */
    public boolean isLastItem() {
        return isLastItem;
    }

    // ==================================================================
    // child view methods ===============================================
    /**
     * 设置文本
     * @param viewId ViewId
     * @param text   文本
     */
    public void setText(int viewId, String text){
        TextView textView = (TextView) findViewById(viewId);
        textView.setText(text);
    }

    /**
     * 设置图片
     * @param viewId   ViewId
     * @param imageUrl 图片路径
     */
    public void setImage(int viewId, String imageUrl){
        ImageView imageView = (ImageView) findViewById(viewId);
        ImagesUtil.displayImage(mContext, imageView, imageUrl);
    }

    /**
     * 设置图片
     * @param viewId   ViewId
     * @param resource 文本
     */
    public void setImage(int viewId, int resource){
        ImageView imageView = (ImageView) findViewById(viewId);
        ImagesUtil.displayImage(mContext, imageView, resource);
    }

    /**
     * 设置圆形图片
     * @param viewId   ViewId
     * @param imageUrl 图片路径
     */
    public void setCircleImage(int viewId, String imageUrl){
        ImageView imageView = (ImageView) findViewById(viewId);
        ImagesUtil.displayCircleImage(mContext, imageView, imageUrl);
    }

    /**
     * 设置是否显示
     * @param viewId
     * @param visible
     */
    public void setVisible(int viewId, int visible){
        View view = findViewById(viewId);
        view.setVisibility(visible);
    }

    /**
     * 设置是否选中
     * @param viewId
     * @param selected
     */
    public void setSelected(int viewId, boolean selected){
        View view = findViewById(viewId);
        view.setSelected(selected);
    }
}
