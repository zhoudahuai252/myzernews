package com.zhoubenliang.myzernews.module.boxview.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.common.view.base.BaseFragment;
import com.zhoubenliang.myzernews.R;

import butterknife.Bind;

/**
 * 作者:MR_zhouBL on 2016/4/24.
 * 邮箱:554524787@qq.com
 */
public class AddFragment extends BaseFragment {
    @Bind(R.id.tab)
    TabLayout mTabLayout;
    @Bind(R.id.vp_add)
    ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_add;
    }

    @Override
    protected void onInit() {
        mTabLayout.addTab(mTabLayout.newTab().setText("精品").
                setIcon(R.drawable.shap_line_tab));
        mTabLayout.addTab(mTabLayout.newTab().setText("频道").setIcon(R.drawable.shap_line_tab));
        mTabLayout.setTabTextColors(R.color.black, R.color.themeColor);
        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {

                    return new JingPFragment();
                } else {
                    return new PingDFragment();
                }

            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position==0){
                    return  "精品";
                }else
                    return "频道";
            }
        };
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onInitEvent() {

    }

    @Override
    protected void onLoadData() {

    }
}
