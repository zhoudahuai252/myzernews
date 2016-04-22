package com.zhoubenliang.myzernews.module.hot.ui;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.ListView;

import com.common.view.base.BaseFragment;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.hot.dao.HotDao;

import butterknife.Bind;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class HotFragment extends BaseFragment {
    @Bind(R.id.lv_hot)
    ListView mListView;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_hot_layout;
    }

    @Override
    protected void onLoadData() {
        HotDao.getMainIns().getHotData("AndroidPhone","");
    }

    @Override
    protected void onInit() {
        String imei =((TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();

//        String IMEI =android.os.SystemProperties.get(android.telephony.TelephonyProperties.PROPERTY_IMEI)
    }

    @Override
    protected void onInitEvent() {

    }
}
