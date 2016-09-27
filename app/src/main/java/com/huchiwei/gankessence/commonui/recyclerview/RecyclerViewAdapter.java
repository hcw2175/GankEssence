package com.huchiwei.gankessence.commonui.recyclerview;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huchiwei.gankessence.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView通用Adapter
 *
 * @author huchiwei
 * @version 1.0.0
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder>{
    private static final int ITEM_TYPE_DEFAULT     = 0;
    private static final int ITEM_TYPE_LOADMORE    = 100000;
    private static final int BASE_ITEM_TYPE_HEADER = 200000;
    private static final int BASE_ITEM_TYPE_FOOTER = 300000;

    // ============================================================
    // fields =====================================================
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;

    private int mItemLayoutId;                                                    // item布局id
    private List<T> mDatas;                                                       // 数据源

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();     // 头部View列表
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();     // 底部View列表

    private boolean isLoadMoreEnable = false;                                     // 是否启用lord more

    // ==================================================================
    // constructor ======================================================
    public RecyclerViewAdapter(Context context, int layoutId){
        mContext = context;
        mItemLayoutId = layoutId;
        mDatas = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public RecyclerViewAdapter(Context context, int layoutId, List<T> data){
        mContext = context;
        mItemLayoutId = layoutId;
        mDatas = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    // ==================================================================
    // methods ==========================================================
    @Override
    public int getItemViewType(int position) {
        if(isHeaderPosition(position)){
            return mHeaderViews.keyAt(position);
        } else if (isFooterPosition(position)){
            return mFooterViews.keyAt(position - getHeaderViewCount() - getRealItemCount());
        } else if(isLoadMorePosition(position)){
            return ITEM_TYPE_LOADMORE;
        }
        return ITEM_TYPE_DEFAULT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(null != mHeaderViews.get(viewType)){
            return new ViewHolder(mContext, mHeaderViews.get(viewType), ViewHolder.VT_HEADER);
        } else if(null != mFooterViews.get(viewType)){
            return new ViewHolder(mContext, mFooterViews.get(viewType), ViewHolder.VT_FOOTER);
        } else if(viewType == ITEM_TYPE_LOADMORE){
            return ViewHolder.create(mContext, parent, R.layout.widget_load_more);
        }
        return ViewHolder.create(mContext, parent, mItemLayoutId);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Header View 或 Footer View 直接返回
        if(isHeaderPosition(position) || isFooterPosition(position) || isLoadMorePosition(position))
            return;

        covert(holder, position);
    }

    @Override
    public int getItemCount() {
        return getRealItemCount() + getHeaderViewCount() + getFooterViewCount();
    }

    public int getRealItemCount(){
        return mDatas.size();
    }

    // ==================================================================
    // data methods =====================================================
    /**
     * 设置数据源
     * @param data 数据
     */
    public void setData(List<T> data){
        this.mDatas.clear();
        this.mDatas = data;
        notifyDataSetChanged();
    }

    /**
     * 插入数据
     * @param data 数据源
     */
    public void insertData(List<T> data){
        int size = mDatas.size();
        mDatas.addAll(data);
        notifyItemRangeChanged(size-1, data.size());
    }

    /**
     * 获取数据
     * @param position 位置
     * @return
     */
    public T getDataItem(int position){
        return mDatas.get(position);
    }

    public void clearData(){
        this.mDatas.clear();
    }

    // ==================================================================
    // header and footer methods ========================================
    /**
     * 判断是否Header位置
     * @param position 位置
     * @return 返回true则是,否则不是
     */
    public boolean isHeaderPosition(int position){
        return position < mHeaderViews.size();
    }

    /**
     * 判断是否Footer位置
     * @param position 位置
     * @return 返回true则是,否则不是
     */
    public boolean isFooterPosition(int position){
        return position >= mHeaderViews.size() + getRealItemCount();
    }

    /**
     * 添加Header View
     * @param view Header View
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加Footer View
     * @param view Footer View
     */
    public void addFootView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeaderViewCount(){
        return mHeaderViews.size();
    }

    public int getFooterViewCount(){
        return mFooterViews.size();
    }

    /**
     * 隐藏或者显示对应位置Header视图
     * @param position 位置
     * @param visible  可见参数
     */
    public void setHeaderViewsVisible(int position, int visible){
        if(getHeaderViewCount() == 0)
            return;

        mHeaderViews.valueAt(position).setVisibility(visible);
    }

    /**
     * 隐藏或者显示对应位置Footer视图
     * @param position 位置
     * @param visible  可见参数
     */
    public void setFooterViewsVisible(int position, int visible){
        if(getFooterViewCount() == 0)
            return;

        mFooterViews.valueAt(position).setVisibility(visible);
    }

    // ==================================================================
    // load more methods ================================================
    /**
     * 禁用或启用load more view
     * @param isEnable
     */
    public void setLoadMoreEnable(boolean isEnable){
        this.isLoadMoreEnable = isEnable;
    }

    public boolean isLoadMorePosition(int position){
        return isLoadMoreEnable && position+1 >= getHeaderViewCount() + getRealItemCount();
    }

    public boolean canLoadMore(){
        return isLoadMoreEnable;
    }

    // ==================================================================
    // abstract methods =================================================
    /**
     * 数据转换函数
     * @param holder    CommonViewHolder
     * @param position  item位置
     */
    public abstract void covert(ViewHolder holder, int position);
}
