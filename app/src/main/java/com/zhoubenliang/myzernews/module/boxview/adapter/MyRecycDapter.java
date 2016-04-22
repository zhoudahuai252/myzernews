package com.zhoubenliang.myzernews.module.boxview.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.model.control.GlideCallBack;
import com.common.model.control.GlideProxy;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.boxview.bean.BoxMenuBean;
import com.zhoubenliang.myzernews.ui.activity.TopicActivity;

import java.util.List;

/**
 * 作者:Administrator on 2016/4/22.
 * 邮箱:554524787@qq.com
 */
public class MyRecycDapter extends android.support.v7.widget.RecyclerView.Adapter<MyRecycDapter.MyHolder> {
    List<BoxMenuBean.DataBean.BlocksDataBean> mBoxMenuData;
    Context mContext;

    public MyRecycDapter(List<BoxMenuBean.DataBean.BlocksDataBean> boxMenuData, Context mContext) {
        this.mBoxMenuData = boxMenuData;
        this.mContext = mContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.box_menu_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        //设置文本文字
        BoxMenuBean.DataBean.BlocksDataBean dataBean = mBoxMenuData.get(position);
        holder.mTextView.setText(dataBean.getBlock_title());
        //在文本上方画图片
        String strUrl = dataBean.getPic();//图片网址
        final String block_color = dataBean.getBlock_color();

        GlideProxy.getInstance().withCallBack(mContext, strUrl, new GlideCallBack() {
            @Override
            public void callBack(Bitmap b) {
                int[] pixels = new int[b.getWidth() * b.getHeight()];
                b.getPixels(pixels, 0, b.getWidth(), 0, 0, b.getWidth(), b.getHeight());
                for (int i = 0; i < pixels.length; i++) {
                    if (pixels[i] != 0) {
                        if (block_color == null)
                            pixels[i] = Color.parseColor("#000000");
                        else
                            pixels[i] = Color.parseColor(block_color);
                    }
                }
                Bitmap bm = Bitmap.createBitmap(pixels, b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
                holder.mImageView.setImageBitmap(bm);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBoxMenuData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private ImageView mImageView;

        public MyHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_top);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //点击BoxMenu跳转
            Intent intent = new Intent(mContext, TopicActivity.class);
            intent.putExtra("api_url", mBoxMenuData.get(getLayoutPosition()).getApi_url());
            mContext.startActivity(intent);
        }
    }
}
