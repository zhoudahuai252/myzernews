package com.zhoubenliang.myzernews.ui.fragment;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.common.view.base.BaseFragment;
import com.zhoubenliang.myzernews.R;

import butterknife.Bind;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class BoxViewFragment extends BaseFragment {
    @Bind(R.id.convenientBanner)
    ConvenientBanner mConvenientBanner;

    @Override
    protected int getLayoutResource() {
        return R.layout.boxview_fragment_layout;
    }

    @Override
    protected void onInitData() {
        //TODO 获取网络数据

    }
}
