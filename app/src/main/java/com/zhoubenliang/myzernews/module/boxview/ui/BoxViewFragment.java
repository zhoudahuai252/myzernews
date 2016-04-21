package com.zhoubenliang.myzernews.module.boxview.ui;

import android.util.Log;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.common.view.base.BaseFragment;
import com.orhanobut.logger.Logger;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.boxview.bean.BoxViewImageBean;
import com.zhoubenliang.myzernews.module.boxview.dao.BoxViewDao;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class BoxViewFragment extends BaseFragment {
    @Bind(R.id.convenientBanner)
    ConvenientBanner mConvenientBanner;
    private final CompositeSubscription pendingSubscriptions =
            new CompositeSubscription();

    @Override
    protected int getLayoutResource() {
        return R.layout.boxview_fragment_layout;
    }

    @Override
    protected void onInitData() {
        Logger.d("------我是谁--------->");
//        Map<String, String> parmsMap = new HashMap<>();
//        parmsMap.put("_appid", "AndroidPhone");
        Observable<BoxViewImageBean> imageBeanObservable = BoxViewDao
                .getMainIns().getData("AndroidPhone");
        imageBeanObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BoxViewImageBean>() {
                    @Override
                    public void call(BoxViewImageBean boxViewImageBean) {
                        Log.d("BoxViewFragment", "--->" + boxViewImageBean.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("BoxViewFragment", "throwable:" + throwable);
                    }
                });
    }

    @Override
    protected void onInit() {
        Logger.d("------我是谁--------->");
    }

    @Override
    protected void onInitEvent() {

    }
}
