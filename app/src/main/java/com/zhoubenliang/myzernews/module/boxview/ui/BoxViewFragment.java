package com.zhoubenliang.myzernews.module.boxview.ui;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.common.view.base.BaseFragment;
import com.orhanobut.logger.Logger;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.boxview.adapter.MyRecycDapter;
import com.zhoubenliang.myzernews.module.boxview.bean.BoxMenuBean;
import com.zhoubenliang.myzernews.module.boxview.bean.BoxViewImageBean;
import com.zhoubenliang.myzernews.module.boxview.dao.BoxViewDao;
import com.zhoubenliang.myzernews.module.boxview.dao.NetworkHodler;
import com.zhoubenliang.myzernews.ui.activity.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

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
    @Bind(R.id.box_recy)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private final CompositeSubscription pendingSubscriptions =
            new CompositeSubscription();
    private List<BoxViewImageBean.DataBean.ListBean> mBannnerList;
    private List<BoxMenuBean.DataBean.BlocksDataBean> mBoxMenuData;
    private RecyclerView.Adapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.boxview_fragment_layout;
    }

    @Override
    protected void onInit() {
        mBannnerList = new ArrayList<>();
        mBoxMenuData = new ArrayList<>();
        adapter = new MyRecycDapter(mBoxMenuData, getActivity());
        initToolbar();

    }

    private void initToolbar() {
        mToolbar.setTitle("订阅");
        mToolbar.inflateMenu(R.menu.menu_box_fragment);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.btn_search:
                        //跳转搜索页面
                        startActivity(new Intent(getActivity(), SearchActivity.class));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    @Override
    protected void onInitEvent() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);
        //设置广告页
        mConvenientBanner.setPages(new CBViewHolderCreator<NetworkHodler>() {
            @Override
            public NetworkHodler createHolder() {
                return new NetworkHodler();
            }
        }, mBannnerList);
        mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到详情页
                BoxViewImageBean.DataBean.ListBean.ArticleBean articleBean = mBannnerList.
                        get(position).getArticle();
                if (articleBean != null) {
                    //说明点击进入webView,展示文章详情
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", articleBean.getWeburl());
                    startActivity(intent);

                } else {
                    // TODO: 2016/4/22 进入主题页
                }
            }
        });
    }

    @Override
    protected void onLoadData() {
        doBanner();
        doBoxMenu();

    }

    private void doBoxMenu() {
        Observable<BoxMenuBean> boxMenuBeanObservable = BoxViewDao
                .getMainIns().getBoxMenu("AndroidPhone");
        boxMenuBeanObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BoxMenuBean>() {
                    @Override
                    public void call(BoxMenuBean boxMenuBean) {
                        List<BoxMenuBean.DataBean.BlocksDataBean> data = boxMenuBean
                                .getData().getBlocksData();
                        Logger.d(data.size() + "");
                        mBoxMenuData.addAll(data);
                        adapter.notifyDataSetChanged();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable.toString());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        mConvenientBanner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }

    private void doBanner() {
        Observable<BoxViewImageBean> imageBeanObservable = BoxViewDao
                .getMainIns().getData("AndroidPhone");
        imageBeanObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BoxViewImageBean>() {
                    @Override
                    public void call(BoxViewImageBean boxViewImageBean) {
                        Log.d("BoxViewFragment", "--->" + boxViewImageBean.toString());
                        mBannnerList.addAll(boxViewImageBean.getData().getList());
                        mConvenientBanner.notifyDataSetChanged();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("BoxViewFragment", "throwable:" + throwable);
                    }
                });

    }


}
