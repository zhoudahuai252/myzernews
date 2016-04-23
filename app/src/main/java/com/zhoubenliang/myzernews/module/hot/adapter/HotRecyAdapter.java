package com.zhoubenliang.myzernews.module.hot.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.model.control.GlideProxy;
import com.orhanobut.logger.Logger;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.hot.bean.ArticlesBean;
import com.zhoubenliang.myzernews.ui.activity.WebViewActivity;

import java.util.List;

/**
 * 作者:MR_zhouBL on 2016/4/23.
 * 邮箱:554524787@qq.com
 */
public class HotRecyAdapter extends RecyclerView.Adapter<HotRecyAdapter.Holder> {
    private List<ArticlesBean> mDataList;
    private Context mContext;

    public HotRecyAdapter(Context context, List<ArticlesBean> articlesBeen) {
        this.mContext = context;
        this.mDataList = articlesBeen;
    }

    @Override
    public HotRecyAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.hot_recyview_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(HotRecyAdapter.Holder holder, int position) {
        ArticlesBean articlesBean = mDataList.get(position);
        holder.tvTitle.setText(articlesBean.getTitle());
        holder.tvBom.setText(articlesBean.getAuther_name() + "  " + articlesBean.getDate());
        List<ArticlesBean.ThumbnailMediasBean> medias =
                articlesBean.getThumbnail_medias();
        if (medias != null) {
            GlideProxy.getInstance().loadNetworkImage(mContext
                    , medias.get(0).getUrl()
                    , holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView tvTitle;
        private TextView tvBom;

        public Holder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_hot);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvBom = (TextView) itemView.findViewById(R.id.tv_bom);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Logger.d("onClick");
            //点击跳转到webview
            Intent intent = new Intent(mContext, WebViewActivity.class);
            int position = getLayoutPosition();
            intent.putExtra("url", mDataList.get(position).getUrl());
            mContext.startActivity(intent);
        }
    }
}
