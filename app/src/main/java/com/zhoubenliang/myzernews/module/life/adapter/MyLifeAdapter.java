package com.zhoubenliang.myzernews.module.life.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.model.control.GlideProxy;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.life.bean.LIfeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:Administrator on 2016/4/25.
 * 邮箱:554524787@qq.com
 */
public class MyLifeAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEAD_TYPE = 1;
    private static final int SECOND_TYPE = 2;
    private static final int TITLE_TYPE = 3;
    private static final int NOM_TYPE = 4;
    LIfeBean mLIfeBean;
    Context mContext;
    List<LIfeBean.DataBean.ColumnsBean.ItemsBean> mData;

    public MyLifeAdapter(Context context, LIfeBean ifeBean) {
        mData = new ArrayList<>();
        mLIfeBean = ifeBean;
        mContext = context;
        intiData();
    }

    public void refreshData(LIfeBean ifeBean) {
        mLIfeBean = ifeBean;
        this.notifyDataSetChanged();
        intiData();
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        List<LIfeBean.DataBean.ColumnsBean> columns = mLIfeBean.getData().getColumns();
        int sum = 0;
        for (int i = 0; i < columns.size(); i++) {

            if (position == sum) {
                return TITLE_TYPE;
            }
            sum += columns.get(i).getItems().size();
        }
        return NOM_TYPE;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TITLE_TYPE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_rcy_title, parent, false);
            return new TitledHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_rcy_notitle, parent, false);
            return new NoTitleHolder(view);
        }


    }

    private void intiData() {
        if (mLIfeBean.getData() != null) {
            List<LIfeBean.DataBean.ColumnsBean> columns =
                    mLIfeBean.getData().getColumns();
            for (int i = 0; i < columns.size(); i++) {
                List<LIfeBean.DataBean.ColumnsBean.ItemsBean> items =
                        columns.get(i).getItems();
                mData.addAll(items);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == TITLE_TYPE) {
            TitledHolder titledHolder = (TitledHolder) holder;
            int pos = position / 5;
            titledHolder.mTextView.setText(mLIfeBean.getData().getColumns().get(pos).getTitle());
            String imgUrl = mData.get(position).getPic().getUrl();
            GlideProxy.getInstance().loadNetworkImage(mContext, imgUrl, titledHolder.mImageView);
        } else {
            NoTitleHolder noTitleHolder = (NoTitleHolder) holder;
            String imgUrl = mData.get(position).getPic().getUrl();
            GlideProxy.getInstance().loadNetworkImage(mContext, imgUrl, noTitleHolder.mImageView);
        }

    }


    @Override
    public int getItemCount() {
        int count = 0;
        if (mLIfeBean.getData() != null) {
            List<LIfeBean.DataBean.ColumnsBean> columns =
                    mLIfeBean.getData().getColumns();
            for (int i = 0; i < columns.size(); i++) {
                count += columns.get(i).getItems().size();
            }
        }
        return count;
    }


    /**
     * 没有头标题布局
     */
    public class NoTitleHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public NoTitleHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_show);
        }
    }

    /**
     * 有头标题布局
     */
    public class TitledHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        public TitledHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_show);
        }
    }
}
