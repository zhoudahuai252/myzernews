package com.zhoubenliang.myzernews.ui.adapter;

import android.content.Context;

import com.common.view.apdater.BasicAdapter;
import com.common.view.apdater.BasicViewHolder;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.pojo.GankDetails;

import java.util.List;

/**
 * author miekoz on 2016/3/18.
 * email  meikoz@126.com
 */
public class MainAdapter extends BasicAdapter<GankDetails> {

    public MainAdapter(Context context, List<GankDetails> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(BasicViewHolder helper, GankDetails item) {
        helper.setText(R.id.tv_test, item.desc);
    }

}
