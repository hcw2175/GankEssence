package com.huchiwei.gankessence.modules.nav.home.apdater;

import android.content.Context;
import android.view.View;

import com.huchiwei.gankessence.R;
import com.huchiwei.gankessence.commonui.recyclerview.RecyclerViewAdapter;
import com.huchiwei.gankessence.commonui.recyclerview.ViewHolder;
import com.huchiwei.gankessence.core.utils.DateUtil;
import com.huchiwei.gankessence.modules.articles.entity.Article;

import java.util.List;

/**
 * 首页干货列表适配器
 *
 * @author huchiwei
 * @version 1.0.0
 */
public class HomeGankListAdapter extends RecyclerViewAdapter<Article> {

    public HomeGankListAdapter(Context context, int layoutId, List<Article> data) {
        super(context, layoutId, data);
    }

    public HomeGankListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void covert(ViewHolder holder, int position) {
        Article article = this.getDataItem(position);
        holder.setText(R.id.gank_title, article.getDesc());
        holder.setText(R.id.gank_who, article.getWho());
        holder.setText(R.id.gank_publish, DateUtil.format(DateUtil.parseDate(article.getPublishedAt()), "MM-dd"));
        holder.setText(R.id.gank_category, article.getCategory());

        if(position == 0){
            holder.setVisible(R.id.gank_category, View.VISIBLE);
        }else{
            Article prevArticle = this.getDataItem(position-1);
            if(!prevArticle.getCategory().equalsIgnoreCase(article.getCategory())){
                holder.setVisible(R.id.gank_category, View.VISIBLE);
            }
        }
    }
}
