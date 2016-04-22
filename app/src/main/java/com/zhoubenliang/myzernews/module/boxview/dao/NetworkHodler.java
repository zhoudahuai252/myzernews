package com.zhoubenliang.myzernews.module.boxview.dao;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.common.model.control.GlideProxy;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.boxview.bean.BoxViewImageBean;

/**
 * 作者:Administrator on 2016/4/22.
 * 邮箱:554524787@qq.com
 */
public class NetworkHodler implements Holder<BoxViewImageBean.DataBean.ListBean> {
    private ImageView mImageView;
    private TextView mTextView;

    @Override
    public View createView(Context context) {
        View view = View.inflate(context, R.layout.banner_layout_item, null);
        mImageView = (ImageView) view.findViewById(R.id.iv_pic);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mTextView = (TextView) view.findViewById(R.id.tv_txt);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, BoxViewImageBean.DataBean.ListBean data) {
        mImageView.setImageResource(R.mipmap.ic_launcher);
        mTextView.setText(data.getTitle());
        String strUrl = data.getPromotion_img();
        GlideProxy.getInstance().loadNetworkImage(context, strUrl, mImageView);
    }
}
